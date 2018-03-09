package hitro.a5x5assistant;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

//firebase database imports
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;


import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;


public class WorkoutBActivity extends BaseActivity {

    private static final String TAG = "WorkoutBActivity";
    private double squatNew, ohpNew, dlNew;
    private boolean squatDone, ohpDone, dlDone;
    private TextView txtSquat, txtOHP, txtDL;
    private Button a1, a2, a3, a4, a5, b1, b2, b3, b4, b5, c1, fin;
    private Drawable round, roundFilled;
    private SimpleDateFormat dateFormat;
    private TextView[] txtUnits;
    private View[] txtValues;
    private DatabaseReference dbHistory;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_workout_b);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        roundFilled = getResources().getDrawable(R.drawable.button_round_filled);
        round = getResources().getDrawable(R.drawable.button_round);
        a1 = findViewById(R.id.btnSq1b);
        a2 = findViewById(R.id.btnSq2b);
        a3 = findViewById(R.id.btnSq3b);
        a4 = findViewById(R.id.btnSq4b);
        a5 = findViewById(R.id.btnSq5b);
        b1 = findViewById(R.id.btnOHP1);
        b2 = findViewById(R.id.btnOHP2);
        b3 = findViewById(R.id.btnOHP3);
        b4 = findViewById(R.id.btnOHP4);
        b5 = findViewById(R.id.btnOHP5);
        c1 = findViewById(R.id.btnDL1);
        fin = findViewById(R.id.btnFin);

        //textview inits
        txtUnits = new TextView[] {findViewById(R.id.txtUnits1b), findViewById(R.id.txtUnits2b), findViewById(R.id.txtUnits3b)};
        txtSquat = findViewById(R.id.txtSquatWeight);
        txtOHP = findViewById(R.id.txtOHPweight);
        txtDL = findViewById(R.id.txtDLweight);
        txtValues = new View[] {txtSquat, txtOHP, txtDL};
        squatDone = false;
        ohpDone = false;
        dlDone = false;

        // read weights from db and set the respective textviews.
        readAndSetValuesDB(txtValues, txtUnits, "B");

        //squat stuff
        final RepButtonClickHandler a1CL = new RepButtonClickHandler(this, a1);
        final RepButtonClickHandler a2CL = new RepButtonClickHandler(this, a2);
        final RepButtonClickHandler a3CL = new RepButtonClickHandler(this, a3);
        final RepButtonClickHandler a4CL = new RepButtonClickHandler(this, a4);
        final RepButtonClickHandler a5CL = new RepButtonClickHandler(this, a5);
        a1.setOnClickListener(a1CL);
        a2.setOnClickListener(a2CL);
        a3.setOnClickListener(a3CL);
        a4.setOnClickListener(a4CL);
        a5.setOnClickListener(a5CL);

        //OHP stuff
        final RepButtonClickHandler b1CL = new RepButtonClickHandler(this, b1);
        final RepButtonClickHandler b2CL = new RepButtonClickHandler(this, b2);
        final RepButtonClickHandler b3CL = new RepButtonClickHandler(this, b3);
        final RepButtonClickHandler b4CL = new RepButtonClickHandler(this, b4);
        final RepButtonClickHandler b5CL = new RepButtonClickHandler(this, b5);
        b1.setOnClickListener(b1CL);
        b2.setOnClickListener(b2CL);
        b3.setOnClickListener(b3CL);
        b4.setOnClickListener(b4CL);
        b5.setOnClickListener(b5CL);

        //DL stuff
        final RepButtonClickHandler c1CL = new RepButtonClickHandler(this, c1);
        c1.setOnClickListener(c1CL);

        fin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                squatNew = Double.valueOf(txtSquat.getText().toString());
                ohpNew = Double.valueOf(txtOHP.getText().toString());
                dlNew = Double.valueOf(txtDL.getText().toString());

                if (units.equals(getString(R.string.kg))) {
                    squatNew = Math.round(squatNew * .44) * 5;
                    ohpNew = Math.round(ohpNew * .44) * 5;
                    dlNew = Math.round(dlNew * .44) * 5;
                }

                Drawable bg = c1.getBackground();
                Log.i(TAG, bg.toString());

                //check if all sets of the 3 exercises were completed successfully
                if (a1CL.getClicks() %6 == 1 && a2CL.getClicks() %6 == 1 && a3CL.getClicks() %6 == 1
                        && a4CL.getClicks() %6 == 1 && a5CL.getClicks() %6 == 1) {

                    squatDone = true;
                    squatNew += 5;
                }

                if (b1CL.getClicks() %6 == 1 && b2CL.getClicks() %6 == 1 && b3CL.getClicks() %6 == 1
                        && b4CL.getClicks() %6 == 1 && b5CL.getClicks() %6 == 1) {

                    ohpDone = true;
                    ohpNew += 5;
                }

                if (c1CL.getClicks() %6 == 1) {
                    dlDone = true;
                    dlNew += 10;
                }

                // save workout results to db
                dateFormat = new SimpleDateFormat("yyyy-MMM-dd HH:mm:ss");
                String date = dateFormat.format(new Date());
                dbHistory =  FirebaseDatabase.getInstance().getReference("history/" + uid);
                dbHistory.child(date).setValue(
                        new HistoryB(date, squatNew, ohpNew, dlNew,
                                squatDone, ohpDone, dlDone)
                );

                Map<String, Object> profileUpdate = new HashMap<>();
                profileUpdate.put("squat", squatNew);
                profileUpdate.put("ohp", ohpNew);
                profileUpdate.put("dl", dlNew);
                dbProfile.updateChildren(profileUpdate);

                startActivity(new Intent(getApplicationContext(), HomeActivity.class));
            }
        });

    }

}




