package personal_project.entity.agent;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import personal_project.entity.BaseEntity;

import javax.persistence.Entity;
import javax.persistence.OneToOne;
import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class AgentDepositEntity extends BaseEntity {

    @OneToOne
    AgentEntity agentEntity;
    BigDecimal amount = BigDecimal.valueOf(1000);
}
