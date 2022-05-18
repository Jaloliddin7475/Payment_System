package personal_project.repository.agent;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import personal_project.entity.agent.AgentDepositEntity;
import personal_project.entity.agent.AgentEntity;

import java.util.Optional;

@Repository
public interface AgentRepository extends JpaRepository<AgentEntity,Long> {

    @Query("select a from AgentEntity a where a.username = ?1 and a.isActive = true")
    AgentEntity findByUsername(String username);

    @Query("select a from AgentDepositEntity a where a.agentEntity.username = ?1")
    Optional<AgentDepositEntity> getAgentDepositByUsername(String username);
}
