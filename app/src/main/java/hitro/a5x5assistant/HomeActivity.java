package hitro.a5x5assistant;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class HomeActivity extends AppCompatActivity {

    private Button logout, exGuide, stats, progress;
    private FirebaseAuth.AuthStateListener authListener;
    private FirebaseAuth auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        //Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        //setSupportActionBar(toolbar);

        //get firebase auth instance
        auth = FirebaseAuth.getInstance();

        // instance of firebase user object
        final FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();

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

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(HomeActivity.this, WorkoutSelectActivity.class);
                startActivity(intent);

            }
        });

    }

    public void signOut() {
        auth.signOut();
    }

    @Override
    protected void onResume() {
        super.onResume();
        //progressBar.setVisibility(View.GONE);
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
