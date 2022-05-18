package personal_project.dto.payload.agent;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AgentServiceDto {
    private double commission;
    private long agentId;
    private long serviceId;
}
