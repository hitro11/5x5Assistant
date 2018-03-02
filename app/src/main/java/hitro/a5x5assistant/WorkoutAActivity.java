package hitro.a5x5assistant;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import java.text.SimpleDateFormat;
import java.util.Date;


public class WorkoutAActivity extends BaseActivity {

    private static final String TAG = "WorkoutAActivity";
    private CollectionReference dbHistory;
    private DocumentReference dbProfile;
    private String uid, name, units;
    private boolean squatDone, benchDone, rowDone;
    private double squat, bench, row, squatNew, benchNew, rowNew, body;
    private Drawable round, roundFilled;
    private Button a1, a2, a3, a4, a5, b1, b2, b3 ,b4, b5, c1, c2, c3, c4, c5, fin;
    private TextView txtSquat, txtBench, txtRow, txtUnits1a, txtUnits2a, txtUnits3a;
    private SimpleDateFormat dateFormat;

    //click counters
    private static int a1Click = 0, a2Click = 0, a3Click = 0, a4Click = 0, a5Click = 0,
            b1Click = 0, b2Click = 0, b3Click = 0, b4Click = 0, b5Click = 0,
            c1Click = 0, c2Click = 0, c3Click = 0, c4Click = 0, c5Click = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_workout_a);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        roundFilled = ContextCompat.getDrawable(this, R.drawable.button_round_filled);
        round = ContextCompat.getDrawable(this, R.drawable.button_round);
        a1 = findViewById(R.id.btnSq1a);
        a2 = findViewById(R.id.btnSq2a);
        a3 = findViewById(R.id.btnSq3a);
        a4 = findViewById(R.id.btnSq4a);
        a5 = findViewById(R.id.btnSq5a);
        b1 = findViewById(R.id.btnBP1);
        b2 = findViewById(R.id.btnBP2);
        b3 = findViewById(R.id.btnBP3);
        b4 = findViewById(R.id.btnBP4);
        b5 = findViewById(R.id.btnBP5);
        c1 = findViewById(R.id.btnRow1);
        c2 = findViewById(R.id.btnRow2);
        c3 = findViewById(R.id.btnRow3);
        c4 = findViewById(R.id.btnRow4);
        c5 = findViewById(R.id.btnRow5);
        fin = findViewById(R.id.btnFin);

        //textview inits
        txtSquat = findViewById(R.id.txtSqWeight);
        txtBench = findViewById(R.id.txtBenchWeight);
        txtRow = findViewById(R.id.txtRowWeight);
        txtUnits1a = findViewById(R.id.txtUnits1a);
        txtUnits2a = findViewById(R.id.txtUnits2a);
        txtUnits3a = findViewById(R.id.txtUnits3a);

        squatDone = false;
        benchDone = false;
        rowDone = false;

        //firebase user auth refs
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        try {
            uid = user.getUid();
        } catch (NullPointerException e) {
            startActivity(new Intent(WorkoutAActivity.this, LoginActivity.class));
        }

        //firebase user auth refs
        user = FirebaseAuth.getInstance().getCurrentUser();
        dbProfile =  FirebaseFirestore.getInstance().document("users/" + uid);
        dbHistory =  FirebaseFirestore.getInstance().collection("users/" + uid + "/history");

        /* set appropriate units */
        units = sharedPref.getString("units", "");
        if(units.equals("kg")) {
            txtUnits1a.setText(R.string.kg);
            txtUnits2a.setText(R.string.kg);
            txtUnits3a.setText(R.string.kg);
        }

        // read weights from profile
        dbProfile.get()
                .addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                        if (task.isSuccessful() && task.getResult().exists()) {
                            Profile profile = task.getResult().toObject(Profile.class);
                            squat = profile.getSquat();
                            bench = profile.getBench();
                            row = profile.getRow();
                        }
                    }
                });

        // set weights for current workout
        if (units.equals("lb")) {
            txtSquat.setText(String.valueOf(squat));
            txtBench.setText(String.valueOf(bench));
            txtRow.setText(String.valueOf(row));
        }
        else {
            txtSquat.setText(String.valueOf(Math.round((squat / 2.2) / 2.5) * 2.5));
            txtBench.setText(String.valueOf(Math.round((bench / 2.2) / 2.5) * 2.5));
            txtRow.setText(String.valueOf(Math.round((row / 2.2) / 2.5) * 2.5));
        }

        //squat rep counters click handlers
        a1.setOnClickListener(new RepCounterClick(this, a1, a1Click));
        a2.setOnClickListener(new RepCounterClick(this, a2, a2Click));
        a3.setOnClickListener(new RepCounterClick(this, a3, a3Click));
        a4.setOnClickListener(new RepCounterClick(this, a4, a4Click));
        a5.setOnClickListener(new RepCounterClick(this, a5, a5Click));

        //bench rep counters click handlers
        b1.setOnClickListener(new RepCounterClick(this, b1, b1Click));
        b2.setOnClickListener(new RepCounterClick(this, b2, b2Click));
        b3.setOnClickListener(new RepCounterClick(this, b3, b3Click));
        b4.setOnClickListener(new RepCounterClick(this, b4, b4Click));
        b5.setOnClickListener(new RepCounterClick(this, b5, b5Click));

        //row rep counters click handlers
        c1.setOnClickListener(new RepCounterClick(this, c1, c1Click));
        c2.setOnClickListener(new RepCounterClick(this, c2, c2Click));
        c3.setOnClickListener(new RepCounterClick(this, c3, c3Click));
        c4.setOnClickListener(new RepCounterClick(this, c4, c4Click));
        c5.setOnClickListener(new RepCounterClick(this, c5, c5Click));

        // workout complete button click handler
        fin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                squatNew = squat;
                benchNew = bench;
                rowNew = row;

                //check if all sets of the 3 exercises were completed successfully
                if (a1.getBackground() == roundFilled && a2.getBackground() == roundFilled &&
                        a3.getBackground() == roundFilled && a4.getBackground() == roundFilled
                        && a5.getBackground() == roundFilled) {

                    squatDone = true;
                    squatNew = squat + 5;
                }

                if (b1.getBackground() == roundFilled && b2.getBackground() == roundFilled &&
                        b3.getBackground() == roundFilled && b4.getBackground() == roundFilled
                        && b5.getBackground() == roundFilled) {

                    benchDone = true;
                    benchNew = bench + 5;
                }

                if (c1.getBackground() == roundFilled && c2.getBackground() == roundFilled &&
                        c3.getBackground() == roundFilled && c4.getBackground() == roundFilled &&
                        c5.getBackground() == roundFilled) {

                    rowDone = true;
                    rowNew = row + 5;
                }

                // save workout to db
                dateFormat = new SimpleDateFormat("yyyy-MMM-dd HH:mm:ss");
                String date = dateFormat.format(new Date());
                dbHistory.add(
                        new HistoryA(date, squat, bench, row,
                        squatDone, benchDone, rowDone)
                );

                dbProfile.update("squat", squat, "bench", bench, "row", row );

                //redirects to home activity
                startActivity(new Intent(WorkoutAActivity.this, HomeActivity.class));
            }
        });
    }
}

class RepCounterClick implements View.OnClickListener {

    private Button button;
    private int clicks;
    private Context context;


    public RepCounterClick(Context context, Button button, int clicks) {
        this.button = button;
        this.clicks = clicks;
        this.context = context;
    }


    @Override
    public void onClick(View v)
    {
        Drawable round = ContextCompat.getDrawable(context, R.drawable.button_round);
        Drawable roundFilled = ContextCompat.getDrawable(context, R.drawable.button_round_filled);

        clicks++;
        if (clicks % 6 == 1) {
            button.setBackground(roundFilled);
            button.setText("");
        }
        if (clicks % 6 == 2) {
            button.setBackground(round);
            button.setText("4");
        }
        if (clicks % 6 == 3) button.setText("3");
        if (clicks % 6 == 4) button.setText("2");
        if (clicks % 6 == 5) button.setText("1");
        if (clicks % 6 == 0) button.setText("");
    }
}