package personal_project.service.agent;


import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import personal_project.dto.payload.user.UserRegisterDto;
import personal_project.entity.agent.AgentDepositEntity;
import personal_project.entity.agent.AgentEntity;
import personal_project.entity.user.UserEntity;
import personal_project.exception.LoginValidationException;
import personal_project.repository.agent.AgentDepositRepository;
import personal_project.repository.agent.AgentRepository;
import personal_project.service.user.UserService;

import java.math.BigDecimal;
import java.util.Optional;

@Service("a")
@RequiredArgsConstructor
public class AgentServiceImp implements UserService {

    private final AgentRepository agentRepository;
    private final AgentDepositRepository agentDepositRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public Boolean add(UserRegisterDto userRegisterDto) {
        AgentEntity agent = agentRepository.findByUsername(userRegisterDto.getUsername());
        if (agent != null)
            throw new LoginValidationException("username is already exists");

        AgentEntity agentEntity = new AgentEntity();
        agentEntity.setPassword(passwordEncoder.encode(userRegisterDto.getPassword()));
        agentEntity.setPermission(userRegisterDto.getPermission().get(0));
        agentEntity.setName(userRegisterDto.getName());
        agentEntity.setUsername(userRegisterDto.getUsername());


        AgentEntity savedEntity = agentRepository.save(agentEntity);
        agentDepositRepository.save(new AgentDepositEntity(savedEntity, BigDecimal.valueOf(10000)));

        return true;
    }

    @Override
    public Optional<UserEntity> getUser(Long id) {
        return Optional.empty();
    }


}
