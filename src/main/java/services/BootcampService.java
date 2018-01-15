package services;


import model.Bootcamp;
import model.Codecadet;

import java.util.List;

public interface BootcampService extends Service {
    void addBootcampToList(Bootcamp bootcamp);
    Bootcamp findBootcampById(int id);
    Codecadet findCodecadet(String username);
    List<Codecadet> listAllCadets(Bootcamp bootcamp);
    List<Bootcamp> listAllBootcamps();
    void addToBootcamp(int id, Codecadet codecadet);
    void changeBootcamp(String username, int bootcampId);
}
