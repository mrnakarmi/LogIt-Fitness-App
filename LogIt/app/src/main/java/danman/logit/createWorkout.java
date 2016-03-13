package danman.logit;

import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
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
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.Spinner;
import android.widget.TextView;

import java.text.SimpleDateFormat;

/**
 * Created by Raman on 3/10/2016.
 */
public class createWorkout extends AppCompatActivity {

    SharedPreferences settings;
    Context context;
    String[] workouts = {"Select", "Arms", "Legs", "Chest", "Back", "Abs", "Cardio"};
    Spinner workoutItems;
    CheckBox workout1,workout2,workout3,workout4;
    TextView excerciseSelectText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_createworkout);
        context = getApplicationContext();
        workoutItems = (Spinner) findViewById(R.id.createWorkoutFocus);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, workouts);
        workoutItems.setAdapter(adapter);

        excerciseSelectText = (TextView)findViewById(R.id.excerciseSelectText);
        workout1 = (CheckBox)findViewById(R.id.workout1Title);
        workout2 = (CheckBox)findViewById(R.id.workout2Title);
        workout3 = (CheckBox)findViewById(R.id.workout3Title);
        workout4 = (CheckBox)findViewById(R.id.workout4Title);

        excerciseSelectText.setVisibility(View.INVISIBLE);
        workout1.setVisibility(View.INVISIBLE);
        workout2.setVisibility(View.INVISIBLE);
        workout3.setVisibility(View.INVISIBLE);
        workout4.setVisibility(View.INVISIBLE);

        addListeners();


    }


    @Override
    public void onResume() {
        context = getApplicationContext();
        Log.i("HELLOINFO","I AM ONRESUME");

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
                    workout1.setVisibility(View.INVISIBLE);
                    workout2.setVisibility(View.INVISIBLE);
                    workout3.setVisibility(View.INVISIBLE);
                    workout4.setVisibility(View.INVISIBLE);

                    workout1.setChecked(false);
                    workout2.setChecked(false);
                    workout3.setChecked(false);
                    workout4.setChecked(false);
                } else {

                    //They Chose a workout
                    SharedPreferences.Editor editor = settings.edit();
                    editor.putString("createWorkoutItem", selected);
                    editor.commit();
                    workout1.setChecked(false);
                    workout2.setChecked(false);
                    workout3.setChecked(false);
                    workout4.setChecked(false);
                    excerciseSelectText.setVisibility(View.VISIBLE);

                    if (selected.equals("Cardio")) {

                        workout1.setText("Running");
                        workout2.setText("Power Walking");
                        workout3.setText("Pacers");
                        workout4.setText("Swimming");

                    } else if (selected.equals("Arms")) {

                        workout1.setText("Upright Row");
                        workout2.setText("Overhead Tricep Extensions");
                        workout3.setText("Bent-over Row");
                        workout4.setText("Bicep Curl");

                    } else if (selected.equals("Legs")) {

                        workout1.setText("Front Squats");
                        workout2.setText("Deadlifts");
                        workout3.setText("Leg Press");
                        workout4.setText("Lunges");

                    } else if (selected.equals("Chest")) {

                        workout1.setText("Pushups");
                        workout2.setText("Bench Press");
                        workout3.setText("Dumbell Chest Flyes");
                        workout4.setText("Chest Dips");

                    } else if (selected.equals("Abs")) {

                        workout1.setText("Abs Wheel Rollout");
                        workout2.setText("Dip with Leg Raise");
                        workout3.setText("Sit ups");
                        workout4.setText("Leg raise");

                    } else if (selected.equals("Back")) {

                        workout1.setText("Wide Grip Pull Up");
                        workout2.setText("Standing T-Bar Row");
                        workout3.setText("Close Grip Pull Down");
                        workout4.setText("Deadlift");

                    }

                    workout1.setVisibility(View.VISIBLE);
                    workout2.setVisibility(View.VISIBLE);
                    workout3.setVisibility(View.VISIBLE);
                    workout4.setVisibility(View.VISIBLE);
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

    public void saveExcercises(){

        Log.i("SAVEEXCERCISEINFO","I AM HERE");
        settings = PreferenceManager.getDefaultSharedPreferences(this);


        workout1.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView,boolean isChecked){
                Log.i("INFO1","LOL I AM HERE");
                SharedPreferences.Editor editor = settings.edit();
                if(buttonView.isChecked()){
                    editor.putBoolean("workout1",true);
                }else{
                    editor.putBoolean("workout1",false);
                }
                editor.commit();

            }
        });

        workout2.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                Log.i("INFO2", "LOL I AM HERE");
                SharedPreferences.Editor editor = settings.edit();
                if (buttonView.isChecked()) {
                    editor.putBoolean("workout2", true);
                } else {
                    editor.putBoolean("workout2", false);
                }
                editor.commit();

            }
        });

        workout3.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                Log.i("INFO3", "LOL I AM HERE");
                SharedPreferences.Editor editor = settings.edit();
                if (buttonView.isChecked()) {
                    editor.putBoolean("workout3", true);
                } else {
                    editor.putBoolean("workout3", false);
                }
                editor.commit();

            }
        });

        workout4.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                Log.i("INFO4", "LOL I AM HERE");
                SharedPreferences.Editor editor = settings.edit();
                if (buttonView.isChecked()) {
                    editor.putBoolean("workout4", true);
                } else {
                    editor.putBoolean("workout4", false);
                }
                editor.commit();

            }
        });

    }

    public void editWorkout(View v){
        settings = PreferenceManager.getDefaultSharedPreferences(this);
        SharedPreferences.Editor editor = settings.edit();

        if(workout1.isChecked() == false){
            editor.putBoolean("workout1",false);
        }else{
            editor.putBoolean("workout1",true);
        }
        if(workout2.isChecked() == false){
            editor.putBoolean("workout2",false);
        }else{
            editor.putBoolean("workout2",true);
        }
        if(workout3.isChecked() == false){
            editor.putBoolean("workout3",false);
        }else{
            editor.putBoolean("workout3",true);
        }
        if(workout4.isChecked() == false){
            editor.putBoolean("workout4",false);
        }else{
            editor.putBoolean("workout4",true);
        }

        editor.commit();

        Intent intent = new Intent(this,editWorkout.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);

    }

}
