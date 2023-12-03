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
    public Exercise getExercise(int id) {
        String query = "SELECT * FROM " + DBConst.TABLE_EXERCISE + " WHERE " +
                DBConst.EXERCISE_COLUMN_ID + " =" + id;
        Exercise exercise = new Exercise();
        try {
            Statement getExercise = db.getConnection().createStatement();
            ResultSet data = getExercise.executeQuery(query);
            data.next();
            exercise = new Exercise(
                    data.getInt(DBConst.EXERCISE_COLUMN_ID),
                    data.getString(DBConst.EXERCISE_COLUMN_NAME),
                    data.getString(DBConst.EXERCISE_COLUMN_DESCRIPTION),
                    data.getInt(DBConst.EXERCISE_COLUMN_TYPE),
                    data.getInt(DBConst.EXERCISE_COLUMN_MUSCLE_GROUP_ID)
            );
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return exercise;
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

    public ArrayList<DisplayExercise> getDisplayExercises() {
        ArrayList<DisplayExercise> exercises = new ArrayList<>();
        String query = """
                SELECT
                 exercise.id, 
                 exercise.name AS exercise_name,
                 exercise.description,
                 exercise_type.name AS exercise_type_name,
                 muscle_group.name AS muscle_group_name
                FROM exercise
                JOIN exercise_type ON exercise.exercise_type = exercise_type.id
                JOIN muscle_group ON exercise.muscle_group_id = muscle_group.id
                ORDER BY exercise.id ASC
                """;
        try {
            Statement getDisplayExercises = db.getConnection().createStatement();
            ResultSet data = getDisplayExercises.executeQuery(query);
            while (data.next()) {
                exercises.add(new DisplayExercise(
                        data.getInt("id"),
                        data.getString("exercise_name"),
                        data.getString("description"),
                        data.getString("exercise_type_name"),
                        data.getString("muscle_group_name")
                ));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return exercises;
    }

    public int getExerciseCount(int exercise) {
        int count = -1;
        try {
            PreparedStatement getCount = db.getConnection()
                    .prepareStatement("SELECT * FROM " + DBConst.TABLE_EXERCISE +
                                    " WHERE " + DBConst.EXERCISE_COLUMN_NAME
                                    + " = '" + exercise + "'", ResultSet.TYPE_SCROLL_SENSITIVE,
                            ResultSet.CONCUR_UPDATABLE);
            ResultSet data = getCount.executeQuery();
            data.last();
            count = data.getRow();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return count;
    }

    public static ExerciseTable getInstance() {
        if (instance == null) {
            instance = new ExerciseTable();
        }
        return instance;
    }
}
