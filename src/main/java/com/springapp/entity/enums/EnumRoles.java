package com.springapp.entity.enums;

public enum EnumRoles {
    ADMIN(1,"ROLE_ADMIN"),
    USER(2, "ROLE_USER"),
    ANONYMOUS(1, "ROLE_ANONYMOUS");

    private final long id;
    private final String description;

    EnumRoles(long id, String description) {
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

    public static EnumRoles find(long id) {
        for (EnumRoles value: values())
            if (value.getId() == id) return value;
        throw new IllegalArgumentException(String.format("Unknown record status id: %d", id));
    }
}
