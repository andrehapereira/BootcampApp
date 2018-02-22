package dao.jpa;

import dao.CodecadetDao;
import model.Codecadet;
import persistence.SessionManager;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;


public class JPACodecadetDao extends JPADao<Codecadet> implements CodecadetDao {

    @PersistenceContext
    private EntityManager entityManager;

    public JPACodecadetDao() {
        super(Codecadet.class);
    }


    @Override
    public Codecadet findByUsername(String username) {
        TypedQuery<Codecadet> query = entityManager.createQuery("SELECT code FROM Codecadet code WHERE user.username = :username", Codecadet.class);
        query.setParameter("username", username);
        return query.getSingleResult();
    }
}
