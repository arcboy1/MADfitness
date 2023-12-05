package com.example.madfitness.Tables;

import com.example.madfitness.DAO.ExerciseTypeDAO;
import com.example.madfitness.Database.DBConst;
import com.example.madfitness.Database.DatabaseConnection;
import com.example.madfitness.POJO.ExerciseType;

import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

public class ExerciseTypeTable implements ExerciseTypeDAO {
    private static ExerciseTypeTable instance;
    DatabaseConnection db;

    private ExerciseTypeTable() {
        db = DatabaseConnection.getInstance();
    }
    ArrayList<ExerciseType> workoutTypes;
    @Override
    public ArrayList<ExerciseType> getAllExerciseTypes() {
        String query = "SELECT * FROM " + DBConst.TABLE_EXERCISE_TYPE;
        workoutTypes = new ArrayList<>();
        try {
            Statement getWorkoutTypes = db.getConnection().createStatement();
            ResultSet data = getWorkoutTypes.executeQuery(query);

            while (data.next()) {
                workoutTypes.add(new ExerciseType(
                        data.getInt(DBConst.EXERCISE_TYPE_COLUMN_ID),
                        data.getString(DBConst.EXERCISE_TYPE_COLUMN_NAME)
                ));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return workoutTypes;
    }

    @Override
    public ExerciseType getExerciseType(int id) {
        String query = "SELECT * FROM " + DBConst.TABLE_EXERCISE_TYPE +
                " WHERE " + DBConst.EXERCISE_TYPE_COLUMN_ID + " = " + id;
        try {
            Statement getWorkoutType = db.getConnection().createStatement();
            ResultSet data = getWorkoutType.executeQuery(query);
            if (data.next()) {
                return new ExerciseType(
                        data.getInt(DBConst.EXERCISE_TYPE_COLUMN_ID),
                        data.getString(DBConst.EXERCISE_TYPE_COLUMN_NAME)
                );
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static ExerciseTypeTable getInstance() {
        if (instance == null) {
            instance = new ExerciseTypeTable();
        }
        return instance;
    }

}
