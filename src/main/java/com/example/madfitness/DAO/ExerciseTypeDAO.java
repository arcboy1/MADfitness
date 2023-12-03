package com.example.madfitness.DAO;

import com.example.madfitness.POJO.ExerciseType;

import java.util.ArrayList;

public interface ExerciseTypeDAO {
    public ArrayList<ExerciseType> getAllExerciseTypes();
    public ExerciseType getExerciseType(int id);
}
