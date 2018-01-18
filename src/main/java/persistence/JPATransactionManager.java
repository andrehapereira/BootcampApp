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
    }

    @Override
    public void rollback() {
        if (sm.getCurrentSession().getTransaction().isActive()) {
            sm.getCurrentSession().getTransaction().rollback();
        }
    }

    @Override
    public void close() {
        if(sm.getCurrentSession().getTransaction().isActive()) {
            sm.sessionClose();
        }
    }

    public void setSessionManager(SessionManager sm) {
        this.sm = sm;
    }
}
