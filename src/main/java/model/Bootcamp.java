package model;

import javax.persistence.*;
import java.sql.Date;
import java.util.HashMap;
import java.util.Map;

@Entity

@Table(name="bootcamp")
public class  Bootcamp {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private int bootcampNumber;
    private String location;
    private Date start;
    private Date end;

    @OneToMany(
            fetch = FetchType.EAGER,
            cascade = {CascadeType.ALL},
            orphanRemoval = true,
            mappedBy = "bootcamp"
    )
    private Map<String, Codecadet> codecadets;

    public Bootcamp(){}

    public Bootcamp(int id, String location, Date start, Date end) {
        this.bootcampNumber = id;
        this.location = location;
        this.start = start;
        this.end = end;
        codecadets = new HashMap<>();
    }

    public Integer getId() {
        return id;
    }

    public int getBootcampNumber() {
        return bootcampNumber;
    }

    public String getLocation() {
        return location;
    }

    public Date getStart() {
        return start;
    }

    public Date getEnd() {
        return end;
    }

    public void setBootcampNumber(int bootcampNumber) {
        this.bootcampNumber = bootcampNumber;
    }

    public void setEnd(Date end) {
        this.end = end;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public void setStart(Date start) {
        this.start = start;
    }

    public Map<String, Codecadet> getCodecadets() {
        return codecadets;
    }

    public void addToList(Codecadet cd) {
        codecadets.put(cd.getUser().getUsername(), cd);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Bootcamp bootcamp = (Bootcamp) o;

        return bootcampNumber == bootcamp.bootcampNumber;
    }

    @Override
    public int hashCode() {
        return bootcampNumber;
    }

    @Override
    public String toString() {
        return "Bootcamp{" +
                "id=" + bootcampNumber +
                ", location='" + location + '\'' +
                ", start=" + start +
                ", end=" + end +
                '}';
    }
}
