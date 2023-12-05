package com.example.madfitness.POJO;

public class Exercise {
    private int id;
    private String name;
    private String description;
    private int workoutType;
    private int muscleGroup;

    public Exercise(){

    }
    public Exercise(int id, String name, String description, int workoutType, int muscleGroup) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.workoutType = workoutType;
        this.muscleGroup = muscleGroup;
    }

    public Exercise(String name, String description, int workoutType, int muscleGroup) {
        this.name = name;
        this.description = description;
        this.workoutType = workoutType;
        this.muscleGroup = muscleGroup;
    }

    public Exercise(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return name;
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

    public int getWorkoutType() {
        return workoutType;
    }

    public void setWorkoutType(int workoutType) {
        this.workoutType = workoutType;
    }

    public int getMuscleGroup() {
        return muscleGroup;
    }

    public void setMuscleGroup(int muscleGroup) {
        this.muscleGroup = muscleGroup;
    }
}
