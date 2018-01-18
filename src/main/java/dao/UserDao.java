package dao;
import model.Bootcamp;
import model.User;

import java.util.List;

public interface UserDao extends GenericDao<User> {
    int userCount();
    User findByUsername(String username);
}
