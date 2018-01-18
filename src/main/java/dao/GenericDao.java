package dao;



import model.AbstractModel;

import java.util.List;

public interface GenericDao<T extends AbstractModel> {
    void createOrUpdate(T value);
    T findById(int id);
    void remove(T value);
    List<T> listAll();
}
