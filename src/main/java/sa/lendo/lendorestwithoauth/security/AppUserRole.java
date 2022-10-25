package sa.lendo.lendorestwithoauth.security;

import com.google.common.collect.Sets;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.Set;
import java.util.stream.Collectors;

public enum AppUserRole {

    USER(Sets.newHashSet(AppUserPermission.AD_READ, AppUserPermission.AD_WRITE,
            AppUserPermission.ADDRESS_READ, AppUserPermission.ADDRESS_WRITE,
            AppUserPermission.USERS_READ,
            AppUserPermission.COMMENT_READ, AppUserPermission.COMMENT_WRITE)),

    ADMIN(Sets.newHashSet(AppUserPermission.USERS_READ, AppUserPermission.USERS_WRITE,
            AppUserPermission.ADDRESS_READ, AppUserPermission.ADDRESS_WRITE,
            AppUserPermission.CONTINENT_READ, AppUserPermission.CONTINENT_WRITE,
            AppUserPermission.COUNTRY_READ, AppUserPermission.COUNTRY_WRITE,
            AppUserPermission.CITY_READ, AppUserPermission.CITY_WRITE,
            AppUserPermission.CATEGORY_READ, AppUserPermission.CATEGORY_WRITE,
            AppUserPermission.AD_READ, AppUserPermission.AD_WRITE,
            AppUserPermission.COMMENT_READ, AppUserPermission.COMMENT_WRITE));

    private final Set<AppUserPermission> permissions;

    AppUserRole(Set<AppUserPermission> permissions) {
        this.permissions = permissions;
    }

    public Set<AppUserPermission> getPermissions() {
        return permissions;
    }

    public Set<GrantedAuthority> getGrantedAuthorities() {
        Set<GrantedAuthority> permissions = getPermissions().stream()
                .map(permission -> new SimpleGrantedAuthority(permission.getPermission()))
                .collect(Collectors.toSet());
        permissions.add(new SimpleGrantedAuthority("ROLE_" + this.name()));
        return permissions;
    }
}
