package com.springapp.entity.enums;

public enum EnumStatus {
    ADMIN(1,"ROLE_ADMIN"),
    USER(2, "ROLE_USER"),
    ANONYMOUS(1, "ROLE_ANONYMOUS");

    private final long id;
    private final String description;

    EnumStatus(long id, String description) {
        this.description = description;
        this.id = id;
    }

    public String getDescription() {
        return description;
    }
    public long getId() {
        return id;
    }
    public String getName() {
        return this.name();
    }

    public static EnumStatus find(long id) {
        for (EnumStatus value: values())
            if (value.getId() == id) return value;
        throw new IllegalArgumentException(String.format("Unknown record status id: %d", id));
    }
}
