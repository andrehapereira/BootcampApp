package dao.jpa;

import dao.GenericDao;

import model.AbstractModel;
import persistence.SessionManager;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;


public abstract class JPADao<T extends AbstractModel> implements GenericDao<T> {
    private SessionManager sm;
    Class<T> tClass;

    public JPADao(Class<T> tClass) {
        this.tClass = tClass;
    }


    @Override
    public void createOrUpdate(T value) {
        sm.getCurrentSession().merge(value);
    }

    @Override
    public T findById(int id) {
        return sm.getCurrentSession().find(tClass, id);
    }

    @Override
    public void remove(T value) {
        sm.getCurrentSession().remove(value);
    }

    @Override
    public List<T> listAll() {
        CriteriaBuilder builder = sm.getCurrentSession().getCriteriaBuilder();
        CriteriaQuery<T> criteriaQuery = builder.createQuery(tClass);
        Root<T> root = criteriaQuery.from(tClass);
        criteriaQuery.select(root);
        return sm.getCurrentSession().createQuery(criteriaQuery).getResultList();
    }

    public void setSm(SessionManager sm) {
        this.sm = sm;
    }
}
