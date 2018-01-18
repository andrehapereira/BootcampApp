package dao.jpa;

import dao.BootcampDao;
import model.Bootcamp;
import persistence.SessionManager;


public class JPABootcampDao extends JPADao<Bootcamp> implements BootcampDao {
    private SessionManager sm;

    public JPABootcampDao() {
        super(Bootcamp.class);
    }

    public void setSm(SessionManager sm) {
        this.sm = sm;
        super.setSm(sm);
    }
}
