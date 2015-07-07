package com.springapp.entity;

import org.springframework.security.core.GrantedAuthority;

public class Groups implements  GrantedAuthority {
    private long id;
    private String group_name;
    private String description;

    public Groups(long id, String group_name, String description) {
        this.id = id;
        this.group_name = group_name;
        this.description = description;
    }

    public long getId() {
        return id;
    }

    public String getGroup_name() {
        return group_name;
    }

    public String getDescription() {
        return description;
    }

    @Override
    public String getAuthority() {
        return group_name;
    }
}
