package com.springapp.services.user;

import com.springapp.entity.User;

import java.util.List;

public interface UserService {

    User get(String login);

    User get(Long id);

    boolean save(User user);

    List<User> list();

    void delete(User user);
}
