package com.example.madfitness.Database;
import java.sql.*;

public class DatabaseConnection {
    private static DatabaseConnection instance;
    private Connection connection=null;
    private String databaseName;

    public DatabaseConnection(String jdbcDriver,String connectionURL,String username,String password){
        try{
            Class.forName(jdbcDriver);
            connection = DriverManager.getConnection(connectionURL, username, password);

            System.out.println("Connection Successful");

            createTable(DBConst.TABLE_USER, DBConst.CREATE_TABLE_USER, connection);
            createTable(DBConst.TABLE_WORKOUT, DBConst.CREATE_TABLE_WORKOUT, connection);
            createTable(DBConst.TABLE_MUSCLE_GROUP, DBConst.CREATE_TABLE_MUSCLE_GROUP, connection);
            createTable(DBConst.TABLE_EXERCISE, DBConst.CREATE_TABLE_EXERCISE, connection);
            createTable(DBConst.TABLE_WORKOUT_EXERCISE, DBConst.CREATE_TABLE_WORKOUT_EXERCISE, connection);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public DatabaseConnection setDatabaseName(String databaseName) {
        this.databaseName = databaseName;
        return this;
    }

    private DatabaseConnection() {}

    //constructor without createTable methods to test connection
    private DatabaseConnection(String jdbcDriver, String connectionURL, String username, String password, boolean test) {
        try {
            Class.forName(jdbcDriver);
            connection = DriverManager.getConnection(connectionURL, username, password);

            System.out.println("Test Connection Successful");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static DatabaseConnection getInstance() {
        if (instance == null) {
            instance = new DatabaseConnection();
        }
        return instance;
    }

    public DatabaseConnection(Connection connection) {
        this.connection = connection;
    }

    public Connection getConnection() {
        return connection;
    }

    public DatabaseConnection setConnection(Connection connection) {
        this.connection = connection;
        return this;
    }

    private void createTable(String tableName, String tableQuery, Connection connection) throws SQLException {
        Statement createTable;
        DatabaseMetaData md = connection.getMetaData();

        //look in the database for a table that matches the tableName

        ResultSet resultSet = md.getTables(databaseName, null, tableName, null);

        if (resultSet.next()) {
            System.out.println(tableName + " table already exists");
        } else {
            createTable = connection.createStatement();
            createTable.execute(tableQuery);
            System.out.println("The " + tableName + " table has been created");
        }
    }

    public static void testConnection(String jdbcDriver, String connectionURL, String username, String password) throws SQLException {
        try (Connection connection = DriverManager.getConnection(connectionURL, username, password)) {
            System.out.println("Test Connection Successful");
        } catch (SQLException e) {
            System.out.println("Test Connection Failed");
            throw e;
        }
    }
}
