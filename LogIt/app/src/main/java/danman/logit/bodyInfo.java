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
import android.widget.Toast;

/**
 * Created by Raman on 3/7/2016.
 */
public class bodyInfo extends AppCompatActivity {

    int heightFeet,heightInches;
    float weight,biscepLeft,biscepRight,waist,BMI,thighLeft,thighRight;
    EditText heightFeetField;
    EditText heightInchesField;
    EditText weightField;
    EditText biscepLeftField;
    EditText biscepRightField;
    EditText waistField;
    EditText bmiField;
    EditText thighLeftField;
    EditText thighRightField;


    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bodyinfo);

        heightFeetField = (EditText)findViewById(R.id.feet);
        heightInchesField = (EditText)findViewById(R.id.inches);
        weightField =(EditText)findViewById(R.id.pounds);
        biscepLeftField = (EditText)findViewById(R.id.goalBiscepLeft);
        biscepRightField = (EditText)findViewById(R.id.goalBiscepRight);
        waistField = (EditText)findViewById(R.id.goalWaist);
        bmiField = (EditText)findViewById(R.id.BMI);
        thighLeftField = (EditText)findViewById(R.id.goalThighLeft);
        thighRightField = (EditText)findViewById(R.id.goalThighRight);

        addListeners();
    }

    @Override
    public void onResume(){

        SharedPreferences settings = PreferenceManager.getDefaultSharedPreferences(this);

        heightFeetField.setText(Integer.toString(settings.getInt("heightFeet",0)));
        heightInchesField.setText(Integer.toString(settings.getInt("heightInches",0)));
        weightField.setText(Float.toString(settings.getFloat("weight", 0)));
        biscepLeftField.setText(Float.toString(settings.getFloat("biscepLeft", 0)));
        biscepRightField.setText(Float.toString(settings.getFloat("biscepRight",0)));
        waistField.setText(Float.toString(settings.getFloat("waist",0)));
        bmiField.setText(Float.toString(settings.getFloat("BMI",0)));
        thighLeftField.setText(Float.toString(settings.getFloat("thighLeft", 0)));
        thighRightField.setText(Float.toString(settings.getFloat("thighRight", 0)));

        Log.i("RESUMEHEIGHTFEET",heightFeetField.getText().toString());

        super.onResume();
    }





    public void checkBmi(){
        if(!(heightFeetField.getText().toString().equals("")) &&
                !(heightInchesField.getText().toString().equals("")) && !(weightField.getText().toString().equals(""))){
            calculateBmi();
        }
        else{
            bmiField.setText("");
        }
    }


    public void calculateBmi(){

        float weight = Float.parseFloat(weightField.getText().toString());
        int heightFeet = Integer.parseInt(heightFeetField.getText().toString());
        int heightInch = Integer.parseInt(heightInchesField.getText().toString());
        heightInch = heightInch + (12*heightFeet);
        //ENABLE THIS BUTTON ONLY AFTER THE REQUIRED FIELDS ARE THERE
        Log.i("CALCULATEWEIGHT",weightField.getText().toString());
        Log.i("CALCULATHEIGHTFEET",heightFeetField.getText().toString());
        Log.i("CALCULATEHEIGHTINCHES", heightInchesField.getText().toString());
        float bmi = 0;
        bmi = weight * 703;
        heightInch = heightInch * heightInch;
        bmi = bmi / heightInch;
        String finalResult = String.format("%.2f", bmi);
        bmiField.setText(finalResult);

    }

    public void updateInfo(View v){

        //THIS NEEDS TO BE CALLED FOR THE BODY INFO TO BE STORED

        SharedPreferences settings = PreferenceManager.getDefaultSharedPreferences(this);




        if(heightFeetField.getText().toString().isEmpty() || heightInchesField.getText().toString().isEmpty() || weightField.getText().toString().isEmpty()
                || biscepLeftField.getText().toString().isEmpty() || biscepRightField.getText().toString().isEmpty() || waistField.getText().toString().isEmpty()
                || thighLeftField.getText().toString().isEmpty() || thighRightField.getText().toString().isEmpty()){

            //Its okay if BMI is empty because someone might need to calculate using the "Calculate BMI Update"

            Log.i("EMPTYFIELD","FIELD IS EMPTY");

        }else {

            SharedPreferences.Editor editor = settings.edit();

            heightFeet = Integer.parseInt(heightFeetField.getText().toString());
            heightInches = Integer.parseInt(heightInchesField.getText().toString());
            weight = Float.parseFloat(weightField.getText().toString());
            biscepLeft = Float.parseFloat(biscepLeftField.getText().toString());
            biscepRight = Float.parseFloat(biscepRightField.getText().toString());
            waist = Float.parseFloat(waistField.getText().toString());
            BMI = Float.parseFloat(bmiField.getText().toString());
            thighLeft = Float.parseFloat(thighLeftField.getText().toString());
            thighRight = Float.parseFloat(thighRightField.getText().toString());


            editor.putInt("heightFeet", heightFeet);
            editor.putInt("heightInches", heightInches);
            editor.putFloat("weight", weight);
            editor.putFloat("biscepLeft", biscepLeft);
            editor.putFloat("biscepRight", biscepRight);
            editor.putFloat("waist", waist);
            editor.putFloat("BMI", BMI);
            editor.putFloat("thighLeft",thighLeft);
            editor.putFloat("thighRight",thighRight);

            editor.commit();

            Log.i("HEIGHTINFOFEET", Integer.toString(heightFeet));
            Log.i("HEIGHTINFOINCHES", Integer.toString(heightInches));
            Log.i("WEIGHTINFO", Float.toString(weight));
            Log.i("BISCEPLEFTINFO", Float.toString(biscepLeft));
            Log.i("BISCEPRIGHTINFO", Float.toString(biscepRight));
            Log.i("WAISTINFO", Float.toString(waist));
            Log.i("BMIINFO", Float.toString(BMI));
            Log.i("THIGHLEFTINFO", Float.toString(thighLeft));
            Log.i("THIGHRIGHTINFO",Float.toString(thighRight));
        }

        Toast.makeText(this,"INFO HAS BEEN UPDATED",Toast.LENGTH_SHORT).show();

    }

    public void addListeners(){
        heightFeetField.addTextChangedListener(new TextWatcher() {

            @Override
            public void afterTextChanged(Editable s) {}

            @Override
            public void beforeTextChanged(CharSequence s, int start,
                                          int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start,
                                      int before, int count) {
                checkBmi();
            }
        });
        heightInchesField.addTextChangedListener(new TextWatcher() {

            @Override
            public void afterTextChanged(Editable s) {}

            @Override
            public void beforeTextChanged(CharSequence s, int start,
                                          int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start,
                                      int before, int count) {
                checkBmi();
            }
        });
        weightField.addTextChangedListener(new TextWatcher() {

            @Override
            public void afterTextChanged(Editable s) {}

            @Override
            public void beforeTextChanged(CharSequence s, int start,
                                          int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start,
                                      int before, int count) {
                checkBmi();
            }
        });
    }


}
