package com.example.madfitness.Controller;

import com.example.madfitness.Database.DatabaseConnection;
import javafx.animation.*;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.effect.Glow;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.*;

public class LoginController {

    @FXML
    private Label usernameLabel;

    @FXML
    private TextField usernameField;

    @FXML
    private Label passwordLabel;

    @FXML
    private Label databaseLabel;

    @FXML
    private TextField databaseField;

    @FXML
    private Label locationLabel;

    @FXML
    private TextField locationField;

    @FXML
    private Label logoLabelTop;

    @FXML
    private Label logoLabelBottom;

    @FXML
    private Button loginButton;

    @FXML
    private Label loginResultLabel;

    @FXML
    private PasswordField passwordField;

    @FXML
    private void initialize() {
        topLogoAnimation();
        bottomLogoAnimation();
    }
    private void topLogoAnimation() {
        logoLabelTop.setTranslateX(-700);

        PauseTransition delay = new PauseTransition(Duration.seconds(4));
        TranslateTransition slideIn = new TranslateTransition(Duration.seconds(1), logoLabelTop);
        slideIn.setToX(0);
        Glow glow = new Glow(0.0);

        // Create KeyFrames for breathing effect
        KeyFrame keyFrame1 = new KeyFrame(Duration.seconds(1), new KeyValue(glow.levelProperty(), 0.0));
        KeyFrame keyFrame2 = new KeyFrame(Duration.seconds(2), new KeyValue(glow.levelProperty(), 1.0));
        KeyFrame keyFrame3 = new KeyFrame(Duration.seconds(3), new KeyValue(glow.levelProperty(), 0.0));
        KeyFrame keyFrame4 = new KeyFrame(Duration.seconds(4), new KeyValue(glow.levelProperty(), 1.0));
        KeyFrame keyFrame5 = new KeyFrame(Duration.seconds(5), new KeyValue(glow.levelProperty(), 0.0));


        // Create a Timeline for the breathing effect
        Timeline glowTimeline = new Timeline(keyFrame1, keyFrame2, keyFrame3, keyFrame4,keyFrame5);

        // Set the glow effect to the label
        logoLabelTop.setEffect(glow);

        // Play the delay first, then play the slide-in animation and breathing glow effect
        delay.setOnFinished(event -> {
            slideIn.play();
            glowTimeline.setCycleCount(Timeline.INDEFINITE); // Make the breathing effect repeat indefinitely
            glowTimeline.play();
        });
        delay.play();

    }
    private void bottomLogoAnimation() {
        logoLabelBottom.setTranslateX(700);

        PauseTransition delay = new PauseTransition(Duration.seconds(4));
        TranslateTransition slideIn = new TranslateTransition(Duration.seconds(1), logoLabelBottom);
        slideIn.setToX(0);

        // Create a Glow effect
        Glow glow = new Glow(0.0);

        // Create KeyFrames for breathing effect
        KeyFrame keyFrame1 = new KeyFrame(Duration.seconds(1), new KeyValue(glow.levelProperty(), 0.0));
        KeyFrame keyFrame2 = new KeyFrame(Duration.seconds(2), new KeyValue(glow.levelProperty(), 1.0));
        KeyFrame keyFrame3 = new KeyFrame(Duration.seconds(3), new KeyValue(glow.levelProperty(), 0.0));
        KeyFrame keyFrame4 = new KeyFrame(Duration.seconds(4), new KeyValue(glow.levelProperty(), 1.0));
        KeyFrame keyFrame5 = new KeyFrame(Duration.seconds(5), new KeyValue(glow.levelProperty(), 0.0));


        // Create a Timeline for the breathing effect
        Timeline glowTimeline = new Timeline(keyFrame1, keyFrame2, keyFrame3, keyFrame4,keyFrame5);

        // Set the glow effect to the label
        logoLabelBottom.setEffect(glow);

        // Play the delay first, then play the slide-in animation and breathing glow effect
        delay.setOnFinished(event -> {
            slideIn.play();
            glowTimeline.setCycleCount(Timeline.INDEFINITE); // Make the breathing effect repeat indefinitely
            glowTimeline.play();
        });
        delay.play();
    }



    @FXML
    private void loginClicked() {
        String username = usernameField.getText();
        String password = passwordField.getText();
        String databaseName = databaseField.getText();
        String location = locationField.getText();

        String jdbcDriver = "com.mysql.cj.jdbc.Driver";
        String connectionURL = "jdbc:mysql://" + location + "/" + databaseName + "?serverTimezone=UTC";

        // Attempt to set login information and establish the connection
        DatabaseConnection databaseConnection = DatabaseConnection.getInstance();
        databaseConnection.setLoginInfo(jdbcDriver, connectionURL, username, password, databaseName);

        // Check if the connection is successful
        if (databaseConnection.getConnection() != null) {
            openForm((Stage) loginButton.getScene().getWindow());
            loginResultLabel.setText("Login Successful");

            // Save login information to the file after successful login
            saveLoginInfo(username, password, databaseName, location);
        } else {
            loginResultLabel.setText("Connection Failed");
        }
    }

    private String[] loadLoginInfo() {
        try (BufferedReader reader = new BufferedReader(new FileReader("login_info.txt"))) {
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
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("login_info.txt"))) {
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
            // Load the FXML file
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/madfitness/form-manager.fxml"));
            Parent root = loader.load();

            // Create a scene using the loaded FXML file
            Scene formScene = new Scene(root);

            // Set the scene for the primary stage
            primaryStage.setScene(formScene);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
