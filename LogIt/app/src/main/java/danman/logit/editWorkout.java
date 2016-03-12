package danman.logit;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

/**
 * Created by Raman on 3/12/2016.
 */
public class editWorkout extends AppCompatActivity {

    SharedPreferences settings;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editworkout);
        getSupportActionBar().setDisplayHomeAsUpEnabled(false);

        settings = PreferenceManager.getDefaultSharedPreferences(this);
      boolean bob =  settings.getBoolean("workout1",false);
        Log.i("CHECKINGINFO", Boolean.toString(bob));

    }

}