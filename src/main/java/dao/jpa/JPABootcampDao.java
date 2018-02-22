package dao.jpa;

import dao.BootcampDao;
import model.Bootcamp;
import persistence.SessionManager;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;


public class JPABootcampDao extends JPADao<Bootcamp> implements BootcampDao {

    @PersistenceContext
    private EntityManager entityManager;

    public JPABootcampDao() {
        super(Bootcamp.class);
    }

}
