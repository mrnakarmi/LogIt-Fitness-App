package danman.logit;

import android.content.ContentValues;
import android.content.Context;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

/**
 * Created by Raman on 3/10/2016.
 */
public class createWorkout extends AppCompatActivity {

    SharedPreferences settings;
    Context context;
    String[] workouts = {"Select", "Arms", "Legs", "Chest", "Back", "Abs", "Cardio"};
    Spinner workoutItems;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_createworkout);
        context = getApplicationContext();
        workoutItems = (Spinner) findViewById(R.id.createWorkoutFocus);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, workouts);
        workoutItems.setAdapter(adapter);


    }


    @Override
    public void onResume() {
        context = getApplicationContext();
        addListeners();
        super.onResume();
    }

    public void addListeners() {

        settings = PreferenceManager.getDefaultSharedPreferences(this);
        SharedPreferences.Editor editor = settings.edit();

        workoutItems.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            protected Adapter initializedAdapter = null;

            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {

                if (initializedAdapter != parentView.getAdapter()) {
                    initializedAdapter = parentView.getAdapter();
                    return;
                }

                String selected = parentView.getItemAtPosition(position).toString();
                Log.i("WORKOUTINFO", selected);

                if (selected.equals("Select")) {
                    //They didn't choose anything
                } else {

                    SharedPreferences.Editor editor = settings.edit();
                    editor.putString("createWorkoutItem", selected);
                    workoutDatabaseDbHelper mDbHelper = new workoutDatabaseDbHelper(context);
                    // Gets the data repository in write mode
                    SQLiteDatabase db = mDbHelper.getWritableDatabase();
                    //variables to insert
                    int sets = 3;
                    int reps = 10;
                    int weight = 50;
                    String name = "bicep";
                    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                    String time = sdf.format(new java.util.Date());



                    // Create a new map of values, where column names are the keys
                    ContentValues values = new ContentValues();
                    values.put(workoutDatabase.gymWorkout.COLUMN_NAME_CATEGORY, selected);
                    values.put(workoutDatabase.gymWorkout.COLUMN_NAME_SETS, sets);
                    values.put(workoutDatabase.gymWorkout.COLUMN_NAME_REPS, reps);
                    values.put(workoutDatabase.gymWorkout.COLUMN_NAME_WEIGHT, weight);
                    values.put(workoutDatabase.gymWorkout.COLUMN_NAME_NAME, name);
                    values.put(workoutDatabase.gymWorkout.COLUMN_NAME_TIME, time);

                    // Insert the new row, returning the primary key value of the new row
                    long newRowId;
                    newRowId = db.insert(
                            workoutDatabase.gymWorkout.TABLE_NAME,
                            "null",
                            values);
                    Log.i("INSERTEDROWID", String.valueOf(newRowId));

                    SQLiteDatabase dbRead = mDbHelper.getReadableDatabase();

                    // Define a projection that specifies which columns from the database
                    // you will actually use after this query.
                    String[] projection = {
                            workoutDatabase.gymWorkout._ID,
                            workoutDatabase.gymWorkout.COLUMN_NAME_SETS,
                            workoutDatabase.gymWorkout.COLUMN_NAME_CATEGORY,
                            workoutDatabase.gymWorkout.COLUMN_NAME_REPS,
                            workoutDatabase.gymWorkout.COLUMN_NAME_WEIGHT,
                            workoutDatabase.gymWorkout.COLUMN_NAME_NAME,
                            workoutDatabase.gymWorkout.COLUMN_NAME_TIME
                    };

                    String selection = "category = ?";
                    String[] selectionArgs = {selected};



                    // How you want the results sorted in the resulting Cursor
                    String sortOrder =
                            workoutDatabase.gymWorkout.COLUMN_NAME_CATEGORY + " DESC";

                    Cursor c = dbRead.query(
                            workoutDatabase.gymWorkout.TABLE_NAME,  // The table to query
                            projection,                               // The columns to return
                            selection,                                // The columns for the WHERE clause
                            selectionArgs,                            // The values for the WHERE clause
                            null,                                     // don't group the rows
                            null,                                     // don't filter by row groups
                            sortOrder                                 // The sort order
                    );
                    //moveToLast is not necesarry, could use moveToFirst instead
                    c.moveToLast();
                    String category = c.getString(
                            c.getColumnIndexOrThrow(workoutDatabase.gymWorkout.COLUMN_NAME_CATEGORY)
                    );
                    Log.i("GETCATEGORYNAME", category);
                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> parentView) {

            }
        });

    }

}
