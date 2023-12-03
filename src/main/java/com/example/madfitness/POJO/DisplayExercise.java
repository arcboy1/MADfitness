package com.example.madfitness.POJO;

public class DisplayExercise {
    private int id;
    private String name;
    private String description;
    private String  workoutType;
    private String muscleGroup;

    public DisplayExercise(int id, String name, String description, String workoutType, String muscleGroup) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.workoutType = workoutType;
        this.muscleGroup = muscleGroup;
    }

    @Override
    public String toString() {
        return this.name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getWorkoutType() {
        return workoutType;
    }

    public void setWorkoutType(String workoutType) {
        this.workoutType = workoutType;
    }

    public String getMuscleGroup() {
        return muscleGroup;
    }

    public void setMuscleGroup(String muscleGroup) {
        this.muscleGroup = muscleGroup;
    }
}
