package personal_project.dto.response.agent;

import personal_project.entity.transaction.TransactionEntity;

public class DefaultAgentResponse implements BaseAgentResponse{
    @Override
    public BaseAgentResponse success(TransactionEntity transaction) {
        return null;
    }

    @Override
    public BaseAgentResponse error(TransactionEntity transaction) {
        return null;
    }
}
