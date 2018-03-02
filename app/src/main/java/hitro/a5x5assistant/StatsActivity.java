package hitro.a5x5assistant;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;


public class StatsActivity extends BaseActivity {

    private Button btnStats;
    private EditText etBody, etSquat, etBench, etRow, etOHP, etDL;
    private TextView txtUnits;
    private String name;
    private String units;
    private Profile profile;
    private double body, squat, bench, row, ohp, dl;

    private FirebaseAuth auth;
    private String uid;
    DocumentReference dbProfile;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stats);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(StatsActivity.this, WorkoutSelectActivity.class);
                startActivity(intent);
            }
        });

        units = sharedPref.getString("units","");
        btnStats = findViewById(R.id.btnUpdStats);
        etBody = findViewById(R.id.etBW);
        etSquat = findViewById(R.id.etSquatW);
        etBench = findViewById(R.id.etBPW);
        etRow = findViewById(R.id.etRowW);
        etOHP = findViewById(R.id.etOHPW);
        etDL = findViewById(R.id.etDLW);
        txtUnits = findViewById(R.id.txtUnits);
        auth = FirebaseAuth.getInstance();
        dbProfile = FirebaseFirestore.getInstance().document("users/" + uid);

        try {
            uid = FirebaseAuth.getInstance().getCurrentUser().getUid();
        } catch (NullPointerException e) {
            startActivity(new Intent(this, LoginActivity.class));
        }

        // retreives stats from db
        dbProfile.get()
                .addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                        if (task.isSuccessful() && task.getResult().exists()) {
                            profile = task.getResult().toObject(Profile.class);
                        }
                    }
                });

                if(units.equals("lb")) {
                    etBody.setText(String.valueOf(profile.getBody()));
                    etSquat.setText(String.valueOf(profile.getSquat()));
                    etBench.setText(String.valueOf(profile.getBench()));
                    etRow.setText(String.valueOf(profile.getRow()));
                    etOHP.setText(String.valueOf(profile.getOhp()));
                    etDL.setText(String.valueOf(profile.getDl()));
                    txtUnits.setText(R.string.lb);
                }
                else {

                    double bodyweightTemp = Math.round((profile.getBody() / 2.2) / 2.5) * 2.5;
                    double squatTemp = Math.round((profile.getSquat() / 2.2) / 2.5) * 2.5;
                    double benchTemp = Math.round((profile.getBench() / 2.2) / 2.5) * 2.5;
                    double rowTemp = Math.round((profile.getRow() / 2.2) / 2.5) * 2.5;
                    double ohpTemp = Math.round((profile.getOhp() / 2.2) / 2.5) * 2.5;
                    double dlTemp = Math.round((profile.getDl() / 2.2) / 2.5) * 2.5;

                    etBody.setText(String.valueOf(bodyweightTemp));
                    etSquat.setText(String.valueOf(squatTemp));
                    etBench.setText(String.valueOf(benchTemp));
                    etRow.setText(String.valueOf(rowTemp));
                    etOHP.setText(String.valueOf(ohpTemp));
                    etDL.setText(String.valueOf(dlTemp));
                    txtUnits.setText(R.string.kg);
                }


        //updates stats when "Update Stats" button is pressed
        btnStats.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(units.equals("lb")) {
                    dbProfile.update(
                            "body", Double.valueOf(etBody.getText().toString().trim()),
                            "squat", Double.valueOf(etSquat.getText().toString().trim()),
                            "bench", Double.valueOf(etBody.getText().toString().trim()),
                            "row", profile.getRow(),
                            "ohp", profile.getOhp(),
                            "dl", profile.getDl()
                    );
                }
                else {
                    double bodyweightNew = Double.parseDouble(etBody.getText().toString());
                    bodyweightNew = bodyweightNew * 2 + bodyweightNew / 10;

                    double squatNew = Double.parseDouble(etSquat.getText().toString());
                    squatNew = squatNew * 2 + squatNew / 10;

                    double benchNew = Double.parseDouble(etBench.getText().toString());
                    benchNew = benchNew * 2 + benchNew / 10;

                    double rowNew = Double.parseDouble(etRow.getText().toString());
                    rowNew = rowNew * 2 + rowNew / 10;

                    double ohpNew = Double.parseDouble(etOHP.getText().toString());
                    ohpNew = ohpNew * 2 + ohpNew / 10;

                    double dlNew = Double.parseDouble(etDL.getText().toString());
                    dlNew = dlNew * 2 + dlNew / 10;

                    Profile profile = new Profile(name, bodyweightNew, squatNew,
                            benchNew, rowNew, ohpNew, dlNew);
                }

                dbProfile.set(profile);
                Toast.makeText(getApplicationContext(), "Stats successfully updated",
                        Toast.LENGTH_SHORT).show();
            }
        });
    }
}
