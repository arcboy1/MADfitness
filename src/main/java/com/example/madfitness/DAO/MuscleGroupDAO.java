package com.example.madfitness.DAO;

import com.example.madfitness.POJO.MuscleGroup;

import java.util.ArrayList;

public interface MuscleGroupDAO {
    public ArrayList<MuscleGroup> getAllMuscleGroups();
    public MuscleGroup getMuscleGroup(int id);
}
