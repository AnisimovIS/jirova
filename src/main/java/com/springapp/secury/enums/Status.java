package com.springapp.secury.enums;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.GrantedAuthorityImpl;

public enum Status {
    STATUS_ACTIVE(0),
    STATUS_FROZEN(1),
    STATUS_LOCKED(2),
    STATUS_READONLY(3);

    public static final String status_active = "STATUS_ACTIVE";
    public static final String status_frozen = "STATUS_FROZEN";
    public static final String status_expired = "STATUS_EXPIRED";
    public static final String status_readonly = "STATUS_READONLY";
    public static final String status_locked = "STATUS_LOCKED";

    private final long value;

    Status(long value) {
        this.value = value;
    }

    public long getValue() {
        return value;
    }

    public String getName() {
        return name();
    }

    public static Status resolve(long value) {
        for (Status status : values())
            if (status.getValue() == value) return status;
        throw new IllegalArgumentException(String.format("Unknown user status value: %s", value));
    }


    public String getAuthority() {
        return name();
    }

    public boolean greaterOrEquals(String statusName) {
        return name().equals(statusName);
    }

    public GrantedAuthority authority() {
        return new GrantedAuthorityImpl(getAuthority());
    }

}

