package services;

import model.Bootcamp;
import model.Codecadet;
import persistence.ConnectionManager;

import javax.persistence.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class JPABootcampService implements BootcampService {
    private EntityManagerFactory emf;
    private String name = "jpabootcampservice";
    private Connection dbConnection;
    private ConnectionManager connectionManager;

    @Override
    public void addBootcampToList(Bootcamp bootcamp) {
        if (findBootcampById(bootcamp.getBootcampNumber()) == null) {
            EntityManager em = emf.createEntityManager();
            try {
                checkConnection();
                em.getTransaction().begin();
                em.merge(bootcamp);
                em.getTransaction().commit();
            } catch (SQLException e) {
                System.out.println("No SQL connection.");
            } catch(RollbackException ex) {
                em.getTransaction().rollback();
            } finally {
                em.close();
            }
        }
    }

    @Override
    public Bootcamp findBootcampById(int id) {
        Bootcamp bootcamp = null;
        EntityManager em = emf.createEntityManager();
        try {
            checkConnection();
            TypedQuery<Bootcamp> query = em.createQuery("SELECT bootcamp FROM Bootcamp bootcamp WHERE bootcamp.bootcampNumber = :id", Bootcamp.class);
            query.setParameter("id",id);
            bootcamp = query.getSingleResult();
        } catch (SQLException e) {
            System.out.println("No SQL Connection.");;
        } catch (NoResultException ex) {
            return null;
        } finally {
            em.close();
        }
        return bootcamp;
    }

    @Override
    public Codecadet findCodecadet(String username) {
        Codecadet codecadet = null;
        EntityManager em = emf.createEntityManager();
        try {
            checkConnection();
            TypedQuery<Codecadet> query = em.createQuery("SELECT codecadet FROM Codecadet codecadet WHERE codecadet.user.username = :id", Codecadet.class);
            query.setParameter("id",username);
            codecadet = query.getSingleResult();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch(NoResultException e) {
            return null;
        } finally {
            em.close();
        }
        return codecadet;
    }

    @Override
    public List<Codecadet> listAllCadets(Bootcamp bootcamp) {
        Bootcamp foundcmp = findBootcampById(bootcamp.getBootcampNumber());
        return new ArrayList<>(foundcmp.getCodecadets().values());
    }

    @Override
    public List<Bootcamp> listAllBootcamps() {
        List<Bootcamp> bootcamps = null;
        EntityManager em = emf.createEntityManager();
        try {
            checkConnection();
            TypedQuery<Bootcamp> query = em.createQuery("SELECT bootcamp FROM Bootcamp bootcamp", Bootcamp.class);
            bootcamps = query.getResultList();
        } catch (SQLException e) {
            System.out.println("No SQL Connection.");;
        } catch (NoResultException ex) {
            return null;
        }finally {
            em.close();
        }
        System.out.println(bootcamps);
        return bootcamps;
    }

    @Override
    public void addToBootcamp(int id, Codecadet codecadet) {
        EntityManager em = emf.createEntityManager();
        try {
            checkConnection();
            em.getTransaction().begin();
            Bootcamp bcamp = findBootcampById(id);
            codecadet.setBootcamp(bcamp);
            bcamp.addToList(codecadet);
            em.merge(bcamp);
            em.getTransaction().commit();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (RollbackException ex) {
            em.getTransaction().rollback();
        } finally {
            em.close();
            System.out.println("Added.");
        }
    }

    @Override
    public void changeBootcamp(String username, int bootcampId) {
        Codecadet codecadet = findCodecadet(username);
        Bootcamp bootcamp = findBootcampById(bootcampId);
        EntityManager em = emf.createEntityManager();
        try {
            checkConnection();
            em.getTransaction().begin();
            codecadet.setBootcamp(bootcamp);
            em.getTransaction().commit();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (RollbackException ex) {
            em.getTransaction().rollback();
        } finally {
            em.close();
        }
    }

    @Override
    public String getName() {
        return name;
    }

    public void checkConnection() throws SQLException {
        if (dbConnection == null || dbConnection.isClosed()) {
            dbConnection = connectionManager.getConnection();
        }

        if (dbConnection == null) {
            throw new SQLException("No SQL connection");
        }
    }

    public void setEmf(EntityManagerFactory emf) {
        this.emf = emf;
    }

    public void setConnectionManager(ConnectionManager connectionManager) {
        this.connectionManager = connectionManager;
    }

}
