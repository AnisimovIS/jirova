package com.springapp.services.user;

import com.springapp.dao.user.UserDao;
import com.springapp.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.encoding.Md5PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserDao userDao;

    @Override
    public User get(String login) {
        User user = userDao.getUser(login);
        return user;
    }

    @Override
    public User get(Long id) {
        User user = userDao.getUser(id);
        return user;
    }

    @Override
    public boolean save(User user){
        userDao.saveOrUpdate(user);
        return true;
    }

    @Override
    public List<User> list(){
        return userDao.list();
    }

    @Override
    public void delete(User user){
        userDao.delete(user);

    }
}
