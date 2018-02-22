package services;

import dao.UserDao;
import model.User;
import org.springframework.transaction.annotation.Transactional;
import persistence.TransactionManager;
import javax.persistence.*;
import java.util.List;

public class JPAUserService implements UserService {
    private UserDao userDao;
    private String name = "userservice";

    public JPAUserService(UserDao userDao) {
        this.userDao = userDao;
    }

    /**
     * @param username the user name
     * @param password the user password
     * @return true if authenticated
     */
    @Override
    public boolean autenthicate(String username, String password) {
        if (username.equals("") || username.matches("\\s+") || password.equals("") || password.matches("\\s+")) {
            return false;
        }
        User user = findByName(username);
        if (user == null) {
            return false;
        }
        System.out.println("FOUND USER: ");
        return user.getUsername().equals(username) && user.getPassword().equals(password);
    }

    @Override
    public void addUser(User user) {
        if (user.getUsername().equals("") || user.getUsername().matches("\\s+") || user.getPassword().equals("") || user.getPassword().matches("\\s+")){
            return;
        }
        if(findByName(user.getUsername()) == null) {
            addUserDB(user);
            //userList.put(user.getUsername(),user);
        }
    }

    @Override
    public User findByName(String username) {
        return findUserByUsername(username);
    }

    @Transactional
    public User findUserByUsername(String username) {
        User user;
        try {
            user = userDao.findByUsername(username);
        } catch (NoResultException e) {
            return null;
        }
        return user;
    }

    @Override
    public int count() {
        return userCountDB();
    }

    @Transactional
    public void addUserDB(User user) {
            userDao.createOrUpdate(user);
    }
    @Transactional
    public User findUserById(int id) {
        User user = userDao.findById(id);
        return user;
    }

    @Transactional
    public void removeUser(User user) {
            userDao.remove(user);
    }

    @Transactional
    public int userCountDB() {
        int count = userDao.userCount();
        return count;
    }

    @Transactional
    public List<User> userList() {
        return userDao.listAll();
    }

    public String getName() {
        return name;
    }
}
