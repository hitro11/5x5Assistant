package hitro.a5x5assistant;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.annotation.NonNull;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.firebase.ui.auth.AuthUI;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import java.util.Locale;

public class BaseActivity extends AppCompatActivity {

    final String TAG = "BaseActivity";
    String uid;
    SharedPreferences sharedPref;
    String units;
    DatabaseReference dbProfile;
    ValueEventListener profileListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Locale.getDefault();

        sharedPref = this.getSharedPreferences("userPref", Context.MODE_PRIVATE);
        units = sharedPref.getString("units", "lb");
        try {
            uid = FirebaseAuth.getInstance().getUid();
        } catch (NullPointerException e) {
            startActivity(new Intent(this, LoginActivity.class));
        }


    }

    @Override
    protected void onStart() {
        super.onStart();
        //Log.i(TAG, "onStart");
        checkUserSignedIn(FirebaseAuth.getInstance().getCurrentUser());
    }

    @Override
    protected void onResume() {
        super.onResume();
        //Log.i(TAG, "onResume");
        checkUserSignedIn(FirebaseAuth.getInstance().getCurrentUser());
        units = sharedPref.getString("units", "lb");
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

            case R.id.actionBarHome:
                Intent intent2 = new Intent(this, HomeActivity.class);
                startActivity(intent2);
                return true;

            case R.id.actionBarSettings:
                Intent intent = new Intent(this, SettingsActivity.class);
                startActivity(intent);
                return true;

            case R.id.actionBarLogout:
                new AlertDialog.Builder(this)
                        .setMessage("Are you sure you want to sign out?")
                        .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                signout();
                            }
                        })
                        .setNegativeButton("No", null)
                        .show();
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }

    //listens for a change in auth status, and redirects to login page if change detected,
    public void checkUserSignedIn(final FirebaseUser user){
        if (user == null) {
            signout();
        }
    }

    public void readAndSetValuesDB (final View[] weightViews, final TextView[] txtUnits, final String workout) {
        dbProfile = FirebaseDatabase.getInstance().getReference("profiles/" + uid);

        profileListener = new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                Profile profile = dataSnapshot.getValue(Profile.class);
                double body = profile.getBody();
                double squat = profile.getSquat();
                double bench = profile.getBench();
                double row = profile.getRow();
                double ohp = profile.getOhp();
                double dl = profile.getDl();

                if (units.equals(getString(R.string.kg))) {
                    body = Math.round(body / 5.5) * 2.5;
                    squat = Math.round(squat / 5.5) * 2.5;
                    bench = Math.round(bench / 5.5) * 2.5;
                    row = Math.round(row / 5.5) * 2.5;
                    ohp = Math.round(ohp / 5.5) * 2.5;
                    dl = Math.round(dl / 5.5) * 2.5;

                    for (int i = 0; i < txtUnits.length; i++) {
                        txtUnits[i].setText(R.string.kg);
                    }
                }

                double[] values = new double[] {body, squat, bench, row, ohp, dl};

                if (workout.equals("A")) {
                    ((TextView) weightViews[0]).setText(String.valueOf(squat));
                    ((TextView) weightViews[1]).setText(String.valueOf(bench));
                    ((TextView) weightViews[2]).setText(String.valueOf(row));
                    return;
                }
                if (workout.equals("B")) {
                    ((TextView) weightViews[0]).setText(String.valueOf(squat));
                    ((TextView) weightViews[1]).setText(String.valueOf(ohp));
                    ((TextView) weightViews[2]).setText(String.valueOf(dl));
                    return;
                }
                if (workout.equals("")) {
                    for (int i = 0; i < weightViews.length; i++) {
                        ((TextView) weightViews[i]).setText(String.valueOf(values[i]));
                    }
                }
            }
            @Override
            public void onCancelled(DatabaseError databaseError) {
                Log.w(TAG, "loadPost:onCancelled", databaseError.toException());
            }
        };

        dbProfile.addValueEventListener(profileListener);
    }

    public double[] finishWorkout (String workout, EditText etEx1, EditText etEx2, EditText etEx3) {
        if (workout.equals("A")) {


        } else {

        }
        return new double[]{};
    }
    void signout() {
        AuthUI.getInstance()
                .signOut(this).addOnCompleteListener(new OnCompleteListener<Void>() {
            public void onComplete(@NonNull Task<Void> task) {
                startActivity(new Intent(getApplicationContext(), LoginActivity.class));
                finish();
            }
        });
    }

}
