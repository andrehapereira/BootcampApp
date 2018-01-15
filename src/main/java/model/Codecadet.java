package model;

import javax.persistence.*;
import java.sql.Date;

@Entity
@Table(name = "codecadet")
public class Codecadet {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String name;

    @OneToOne
    private User user;
    private Gender gender;
    private String address;
    private String city;
    private String phone;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Bootcamp bootcamp;

    private java.sql.Date birthday;

    public Codecadet() {}

    public Codecadet(User user, Gender gender, String address, String city, String phone, java.sql.Date birthday) {
        this.user = user;
        this.gender = gender;
        this.address = address;
        this.city = city;
        this.phone = phone;
        this.birthday = birthday;
        this.name = user.getUsername();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Codecadet codecadet = (Codecadet) o;

        return user != null ? user.equals(codecadet.user) : codecadet.user == null;
    }

    @Override
    public int hashCode() {
        return user != null ? user.hashCode() : 0;
    }

    public enum Gender {
        MALE,
        FEMALE,
        STRANGESPECIE
    }

    public User getUser() {
        return user;
    }

    public String getName() {
        return name;
    }

    public Gender getGender() {
        return gender;
    }

    public String getAddress() {
        return address;
    }

    public String getCity() {
        return city;
    }

    public String getPhone() {
        return phone;
    }

    public Bootcamp getBootcamp() {
        return bootcamp;
    }

    public java.sql.Date getBirthday() {
        return birthday;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    @Override
    public String toString() {
        return "Codecadet{" +
                "user=" + user +
                ", gender=" + gender +
                ", bootcamp=" + bootcamp +
                ", birthday=" + birthday +
                '}';
    }

    public void setBootcamp(Bootcamp bootcamp) {
        this.bootcamp = bootcamp;
    }
}
