package dao.jpa;

import dao.CodecadetDao;
import model.Codecadet;
import persistence.SessionManager;

import javax.persistence.TypedQuery;


public class JPACodecadetDao extends JPADao<Codecadet> implements CodecadetDao {

    private SessionManager sm;

    public JPACodecadetDao() {
        super(Codecadet.class);
    }



    public void setSm(SessionManager sm) {
        this.sm = sm;
        super.setSm(sm);
    }

    @Override
    public Codecadet findByUsername(String username) {
        TypedQuery<Codecadet> query = sm.getCurrentSession().createQuery("SELECT code FROM Codecadet code WHERE user.username = :username", Codecadet.class);
        query.setParameter("username", username);
        return query.getSingleResult();
    }
}
