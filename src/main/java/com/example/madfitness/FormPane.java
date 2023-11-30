package com.example.madfitness;

import com.example.madfitness.Tabs.ExerciseTab;
import com.example.madfitness.Tabs.StatisticsTab;
import com.example.madfitness.Tabs.UpdateWorkoutTab;
import com.example.madfitness.Tabs.ViewWorkoutTab;
import com.example.madfitness.form.Settings;
import javafx.scene.control.TabPane;
import javafx.scene.layout.BorderPane;

public class FormPane extends BorderPane {

    public FormPane() {
        // Tab Pane that will hold the tabs
        TabPane tabPane = new TabPane();
        // Creating the tabs
        ViewWorkoutTab addDeleteWorkoutsTab = new ViewWorkoutTab();
        addDeleteWorkoutsTab.setText("View Workouts");
        addDeleteWorkoutsTab.setClosable(false);

        UpdateWorkoutTab viewWorkoutsTab = new UpdateWorkoutTab();
        viewWorkoutsTab.setText("Add/Delete Workouts");
        viewWorkoutsTab.setClosable(false);

        ExerciseTab exercisesTab = new ExerciseTab();
        exercisesTab.setText("Exercises");
        exercisesTab.setClosable(false);

        StatisticsTab statsTab = new StatisticsTab();
        statsTab.setText("Stats");
        statsTab.setClosable(false);

        // Adding all the tabs to the TabPane
        tabPane.getTabs().addAll(addDeleteWorkoutsTab, viewWorkoutsTab, exercisesTab, statsTab);

        // Adding the TabPane to the border pane
        this.setCenter(tabPane);
    }
}
