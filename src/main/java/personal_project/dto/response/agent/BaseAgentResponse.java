package personal_project.dto.response.agent;

import personal_project.entity.agent.AgentEntity;
import personal_project.entity.merchant.MerchantEntity;
import personal_project.entity.merchant.MerchantServiceEntity;
import personal_project.entity.transaction.TransactionEntity;
import personal_project.entity.transaction.TransactionState;

public interface BaseAgentResponse {
    default BaseAgentResponse response(
            AgentEntity agent,
            MerchantEntity merchantEntity,
            MerchantServiceEntity merchantServiceEntity,
            TransactionEntity transaction
    ) {
        if (transaction.getTransactionState() == TransactionState.CHECKED.getState()) {
//            return success(agent, merchantEntity, merchantServiceEntity, transaction);
            return success(transaction);
        } else {
            return error(transaction);
        }
    }
    BaseAgentResponse success(TransactionEntity transaction);
    BaseAgentResponse error(TransactionEntity transaction);
}
