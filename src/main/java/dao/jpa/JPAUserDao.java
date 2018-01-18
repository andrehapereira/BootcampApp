package dao.jpa;

import dao.UserDao;
import model.User;
import persistence.SessionManager;

import javax.persistence.TypedQuery;
import java.util.List;

public class JPAUserDao extends JPADao<User> implements UserDao {
    private SessionManager sm;

    public JPAUserDao() {
        super(User.class);
    }


    public void setSm(SessionManager sm) {
        this.sm = sm;
        super.setSm(sm);
    }

    @Override
    public int userCount() {
        TypedQuery<User> query = sm.getCurrentSession().createQuery("SELECT user FROM User user", User.class);
        return query.getResultList().size();
    }

    @Override
    public User findByUsername(String username) {
        TypedQuery<User> query = sm.getCurrentSession().createQuery("SELECT user FROM User user WHERE username = :username", User.class);
        query.setParameter("username", username);
        return query.getSingleResult();
    }
}
