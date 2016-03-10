package danman.logit;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;

import org.w3c.dom.Text;

/**
 * Created by Raman on 3/9/2016.
 */
public class milestone extends AppCompatActivity {

    EditText goalWeightField,goalWaistField,goalBiscepLeftField,goalBiscepRightField,goalThighLeftField,goalThighRightField;
    float goalWeight,goalBiscepLeft,goalBiscepRight,goalWaist,goalThighLeft,goalThighRight;
    float currentWeight,currentBiscepLeft,currentBiscepRight,currentWaist,currentThighLeft,currentThighRight;


    ProgressBar weightProgressBar, biscepLeftProgressBar, biscepRightProgressBar, waistProgressBar, thighLeftProgressBar, thighRightProgressBar;
    TextView weightProgressValue,biscepLeftProgressValue,biscepRightProgressValue, waistProgressValue, thighLeftProgressValue, thighRightProgressValue;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_milestone);

        goalWeightField = (EditText)findViewById(R.id.goalWeightPounds);
        goalWaistField = (EditText)findViewById(R.id.goalWaist);
        goalBiscepLeftField = (EditText)findViewById(R.id.goalBiscepLeft);
        goalBiscepRightField = (EditText)findViewById(R.id.goalBiscepRight);
        goalThighLeftField = (EditText)findViewById(R.id.goalThighLeft);
        goalThighRightField = (EditText)findViewById(R.id.goalThighRight);

        weightProgressBar = (ProgressBar)findViewById(R.id.weightProgressBar);
        biscepLeftProgressBar = (ProgressBar)findViewById(R.id.biscepLeftProgressBar);
        biscepRightProgressBar = (ProgressBar)findViewById(R.id.biscepRightProgressBar);
        waistProgressBar = (ProgressBar)findViewById(R.id.waistProgressBar);
        thighLeftProgressBar = (ProgressBar)findViewById(R.id.thighLeftProgressBar);
        thighRightProgressBar = (ProgressBar)findViewById(R.id.thighRightProgressBar);

        weightProgressValue = (TextView)findViewById(R.id.weightProgressNumber);
        biscepLeftProgressValue = (TextView)findViewById(R.id.biscepLeftProgressNumber);
        biscepRightProgressValue = (TextView)findViewById(R.id.biscepRightProgressNumber);
        waistProgressValue = (TextView)findViewById(R.id.waistProgressNumber);
        thighLeftProgressValue = (TextView)findViewById(R.id.thighLeftProgressNumber);
        thighRightProgressValue = (TextView)findViewById(R.id.thighRightProgressNumber);

        weightProgressValue.setVisibility(View.INVISIBLE);
        biscepLeftProgressValue.setVisibility(View.INVISIBLE);
        biscepRightProgressValue.setVisibility(View.INVISIBLE);
        waistProgressValue.setVisibility(View.INVISIBLE);
        thighLeftProgressValue.setVisibility(View.INVISIBLE);
        thighRightProgressValue.setVisibility(View.INVISIBLE);

    }

    @Override
    public void onResume(){
        SharedPreferences settings = PreferenceManager.getDefaultSharedPreferences(this);

        currentWeight = settings.getFloat("weight",0);
        currentBiscepLeft = settings.getFloat("biscepLeft",0);
        currentBiscepRight = settings.getFloat("biscepRight",0);
        currentWaist = settings.getFloat("waist",0);
        currentThighLeft = settings.getFloat("thighLeft",0);
        currentThighRight = settings.getFloat("thighRight",0);

        Log.i("CURRENTWEIGHTINFO",Float.toString(currentWeight));

        goalWeight = settings.getFloat("goalWeight",0);
        goalBiscepLeft = settings.getFloat("goalBiscepLeft",0);
        goalBiscepRight = settings.getFloat("goalBiscepRight",0);
        goalWaist = settings.getFloat("goalWaist",0);
        goalThighLeft = settings.getFloat("goalThighLeft",0);
        goalThighRight = settings.getFloat("goalThighRight",0);

        goalWeightField.setText(Float.toString(goalWeight));
        goalBiscepLeftField.setText(Float.toString(goalBiscepLeft));
        goalBiscepRightField.setText(Float.toString(goalBiscepRight));
        goalWaistField.setText(Float.toString(goalWaist));
        goalThighLeftField.setText(Float.toString(goalThighLeft));
        goalThighRightField.setText(Float.toString(goalThighRight));

        checkUpdateProgress();

        addListener();
        super.onResume();
    }

    public void addListener(){


        goalWeightField.addTextChangedListener(new TextWatcher() {

            @Override
            public void afterTextChanged(Editable s) {
            }

            @Override
            public void beforeTextChanged(CharSequence s, int start,
                                          int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start,
                                      int before, int count) {
                checkUpdateProgress();
            }
        });

        goalBiscepLeftField.addTextChangedListener(new TextWatcher() {

            @Override
            public void afterTextChanged(Editable s) {
            }

            @Override
            public void beforeTextChanged(CharSequence s, int start,
                                          int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start,
                                      int before, int count) {
                checkUpdateProgress();
            }
        });
        goalBiscepRightField.addTextChangedListener(new TextWatcher() {

            @Override
            public void afterTextChanged(Editable s) {
            }

            @Override
            public void beforeTextChanged(CharSequence s, int start,
                                          int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start,
                                      int before, int count) {
                checkUpdateProgress();
            }
        });
        goalWaistField.addTextChangedListener(new TextWatcher() {

            @Override
            public void afterTextChanged(Editable s) {
            }

            @Override
            public void beforeTextChanged(CharSequence s, int start,
                                          int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start,
                                      int before, int count) {
                checkUpdateProgress();
            }
        });
        goalThighLeftField.addTextChangedListener(new TextWatcher() {

            @Override
            public void afterTextChanged(Editable s) {
            }

            @Override
            public void beforeTextChanged(CharSequence s, int start,
                                          int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start,
                                      int before, int count) {
                checkUpdateProgress();
            }
        });
        goalThighRightField.addTextChangedListener(new TextWatcher() {

            @Override
            public void afterTextChanged(Editable s) {
            }

            @Override
            public void beforeTextChanged(CharSequence s, int start,
                                          int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start,
                                      int before, int count) {
                checkUpdateProgress();
            }
        });


    }

    public void checkUpdateProgress() {

        SharedPreferences settings = PreferenceManager.getDefaultSharedPreferences(this);
        SharedPreferences.Editor editor = settings.edit();

        if (!(goalWeightField.getText().toString().equals(""))) {

            goalWeight = Float.parseFloat(goalWeightField.getText().toString());
            editor.putFloat("goalWeight", goalWeight);
            editor.commit();

            float finalPercent = updateProgressBar(goalWeight, currentWeight);

            weightProgressBar.setProgress(Math.round(finalPercent));
            weightProgressValue.setVisibility(View.VISIBLE);
            String percent = String.format("%.1f",finalPercent);
            weightProgressValue.setText(percent + "% close to your goal!");

        }else{
            weightProgressValue.setVisibility(View.INVISIBLE);
        }

        if(!(goalBiscepLeftField.getText().toString().equals(""))){
            goalBiscepLeft = Float.parseFloat(goalBiscepLeftField.getText().toString());
            editor.putFloat("goalBiscepLeft",goalBiscepLeft);
            editor.commit();

            float finalPercent = updateProgressBar(goalBiscepLeft,currentBiscepLeft);

            biscepLeftProgressBar.setProgress(Math.round(finalPercent));
            biscepLeftProgressValue.setVisibility(View.VISIBLE);
            String percent = String.format("%.1f",finalPercent);
            biscepLeftProgressValue.setText(percent + "% close to your goal!");

        }else{
            biscepLeftProgressValue.setVisibility(View.INVISIBLE);
        }

        if(!(goalBiscepRightField.getText().toString().equals(""))){
            goalBiscepRight = Float.parseFloat(goalBiscepRightField.getText().toString());
            editor.putFloat("goalBiscepRight",goalBiscepRight);
            editor.commit();

            float finalPercent = updateProgressBar(goalBiscepRight,currentBiscepRight);

            biscepRightProgressBar.setProgress(Math.round(finalPercent));
            biscepRightProgressValue.setVisibility(View.VISIBLE);
            String percent = String.format("%.1f",finalPercent);
            biscepRightProgressValue.setText(percent + "% close to your goal!");

        }else{
            biscepRightProgressValue.setVisibility(View.INVISIBLE);
        }

        if (!(goalWaistField.getText().toString().equals(""))) {

            goalWaist = Float.parseFloat(goalWaistField.getText().toString());
            editor.putFloat("goalWaist",goalWaist);
            editor.commit();

            float finalPercent = updateProgressBar(goalWaist, currentWaist);

            waistProgressBar.setProgress(Math.round(finalPercent));
            waistProgressValue.setVisibility(View.VISIBLE);
            String percent = String.format("%.1f",finalPercent);
            waistProgressValue.setText(percent + "% close to your goal!");

        }else{
            waistProgressValue.setVisibility(View.INVISIBLE);
        }

        if (!(goalThighLeftField.getText().toString().equals(""))) {

            goalThighLeft = Float.parseFloat(goalThighLeftField.getText().toString());
            editor.putFloat("goalThighLeft",goalWaist);
            editor.commit();

            float finalPercent = updateProgressBar(goalThighLeft, currentThighLeft);

            thighLeftProgressBar.setProgress(Math.round(finalPercent));
            thighLeftProgressValue.setVisibility(View.VISIBLE);
            String percent = String.format("%.1f",finalPercent);
            thighLeftProgressValue.setText(percent + "% close to your goal!");

        }else{
            thighLeftProgressValue.setVisibility(View.INVISIBLE);
        }

        if (!(goalThighRightField.getText().toString().equals(""))) {

            goalThighRight = Float.parseFloat(goalThighRightField.getText().toString());
            editor.putFloat("goalThighRight",goalThighRight);
            editor.commit();

            float finalPercent = updateProgressBar(goalThighRight, currentThighRight);

            thighRightProgressBar.setProgress(Math.round(finalPercent));
            thighRightProgressValue.setVisibility(View.VISIBLE);
            String percent = String.format("%.1f",finalPercent);
            thighRightProgressValue.setText(percent + "% close to your goal!");

        }else{
            thighRightProgressValue.setVisibility(View.INVISIBLE);
        }

    }

    public float updateProgressBar(float goal,float initial) {

        float percentChange,percentFinal;


        if (goal > initial) {
            //person wants to gain
            percentChange = initial / goal;
            percentFinal = percentChange * 100;
            return percentFinal;

        } else {
            //person wants to lose
            percentChange = goal / initial;
            percentFinal = percentChange * 100;
            return percentFinal;

        }


    }


}
