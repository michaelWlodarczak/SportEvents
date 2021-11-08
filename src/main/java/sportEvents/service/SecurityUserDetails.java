package sportEvents.service;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import sportEvents.entity.User;

import java.util.Collection;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

public class SecurityUserDetails implements UserDetails {
    private UUID userId;
    private String userLogin;
    private String userPassword;
    private Boolean userActive;
    private List<GrantedAuthority> authorityList;

    public SecurityUserDetails(User user) {
        this.userId = user.getUserId();
        this.userLogin = user.getUserLogin();
        this.userPassword = user.getUserPassword();
        this.userActive = user.isUserActive();
        this.authorityList = user.getUserRoles()
                .stream()
                .map(SimpleGrantedAuthority::new)
                .collect(Collectors.toList());
    }

    public UUID getUserId() {
        return userId;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorityList;
    }

    @Override
    public String getPassword() {
        return userPassword;
    }

    @Override
    public String getUsername() {
        return userLogin;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return userActive;
    }
}
