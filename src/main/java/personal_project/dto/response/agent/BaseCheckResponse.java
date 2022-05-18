package personal_project.dto.response.agent;

import personal_project.dto.response.agent.paynet.PaynetAgentCheckResponse;
import personal_project.entity.agent.AgentEntity;
import personal_project.entity.merchant.MerchantEntity;
import personal_project.entity.merchant.MerchantServiceEntity;
import personal_project.entity.transaction.TransactionEntity;

public interface BaseCheckResponse {
    static BaseAgentResponse response(
            AgentEntity agent,
            MerchantEntity merchantEntity,
            MerchantServiceEntity merchantServiceEntity,
            TransactionEntity transaction
    ) {
        BaseAgentResponse baseAgentResponse;
        if (agent.isPaynet()) {
            baseAgentResponse = new PaynetAgentCheckResponse();
        } else {
            baseAgentResponse = new DefaultAgentResponse();
        }
        return baseAgentResponse.response(agent, merchantEntity, merchantServiceEntity, transaction);
    }
}
