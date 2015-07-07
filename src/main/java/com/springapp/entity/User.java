package com.springapp.entity;

import org.springframework.security.core.GrantedAuthority;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class User implements Serializable{
    private long id;
    private String login;
    private String password;


    private List<Roles> roles = new ArrayList<>();
    private List<Status> status = new ArrayList<>();
    private List<Groups> groups = new ArrayList<>();

    public User() {
    }

    public User(String login, String password) {
        this.login = login;
        this.password = password;
    }
    public User(long id, String login, String password) {
        this.id = id;
        this.login = login;
        this.password = password;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getLogin() {
        return login;
    }

    public String getPassword() {
        return password;
    }

    public void setGroups(List<Groups> groups) {
        this.groups = groups;
    }

    public void setRoles(List<Roles> roles) {
        this.roles = roles;
    }

    public void setStatus(List<Status> status) {
        this.status = status;
    }

    public List<Groups> getGroups() {
        return groups;
    }

    public List<Roles> getRoles() {
        return roles;
    }

    public List<Status> getStatus() {
        return status;
    }



}
