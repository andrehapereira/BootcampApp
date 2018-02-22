package persistence;

public class JPATransactionManager implements TransactionManager {

    private SessionManager sm;

    @Override
    public void beginRead() {
        sm.sessionStart();
    }

    @Override
    public void beginWrite() {
        sm.getCurrentSession().getTransaction().begin();
    }

    @Override
    public void commit() {
        if (sm.getCurrentSession().getTransaction().isActive()) {
            sm.getCurrentSession().getTransaction().commit();
        }

        sm.sessionClose();
    }

    @Override
    public void rollback() {
        if (sm.getCurrentSession().getTransaction().isActive()) {
            sm.getCurrentSession().getTransaction().rollback();
        }

        sm.sessionClose();
    }

    public void setSm(SessionManager sm) {
        this.sm = sm;
    }
}
