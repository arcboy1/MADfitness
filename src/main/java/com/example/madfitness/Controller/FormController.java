package com.example.madfitness.Controller;

import com.example.madfitness.Database.DBConst;
import com.example.madfitness.Database.DatabaseConnection;
import com.example.madfitness.POJO.DisplayExercise;
import com.example.madfitness.POJO.Exercise;
import com.example.madfitness.Tables.ExerciseTable;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
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

import java.sql.Connection;
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
    private ListView<Exercise> exerciseListView;

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
    private TextField timeField;

    @FXML
    private Label setsLabel;
    @FXML
    private Button deleteWorkoutButton;

    @FXML
    private TextField deleteWorkoutField;

    @FXML
    private Label deleteWorkoutLabel;
    @FXML
    private Button viewWorkoutDetailsButton;

    @FXML
    private Label timeLabel;

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
    void deleteWorkoutClicked(ActionEvent event) {

    }
    @FXML
    void viewWorkoutClicked(ActionEvent event) {

    }


    @FXML
    private void initialize() {
        topLogoAnimation();
        bottomLogoAnimation();
        populateExerciseListView();
    }
    private void populateExerciseListView() {

        try {
            DatabaseConnection databaseConnection = DatabaseConnection.getInstance();

            // Assuming you have a method in your DatabaseConnection class to retrieve exercise names
            List<Exercise> exerciseNames = new ArrayList<>();

            try (Statement statement = databaseConnection.getConnection().createStatement()) {
                ResultSet resultSet = statement.executeQuery("SELECT " + DBConst.EXERCISE_COLUMN_NAME + " FROM " + DBConst.TABLE_EXERCISE);

                while (resultSet.next()) {
                    exerciseNames.add(new Exercise(
                            resultSet.getString(DBConst.EXERCISE_COLUMN_NAME)
                    ));
                }
            }

            // Populate the exerciseListView with exercise names
            exerciseListView.getItems().addAll(exerciseNames);
        } catch (SQLException e) {
            e.printStackTrace();
            // Handle the exception as needed
        }
    }
    private void topLogoAnimation() {

        // Create a Glow effect
        Glow glow = new Glow(0.0);

        // Create KeyFrames for breathing effect
        KeyFrame keyFrame1 = new KeyFrame(Duration.seconds(1), new KeyValue(glow.levelProperty(), 0.0));
        KeyFrame keyFrame2 = new KeyFrame(Duration.seconds(2), new KeyValue(glow.levelProperty(), 1.5));
        KeyFrame keyFrame3 = new KeyFrame(Duration.seconds(3), new KeyValue(glow.levelProperty(), 0.0));
        KeyFrame keyFrame4 = new KeyFrame(Duration.seconds(4), new KeyValue(glow.levelProperty(), 1.5));
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
        KeyFrame keyFrame2 = new KeyFrame(Duration.seconds(2), new KeyValue(glow.levelProperty(), 1.5));
        KeyFrame keyFrame3 = new KeyFrame(Duration.seconds(3), new KeyValue(glow.levelProperty(), 0.0));
        KeyFrame keyFrame4 = new KeyFrame(Duration.seconds(4), new KeyValue(glow.levelProperty(), 1.5));
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
