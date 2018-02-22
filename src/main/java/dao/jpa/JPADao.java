package dao.jpa;

import dao.GenericDao;

import model.AbstractModel;
import org.springframework.transaction.annotation.Transactional;
import persistence.SessionManager;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;


public abstract class JPADao<T extends AbstractModel> implements GenericDao<T> {

    @PersistenceContext
    private EntityManager entityManager;

    Class<T> tClass;

    public JPADao(Class<T> tClass) {
        this.tClass = tClass;
    }

    @Transactional
    @Override
    public void createOrUpdate(T value) {
        entityManager.merge(value);
    }

    @Override
    public T findById(int id) {
        return entityManager.find(tClass, id);
    }

    @Transactional
    @Override
    public void remove(T value) {
        entityManager.remove(value);
    }

    @Override
    public List<T> listAll() {
        CriteriaBuilder builder = entityManager.getCriteriaBuilder();
        CriteriaQuery<T> criteriaQuery = builder.createQuery(tClass);
        Root<T> root = criteriaQuery.from(tClass);
        criteriaQuery.select(root);
        List<T> lista = entityManager.createQuery(criteriaQuery).getResultList();
        System.out.println(lista);
        return lista;
    }

}
