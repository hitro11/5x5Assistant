package hitro.a5x5assistant;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
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


public class WorkoutAActivity extends AppCompatActivity {

    private static final String TAG = "WorkoutAAcivity";

    private FirebaseAuth auth;
    private FirebaseDatabase mFirebaseInstance;
    private DatabaseReference mFirebaseDatabase;
    private FirebaseUser user;
    private String uid;

    //weight trackers
    int sqW, bpW, rowW;

    //click counters
   private int a1Click = 0, a2Click = 0, a3Click = 0, a4Click = 0, a5Click = 0,
               b1Click = 0, b2Click = 0, b3Click = 0, b4Click = 0, b5Click = 0,
               c1Click = 0, c2Click = 0, c3Click = 0, c4Click = 0, c5Click = 0;

    // button inits
    Button a1, a2, a3, a4, a5, b1, b2, b3 ,b4, b5, c1, c2, c3, c4, c5, fin;

    //textview inits
    TextView txtSq, txtBP, txtRow;
    Drawable roundFilled, round;

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
        fin = (Button)findViewById(R.id.btnFin);
        round = getResources().getDrawable(R.drawable.button_round);
        roundFilled = getResources().getDrawable(R.drawable.button_round_filled);

        //textview inits
        txtSq = (TextView)findViewById(R.id.txtSqWeight);
        txtBP = (TextView)findViewById(R.id.txtBenchWeight);
        txtRow = (TextView)findViewById(R.id.txtRowWeight);

        //firebase user auth refs
        auth = FirebaseAuth.getInstance();
        user = FirebaseAuth.getInstance().getCurrentUser();
        uid = user.getUid();

        //firebase database ref
        mFirebaseInstance = FirebaseDatabase.getInstance();
        mFirebaseDatabase = mFirebaseInstance.getReference("users"); //get reference to user node


        /* set weights for current workout */
        mFirebaseDatabase.child(uid).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                User user = dataSnapshot.getValue(User.class);
                txtSq.setText(Integer.toString(user.squat));
                txtBP.setText(Integer.toString(user.bench));
                txtRow.setText(Integer.toString(user.row));
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                // Failed to read value
               // Log.w(TAG, "Failed to read value.", error.toException());

            }
        });

        fin.setOnClickListener(new Button.OnClickListener() {
                                       public void onClick(View view){

                                           //check if all sets of all 3 exercises were completed successfully
                                           if (a1.getBackground() == roundFilled && a2.getBackground() == roundFilled &&
                                                   a3.getBackground() == roundFilled && a4.getBackground() == roundFilled
                                                   && a5.getBackground() == roundFilled) {

                                               mFirebaseDatabase.child(uid).child("squat").setValue(sqW + 5);
                                           }

                                           if (b1.getBackground() == roundFilled && b2.getBackground() == roundFilled &&
                                                   b3.getBackground() == roundFilled && b4.getBackground() == roundFilled
                                                   && b5.getBackground() == roundFilled) {

                                               mFirebaseDatabase.child(uid).child("bench").setValue(bpW + 5);
                                           }

                                           if (c1.getBackground() == roundFilled && c2.getBackground() == roundFilled &&
                                                   c3.getBackground() == roundFilled && c4.getBackground() == roundFilled
                                                   && c5.getBackground() == roundFilled) {

                                               mFirebaseDatabase.child(uid).child("row").setValue(rowW + 5);
                                           }

                                           //redirects to home activity
                                           Intent intent = new Intent(WorkoutAActivity.this, HomeActivity.class);
                                           startActivity(intent);
                                       }
                                   }
        );


        /* workout button behavior */

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

                if (a1Click%5 == 3)
                    a1.setText("2");

                if (a1Click%5 == 4)
                    a1.setText("3");

                if (a1Click%5 == 0)
                    a1.setText("4");
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

                if (a2Click%5 == 3)
                    a2.setText("2");

                if (a2Click%5 == 4)
                    a2.setText("3");

                if (a2Click%5 == 0)
                    a2.setText("4");
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

                if (a4Click%5 == 3)
                    a4.setText("2");

                if (a4Click%5 == 4)
                    a4.setText("3");

                if (a4Click%5 == 0)
                    a4.setText("4");
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

                if (a5Click%5 == 3)
                    a5.setText("2");

                if (a5Click%5 == 4)
                    a5.setText("3");

                if (a5Click%5 == 0)
                    a5.setText("4");
            }
        });


        //b stuff
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

                if (b1Click%5 == 0)
                    b1.setText("4");
            }
        });

        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick (View v) {

                b2Click++;

                if (b2Click%5 == 1) {
                    b2.setBackground(roundFilled);
                    b2.setText("");
                }

                if (b2Click%5 == 2) {
                    b2.setBackground(round);
                    b2.setText("1");
                }

                if (b2Click%5 == 3)
                    b2.setText("2");

                if (b2Click%5 == 4)
                    b2.setText("3");

                if (b2Click%5 == 0)
                    b2.setText("4");
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

                if (b3Click%5 == 3)
                    b3.setText("2");

                if (b3Click%5 == 4)
                    b3.setText("3");

                if (b3Click%5 == 0)
                    b3.setText("4");
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

                if (b4Click%5 == 3)
                    b4.setText("2");

                if (b4Click%5 == 4)
                    b4.setText("3");

                if (b4Click%5 == 0)
                    b4.setText("4");
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

                if (b5Click%5 == 3)
                    b5.setText("2");

                if (b5Click%5 == 4)
                    b5.setText("3");

                if (b5Click%5 == 0)
                    b5.setText("4");
            }
        });


        //c stuff
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

                if (c1Click%5 == 3)
                    c1.setText("2");

                if (c1Click%5 == 4)
                    c1.setText("3");

                if (c1Click%5 == 0)
                    c1.setText("4");
            }
        });

        c2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick (View v) {

                c2Click++;

                if (c2Click%5 == 1) {
                    c2.setBackground(roundFilled);
                    c2.setText("");
                }

                if (c2Click%5 == 2) {
                    c2.setBackground(round);
                    c2.setText("1");
                }

                if (c2Click%5 == 3)
                    c2.setText("2");

                if (c2Click%5 == 4)
                    a1.setText("3");

                if (c2Click%5 == 0)
                    c2.setText("4");
            }
        });

        c3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick (View v) {

                c3Click++;

                if (c3Click%5 == 1) {
                    c3.setBackground(roundFilled);
                    c3.setText("");
                }

                if (c3Click%5 == 2) {
                    c3.setBackground(round);
                    c3.setText("1");
                }

                if (c3Click%5 == 3)
                    c3.setText("2");

                if (c3Click%5 == 4)
                    c3.setText("3");

                if (c3Click%5 == 0)
                    c3.setText("4");
            }
        });

        c4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick (View v) {

                c4Click++;

                if (c4Click%5 == 1) {
                    c4.setBackground(roundFilled);
                    c4.setText("");
                }

                if (c4Click%5 == 2) {
                    c4.setBackground(round);
                    c4.setText("1");
                }

                if (c4Click%5 == 3)
                    c4.setText("2");

                if (c4Click%5 == 4)
                    c4.setText("3");

                if (c4Click%5 == 0)
                    c4.setText("4");
            }
        });

        c5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick (View v) {

                c5Click++;

                if (c5Click%5 == 1) {
                    c5.setBackground(roundFilled);
                    c5.setText("");
                }

                if (c5Click%5 == 2) {
                    c5.setBackground(round);
                    c5.setText("1");
                }

                if (c5Click%5 == 3)
                    c5.setText("2");

                if (c5Click%5 == 4)
                    c5.setText("3");

                if (c5Click%5 == 0)
                    c5.setText("4");
            }
        });

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.action_bar, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(final MenuItem item) {
        switch (item.getItemId()) {

            case R.id.actionBarSettings:
                Intent intent = new Intent(WorkoutAActivity.this, SettingsActivity.class);
                startActivity(intent);
                return true;

            case R.id.actionBarHome:
                Intent intent2 = new Intent(WorkoutAActivity.this, HomeActivity.class);
                startActivity(intent2);
                return true;

            case R.id.actionBarLogout:
                auth.signOut();
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public void onResume() {
        super.onResume();

         /* set weights for current workout */
        mFirebaseDatabase.child(uid).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                User user = dataSnapshot.getValue(User.class);
                txtSq.setText(Integer.toString(user.squat));
                txtBP.setText(Integer.toString(user.bench));
                txtRow.setText(Integer.toString(user.row));
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                // Failed to read value
                // Log.w(TAG, "Failed to read value.", error.toException());

            }
        });
    }

}
