package dao;

import model.Bootcamp;
import model.Codecadet;

import java.util.List;

public interface CodecadetDao extends GenericDao<Codecadet> {
    Codecadet findByUsername(String username);
}
