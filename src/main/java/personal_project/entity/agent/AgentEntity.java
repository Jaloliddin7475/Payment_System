package personal_project.entity.agent;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import personal_project.entity.user.UserEntity;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.Transient;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@Entity
@DiscriminatorValue("agent_entity")
public class AgentEntity extends UserEntity {

    @Transient
    private boolean isPaynet;

    @Transient
    private boolean isClick;

    @Transient
    private boolean isApelsin;

    @Transient
    private boolean isPayme;

    public boolean isPaynet() {
        return super.getId() == 4;
    }

    public boolean isPayme() {
        return super.getId() ==6;
    }

    @JsonIgnore
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        Set<GrantedAuthority> authority = new HashSet<>();
        authority.add(new SimpleGrantedAuthority("ROLE_AGENT"));
        return authority;
    }
}
