package personal_project.repository.merchant;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import personal_project.entity.merchant.MerchantServiceEntity;

import java.util.Optional;

@Repository
public interface MerchantServiceRepository extends JpaRepository<MerchantServiceEntity,Long> {

    @Query("select m from MerchantServiceEntity m where m.merchantServiceId = ?1")
    Optional<MerchantServiceEntity> findById(Long aLong);

    MerchantServiceEntity findByMerchantServiceId(Long merchantServiceId);
}
