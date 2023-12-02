package com.example.madfitness.GUI;

import com.example.madfitness.Database.DatabaseConnection;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.sql.SQLException;

public class Login extends Application {

    private static final String LOGIN_INFO_FILE = "login_info.txt";

    @Override
    public void start(Stage primaryStage) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/madfitness/hello-view.fxml"));
            primaryStage.setScene(new Scene(loader.load()));
            primaryStage.setTitle("Database Login");
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
        } catch (IOException e) {
            e.printStackTrace();
        }
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

    private void openForm(Stage primaryStage) {
        try {
            // Load the FXML file
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/madfitness/form-manager.fxml"));
            Parent root = loader.load();

            // Create a scene using the loaded FXML file
            Scene formScene = new Scene(root);

            // Set the scene for the primary stage
            primaryStage.setScene(formScene);
            primaryStage.setTitle("MADFitness");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}
