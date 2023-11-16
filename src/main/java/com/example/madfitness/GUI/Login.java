package com.example.madfitness.GUI;

import com.example.madfitness.Database.DatabaseConnection;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class Login extends Application {
    /**
     * @param primaryStage the primary stage for this application, onto which
     *                     the application scene can be set.
     *                     Applications may create other stages, if needed, but they will not be
     *                     primary stages.
     * @throws Exception
     */
    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Database Login");

        // Create UI elements
        Label usernameLabel = new Label("Username:");
        TextField usernameField = new TextField();

        Label passwordLabel = new Label("Password:");
        PasswordField passwordField = new PasswordField();

        Label databaseLabel = new Label("Database Name:");
        TextField databaseField = new TextField();

        Label locationLabel = new Label("Database Location:");
        TextField locationField = new TextField();

        Button loginButton = new Button("Login");
        Button testConnectionButton=new Button("Test Connection");

        // Create a layout
        VBox vbox = new VBox(10);
        vbox.getChildren().addAll(usernameLabel, usernameField, passwordLabel, passwordField, databaseLabel, databaseField, locationLabel, locationField,testConnectionButton, loginButton);

        // Create a scene
        Scene scene = new Scene(vbox, 350, 350);

        primaryStage.setScene(scene);
        primaryStage.show();

        testConnectionButton.setOnAction(event -> {
            String username = usernameField.getText();
            String password = passwordField.getText();
            String databaseName = databaseField.getText();
            String location = locationField.getText();

            String jdbcDriver = "com.mysql.cj.jdbc.Driver";
            String connectionURL = "jdbc:mysql://" + location + "/" + databaseName + "?serverTimezone=UTC";

            try{
                DatabaseConnection.testConnection(jdbcDriver,connectionURL,username,password);
                System.out.println("Test Connection Succesful");
            }catch (Exception e){
                e.printStackTrace();
            }
        });

        loginButton.setOnAction(event -> {
            String username = usernameField.getText();
            String password = passwordField.getText();
            String databaseName = databaseField.getText();
            String location = locationField.getText();

            String jdbcDriver="com.mysql.cj.jdbc.Driver";
            String connectionURL="jdbc:mysql://" + location + "/" + databaseName + "?serverTimezone=UTC";


            try{
                DatabaseConnection databaseConnection=new DatabaseConnection(jdbcDriver,connectionURL,username,password)
                        .setDatabaseName(databaseName);
                System.out.println("Connection Succesful");
            }catch (Exception e){
                e.printStackTrace();
            }

        });
    }

}
