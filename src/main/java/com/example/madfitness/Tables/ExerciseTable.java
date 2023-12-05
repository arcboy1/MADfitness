package com.example.madfitness.Tables;

import com.example.madfitness.DAO.ExerciseDAO;
import com.example.madfitness.Database.DBConst;
import com.example.madfitness.Database.DatabaseConnection;
import com.example.madfitness.POJO.DisplayExercise;
import com.example.madfitness.POJO.Exercise;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class ExerciseTable implements ExerciseDAO {
    private static ExerciseTable instance;
    DatabaseConnection db;

    private ExerciseTable() {
        db = DatabaseConnection.getInstance();
    }

    ArrayList<Exercise> exercises;

    @Override
    public void createExercise(Exercise exercise) {
        String query = "INSERT INTO " + DBConst.TABLE_EXERCISE +
                "(" + DBConst.EXERCISE_COLUMN_NAME + ", " +
                DBConst.EXERCISE_COLUMN_DESCRIPTION + ", " +
                DBConst.EXERCISE_COLUMN_TYPE + ", " +
                DBConst.EXERCISE_COLUMN_MUSCLE_GROUP_ID + ") VALUES ('" +
                exercise.getName() + "','" +
                exercise.getDescription() + "','" +
                exercise.getWorkoutType() + "','" +
                exercise.getMuscleGroup() + "')";
        try {
            db.getConnection().createStatement().execute(query);
            System.out.println("Inserted Record yesss");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public ArrayList<Exercise> getAllExercises() {
        String query = "SELECT * FROM " + DBConst.TABLE_EXERCISE;
        exercises = new ArrayList<>();
        try {
            Statement getExercises = db.getConnection().createStatement();
            ResultSet data = getExercises.executeQuery(query);
            while (data.next()) {
                exercises.add(new Exercise(
                        data.getString(DBConst.EXERCISE_COLUMN_NAME),
                        data.getString(DBConst.EXERCISE_COLUMN_DESCRIPTION),
                        data.getInt(DBConst.EXERCISE_COLUMN_TYPE),
                        data.getInt(DBConst.EXERCISE_COLUMN_MUSCLE_GROUP_ID)
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return exercises;
    }

    @Override
    public Exercise getExercise(int exerciseId) {
        String query = "SELECT * FROM " + DBConst.TABLE_EXERCISE + " WHERE " + DBConst.EXERCISE_COLUMN_ID + " = ?";
        try (PreparedStatement statement = db.getConnection().prepareStatement(query)) {
            statement.setInt(1, exerciseId);
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                return new Exercise(
                        resultSet.getInt(DBConst.EXERCISE_COLUMN_ID),
                        resultSet.getString(DBConst.EXERCISE_COLUMN_NAME),
                        resultSet.getString(DBConst.EXERCISE_COLUMN_DESCRIPTION),
                        resultSet.getInt(DBConst.EXERCISE_COLUMN_TYPE),
                        resultSet.getInt(DBConst.EXERCISE_COLUMN_MUSCLE_GROUP_ID)
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    @Override
    public void updateExercise(Exercise exercise) {
        String query = "UPDATE " + DBConst.TABLE_EXERCISE + " SET " +
                DBConst.EXERCISE_COLUMN_NAME + "= '" + exercise.getName() + "', " +
                DBConst.EXERCISE_COLUMN_DESCRIPTION + "= '" + exercise.getDescription() + "', " +
                DBConst.EXERCISE_COLUMN_TYPE + "= " + exercise.getWorkoutType() + ", " +
                DBConst.EXERCISE_COLUMN_MUSCLE_GROUP_ID + "= " + exercise.getId() +
                " WHERE " + DBConst.EXERCISE_COLUMN_ID + " = " + exercise.getId();
        try {
            Statement updateExercise = db.getConnection().createStatement();
            updateExercise.executeUpdate(query);
            System.out.println("Record Updated");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void deleteExercise(int id) {
        String query = "DELETE FROM " + DBConst.TABLE_EXERCISE + " WHERE " +
                DBConst.EXERCISE_COLUMN_ID + " = " + id;
        try {
            db.getConnection().createStatement().execute(query);
            System.out.println("Deleted Record");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


    public static ExerciseTable getInstance() {
        if (instance == null) {
            instance = new ExerciseTable();
        }
        return instance;
    }
}
