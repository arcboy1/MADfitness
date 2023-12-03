package com.example.madfitness.Tables;

import com.example.madfitness.DAO.MuscleGroupDAO;
import com.example.madfitness.Database.DBConst;
import com.example.madfitness.Database.DatabaseConnection;
import com.example.madfitness.POJO.MuscleGroup;

import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

public class MuscleGroupTable implements MuscleGroupDAO {
    private static  MuscleGroupTable instance;

    DatabaseConnection db;

    private MuscleGroupTable() {
        db = DatabaseConnection.getInstance();
    }

    ArrayList<MuscleGroup> muscleGroups;

    @Override
    public ArrayList<MuscleGroup> getAllMuscleGroups() {
        String query = "SELECT * FROM " + DBConst.TABLE_MUSCLE_GROUP;
        muscleGroups = new ArrayList<>();
        try {
            Statement getMuscleGroups = db.getConnection().createStatement();
            ResultSet data = getMuscleGroups.executeQuery(query);

            while (data.next()) {
                muscleGroups.add(new MuscleGroup(
                        data.getInt(DBConst.MUSCLE_GROUP_COLUMN_ID),
                        data.getString(DBConst.MUSCLE_GROUP_COLUMN_NAME)
                ));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return muscleGroups;
    }

    @Override
    public MuscleGroup getMuscleGroup(int id) {
        String query = "SELECT * FROM " + DBConst.TABLE_MUSCLE_GROUP +
                " WHERE " + DBConst.MUSCLE_GROUP_COLUMN_ID + " = " + id;
        try {
            Statement getMuscleGroup = db.getConnection().createStatement();
            ResultSet data = getMuscleGroup.executeQuery(query);
            if (data.next()) {
                return new MuscleGroup(
                        data.getInt(DBConst.MUSCLE_GROUP_COLUMN_ID),
                        data.getString(DBConst.MUSCLE_GROUP_COLUMN_NAME)
                );
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static MuscleGroupTable getInstance() {
        if (instance == null) {
            instance = new MuscleGroupTable();
        }
        return instance;
    }
}
