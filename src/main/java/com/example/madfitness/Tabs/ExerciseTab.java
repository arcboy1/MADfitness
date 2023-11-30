package com.example.madfitness.Tabs;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;

public class ExerciseTab extends Tab {

    public ExerciseTab() {
        this.setText("Add Exercise");

        // Create UI components for adding exercise
        Label nameLabel = new Label("Exercise Name:");
        TextField nameTextField = new TextField();

        Label descriptionLabel = new Label("Description:");
        TextField descriptionTextField = new TextField();

        Label typeLabel = new Label("Exercise Type:");
        TextField typeTextField = new TextField();

        Label muscleGroupLabel = new Label("Muscle Group:");
        ComboBox<String> muscleGroupComboBox = createMuscleGroupComboBox();

        Button addButton = new Button("Add Exercise");

        // Create layout for adding exercise
        GridPane addExerciseGrid = new GridPane();
        addExerciseGrid.setVgap(10);
        addExerciseGrid.setHgap(10);
        addExerciseGrid.setPadding(new Insets(20, 20, 20, 20));

        addExerciseGrid.add(nameLabel, 0, 0);
        addExerciseGrid.add(nameTextField, 1, 0);
        addExerciseGrid.add(descriptionLabel, 0, 1);
        addExerciseGrid.add(descriptionTextField, 1, 1);
        addExerciseGrid.add(typeLabel, 0, 2);
        addExerciseGrid.add(typeTextField, 1, 2);
        addExerciseGrid.add(muscleGroupLabel, 0, 3);
        addExerciseGrid.add(muscleGroupComboBox, 1, 3);
        addExerciseGrid.add(addButton, 0, 4, 2, 1);

        ListView<String> exerciseListView = new ListView<>();
        exerciseListView.setItems(getSampleExerciseList()); // Replace this with your actual logic to fetch exercise data

        VBox listVBox = new VBox(exerciseListView);
        listVBox.setPadding(new Insets(10));
        VBox.setVgrow(exerciseListView, Priority.ALWAYS);

        SplitPane splitPane = new SplitPane();
        splitPane.getItems().addAll(addExerciseGrid, listVBox);
        splitPane.setDividerPositions(0.5);

        this.setContent(splitPane);

        addButton.setOnAction(event -> {
            String exerciseName = nameTextField.getText();
            String description = descriptionTextField.getText();
            String exerciseType = typeTextField.getText();
            String selectedMuscleGroup = muscleGroupComboBox.getValue();

            // TODO: Add logic to insert data into the exercise table

            System.out.println("Name: " + exerciseName);
            System.out.println("Description: " + description);
            System.out.println("Type: " + exerciseType);
            System.out.println("Muscle Group: " + selectedMuscleGroup);

            // Refresh the list view with updated data (replace this with your actual logic to fetch exercise data)
            exerciseListView.setItems(getSampleExerciseList());
        });
    }

    private ComboBox<String> createMuscleGroupComboBox() {
        // TODO replace with logic to get musclegroupid from database
        ObservableList<String> muscleGroups = FXCollections.observableArrayList(
                "Chest", "Back", "Legs", "Arms", "Shoulders", "Abs"
        );

        ComboBox<String> comboBox = new ComboBox<>(muscleGroups);
        comboBox.setPromptText("Select Muscle Group");
        return comboBox;
    }

    private ObservableList<String> getSampleExerciseList() {

        //TODO add logic to get exercise list from database
        return FXCollections.observableArrayList(
                "Exercise 1", "Exercise 2", "Exercise 3"
        );
    }
}
