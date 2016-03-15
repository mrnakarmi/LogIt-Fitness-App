package danman.logit;

import android.content.Context;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

/**
 * Created by dkimm on 3/14/2016.
 */
public class workoutInfo extends AppCompatActivity {
    Context context;
    String workoutID;
    SharedPreferences settings;
    workoutDatabaseDbHelper mDbHelper;

    TextView workout1Title,workout2Title,workout3Title,workout4Title;
    TextView workout1SetsText, workout2SetsText,workout3SetsText,workout4SetsText;
    TextView workout1RepsText,workout2RepsText,workout3RepsText,workout4RepsText;
    TextView workout1WeightText,workout2WeightText,workout3WeightText,workout4WeightText;
    EditText workout1SetsField,workout2SetsField,workout3SetsField,workout4SetsField;
    EditText workout1RepsField,workout2RepsField,workout3RepsField,workout4RepsField;
    EditText workout1WeightField,workout2WeightField,workout3WeightField,workout4WeightField;
    EditText [] sets;
    EditText [] reps;
    EditText [] weights;
    TextView [] names;
    TextView [] setsText;
    TextView [] repsText;
    TextView [] weightsText;


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_workoutinfo);
        settings = PreferenceManager.getDefaultSharedPreferences(this);
        mDbHelper = new workoutDatabaseDbHelper(this);

        workoutID = settings.getString("workoutName", ""); //TODO must change db such that unique name is not optional
        workout1Title = (TextView)findViewById(R.id.workout1Title1);
        workout2Title = (TextView)findViewById(R.id.workout2Title1);
        workout3Title = (TextView)findViewById(R.id.workout3Title1);
        workout4Title = (TextView)findViewById(R.id.workout4Title1);
        workout1Title.setVisibility(View.INVISIBLE);
        workout2Title.setVisibility(View.INVISIBLE);
        workout3Title.setVisibility(View.INVISIBLE);
        workout4Title.setVisibility(View.INVISIBLE);

        workout1SetsText = (TextView) findViewById(R.id.workout1SetsText1);
        workout2SetsText = (TextView) findViewById(R.id.workout2SetsText1);
        workout3SetsText = (TextView) findViewById(R.id.workout3SetsText1);
        workout4SetsText = (TextView) findViewById(R.id.workout4SetsText);
        workout1SetsText.setVisibility(View.INVISIBLE);
        workout2SetsText.setVisibility(View.INVISIBLE);
        workout3SetsText.setVisibility(View.INVISIBLE);
        workout4SetsText.setVisibility(View.INVISIBLE);

        workout1RepsText = (TextView) findViewById(R.id.workout1RepsText1);
        workout2RepsText = (TextView) findViewById(R.id.workout2RepsText1);
        workout3RepsText = (TextView) findViewById(R.id.workout3RepsText1);
        workout4RepsText = (TextView) findViewById(R.id.workout4RepsText1);
        workout1RepsText.setVisibility(View.INVISIBLE);
        workout2RepsText.setVisibility(View.INVISIBLE);
        workout3RepsText.setVisibility(View.INVISIBLE);
        workout4RepsText.setVisibility(View.INVISIBLE);

        workout1WeightText = (TextView) findViewById(R.id.workout1WeightText1);
        workout2WeightText = (TextView) findViewById(R.id.workout2WeightText1);
        workout3WeightText = (TextView) findViewById(R.id.workout3WeightText1);
        workout4WeightText = (TextView) findViewById(R.id.workout4WeightText1);
        workout1WeightText.setVisibility(View.INVISIBLE);
        workout2WeightText.setVisibility(View.INVISIBLE);
        workout3WeightText.setVisibility(View.INVISIBLE);
        workout4WeightText.setVisibility(View.INVISIBLE);

        workout1SetsField = (EditText)findViewById(R.id.workout1Sets1);
        workout2SetsField = (EditText)findViewById(R.id.workout2Sets1);
        workout3SetsField = (EditText)findViewById(R.id.workout3Sets1);
        workout4SetsField = (EditText)findViewById(R.id.workout4Sets1);
        workout1SetsField.setVisibility(View.INVISIBLE);
        workout2SetsField.setVisibility(View.INVISIBLE);
        workout3SetsField.setVisibility(View.INVISIBLE);
        workout4SetsField.setVisibility(View.INVISIBLE);

        workout1RepsField = (EditText)findViewById(R.id.workout1Reps1);
        workout2RepsField = (EditText)findViewById(R.id.workout2Reps1);
        workout3RepsField = (EditText)findViewById(R.id.workout3Reps1);
        workout4RepsField = (EditText)findViewById(R.id.workout4Reps1);
        workout1RepsField.setVisibility(View.INVISIBLE);
        workout2RepsField.setVisibility(View.INVISIBLE);
        workout3RepsField.setVisibility(View.INVISIBLE);
        workout4RepsField.setVisibility(View.INVISIBLE);


        workout1WeightField = (EditText)findViewById(R.id.workout1Weight1);
        workout2WeightField = (EditText)findViewById(R.id.workout2Weight1);
        workout3WeightField = (EditText)findViewById(R.id.workout3Weight1);
        workout4WeightField = (EditText)findViewById(R.id.workout4Weight1);
        workout1WeightField.setVisibility(View.INVISIBLE);
        workout2WeightField.setVisibility(View.INVISIBLE);
        workout3WeightField.setVisibility(View.INVISIBLE);
        workout4WeightField.setVisibility(View.INVISIBLE);
        sets = new EditText []{workout1SetsField,workout2SetsField,workout3SetsField,workout4SetsField};
        reps = new EditText[]{workout1RepsField,workout2RepsField,workout3RepsField,workout4RepsField};
        weights = new EditText[]{workout1WeightField,workout2WeightField,workout3WeightField,workout4WeightField};
        names = new TextView[] {workout1Title,workout2Title,workout3Title,workout4Title};
        setsText = new TextView[]{workout1SetsText, workout2SetsText,workout3SetsText,workout4SetsText};
        repsText = new TextView[]{workout1RepsText,workout2RepsText,workout3RepsText,workout4RepsText};
        weightsText = new TextView[]{workout1WeightText,workout2WeightText,workout3WeightText,workout4WeightText};
        loadWorkoutInfo();
    }

    public void loadWorkoutInfo(){
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

        String selection = "uniqueWorkout = ?";
        String[] selectionArgs = {workoutID};



        // How you want the results sorted in the resulting Cursor
        String sortOrder =
                workoutDatabase.gymWorkout._ID + " ASC";

        Cursor c = dbRead.query(
                workoutDatabase.gymWorkout.TABLE_NAME,  // The table to query
                projection,                               // The columns to return
                selection,                                // The columns for the WHERE clause
                selectionArgs,                            // The values for the WHERE clause
                null,                                     // don't group the rows
                null,                                     // don't filter by row groups
                sortOrder                                 // The sort order
        );
        Log.i("QUERY", "query is here");
        if (c .moveToFirst()) {
            int i = 0;
            while (c.isAfterLast() == false) {
                Log.i("SETTINGDATA", "have some data");
                String name = c.getString(c.getColumnIndexOrThrow(workoutDatabase.gymWorkout.COLUMN_NAME_NAME));
                String set = c.getString(c.getColumnIndexOrThrow(workoutDatabase.gymWorkout.COLUMN_NAME_SETS));
                String rep = c.getString(c.getColumnIndexOrThrow(workoutDatabase.gymWorkout.COLUMN_NAME_REPS));
                String weight = c.getString(c.getColumnIndexOrThrow(workoutDatabase.gymWorkout.COLUMN_NAME_WEIGHT));
                if(name != null && i < 4) {
                    Log.i("SETTINGDATA", "data being set");
                    names[i].setText(name);
                    names[i].setVisibility(View.VISIBLE);
                    sets[i].setText(set);
                    sets[i].setVisibility(View.VISIBLE);
                    reps[i].setText(rep);
                    reps[i].setVisibility(View.VISIBLE);
                    weights[i].setText(weight);
                    weights[i].setVisibility(View.VISIBLE);
                    setsText[i].setVisibility(View.VISIBLE);
                    repsText[i].setVisibility(View.VISIBLE);
                    weightsText[i].setVisibility(View.VISIBLE);
                }
                i++;
                c.moveToNext();
            }
        }
        c.close();
    }

}
