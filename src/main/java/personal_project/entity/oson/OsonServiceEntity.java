package personal_project.entity.oson;

import lombok.Getter;
import lombok.Setter;
import personal_project.entity.BaseEntity;
import personal_project.entity.merchant.MerchantServiceEntity;

import javax.persistence.Entity;
import javax.persistence.OneToOne;

@Getter
@Setter
@Entity
public class OsonServiceEntity extends BaseEntity {

    private String name;

    @OneToOne
    private MerchantServiceEntity merchantServiceEntity;
}
