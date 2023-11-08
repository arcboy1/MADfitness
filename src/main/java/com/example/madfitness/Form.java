package com.example.madfitness;

import com.example.madfitness.Tabs.AddItemTab;
import com.example.madfitness.Tabs.RemoveItemTab;
import com.example.madfitness.Tabs.StatisticsTab;
import com.example.madfitness.Tabs.UpdateItemTab;
import com.example.madfitness.form.Settings;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.TabPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

import java.io.IOException;

public class Form extends Application {

    @Override
    public void start(Stage stage) throws IOException {
        BorderPane root = new BorderPane();
        // Tab Pane that will hold the tabs
        TabPane tabPane = new TabPane();
        // Creating the tabs
        AddItemTab addItemTab = new AddItemTab();
        addItemTab.setClosable(false);
        RemoveItemTab removeItemTab = new RemoveItemTab();
        removeItemTab.setClosable(false);
        UpdateItemTab updateItemTab = new UpdateItemTab();
        updateItemTab.setClosable(false);
        StatisticsTab statsTab = new StatisticsTab();
        statsTab.setClosable(false);
        // Adding all the tabs to the TabPane
        tabPane.getTabs().addAll(addItemTab, updateItemTab, removeItemTab, statsTab);

        // Adding the menu and TabPane to the borderpane
        root.setCenter(tabPane);
        Scene scene = new Scene(root, Settings.SCREEN_WIDTH, Settings.SCREEN_HEIGHT);
        stage.setTitle("MADTracker");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
