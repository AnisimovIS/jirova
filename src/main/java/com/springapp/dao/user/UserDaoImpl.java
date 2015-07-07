package com.springapp.dao.user;

import com.springapp.entity.Groups;
import com.springapp.entity.Roles;
import com.springapp.entity.Status;
import com.springapp.entity.User;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.security.authentication.encoding.Md5PasswordEncoder;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserDaoImpl implements UserDao {
    private JdbcTemplate jdbcTemplate;
    protected final Log log = LogFactory.getLog(this.getClass());
    public UserDaoImpl(DataSource dataSource) {
        jdbcTemplate = new JdbcTemplate(dataSource);
    }

    @Override
    public Long saveOrUpdate(User user) {
        if (user.getId() > 0) {
            return update(user);
        } else {
            return add(user);
        }
    }

    private Long update (User user) throws  DataAccessException{
        final String hashedPass = new Md5PasswordEncoder().encodePassword(user.getPassword(), null);
        try {
            jdbcTemplate.update("UPDATE users SET username=?, password=? WHERE id=?", user.getLogin(), hashedPass, user.getId());
            updateUserConditional(user);
            return user.getId();
        } catch (DataAccessException e) {
            log.debug(e.toString());
            return null;
        }
    }
    @Override
    public Long add(final User user) throws DataAccessException{
        final String hashedPass = new Md5PasswordEncoder().encodePassword(user.getPassword(), null);
        try {
            jdbcTemplate.update("INSERT INTO users (username, password) VALUES ( ?, ?)", user.getLogin(), hashedPass);
            Long user_id = jdbcTemplate.queryForLong("select currval('users_id_seq')");
            user.setId(user_id);
            updateUserConditional(user);
            return user_id;
        } catch (DataAccessException e) {
            log.debug(e.toString());
            return null;
        }
    }
    private void clearUserConditional(User user) throws DataAccessException{
        try {
            jdbcTemplate.update("DELETE FROM users_groups WHERE user_id = ?", user.getId());
            jdbcTemplate.update("DELETE FROM users_roles WHERE user_id = ?", user.getId());
            jdbcTemplate.update("DELETE FROM users_status WHERE user_id = ?", user.getId());
        }catch (DataAccessException e){
            log.debug(e.toString());
        }
    }
    private void updateUserConditional(User user) throws DataAccessException{
        try {
            clearUserConditional(user);
            List<Object[]> batch = new ArrayList<>();
            for (Groups group : user.getGroups()) {
                batch.add(new Object[]{user.getId(), group.getId()});
            }
            jdbcTemplate.batchUpdate("INSERT INTO users_groups (user_id,groups_id) VALUES ( ?, ?)", batch);
            batch.clear();
            for (Roles role : user.getRoles()) {
                batch.add(new Object[]{user.getId(), role.getId()});
            }
            jdbcTemplate.batchUpdate("INSERT INTO users_roles (user_id,roles_id) VALUES ( ?, ?)", batch);
            batch.clear();
            for (Status status : user.getStatus()) {
                batch.add(new Object[]{user.getId(), status.getId()});
            }
            jdbcTemplate.batchUpdate("INSERT INTO users_status (user_id,status_id) VALUES ( ?, ?)", batch);
        } catch (DataAccessException e) {
            log.debug(e.toString());
        }

    }
    @Override
    public int delete(User user) throws DataAccessException{
        try {
            return jdbcTemplate.update("DELETE FROM users WHERE id=?", user.getId());
        }catch (DataAccessException e){
            log.debug(e.toString());
            return -1;
        }
    }
    @Override
    public User getUser(long userId)  {
        try {
            User user = (User) this.jdbcTemplate.queryForObject(sql_getUser(" WHERE u.id=? "), new Object[]{userId, userId, userId}, userRowMapper());
            return user;
        } catch (DataAccessException e){
            log.debug(e.toString());
            return (User)null;
        }
    }


    @Override
    public User getUser(String  login) {
        try {
            User user =  (User) this.jdbcTemplate.queryForObject(sql_getUser(" WHERE u.username=? "), new String[]{login, login, login}, userRowMapper());
            return user;
        } catch (DataAccessException e){
            log.error(e.toString());
            return (User)null;
        }

    }

    @Override
    public List<User> list() throws DataAccessException {
        try {
            List<User> userList = (List<User>)this.jdbcTemplate.query( sql_getUser(""), userRowMapper());
            return userList;
        } catch (DataAccessException e){
            log.debug(e.toString());
            return null;
        }

    }

    private class UserExtractor implements ResultSetExtractor {
        public User extractData(ResultSet rs) throws SQLException, DataAccessException {
            User user = (!rs.isLast()) ? new User(rs.getLong(1), rs.getString(2), rs.getString(3)) : null;
            while (!rs.isAfterLast()) {
                if (user.getId() != rs.getLong(1)) {
                    return user;
                }
                if (rs.getString(7).equals(Groups.class.getSimpleName().toLowerCase())) {
                    user.getGroups().add(new Groups(rs.getLong(4), rs.getString(5), rs.getString(6)));
                }
                if (rs.getString(7).equals(Roles.class.getSimpleName().toLowerCase())) {
                    user.getRoles().add(new Roles(rs.getLong(4), rs.getString(5), rs.getString(6)));
                }
                if (rs.getString(7).equals(Status.class.getSimpleName().toLowerCase())) {
                    user.getStatus().add(new Status(rs.getLong(4), rs.getString(5), rs.getString(6)));
                }
                rs.next();
            }
            return user;
        }
    }


    private  RowMapper userRowMapper(){
        RowMapper rowMapper = new RowMapper<User>() {
            @Override
            public User mapRow(ResultSet rs, int rowCount) throws SQLException {
                ResultSetExtractor uExt = new UserExtractor();
                return (User)uExt.extractData(rs);
            }
        };
        return rowMapper;
    }

    private String sql_getUser(String conditional){
        String sql = "SELECT u.*,g.id as cond_id,g.group_name as cond_name,g.description as cond_description,'groups' as clazz from users u "
                + " LEFT JOIN (SELECT ug.user_id, g1.* FROM users_groups ug, groups g1 WHERE g1.id = ug.groups_id) g ON u.id = g.user_id " + conditional
                + " UNION SELECT u.*,g.id ,g.role_name ,g.description,'roles' FROM users u "
                + " LEFT JOIN (SELECT ug.user_id, g1.* FROM users_roles ug, roles g1 WHERE g1.id = ug.roles_id) g ON u.id = g.user_id " + conditional
                + " UNION SELECT u.*,g.id,g.status_name,g.description,'status' FROM users u "
                + " LEFT JOIN (SELECT ug.user_id, g1.* FROM users_status ug, status g1 WHERE g1.id = ug.status_id) g ON u.id = g.user_id " + conditional
                + " ORDER BY id, clazz,cond_id;";
        return sql;
    }

}
