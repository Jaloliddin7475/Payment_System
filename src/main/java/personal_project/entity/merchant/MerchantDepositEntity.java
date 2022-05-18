package personal_project.entity.merchant;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import personal_project.entity.BaseEntity;

import javax.persistence.Entity;
import javax.persistence.OneToOne;
import java.math.BigDecimal;

@Getter
@Setter
@Entity
@NoArgsConstructor
public class MerchantDepositEntity extends BaseEntity {

    @OneToOne
    private MerchantEntity merchantEntity;

    public MerchantDepositEntity(MerchantEntity merchantEntity) {
        this.merchantEntity = merchantEntity;
    }

    private BigDecimal amount = BigDecimal.valueOf(1000);
}
