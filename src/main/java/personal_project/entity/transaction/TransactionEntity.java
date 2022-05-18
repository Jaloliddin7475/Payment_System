package personal_project.entity.transaction;

import lombok.Getter;
import lombok.Setter;
import personal_project.entity.BaseEntity;
import personal_project.entity.agent.AgentEntity;
import personal_project.entity.merchant.MerchantEntity;
import personal_project.entity.oson.OsonServiceEntity;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import java.math.BigDecimal;

@Getter
@Setter
@Entity
public class TransactionEntity extends BaseEntity {
    private int transactionState;
    private BigDecimal transactionAmountFromAgent;
    private BigDecimal transactionAmountToMerchant;
    private String transactionAccount;

    @ManyToOne
    private AgentEntity agent;

    @ManyToOne
    private MerchantEntity merchant;

    @ManyToOne
    private OsonServiceEntity osonServiceEntity;
}
