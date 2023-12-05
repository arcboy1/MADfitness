package com.example.madfitness.Tables;

import com.example.madfitness.Database.DBConst;
import com.example.madfitness.Database.DatabaseConnection;
import com.example.madfitness.POJO.WorkoutExercise2;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class WorkoutExerciseTable2 {
    private static  WorkoutExerciseTable2 instance;

    DatabaseConnection db;

    private WorkoutExerciseTable2() {
        db = DatabaseConnection.getInstance();
    }

    ArrayList<WorkoutExercise2> workoutExercises;

    public ArrayList<WorkoutExercise2> getAllWorkoutExercises() {
        String query = "SELECT * FROM " + DBConst.TABLE_WORKOUT_EXERCISE;
        workoutExercises = new ArrayList<>();
        try {
            Statement getWorkoutExercises = db.getConnection().createStatement();
            ResultSet data = getWorkoutExercises.executeQuery(query);
            while (data.next()) {
                workoutExercises.add(new WorkoutExercise2(
                        data.getInt(DBConst.WORKOUT_EXERCISE_COLUMN_ID),
                        data.getInt(DBConst.WORKOUT_EXERCISE_COLUMN_WORKOUT_ID),
                        data.getInt(DBConst.WORKOUT_EXERCISE_COLUMN_EXERCISE_ID),
                        data.getInt(DBConst.WORKOUT_EXERCISE_COLUMN_SETS),
                        data.getInt(DBConst.WORKOUT_EXERCISE_COLUMN_REPS),
                        data.getInt(DBConst.WORKOUT_EXERCISE_COLUMN_WEIGHT),
                        data.getInt(DBConst.WORKOUT_EXERCISE_COLUMN_DURATION)
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return workoutExercises;
    }

    public static WorkoutExerciseTable2 getInstance() {
        if (instance == null) {
            instance = new WorkoutExerciseTable2();
        }
        return instance;
    }
}
