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
                    DatabaseConnection databaseConnection = DatabaseConnection.getInstance();
                    databaseConnection.setLoginInfo(jdbcDriver, connectionURL, username, password, databaseName);

                    if (databaseConnection.getConnection() != null) {
                        openForm(primaryStage);
                    }
                } catch (Exception e) {
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
    /*
    SELECT
    exercise.exercise_name AS exercise_name,
    workout_exercise.sets AS sets,
    workout_exercise.reps AS reps,
    workout_exercise.weight AS weight,
    workout_exercise.duration AS duration,
    exercise_type.exercise_type_name AS type,
    muscle_group.muscle_group_name AS muscle
    FROM workout_exercise
    JOIN exercise ON workout_exercise.exercise_id = exercise.exercise_id
    JOIN exercise_type ON exercise.exercise_type = exercise_type.exercise_type_id
    JOIN muscle_group ON exercise.muscle_group_id = muscle_group.muscle_group_id
    */


    public static void main(String[] args) {
        launch(args);
    }
}
