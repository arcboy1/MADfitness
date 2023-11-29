package com.example.madfitness.GUI;

import com.example.madfitness.Database.DBConst;
import com.example.madfitness.Database.DatabaseConnection;
import com.example.madfitness.Form;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class UserLogin extends Application {
    /**
     * @param primaryStage the primary stage for this application, onto which
     *                     the application scene can be set.
     *                     Applications may create other stages, if needed, but they will not be
     *                     primary stages.
     * @throws Exception
     */
    @Override
    public void start(Stage primaryStage) throws Exception {
        primaryStage.setTitle("User Login");

        Label usernameLabel=new Label("Username:");
        TextField usernameField=new TextField();

        Label passwordLabel = new Label("Password:");
        PasswordField passwordField = new PasswordField();

        Button newUserButton = new Button("New User");
        Button loginButton = new Button("Login");

        VBox vbox = new VBox(10);
        HBox buttonBox = new HBox(10);

        buttonBox.getChildren().addAll(newUserButton, loginButton);
        vbox.getChildren().addAll(usernameLabel, usernameField, passwordLabel, passwordField, buttonBox);

        Scene scene = new Scene(vbox, 300, 200);

        primaryStage.setScene(scene);
        primaryStage.show();

        newUserButton.setOnAction(event -> {
            String username = usernameField.getText();
            String password = passwordField.getText();

            try {
                // check if the username already exists
                if (!doesUserExist(username)) {
                    // if the username doesn't exist, insert the new user
                    addUser(username, password);

                    System.out.println("User created successfully!");
                } else {
                    System.out.println("Username already exists. Please choose a different username.");
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }


        });

        loginButton.setOnAction(event -> {
            String username = usernameField.getText();
            String password = passwordField.getText();

            //if login successful open form page

            //TODO add code to retrieve user id and pass to form page
            try {
                if (isLoginSuccessful(username, password)) {
                    openForm(primaryStage);
                } else {
                    System.out.println("Login failed");
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        });


    }
    private boolean doesUserExist(String username) throws SQLException {
        // construct the SQL query to check if the username already exists
        String query = "SELECT COUNT(*) FROM " + DBConst.TABLE_USER + " WHERE " +
                DBConst.USER_COLUMN_USERNAME + " = '" + username + "'";

        try (Statement statement = DatabaseConnection.getInstance().getConnection().createStatement()) {
            try (ResultSet resultSet = statement.executeQuery(query)) {
                if (resultSet.next()) {
                    int count = resultSet.getInt(1);
                    return count > 0; // if count is greater than 0, the username already exists
                }
            }
        }
        return false;
    }

    private void addUser(String username, String password) throws SQLException {
        // construct the SQL query to insert the new user into the user table
        String query = "INSERT INTO " + DBConst.TABLE_USER + " (" +
                DBConst.USER_COLUMN_USERNAME + ", " +
                DBConst.USER_COLUMN_PASSWORD + ") VALUES ('" + username + "', '" + password + "')";

        try (Statement statement = DatabaseConnection.getInstance().getConnection().createStatement()) {
            statement.executeUpdate(query);
        }
    }

    //this method executes a query that checks if username and password exist in the table, if count > 0 login succesful
    private boolean isLoginSuccessful(String username, String password) throws SQLException {
        String query = "SELECT COUNT(*) FROM " + DBConst.TABLE_USER + " WHERE " +
                DBConst.USER_COLUMN_USERNAME + " = '" + username + "' AND " +
                DBConst.USER_COLUMN_PASSWORD + " = '" + password + "'";

        try (Statement statement = DatabaseConnection.getInstance().getConnection().createStatement()) {
            try (ResultSet resultSet = statement.executeQuery(query)) {
                if (resultSet.next()) {
                    int count = resultSet.getInt(1);
                    return count > 0;
                }
            }
        }
        return false;
    }

    private void openForm(Stage primaryStage){
        try{
            Form form=new Form();
            //TODO create code to handle user id to form page
            form.start(primaryStage);
        }catch (Exception e){
            e.printStackTrace();
        }
    }


}
