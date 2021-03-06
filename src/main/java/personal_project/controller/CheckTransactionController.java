package personal_project.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import personal_project.dto.request.agent.DefaultAgentRequest;
import personal_project.dto.response.agent.BaseAgentResponse;
import personal_project.service.transaction.CheckTransactionService;

@RestController
@RequestMapping("/api/transaction")
public class CheckTransactionController {

    @Autowired
    private CheckTransactionService checkTransactionService;

    @PreAuthorize("hasRole('ROLE_AGENT')")
    @PostMapping(value = "/check",
            consumes
                    = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_ATOM_XML_VALUE},
            produces =
                    {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_ATOM_XML_VALUE}
    )
    public BaseAgentResponse checkTransaction(
            @RequestBody DefaultAgentRequest baseAgentRequest,
            @AuthenticationPrincipal String username
    ) {

        baseAgentRequest.getDefaultAgentRequest();
        return checkTransactionService.check(baseAgentRequest, username);
    }
}
