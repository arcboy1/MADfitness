package com.example.madfitness.Database;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {
    public static Connection connect(String location, String databaseName, String username, String password) throws SQLException {
        Connection connection = null;

        try {
            // Construct the URL to connect to your specific database
            String url = "jdbc:your_database_system://" + location + "/" + databaseName;
            // For example, for MySQL: "jdbc:mysql://localhost:3306/your_database_name"

            // Attempt to connect
            connection = DriverManager.getConnection(url, username, password);

            if (connection != null) {
                System.out.println("Connected to the database!");
            }

        } catch (SQLException e) {
            System.out.println("Connection failed!");
            e.printStackTrace();
        }

        return connection;
    }

}
