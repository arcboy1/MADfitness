package com.example.madfitness.DAO;

import com.example.madfitness.POJO.Exercise;

import java.util.ArrayList;

public interface ExerciseDAO {
    public void createExercise(Exercise exercise);
    ArrayList<Exercise> getAllExercises();
    Exercise getExercise(int exerciseId);
    public void updateExercise(Exercise exercise);
    public void deleteExercise(int id);
}
