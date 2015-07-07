package com.springapp.secury;

import com.springapp.secury.enums.Group;
import com.springapp.secury.enums.Status;
import org.springframework.security.core.userdetails.UserDetails;

public abstract class Principal implements UserDetails {
    public static Principal principal(long id, String login, String password, Status status,String[] roles, Group[] groups) {
        return new PrincipalAuth( id, login, password, status, roles, groups);

    }
    public abstract long getId();

    public abstract String getLogin();

    public abstract Status getStatus();

    public abstract boolean isAnonymous();

    public abstract String getSalt();

}
