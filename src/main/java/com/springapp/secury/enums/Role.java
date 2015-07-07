package com.springapp.secury.enums;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.GrantedAuthorityImpl;

/**
 * Created by Ilya on 18.05.2015.
 */
public enum Role {
    ROLE_ADMIN,
    ROLE_USER;

    public static final String role_user = "ROLE_USER";
    public static final String role_admin = "ROLE_ADMIN";

    public String getAuthority() {
        return name();
    }

    public GrantedAuthority authority() {
        return new GrantedAuthorityImpl(getAuthority());
    }

}
