package dzl.tech.avisapp.Enum;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.Collection;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import static dzl.tech.avisapp.Enum.TypePermission.*;

@AllArgsConstructor
public enum TypeDeRole {
    UTILISATEUR(
            Set.of(TypePermission.UTILISATEUR_CREATE_AVIS)
    ),

    ADMINISTRATEUR (
            Set.of(
                    ADMINISTRATEUR_CREATE,
                   ADMINISTRATEUR_READ,
                   ADMINISTRATEUR_UPDATE,
                    ADMINISTRATEUR_DELETE,

                    MANAGER_READ,
                    MANAGER_CREATE,
                    MANAGER_UPDATE,
                    MANAGER_DELETE_AVIS
            )

    ),
    MANAGEMENT_EXEC(
            Set.of(
                    MANAGER_READ,
                    MANAGER_CREATE,
                    MANAGER_UPDATE,
                    MANAGER_DELETE_AVIS
            )
    ),;

    @Getter
    final
    Set<TypePermission> permissions;

    public Collection<? extends GrantedAuthority> getAuthorities() {
        final List<SimpleGrantedAuthority> grantedAuthorities =  this.permissions.stream().map(
                permission -> new SimpleGrantedAuthority(permission.name())
        ).collect(Collectors.toList());
        grantedAuthorities.add(new SimpleGrantedAuthority("ROLE_"+ this.name()));
        return grantedAuthorities;
    }
}
