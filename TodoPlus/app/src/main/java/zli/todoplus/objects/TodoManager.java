package zli.todoplus.objects;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import zli.todoplus.TodoActivity;
import zli.todoplus.database.DBScheme;
import zli.todoplus.database.TodoDBOpenHelper;

/**
 * Created by yvokeller on 02.09.17.
 */

public class TodoManager {
    List<Todo> todoList = new ArrayList<>();

    Map<Integer, String> list = new LinkedHashMap<>();

    public static TodoDBOpenHelper oDbHelper;

    //Function To Add new todo. Is automatically added to database.
    public boolean addTodo(Todo todo, Context context) {
        todoList.add(todo);

        //Insert To DB
        oDbHelper = new TodoDBOpenHelper(context);

        if (todo.getType().equals("date")) {
            //Date Todo
            DateTodo newTodo = (DateTodo) todo;
            insertDateTodo(newTodo);
        } else if (todo.getType().equals("sport")) {
            //Sport Todo
            SportTodo newTodo = (SportTodo) todo;
            insertSportTodo(newTodo);
        }

        return true;
    }

    public Map returnData() {
        SQLiteDatabase db = oDbHelper.getReadableDatabase();
        int count = 0;

        // Define a projection that specifies which columns from the database
        // you will actually use after this query.
        String[] projection = {
                DBScheme.DateTodo._ID,
                DBScheme.DateTodo.COLUMN_NAME_TITLE,
                DBScheme.DateTodo.COLUMN_NAME_STATE,
                DBScheme.DateTodo.COLUMN_NAME_PRIORITY,
                DBScheme.DateTodo.COLUMN_NAME_CREATION_DATE,
                DBScheme.DateTodo.COLUMN_NAME_REMINDER_DATE
        };

        // How you want the results sorted in the resulting Cursor
        String sortOrder = DBScheme.DateTodo._ID + " ASC";

        Cursor cursor = db.query(
                DBScheme.DateTodo.TABLE_NAME,             // The table to query
                projection,                               // The columns to return
                null,                                     // The columns for the WHERE clause
                null,                                     // The values for the WHERE clause
                null,                                     // don't group the rows
                null,                                     // don't filter by row groups
                sortOrder                                 // The sort order
        );

        String content = "";


        while (cursor.moveToNext()) {
            //vars
            long id = cursor.getLong(cursor.getColumnIndexOrThrow(DBScheme.DateTodo._ID));
            String title = cursor.getString(cursor.getColumnIndexOrThrow(DBScheme.DateTodo.COLUMN_NAME_TITLE));
            String creationDate = cursor.getString(cursor.getColumnIndexOrThrow(DBScheme.DateTodo.COLUMN_NAME_CREATION_DATE));
            String reminderDate = cursor.getString(cursor.getColumnIndexOrThrow(DBScheme.DateTodo.COLUMN_NAME_REMINDER_DATE));
            String state = cursor.getString(cursor.getColumnIndexOrThrow(DBScheme.DateTodo.COLUMN_NAME_STATE));
            int priority = cursor.getInt(cursor.getColumnIndexOrThrow(DBScheme.DateTodo.COLUMN_NAME_PRIORITY));

            content = content + title + ";" + reminderDate + " | " + state + " | " + String.valueOf(priority);
            final String databaseId = Long.toString(id);
            list.put(count, title + ";" + "dateTodo;" + databaseId + ";" + reminderDate + " | " + state + " | " + String.valueOf(priority));
            count++;
        }
        cursor.close();


        //Get Sport Todo
        projection = new String[]{
                DBScheme.SportTodo._ID,
                DBScheme.SportTodo.COLUMN_NAME_TITLE,
                DBScheme.SportTodo.COLUMN_NAME_STATE,
                DBScheme.SportTodo.COLUMN_NAME_PRIORITY,
                DBScheme.SportTodo.COLUMN_NAME_CREATION_DATE,
                DBScheme.SportTodo.COLUMN_NAME_STEP_GOAL,
                DBScheme.SportTodo.COLUMN_NAME_STEPS_DONE,
                DBScheme.SportTodo.COLUMN_NAME_TIME_USED
        };

        // Filter results
        String selection = DBScheme.SportTodo.COLUMN_NAME_STATE + " = ?";
        String[] selectionArgs = {"pending"};

        // How you want the results sorted in the resulting Cursor
        sortOrder = DBScheme.SportTodo._ID + " ASC";

        cursor = db.query(
                DBScheme.SportTodo.TABLE_NAME,            // The table to query
                projection,                               // The columns to return
                selection,                                // The columns for the WHERE clause
                selectionArgs,                            // The values for the WHERE clause
                null,                                     // don't group the rows
                null,                                     // don't filter by row groups
                sortOrder                                 // The sort order
        );

        List itemIds = new ArrayList<>();
        while (cursor.moveToNext()) {
            long id = cursor.getLong(cursor.getColumnIndexOrThrow(DBScheme.SportTodo._ID));
            String title = cursor.getString(cursor.getColumnIndexOrThrow(DBScheme.SportTodo.COLUMN_NAME_TITLE));
            String creationDate = cursor.getString(cursor.getColumnIndexOrThrow(DBScheme.SportTodo.COLUMN_NAME_CREATION_DATE));
            String state = cursor.getString(cursor.getColumnIndexOrThrow(DBScheme.SportTodo.COLUMN_NAME_STATE));

            int priority = cursor.getInt(cursor.getColumnIndexOrThrow(DBScheme.SportTodo.COLUMN_NAME_PRIORITY));
            int stepgoal = cursor.getInt(cursor.getColumnIndexOrThrow(DBScheme.SportTodo.COLUMN_NAME_STEP_GOAL));
            int stepsdone = cursor.getInt(cursor.getColumnIndexOrThrow(DBScheme.SportTodo.COLUMN_NAME_STEPS_DONE));
            int timeused = cursor.getInt(cursor.getColumnIndexOrThrow(DBScheme.SportTodo.COLUMN_NAME_TIME_USED));

            final String databaseId = Long.toString(id);
            list.put(count, title + ";" + "sportTodo;" + databaseId + ";" + stepsdone + " / " + stepgoal + " steps done. | Used time: " + String.valueOf(timeused));
            count++;
        }
        cursor.close();

        //Return the Result
        return list;
    }

    //Function To remove Todo. Is automatically removed from database.
    public boolean removeTodo(Todo todo) {
        todoList.remove(todo);

        return true;
    }

    //Create New Date Todo in Database
    public void insertDateTodo(DateTodo todo) {
        // Gets the data repository in write mode
        SQLiteDatabase db = oDbHelper.getWritableDatabase();

        // Create a new map of values, where column names are the keys
        ContentValues values = new ContentValues();
        values.put(DBScheme.DateTodo.COLUMN_NAME_TITLE, todo.getTitle());
        values.put(DBScheme.DateTodo.COLUMN_NAME_CREATION_DATE, String.valueOf(todo.getCreationDate()));
        values.put(DBScheme.DateTodo.COLUMN_NAME_REMINDER_DATE, String.valueOf(todo.getReminderDate()));
        values.put(DBScheme.DateTodo.COLUMN_NAME_STATE, todo.getState());
        values.put(DBScheme.DateTodo.COLUMN_NAME_PRIORITY, todo.getPriority());

        // Insert the new row, returning the primary key value of the new row
        long lID = db.insert(DBScheme.DateTodo.TABLE_NAME, null, values);
    }

    //Create New Sport Todo in Database
    public void insertSportTodo(SportTodo todo) {
        // Gets the data repository in write mode
        SQLiteDatabase db = oDbHelper.getWritableDatabase();

        // Create a new map of values, where column names are the keys
        ContentValues values = new ContentValues();
        values.put(DBScheme.SportTodo.COLUMN_NAME_TITLE, todo.getTitle());
        values.put(DBScheme.SportTodo.COLUMN_NAME_CREATION_DATE, String.valueOf(todo.getCreationDate()));
        values.put(DBScheme.SportTodo.COLUMN_NAME_STATE, todo.getState());
        values.put(DBScheme.SportTodo.COLUMN_NAME_PRIORITY, todo.getPriority());
        values.put(DBScheme.SportTodo.COLUMN_NAME_STEP_GOAL, todo.getStepGoal());
        values.put(DBScheme.SportTodo.COLUMN_NAME_STEPS_DONE, 0);
        values.put(DBScheme.SportTodo.COLUMN_NAME_TIME_USED, 0);

        // Insert the new row, returning the primary key value of the new row
        long lID = db.insert(DBScheme.SportTodo.TABLE_NAME, null, values);
    }


    public void readDateTodo() {
        SQLiteDatabase db = oDbHelper.getReadableDatabase();

        // Define a projection that specifies which columns from the database
        // you will actually use after this query.
        String[] projection = {
                DBScheme.DateTodo._ID,
                DBScheme.DateTodo.COLUMN_NAME_TITLE,
                DBScheme.DateTodo.COLUMN_NAME_STATE,
                DBScheme.DateTodo.COLUMN_NAME_PRIORITY,
                DBScheme.DateTodo.COLUMN_NAME_CREATION_DATE
        };

        // Filter results WHERE "title" = 'My Title'
        String selection = DBScheme.DateTodo.COLUMN_NAME_TITLE + " = ?";
        String[] selectionArgs = {"Title Desc"};

        // How you want the results sorted in the resulting Cursor
        String sortOrder = DBScheme.DateTodo._ID + " ASC";

        Cursor cursor = db.query(
                DBScheme.DateTodo.TABLE_NAME,             // The table to query
                projection,                               // The columns to return
                selection,                                // The columns for the WHERE clause
                selectionArgs,                            // The values for the WHERE clause
                null,                                     // don't group the rows
                null,                                     // don't filter by row groups
                sortOrder                                 // The sort order
        );

        List itemIds = new ArrayList<>();
        while (cursor.moveToNext()) {
            long id = cursor.getLong(cursor.getColumnIndexOrThrow(DBScheme.DateTodo._ID));
            String title = cursor.getString(cursor.getColumnIndexOrThrow(DBScheme.DateTodo.COLUMN_NAME_TITLE));
            String creationDate = cursor.getString(cursor.getColumnIndexOrThrow(DBScheme.DateTodo.COLUMN_NAME_CREATION_DATE));
            String state = cursor.getString(cursor.getColumnIndexOrThrow(DBScheme.DateTodo.COLUMN_NAME_STATE));

            int priority = cursor.getInt(cursor.getColumnIndexOrThrow(DBScheme.DateTodo.COLUMN_NAME_PRIORITY));

            itemIds.add(String.valueOf(id) + " | " + title + " | " + creationDate + " | " + state + " | " + String.valueOf(priority));
        }
        cursor.close();

        //txtOutput.setText("Length: " + itemIds.size());

        for (int i = 0; i < itemIds.size(); i++) {
            //txtOutput.setText(String.valueOf(itemIds.get(i)));
        }

    }

    public void readSportTodo() {
        SQLiteDatabase db = oDbHelper.getReadableDatabase();

        // Define a projection that specifies which columns from the database
        // you will actually use after this query.
        String[] projection = {
                DBScheme.SportTodo._ID,
                DBScheme.SportTodo.COLUMN_NAME_TITLE,
                DBScheme.SportTodo.COLUMN_NAME_STATE,
                DBScheme.SportTodo.COLUMN_NAME_PRIORITY,
                DBScheme.SportTodo.COLUMN_NAME_CREATION_DATE,
                DBScheme.SportTodo.COLUMN_NAME_STEP_GOAL,
                DBScheme.SportTodo.COLUMN_NAME_STEPS_DONE,
                DBScheme.SportTodo.COLUMN_NAME_TIME_USED
        };

        // Filter results WHERE "title" = 'My Title'
        String selection = DBScheme.SportTodo.COLUMN_NAME_TITLE + " = ?";
        String[] selectionArgs = {"Title Desc"};

        // How you want the results sorted in the resulting Cursor
        String sortOrder = DBScheme.SportTodo._ID + " ASC";

        Cursor cursor = db.query(
                DBScheme.SportTodo.TABLE_NAME,             // The table to query
                projection,                               // The columns to return
                selection,                                // The columns for the WHERE clause
                selectionArgs,                            // The values for the WHERE clause
                null,                                     // don't group the rows
                null,                                     // don't filter by row groups
                sortOrder                                 // The sort order
        );

        List itemIds = new ArrayList<>();
        while (cursor.moveToNext()) {
            long id = cursor.getLong(cursor.getColumnIndexOrThrow(DBScheme.SportTodo._ID));
            String title = cursor.getString(cursor.getColumnIndexOrThrow(DBScheme.SportTodo.COLUMN_NAME_TITLE));
            String creationDate = cursor.getString(cursor.getColumnIndexOrThrow(DBScheme.SportTodo.COLUMN_NAME_CREATION_DATE));
            String state = cursor.getString(cursor.getColumnIndexOrThrow(DBScheme.SportTodo.COLUMN_NAME_STATE));

            int priority = cursor.getInt(cursor.getColumnIndexOrThrow(DBScheme.SportTodo.COLUMN_NAME_PRIORITY));
            int stepgoal = cursor.getInt(cursor.getColumnIndexOrThrow(DBScheme.SportTodo.COLUMN_NAME_STEP_GOAL));
            int stepsdone = cursor.getInt(cursor.getColumnIndexOrThrow(DBScheme.SportTodo.COLUMN_NAME_STEPS_DONE));
            int timeused = cursor.getInt(cursor.getColumnIndexOrThrow(DBScheme.SportTodo.COLUMN_NAME_TIME_USED));

            itemIds.add(String.valueOf(id) + " | " + title + " | " + creationDate + " | " + state + " | " + String.valueOf(priority) + " | " + String.valueOf(stepgoal) + " | " + String.valueOf(stepsdone) + " | " + String.valueOf(timeused));
        }
        cursor.close();

        //txtOutput.setText("Length: " + itemIds.size());

        for (int i = 0; i < itemIds.size(); i++) {
            //txtOutput.setText(String.valueOf(itemIds.get(i)));
        }
    }

    public void updateDateTodo() {
        SQLiteDatabase db = oDbHelper.getWritableDatabase();

        // New value for one column
        ContentValues values = new ContentValues();
        values.put(DBScheme.DateTodo.COLUMN_NAME_TITLE, "Title Desc Updated!");
        values.put(DBScheme.DateTodo.COLUMN_NAME_CREATION_DATE, "Date");
        values.put(DBScheme.DateTodo.COLUMN_NAME_STATE, "State");
        values.put(DBScheme.DateTodo.COLUMN_NAME_PRIORITY, 1);

        // Which row to update, based on the title
        String selection = DBScheme.DateTodo.COLUMN_NAME_CREATION_DATE + " LIKE ?";
        String[] selectionArgs = {"Date"};

        int count = db.update(
                DBScheme.DateTodo.TABLE_NAME,
                values,
                selection,
                selectionArgs);
        //txtOutput.setText(String.valueOf(count));

    }

    public void deleteDateTodo(Integer entryID) {
        SQLiteDatabase db = oDbHelper.getWritableDatabase();

        //Execute sql query to remove from database
        //NOTE: When removing by String in SQL, value must be enclosed with ''
        db.execSQL("DELETE FROM " + DBScheme.DateTodo.TABLE_NAME + " WHERE " + DBScheme.DateTodo._ID + " = "  + entryID.toString());

        //Close the database
        db.close();
    }

    public void deleteSportTodo(Integer entryID) {
        SQLiteDatabase db = oDbHelper.getWritableDatabase();

        //Execute sql query to remove from database
        //NOTE: When removing by String in SQL, value must be enclosed with ''
        db.execSQL("DELETE FROM " + DBScheme.SportTodo.TABLE_NAME + " WHERE " + DBScheme.SportTodo._ID + " = "  + entryID.toString());

        //Close the database
        db.close();
    }

    public void deleteDB() {
        SQLiteDatabase db = oDbHelper.getWritableDatabase();

        // Define 'where' part of query.
        String selection = DBScheme.DateTodo.COLUMN_NAME_TITLE + " LIKE ?";
        // Specify arguments in placeholder order.
        String[] selectionArgs = {"Title Desc"};

        // Issue SQL statement.
        db.delete(DBScheme.DateTodo.TABLE_NAME, selection, selectionArgs);
    }
}
