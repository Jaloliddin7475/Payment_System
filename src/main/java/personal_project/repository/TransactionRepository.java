package personal_project.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import personal_project.entity.transaction.TransactionEntity;

@Repository
public interface TransactionRepository extends JpaRepository<TransactionEntity,Long> {

}
