package danman.logit;

import android.app.Activity;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by Raman on 3/7/2016.
 */
public class quickWorkout extends AppCompatActivity {

    Spinner dropdown;

    TextView time,sets,reps,weight;
    EditText setsValue,repsValue,weightValue;
    Button timer;
    CountDownTimer myCountDownTimer = null;


    @Override
    protected void onCreate(Bundle savedinstanceState){
        super.onCreate(savedinstanceState);
        setContentView(R.layout.activity_quickworkout);

        timer = (Button)findViewById(R.id.startTimer);

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

        time = (TextView)findViewById(R.id.minutes);
        long minutes = Long.parseLong(time.getText().toString());
        Log.i("MYAPPMINUTES",time.getText().toString());
        TextView mTextField = (TextView) findViewById(R.id.timer);
        long seconds = minutes *60;
        long milli = seconds *1000;

        if(timer.getText().equals("Start Timer")){
            timer.setText("Cancel");

            if(time.getText().toString().isEmpty()){
                Log.i("MYAPP","minute field is empty");

            }else {

                //start
                myCountDownTimer = new CountDownTimer(milli, 1000) {
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


}
