package com.example.madfitness.Database;

import com.example.madfitness.POJO.ExerciseRecord;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class AddWorkoutManager {
    public static int insertNewWorkout() throws SQLException {
        DatabaseConnection databaseConnection = DatabaseConnection.getInstance();
        String insertWorkoutQuery = "INSERT INTO " + DBConst.TABLE_WORKOUT + " (" + DBConst.WORKOUT_COLUMN_DATE + ") VALUES (CURRENT_DATE())";

        try (PreparedStatement preparedStatement = databaseConnection.getConnection().prepareStatement(insertWorkoutQuery, PreparedStatement.RETURN_GENERATED_KEYS)) {
            preparedStatement.executeUpdate();
            ResultSet generatedKeys = preparedStatement.getGeneratedKeys();

            if (generatedKeys.next()) {
                return generatedKeys.getInt(1); // Return the generated workout ID
            } else {
                throw new SQLException("Failed to insert workout, no ID obtained.");
            }
        }
    }

    public static void insertWorkoutExercise(int workoutId, int exerciseId, ExerciseRecord exerciseRecord) throws SQLException {
        DatabaseConnection databaseConnection = DatabaseConnection.getInstance();
        String insertWorkoutExerciseQuery = "INSERT INTO " + DBConst.TABLE_WORKOUT_EXERCISE +
                " (" + DBConst.WORKOUT_EXERCISE_COLUMN_WORKOUT_ID + ", " + DBConst.WORKOUT_EXERCISE_COLUMN_EXERCISE_ID + ", " +
                DBConst.WORKOUT_EXERCISE_COLUMN_SETS + ", " + DBConst.WORKOUT_EXERCISE_COLUMN_REPS + ", " +
                DBConst.WORKOUT_EXERCISE_COLUMN_WEIGHT + ", " + DBConst.WORKOUT_EXERCISE_COLUMN_DURATION + ") " +
                "VALUES (?, ?, ?, ?, ?, ?)";

        try (PreparedStatement preparedStatement = databaseConnection.getConnection().prepareStatement(insertWorkoutExerciseQuery)) {
            preparedStatement.setInt(1, workoutId);
            preparedStatement.setInt(2, exerciseId);
            preparedStatement.setInt(3, exerciseRecord.getSets());
            preparedStatement.setInt(4, exerciseRecord.getReps());
            preparedStatement.setInt(5, exerciseRecord.getWeight());
            preparedStatement.setInt(6, exerciseRecord.getTime());

            preparedStatement.executeUpdate();
        }
    }

    public static int getExerciseIdFromDatabase(String exerciseName) throws SQLException {
        DatabaseConnection databaseConnection = DatabaseConnection.getInstance();
        String selectExerciseIdQuery = "SELECT " + DBConst.EXERCISE_COLUMN_ID + " FROM " + DBConst.TABLE_EXERCISE +
                " WHERE " + DBConst.EXERCISE_COLUMN_NAME + " = ?";

        try (PreparedStatement preparedStatement = databaseConnection.getConnection().prepareStatement(selectExerciseIdQuery)) {
            preparedStatement.setString(1, exerciseName);
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                return resultSet.getInt(DBConst.EXERCISE_COLUMN_ID); // Return the exercise ID
            } else {
                throw new SQLException("Exercise not found in the database.");
            }
        }
    }
}
