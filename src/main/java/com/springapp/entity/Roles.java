package com.springapp.entity;

import org.springframework.security.core.GrantedAuthority;

public class Roles implements GrantedAuthority{
    private long id;
    private String role_name;
    private String description;

    public Roles(long id, String role_name, String description ) {
        this.id = id;
        this.role_name = role_name;
        this.description = description;
    }

    public long getId() {
        return id;
    }

    public String getRole_name() {
        return role_name;
    }

    public String getDescription() {
        return description;
    }

    @Override
    public String getAuthority() {
        return role_name;
    }
}
