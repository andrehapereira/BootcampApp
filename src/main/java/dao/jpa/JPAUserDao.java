package dao.jpa;

import dao.UserDao;
import model.User;
import persistence.SessionManager;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

public class JPAUserDao extends JPADao<User> implements UserDao {
    @PersistenceContext
    private EntityManager entityManager;

    public JPAUserDao() {
        super(User.class);
    }


    @Override
    public int userCount() {
        TypedQuery<User> query = entityManager.createQuery("SELECT user FROM User user", User.class);
        return query.getResultList().size();
    }

    @Override
    public User findByUsername(String username) {
        TypedQuery<User> query = entityManager.createQuery("SELECT user FROM User user WHERE username = :username", User.class);
        query.setParameter("username", username);
        return query.getSingleResult();
    }
}
