package personal_project.entity.merchant;

import lombok.Getter;
import lombok.Setter;
import personal_project.entity.BaseEntity;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;

@Entity
@Getter
@Setter
public class MerchantServiceEntity extends BaseEntity {

    private String name;

    private Long merchantServiceId;

    @ManyToOne
    private MerchantEntity merchantEntity;
}
