package services;

import dao.BootcampDao;
import dao.CodecadetDao;
import dao.jpa.JPABootcampDao;
import dao.jpa.JPACodecadetDao;
import model.Bootcamp;
import model.Codecadet;
import persistence.ConnectionManager;
import persistence.TransactionManager;

import javax.persistence.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class JPABootcampService implements BootcampService {
    private final BootcampDao bootcampDao;
    private final CodecadetDao codecadetDao;
    private TransactionManager transactionManager;
    private String name = "jpabootcampservice";
    private Connection dbConnection;
    private ConnectionManager connectionManager;

    public JPABootcampService(BootcampDao jpaBootcampDao, CodecadetDao codecadetDao) {
        this.bootcampDao = jpaBootcampDao;
        this.codecadetDao = codecadetDao;
    }

    @Override
    public void addBootcampToList(Bootcamp bootcamp) {
        if (findBootcampById(bootcamp.getBootcampNumber()) == null) {
            try {
                checkConnection();
                transactionManager.beginWrite();
                bootcampDao.createOrUpdate(bootcamp);
                transactionManager.commit();
            } catch (SQLException e) {
                System.out.println(e);
            } catch(RollbackException ex) {
                transactionManager.rollback();
            } finally {
                transactionManager.close();
            }
        }
    }

    @Override
    public Bootcamp findBootcampById(int id) {
        Bootcamp bootcamp = null;
        try {
            checkConnection();
            transactionManager.beginRead();
            bootcamp = bootcampDao.findById(id);
        } catch (SQLException e) {
            System.out.println(e);;
        } catch (NoResultException ex) {
            return null;
        } finally {
            transactionManager.close();
        }
        return bootcamp;
    }

    @Override
    public Codecadet findCodecadet(String username) {
        Codecadet codecadet = null;
        try {
            checkConnection();
            transactionManager.beginRead();
            codecadet = codecadetDao.findByUsername(username);
        } catch (SQLException e) {
            e.printStackTrace();
        } catch(NoResultException e) {
            return null;
        } finally {
           transactionManager.close();
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
        try {
            checkConnection();
            transactionManager.beginRead();
            bootcamps = bootcampDao.listAll();
        } catch (SQLException e) {
            System.out.println(e);
        } catch (NoResultException ex) {
            return null;
        }finally {
            transactionManager.close();
        }
        System.out.println(bootcamps);
        return bootcamps;
    }

    @Override
    public void addToBootcamp(int id, Codecadet codecadet) {
        try {
            checkConnection();
            transactionManager.beginWrite();
            Bootcamp bcamp = bootcampDao.findById(id);
            codecadet.setBootcamp(bcamp);
            bcamp.addToList(codecadet);
            bootcampDao.createOrUpdate(bcamp);
            transactionManager.commit();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (RollbackException ex) {
            transactionManager.rollback();
        } finally {
            transactionManager.close();
            System.out.println("Added.");
        }
    }

    @Override
    public void changeBootcamp(String username, int bootcampId) {
        try {
            checkConnection();
            transactionManager.beginWrite();
            Codecadet codecadet = codecadetDao.findByUsername(username);
            Bootcamp bootcamp = bootcampDao.findById(bootcampId);
            Bootcamp currentBootcamp = bootcampDao.findById(codecadet.getBootcamp().getId());
            currentBootcamp.getCodecadets().remove(codecadet);
            bootcamp.getCodecadets().put(codecadet.getUser().getUsername(), codecadet);
            codecadet.setBootcamp(bootcamp);
            bootcampDao.createOrUpdate(currentBootcamp);
            transactionManager.commit();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (RollbackException ex) {
            transactionManager.rollback();
        } finally {
            transactionManager.close();
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
            throw new SQLException("No SQL connection.");
        }
    }

    public void setTransactionManager(TransactionManager transactionManager) {
        this.transactionManager = transactionManager;
    }

    public void setConnectionManager(ConnectionManager connectionManager) {
        this.connectionManager = connectionManager;
    }

}
