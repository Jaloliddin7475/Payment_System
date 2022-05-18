package personal_project.controller.agent;


import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import personal_project.dto.payload.agent.AgentServiceDto;
import personal_project.service.agent.AgentServiceService;

@RestController
@RequestMapping("/api/agent/service/")
@RequiredArgsConstructor
public class AgentServiceController {
    private final AgentServiceService agentServiceService;

    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_SUPER_ADMIN')")
    @RequestMapping("/add")
    public ResponseEntity<?> add(@RequestBody AgentServiceDto agentServiceDto){
        return ResponseEntity.ok(agentServiceService.add(agentServiceDto));
    }
}
