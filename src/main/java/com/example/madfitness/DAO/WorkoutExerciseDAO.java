package com.example.madfitness.DAO;


import com.example.madfitness.Database.DatabaseConnection;
import com.example.madfitness.POJO.WorkoutExercise;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class WorkoutExerciseDAO {
   DatabaseConnection db =DatabaseConnection.getInstance();

    public ArrayList<WorkoutExercise> getExercisesForWorkout(String workoutId) {
        ArrayList<WorkoutExercise> exercises = new ArrayList<>();
        String query = """
           SELECT
               exercise.exercise_name AS exercise_name,
               workout_exercise.sets AS sets,
               workout_exercise.reps AS reps,
               workout_exercise.weight AS weight,
               workout_exercise.duration AS duration,
               exercise_type.exercise_type_name AS type,
               muscle_group.muscle_group_name AS muscle
               FROM workout_exercise
               JOIN exercise ON workout_exercise.exercise_id = exercise.exercise_id
               JOIN exercise_type ON exercise.exercise_type = exercise_type.exercise_type_id
               JOIN muscle_group ON exercise.muscle_group_id = muscle_group.muscle_group_id
               WHERE workout_exercise.workout_id = ?
            """;

        try (PreparedStatement preparedStatement = db.getConnection().prepareStatement(query)) {
            preparedStatement.setString(1, workoutId);
            ResultSet data = preparedStatement.executeQuery();
            while (data.next()) {
                WorkoutExercise exercise = new WorkoutExercise();
                exercise.setWorkoutExerciseName(data.getString("exercise_name"));
                exercise.setWorkoutExerciseSets(data.getInt("sets"));
                exercise.setWorkoutExerciseReps(data.getInt("reps"));
                exercise.setWorkoutExerciseWeight(data.getDouble("weight"));
                exercise.setWorkoutExerciseDuration(data.getInt("duration"));
                exercise.setWorkoutExerciseType(data.getString("type"));
                exercise.setWorkoutExerciseMuscle(data.getString("muscle"));

                exercises.add(exercise);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return exercises;
    }

}
