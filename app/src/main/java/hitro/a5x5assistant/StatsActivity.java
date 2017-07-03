package hitro.a5x5assistant;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.games.stats.Stats;
import com.google.android.gms.vision.text.Text;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;


public class StatsActivity extends BaseActivity {

    Button stats;
    EditText bodyweight, squat, bench, row, ohp, dl;
    private TextView txtUnits;

    private FirebaseAuth auth;
    private DatabaseReference dbProfiles;
    private FirebaseUser user;
    private String uid;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stats);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(StatsActivity.this, WorkoutSelectActivity.class);
                startActivity(intent);

            }
        });

        //button and edit text inits
        stats = (Button)findViewById(R.id.btnUpdStats);
        bodyweight = (EditText)findViewById(R.id.etBW);
        squat = (EditText)findViewById(R.id.etSquatW);
        bench = (EditText)findViewById(R.id.etBPW);
        row = (EditText)findViewById(R.id.etRowW);
        ohp = (EditText)findViewById(R.id.etOHPW);
        dl = (EditText)findViewById(R.id.etDLW);
        txtUnits = (TextView)findViewById(R.id.txtUnits);

        //firebase user auth refs
        auth = FirebaseAuth.getInstance();
        user = FirebaseAuth.getInstance().getCurrentUser();
        uid = user.getUid().toString();
        dbProfiles = FirebaseDatabase.getInstance().getReference("users"); //get reference to user node


        /* shows current stats*/
        dbProfiles.child(uid).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                /*
                User user = dataSnapshot.getValue(User.class);

                if (SettingsActivity.units == 0) {
                    bodyweight.setText(Integer.toString(user.bodyweight));
                    squat.setText(Integer.toString(user.squat));
                    bench.setText(Integer.toString(user.bench));
                    row.setText(Integer.toString(user.row));
                    ohp.setText(Integer.toString(user.ohp));
                    dl.setText(Integer.toString(user.dl));
                    txtUnits.setText(R.string.lb);
                }
                else {
                    bodyweight.setText(Integer.toString((int)(user.bodyweight / 2.205)));
                    squat.setText(Integer.toString((int)(user.squat / 2.205)));
                    bench.setText(Integer.toString((int)(user.bench / 2.205)));
                    row.setText(Integer.toString((int)(user.row / 2.205)));
                    ohp.setText(Integer.toString((int)(user.ohp / 2.205)));
                    dl.setText(Integer.toString((int)(user.dl / 2.205)));
                    txtUnits.setText(R.string.kg);

                }
                */
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                // Failed to read value
                // Log.w(TAG, "Failed to read value.", error.toException());
            }
        });

        //updates stats when "Update Stats" button is pressed
        stats.setOnClickListener(new Button.OnClickListener() {
                                   public void onClick(View view){

                                       if (SettingsActivity.units == 0) {
                                           dbProfiles.child(uid).child("bodyweight").setValue(Integer.parseInt(bodyweight.getText().toString()));
                                           dbProfiles.child(uid).child("squat").setValue(Integer.parseInt(squat.getText().toString()));
                                           dbProfiles.child(uid).child("bench").setValue(Integer.parseInt(bench.getText().toString()));
                                           dbProfiles.child(uid).child("row").setValue(Integer.parseInt(row.getText().toString()));
                                           dbProfiles.child(uid).child("ohp").setValue(Integer.parseInt(ohp.getText().toString()));
                                           dbProfiles.child(uid).child("dl").setValue(Integer.parseInt(dl.getText().toString()));
                                       }

                                       else {
                                           dbProfiles.child(uid).child("bodyweight").setValue(Integer.parseInt(bodyweight.getText().toString()) * 2.205);
                                           dbProfiles.child(uid).child("squat").setValue(Integer.parseInt(squat.getText().toString())*2.205);
                                           dbProfiles.child(uid).child("bench").setValue(Integer.parseInt(bench.getText().toString())*2.205);
                                           dbProfiles.child(uid).child("row").setValue(Integer.parseInt(row.getText().toString())*2.205);
                                           dbProfiles.child(uid).child("ohp").setValue(Integer.parseInt(ohp.getText().toString())*2.205);
                                           dbProfiles.child(uid).child("dl").setValue(Integer.parseInt(dl.getText().toString())*2.205);
                                       }

                                       //display toast
                                       Toast.makeText(getApplicationContext(), "Stats successfully updated",
                                               Toast.LENGTH_SHORT).show();
                                   }
                               }
        );

    }
}
