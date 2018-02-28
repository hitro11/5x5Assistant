package hitro.a5x5assistant;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

//firebase database imports
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;


import java.text.SimpleDateFormat;
import java.util.Date;


public class WorkoutBActivity extends BaseActivity {

    private static final String TAG = "WorkoutBActivity";
    private String name;
    private double squatWeight, ohpWeight, dlWeight;
    private double bodyWeight, benchWeight, rowWeight;
    private double squatWeightNew, ohpWeightNew, dlWeightNew;
    private String squatDone, ohpDone, dlDone;
    private String units;
    private TextView txtSquat, txtOHP, txtDL, txtUnits1b, txtUnits2b, txtUnits3b;
    private Button a1, a2, a3, a4, a5, b1, b2, b3, b4, b5, c1, fin;
    private Drawable round, roundFilled;
    private FirebaseUser user;
    private String uid;

    private SimpleDateFormat dateFormat;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_workout_b);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        units = sharedPref.getString("units", "");

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
        roundFilled = getResources().getDrawable(R.drawable.button_round_filled);
        round = getResources().getDrawable(R.drawable.button_round);

        //textview inits
        txtSquat = findViewById(R.id.txtSquatWeight);
        txtOHP = findViewById(R.id.txtOHPweight);
        txtDL = findViewById(R.id.txtDLweight);
        txtUnits1b = findViewById(R.id.txtUnits1b);
        txtUnits2b = findViewById(R.id.txtUnits2b);
        txtUnits3b = findViewById(R.id.txtUnits3b);

        squatDone = "no";
        ohpDone = "no";
        dlDone = "no";

        //firebase user auth refs
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        try {
            uid = user.getUid();
        } catch (NullPointerException e) {
            startActivity(new Intent(WorkoutBActivity.this, LoginActivity.class));
        }



        // set weights for this workout
        if (units.equals("lb")) {
            txtSquat.setText(String.valueOf(squatWeight));
            txtOHP.setText(String.valueOf(ohpWeight));
            txtDL.setText(String.valueOf(dlWeight));
        } else {
            txtSquat.setText(String.valueOf(Math.round((squatWeight / 2.2) / 2.5) * 2.5));
            txtOHP.setText(String.valueOf(Math.round((ohpWeight / 2.2) / 2.5) * 2.5));
            txtDL.setText(String.valueOf(Math.round((dlWeight / 2.2) / 2.5) * 2.5));
        }

        fin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                squatWeightNew = squatWeight;
                ohpWeightNew = ohpWeight;
                dlWeightNew = dlWeight;

                //check if all sets of the 3 exercises were completed successfully
                if (a1.getBackground() == roundFilled && a2.getBackground() == roundFilled &&
                        a3.getBackground() == roundFilled && a4.getBackground() == roundFilled
                        && a5.getBackground() == roundFilled) {

                    squatDone = "yes";
                    squatWeightNew = squatWeight + 5;
                }

                if (b1.getBackground() == roundFilled && b2.getBackground() == roundFilled &&
                        b3.getBackground() == roundFilled && b4.getBackground() == roundFilled
                        && b5.getBackground() == roundFilled) {

                    ohpDone = "yes";
                    ohpWeightNew = ohpWeight + 5;
                }

                if (c1.getBackground() == roundFilled) {

                    dlDone = "yes";
                    dlWeightNew = dlWeight + 10;
                }


            }
        });

        /*------------------------- workout button click handlers --------------------------------*/

        //click counters
        int a1Click = 0, a2Click = 0, a3Click = 0, a4Click = 0, a5Click = 0,
                b1Click = 0, b2Click = 0, b3Click = 0, b4Click = 0, b5Click = 0,
                c1Click = 0;

        //squat stuff
        a1.setOnClickListener(new RepCounterClick(this, a1, a1Click));
        a2.setOnClickListener(new RepCounterClick(this, a2, a2Click));
        a3.setOnClickListener(new RepCounterClick(this, a3, a3Click));
        a4.setOnClickListener(new RepCounterClick(this, a4, a4Click));
        a5.setOnClickListener(new RepCounterClick(this, a5, a5Click));


        //OHP stuff
        b1.setOnClickListener(new RepCounterClick(this, b1, b1Click));
        b2.setOnClickListener(new RepCounterClick(this, b2, b2Click));
        b3.setOnClickListener(new RepCounterClick(this, b3, b3Click));
        b4.setOnClickListener(new RepCounterClick(this, b4, b4Click));
        b5.setOnClickListener(new RepCounterClick(this, b5, b5Click));

        //DL stuff
        c1.setOnClickListener(new RepCounterClick(this, c1, c1Click));
    }
}




