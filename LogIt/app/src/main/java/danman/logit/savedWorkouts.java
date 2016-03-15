package danman.logit;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.ListView;

import java.util.ArrayList;

/**
 * Created by Raman on 3/13/2016.
 */
public class savedWorkouts extends AppCompatActivity {

    private MyAdapter aa;
    private ArrayList<ListElement> aList;
    workoutDatabaseDbHelper mDbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_savedworkouts);
        mDbHelper = new workoutDatabaseDbHelper(getApplicationContext());
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

        SQLiteDatabase dbRead = mDbHelper.getReadableDatabase();

        // Define a projection that specifies which columns from the database
        // you will actually use after this query.




        // How you want the results sorted in the resulting Cursor
        String sortOrder =
                workoutDatabase.gymWorkout.COLUMN_NAME_NAME + " DESC";

        Cursor c = dbRead.rawQuery("select DISTINCT uniqueWorkout from workout", null);

            if (c .moveToFirst()) {

                while (c.isAfterLast() == false) {
                    String name = c.getString(c.getColumnIndexOrThrow(workoutDatabase.gymWorkout.COLUMN_NAME_UNIQUE_WORKOUT));
                    if(name != null) {
                        aList.add(new ListElement(name, "info", aList));
                    }
                    c.moveToNext();
                }
            }
        c.close();


        aa.notifyDataSetChanged();

    }


}
