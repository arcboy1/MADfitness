package com.example.madfitness.Database;

public class DBConst {
    //database constants stored in this class


    // Exercise Type Table
    public static final String TABLE_EXERCISE_TYPE = "exercise_type";
    public static final String EXERCISE_TYPE_COLUMN_ID = "exercise_type_id";
    public static final String EXERCISE_TYPE_COLUMN_NAME = "exercise_type_name";


    //Workout Table
    public static final String TABLE_WORKOUT="workout";
    public static final String WORKOUT_COLUMN_ID="workout_id";
    public static final String WORKOUT_COLUMN_DATE="date";


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
    public static final String WORKOUT_EXERCISE_COLUMN_DURATION="duration"; // Added duration column


    //below are the queries to create the tables in the database
    public static final String CREATE_TABLE_WORKOUT = "CREATE TABLE " + TABLE_WORKOUT + " (" +
            WORKOUT_COLUMN_ID + " int NOT NULL AUTO_INCREMENT, " +
            WORKOUT_COLUMN_DATE + " DATE, " +
            "PRIMARY KEY (" + WORKOUT_COLUMN_ID + "));";

    public static final String CREATE_TABLE_EXERCISE = "CREATE TABLE " + TABLE_EXERCISE + " (" +
            EXERCISE_COLUMN_ID + " int NOT NULL AUTO_INCREMENT, " +
            EXERCISE_COLUMN_NAME + " VARCHAR(50), " +
            EXERCISE_COLUMN_DESCRIPTION + " VARCHAR(200), " +
            EXERCISE_COLUMN_TYPE + " int, " +
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
            WORKOUT_EXERCISE_COLUMN_DURATION + " int, " +
            "PRIMARY KEY (" + WORKOUT_EXERCISE_COLUMN_ID + ")," +
            "FOREIGN KEY (" + WORKOUT_EXERCISE_COLUMN_WORKOUT_ID + ") " +
            "REFERENCES " + TABLE_WORKOUT + "(" + WORKOUT_COLUMN_ID + ")," +
            "FOREIGN KEY (" + WORKOUT_EXERCISE_COLUMN_EXERCISE_ID + ") " +
            "REFERENCES " + TABLE_EXERCISE + "(" + EXERCISE_COLUMN_ID + "));";

    // INSERT STATEMENTS
    public static final String INSERT_COMMON_EXERCISES = "INSERT INTO " + TABLE_EXERCISE +
            " (" + EXERCISE_COLUMN_NAME + ", " + EXERCISE_COLUMN_DESCRIPTION + ", " +
            EXERCISE_COLUMN_TYPE + ", " + EXERCISE_COLUMN_MUSCLE_GROUP_ID + ") VALUES " +
            "('Push up', 'Bodyweight exercise targeting chest, shoulders, and triceps.', 2, 1), " +
            "('Pull up', 'Upper body strength exercise focusing on back and biceps.', 2, 2), " +
            "('Squats', 'Compound movement working on lower body muscles.', 2, 3), " +
            "('Bicep Curl', 'Isolation exercise for the biceps.', 2, 5), " +
            "('Plank', 'Core stability exercise targeting abdominals and back.', 4, 6), " +
            "('Lunges', 'Leg exercise targeting quadriceps and hamstrings.', 2, 3), " +
            "('Deadlift', 'Full-body compound movement emphasizing back and legs.', 2, 2), " +
            "('Crunches', 'Abdominal exercise for targeting the rectus abdominis.', 4, 6), " +
            "('Shoulder Press', 'Strengthens shoulder muscles, especially deltoids.', 2, 5), " +
            "('Tricep Dip', 'Isolation exercise for the triceps.', 2, 5), " +
            "('Leg Press', 'Machine exercise for building leg strength.', 2, 3), " +
            "('Calf Raises', 'Targeting the muscles in the calf area.', 2, 10), " +
            "('Russian Twist', 'Core exercise involving twisting motion for obliques.', 4, 6), " +
            "('Lat Pulldown', 'Targets the latissimus dorsi muscles in the back.', 2, 2), " +
            "('Hammer Curl', 'Variation of bicep curl targeting brachialis and brachioradialis.', 2, 5), " +
            "('Front Squat', 'Similar to squats but emphasizes the front of the leg.', 2, 3), " +
            "('Hanging Leg Raise', 'Advanced abdominal exercise hanging from a bar.', 4, 6), " +
            "('Arnold Press', 'Shoulder exercise involving a twisting motion during the press.', 2, 5), " +
            "('Dumbbell Flyes', 'Isolation exercise for chest muscles using dumbbells.', 2, 1), " +
            "('Box Jumps', 'Explosive leg exercise involving jumping onto a raised platform.', 5, 3), " +
            "('Running', 'Cardiovascular exercise for overall endurance.', 1, 11), " +
            "('Jump Rope', 'Great for cardio and coordination.', 1, 11), " +
            "('Mountain Climbers', 'Full-body exercise with a focus on cardio and core strength.', 5, 6);";

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
}

