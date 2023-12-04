package com.example.madfitness.POJO;

public class WorkoutExercise {
    private String workoutExerciseName;
    private int workoutExerciseSets;
    private int workoutExerciseReps;
    private double workoutExerciseWeight;
    private int workoutExerciseDuration;
    private String workoutExerciseType;
    private String workoutExerciseMuscle;
    public WorkoutExercise() {
        // Empty constructor
    }

    public String getWorkoutExerciseName() {
        return workoutExerciseName;
    }

    public WorkoutExercise setWorkoutExerciseName(String workoutExerciseName) {
        this.workoutExerciseName = workoutExerciseName;
        return this;
    }

    public int getWorkoutExerciseSets() {
        return workoutExerciseSets;
    }

    public WorkoutExercise setWorkoutExerciseSets(int workoutExerciseSets) {
        this.workoutExerciseSets = workoutExerciseSets;
        return this;
    }

    public int getWorkoutExerciseReps() {
        return workoutExerciseReps;
    }

    public WorkoutExercise setWorkoutExerciseReps(int workoutExerciseReps) {
        this.workoutExerciseReps = workoutExerciseReps;
        return this;
    }

    public double getWorkoutExerciseWeight() {
        return workoutExerciseWeight;
    }

    public WorkoutExercise setWorkoutExerciseWeight(double workoutExerciseWeight) {
        this.workoutExerciseWeight = workoutExerciseWeight;
        return this;
    }

    public int getWorkoutExerciseDuration() {
        return workoutExerciseDuration;
    }

    public WorkoutExercise setWorkoutExerciseDuration(int workoutExerciseDuration) {
        this.workoutExerciseDuration = workoutExerciseDuration;
        return this;
    }

    public String getWorkoutExerciseType() {
        return workoutExerciseType;
    }

    public WorkoutExercise setWorkoutExerciseType(String workoutExerciseType) {
        this.workoutExerciseType = workoutExerciseType;
        return this;
    }

    public String getWorkoutExerciseMuscle() {
        return workoutExerciseMuscle;
    }

    public WorkoutExercise setWorkoutExerciseMuscle(String workoutExerciseMuscle) {
        this.workoutExerciseMuscle = workoutExerciseMuscle;
        return this;
    }

    public WorkoutExercise(String workoutExerciseName, int workoutExerciseSets, int workoutExerciseReps, double workoutExerciseWeight, int workoutExerciseDuration, String workoutExerciseType, String workoutExerciseMuscle) {
        this.workoutExerciseName = workoutExerciseName;
        this.workoutExerciseSets = workoutExerciseSets;
        this.workoutExerciseReps = workoutExerciseReps;
        this.workoutExerciseWeight = workoutExerciseWeight;
        this.workoutExerciseDuration = workoutExerciseDuration;
        this.workoutExerciseType = workoutExerciseType;
        this.workoutExerciseMuscle = workoutExerciseMuscle;
    }

    @Override
    public String toString() {
        return "WorkoutExercise{" +
                "workoutExerciseName='" + workoutExerciseName + '\'' +
                ", workoutExerciseSets=" + workoutExerciseSets +
                ", workoutExerciseReps=" + workoutExerciseReps +
                ", workoutExerciseWeight=" + workoutExerciseWeight +
                ", workoutExerciseDuration=" + workoutExerciseDuration +
                ", workoutExerciseType='" + workoutExerciseType + '\'' +
                ", workoutExerciseMuscle='" + workoutExerciseMuscle + '\'' +
                '}';
    }
}