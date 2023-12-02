package com.example.madfitness.Controller;

import com.example.madfitness.Database.DBConst;
import com.example.madfitness.Database.DatabaseConnection;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.Tab;
import javafx.scene.control.TextField;
import javafx.scene.effect.Glow;
import javafx.util.Duration;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class FormController {

    @FXML
    private Button addExerciseButton;

    @FXML
    private ComboBox<String> addExerciseCombo;

    @FXML
    private Label addExerciseLabel;

    @FXML
    private Button addToWorkoutButton;

    @FXML
    private Tab addWorkoutTab;

    @FXML
    private Label displayDescriptionLabel;

    @FXML
    private TextField exerciseDescriptionField;

    @FXML
    private Label exerciseDescriptionLabel;

    @FXML
    private ListView<?> exerciseListView;

    @FXML
    private TextField exerciseNameField;

    @FXML
    private Label exerciseNameLabel;

    @FXML
    private Tab exerciseTab;

    @FXML
    private Button finishWorkoutButton;

    @FXML
    private Label logoLabelBottom;

    @FXML
    private Label logoLabelTop;

    @FXML
    private ComboBox<?> muscleGroupCombo;

    @FXML
    private Label muscleGroupLabel;

    @FXML
    private Tab progressTab;

    @FXML
    private TextField repsField;

    @FXML
    private Label repsLabel;

    @FXML
    private TextField setsField;

    @FXML
    private TextField setsField1;

    @FXML
    private Label setsLabel;

    @FXML
    private Label setsLabel1;

    @FXML
    private Tab viewWorkoutTab;

    @FXML
    private TextField weightField;

    @FXML
    private Label weightLabel;

    @FXML
    private ComboBox<String> workoutTypeCombo;

    @FXML
    private Label workoutTypeLabel;

    @FXML
    void addExerciseClicked(ActionEvent event) {

    }

    @FXML
    void addToWorkoutClicked(ActionEvent event) {

    }

    @FXML
    void finishWorkoutCllicked(ActionEvent event) {

    }


    @FXML
    private void initialize() {
        topLogoAnimation();
        bottomLogoAnimation();
        populateExerciseComboBox();
    }
    private void populateExerciseComboBox() {
        try {
            // Assuming you have a DatabaseConnection instance
            DatabaseConnection databaseConnection = DatabaseConnection.getInstance();

            // Execute a query to retrieve exercise names
            String query = "SELECT exercise_name FROM " + DBConst.TABLE_EXERCISE;
            Statement statement = databaseConnection.getConnection().createStatement();
            ResultSet resultSet = statement.executeQuery(query);

            // Populate the ComboBox with the retrieved exercise names
            List<String> exerciseNames = new ArrayList<>();
            while (resultSet.next()) {
                String exerciseName = resultSet.getString("exercise_name");
                exerciseNames.add(exerciseName);
            }

            addExerciseCombo.getItems().addAll(exerciseNames);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    private void topLogoAnimation() {

        // Create a Glow effect
        Glow glow = new Glow(0.0);

        // Create KeyFrames for breathing effect
        KeyFrame keyFrame1 = new KeyFrame(Duration.seconds(1), new KeyValue(glow.levelProperty(), 0.0));
        KeyFrame keyFrame2 = new KeyFrame(Duration.seconds(2), new KeyValue(glow.levelProperty(), 1.0));
        KeyFrame keyFrame3 = new KeyFrame(Duration.seconds(3), new KeyValue(glow.levelProperty(), 0.0));
        KeyFrame keyFrame4 = new KeyFrame(Duration.seconds(4), new KeyValue(glow.levelProperty(), 1.0));
        KeyFrame keyFrame5 = new KeyFrame(Duration.seconds(5), new KeyValue(glow.levelProperty(), 0.0));

        // Create a Timeline for the breathing effect
        Timeline glowTimeline = new Timeline(keyFrame1, keyFrame2, keyFrame3, keyFrame4, keyFrame5);

        // Set the glow effect to the label
        logoLabelTop.setEffect(glow);

        // Play the breathing glow effect
        glowTimeline.setCycleCount(Timeline.INDEFINITE); // Make the breathing effect repeat indefinitely
        glowTimeline.play();
    }

    private void bottomLogoAnimation() {

        // Create a Glow effect
        Glow glow = new Glow(0.0);

        // Create KeyFrames for breathing effect
        KeyFrame keyFrame1 = new KeyFrame(Duration.seconds(1), new KeyValue(glow.levelProperty(), 0.0));
        KeyFrame keyFrame2 = new KeyFrame(Duration.seconds(2), new KeyValue(glow.levelProperty(), 1.0));
        KeyFrame keyFrame3 = new KeyFrame(Duration.seconds(3), new KeyValue(glow.levelProperty(), 0.0));
        KeyFrame keyFrame4 = new KeyFrame(Duration.seconds(4), new KeyValue(glow.levelProperty(), 1.0));
        KeyFrame keyFrame5 = new KeyFrame(Duration.seconds(5), new KeyValue(glow.levelProperty(), 0.0));

        // Create a Timeline for the breathing effect
        Timeline glowTimeline = new Timeline(keyFrame1, keyFrame2, keyFrame3, keyFrame4, keyFrame5);

        // Set the glow effect to the label
        logoLabelBottom.setEffect(glow);

        // Play the breathing glow effect
        glowTimeline.setCycleCount(Timeline.INDEFINITE); // Make the breathing effect repeat indefinitely
        glowTimeline.play();
    }


}
