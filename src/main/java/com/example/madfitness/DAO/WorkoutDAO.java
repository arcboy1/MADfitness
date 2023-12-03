package com.example.madfitness.DAO;

import com.example.madfitness.POJO.MuscleGroup;
import com.example.madfitness.POJO.Workout;

import java.util.ArrayList;

public interface WorkoutDAO {
    public ArrayList<Workout> getAllWorkouts();
    public Workout getWorkout(int id);
}
