package danman.logit;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

public class HomeScreen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
    }

    public void bodyInfo(View v){
        Log.i("MYAPP", "I AM IN QUICK ADD");

        Intent intent = new Intent(this,bodyInfo.class);
        startActivity(intent);
    }

    public void bodyStats(View v){
        Log.i("MYAPP", "I AM IN BODY STATS");
    }

    public void createWorkout(View v){
        Log.i("MYAPP", "I AM IN CREATE WORKOUT");

        Intent intent = new Intent (this,createWorkout.class);
        startActivity(intent);
    }

    public void milestone(View v) {
        Log.i("MYAPP", "I AM IN MILESTONE");

        Intent intent = new Intent(this,milestone.class);
        startActivity(intent);

    }

    public void calendar(View v){
        Log.i("MYAPP","I AM IN CALENDAR");
    }

    public void quickWorkout(View v){
        Log.i("MYAPP","I AM IN QUICKWORKOUT");

        Intent intent = new Intent(this,quickWorkout.class);
        startActivity(intent);
    }

}
