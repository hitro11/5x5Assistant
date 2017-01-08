package hitro.a5x5assistant;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class HomeActivity extends AppCompatActivity {

    private Button logout, exGuide, stats, progress;
    private FirebaseAuth.AuthStateListener authListener;
    private FirebaseAuth auth;
    private FirebaseUser user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(HomeActivity.this, WorkoutSelectActivity.class);
                startActivity(intent);
            }
        });

        //get firebase auth instance
        auth = FirebaseAuth.getInstance();

        // instance of firebase user object
        user = FirebaseAuth.getInstance().getCurrentUser();

        //listens for a change in auth status, and redirects to login page if change detected,
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
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.action_bar, menu);
        return true;
    }


    @Override
    public boolean onOptionsItemSelected(final MenuItem item) {
        switch (item.getItemId()) {

            case R.id.actionBarHome:
                Intent intent2 = new Intent(HomeActivity.this, HomeActivity.class);
                startActivity(intent2);
                return true;

            case R.id.actionBarSettings:
                Intent intent = new Intent(HomeActivity.this, SettingsActivity.class);
                startActivity(intent);
                return true;

            case R.id.actionBarLogout:
                auth.signOut();
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }


    @Override
    public void onStart() {
        super.onStart();
        auth.addAuthStateListener(authListener);
    }

    @Override
    public void onStop() {
        super.onStop();
        if (authListener != null) {
            auth.removeAuthStateListener(authListener);
        }
    }
}
