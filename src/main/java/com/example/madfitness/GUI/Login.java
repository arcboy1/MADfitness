package com.example.madfitness.GUI;

import java.io.*;
import java.sql.SQLException;

import com.example.madfitness.Database.DatabaseConnection;
import com.example.madfitness.scenes.FormScene;
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

public class Login extends Application {

    private static final String LOGIN_INFO_FILE = "login_info.txt";

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Database Login");

        Label usernameLabel = new Label("Username:");
        TextField usernameField = new TextField("ntaggart");

        Label passwordLabel = new Label("Password:");
        PasswordField passwordField = new PasswordField();

        Label databaseLabel = new Label("Database Name:");
        TextField databaseField = new TextField("ntaggartmd");

        Label locationLabel = new Label("Database Location:");
        TextField locationField = new TextField("localhost");

        Button loginButton = new Button("Login");

        Text loginResultText = new Text("");

        VBox vbox = new VBox(10);
        HBox hbox = new HBox(10);
        hbox.getChildren().addAll(loginResultText);
        vbox.getChildren().addAll(usernameLabel, usernameField, passwordLabel, passwordField, databaseLabel, databaseField, locationLabel, locationField, hbox, loginButton);

        Scene scene = new Scene(vbox, 350, 350);

        primaryStage.setScene(scene);
        primaryStage.show();

        // Attempt automatic login using saved information
        String[] savedLoginInfo = loadLoginInfo();
        if (savedLoginInfo != null) {
            try {
                String username = savedLoginInfo[0];
                String password = savedLoginInfo[1];
                String databaseName = savedLoginInfo[2];
                String location = savedLoginInfo[3];

                String jdbcDriver = "com.mysql.cj.jdbc.Driver";
                String connectionURL = "jdbc:mysql://" + location + "/" + databaseName + "?serverTimezone=UTC";

                // Attempt to log in automatically
                if (DatabaseConnection.testConnection(jdbcDriver, connectionURL, username, password)) {
                    DatabaseConnection databaseConnection = new DatabaseConnection(jdbcDriver, connectionURL, username, password)
                            .setDatabaseName(databaseName);
                    openForm(primaryStage);
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        loginButton.setOnAction(event -> {
            String username = usernameField.getText();
            String password = passwordField.getText();
            String databaseName = databaseField.getText();
            String location = locationField.getText();

            String jdbcDriver = "com.mysql.cj.jdbc.Driver";
            String connectionURL = "jdbc:mysql://" + location + "/" + databaseName + "?serverTimezone=UTC";

            try {
                // Attempt login
                if (DatabaseConnection.testConnection(jdbcDriver, connectionURL, username, password)) {
                    DatabaseConnection databaseConnection = new DatabaseConnection(jdbcDriver, connectionURL, username, password)
                            .setDatabaseName(databaseName);
                    openForm(primaryStage);

                    // Save login information to the file after successful login
                    saveLoginInfo(username, password, databaseName, location);
                } else {
                    loginResultText.setText("Login Failed");
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    private String[] loadLoginInfo() {
        try (BufferedReader reader = new BufferedReader(new FileReader(LOGIN_INFO_FILE))) {
            String username = reader.readLine();
            String password = reader.readLine();
            String databaseName = reader.readLine();
            String location = reader.readLine();

            // Return loaded login information as an array
            return new String[]{username, password, databaseName, location};

        } catch (IOException e) {
            // Handle file not found or other IO exceptions
            return null;
        }
    }

    private void saveLoginInfo(String username, String password, String databaseName, String location) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(LOGIN_INFO_FILE))) {
            // Write login information to the file
            writer.write(username);
            writer.newLine();
            writer.write(password);
            writer.newLine();
            writer.write(databaseName);
            writer.newLine();
            writer.write(location);

        } catch (IOException e) {
            // Handle IO exceptions
            e.printStackTrace();
        }
    }

    private void openForm(Stage primaryStage) {
        try {
            primaryStage.setScene(new FormScene());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}
