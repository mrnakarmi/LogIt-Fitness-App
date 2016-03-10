package danman.logit;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.SystemClock;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Chronometer;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import org.w3c.dom.Text;

/**
 * Created by Raman on 3/7/2016.
 */
public class quickWorkout extends AppCompatActivity {

    Spinner dropdown;

    TextView timeMinutes,timeSeconds,sets,reps,weight;
    EditText setsValue,repsValue,weightValue;
    Button timer;
    CountDownTimer myCountDownTimer = null;
    Button stopwatch;
    Chronometer stopper;
    boolean isRunning;


    @Override
    protected void onCreate(Bundle savedinstanceState){
        super.onCreate(savedinstanceState);
        setContentView(R.layout.activity_quickworkout);
        isRunning = false;
        stopper = (Chronometer)findViewById(R.id.chronometer);
        timer = (Button)findViewById(R.id.startTimer);
        stopwatch = (Button)findViewById(R.id.stopwatch);
        sets = (TextView)findViewById(R.id.quickSets);
        reps = (TextView)findViewById(R.id.quickRepitions);
        weight = (TextView)findViewById(R.id.quickWeight);

        setsValue = (EditText) findViewById(R.id.quickExcerciseSets);
        repsValue = (EditText) findViewById(R.id.quickExcerciseReps);
        weightValue = (EditText) findViewById(R.id.quickExcerciseWeight);

        sets.setVisibility(View.INVISIBLE);
        reps.setVisibility(View.INVISIBLE);
        weight.setVisibility(View.INVISIBLE);
        setsValue.setVisibility(View.INVISIBLE);
        repsValue.setVisibility(View.INVISIBLE);
        weightValue.setVisibility(View.INVISIBLE);

        dropdown = (Spinner)findViewById(R.id.quickFocus);
        String[] items = new String[]{"Select","Arms","Legs","Core","Cardio"};
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_dropdown_item,items);
        dropdown.setAdapter(adapter);

        addListeners();

    }

    public void addListeners(){
        dropdown = (Spinner)findViewById(R.id.quickFocus);
        dropdown.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            protected Adapter initializedAdapter=null;
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {

                if (initializedAdapter != parentView.getAdapter()) {
                    initializedAdapter = parentView.getAdapter();
                    return;
                }

                String selected = parentView.getItemAtPosition(position).toString();
                Log.i("SPINNERINFO",selected);

                if(selected.equals("Cardio")){
                    //Cardio does not require these fields
                    sets.setVisibility(View.INVISIBLE);
                    reps.setVisibility(View.INVISIBLE);
                    weight.setVisibility(View.INVISIBLE);
                    setsValue.setVisibility(View.INVISIBLE);
                    repsValue.setVisibility(View.INVISIBLE);
                    weightValue.setVisibility(View.INVISIBLE);

                    //Other fields dedicated to cardio should be active here.

                }else if(selected.equals("Select")){
                    //if they don't pick anything
                    sets.setVisibility(View.INVISIBLE);
                    reps.setVisibility(View.INVISIBLE);
                    weight.setVisibility(View.INVISIBLE);
                    setsValue.setVisibility(View.INVISIBLE);
                    repsValue.setVisibility(View.INVISIBLE);
                    weightValue.setVisibility(View.INVISIBLE);

                }else{
                    //All other categories need this field
                    sets.setVisibility(View.VISIBLE);
                    reps.setVisibility(View.VISIBLE);
                    weight.setVisibility(View.VISIBLE);
                    setsValue.setVisibility(View.VISIBLE);
                    repsValue.setVisibility(View.VISIBLE);
                    weightValue.setVisibility(View.VISIBLE);
                }

            }
            @Override
            public void onNothingSelected(AdapterView<?> parentView) {

            }
        });
    }

    public void timeStart (View v){

        timeMinutes = (TextView)findViewById(R.id.quickTimerMinutes);
        timeSeconds = (TextView)findViewById(R.id.quickTimerSeconds);
        long minutes,seconds;

        if(timeMinutes.getText().toString().isEmpty()){
            minutes = 0;
        }else{
            minutes = Long.parseLong(timeMinutes.getText().toString());
        }
        if(timeSeconds.getText().toString().isEmpty()){
            seconds = 0;
        }else{
            seconds = Long.parseLong(timeSeconds.getText().toString());
        }
        Log.i("MYAPPMINUTES",timeMinutes.getText().toString());
        Log.i("MYAPPSECONDS",timeSeconds.getText().toString());
        TextView mTextField = (TextView) findViewById(R.id.timer);
        long minutesToSeconds = minutes *60;
        long secondsToMilli = minutesToSeconds *1000;
        long addingSeconds = seconds * 1000;
        long finalTime = secondsToMilli + addingSeconds; //to produce cases like 5 mins and 30 seconds.
        Log.i("MYAPPFINALTIME",Float.toString(finalTime));

        if(timer.getText().equals("Start Timer")){
            timer.setText("Cancel");

            if(timeMinutes.getText().toString().isEmpty() && timeSeconds.getText().toString().isEmpty()){
                Log.i("MYAPP","time field is empty");

            }else {

                //start
                myCountDownTimer = new CountDownTimer(finalTime, 1000) {
                    TextView mTextField = (TextView) findViewById(R.id.timer);
                    @Override
                    public void onTick(long millisUntilFinished) {
                        mTextField.setVisibility(View.VISIBLE);
                        mTextField.setText("seconds remaining: " + millisUntilFinished / 1000 + " secs");
                    }

                    @Override
                    public void onFinish() {
                        mTextField.setVisibility(View.INVISIBLE);
                    }
                }.start();

            }
        }else{
            //cancel
            myCountDownTimer.cancel();
            myCountDownTimer = null;
            mTextField.setVisibility(View.INVISIBLE);
            timer.setText("Start Timer");
        }
    }

    public void startStopWatch(View v){

        if(isRunning == false) {
            isRunning = true;
            stopper.start();
            stopwatch.setText("Pause");
        }
        else{
            stopper.stop();
            isRunning = false;
            stopwatch.setText("Start Stopwatch");
        }
    }

    public void resetStopwatch(View v){
        stopper.stop();
        stopper.setBase(SystemClock.elapsedRealtime());
        isRunning = false;
        stopwatch.setText("Start Stopwatch");
    }


}
