package hitro.a5x5assistant;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

/**
 * Created by rohit on 1/3/2017.
 */

public class ProgressHolder extends RecyclerView.ViewHolder {

    TextView txtWorkout, txtDate,
            txtSquatW, txtBenchW, txtRowW, txtOHPW, txtDLW,
            txtSquatDone, txtBenchDone, txtRowDone, txtOHPDone, txtDLDone;

    //default constructor
    public ProgressHolder (View view) {
        super(view);

        txtDate = (TextView)view.findViewById(R.id.txtDate);
        txtWorkout = (TextView)view.findViewById(R.id.txtWorkout);

        txtSquatW = (TextView)view.findViewById(R.id.txtSquatW);
        txtBenchW = (TextView)view.findViewById(R.id.txtBenchW);
        txtRowW = (TextView)view.findViewById(R.id.txtRowW);
        txtOHPW = (TextView)view.findViewById(R.id.txtOHPW);
        txtDLW = (TextView)view.findViewById(R.id.txtDLW);

        txtSquatDone = (TextView)view.findViewById(R.id.txtSquatDone);
        txtBenchDone = (TextView)view.findViewById(R.id.txtBenchDone);
        txtRowDone = (TextView)view.findViewById(R.id.txtRowDone);
        txtOHPDone = (TextView)view.findViewById(R.id.txtOHPDone);
        txtDLDone = (TextView)view.findViewById(R.id.txtDLDone);
    }





}
