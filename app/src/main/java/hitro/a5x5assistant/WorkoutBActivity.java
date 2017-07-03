package hitro.a5x5assistant;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

//firebase database imports
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class WorkoutBActivity extends BaseActivity {

    private static final String TAG = "WorkoutBActivity";
    private int squatWeight, ohpWeight, dlWeight;
    private String squatWeightNew, ohpWeightNew, dlWeightNew;
    private boolean squatDone, ohpDone, dlDone;
    private TextView txtSquat, txtOHP, txtDL;
    private Button a1, a2, a3, a4, a5, b1, b2, b3 ,b4, b5, c1, fin;
    private Drawable round, roundFilled;
    private FirebaseUser user;
    private String uid;
    private DatabaseReference dbProfile, dbHistory;
    private SimpleDateFormat dateFormat;

    //click counters
    private int a1Click = 0, a2Click = 0, a3Click = 0, a4Click = 0, a5Click = 0,
            b1Click = 0, b2Click = 0, b3Click = 0, b4Click = 0, b5Click = 0,
            c1Click = 0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_workout_b);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        a1 = (Button)findViewById(R.id.btnSq1);
        a2 = (Button)findViewById(R.id.btnSq2);
        a3 = (Button)findViewById(R.id.btnSq3);
        a4 = (Button)findViewById(R.id.btnSq4);
        a5 = (Button)findViewById(R.id.btnSq5);
        b1 = (Button)findViewById(R.id.btnOHP1);
        b2 = (Button)findViewById(R.id.btnOHP2);
        b3 = (Button)findViewById(R.id.btnOHP3);
        b4 = (Button)findViewById(R.id.btnOHP4);
        b5 = (Button)findViewById(R.id.btnOHP5);
        c1 = (Button)findViewById(R.id.btnDL1);

        fin = (Button)findViewById(R.id.btnFin);
        roundFilled = getResources().getDrawable(R.drawable.button_round_filled);
        round = getResources().getDrawable(R.drawable.button_round);

        //textview inits
        txtSquat = (TextView)findViewById(R.id.txtSquatWeight);
        txtOHP = (TextView)findViewById(R.id.txtOHPweight);
        txtDL = (TextView)findViewById(R.id.txtDLweight);

        //firebase user auth refs
        user = FirebaseAuth.getInstance().getCurrentUser();
        uid = user.getUid();
        dbProfile =  FirebaseDatabase.getInstance().getReference("profiles");
        dbHistory = FirebaseDatabase.getInstance().getReference("history");

        /* set weights for current workout */
        dbProfile.child(uid).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                Profile profile = dataSnapshot.getValue(Profile.class); //retrieves user's profile

                if (SettingsActivity.units == 0) {
                    txtSquat.setText(profile.squat);
                    txtOHP.setText(profile.ohp);
                    txtDL.setText(profile.dl);

                    squatWeight = Integer.parseInt(txtSquat.getText().toString().trim());
                    ohpWeight = Integer.parseInt(txtOHP.getText().toString().trim());
                    dlWeight = Integer.parseInt(txtDL.getText().toString().trim());
                }
                else {
                    int sqTemp = Integer.parseInt(profile.squat);
                    int ohpTemp = Integer.parseInt(profile.ohp);
                    int dlTemp = Integer.parseInt(profile.dl);

                    txtSquat.setText(Integer.toString(sqTemp/10 - sqTemp/20));
                    txtOHP.setText(Integer.toString(ohpTemp/10 - ohpTemp/20));
                    txtDL.setText(Integer.toString(dlTemp/10 - dlTemp/20));

                    squatWeight = Integer.parseInt(txtSquat.getText().toString().trim());
                    ohpWeight = Integer.parseInt(txtOHP.getText().toString().trim());
                    dlWeight = Integer.parseInt(txtDL.getText().toString().trim());
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                // Failed to read value
                // Log.w(TAG, "Failed to read value.", error.toException());
            }
        });

        fin.setOnClickListener(new Button.OnClickListener() {
                                   public void onClick(View view){

                                       //check if all sets of the 3 exercises were completed successfully
                                       if (a1.getBackground() == roundFilled && a2.getBackground() == roundFilled &&
                                               a3.getBackground() == roundFilled && a4.getBackground() == roundFilled
                                               && a5.getBackground() == roundFilled) {

                                           squatDone = true;

                                           if (SettingsActivity.units == 0) {
                                               squatWeightNew = Integer.toString(squatWeight + 5);
                                               dbProfile.child(uid).child("squat").setValue(squatWeightNew);
                                           }
                                           else {
                                               squatWeightNew = Integer.toString(squatWeight*2 + Math.round(squatWeight/2) + 5);
                                               dbProfile.child(uid).child("squat").setValue(squatWeightNew);                                           }
                                       }

                                       if (b1.getBackground() == roundFilled && b2.getBackground() == roundFilled &&
                                               b3.getBackground() == roundFilled && b4.getBackground() == roundFilled
                                               && b5.getBackground() == roundFilled) {

                                           ohpDone = true;

                                           if (SettingsActivity.units == 0) {
                                               ohpWeightNew = Integer.toString(ohpWeight + 5);
                                               dbProfile.child(uid).child("ohp").setValue(ohpWeightNew);
                                           }
                                           else {
                                               ohpWeightNew = Integer.toString(ohpWeight*2 + Math.round(ohpWeight/10) + 5);
                                               dbProfile.child(uid).child("ohp").setValue(ohpWeightNew);
                                           }
                                       }

                                       if (c1.getBackground() == roundFilled) {

                                           dlDone = true;

                                           if (SettingsActivity.units == 0) {
                                               dlWeightNew = Integer.toString(dlWeight + 5);
                                               dbProfile.child(uid).child("dl").setValue(dlWeightNew);
                                           }
                                           else {
                                               dlWeightNew = Integer.toString(dlWeight*2 + Math.round(dlWeight/10) + 5);
                                               dbProfile.child(uid).child("dl").setValue(dlWeightNew);
                                           }
                                       }

                                       // save workout to db
                                       dateFormat = new SimpleDateFormat("yyyy-MMM-dd HH:mm:ss");
                                       String date = dateFormat.format(new Date());
                                       History history = new History("B", date, Integer.toString(squatWeight),
                                               null, null, Integer.toString(ohpWeight), Integer.toString(dlWeight),
                                               squatDone, false, false, ohpDone, dlDone);

                                       //redirects to home activity
                                       Intent intent = new Intent(WorkoutBActivity.this, HomeActivity.class);
                                       startActivity(intent);
                                   }
                               }
        );

        /* button click handlers */

        a1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick (View v) {
                a1Click++;
                if (a1Click%5 == 1) {
                    a1.setBackground(roundFilled);
                    a1.setText("");
                }
                if (a1Click%5 == 2) {
                    a1.setBackground(round);
                    a1.setText("1");
                }
                if (a1Click%5 == 3) a1.setText("2");
                if (a1Click%5 == 4) a1.setText("3");
                if (a1Click%5 == 0) a1.setText("4");
            }
        });

        a2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick (View v) {
                a2Click++;
                if (a2Click%5 == 1) {
                    a2.setBackground(roundFilled);
                    a2.setText("");
                }
                if (a2Click%5 == 2) {
                    a2.setBackground(round);
                    a2.setText("1");
                }
                if (a2Click%5 == 3) a2.setText("2");
                if (a2Click%5 == 4) a2.setText("3");
                if (a2Click%5 == 0) a2.setText("4");
            }
        });

        a3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick (View v) {
                a3Click++;
                if (a3Click%5 == 1) {
                    a3.setBackground(roundFilled);
                    a3.setText("");
                }
                if (a3Click%5 == 2) {
                    a3.setBackground(round);
                    a3.setText("1");
                }
                if (a3Click%5 == 3)
                    a3.setText("2");
                if (a3Click%5 == 4)
                    a3.setText("3");
                if (a3Click%5 == 0)
                    a3.setText("4");
            }
        });

        a4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick (View v) {
                a4Click++;
                if (a4Click%5 == 1) {
                    a4.setBackground(roundFilled);
                    a4.setText("");
                }
                if (a4Click%5 == 2) {
                    a4.setBackground(round);
                    a4.setText("1");
                }
                if (a4Click%5 == 3) a4.setText("2");
                if (a4Click%5 == 4) a4.setText("3");
                if (a4Click%5 == 0) a4.setText("4");
            }
        });

        a5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick (View v) {
                a5Click++;
                if (a5Click%5 == 1) {
                    a5.setBackground(roundFilled);
                    a5.setText("");
                }
                if (a5Click%5 == 2) {
                    a5.setBackground(round);
                    a5.setText("1");
                }
                if (a5Click%5 == 3) a5.setText("2");
                if (a5Click%5 == 4) a5.setText("3");
                if (a5Click%5 == 0)
                    a5.setText("4");
            }
        });

        //OHP stuff
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick (View v) {
                b1Click++;
                if (b1Click%5 == 1) {
                    b1.setBackground(roundFilled);
                    b1.setText("");
                }
                if (b1Click%5 == 2) {
                    b1.setBackground(round);
                    b1.setText("1");
                }
                if (b1Click%5 == 3)
                    b1.setText("2");
                if (b1Click%5 == 4)
                    b1.setText("3");
                if (b1Click%5 == 0) b1.setText("4");
            }
        });
        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick (View v) {
                b2Click++;
                if (b2Click%5 == 1) {b2.setBackground(roundFilled);b2.setText("");
                }
                if (b2Click%5 == 2) {b2.setBackground(round);b2.setText("1");
                }
                if (b2Click%5 == 3) b2.setText("2");
                if (b2Click%5 == 4) b2.setText("3");
                if (b2Click%5 == 0) b2.setText("4");
            }
        });

        b3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick (View v) {
                b3Click++;
                if (b3Click%5 == 1) {
                    b3.setBackground(roundFilled);
                    b3.setText("");
                }
                if (b3Click%5 == 2) {
                    b3.setBackground(round);
                    b3.setText("1");
                }
                if (b3Click%5 == 3) b3.setText("2");
                if (b3Click%5 == 4) b3.setText("3");
                if (b3Click%5 == 0) b3.setText("4");
            }
        });

        b4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick (View v) {
                b4Click++;
                if (b4Click%5 == 1) {
                    b4.setBackground(roundFilled);
                    b4.setText("");
                }
                if (b4Click%5 == 2) {
                    b4.setBackground(round);
                    b4.setText("1");
                }
                if (b4Click%5 == 3) b4.setText("2");
                if (b4Click%5 == 4) b4.setText("3");
                if (b4Click%5 == 0) b4.setText("4");
            }
        });

        b5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick (View v) {
                b5Click++;
                if (b5Click%5 == 1) {
                    b5.setBackground(roundFilled);
                    b5.setText("");
                }
                if (b5Click%5 == 2) {
                    b5.setBackground(round);
                    b5.setText("1");
                }
                if (b5Click%5 == 3) b5.setText("2");
                if (b5Click%5 == 4) b5.setText("3");
                if (b5Click%5 == 0) b5.setText("4");
            }
        });

        //DL stuff
        c1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick (View v) {
                c1Click++;

                if (c1Click%5 == 1) {
                    c1.setBackground(roundFilled);
                    c1.setText("");
                }
                if (c1Click%5 == 2) {
                    c1.setBackground(round);
                    c1.setText("1");
                }
                if (c1Click%5 == 3) c1.setText("2");
                if (c1Click%5 == 4) c1.setText("3");
                if (c1Click%5 == 0) c1.setText("4");
            }
        });
    }
}
