package com.example.madfitness.Controller;

import com.example.madfitness.Database.DBConst;
import com.example.madfitness.Database.DatabaseConnection;
import com.example.madfitness.POJO.DisplayExercise;
import com.example.madfitness.POJO.Exercise;
import com.example.madfitness.POJO.ExerciseRecord;
import com.example.madfitness.Tables.ExerciseTable;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
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
    private ComboBox<Exercise> addExerciseCombo;

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


    //PROPERTIES BELOW ARE FOR THE NEW WORKOUT PAGE TABLEVIEW
    @FXML
    private TableView<ExerciseRecord> exerciseTableView;
    @FXML
    private TableColumn<ExerciseRecord, String> exerciseColumn;

    @FXML
    private TableColumn<ExerciseRecord, Integer> setsColumn;

    @FXML
    private TableColumn<ExerciseRecord, Integer> repsColumn;

    @FXML
    private TableColumn<ExerciseRecord, Integer> weightColumn;

    @FXML
    private TableColumn<ExerciseRecord, Integer> timeColumn;

    private ObservableList<ExerciseRecord> exerciseRecords = FXCollections.observableArrayList();

//STOP HERE FOR NEW WORKOUT PAGE TABLEVIEW

    @FXML
    private void initialize() {
        topLogoAnimation();
        bottomLogoAnimation();
        populateExerciseListView();
        populateExerciseComboBox();
        exerciseColumn.setCellValueFactory(new PropertyValueFactory<>("exerciseName"));
        setsColumn.setCellValueFactory(new PropertyValueFactory<>("sets"));
        repsColumn.setCellValueFactory(new PropertyValueFactory<>("reps"));
        weightColumn.setCellValueFactory(new PropertyValueFactory<>("weight"));
        timeColumn.setCellValueFactory(new PropertyValueFactory<>("time"));
    }
    @FXML
    void addExerciseClicked(ActionEvent event) {

    }

    @FXML
    void addToWorkoutClicked(ActionEvent event) {
        Exercise selectedExercise = addExerciseCombo.getValue();
        String exerciseName = selectedExercise.getName();
        int sets = Integer.parseInt(setsField.getText());
        int reps = Integer.parseInt(repsField.getText());
        int weight = Integer.parseInt(weightField.getText());
        int time = Integer.parseInt(timeField.getText());

        ExerciseRecord exerciseRecord = new ExerciseRecord(exerciseName, sets, reps, weight, time);
        exerciseRecords.add(exerciseRecord);
        exerciseTableView.setItems(exerciseRecords);

        // Clear input fields
        clearInputFields();

    }
    private void clearInputFields() {
        addExerciseCombo.setValue(null);
        setsField.clear();
        repsField.clear();
        weightField.clear();
        timeField.clear();
    }

    @FXML
    void finishWorkoutClicked(ActionEvent event) {

    }
    @FXML
    void deleteWorkoutClicked(ActionEvent event) {

    }
    @FXML
    void viewWorkoutClicked(ActionEvent event) {

    }




    //METHOD TO ADD EXERCISE NAMES TO LIST VIEW
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

    //ADD EXERCISE NAMES TO EXERCISE COMBO BOX
    private void populateExerciseComboBox() {
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

            // Populate the ComboBox with exercise names
            addExerciseCombo.getItems().addAll(exerciseNames);
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
