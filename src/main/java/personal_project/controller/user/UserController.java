package personal_project.controller.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import personal_project.dto.payload.user.UserRegisterDto;
import personal_project.service.user.UserService;

@RestController
@RequestMapping("/api/user/")
public class UserController {

    @Autowired
    @Qualifier("u")
    private UserService userService;

    @PreAuthorize("hasRole('ROLE_SUPER_ADMIN')")
    @PostMapping("/add")
    public ResponseEntity<Boolean> add(@RequestBody UserRegisterDto userRegisterDto){
        return ResponseEntity.ok(userService.add(userRegisterDto));
    }

}
