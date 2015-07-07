package com.springapp.entity;

import org.springframework.security.core.GrantedAuthority;

public class Status implements GrantedAuthority {
    private long id;
    private String status_name;
    private String description;

    public Status( long id, String status_name,String description) {
        this.id = id;
        this.status_name = status_name;
        this.description = description;
    }

    public long getId() {
        return id;
    }

    public String getStatus_name() {
        return status_name;
    }

    public String getDescription() {
        return description;
    }

    @Override
    public String getAuthority() {
        return status_name;
    }
}
