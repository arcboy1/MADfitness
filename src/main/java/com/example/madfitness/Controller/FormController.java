package com.example.madfitness.Controller;

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


    @FXML
    private void initialize() {
        topLogoAnimation();
        bottomLogoAnimation();
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
