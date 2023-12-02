package com.example.madfitness.GUI;

import com.example.madfitness.Database.DBConst;
import com.example.madfitness.Database.DatabaseConnection;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class UserLoginPane extends Pane {

    public UserLoginPane() {
        Label usernameLabel = new Label("Username:");
        TextField usernameField = new TextField();

        Label passwordLabel = new Label("Password:");
        PasswordField passwordField = new PasswordField();

        Button newUserButton = new Button("New User");
        Button loginButton = new Button("Login");

        VBox vbox = new VBox(10);
        HBox buttonBox = new HBox(10);

        buttonBox.getChildren().addAll(newUserButton, loginButton);
        vbox.getChildren().addAll(usernameLabel, usernameField, passwordLabel, passwordField, buttonBox);

        getChildren().add(vbox);



    }


    }


