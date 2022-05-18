package personal_project.service.agent;


import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import personal_project.dto.payload.agent.AgentServiceDto;
import personal_project.dto.response.BaseApiResponse;
import personal_project.entity.agent.AgentServiceEntity;
import personal_project.repository.agent.AgentServiceRepository;

@Service
@RequiredArgsConstructor
public class AgentServiceServiceImp implements AgentServiceService{

    private final AgentServiceRepository agentServiceRepository;
//    private final ModelMapper modelMapper;

    @Override
    public BaseApiResponse add(AgentServiceDto agentServiceDto) {

        if(agentServiceRepository.findByServiceIdAndAgentId(
                agentServiceDto.getAgentId(),
                agentServiceDto.getServiceId()
        ).isPresent())
            return new BaseApiResponse(11, "Service Already exist", null);

        AgentServiceEntity agentServiceEntity = new AgentServiceEntity();
        agentServiceEntity.setServiceId(agentServiceDto.getServiceId());
        agentServiceEntity.setAgentId(agentServiceDto.getAgentId());
        agentServiceEntity.setCommission(agentServiceDto.getCommission());

        agentServiceRepository.save(agentServiceEntity);
        return new BaseApiResponse(1, "Service Added", agentServiceEntity);
    }
}
