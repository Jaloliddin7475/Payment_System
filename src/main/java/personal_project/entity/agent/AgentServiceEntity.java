package personal_project.entity.agent;


import lombok.Getter;
import lombok.Setter;
import personal_project.entity.BaseEntity;

import javax.persistence.Entity;

@Getter
@Setter
@Entity
public class AgentServiceEntity extends BaseEntity {

    private Long agentId;
    private Long serviceId;
    private double commission;
}
