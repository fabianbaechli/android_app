package zli.todoplus.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by yvokeller on 31.08.17.
 */

public class TodoDBOpenHelper extends SQLiteOpenHelper {
    // If you change the database schema, you must increment the database version.
    public static final int DATABASE_VERSION = 2;
    public static final String DATABASE_NAME = "TodoPlus.db";

    public TodoDBOpenHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    public void onCreate(SQLiteDatabase db) {
        //Create Tables

        db.execSQL(SQL_CREATE_DATE_TODO);
        db.execSQL(SQL_CREATE_SPORT_TODO);
    }

    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // This database is only a cache for online data, so its upgrade policy is
        // to simply to discard the data and start over

        db.execSQL(SQL_DELETE_DATE_TODO);
        db.execSQL(SQL_DELETE_SPORT_TODO);

        onCreate(db);
    }

    public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        onUpgrade(db, oldVersion, newVersion);
    }

    //
    // Queries
    //

    //Date Todo
    private static final String SQL_CREATE_DATE_TODO =
            "CREATE TABLE " + DBScheme.DateTodo.TABLE_NAME + " (" +
                    DBScheme.DateTodo._ID + " INTEGER PRIMARY KEY," +
                    DBScheme.DateTodo.COLUMN_NAME_TITLE + " TEXT," +
                    DBScheme.DateTodo.COLUMN_NAME_CREATION_DATE + " TEXT," +
                    DBScheme.DateTodo.COLUMN_NAME_STATE + " TEXT," +
                    DBScheme.DateTodo.COLUMN_NAME_PRIORITY + " INTEGER)";

    private static final String SQL_DELETE_DATE_TODO =
            "DROP TABLE IF EXISTS " + DBScheme.DateTodo.TABLE_NAME;

    //Sport Todo
    private static final String SQL_CREATE_SPORT_TODO =
            "CREATE TABLE " + DBScheme.SportTodo.TABLE_NAME + " (" +
                    DBScheme.SportTodo._ID + " INTEGER PRIMARY KEY," +
                    DBScheme.SportTodo.COLUMN_NAME_TITLE + " TEXT," +
                    DBScheme.SportTodo.COLUMN_NAME_CREATION_DATE + " TEXT," +
                    DBScheme.SportTodo.COLUMN_NAME_STATE + " TEXT," +
                    DBScheme.SportTodo.COLUMN_NAME_PRIORITY + " INTEGER," +
                    DBScheme.SportTodo.COLUMN_NAME_STEP_GOAL + " INTEGER," +
                    DBScheme.SportTodo.COLUMN_NAME_STEPS_DONE + " INTEGER," +
                    DBScheme.SportTodo.COLUMN_NAME_TIME_USED + " INTEGER)";

    private static final String SQL_DELETE_SPORT_TODO =
            "DROP TABLE IF EXISTS " + DBScheme.SportTodo.TABLE_NAME;
}