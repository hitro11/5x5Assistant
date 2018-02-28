package hitro.a5x5assistant;

import android.content.Intent;
import android.os.Bundle;
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


import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;



public class StatsActivity extends BaseActivity {

    private Button btnStats;
    private EditText etBodyweight, etSquat, etBench, etRow, etOHP, etDL;
    private TextView txtUnits;
    private String name;
    private String units;

    private FirebaseAuth auth;

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

        units = sharedPref.getString("units","");

        btnStats = (Button)findViewById(R.id.btnUpdStats);
        etBodyweight = (EditText)findViewById(R.id.etBW);
        etSquat = (EditText)findViewById(R.id.etSquatW);
        etBench = (EditText)findViewById(R.id.etBPW);
        etRow = (EditText)findViewById(R.id.etRowW);
        etOHP = (EditText)findViewById(R.id.etOHPW);
        etDL = (EditText)findViewById(R.id.etDLW);
        txtUnits = (TextView)findViewById(R.id.txtUnits);
        auth = FirebaseAuth.getInstance();
        user = FirebaseAuth.getInstance().getCurrentUser();
        uid = user.getUid();
      //  dbProfiles = FirebaseDatabase.getInstance().getReference("profiles").child(uid); //get reference to user node

//        /* shows current stats */
//        dbProfiles.addListenerForSingleValueEvent(new ValueEventListener() {
//            @Override
//            public void onDataChange(DataSnapshot dataSnapshot) {
//
//                Profile profile = dataSnapshot.getValue(Profile.class);
//                name = profile.;
//
//                if(units.equals("lb")) {
//                    etBodyweight.setText(String.valueOf(profile.getBodyweight()));
//                    etSquat.setText(String.valueOf(profile.getSquat()));
//                    etBench.setText(String.valueOf(profile.getBench()));
//                    etRow.setText(String.valueOf(profile.getRow()));
//                    etOHP.setText(String.valueOf(profile.getOhp()));
//                    etDL.setText(String.valueOf(profile.getDl()));
//                    txtUnits.setText(R.string.lb);
//                }
//                else {
//
//                    double bodyweightTemp = Math.floor(profile.getBodyweight()/2 - profile.getBodyweight()/20);
//                    double squatTemp = Math.floor(profile.getSquat()/2 - profile.getSquat()/20);
//                    double benchTemp = Math.floor(profile.getBench()/2 - profile.getBench()/20);
//                    double rowTemp = Math.floor(profile.getRow()/2 - profile.getRow()/20);
//                    double ohpTemp = Math.floor(profile.getOhp()/2 - profile.getOhp()/20);
//                    double dlTemp = Math.floor(profile.getDl()/2 - profile.getDl()/20);
//
//                    etBodyweight.setText(String.valueOf(bodyweightTemp));
//                    etSquat.setText(String.valueOf(squatTemp));
//                    etBench.setText(String.valueOf(benchTemp));
//                    etRow.setText(String.valueOf(rowTemp));
//                    etOHP.setText(String.valueOf(ohpTemp));
//                    etDL.setText(String.valueOf(dlTemp));
//                    txtUnits.setText(R.string.kg);
//                }
//            }
//            @Override
//            public void onCancelled(DatabaseError databaseError) {
//                // Log.w(TAG, "Failed to read value.", error.toException());
//            }
//        });
//
//        //updates stats when "Update Stats" button is pressed
//        btnStats.setOnClickListener(new Button.OnClickListener() {
//                                   public void onClick(View view){
//
//                                       if(units.equals("lb")) {
//                                           dbProfiles.child("bodyweight").setValue(Double.parseDouble(etBodyweight.getText().toString()));
//                                           dbProfiles.child("squat").setValue(Double.parseDouble(etSquat.getText().toString()));
//                                           dbProfiles.child("bench").setValue(Double.parseDouble(etBench.getText().toString()));
//                                           dbProfiles.child("row").setValue(Double.parseDouble(etRow.getText().toString()));
//                                           dbProfiles.child("ohp").setValue(Double.parseDouble(etOHP.getText().toString()));
//                                           dbProfiles.child("dl").setValue(Double.parseDouble(etDL.getText().toString()));
//                                       }
//                                       else {
//                                           double bodyweightNew = Double.parseDouble(etBodyweight.getText().toString());
//                                           bodyweightNew = bodyweightNew*2 + bodyweightNew/10;
//
//                                           double squatNew = Double.parseDouble(etSquat.getText().toString());
//                                           squatNew = squatNew*2 + squatNew/10;
//
//                                           double benchNew = Double.parseDouble(etBench.getText().toString());
//                                           benchNew = benchNew*2 + benchNew/10;
//
//                                           double rowNew = Double.parseDouble(etRow.getText().toString());
//                                           rowNew = rowNew*2 + rowNew/10;
//
//                                           double ohpNew = Double.parseDouble(etOHP.getText().toString());
//                                           ohpNew = ohpNew*2 + ohpNew/10;
//
//                                           double dlNew = Double.parseDouble(etDL.getText().toString());
//                                           dlNew = dlNew*2 + dlNew/10;
//
//                                           Profile profile = new Profile(name, bodyweightNew, squatNew,
//                                                   benchNew, rowNew, ohpNew, dlNew );
//                                           dbProfiles.setValue(profile);
//                                       }
//
//                                       Toast.makeText(getApplicationContext(), "Stats successfully updated",
//                                               Toast.LENGTH_SHORT).show();
//                                   }
//                               }
//        );
    }
}
