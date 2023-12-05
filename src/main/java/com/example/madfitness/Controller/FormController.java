package com.example.madfitness.Controller;

import com.example.madfitness.DAO.WorkoutExerciseDAO;
import com.example.madfitness.Database.AddWorkoutManager;
import com.example.madfitness.Database.DBConst;
import com.example.madfitness.Database.DatabaseConnection;
import com.example.madfitness.POJO.*;
import com.example.madfitness.Tables.*;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.chart.PieChart;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.effect.Glow;
import javafx.util.Duration;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.*;

public class FormController {



    //Creating all the tables
    ExerciseTable exerciseTable = ExerciseTable.getInstance();
    ExerciseTypeTable exerciseTypeTable = ExerciseTypeTable.getInstance();
    MuscleGroupTable muscleGroupTable = MuscleGroupTable.getInstance();
    WorkoutTable workoutTable = WorkoutTable.getInstance();
    WorkoutExerciseTable2 workoutExerciseTable2 = WorkoutExerciseTable2.getInstance();

    //Charts
    public void generateExercisesChart() {
        try {
            ArrayList<WorkoutExercise2> allWorkoutExercises = workoutExerciseTable2.getAllWorkoutExercises();

            // Print the size of allWorkoutExercises for debugging
            System.out.println("Number of Workout Exercises: " + allWorkoutExercises.size());

            // Create a map to store exercise counts
            Map<String, Integer> exerciseCounts = new HashMap<>();

            // Count occurrences of each exercise
            for (WorkoutExercise2 workoutExercise : allWorkoutExercises) {
                String exerciseName = getExerciseNameById(workoutExercise.getExerciseId());

                // Increment count or initialize to 1
                exerciseCounts.put(exerciseName, exerciseCounts.getOrDefault(exerciseName, 0) + 1);

                // Print exercise details for debugging
                System.out.println("Exercise ID: " + workoutExercise.getExerciseId() + ", Exercise Name: " + exerciseName);
            }

            // Create data for the pie chart
            ObservableList<PieChart.Data> pieChartData = FXCollections.observableArrayList();

            for (Map.Entry<String, Integer> entry : exerciseCounts.entrySet()) {
                pieChartData.add(new PieChart.Data(entry.getKey(), entry.getValue()));
            }

            // Print the size of pieChartData for debugging
            System.out.println("Pie Chart Data Size: " + pieChartData.size());

            // Set the data to the pie chart
            statsExercisePieChart.setData(pieChartData);

            // Enable legend
            statsExercisePieChart.setLegendVisible(true);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private String getExerciseNameById(int exerciseId) {
        // Assuming you have a method in your ExerciseTable class to retrieve exercise name by ID
        Exercise exercise = exerciseTable.getExercise(exerciseId);
        return (exercise != null) ? exercise.getName() : "";
    }




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
    private PieChart statsExercisePieChart;

    @FXML
    private Label statsLabel;

    @FXML
    private Button statsRefreshButton;

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

//PROPERTIES BELOW ARE FOR THE MANAGE WORKOUTS PAGE

    //WORKOUT TABLEVIEW

    @FXML
    private TableView<Workout> workoutTableView;

    @FXML
    private TableColumn<Workout, Integer> workoutIdColumn;

    @FXML
    private TableColumn<Workout, Date> workoutDateColumn;


    //WORKOUT_EXERCISE TABLEVIEW

    @FXML
    private TableView<WorkoutExercise> workoutExerciseTableView;

    @FXML
    private TableColumn<WorkoutExercise, ?> workoutExerciseNameColumn;

    @FXML
    private TableColumn<WorkoutExercise, ?> workoutExerciseSetsColumn;

    @FXML
    private TableColumn<WorkoutExercise, ?> workoutExerciseRepsColumn;

    @FXML
    private TableColumn<WorkoutExercise, ?> workoutExerciseWeightColumn;

    @FXML
    private TableColumn<WorkoutExercise, ?> workoutExerciseTypeColumn;

    @FXML
    private TableColumn<WorkoutExercise, ?> workoutExerciseMuscleGroupColumn;

    @FXML
    private TableColumn<WorkoutExercise, ?> workoutExerciseDurationColumn;




//STOP HERE FOR THE MANAGE WORKOUTS PAGE



    @FXML
    private void initialize() {
        topLogoAnimation();
        bottomLogoAnimation();
        populateExerciseListView();
        populateWorkoutTableView();
        populateWorkoutPageComboBox();

        //new workout table
        exerciseColumn.setCellValueFactory(new PropertyValueFactory<>("exerciseName"));
        setsColumn.setCellValueFactory(new PropertyValueFactory<>("sets"));
        repsColumn.setCellValueFactory(new PropertyValueFactory<>("reps"));
        weightColumn.setCellValueFactory(new PropertyValueFactory<>("weight"));
        timeColumn.setCellValueFactory(new PropertyValueFactory<>("time"));

        //Workout manager table
        workoutExerciseNameColumn.setCellValueFactory(new PropertyValueFactory<>("workoutExerciseName"));
        workoutExerciseSetsColumn.setCellValueFactory(new PropertyValueFactory<>("workoutExerciseSets"));
        workoutExerciseRepsColumn.setCellValueFactory(new PropertyValueFactory<>("workoutExerciseReps"));
        workoutExerciseWeightColumn.setCellValueFactory(new PropertyValueFactory<>("workoutExerciseWeight"));
        workoutExerciseDurationColumn.setCellValueFactory(new PropertyValueFactory<>("workoutExerciseDuration"));
        workoutExerciseTypeColumn.setCellValueFactory(new PropertyValueFactory<>("workoutExerciseType"));
        workoutExerciseMuscleGroupColumn.setCellValueFactory(new PropertyValueFactory<>("workoutExerciseMuscle"));

        populateExercisePageCombo();
        refreshTable();
        setDisplayDescriptionLabel();

        //Chart
        generateExercisesChart();
    }

    private void populateWorkoutTableView() {
        // Assuming you have a method in your WorkoutTable class to retrieve all workouts
        ArrayList<Workout> allWorkouts = WorkoutTable.getInstance().getAllWorkouts();

        // Populate the workoutTableView with data
        workoutTableView.setItems(FXCollections.observableArrayList(allWorkouts));

        // Set up the columns to display the data correctly
        workoutIdColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        workoutDateColumn.setCellValueFactory(new PropertyValueFactory<>("date"));
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
        clearInputFields();

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
        exerciseDescriptionField.clear();
        exerciseNameField.clear();
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

        //Refreshes the table
        refreshTable();
    }
    @FXML
    void deleteWorkoutClicked(ActionEvent event) {
        String workoutIdString = deleteWorkoutField.getText();
        DatabaseConnection databaseConnection = DatabaseConnection.getInstance();

        if (!workoutIdString.isEmpty()) {
            try {
                int workoutId = Integer.parseInt(workoutIdString);

                // Delete workout exercises first
                String exerciseQuery = "DELETE FROM " + DBConst.TABLE_WORKOUT_EXERCISE +
                        " WHERE " + DBConst.WORKOUT_EXERCISE_COLUMN_WORKOUT_ID + " = " + workoutId;

                try (Statement exerciseStatement = databaseConnection.getConnection().createStatement()) {
                    if (exerciseStatement.executeUpdate(exerciseQuery) > 0) {
                        System.out.println("Deleted Workout Exercises for Workout ID: " + workoutId);

                        // Now, delete the workout
                        String workoutQuery = "DELETE FROM " + DBConst.TABLE_WORKOUT +
                                " WHERE " + DBConst.WORKOUT_COLUMN_ID + " = " + workoutId;

                        try (Statement workoutStatement = databaseConnection.getConnection().createStatement()) {
                            if (workoutStatement.executeUpdate(workoutQuery) > 0) {
                                System.out.println("Workout deleted successfully!");
                                refreshTable();
                            } else {
                                System.out.println("No workout found with ID: " + workoutId);
                            }
                        }
                    } else {
                        System.out.println("No workout found with ID: " + workoutId);
                    }
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            } catch (NumberFormatException e) {
                System.err.println("Invalid workout ID format");
            }
        } else {
            System.err.println("Please enter a workout ID");
        }
        refreshTable();
    }




    @FXML
    void viewWorkoutClicked(ActionEvent event) {
        String workoutId = deleteWorkoutField.getText();

        WorkoutExerciseDAO exerciseDAO = new WorkoutExerciseDAO();


        // Assuming you have a method in your DAO to get exercises based on workoutId
        List<WorkoutExercise> exercises = exerciseDAO.getExercisesForWorkout(workoutId);

        // Update the TableView
        workoutExerciseTableView.getItems().clear();
        workoutExerciseTableView.getItems().addAll(exercises);
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
        workoutTableView.getItems().clear();
        populateWorkoutTableView();
        addExerciseCombo.getItems().clear();
        populateWorkoutPageComboBox();
        workoutExerciseTableView.getItems().clear();
        generateExercisesChart();

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

    @FXML
    void statsRefreshClicked(ActionEvent event) {

    }



}
