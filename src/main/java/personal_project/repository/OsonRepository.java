package personal_project.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import personal_project.entity.oson.OsonServiceEntity;

@Repository
public interface OsonRepository extends JpaRepository<OsonServiceEntity,Long> {
}
