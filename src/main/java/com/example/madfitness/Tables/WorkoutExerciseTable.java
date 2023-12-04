package com.example.madfitness.Tables;

import com.example.madfitness.DAO.WorkoutExerciseDAO;
import com.example.madfitness.Database.DBConst;
import com.example.madfitness.Database.DatabaseConnection;
import com.example.madfitness.POJO.Workout;
import com.example.madfitness.POJO.WorkoutExercise;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class WorkoutExerciseTable implements WorkoutExerciseDAO {
    private static  WorkoutExerciseTable instance;

    DatabaseConnection db;

    private WorkoutExerciseTable() {
        db = DatabaseConnection.getInstance();
    }

    ArrayList<WorkoutExercise> workoutExercises;

    @Override
    public void createWorkoutExercise(WorkoutExercise workoutExercise) {
        String query = "INSERT INTO " + DBConst.TABLE_WORKOUT_EXERCISE +
                "(" + DBConst.WORKOUT_EXERCISE_COLUMN_WORKOUT_ID + ", " +
                DBConst.WORKOUT_EXERCISE_COLUMN_EXERCISE_ID + ", " +
                DBConst.WORKOUT_EXERCISE_COLUMN_SETS + ", " +
                DBConst.WORKOUT_EXERCISE_COLUMN_REPS + ", " +
                DBConst.WORKOUT_EXERCISE_COLUMN_WEIGHT + ", " +
                DBConst.WORKOUT_EXERCISE_COLUMN_DURATION + ") VALUES (" +
                workoutExercise.getWorkoutId() + "," +
                workoutExercise.getExerciseId() + "," +
                workoutExercise.getSets() + "," +
                workoutExercise.getReps() + "," +
                workoutExercise.getWeight() + "," +
                workoutExercise.getDuration() + ")";
        try {
            db.getConnection().createStatement().execute(query);
            System.out.println("Inserted Record");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public ArrayList<WorkoutExercise> getAllWorkoutExercises() {
        String query = "SELECT * FROM " + DBConst.TABLE_WORKOUT_EXERCISE;
        workoutExercises = new ArrayList<>();
        try {
            Statement getWorkoutExercises = db.getConnection().createStatement();
            ResultSet data = getWorkoutExercises.executeQuery(query);
            while (data.next()) {
                workoutExercises.add(new WorkoutExercise(
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

    @Override
    public WorkoutExercise getWorkoutExercise(int workoutExerciseId) {
        String query = "SELECT * FROM " + DBConst.TABLE_WORKOUT_EXERCISE +
                " WHERE " + DBConst.WORKOUT_EXERCISE_COLUMN_ID + " = " + workoutExerciseId;
        try {
            Statement getWorkoutExercise = db.getConnection().createStatement();
            ResultSet data = getWorkoutExercise.executeQuery(query);
            if (data.next()) {
                return new WorkoutExercise(
                        data.getInt(DBConst.WORKOUT_EXERCISE_COLUMN_ID),
                        data.getInt(DBConst.WORKOUT_EXERCISE_COLUMN_WORKOUT_ID),
                        data.getInt(DBConst.WORKOUT_EXERCISE_COLUMN_EXERCISE_ID),
                        data.getInt(DBConst.WORKOUT_EXERCISE_COLUMN_SETS),
                        data.getInt(DBConst.WORKOUT_EXERCISE_COLUMN_REPS),
                        data.getInt(DBConst.WORKOUT_EXERCISE_COLUMN_WEIGHT),
                        data.getInt(DBConst.WORKOUT_EXERCISE_COLUMN_DURATION)
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void deleteWorkoutExercisesForWorkout(int workoutId) {
        String query = "DELETE FROM " + DBConst.TABLE_WORKOUT_EXERCISE +
                " WHERE " + DBConst.WORKOUT_EXERCISE_COLUMN_WORKOUT_ID + " = " + workoutId;
        try {
            db.getConnection().createStatement().execute(query);
            System.out.println("Deleted Workout Exercises for Workout ID: " + workoutId);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public ArrayList<WorkoutExercise> getWorkoutExercisesForWorkout(int workoutId) {
        String query = "SELECT * FROM " + DBConst.TABLE_WORKOUT_EXERCISE +
                " WHERE " + DBConst.WORKOUT_EXERCISE_COLUMN_WORKOUT_ID + " = " + workoutId;
        ArrayList<WorkoutExercise> workoutExercises = new ArrayList<>();
        try {
            Statement getWorkoutExercises = db.getConnection().createStatement();
            ResultSet data = getWorkoutExercises.executeQuery(query);
            while (data.next()) {
                workoutExercises.add(new WorkoutExercise(
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

    @Override
    public void updateWorkoutExercise(WorkoutExercise workoutExercise) {
        String query = "UPDATE " + DBConst.TABLE_WORKOUT_EXERCISE + " SET " +
                DBConst.WORKOUT_EXERCISE_COLUMN_WORKOUT_ID + "= " + workoutExercise.getWorkoutId() + ", " +
                DBConst.WORKOUT_EXERCISE_COLUMN_EXERCISE_ID + "= " + workoutExercise.getExerciseId() + ", " +
                DBConst.WORKOUT_EXERCISE_COLUMN_SETS + "= " + workoutExercise.getSets() + ", " +
                DBConst.WORKOUT_EXERCISE_COLUMN_REPS + "= " + workoutExercise.getReps() + ", " +
                DBConst.WORKOUT_EXERCISE_COLUMN_WEIGHT + "= " + workoutExercise.getWeight() + ", " +
                DBConst.WORKOUT_EXERCISE_COLUMN_DURATION + "= " + workoutExercise.getDuration() +
                " WHERE " + DBConst.WORKOUT_EXERCISE_COLUMN_ID + " = " + workoutExercise.getId();
        try {
            Statement updateWorkoutExercise = db.getConnection().createStatement();
            updateWorkoutExercise.executeUpdate(query);
            System.out.println("Record Updated");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void deleteWorkoutExercise(int id) {
        String query = "DELETE FROM " + DBConst.TABLE_WORKOUT_EXERCISE +
                " WHERE " + DBConst.WORKOUT_EXERCISE_COLUMN_ID + " = " + id;
        try {
            db.getConnection().createStatement().execute(query);
            System.out.println("Deleted Record");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static WorkoutExerciseTable getInstance() {
        if (instance == null) {
            instance = new WorkoutExerciseTable();
        }
        return instance;
    }
}
