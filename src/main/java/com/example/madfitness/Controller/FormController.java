package com.example.madfitness.Controller;

import com.example.madfitness.Database.AddWorkoutManager;
import com.example.madfitness.Database.DBConst;
import com.example.madfitness.Database.DatabaseConnection;
import com.example.madfitness.POJO.Exercise;
import com.example.madfitness.POJO.ExerciseRecord;
import com.example.madfitness.POJO.ExerciseType;
import com.example.madfitness.POJO.MuscleGroup;
import com.example.madfitness.Tables.ExerciseTable;
import com.example.madfitness.Tables.ExerciseTypeTable;
import com.example.madfitness.Tables.MuscleGroupTable;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.effect.Glow;
import javafx.util.Duration;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class FormController {

    //Creating all the tables
    ExerciseTable exerciseTable = ExerciseTable.getInstance();
    ExerciseTypeTable exerciseTypeTable = ExerciseTypeTable.getInstance();
    MuscleGroupTable muscleGroupTable = MuscleGroupTable.getInstance();


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
    private ComboBox<MuscleGroup> muscleGroupCombo;

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

    //ExerciseType
    @FXML
    private ComboBox<ExerciseType> workoutTypeCombo;

    //ExerciseType
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
        populateWorkoutPageComboBox();
        exerciseColumn.setCellValueFactory(new PropertyValueFactory<>("exerciseName"));
        setsColumn.setCellValueFactory(new PropertyValueFactory<>("sets"));
        repsColumn.setCellValueFactory(new PropertyValueFactory<>("reps"));
        weightColumn.setCellValueFactory(new PropertyValueFactory<>("weight"));
        timeColumn.setCellValueFactory(new PropertyValueFactory<>("time"));
        populateExercisePageCombo();
        refreshTable();
        setDisplayDescriptionLabel();
    }
    @FXML
    void addExerciseClicked(ActionEvent event) {
        Exercise exercise = new Exercise(
                exerciseNameField.getText(),
                exerciseDescriptionField.getText(),
                workoutTypeCombo.getSelectionModel().getSelectedItem().getId(),
                muscleGroupCombo.getSelectionModel().getSelectedItem().getId()
        );
        exerciseTable.createExercise(exercise);
        System.out.println("AddExercise Clicked");
        refreshTable();

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
        try {
            int workoutId = AddWorkoutManager.insertNewWorkout();
            for (ExerciseRecord exerciseRecord : exerciseRecords) {
                String exerciseName = exerciseRecord.getExerciseName();

                int exerciseId = AddWorkoutManager.getExerciseIdFromDatabase(exerciseName);

                AddWorkoutManager.insertWorkoutExercise(workoutId, exerciseId, exerciseRecord);
            }
            exerciseRecords.clear();
            exerciseTableView.setItems(exerciseRecords);


            System.out.println("Workout finished and data saved to the database!");
        } catch (SQLException e) {
            e.printStackTrace();
        }
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
                ResultSet resultSet = statement.executeQuery("SELECT * FROM " + DBConst.TABLE_EXERCISE);

                while (resultSet.next()) {
                    exerciseNames.add(new Exercise(
                            resultSet.getInt(DBConst.EXERCISE_COLUMN_ID),
                            resultSet.getString(DBConst.EXERCISE_COLUMN_NAME),
                            resultSet.getString(DBConst.EXERCISE_COLUMN_DESCRIPTION),
                            resultSet.getInt(DBConst.EXERCISE_COLUMN_TYPE),
                            resultSet.getInt(DBConst.EXERCISE_COLUMN_MUSCLE_GROUP_ID)

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

    public void setDisplayDescriptionLabel(){
        exerciseListView.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Exercise>() {
            @Override
            public void changed(ObservableValue<? extends Exercise> observable, Exercise oldValue, Exercise newValue) {
                if(newValue!=null){
                    Exercise exercise=ExerciseTable.getInstance().getExercise(((Exercise) newValue).getId());
                    displayDescriptionLabel.setText("Description: "+exercise.getDescription());
                }
            }
        });

    }
    public void refreshTable(){
        exerciseListView.getItems().clear();
        populateExerciseListView();
    }

    //ADD EXERCISE NAMES TO EXERCISE COMBO BOX
    private void populateWorkoutPageComboBox() {
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

    public void populateExercisePageCombo(){
        workoutTypeCombo.setItems(FXCollections.observableArrayList(exerciseTypeTable.getAllExerciseTypes()));
        workoutTypeCombo.getSelectionModel().select(0);

        muscleGroupCombo.setItems(FXCollections.observableArrayList(muscleGroupTable.getAllMuscleGroups()));
        muscleGroupCombo.getSelectionModel().select(0);
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
