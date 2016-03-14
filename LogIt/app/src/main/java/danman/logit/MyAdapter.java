package danman.logit;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

/**
 * Created by Raman on 3/13/2016.
 */
public class MyAdapter extends ArrayAdapter<ListElement> {

    int resource;
    Context context;

    public MyAdapter(Context _context,int _resource, List<ListElement> items){
        super(_context,_resource,items);
        resource = _resource;
        context = _context;

    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent){
        LinearLayout newView;

        ListElement w = getItem(position);

        //inflate a new view if necessary.
        if(convertView == null ){
            newView = new LinearLayout(getContext());
            String inflater = Context.LAYOUT_INFLATER_SERVICE;
            LayoutInflater vi = (LayoutInflater) getContext().getSystemService(inflater);
            vi.inflate(resource,newView,true);
        }else{
            newView = (LinearLayout)convertView;
        }

        //Fills in the view
        TextView workoutName = (TextView) newView.findViewById(R.id.itemText);
        Button b = (Button) newView.findViewById(R.id.itemInfo);

        workoutName.setText(w.workoutName);

        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v){

                //do something when the info button is clicked
                //show the excercises, their sets, reps and weight;

                Toast.makeText(context,"Hello there",Toast.LENGTH_SHORT).show();
                //Toast.makeText(this, "INFO HAS BEEN UPDATED", Toast.LENGTH_SHORT).show();
            }
        });

        return newView;
    }


}
