package persistence;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceUnit;

public class SessionManager {

    @PersistenceUnit(name="pUnit")
    private EntityManagerFactory emf;
    private EntityManager em;

    public void sessionStart() {
        if (em == null) {
            em = emf.createEntityManager();
        }
    }

    public void sessionClose() {
        if (em != null) {
            em.close();
        }
        em = null;
    }

    public EntityManager getCurrentSession() {
        sessionStart();
        return em;
    }
}
