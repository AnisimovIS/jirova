package com.springapp.dao;

import com.springapp.config.db.DbConfig;
import com.springapp.dao.user.UserDao;
import com.springapp.entity.User;
import com.springapp.secury.Principal;
import com.springapp.secury.enums.Group;
import com.springapp.secury.enums.Role;
import com.springapp.secury.enums.Status;
import com.springapp.utils.TestUtils;
import junit.framework.TestCase;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.encoding.Md5PasswordEncoder;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {DbConfig.class})
@Transactional
public class UserDaoImplTest extends TestCase {
    @Autowired
    private UserDao userDao;

    @Before
    public void setUp() throws Exception {
        super.setUp();
        TestUtils.setUser(Principal.principal(1L, "test", "test",
                Status.STATUS_ACTIVE,
                new String[]{Role.role_user},
                new Group[]{Group.GROUP_USER}));
    }

    @Test
    @Rollback(true)
    public void testSaveOrUpdate() throws Exception {
        User user_test = userDao.getUser("test");
        assertNull(user_test);
        User user = new User("test", "test");
        userDao.saveOrUpdate(user);
        user_test = userDao.getUser("test");
        assertNotNull(user_test);
        assertFalse(user.getPassword().equals(user_test.getPassword()));

    }

    @Test
    @Rollback(true)
    public void testSaveOrUpdate2() throws Exception {
        User user = new User("test", "1");
        Long user_id = userDao.saveOrUpdate(user);
        final String hashedPass = new Md5PasswordEncoder().encodePassword(user.getPassword(), null);
        User user_test = userDao.getUser(user_id);
        assertEquals(user_test.getPassword(), hashedPass);
    }


    @Test
    @Rollback(true)
    public void testDelete() throws Exception {
        User user = new User("test", "test");
        userDao.saveOrUpdate(user);
        User user_test = userDao.getUser("test");
        assertNotNull(user_test);
        userDao.delete(user_test);
        user_test = userDao.getUser("test");
        assertNull(user_test);
    }

    @Test
    @Rollback(true)
    public void testGetUser() throws Exception {
        User user = new User("test", "test");
        long id = userDao.saveOrUpdate(user);
        User user_test = userDao.getUser(id);
        assertNotNull(user_test);
        user_test = userDao.getUser("test");
        assertNotNull(user_test);
        assertEquals(user.getLogin(), user_test.getLogin());
    }

    @Test
    @Rollback(true)
    public void testGetUserList() throws Exception {
        int count = userDao.list().size();
        User user1 = new User("one", "pass_one");
        User user2 = new User("two", "pass_two");
        User user3 = new User("two", "pass_two");
        userDao.saveOrUpdate(user1);
        userDao.saveOrUpdate(user2);
        userDao.saveOrUpdate(user3);
        assertEquals(userDao.list().size(), count + 3);
    }


    @Test
    @Rollback(true)
    public void testList() throws Exception {
        User user = new User("test", "test");
        userDao.saveOrUpdate(user);
        long count = userDao.list().size();
        assertTrue(count > 0);
    }

}