package services;

import dao.BootcampDao;
import dao.CodecadetDao;
import dao.jpa.JPABootcampDao;
import dao.jpa.JPACodecadetDao;
import model.Bootcamp;
import model.Codecadet;
import navigation.Navigation;
import org.springframework.transaction.annotation.Transactional;
import persistence.ConnectionManager;
import persistence.TransactionManager;

import javax.persistence.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class JPABootcampService implements BootcampService {
    private final BootcampDao bootcampDao;
    private final CodecadetDao codecadetDao;
    private String name = "bootcampservice";

    public JPABootcampService(BootcampDao bootcampDao, CodecadetDao codecadetDao) {
        this.bootcampDao = bootcampDao;
        this.codecadetDao = codecadetDao;
    }

    @Transactional
    @Override
    public void addBootcampToList(Bootcamp bootcamp) {
        if (findBootcampById(bootcamp.getBootcampNumber()) == null) {
                bootcampDao.createOrUpdate(bootcamp);
        }
    }

    @Transactional
    @Override
    public Bootcamp findBootcampById(int id) {
        Bootcamp bootcamp = bootcampDao.findById(id);
        return bootcamp;
    }

    @Transactional
    @Override
    public Codecadet findCodecadet(String username) {
        Codecadet codecadet = codecadetDao.findByUsername(username);
        return codecadet;
    }

    @Override
    public List<Codecadet> listAllCadets(Bootcamp bootcamp) {
        Bootcamp foundcmp = findBootcampById(bootcamp.getBootcampNumber());
        return new ArrayList<>(foundcmp.getCodecadets().values());
    }

    @Transactional
    @Override
    public List<Bootcamp> listAllBootcamps() {
        List<Bootcamp> bootcamps = bootcampDao.listAll();
        return bootcamps;
    }

    @Transactional
    @Override
    public void addToBootcamp(int id, Codecadet codecadet) {
            Bootcamp bcamp = bootcampDao.findById(id);
            codecadet.setBootcamp(bcamp);
            bcamp.addToList(codecadet);
            bootcampDao.createOrUpdate(bcamp);
    }

    @Transactional
    @Override
    public void changeBootcamp(String username, int bootcampId) {

            Codecadet codecadet = codecadetDao.findByUsername(username);
            Bootcamp bootcamp = bootcampDao.findById(bootcampId);
            Bootcamp currentBootcamp = bootcampDao.findById(codecadet.getBootcamp().getId());
            currentBootcamp.getCodecadets().remove(codecadet);
            bootcamp.getCodecadets().put(codecadet.getUser().getUsername(), codecadet);
            codecadet.setBootcamp(bootcamp);
            bootcampDao.createOrUpdate(currentBootcamp);
    }

    @Override
    public String getName() {
        return name;
    }

}
