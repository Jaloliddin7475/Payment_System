package personal_project.service.transaction;


import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import personal_project.dto.request.agent.DefaultAgentRequest;
import personal_project.dto.response.agent.BaseAgentResponse;
import personal_project.dto.response.agent.BaseCheckResponse;
import personal_project.entity.agent.AgentEntity;
import personal_project.entity.merchant.MerchantEntity;
import personal_project.entity.merchant.MerchantServiceEntity;
import personal_project.entity.oson.OsonServiceEntity;
import personal_project.entity.transaction.TransactionEntity;
import personal_project.entity.transaction.TransactionState;
import personal_project.repository.OsonRepository;
import personal_project.repository.TransactionRepository;
import personal_project.repository.agent.AgentRepository;
import personal_project.service.gateway.PaymeTransactionService;
import personal_project.service.gateway.PaynetTransactionService;

import java.math.BigDecimal;

@Service
@RequiredArgsConstructor
public class CheckTransactionService {
    private final OsonRepository osonRepository;
//    private final MerchantRepository merchantRepository;
    private final PaymeTransactionService paymeTransactionService;
    private final PaynetTransactionService paynetTransactionService;
    private final TransactionRepository transactionRepository;
    private final AgentRepository agentRepository;


    public BaseAgentResponse check(
            DefaultAgentRequest defaultAgentRequest,
            String username) {

        AgentEntity agentEntity = agentRepository.findByUsername(username);
        //// TODO: 11.04.2022 check agent validity
        if (agentEntity == null || !agentEntity.isActive())
            throw new UsernameNotFoundException("Agent not found");

        OsonServiceEntity osonServiceEntity = osonRepository.findById(defaultAgentRequest.getServiceId()).get();

        TransactionEntity transactionEntity = new TransactionEntity();

        transactionEntity.setTransactionState(TransactionState.CREATED.getState());
        transactionEntity.setTransactionAccount(defaultAgentRequest.getAccount());
        transactionEntity.setTransactionAmountFromAgent(defaultAgentRequest.getAmount());
        transactionEntity.setTransactionAmountToMerchant(
                getMerchantAmount(defaultAgentRequest.getAmount(), agentEntity)
        );
        transactionEntity.setAgent(agentEntity);
        transactionEntity.setOsonServiceEntity(osonServiceEntity);


        MerchantServiceEntity merchantServiceEntity = osonServiceEntity.getMerchantServiceEntity();
        MerchantEntity merchantEntity = merchantServiceEntity.getMerchantEntity();

        transactionEntity.setMerchant(merchantEntity);

        transactionRepository.save(transactionEntity);

        TransactionEntity transaction = requestToMerchant(
                merchantServiceEntity,
                merchantEntity,
                transactionEntity);

        transactionRepository.save(transaction);

        return BaseCheckResponse.response(
                agentEntity,
                merchantEntity,
                merchantServiceEntity,
                transactionEntity
        );
    }


    private TransactionEntity requestToMerchant
            (
                    MerchantServiceEntity merchantServiceEntity,
                    MerchantEntity merchantEntity,
                    TransactionEntity transactionEntity
            ) {

        if (merchantEntity.isPayme()) {
            transactionEntity = paymeTransactionService.createTransaction
                    (
                            transactionEntity,
                            merchantServiceEntity
                    );
        } else if (merchantEntity.isClick()) {
            //TODO
        } else if (merchantEntity.isPaynet()) {
//           transactionEntity = paynetTransactionService.(
//                    transactionEntity
//            );
        }

        return transactionEntity;
    }


    private BigDecimal getMerchantAmount(BigDecimal amount, AgentEntity agentEntity) {
        return amount.subtract(amount.multiply(BigDecimal.valueOf(0.1)));
    }
}
