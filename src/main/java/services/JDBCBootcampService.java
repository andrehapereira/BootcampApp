package services;

import model.Bootcamp;
import model.Codecadet;
import persistence.ConnectionManager;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class JDBCBootcampService implements BootcampService {
    private String name = "jdbcbootcampservice";
    private Connection dbConnection;
    private ConnectionManager connectionManager;

    @Override
    public void addBootcampToList(Bootcamp bootcamp) {
       if (findBootcampById(bootcamp.getBootcampNumber()) == null) {
           try {
               checkConnection();
               String insertQuery = "INSERT INTO bootcamps(id, location, start_date, end_date) VALUES (?,?,?,?);";
               PreparedStatement statement = dbConnection.prepareStatement(insertQuery);

               statement.setInt(1,bootcamp.getBootcampNumber());
               statement.setString(2, bootcamp.getLocation());
               statement.setDate(3, bootcamp.getStart());
               statement.setDate(4,bootcamp.getEnd());

               statement.executeUpdate();
               System.out.println("bootcamp added");
               statement.close();
           } catch (SQLException e) {
               e.printStackTrace();
           }
       }
    }

    @Override
    public Bootcamp findBootcampById(int id) {
        Bootcamp bootcamp = null;
        try {
            checkConnection();
            String selectQuery = "SELECT * FROM bootcamps WHERE id = ?;";
            PreparedStatement statement = dbConnection.prepareStatement(selectQuery);
            statement.setInt(1,id);
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                bootcamp = new Bootcamp(resultSet.getInt("id"), resultSet.getString("location"),
                        resultSet.getDate("start_date"), resultSet.getDate("end_date"));
            }

            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return bootcamp;
    }

    @Override
    public Codecadet findCodecadet(String username) {
        Codecadet codecadet = null;
        try {
            String selectQuery = "SELECT * FROM codecadets WHERE username=?";
            PreparedStatement statement = dbConnection.prepareStatement(selectQuery);
            statement.setString(1,username);
            ResultSet resultSet = statement.executeQuery();

            if(resultSet.next()) {
                System.out.println(resultSet.getString("username"));
                //codecadet = new Codecadet()
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    return codecadet;
    }

    @Override
    public List<Codecadet> listAllCadets(Bootcamp bootcamp) {
        return null;
    }

    @Override
    public List<Bootcamp> listAllBootcamps() {
        List<Bootcamp> bootcamps = new ArrayList<>();
        try {
            checkConnection();
            Statement statement = dbConnection.createStatement();
            String selectQuery = "SELECT * FROM bootcamps;";
            ResultSet resultSet = statement.executeQuery(selectQuery);

            while (resultSet.next()) {
                bootcamps.add(new Bootcamp(resultSet.getInt("id"), resultSet.getString("location"),
                        resultSet.getDate("start_date"), resultSet.getDate("end_date")));
            }

            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        System.out.println(bootcamps);
        return bootcamps;
    }

    @Override
    public void addToBootcamp(int id, Codecadet codecadet) {
        String gender;
        switch (codecadet.getGender()) {
            case MALE:
                gender = "MALE";
                break;
            case FEMALE:
                gender = "FEMALE";
                break;
            default:
                gender = "SS";
                break;
        }
        try {
            checkConnection();
            String selectQuery = "INSERT INTO codecadets(username, gender, address, city, phone_number, bootcamp, date) VALUES (?,?,?,?,?,?,?)";
            PreparedStatement statement = dbConnection.prepareStatement(selectQuery);
            statement.setString(1,codecadet.getUser().getUsername());
            statement.setString(2, gender);
            statement.setString(3,codecadet.getAddress());
            statement.setString(4,codecadet.getCity());
            statement.setString(5,codecadet.getPhone());
            statement.setInt(6,id);
            statement.setDate(7,codecadet.getBirthday());


            statement.executeUpdate();
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void changeBootcamp(String username, int bootcampId) {
        try {
            checkConnection();
            String selectQuery = "UPDATE codecadets SET bootcamp=? WHERE username=?;";
            PreparedStatement statement = dbConnection.prepareStatement(selectQuery);
            statement.setInt(1,bootcampId);
            statement.setString(2,username);
            statement.executeUpdate();
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
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


    public void setConnectionManager(ConnectionManager connectionManager) {
        this.connectionManager = connectionManager;
    }

}
