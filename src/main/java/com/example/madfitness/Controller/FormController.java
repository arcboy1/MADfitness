package com.example.madfitness.Controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.Tab;
import javafx.scene.control.TextField;

public class FormController {

    @FXML
    private Button addExerciseButton;

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
    private Tab viewWorkoutTab;

    @FXML
    private ComboBox<?> workoutTypeCombo;

    @FXML
    private Label workoutTypeLabel;

    @FXML
    void addExerciseCllicked(ActionEvent event) {

    }

}
