package danman.logit;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

/**
 * Created by Raman on 3/10/2016.
 */
public class createWorkout extends AppCompatActivity {

    SharedPreferences settings;

    String[] workouts = {"Select","Arms","Legs","Chest","Back","Abs","Cardio"};
    Spinner workoutItems;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_createworkout);

        workoutItems = (Spinner)findViewById(R.id.createWorkoutFocus);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_dropdown_item,workouts);
        workoutItems.setAdapter(adapter);


    }


    @Override
    public void onResume(){

        addListeners();
        super.onResume();
    }

    public void addListeners(){

        settings = PreferenceManager.getDefaultSharedPreferences(this);
        SharedPreferences.Editor editor = settings.edit();

        workoutItems.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            protected Adapter initializedAdapter=null;
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {

                if (initializedAdapter != parentView.getAdapter()) {
                    initializedAdapter = parentView.getAdapter();
                    return;
                }

                String selected = parentView.getItemAtPosition(position).toString();
                Log.i("WORKOUTINFO", selected);

                if(selected.equals("Select")){
                    //They didn't choose anything
                }else{

                    SharedPreferences.Editor editor = settings.edit();
                    editor.putString("createWorkoutItem",selected);

                }

            }
            @Override
            public void onNothingSelected(AdapterView<?> parentView) {

            }
        });

    }

}
