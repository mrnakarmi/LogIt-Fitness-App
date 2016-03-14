package danman.logit;

import java.util.ArrayList;

/**
 * Created by Raman on 3/13/2016.
 */
public class ListElement {

    ListElement(){}

    ListElement(String tl, String bl, ArrayList<ListElement> aList){
        workoutName = tl;
        workoutInfo = bl;
        a = aList;
    }

    public String workoutName, workoutInfo;
    ArrayList<ListElement> a;

}
