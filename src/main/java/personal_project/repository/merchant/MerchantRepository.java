package personal_project.repository.merchant;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import personal_project.entity.merchant.MerchantEntity;
import personal_project.entity.merchant.MerchantServiceEntity;

import java.util.Optional;
@Repository
public interface MerchantRepository extends JpaRepository<MerchantEntity,Long> {

    @Query("select m from MerchantEntity m where m.id = ?1")
    Optional<MerchantServiceEntity> getMerchantServiceEntityById(Long id);
}
