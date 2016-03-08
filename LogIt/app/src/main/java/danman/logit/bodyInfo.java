package danman.logit;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

/**
 * Created by Raman on 3/7/2016.
 */
public class bodyInfo extends AppCompatActivity {

    int heightFeet,heightInches;
    float weight,biscepLeft,biscepRight,waist,BMI;


    @Override
    protected void onCreate(Bundle savedinstanceState){
        super.onCreate(savedinstanceState);
        setContentView(R.layout.activity_bodyinfo);
    }



    public void calculateBmi(View v){

        //ENABLE THIS BUTTON ONLY AFTER THE REQUIRED FIELDS ARE THERE
        Log.i("CALCULATEWEIGHT",Float.toString(weight));
        Log.i("CALCULATHEIGHTFEET",Integer.toString(heightFeet));
        Log.i("CALCULATEHEIGHTINCHES",Integer.toString(heightInches));
        Log.i("CALCULATEBMI",Float.toString(BMI));


    }

    public void updateInfo(View v){

        //THIS NEEDS TO BE CALLED FOR THE BODY INFO TO BE STORED

        SharedPreferences settings = PreferenceManager.getDefaultSharedPreferences(this);

        EditText heightFeetField = (EditText)findViewById(R.id.feet);
        EditText heightInchesField = (EditText)findViewById(R.id.inches);
        EditText weightField =(EditText)findViewById(R.id.pounds);
        EditText biscepLeftField = (EditText)findViewById(R.id.biscepLeft);
        EditText biscepRightField = (EditText)findViewById(R.id.biscepRight);
        EditText waistField = (EditText)findViewById(R.id.waist);
        EditText bmiField = (EditText)findViewById(R.id.BMI);


        if(heightFeetField.getText().toString().isEmpty() || heightInchesField.getText().toString().isEmpty() || weightField.getText().toString().isEmpty()
                || biscepLeftField.getText().toString().isEmpty() || biscepRightField.getText().toString().isEmpty() || waistField.getText().toString().isEmpty()){

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

            editor.putInt("heightFeet", heightFeet);
            editor.putInt("heightInches", heightInches);
            editor.putFloat("weight", weight);
            editor.putFloat("biscepLeft", biscepLeft);
            editor.putFloat("biscepRight", biscepRight);
            editor.putFloat("waist",waist);
            editor.putFloat("BMI", BMI);

            Log.i("HEIGHTINFOFEET", Integer.toString(heightFeet));
            Log.i("HEIGHTINFOINCHES", Integer.toString(heightInches));
            Log.i("WEIGHTINFO", Float.toString(weight));
            Log.i("BISCEPLEFTINFO", Float.toString(biscepLeft));
            Log.i("BISCEPRIGHTINFO", Float.toString(biscepRight));
            Log.i("WAISTINFO", Float.toString(waist));
            Log.i("BMIINFO", Float.toString(BMI));
        }

    }
}
