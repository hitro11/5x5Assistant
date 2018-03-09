package hitro.a5x5assistant;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class WorkoutAActivity extends BaseActivity {

    private static final String TAG = "WorkoutAActivity";
    private DatabaseReference dbHistory;
    private boolean squatDone, benchDone, rowDone;
    private double squatNew, benchNew, rowNew;
    private Button a1, a2, a3, a4, a5, b1, b2, b3 ,b4, b5, c1, c2, c3, c4, c5, fin;
    private TextView txtSquat, txtBench, txtRow;
    private View[] txtValues;
    private SimpleDateFormat dateFormat;
    private TextView[] txtUnits;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_workout_a);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        final Drawable roundFilled = ContextCompat.getDrawable(this, R.drawable.button_round_filled);
        a1 = findViewById(R.id.btnSquat1a);
        a2 = findViewById(R.id.btnSquat2a);
        a3 = findViewById(R.id.btnSquat3a);
        a4 = findViewById(R.id.btnSquat4a);
        a5 = findViewById(R.id.btnSquat5a);
        b1 = findViewById(R.id.btnBench1);
        b2 = findViewById(R.id.btnBench2);
        b3 = findViewById(R.id.btnBench3);
        b4 = findViewById(R.id.btnBench4);
        b5 = findViewById(R.id.btnBench5);
        c1 = findViewById(R.id.btnRow1);
        c2 = findViewById(R.id.btnRow2);
        c3 = findViewById(R.id.btnRow3);
        c4 = findViewById(R.id.btnRow4);
        c5 = findViewById(R.id.btnRow5);
        fin = findViewById(R.id.btnFin);

        //textview inits
        txtUnits = new TextView[] {findViewById(R.id.txtUnits1a), findViewById(R.id.txtUnits2a), findViewById(R.id.txtUnits3a)};
        txtSquat = findViewById(R.id.txtSqWeight);
        txtBench = findViewById(R.id.txtBenchWeight);
        txtRow = findViewById(R.id.txtRowWeight);
        txtValues = new View[] {txtSquat, txtBench, txtRow};
        squatDone = false;
        benchDone = false;
        rowDone = false;

        // read weights from db and set the respective textviews.
        readAndSetValuesDB(txtValues, txtUnits, "A");


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
        final RepButtonClickHandler c2CL = new RepButtonClickHandler(this, c2);
        final RepButtonClickHandler c3CL = new RepButtonClickHandler(this, c3);
        final RepButtonClickHandler c4CL = new RepButtonClickHandler(this, c4);
        final RepButtonClickHandler c5CL = new RepButtonClickHandler(this, c5);
        c1.setOnClickListener(c1CL);
        c2.setOnClickListener(c2CL);
        c3.setOnClickListener(c3CL);
        c4.setOnClickListener(c4CL);
        c5.setOnClickListener(c5CL);


        // workout complete button click handler
        fin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                squatNew = Double.valueOf(txtSquat.getText().toString());
                benchNew = Double.valueOf(txtBench.getText().toString());
                rowNew = Double.valueOf(txtRow.getText().toString());

                if (units.equals(getString(R.string.kg))) {
                    squatNew = Math.round(squatNew * .44) * 5;
                    benchNew = Math.round(squatNew * .44) * 5;
                    rowNew = Math.round(squatNew * .44) * 5;
                }

                //check if all sets of the 3 exercises were completed successfully
                if (a1CL.getClicks() %6 == 1 && a2CL.getClicks() %6 == 1 && a3CL.getClicks() %6 == 1
                        && a4CL.getClicks() %6 == 1 && a5CL.getClicks() %6 == 1) {

                    squatDone = true;
                    squatNew += 5;
                }

                if (b1CL.getClicks() %6 == 1 && b2CL.getClicks() %6 == 1 && b3CL.getClicks() %6 == 1
                        && b4CL.getClicks() %6 == 1 && b5CL.getClicks() %6 == 1) {

                    benchDone = true;
                    benchNew += 5;
                }

                if (c1CL.getClicks() %6 == 1 && c2CL.getClicks() %6 == 1 && c3CL.getClicks() %6 == 1
                        && c4CL.getClicks() %6 == 1 && c5CL.getClicks() %6 == 1) {

                    rowDone = true;
                    rowNew += 5;
                }


                // save workout results to db
                dateFormat = new SimpleDateFormat("yyyy-MMM-dd HH:mm:ss");
                String date = dateFormat.format(new Date());
                dbHistory =  FirebaseDatabase.getInstance().getReference("history/" + uid);
                dbHistory.child(date).setValue(
                        new HistoryA(date, squatNew, benchNew, rowNew,
                        squatDone, benchDone, rowDone)
                );

                Map<String, Object> profileUpdate = new HashMap<>();
                profileUpdate.put("squat", squatNew);
                profileUpdate.put("bench", benchNew);
                profileUpdate.put("row", rowNew);
                dbProfile.updateChildren(profileUpdate);

                Toast.makeText(getApplicationContext(), "Workout saved",
                        Toast.LENGTH_LONG).show();

                startActivity(new Intent(WorkoutAActivity.this, HomeActivity.class));
            }
        });
    }
}

class RepButtonClickHandler implements View.OnClickListener {

    private Button button;
    private int clicks;
    private Context context;
    private Drawable round, roundFilled;

    RepButtonClickHandler(Context context, Button button) {
        this.context = context;
        this.button = button;
        this.clicks = 0;
    }

    @Override
    public void onClick(View v)
    {
        round = ContextCompat.getDrawable(context, R.drawable.button_round);
        roundFilled = ContextCompat.getDrawable(context, R.drawable.button_round_filled);

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

    public int getClicks() { return this.clicks; }
}