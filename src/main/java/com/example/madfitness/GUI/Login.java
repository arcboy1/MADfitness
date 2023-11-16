package com.example.madfitness.GUI;

import com.example.madfitness.Database.DatabaseConnection;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.sql.SQLException;

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
        TextField usernameField = new TextField("ntaggart");

        Label passwordLabel = new Label("Password:");
        PasswordField passwordField = new PasswordField();

        Label databaseLabel = new Label("Database Name:");
        TextField databaseField = new TextField("ntaggartmd");

        Label locationLabel = new Label("Database Location:");
        TextField locationField = new TextField("localhost");

        Button loginButton = new Button("Login");
        Button testConnectionButton=new Button("Test Connection");
        loginButton.setDisable(true);

        Text testConnectionResultText = new Text("");

        // Create a layout
        VBox vbox = new VBox(10);
        HBox hbox=new HBox(10);
        hbox.getChildren().addAll(testConnectionButton, testConnectionResultText);
        vbox.getChildren().addAll(usernameLabel, usernameField, passwordLabel, passwordField, databaseLabel, databaseField, locationLabel, locationField, hbox, loginButton);

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
                loginButton.setDisable(false);
                testConnectionResultText.setText("Test Connection Succesful");
            } catch (SQLException e) {
                testConnectionResultText.setText("Test Connection Failed");
                e.printStackTrace();
            } catch (Exception e) {
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
            }catch (Exception e){
                e.printStackTrace();
            }

        });
    }

}
