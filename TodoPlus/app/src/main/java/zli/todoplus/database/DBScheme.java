package zli.todoplus.database;

import android.provider.BaseColumns;

/**
 * Created by yvokeller on 31.08.17.
 */

public final class DBScheme {
    // To prevent someone from accidentally instantiating the contract class,
    // make the constructor private.
    private DBScheme() {}

    /* Inner class that defines the table contents */
    public static class DateTodo implements BaseColumns {
        public static final String TABLE_NAME = "DateTodo";
        public static final String COLUMN_NAME_TITLE = "title";
        public static final String COLUMN_NAME_CREATION_DATE = "creationdate";
        public static final String COLUMN_NAME_REMINDER_DATE = "reminderdate";
        public static final String COLUMN_NAME_STATE = "state";
        public static final String COLUMN_NAME_PRIORITY = "priority";
    }

    /* Inner class that defines the table contents */
    public static class SportTodo implements BaseColumns {
        public static final String TABLE_NAME = "SportTodo";
        public static final String COLUMN_NAME_TITLE = "title";
        public static final String COLUMN_NAME_CREATION_DATE = "creationdate";
        public static final String COLUMN_NAME_STATE = "state";
        public static final String COLUMN_NAME_PRIORITY = "priority";
        public static final String COLUMN_NAME_STEP_GOAL = "stepgoal";
        public static final String COLUMN_NAME_STEPS_DONE = "stepsdone";
        public static final String COLUMN_NAME_TIME_USED = "timeused";
    }
}
