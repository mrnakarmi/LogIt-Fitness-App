package danman.logit;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

/**
 * Created by Raman on 3/7/2016.
 */
public class quickWorkout extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedinstanceState){
        super.onCreate(savedinstanceState);
        setContentView(R.layout.activity_quickworkout);
    }

    public void timeStart (View v){

        TextView time = (TextView)findViewById(R.id.minutes);

        if(time.getText().toString().isEmpty()){
            Log.i("MYAPP","minute field is empty");

        }else {

            long minutes = Long.parseLong(time.getText().toString());
            Log.i("MYAPPMINUTES",time.getText().toString());

            long seconds = minutes *60;
            long milli = seconds *1000;



            new CountDownTimer(milli, 1000) {
                TextView mTextField = (TextView) findViewById(R.id.timer);
                public void onTick(long millisUntilFinished) {
                    mTextField.setText("seconds remaining: " + millisUntilFinished / 1000+" secs");
                }

                public void onFinish() {
                    mTextField.setText("done!");
                }
            }.start();
        }
    }
}
