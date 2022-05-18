package personal_project.repository.agent;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import personal_project.entity.agent.AgentDepositEntity;

@Repository
public interface AgentDepositRepository extends JpaRepository<AgentDepositEntity,Long> {
}
