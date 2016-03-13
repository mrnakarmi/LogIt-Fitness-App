package danman.logit;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

/**
 * Created by Raman on 3/12/2016.
 */
public class editWorkout extends AppCompatActivity {

    SharedPreferences settings;

    boolean workout1Boolean, workout2Boolean, workout3Boolean,workout4Boolean;
    TextView workout1Title,workout2Title,workout3Title,workout4Title;
    TextView workout1SetsText, workout2SetsText,workout3SetsText,workout4SetsText;
    TextView workout1RepsText,workout2RepsText,workout3RepsText,workout4RepsText;
    TextView workout1WeightText,workout2WeightText,workout3WeightText,workout4WeightText;
    EditText workout1SetsField,workout2SetsField,workout3SetsField,workout4SetsField;
    EditText workout1RepsField,workout2RepsField,workout3RepsField,workout4RepsField;
    EditText workout1WeightField,workout2WeightField,workout3WeightField,workout4WeightField;

    String workoutItem;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editworkout);
        getSupportActionBar().setDisplayHomeAsUpEnabled(false);

        settings = PreferenceManager.getDefaultSharedPreferences(this);

        workout1Title = (TextView)findViewById(R.id.workout1Title);
        workout2Title = (TextView)findViewById(R.id.workout2Title);
        workout3Title = (TextView)findViewById(R.id.workout3Title);
        workout4Title = (TextView)findViewById(R.id.workout4Title);
        workout1Title.setVisibility(View.INVISIBLE);
        workout2Title.setVisibility(View.INVISIBLE);
        workout3Title.setVisibility(View.INVISIBLE);
        workout4Title.setVisibility(View.INVISIBLE);

        workout1SetsText = (TextView) findViewById(R.id.workout1SetsText);
        workout2SetsText = (TextView) findViewById(R.id.workout2SetsText);
        workout3SetsText = (TextView) findViewById(R.id.workout3SetsText);
        workout4SetsText = (TextView) findViewById(R.id.workout4SetsText);
        workout1SetsText.setVisibility(View.INVISIBLE);
        workout2SetsText.setVisibility(View.INVISIBLE);
        workout3SetsText.setVisibility(View.INVISIBLE);
        workout4SetsText.setVisibility(View.INVISIBLE);

        workout1RepsText = (TextView) findViewById(R.id.workout1RepsText);
        workout2RepsText = (TextView) findViewById(R.id.workout2RepsText);
        workout3RepsText = (TextView) findViewById(R.id.workout3RepsText);
        workout4RepsText = (TextView) findViewById(R.id.workout4RepsText);
        workout1RepsText.setVisibility(View.INVISIBLE);
        workout2RepsText.setVisibility(View.INVISIBLE);
        workout3RepsText.setVisibility(View.INVISIBLE);
        workout4RepsText.setVisibility(View.INVISIBLE);

        workout1WeightText = (TextView) findViewById(R.id.workout1WeightText);
        workout2WeightText = (TextView) findViewById(R.id.workout2WeightText);
        workout3WeightText = (TextView) findViewById(R.id.workout3WeightText);
        workout4WeightText = (TextView) findViewById(R.id.workout4WeightText);
        workout1WeightText.setVisibility(View.INVISIBLE);
        workout2WeightText.setVisibility(View.INVISIBLE);
        workout3WeightText.setVisibility(View.INVISIBLE);
        workout4WeightText.setVisibility(View.INVISIBLE);

        workout1SetsField = (EditText)findViewById(R.id.workout1Sets);
        workout2SetsField = (EditText)findViewById(R.id.workout2Sets);
        workout3SetsField = (EditText)findViewById(R.id.workout3Sets);
        workout4SetsField = (EditText)findViewById(R.id.workout4Sets);
        workout1SetsField.setVisibility(View.INVISIBLE);
        workout2SetsField.setVisibility(View.INVISIBLE);
        workout3SetsField.setVisibility(View.INVISIBLE);
        workout4SetsField.setVisibility(View.INVISIBLE);

        workout1RepsField = (EditText)findViewById(R.id.workout1Reps);
        workout2RepsField = (EditText)findViewById(R.id.workout2Reps);
        workout3RepsField = (EditText)findViewById(R.id.workout3Reps);
        workout4RepsField = (EditText)findViewById(R.id.workout4Reps);
        workout1RepsField.setVisibility(View.INVISIBLE);
        workout2RepsField.setVisibility(View.INVISIBLE);
        workout3RepsField.setVisibility(View.INVISIBLE);
        workout4RepsField.setVisibility(View.INVISIBLE);


        workout1WeightField = (EditText)findViewById(R.id.workout1Weight);
        workout2WeightField = (EditText)findViewById(R.id.workout2Weight);
        workout3WeightField = (EditText)findViewById(R.id.workout3Weight);
        workout4WeightField = (EditText)findViewById(R.id.workout4Weight);
        workout1WeightField.setVisibility(View.INVISIBLE);
        workout2WeightField.setVisibility(View.INVISIBLE);
        workout3WeightField.setVisibility(View.INVISIBLE);
        workout4WeightField.setVisibility(View.INVISIBLE);



        workout1Boolean =  settings.getBoolean("workout1", false);
        workout2Boolean =  settings.getBoolean("workout2", false);
        workout3Boolean =  settings.getBoolean("workout3",false);
        workout4Boolean =  settings.getBoolean("workout4",false);

        Log.i("CHECKINGINFO1", Boolean.toString(workout1Boolean));
        Log.i("CHECKINGINFO2", Boolean.toString(workout2Boolean));
        Log.i("CHECKINGINFO3", Boolean.toString(workout3Boolean));
        Log.i("CHECKINGINFO4", Boolean.toString(workout4Boolean));

        workoutItem = settings.getString("createWorkoutItem","");
        Log.i("CHECKINGINFOWORKOUTITEM",workoutItem);


    }

    @Override
    public void onResume(){

        setExcerciseInfo();
        loadExcerciseInfo();
        super.onResume();
    }

    public void setExcerciseInfo(){


        if(workoutItem.equals("")){
            //they didn't pick anything before
            //don't do anything
        }else{
            //ask for sets rep weight and store them into DB.
            if(workoutItem.equals("Arms")){

                //List the arm workouts
                workout1Title.setText("Upright Row");
                workout2Title.setText("Overhead Tricep Extensions");
                workout3Title.setText("Bent-over Bow");
                workout4Title.setText("Biscep Curl");

            }else if(workoutItem.equals("Legs")){

                workout1Title.setText("Front Squats");
                workout2Title.setText("Deadlifts");
                workout3Title.setText("Leg Press");
                workout4Title.setText("Lunges");

            }else if(workoutItem.equals("Chest")){

                workout1Title.setText("Pushups");
                workout2Title.setText("Bench Press");
                workout3Title.setText("Dumbell Chest Flyes");
                workout4Title.setText("Chest Dips");

            }else if(workoutItem.equals("Back")){

                workout1Title.setText("Wide Grip Pull Up");
                workout2Title.setText("Standing T-Bar Row");
                workout3Title.setText("Close Grip Pull Down");
                workout4Title.setText("Deadlift");

            }else if(workoutItem.equals("Abs")){

                workout1Title.setText("Abs Wheel Rollout");
                workout2Title.setText("Dip with Leg Raise");
                workout3Title.setText("Sit ups");
                workout4Title.setText("Leg raise");

            }else if(workoutItem.equals("Cardio")){

                workout1Title.setText("Running");
                workout2Title.setText("Power Walking");
                workout3Title.setText("Pacers");
                workout4Title.setText("Swimming");

            }


        }

    }

    public void loadExcerciseInfo(){

        if(workout1Boolean == true){
            workout1Title.setVisibility(View.VISIBLE);
            workout1SetsText.setVisibility(View.VISIBLE);
            workout1SetsField.setVisibility(View.VISIBLE);
            workout1RepsText.setVisibility(View.VISIBLE);
            workout1RepsField.setVisibility(View.VISIBLE);
            workout1WeightText.setVisibility(View.VISIBLE);
            workout1WeightField.setVisibility(View.VISIBLE);
        }

        if(workout2Boolean == true){
            workout2Title.setVisibility(View.VISIBLE);
            workout2SetsText.setVisibility(View.VISIBLE);
            workout2SetsField.setVisibility(View.VISIBLE);
            workout2RepsText.setVisibility(View.VISIBLE);
            workout2RepsField.setVisibility(View.VISIBLE);
            workout2WeightText.setVisibility(View.VISIBLE);
            workout2WeightField.setVisibility(View.VISIBLE);
        }

        if(workout3Boolean == true){
            workout3Title.setVisibility(View.VISIBLE);
            workout3SetsText.setVisibility(View.VISIBLE);
            workout3SetsField.setVisibility(View.VISIBLE);
            workout3RepsText.setVisibility(View.VISIBLE);
            workout3RepsField.setVisibility(View.VISIBLE);
            workout3WeightText.setVisibility(View.VISIBLE);
            workout3WeightField.setVisibility(View.VISIBLE);
        }

        if(workout4Boolean == true){
            workout4Title.setVisibility(View.VISIBLE);
            workout4SetsText.setVisibility(View.VISIBLE);
            workout4SetsField.setVisibility(View.VISIBLE);
            workout4RepsText.setVisibility(View.VISIBLE);
            workout4RepsField.setVisibility(View.VISIBLE);
            workout4WeightText.setVisibility(View.VISIBLE);
            workout4WeightField.setVisibility(View.VISIBLE);
        }


    }
}