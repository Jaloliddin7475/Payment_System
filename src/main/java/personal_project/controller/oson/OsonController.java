package personal_project.controller.oson;


import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import personal_project.dto.response.BaseApiResponse;
import personal_project.service.oson.OsonService;

@Service
@RequiredArgsConstructor
@RequestMapping("/api/oson")
public class OsonController {

    private final OsonService osonService;

    @PreAuthorize("hasRole('ROLE_SUPER_ADMIN')")
    @PostMapping("/add/{id}")
    public ResponseEntity<?> add(@PathVariable("id") Long merchantServId) {

        Boolean add = osonService.add(merchantServId);

        if (add)
            return ResponseEntity.ok().body(new BaseApiResponse(1, "Created", true));

        return ResponseEntity.status(HttpStatus.CONFLICT).body(new BaseApiResponse(0, "creation error", false));
    }
}
