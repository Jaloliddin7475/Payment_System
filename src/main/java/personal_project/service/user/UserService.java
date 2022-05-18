package personal_project.service.user;


import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Repository;
import personal_project.dto.payload.user.UserLoginDto;
import personal_project.dto.payload.user.UserRegisterDto;
import personal_project.entity.user.UserEntity;
import personal_project.repository.UserRepository;
import personal_project.service.BaseService;

import java.util.Optional;

@Repository
public interface UserService extends BaseService {

    default UserEntity login(UserLoginDto userLoginDto, UserRepository userRepository, PasswordEncoder passwordEncoder){
        Optional<UserEntity> optionalUserEntity = userRepository.findByUsername(userLoginDto.getUsername());
        if (optionalUserEntity.isEmpty())
            return null;

        if (!passwordEncoder.matches(userLoginDto.getPassword(),optionalUserEntity.get().getPassword()))
            return null;

        return optionalUserEntity.get();
    }
    Boolean add(UserRegisterDto userRegisterDto);
    Optional<UserEntity> getUser(Long id);
}
