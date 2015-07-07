package com.springapp.entity;

import java.io.Serializable;
import java.util.Date;

public class Layer implements Serializable{
    private long id;
    private String name;
    private Date dateCreate;
    private String description;

    public Layer(long id, String name) {
        this.id = id;
        this.name = name;
    }

    public Date getDateCreate() {
        return dateCreate;
    }

    public void setDateCreate(Date dateCreate) {
        this.dateCreate = dateCreate;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
