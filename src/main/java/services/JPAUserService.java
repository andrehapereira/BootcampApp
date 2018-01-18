package services;

import dao.UserDao;
import model.User;
import persistence.ConnectionManager;
import persistence.TransactionManager;

import javax.persistence.*;
import java.sql.*;
import java.util.List;

public class JPAUserService implements UserService {
    private TransactionManager transactionManager;
    private UserDao userDao;
    private String name = "jpauserservice";
    private Connection dbConnection;
    private ConnectionManager connectionManager;

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
        if (username.equals("") || username.matches("\\s+") || password.equals("") || password.matches("\\s+")){
            return false;
        }
        System.out.println("FOUND USER: ");
        User user = findByName(username);
        if (user == null) {
            return false;
        }
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

    private User findUserByUsername(String username) {
        User user = null;
        try {
            checkConnection();
            transactionManager.beginRead();
            user = userDao.findByUsername(username);
        } catch (SQLException ex) {
            System.out.println("Connection Lost.");
        } catch (NoResultException e) {
            return null;
        } finally {
            transactionManager.close();
        }
        return user;
    }

    @Override
    public int count() {
        return userCountDB();
    }


    public void addUserDB(User user) {
        try {
            checkConnection();
            transactionManager.beginWrite();
            userDao.createOrUpdate(user);
            transactionManager.commit();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (RollbackException ex) {
            transactionManager.rollback();
        } finally {
           transactionManager.close();
        }
    }

    public User findUserById(int id) {
        User user = null;
        try {
            checkConnection();
            System.out.println("OIIIII");
            transactionManager.beginRead();
            System.out.println("OIIII2");
            user = userDao.findById(id);
        } catch (SQLException ex) {
            System.out.println("Connection Lost.");
        } catch (NoResultException e) {
            return null;
        } finally {
          transactionManager.close();
        }
        return user;
    }

    public void removeUser(User user) {
        try {
            checkConnection();
            transactionManager.beginWrite();
            userDao.remove(user);
            transactionManager.commit();
        } catch (SQLException ex) {
            System.out.println("Connection Lost.");
        } finally {
            transactionManager.close();
        }
    }


    public int userCountDB() {
        int count = 0;
        try {
            checkConnection();
            transactionManager.beginRead();
            count = userDao.userCount();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
           transactionManager.close();
        }
        return count;
    }

    public List<User> userList() {
        List<User> count = null;
        try {
            checkConnection();
            transactionManager.beginRead();
            count = userDao.listAll();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            transactionManager.close();
        }
        return count;
    }

    public void checkConnection() throws SQLException {
        if (dbConnection == null || dbConnection.isClosed()) {
            dbConnection = connectionManager.getConnection();
        }

        if (dbConnection == null) {
            throw new SQLException("No SQL connection");
        }
    }


    public void setConnectionManager(ConnectionManager connectionManager) {
        this.connectionManager = connectionManager;
    }

    public String getName() {
        return name;
    }

    public void setTransactionManager(TransactionManager transactionManager) {
        this.transactionManager = transactionManager;
    }
}
