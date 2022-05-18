package personal_project.repository.agent;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import personal_project.entity.agent.AgentServiceEntity;

import java.util.Optional;

@Repository
public interface AgentServiceRepository extends JpaRepository<AgentServiceEntity,Long> {
    @Query("select a from AgentServiceEntity a where a.serviceId = ?1 and a.agentId = ?2")
    Optional<AgentServiceEntity> findByServiceIdAndAgentId(Long serviceId, Long agentId);
}
