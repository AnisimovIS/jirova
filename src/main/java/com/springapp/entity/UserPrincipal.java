package com.springapp.entity;

import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;

public class UserPrincipal extends User implements GrantedAuthority {
    @Override
    public String getAuthority() {
        return authorities();
    }

    private String authorities() {
        return normalizeAuthorities(getAuth(getGroups()).concat(getAuth(getRoles())).concat(getAuth(getStatus())));
    }


    private String getAuth(Collection<? extends GrantedAuthority> grAu) {
        String string = new String("");
        if (grAu != null) {
            for (GrantedAuthority obj : grAu) {
                string = string.concat(obj.getAuthority()).concat(",");
            }
        }
        return string;
    }
    private String normalizeAuthorities(String incoming){
        int l = incoming.length();
        if ( l > 0 && incoming.lastIndexOf(",") == l){
            return incoming.substring(0,l);
        }
        return "";
    }

}
