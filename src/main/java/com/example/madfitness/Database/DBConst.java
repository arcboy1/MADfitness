package com.example.madfitness.Database;

public class DBConst {
    //database constants stored in this class

    //User Table
    public static final String TABLE_USER="user";
    public static final String USER_COLUMN_ID="user_id";
    public static final String USER_COLUMN_USERNAME="username";
    public static final String USER_COLUMN_PASSWORD="password";

    // Exercise Type Table
    public static final String TABLE_EXERCISE_TYPE = "exercise_type";
    public static final String EXERCISE_TYPE_COLUMN_ID = "exercise_type_id";
    public static final String EXERCISE_TYPE_COLUMN_NAME = "exercise_type_name";


    //Workout Table
    public static final String TABLE_WORKOUT="workout";
    public static final String WORKOUT_COLUMN_ID="workout_id";
    public static final String WORKOUT_COLUMN_USER_ID="user_id";
    public static final String WORKOUT_COLUMN_DATE="date";

    public static final String WORKOUT_COLUMN_START_TIME = "start_time";
    public static final String WORKOUT_COLUMN_END_TIME = "end_time";

    public static final String WORKOUT_COLUMN_DURATION="duration";

    //Exercise Table
    public static final String TABLE_EXERCISE="exercise";
    public static final String EXERCISE_COLUMN_ID="exercise_id";
    public static final String EXERCISE_COLUMN_NAME="exercise_name";
    public static final String EXERCISE_COLUMN_DESCRIPTION="exercise_description";
    public static final String EXERCISE_COLUMN_TYPE="exercise_type";
    public static final String EXERCISE_COLUMN_MUSCLE_GROUP_ID="muscle_group_id";

    //Muscle Group Table
    public static final String TABLE_MUSCLE_GROUP="muscle_group";
    public static final String MUSCLE_GROUP_COLUMN_ID="muscle_group_id";
    public static final String MUSCLE_GROUP_COLUMN_NAME="muscle_group_name";

    //Workout_Exercise Table
    public static final String TABLE_WORKOUT_EXERCISE="workout_exercise";
    public static final String WORKOUT_EXERCISE_COLUMN_ID="workout_exercise_id";
    public static final String WORKOUT_EXERCISE_COLUMN_WORKOUT_ID="workout_id";
    public static final String WORKOUT_EXERCISE_COLUMN_EXERCISE_ID="exercise_id";
    public static final String WORKOUT_EXERCISE_COLUMN_SETS="sets";
    public static final String WORKOUT_EXERCISE_COLUMN_REPS="reps";
    public static final String WORKOUT_EXERCISE_COLUMN_WEIGHT="weight";

    //below are the queries to create the tables in the database

    public static final String CREATE_TABLE_USER = "CREATE TABLE " + TABLE_USER + " (" +
            USER_COLUMN_ID + " int NOT NULL AUTO_INCREMENT, " +
            USER_COLUMN_USERNAME + " VARCHAR(50), " +
            USER_COLUMN_PASSWORD + " VARCHAR(50), " +
            "PRIMARY KEY (" + USER_COLUMN_ID + "));";

    public static final String CREATE_TABLE_WORKOUT = "CREATE TABLE " + TABLE_WORKOUT + " (" +
            WORKOUT_COLUMN_ID + " int NOT NULL AUTO_INCREMENT, " +
            WORKOUT_COLUMN_USER_ID + " int NOT NULL, " +
            WORKOUT_COLUMN_DATE + " DATE, " +
            WORKOUT_COLUMN_START_TIME + " DATETIME, " +
            WORKOUT_COLUMN_END_TIME + " DATETIME, " +
            "PRIMARY KEY (" + WORKOUT_COLUMN_ID + ")," +
            "FOREIGN KEY (" + WORKOUT_COLUMN_USER_ID + ") " +
            "REFERENCES " + TABLE_USER + "(" + USER_COLUMN_ID + "));";

    public static final String CREATE_TABLE_EXERCISE = "CREATE TABLE " + TABLE_EXERCISE + " (" +
            EXERCISE_COLUMN_ID + " int NOT NULL AUTO_INCREMENT, " +
            EXERCISE_COLUMN_NAME + " VARCHAR(50), " +
            EXERCISE_COLUMN_DESCRIPTION + " TEXT, " +
            EXERCISE_COLUMN_TYPE + " int, " +  // Modify to use the foreign key
            EXERCISE_COLUMN_MUSCLE_GROUP_ID + " int, " +
            "PRIMARY KEY (" + EXERCISE_COLUMN_ID + ")," +
            "FOREIGN KEY (" + EXERCISE_COLUMN_TYPE + ") " +
            "REFERENCES " + TABLE_EXERCISE_TYPE + "(" + EXERCISE_TYPE_COLUMN_ID + ")," +
            "FOREIGN KEY (" + EXERCISE_COLUMN_MUSCLE_GROUP_ID + ") " +
            "REFERENCES " + TABLE_MUSCLE_GROUP + "(" + MUSCLE_GROUP_COLUMN_ID + "));";


    public static final String CREATE_TABLE_MUSCLE_GROUP = "CREATE TABLE " + TABLE_MUSCLE_GROUP + " (" +
            MUSCLE_GROUP_COLUMN_ID + " int NOT NULL AUTO_INCREMENT, " +
            MUSCLE_GROUP_COLUMN_NAME + " VARCHAR(50), " +
            "PRIMARY KEY (" + MUSCLE_GROUP_COLUMN_ID + "));";

    public static final String INSERT_MUSCLE_GROUPS = "INSERT INTO " + TABLE_MUSCLE_GROUP +
            " (" + MUSCLE_GROUP_COLUMN_NAME + ") VALUES " +
            "('Chest'), " +
            "('Back'), " +
            "('Legs'), " +
            "('Arms'), " +
            "('Shoulders'), " +
            "('Abdominals'), " +
            "('Glutes'), " +
            "('Quadriceps'), " +
            "('Hamstrings'), " +
            "('Calves'), " +
            "('Triceps'), " +
            "('Biceps'), " +
            "('None');";

    public static final String INSERT_EXERCISE_TYPES = "INSERT INTO " + TABLE_EXERCISE_TYPE +
            " (" + EXERCISE_TYPE_COLUMN_NAME + ") VALUES " +
            "('Cardio'), " +
            "('Strength Training'), " +
            "('Flexibility'), " +
            "('Balance'), " +
            "('HIIT'), " +
            "('Yoga'), " +
            "('Pilates'), " +
            "('CrossFit'), " +
            "('HyperTrophy'), " +
            "('Endurance'), " +
            "('Powerlifting');";


    public static final String CREATE_TABLE_EXERCISE_TYPE = "CREATE TABLE " + TABLE_EXERCISE_TYPE + " (" +
            EXERCISE_TYPE_COLUMN_ID + " int NOT NULL AUTO_INCREMENT, " +
            EXERCISE_TYPE_COLUMN_NAME + " VARCHAR(50), " +
            "PRIMARY KEY (" + EXERCISE_TYPE_COLUMN_ID + "));";



    public static final String CREATE_TABLE_WORKOUT_EXERCISE = "CREATE TABLE " + TABLE_WORKOUT_EXERCISE + " (" +
            WORKOUT_EXERCISE_COLUMN_ID + " int NOT NULL AUTO_INCREMENT, " +
            WORKOUT_EXERCISE_COLUMN_WORKOUT_ID + " int NOT NULL, " +
            WORKOUT_EXERCISE_COLUMN_EXERCISE_ID + " int NOT NULL, " +
            WORKOUT_EXERCISE_COLUMN_SETS + " int, " +
            WORKOUT_EXERCISE_COLUMN_REPS + " int, " +
            WORKOUT_EXERCISE_COLUMN_WEIGHT + " int, " +
            "PRIMARY KEY (" + WORKOUT_EXERCISE_COLUMN_ID + ")," +
            "FOREIGN KEY (" + WORKOUT_EXERCISE_COLUMN_WORKOUT_ID + ") " +
            "REFERENCES " + TABLE_WORKOUT + "(" + WORKOUT_COLUMN_ID + ")," +
            "FOREIGN KEY (" + WORKOUT_EXERCISE_COLUMN_EXERCISE_ID + ") " +
            "REFERENCES " + TABLE_EXERCISE + "(" + EXERCISE_COLUMN_ID + "));";
}
