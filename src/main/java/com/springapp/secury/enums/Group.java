package com.springapp.secury.enums;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.GrantedAuthorityImpl;

import java.text.MessageFormat;

/**
 * Created by Ilya on 18.05.2015.
 */
public enum Group {
    GROUP_ADMINISTRATOR(1),
    GROUP_SOMETHING(2),
    GROUP_USER(3);

    public static final String group_administrator = "GROUP_ADMINISTRATOR";
    public static final String group_something = "GROUP_SOMETHING";
    public static final String group_user = "GROUP_USER";

    public static final Group[] EMPTY_ARRAY = new Group[]{};

    private final long id;

    Group(long id) {
        this.id = id;
    }

    public long getId() {
        return id;
    }

    public String getAuthority() {
        return name();
    }

    public GrantedAuthority authority() {
        return new GrantedAuthorityImpl(getAuthority());
    }

    public static Group resolve(long id) {
        for (Group group: values())
            if (group.id == id) return group;
        throw new IllegalArgumentException(MessageFormat.format("Unknown principal group id: {0}", id));
    }

}
