package personal_project.controller.auth;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import personal_project.dto.payload.user.UserLoginDto;
import personal_project.dto.payload.user.UserRegisterDto;
import personal_project.dto.response.ApiJwtResponse;
import personal_project.dto.response.BaseApiResponse;
import personal_project.entity.user.UserEntity;
import personal_project.repository.UserRepository;
import personal_project.service.jwt.JwtProvider;
import personal_project.service.user.UserService;
import personal_project.service.user.UserServiceImp;

@RestController
@RequestMapping("/api/auth/")
@RequiredArgsConstructor
public class AuthController {

    private final JwtProvider jwtProvider;

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final UserServiceImp userServiceImp;

    @Autowired
    private UserService userService;

    @PostMapping(value = "/agent/login", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> login(@RequestBody UserLoginDto userLoginDto) {
        UserEntity agentEntity =  userService.login(userLoginDto, userRepository, passwordEncoder);
        if(agentEntity == null)
            return ResponseEntity.ok().body(new BaseApiResponse(0, "username or password is incorrect", null));


        String accessToken = jwtProvider.generateAccessToken(agentEntity);
        String refreshToken = jwtProvider.generateRefreshToken(agentEntity);

        return ResponseEntity.ok().body(new ApiJwtResponse(1, accessToken, refreshToken));
    }

    @PostMapping(value = "/user/login", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> getToken(@RequestBody UserLoginDto userLoginDto) {
        UserEntity userEntity =  userService.login(userLoginDto,userRepository,passwordEncoder);
        if(userEntity == null)
            return ResponseEntity.ok().body(new BaseApiResponse(0, "username or password is incorrect", null));


        String accessToken = jwtProvider.generateAccessToken(userEntity);
        String refreshToken = jwtProvider.generateRefreshToken(userEntity);

        return ResponseEntity.ok().body(new ApiJwtResponse(1, accessToken, refreshToken));
    }

//    @PreAuthorize("hasRole('ROLE_SUPER_ADMIN')")
    @PostMapping("/refresh_token")
    public ResponseEntity<?> getRefreshToken(@RequestBody String token) {
        String accessToken = jwtProvider.getAccessTokenFromRefreshToken(token);
        return ResponseEntity.ok().body(new ApiJwtResponse(1, accessToken, token));
    }

    @PostMapping("/add/admin")
    public ResponseEntity<Boolean> addSuperAdmin(@RequestBody UserRegisterDto userRegisterDto){
        return ResponseEntity.ok(userServiceImp.addSuperAdmin(userRegisterDto));
    }


}
