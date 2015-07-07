package com.springapp.entity.enums;



public enum EnumGroups {
    ADMIN(1,"GROUP_ADMINISTRATOR"),
    USER(2, "GROUP_SOMETHING"),
    ANONYMOUS(1, "GROUP_USER");

    private final long id;
    private final String description;

    EnumGroups(long id, String description) {
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

    public static EnumGroups find(long id) {
        for (EnumGroups value: values())
            if (value.getId() == id) return value;
        throw new IllegalArgumentException(String.format("Unknown record status id: %d", id));
    }
}
