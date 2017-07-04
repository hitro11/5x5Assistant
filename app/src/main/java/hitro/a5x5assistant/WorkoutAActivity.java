package hitro.a5x5assistant;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.text.SimpleDateFormat;
import java.util.Date;

import static hitro.a5x5assistant.SettingsActivity.units;


public class WorkoutAActivity extends BaseActivity {

    private static final String TAG = "WorkoutAActivity";
    private DatabaseReference dbProfile, dbHistory;
    private String uid;
    private String name;
    private double squatWeight, benchWeight, rowWeight;
    private double ohpWeight, dlWeight, bodyweight;
    private String squatDone, benchDone, rowDone;
    private double squatWeightNew, benchWeightNew, rowWeightNew;
    private double squatTemp, benchTemp, rowTemp;
    private Button a1, a2, a3, a4, a5, b1, b2, b3 ,b4, b5, c1, c2, c3, c4, c5;
    private TextView txtSquat, txtBench, txtRow, txtUnits;
    private SimpleDateFormat dateFormat;
    private Drawable roundFilled, round;

    //click counters
    private static int a1Click = 0, a2Click = 0, a3Click = 0, a4Click = 0, a5Click = 0,
            b1Click = 0, b2Click = 0, b3Click = 0, b4Click = 0, b5Click = 0,
            c1Click = 0, c2Click = 0, c3Click = 0, c4Click = 0, c5Click = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_workout_a);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        //button inits
        a1 = (Button)findViewById(R.id.btnSq1);
        a2 = (Button)findViewById(R.id.btnSq2);
        a3 = (Button)findViewById(R.id.btnSq3);
        a4 = (Button)findViewById(R.id.btnSq4);
        a5 = (Button)findViewById(R.id.btnSq5);
        b1 = (Button)findViewById(R.id.btnBP1);
        b2 = (Button)findViewById(R.id.btnBP2);
        b3 = (Button)findViewById(R.id.btnBP3);
        b4 = (Button)findViewById(R.id.btnBP4);
        b5 = (Button)findViewById(R.id.btnBP5);
        c1 = (Button)findViewById(R.id.btnRow1);
        c2 = (Button)findViewById(R.id.btnRow2);
        c3 = (Button)findViewById(R.id.btnRow3);
        c4 = (Button)findViewById(R.id.btnRow4);
        c5 = (Button)findViewById(R.id.btnRow5);
        Button fin = (Button)findViewById(R.id.btnFin);
        round = getResources().getDrawable(R.drawable.button_round);
        roundFilled = getResources().getDrawable(R.drawable.button_round_filled);

        //textview inits
        txtSquat = (TextView)findViewById(R.id.txtSqWeight);
        txtBench = (TextView)findViewById(R.id.txtBenchWeight);
        txtRow = (TextView)findViewById(R.id.txtRowWeight);
        txtUnits = (TextView)findViewById(R.id.txtUnits);

        squatDone = "Not Completed";
        benchDone = "Not Completed";
        rowDone = "Not Completed";

        //firebase user auth refs
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        uid = user.getUid();

        //firebase user auth refs
        user = FirebaseAuth.getInstance().getCurrentUser();
        uid = user.getUid();
        dbProfile =  FirebaseDatabase.getInstance().getReference("profiles").child(uid);
        dbHistory = FirebaseDatabase.getInstance().getReference("history").child(uid);


        /* set appropriate units */
        if(units != 0) {
            txtUnits.setText(R.string.kg);
        }

        /* set weights for current workout */
        dbProfile.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                Profile profile = dataSnapshot.getValue(Profile.class); //retrieves user's profile

                if (units == 0) {
                    squatWeight = Math.floor(profile.getSquat());
                    benchWeight = Math.floor(profile.getBench());
                    rowWeight = Math.floor(profile.getRow());

                    txtSquat.setText(String.valueOf(squatWeight));
                    txtBench.setText(String.valueOf(benchWeight));
                    txtRow.setText(String.valueOf(rowWeight));
                }
                else {
                    squatWeight = Math.floor(profile.getSquat()/10 - profile.getSquat()/20);
                    benchWeight = Math.floor(profile.getBench()/10 - profile.getBench()/20);
                    rowWeight = Math.floor(profile.getRow()/10 - profile.getRow()/20);

                    txtSquat.setText(String.valueOf(squatWeight));
                    txtBench.setText(String.valueOf(benchWeight));
                    txtRow.setText(String.valueOf(rowWeight));
                }

                ohpWeight = profile.getOhp();
                dlWeight = profile.getDl();
                name = profile.getName();
                bodyweight = profile.getBodyweight();
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                // Failed to read value
                // Log.w(TAG, "Failed to read value.", error.toException());
            }
        });


        fin.setOnClickListener(new Button.OnClickListener() {
                                   public void onClick(View view) {

                                       squatWeightNew = squatWeight;
                                       benchWeightNew = benchWeight;
                                       rowWeightNew = rowWeight;

                                       //check if all sets of the 3 exercises were completed successfully
                                       if (a1.getBackground() == roundFilled && a2.getBackground() == roundFilled &&
                                               a3.getBackground() == roundFilled && a4.getBackground() == roundFilled
                                               && a5.getBackground() == roundFilled) {

                                           squatDone = "Completed";

                                           if (units == 0) {
                                               squatWeightNew = squatWeight + 5;
                                           } else {
                                               squatWeightNew = Math.floor(squatWeight*2 + squatWeight/10 + 5);
                                           }


                                       if (b1.getBackground() == roundFilled && b2.getBackground() == roundFilled &&
                                               b3.getBackground() == roundFilled && b4.getBackground() == roundFilled
                                               && b5.getBackground() == roundFilled) {

                                               benchDone = "Completed";

                                               if (SettingsActivity.units == 0) {
                                                   benchWeightNew = benchWeight + 5;
                                               } else {
                                                   benchWeightNew = Math.floor(benchWeight*2 + benchWeight/10 + 5);
                                               }
                                           }

                                       if (c1.getBackground() == roundFilled && c2.getBackground() == roundFilled &&
                                               c3.getBackground() == roundFilled && c4.getBackground() == roundFilled
                                               && c5.getBackground() == roundFilled) {

                                               rowDone = "Completed";

                                               if (SettingsActivity.units == 0) {
                                                   rowWeightNew = rowWeight + 5;
                                               } else {
                                                   rowWeightNew = Math.floor(rowWeight*2 +  rowWeight/10 + 5);
                                               }
                                           }

                                           // save workout to db and update profile with new weights
                                           dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                                           String date = dateFormat.format(new Date());

                                           History history = new History(date, "A", squatWeight,
                                                   benchWeight, rowWeight, 0.0, 0.0, squatDone,
                                                   benchDone, rowDone, "-", "-");
                                           dbHistory.child(date).setValue(history);

                                           Profile profile = new Profile(name, bodyweight, squatWeightNew,
                                                   benchWeightNew, rowWeightNew, ohpWeight, dlWeight);
                                           dbProfile.setValue(profile);

                                           //redirects to home activity
                                           Intent intent = new Intent(getApplicationContext(), HomeActivity.class);
                                           startActivity(intent);
                                       }
                                   }
                               }
        );


        /*------------------------- workout button click handlers --------------------------------*/

        a1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick (View v) {

                a1Click++;

                if (a1Click%6 == 1) {
                    a1.setBackground(roundFilled);
                    a1.setText("");
                }

                if (a1Click%6 == 2) {
                    a1.setBackground(round);
                    a1.setText("1");
                }

                if (a1Click%6 == 3) {a1.setText("2");}
                if (a1Click%6 == 4) {a1.setText("3");}
                if (a1Click%6 == 5) {a1.setText("4");}
                if (a1Click%6 == 0) {a1.setText("");}
            }
        });

        a2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick (View v) {

                a2Click++;

                if (a2Click%6 == 1) {
                    a2.setBackground(roundFilled);
                    a2.setText("");
                }

                if (a2Click%6 == 2) {
                    a2.setBackground(round);
                    a2.setText("1");
                }

                if (a2Click%6 == 3) {a2.setText("2");}
                if (a2Click%6 == 4) {a2.setText("3");}
                if (a2Click%6 == 5) {a2.setText("4");}
                if (a2Click%6 == 0) {a2.setText("");}
            }
        });

        a3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick (View v) {

                a3Click++;

                if (a3Click%6 == 1) {
                    a3.setBackground(roundFilled);
                    a3.setText("");
                }

                if (a3Click%6 == 2) {
                    a3.setBackground(round);
                    a3.setText("1");
                }

                if (a3Click%6 == 3) {a3.setText("2");}
                if (a3Click%6 == 4) {a3.setText("3");}
                if (a3Click%6 == 5) {a3.setText("4");}
                if (a3Click%6 == 0) {a3.setText("");}
            }
        });

        a4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick (View v) {

                a4Click++;

                if (a4Click%6 == 1) {
                    a4.setBackground(roundFilled);
                    a4.setText("");
                }

                if (a4Click%6 == 2) {
                    a4.setBackground(round);
                    a4.setText("1");
                }

                if (a4Click%6 == 3) {a4.setText("2");}
                if (a4Click%6 == 4) {a4.setText("3");}
                if (a4Click%6 == 5) {a4.setText("4");}
                if (a4Click%6 == 0) {a4.setText("");}
            }
        });

        a5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick (View v) {

                a5Click++;

                if (a5Click%6 == 1) {
                    a5.setBackground(roundFilled);
                    a5.setText("");
                }

                if (a5Click%6 == 2) {
                    a5.setBackground(round);
                    a5.setText("1");
                }

                if (a5Click%6 == 3) {a5.setText("2");}
                if (a5Click%6 == 4) {a5.setText("3");}
                if (a5Click%6 == 5) {a5.setText("4");}
                if (a5Click%6 == 0) {a5.setText("");}
            }
        });


        // bench stuff
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick (View v) {
                b1Click++;
                if (b1Click%6 == 1) {
                    b1.setBackground(roundFilled);
                    b1.setText("");
                }
                if (b1Click%6 == 2) {
                    b1.setBackground(round);
                    b1.setText("1");
                }
                if (b1Click%6 == 3) {b1.setText("2");}
                if (b1Click%6 == 4) {b1.setText("3");}
                if (b1Click%6 == 5) {b1.setText("4");}
                if (b1Click%6 == 0) {b1.setText("");}
            }
        });
        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick (View v) {
                b2Click++;
                if (b2Click%6 == 1) {b2.setBackground(roundFilled);b2.setText("");
                }
                if (b2Click%6 == 2) {b2.setBackground(round);b2.setText("1");
                }
                if (b2Click%6 == 3) {b2.setText("2");}
                if (b2Click%6 == 4) {b2.setText("3");}
                if (b2Click%6 == 5) {b2.setText("4");}
                if (b2Click%6 == 0) {b2.setText("");}
            }
        });

        b3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick (View v) {
                b3Click++;
                if (b3Click%6 == 1) {
                    b3.setBackground(roundFilled);
                    b3.setText("");
                }
                if (b3Click%6 == 2) {
                    b3.setBackground(round);
                    b3.setText("1");
                }
                if (b3Click%6 == 3) {b3.setText("2");}
                if (b3Click%6 == 4) {b3.setText("3");}
                if (b3Click%6 == 5) {b3.setText("4");}
                if (b3Click%6 == 0) {b3.setText("");}
            }
        });

        b4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick (View v) {
                b4Click++;
                if (b4Click%6 == 1) {
                    b4.setBackground(roundFilled);
                    b4.setText("");
                }
                if (b4Click%6 == 2) {
                    b4.setBackground(round);
                    b4.setText("1");
                }
                if (b4Click%6 == 3) {b4.setText("2");}
                if (b4Click%6 == 4) {b4.setText("3");}
                if (b4Click%6 == 5) {b4.setText("4");}
                if (b4Click%6 == 0) {b4.setText("");}
            }
        });

        b5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick (View v) {
                b5Click++;
                if (b5Click%6 == 1) {
                    b5.setBackground(roundFilled);
                    b5.setText("");
                }
                if (b5Click%6 == 2) {
                    b5.setBackground(round);
                    b5.setText("1");
                }
                if (b5Click%6 == 3) {b5.setText("2");}
                if (b5Click%6 == 4) {b5.setText("3");}
                if (b5Click%6 == 5) {b5.setText("4");}
                if (b5Click%6 == 0) {b5.setText("");}
            }
        });


        // ohp stuff
        c1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick (View v) {

                c1Click++;

                if (c1Click%6 == 1) {
                    c1.setBackground(roundFilled);
                    c1.setText("");
                }

                if (c1Click%6 == 2) {
                    c1.setBackground(round);
                    c1.setText("1");
                }

                if (c1Click%6 == 3) c1.setText("2");
                if (c1Click%6 == 4) c1.setText("3");
                if (c1Click%6 == 0) c1.setText("4");
            }
        });

        c2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick (View v) {
                c2Click++;
                if (c2Click%6 == 1) {
                    c2.setBackground(roundFilled);
                    c2.setText("");
                }
                if (c2Click%6 == 2) {
                    c2.setBackground(round);
                    c2.setText("1");
                }
                if (c2Click%6 == 3) c2.setText("3");
                if (c2Click%6 == 4) c2.setText("2");
                if (c2Click%6 == 5) c2.setText("1");
                if (c2Click%6 == 0) c2.setText("");
            }
        });

        c3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick (View v) {

                c3Click++;

                if (c3Click%6 == 1) {
                    c3.setBackground(roundFilled);
                    c3.setText("");
                }

                if (c3Click%6 == 2) {
                    c3.setBackground(round);
                    c3.setText("1");
                }

                if (c3Click%6 == 3) c3.setText("3");
                if (c3Click%6 == 4) c3.setText("2");
                if (c3Click%6 == 5) c3.setText("1");
                if (c3Click%6 == 0) c3.setText("");
            }
        });

        c4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick (View v) {

                c4Click++;

                if (c4Click%6 == 1) {
                    c4.setBackground(roundFilled);
                    c4.setText("");
                }

                if (c4Click%6 == 2) {
                    c4.setBackground(round);
                    c4.setText("1");
                }

                if (c4Click%6 == 3) c4.setText("3");
                if (c4Click%6 == 4) c4.setText("2");
                if (c4Click%6 == 5) c4.setText("1");
                if (c4Click%6 == 0) c4.setText("");
            }
        });

        c5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick (View v) {

                c5Click++;

                if (c5Click%6 == 1) {
                    c5.setBackground(roundFilled);
                    c5.setText("");
                }
                if (c5Click%6 == 2) {
                    c5.setBackground(round);
                    c5.setText("1");
                }
                if (c5Click%6 == 3) c5.setText("3");
                if (c5Click%6 == 4) c5.setText("2");
                if (c5Click%6 == 5) c5.setText("1");
                if (c5Click%6 == 0) c5.setText("");
            }
        });

    }
}
