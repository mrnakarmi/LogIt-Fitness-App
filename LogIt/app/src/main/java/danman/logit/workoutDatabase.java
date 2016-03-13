package danman.logit;

import android.provider.BaseColumns;

/**
 * Created by dkimm on 3/12/2016.
 */
public final class workoutDatabase {

    public workoutDatabase(){}

    public static abstract class gymWorkout implements BaseColumns {
        public static final String TABLE_NAME = "workout";
        public static final String COLUMN_NAME_CATEGORY = "category";
        public static final String COLUMN_NAME_SETS = "sets";
        public static final String COLUMN_NAME_REPS = "reps";
        public static final String COLUMN_NAME_WEIGHT = "weight";
        public static final String COLUMN_NAME_NAME = "name";
        public static final String COLUMN_NAME_TIME = "time";
        public static final String COLUMN_NAME_UNIQUE_WORKOUT = "uniqueWorkout";

    }
}
