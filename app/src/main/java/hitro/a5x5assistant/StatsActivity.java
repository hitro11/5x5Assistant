package hitro.a5x5assistant;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;


public class StatsActivity extends AppCompatActivity {

    Button stats;
    EditText bodyweight, squat, bench, row, ohp, dl;
    int bw, sqW, bpW, rowW, ohpW, dlW;

    private FirebaseAuth mAuth;
    private DatabaseReference mFirebaseDatabase;
    private FirebaseDatabase mFirebaseInstance;
    private FirebaseUser user;
    private String uid;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stats);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        //button and edit text inits
        stats = (Button)findViewById(R.id.btnUpdStats);
        bodyweight = (EditText)findViewById(R.id.etBW);
        squat = (EditText)findViewById(R.id.etSquatW);
        bench = (EditText)findViewById(R.id.etBPW);
        row = (EditText)findViewById(R.id.etRowW);
        ohp = (EditText)findViewById(R.id.etOHPW);
        dl = (EditText)findViewById(R.id.etDLW);
        bodyweight.setGravity(Gravity.CENTER);
        squat.setGravity(Gravity.CENTER);
        bench.setGravity(Gravity.CENTER);
        row.setGravity(Gravity.CENTER);
        ohp.setGravity(Gravity.CENTER);
        dl.setGravity(Gravity.CENTER);

        //firebase user auth refs
        user = FirebaseAuth.getInstance().getCurrentUser();
        uid = user.getUid().toString();

        //firebase database ref
        mFirebaseInstance = FirebaseDatabase.getInstance();
        mFirebaseDatabase = mFirebaseInstance.getReference("users"); //get reference to user node


        /* shows current stats*/

        mFirebaseDatabase.child(uid).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                User user = dataSnapshot.getValue(User.class);
                bw = user.bodyweight;
                String bodyweightW = Integer.toString(bw);
                bodyweight.setText(bodyweightW);

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                // Failed to read value
                // Log.w(TAG, "Failed to read value.", error.toException());

            }
        });

        mFirebaseDatabase.child(uid).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                User user = dataSnapshot.getValue(User.class);
                sqW = user.squat;
                String squatWeight = Integer.toString(sqW);
                squat.setText(squatWeight);

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                // Failed to read value
                // Log.w(TAG, "Failed to read value.", error.toException());

            }
        });

        mFirebaseDatabase.child(uid).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                User user = dataSnapshot.getValue(User.class);
                bpW = user.bench;
                String benchWeight = Integer.toString(bpW);
                bench.setText(benchWeight);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                // Failed to read value
                // Log.w(TAG, "Failed to read value.", error.toException());

            }
        });

        mFirebaseDatabase.child(uid).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                User user = dataSnapshot.getValue(User.class);
                rowW = user.row;
                String rowWeight = Integer.toString(rowW);
                row.setText(rowWeight);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                // Failed to read value
                // Log.w(TAG, "Failed to read value.", error.toException());

            }
        });

        mFirebaseDatabase.child(uid).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                User user = dataSnapshot.getValue(User.class);
                ohpW = user.ohp;
                String ohpWeight = Integer.toString(ohpW);
                ohp.setText(ohpWeight);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                // Failed to read value
                // Log.w(TAG, "Failed to read value.", error.toException());

            }
        });

        mFirebaseDatabase.child(uid).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                User user = dataSnapshot.getValue(User.class);
                dlW = user.dl;
                String dlWeight = Integer.toString(dlW);
                dl.setText(dlWeight);
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

                                       mFirebaseDatabase.child(uid).child("bodyweight").setValue(Integer.parseInt(bodyweight.getText().toString()));
                                       mFirebaseDatabase.child(uid).child("squat").setValue(Integer.parseInt(squat.getText().toString()));
                                       mFirebaseDatabase.child(uid).child("bench").setValue(Integer.parseInt(bench.getText().toString()));
                                       mFirebaseDatabase.child(uid).child("row").setValue(Integer.parseInt(row.getText().toString()));
                                       mFirebaseDatabase.child(uid).child("ohp").setValue(Integer.parseInt(ohp.getText().toString()));
                                       mFirebaseDatabase.child(uid).child("dl").setValue(Integer.parseInt(dl.getText().toString()));

                                       //display toast
                                       Toast.makeText(getApplicationContext(), "Stats successfully updated",
                                               Toast.LENGTH_SHORT).show();
                                   }
                               }
        );

    }

    @Override
    public void onResume(){
        super.onResume();

        /* shows current stats*/

        mFirebaseDatabase.child(uid).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                User user = dataSnapshot.getValue(User.class);
                bw = user.bodyweight;
                String bodyweightW = Integer.toString(bw);
                bodyweight.setText(bodyweightW);

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                // Failed to read value
                // Log.w(TAG, "Failed to read value.", error.toException());

            }
        });

        mFirebaseDatabase.child(uid).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                User user = dataSnapshot.getValue(User.class);
                sqW = user.squat;
                String squatWeight = Integer.toString(sqW);
                squat.setText(squatWeight);

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                // Failed to read value
                // Log.w(TAG, "Failed to read value.", error.toException());

            }
        });

        mFirebaseDatabase.child(uid).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                User user = dataSnapshot.getValue(User.class);
                bpW = user.bench;
                String benchWeight = Integer.toString(bpW);
                bench.setText(benchWeight);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                // Failed to read value
                // Log.w(TAG, "Failed to read value.", error.toException());

            }
        });

        mFirebaseDatabase.child(uid).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                User user = dataSnapshot.getValue(User.class);
                rowW = user.row;
                String rowWeight = Integer.toString(rowW);
                row.setText(rowWeight);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                // Failed to read value
                // Log.w(TAG, "Failed to read value.", error.toException());

            }
        });

        mFirebaseDatabase.child(uid).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                User user = dataSnapshot.getValue(User.class);
                ohpW = user.ohp;
                String ohpWeight = Integer.toString(ohpW);
                ohp.setText(ohpWeight);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                // Failed to read value
                // Log.w(TAG, "Failed to read value.", error.toException());

            }
        });

        mFirebaseDatabase.child(uid).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                User user = dataSnapshot.getValue(User.class);
                dlW = user.dl;
                String dlWeight = Integer.toString(dlW);
                dl.setText(dlWeight);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                // Failed to read value
                // Log.w(TAG, "Failed to read value.", error.toException());

            }
        });
    }



}
