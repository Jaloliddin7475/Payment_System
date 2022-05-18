package personal_project.service.agent;


import personal_project.dto.payload.agent.AgentServiceDto;
import personal_project.dto.response.BaseApiResponse;

public interface AgentServiceService {
    BaseApiResponse add(AgentServiceDto agentServiceDto);
}
