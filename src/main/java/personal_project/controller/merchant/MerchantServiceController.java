package personal_project.controller.merchant;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import personal_project.dto.payload.merchant.MerchantServiceRegisterDto;
import personal_project.dto.response.BaseApiResponse;
import personal_project.service.merchant.MerchantService;

@RestController
@RequestMapping("/api/merchant/service")
public class MerchantServiceController {

    @Autowired
    @Qualifier("merchantServiceServiceImp")
    private MerchantService<MerchantServiceRegisterDto> merchantService;

    @PreAuthorize("hasRole('ROLE_SUPER_ADMIN')")
    @PostMapping("/add")
    public ResponseEntity<?> add(@RequestBody MerchantServiceRegisterDto merchantServiceRegisterDto) {

        Boolean add = merchantService.add(merchantServiceRegisterDto);

        if (add)
            return ResponseEntity.ok().body(new BaseApiResponse(1, "Created", true));

        return ResponseEntity.status(HttpStatus.CONFLICT).body(new BaseApiResponse(0, "creation error", false));
    }
}
