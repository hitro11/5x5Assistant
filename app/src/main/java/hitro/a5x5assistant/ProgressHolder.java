package hitro.a5x5assistant;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

public class ProgressHolder extends RecyclerView.ViewHolder {

    private TextView txtWorkout, txtDate,
            txtEx1, txtEx2, txtEx3,
            txtEx1WProg, txtEx2WProg, txtEx3WProg,
            txtUnit1Prog, txtUnit2Prog, txtUnit3Prog,
            txtEx1DoneProg, txtEx2DoneProg, txtEx3DoneProg;

    //default constructor
    public ProgressHolder (View view) {
        super(view);

        txtDate = view.findViewById(R.id.txtDate);
        txtWorkout = view.findViewById(R.id.txtWorkout);

        txtEx1 = view.findViewById(R.id.txtEx1Prog);
        txtEx2 = view.findViewById(R.id.txtEx2Prog);
        txtEx3 = view.findViewById(R.id.txtEx3Prog);

        txtEx1WProg = view.findViewById(R.id.txtEx1WProg);
        txtEx2WProg = view.findViewById(R.id.txtEx2WProg);
        txtEx3WProg = view.findViewById(R.id.txtEx3WProg);

        txtUnit1Prog = view.findViewById(R.id.txtUnitProg1);
        txtUnit2Prog = view.findViewById(R.id.txtUnitProg2);
        txtUnit3Prog = view.findViewById(R.id.txtUnitProg3);

        txtEx1DoneProg = view.findViewById(R.id.txtEx1DoneProg);
        txtEx2DoneProg = view.findViewById(R.id.txtEx2DoneProg);
        txtEx3DoneProg = view.findViewById(R.id.txtEx3DoneProg);
    }

    public TextView getTxtWorkout() {
        return txtWorkout;
    }

    public TextView getTxtDate() {
        return txtDate;
    }

    public TextView getTxtEx1() {
        return txtEx1;
    }

    public TextView getTxtEx2() {
        return txtEx2;
    }

    public TextView getTxtEx3() {
        return txtEx3;
    }

    public TextView getTxtEx1WProg() {
        return txtEx1WProg;
    }

    public TextView getTxtEx2WProg() {
        return txtEx2WProg;
    }

    public TextView getTxtEx3WProg() {
        return txtEx3WProg;
    }

    public TextView getTxtUnit1Prog() {
        return txtUnit1Prog;
    }

    public TextView getTxtUnit2Prog() {
        return txtUnit2Prog;
    }

    public TextView getTxtUnit3Prog() {
        return txtUnit3Prog;
    }

    public TextView getTxtEx1DoneProg() {
        return txtEx1DoneProg;
    }

    public TextView getTxtEx2DoneProg() {
        return txtEx2DoneProg;
    }

    public TextView getTxtEx3DoneProg() {
        return txtEx3DoneProg;
    }
}
