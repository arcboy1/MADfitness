package com.example.madfitness.Tables;

import com.example.madfitness.DAO.WorkoutDAO;
import com.example.madfitness.Database.DBConst;
import com.example.madfitness.Database.DatabaseConnection;
import com.example.madfitness.POJO.MuscleGroup;
import com.example.madfitness.POJO.Workout;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;

public class WorkoutTable implements WorkoutDAO {
    private static  WorkoutTable instance;

    DatabaseConnection db;

    private WorkoutTable() {
        db = DatabaseConnection.getInstance();
    }

    ArrayList<Workout> workouts;
    @Override
    public ArrayList<Workout> getAllWorkouts() {
        String query = "SELECT * FROM " + DBConst.TABLE_WORKOUT;
        workouts = new ArrayList<>();
        try {
            Statement getWorkouts = db.getConnection().createStatement();
            ResultSet data = getWorkouts.executeQuery(query);
            while (data.next()) {
                workouts.add(new Workout(
                        data.getInt(DBConst.WORKOUT_COLUMN_ID),
                        data.getDate(DBConst.WORKOUT_COLUMN_DATE)
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return workouts;
    }

    @Override
    public Workout getWorkout(int id) {
        String query = "SELECT * FROM " + DBConst.TABLE_WORKOUT +
                " WHERE " + DBConst.WORKOUT_COLUMN_ID + " = " + id;
        try {
            Statement getWorkout = db.getConnection().createStatement();
            ResultSet data = getWorkout.executeQuery(query);
            if (data.next()) {
                return new Workout(
                        data.getInt(DBConst.WORKOUT_COLUMN_ID),
                        data.getDate(DBConst.WORKOUT_COLUMN_DATE)
                );
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static WorkoutTable getInstance() {
        if (instance == null) {
            instance = new WorkoutTable();
        }
        return instance;
    }
}
