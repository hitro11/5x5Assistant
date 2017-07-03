package hitro.a5x5assistant;

import android.content.DialogInterface;
import android.content.Intent;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import com.facebook.FacebookSdk;
import com.facebook.appevents.AppEventsLogger;

import android.util.Log;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.Signature;
import android.util.Base64;


public class HomeActivity extends BaseActivity {

    private Button logout, exGuide, stats, progress;
    private FirebaseAuth.AuthStateListener authListener;
    private FirebaseUser user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        //generates dev hash key
        /*
        try {
            PackageInfo info = getPackageManager().getPackageInfo(
                    getPackageName(),
                    PackageManager.GET_SIGNATURES);
            for (Signature signature : info.signatures) {
                MessageDigest md = MessageDigest.getInstance("SHA");
                md.update(signature.toByteArray());
                Log.d("KeyHash:", Base64.encodeToString(md.digest(), Base64.DEFAULT));
            }
        }
        catch (PackageManager.NameNotFoundException e) {

        }
        catch (NoSuchAlgorithmException e) {

        }
        */

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), WorkoutSelectActivity.class);
                startActivity(intent);
            }
        });

        user = FirebaseAuth.getInstance().getCurrentUser();



        /* logic for button clicks */

        progress = (Button)findViewById(R.id.btnProg);
        progress.setOnClickListener(new Button.OnClickListener() {
            public void onClick(View view){
                Intent intent = new Intent(HomeActivity.this, ProgressActivity.class);
                startActivity(intent);
                 }
            }
        );

        exGuide = (Button)findViewById(R.id.btnGuide);
        exGuide.setOnClickListener(new Button.OnClickListener() {
            public void onClick(View view){
                Intent intent = new Intent(HomeActivity.this, GuideActivity.class);
                startActivity(intent);
              }
            }
        );

        stats = (Button)findViewById(R.id.btnStats);
        stats.setOnClickListener(new Button.OnClickListener() {
                                       public void onClick(View view){
                                           Intent intent = new Intent(HomeActivity.this, StatsActivity.class);
                                           startActivity(intent);
                                       }
                                   }
        );

    }


    @Override
    public void onResume () {
        super.onResume();

        authListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser user = firebaseAuth.getCurrentUser();
                if (user == null) {
                    // user auth state is changed - user is null
                    // launch login activity
                    startActivity(new Intent(HomeActivity.this, LoginActivity.class));
                    finish();
                }
            }
        };
    }

    @Override
    public void onStart() {
        super.onStart();
       // auth.addAuthStateListener(authListener);
    }

    @Override
    public void onStop() {
        super.onStop();
        if (authListener != null) {
           // auth.removeAuthStateListener(authListener);
        }
    }

}
