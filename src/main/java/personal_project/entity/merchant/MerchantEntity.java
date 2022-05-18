package personal_project.entity.merchant;

import lombok.Getter;
import lombok.Setter;
import personal_project.entity.BaseEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Transient;

@Entity
@Getter
@Setter
public class MerchantEntity extends BaseEntity {
    private String name;

    @Column(unique = true)
    private String username;
    private String password;

    private String secretKey;

    @Transient
    private boolean isUcell;

    @Transient
    private boolean isYandex;

    @Transient
    private boolean isPayme;

    public boolean isPaynet(){
        return (super.getId() == 10);
    }

    public boolean isPayme(){
        return (super.getId() == 4);
    }

    public boolean isClick(){
        return (super.getId() == 9);
    }


}
