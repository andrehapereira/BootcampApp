package services;


import model.User;
import persistence.ConnectionManager;

import java.sql.*;

public class JDBCUserService implements UserService {
    private String name = "jdbcservice";
    private Connection dbConnection;
    private ConnectionManager connectionManager;


    @Override
    public boolean autenthicate(String username, String password) {
        if (username.equals("") || username.matches("\\s+") || password.equals("") || password.matches("\\s+")){
            return false;
        }
        User user = findByName(username);
        if (user == null) {
            return false;
        }
        return user.getUsername().equals(username) && user.getPassword().equals(password);
    }

    @Override
    public void addUser(User user) {
        if (user.getUsername().equals("") || user.getUsername().matches("\\s+") || user.getPassword().equals("") || user.getPassword().matches("\\s+")){
            return;
        }
        if(findByName(user.getUsername()) == null) {
            addUserDB(user);
            //userList.put(user.getUsername(),user);
        }
    }

    @Override
    public User findByName(String username) {
        return findUserDB(username);
    }

    @Override
    public int count() {
        return userCountDB();
    }


        public void addUserDB(User user) {
        try {

            checkConnection();
            String insertQuery = "INSERT INTO users (username, password, email) VALUES (?, ?, ?)";

            PreparedStatement statement1 = dbConnection.prepareStatement(insertQuery);

            statement1.setString(1, user.getUsername());
            statement1.setString(2, user.getPassword());
            statement1.setString(3,user.getEmail());

            statement1.executeUpdate();

            System.out.println("user added to db");
            statement1.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public User findUserDB(String username) {
        User user = null;
        try {
            checkConnection();
            String selectQuery = "SELECT * FROM users WHERE username = ?;";
            PreparedStatement statement = dbConnection.prepareStatement(selectQuery);
            statement.setString(1,username);
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
              user = new User(resultSet.getString("username"), resultSet.getString("password") , resultSet.getString("email"));
                System.out.println("found user");
            }
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return user;
    }


    public int userCountDB() {
        int count = 0;
        try {
            checkConnection();
            Statement statement = dbConnection.createStatement();
            String selectQuery = "SELECT COUNT(*) FROM users;";
            ResultSet resultSet = statement.executeQuery(selectQuery);
            count = resultSet.getInt(1);
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return count;
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

    public String getName() {
        return name;
    }
}
