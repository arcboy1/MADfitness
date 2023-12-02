package com.example.madfitness.Database;
import java.sql.*;

public class DatabaseConnection {
    private static DatabaseConnection instance;
    private Connection connection=null;
    private String databaseName;

    private DatabaseConnection() {
    }
    public void setLoginInfo(String jdbcDriver, String connectionURL, String username, String password, String databaseName) {
        try {
            Class.forName(jdbcDriver);
            connection = DriverManager.getConnection(connectionURL, username, password);
            this.databaseName = databaseName;

            System.out.println("Connection Successful");

            createTable(DBConst.TABLE_WORKOUT, DBConst.CREATE_TABLE_WORKOUT, connection);
            createTable(DBConst.TABLE_MUSCLE_GROUP, DBConst.CREATE_TABLE_MUSCLE_GROUP, connection);
            createTable(DBConst.TABLE_EXERCISE_TYPE, DBConst.CREATE_TABLE_EXERCISE_TYPE, connection);
            createTable(DBConst.TABLE_EXERCISE, DBConst.CREATE_TABLE_EXERCISE, connection);
            createTable(DBConst.TABLE_WORKOUT_EXERCISE, DBConst.CREATE_TABLE_WORKOUT_EXERCISE, connection);
            insertIntoTable(DBConst.TABLE_MUSCLE_GROUP, DBConst.INSERT_MUSCLE_GROUPS, connection);
            insertIntoTable(DBConst.TABLE_EXERCISE_TYPE, DBConst.INSERT_EXERCISE_TYPES, connection);
            insertIntoTable(DBConst.TABLE_EXERCISE, DBConst.INSERT_COMMON_EXERCISES, connection);



            // Add other initialization logic if needed
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public DatabaseConnection setDatabaseName(String databaseName) {
        this.databaseName = databaseName;
        return this;
    }



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
    private void insertIntoTable(String tableName, String insertQuery, Connection connection) throws SQLException {
        Statement insertStatement;
        PreparedStatement checkValuesStatement;

        // Check if values already exist in the table
        checkValuesStatement = connection.prepareStatement("SELECT COUNT(*) FROM " + tableName);
        ResultSet countResult = checkValuesStatement.executeQuery();

        if (countResult.next() && countResult.getInt(1) > 0) {
            System.out.println("Values already exist in " + tableName + " table. Not inserting again.");
        } else {
            // Values don't exist, insert them
            insertStatement = connection.createStatement();
            insertStatement.execute(insertQuery);
            System.out.println("Values inserted into " + tableName + " table");
        }
    }



}
