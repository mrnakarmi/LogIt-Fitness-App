package danman.logit;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;

import java.util.ArrayList;

/**
 * Created by Raman on 3/13/2016.
 */
public class savedWorkouts extends AppCompatActivity {

    private MyAdapter aa;
    private ArrayList<ListElement> aList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_savedworkouts);
    }


    @Override
    public void onResume(){

        aList = new ArrayList<ListElement>();
        aa = new MyAdapter(this,R.layout.list_element,aList);

        ListView myListView = (ListView)findViewById(R.id.listView);
        myListView.setAdapter(aa);
        aa.notifyDataSetChanged();

        displayWorkouts();

        super.onResume();
    }

    private void displayWorkouts(){

        aList.clear();

        //probably have to send in workoutInfo as well I think
        for(int i = 1; i < 10; i++) {
            String workoutName = "This is workout number: "+i;
            aList.add(new ListElement(workoutName, "info",aList));
        }

        aa.notifyDataSetChanged();

    }


}
