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
    @Override
    protected void onCreate(Bundle savedinstanceState){
        super.onCreate(savedinstanceState);
        setContentView(R.layout.activity_bodyinfo);

    }

    public void updateInfo(View v){
        SharedPreferences settings = PreferenceManager.getDefaultSharedPreferences(this);

        EditText feetField = (EditText)findViewById(R.id.feet);
        EditText inchesField = (EditText)findViewById(R.id.inches);
        EditText weightField =(EditText)findViewById(R.id.pounds);

        if(feetField.getText().toString().isEmpty() || inchesField.getText().toString().isEmpty() || weightField.getText().toString().isEmpty()){
            Log.i("EMPTYFIELD","FIELD IS EMPTY");
        }else {

            SharedPreferences.Editor editor = settings.edit();

            int feet = Integer.parseInt(feetField.getText().toString());
            int inches = Integer.parseInt(inchesField.getText().toString());
            float weight = Float.parseFloat(weightField.getText().toString());

            editor.putInt("heightFeet", feet);
            editor.putInt("heightInches", inches);
            editor.putFloat("weight", weight);

            Log.i("HEIGHTINFOFEET", Integer.toString(feet));
            Log.i("HEIGHTINFOINCHES", Integer.toString(inches));
            Log.i("WEIGHTINFO", Float.toString(weight));
        }

    }
}
