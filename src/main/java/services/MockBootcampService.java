/*package services;

import model.Bootcamp;
import model.Codecadet;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MockBootcampService implements BootcampService {
    private String name = "bootcampservice";
    private Map<Integer, Bootcamp> bootcamps = new HashMap<>();

    @Override
    public List<Codecadet> listAllCadets(Bootcamp bootcamp) {
        return new ArrayList<>(bootcamp.getCodecadets().values());
    }

    @Override
    public List<Bootcamp> listAllBootcamps() {
        return new ArrayList<>(bootcamps.values());
    }

    @Override
    public void addToBootcamp(int id, Codecadet codecadet) {
        Codecadet find = findCodecadet(codecadet.getUser().getUsername());
        if(find != null) {
            return;
        }
        bootcamps.get(id).getCodecadets().put(codecadet.getUser().getUsername(), codecadet);
        bootcamps.get(id).getCodecadets().get(codecadet.getUser().getUsername()).setBootcamp(bootcamps.get(id));
    }

    @Override
    public void addBootcampToList(Bootcamp bootcamp) {
        if (!bootcamps.containsKey(bootcamp.getBootcampNumber())) {
            bootcamps.put(bootcamp.getBootcampNumber(), bootcamp);
        }
    }

    @Override
    public Bootcamp findBootcampById(int id) {
        return bootcamps.get(id);
    }

    @Override
    public Codecadet findCodecadet(String username) {
        Codecadet result = null;
        for (Bootcamp b : bootcamps.values()) {
            if(b.getCodecadets().containsKey(username)) {
                result = b.getCodecadets().get(username);
                break;
            }
        }
        return result;
    }

    @Override
    public void changeBootcamp(String username, int bootcampId) {
        Codecadet find = findCodecadet(username);
        if(find == null) {
            return;
        }
        if(!bootcamps.containsKey(bootcampId)) {
            return;
        }
        bootcamps.get(find.getBootcamp().getBootcampNumber()).getCodecadets().remove(find.getUser().getUsername(), find);
        find.setBootcamp(bootcamps.get(bootcampId));
        bootcamps.get(bootcampId).getCodecadets().put(find.getUser().getUsername(), find);

    }

    public String getName() {
        return name;
    }
}*/
