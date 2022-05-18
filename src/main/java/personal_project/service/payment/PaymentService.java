package personal_project.service.payment;


import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import personal_project.dto.response.agent.BaseAgentResponse;
import personal_project.dto.response.agent.BaseCheckResponse;
import personal_project.entity.agent.AgentDepositEntity;
import personal_project.entity.agent.AgentServiceEntity;
import personal_project.entity.merchant.MerchantEntity;
import personal_project.entity.transaction.TransactionEntity;
import personal_project.entity.transaction.TransactionState;
import personal_project.exception.DataNotFoundException;
import personal_project.repository.TransactionRepository;
import personal_project.repository.agent.AgentRepository;
import personal_project.repository.agent.AgentServiceRepository;
import personal_project.service.gateway.PaymeTransactionService;
import personal_project.service.gateway.PaynetTransactionService;

import java.math.BigDecimal;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PaymentService {

    private final TransactionRepository transactionRepository;
    private final AgentServiceRepository agentServiceRepository;
    private final AgentRepository agentRepository;
    private final PaymeTransactionService paymeTransactionService;
    private final PaynetTransactionService paynetTransactionService;


    public BaseAgentResponse pay(Long transactionId, String agentUsername){

        Optional<AgentDepositEntity> agentDepositEntity = agentRepository.getAgentDepositByUsername(agentUsername);
        Optional<TransactionEntity> optionalTransaction = transactionRepository.findById(transactionId);

       // TODO: 12.04.2022 give an appropriate name to
        if(optionalTransaction.isEmpty() || agentDepositEntity.isEmpty())
            throw new DataNotFoundException("Bad request");

        TransactionEntity transactionEntity1 = optionalTransaction.get();
        transactionEntity1.setTransactionState(TransactionState.PAYING.getState());
        manageAgentDeposit(transactionEntity1, agentDepositEntity.get(), false);

        //todo Request to Merchant

        TransactionEntity transactionEntity = payRequestToMerchant(transactionEntity1);

        if(transactionEntity.getTransactionState() == TransactionState.PAY_ERROR.getState())
            manageAgentDeposit(transactionEntity1, agentDepositEntity.get(), true);



        return BaseCheckResponse.response(
                transactionEntity.getAgent(),
                transactionEntity.getMerchant(),
                transactionEntity.getOsonServiceEntity().getMerchantServiceEntity(),
                transactionEntity
        );

    }

    private void manageAgentDeposit(TransactionEntity transaction, AgentDepositEntity agentDeposit, boolean isRollback){
        Long serviceId = transaction.getOsonServiceEntity().getId();
        Long agentId = transaction.getAgent().getId();
        Optional<AgentServiceEntity> optionalAgentServiceEntity = agentServiceRepository.findByServiceIdAndAgentId(serviceId, agentId);

        if(optionalAgentServiceEntity.isEmpty())
            throw new DataNotFoundException("Agent Service not found");

        double commission = optionalAgentServiceEntity.get().getCommission();

        if(!isRollback)
            calculate(agentDeposit, transaction.getTransactionAmountFromAgent(), commission);
        else
            calculate(agentDeposit, transaction.getTransactionAmountFromAgent(), (2 * (-1 - commission)));
    }


    private void calculate(
            AgentDepositEntity agentDeposit,
            BigDecimal transactionAmount,
            double commission
            ){

        agentDeposit.setAmount(agentDeposit
                .getAmount()
                .subtract(transactionAmount.multiply(BigDecimal.valueOf(1 + commission)))
        );

        // TODO: 12.04.2022 here commission * transactionAmount should save our budget
    }

    private TransactionEntity payRequestToMerchant
            (
                    TransactionEntity transactionEntity
            ) {
        MerchantEntity merchantEntity = transactionEntity.getMerchant();

        transactionRepository.save(transactionEntity);

        return transactionEntity;
    }


}