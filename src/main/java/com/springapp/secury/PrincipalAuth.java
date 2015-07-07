package com.springapp.secury;

import com.springapp.secury.enums.Group;
import com.springapp.secury.enums.Status;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;

import java.util.Collection;

class PrincipalAuth extends Principal {

    private final long id;
    private final String login;
    private final String password;
    private final Status status;
    private final Collection<GrantedAuthority> authorities;
    private final String salt;
    private static final String MESSAGE = "The %s is required.";

    public PrincipalAuth(long id, String login, String password, Status status, String[] roles, Group[] groups) {
        super();
        this.id = id;
        this.login = login;
        this.password = password;
        this.status = status;
        salt = null;
        authorities = AuthorityUtils.commaSeparatedStringToAuthorityList(String.join(", ", roles));
    }

    @Override
    public long getId() {
        return this.id;
    }

    @Override
    public String getLogin() {
        return this.login;
    }

    @Override
    public Status getStatus() {
        return this.status;
    }

    @Override
    public boolean isAnonymous() {
        return false;
    }

    @Override
    public String getSalt() {
        return  this.salt;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return this.authorities;
    }

    @Override
    public String getPassword() {
        return this.password;
    }

    @Override
    public String getUsername() {
        return this.getLogin();
    }

    @Override
    public boolean isAccountNonExpired() {
        return false;
    }

    @Override
    public boolean isAccountNonLocked() {
        return false;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return false;
    }

    @Override
    public boolean isEnabled() {
        return false;
    }



}
