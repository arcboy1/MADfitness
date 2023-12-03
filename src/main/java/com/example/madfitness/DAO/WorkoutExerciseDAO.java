package com.example.madfitness.DAO;


import com.example.madfitness.POJO.WorkoutExercise;

import java.util.ArrayList;

public interface WorkoutExerciseDAO {
    public void createWorkoutExercise(WorkoutExercise workoutExercise);
    ArrayList<WorkoutExercise> getAllWorkoutExercises();
    WorkoutExercise getWorkoutExercise(int workoutExerciseId);
    public void updateWorkoutExercise(WorkoutExercise workoutExercise);
    public void deleteWorkoutExercise(int id);
}
