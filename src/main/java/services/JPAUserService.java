package services;

import model.User;
import persistence.ConnectionManager;

import javax.persistence.*;
import java.sql.*;
import java.util.List;

public class JPAUserService implements UserService {
    private EntityManagerFactory emf;
    private String name = "jpauserservice";
    private Connection dbConnection;
    private ConnectionManager connectionManager;


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
        return findUserDB(username);
    }

    @Override
    public int count() {
        return userCountDB();
    }


    public void addUserDB(User user) {
        EntityManager em = emf.createEntityManager();
        try {
            checkConnection();
            em.getTransaction().begin();
            em.merge(user);
            em.getTransaction().commit();

        } catch (SQLException e) {
            e.printStackTrace();
        } catch (RollbackException ex) {
            em.getTransaction().rollback();
        } finally {
            em.close();
        }
    }

    public User findUserDB(String username) {
        User user = null;
            EntityManager em = emf.createEntityManager();
        try {
            checkConnection();
            TypedQuery<User> query = em.createQuery("SELECT user FROM User user WHERE user.username = :name", User.class);
            query.setParameter("name", username);
            user = query.getSingleResult();
        } catch (SQLException ex) {
            System.out.println("Connection Lost.");
        } catch (NoResultException e) {
            return null;
        } finally {
            if (em != null) {
                em.close();
            }
        }
        return user;
    }


    public int userCountDB() {
        int count = 0;
        EntityManager em = emf.createEntityManager();
        try {
            checkConnection();
            TypedQuery<User> query = em.createQuery("SELECT user FROM User user", User.class);
            count = query.getResultList().size();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return count;
    }

    public List<User> userList() {
        List<User> count = null;
        EntityManager em = emf.createEntityManager();
        try {
            checkConnection();
            TypedQuery<User> query = em.createQuery("SELECT user FROM User user", User.class);
            count = query.getResultList();
        } catch (SQLException e) {
            e.printStackTrace();
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

    public void setEmf(EntityManagerFactory emf) {
        this.emf = emf;
    }
}
