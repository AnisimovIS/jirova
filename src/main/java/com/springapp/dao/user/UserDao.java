package com.springapp.dao.user;

import com.springapp.entity.Groups;
import com.springapp.entity.Roles;
import com.springapp.entity.Status;
import com.springapp.entity.User;
import com.springapp.secury.enums.Role;
import org.springframework.security.access.annotation.Secured;

import java.util.List;

public interface UserDao {

    @Secured(value = {Role.role_admin,Role.role_user})
    public Long add(User user);

    @Secured(value = {Role.role_admin,Role.role_user})
    public Long saveOrUpdate(User user);

    @Secured(value = {Role.role_admin,Role.role_user})
    public int delete(User user);

    @Secured({Role.role_user,Role.role_admin})
    public User getUser(long userId);


    public User getUser(String login);

    @Secured(value = {Role.role_admin,Role.role_user})
    public List<User> list();
}
