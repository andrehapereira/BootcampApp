package persistence;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionManager {
    private final String dbURL = "jdbc:mysql://localhost:3306/db?useSSL=false";

    private final String user = "root";
    private final String password = "";
    Connection connection = null;

    public  Connection getConnection() {
        try {
            if (connection == null) {
                connection = DriverManager.getConnection(dbURL, user, password);
                System.out.println("connected");
            }
        } catch (SQLException e) {
            System.out.println("Failure to connect to database : " + e.getMessage());
        }
        return connection;
    }

    public void close() {
        try {
            if (connection != null) {
                connection.close();
            }
        } catch (SQLException ex) {
            System.out.println("Failure to close database connections: " + ex.getMessage());
        }
    }
}
