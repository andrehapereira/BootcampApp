package persistence;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

public class SessionManager {
    private EntityManagerFactory emf;
    private EntityManager em;

    public void sessionStart() {
        if (em == null) {
            System.out.println(em);
            em = emf.createEntityManager();
            System.out.println(em);
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

    public void setEmf(EntityManagerFactory emf) {
        this.emf = emf;
    }
}
