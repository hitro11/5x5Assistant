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

import com.firebase.ui.auth.AuthUI;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.Locale;

public class BaseActivity extends AppCompatActivity {

    final String TAG = "BaseActivity";
    SharedPreferences sharedPref;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_base);
        Locale.getDefault();

        sharedPref = getSharedPreferences("userPref", Context.MODE_PRIVATE);

    }

    void signout() {
        AuthUI.getInstance()
                .signOut(this)
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    public void onComplete(@NonNull Task<Void> task) {
                        startActivity(new Intent(getApplicationContext(), LoginActivity.class));
                    }
                });
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
            finish();
        } else {
            Log.i(TAG, user.getUid());
        }
    }
}
