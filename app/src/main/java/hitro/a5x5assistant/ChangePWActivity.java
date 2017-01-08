package hitro.a5x5assistant;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.EmailAuthCredential;
import com.google.firebase.auth.EmailAuthProvider;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class ChangePWActivity extends AppCompatActivity {

    private FirebaseAuth auth;
    private FirebaseUser user;
    private EditText etChangePWOld, etChangePWNew1, etChangePWNew2;
    private Button btnChangePW;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_pw);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ChangePWActivity.this, WorkoutSelectActivity.class);
                startActivity(intent);
            }
        });
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        auth = FirebaseAuth.getInstance();
        user = FirebaseAuth.getInstance().getCurrentUser();

        etChangePWOld = (EditText) findViewById(R.id.etChangePWold);
        etChangePWNew1 = (EditText) findViewById(R.id.etChangePWNew);
        etChangePWNew2 = (EditText) findViewById(R.id.etChangePWNew2);
        btnChangePW = (Button) findViewById(R.id.btnChangePW);


        /* Check if old password is correct (by re-authenticating user) */


        btnChangePW.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                final String pwOld = etChangePWOld.getText().toString();
                final String pwNew1 = etChangePWNew1.getText().toString();
                final String pwNew2 = etChangePWNew2.getText().toString();
                final AuthCredential credential = EmailAuthProvider.getCredential(user.getEmail(), pwOld);

                if (! pwNew1.equals(pwNew2)) {
                    Toast.makeText(getApplicationContext(), "New passwords don't match", Toast.LENGTH_SHORT).show();
                }

                if (pwNew1.equals(pwOld)) {
                    Toast.makeText(getApplicationContext(), "New password cannot be the same as old password", Toast.LENGTH_SHORT).show();
                }

                if (pwNew1.length() < 6) {
                    Toast.makeText(getApplicationContext(), "Password must be at least 6 characters long", Toast.LENGTH_SHORT).show();
                }

                user.reauthenticate(credential).addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        user.updatePassword(pwNew1)
                                .addOnCompleteListener(new OnCompleteListener<Void>() {
                                    @Override
                                    public void onComplete(@NonNull Task<Void> task) {
                                        if (task.isSuccessful()) {
                                            Toast.makeText(ChangePWActivity.this, "Password is updated!", Toast.LENGTH_SHORT).show();
                                        } else {
                                            Toast.makeText(ChangePWActivity.this, "Failed to update password!", Toast.LENGTH_SHORT).show();
                                        }
                                    }
                                });
                    }
                });

            }
        });

    }


    @Override
    public void onResume() {
        super.onResume();

        btnChangePW.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                final String pwOld = etChangePWOld.getText().toString();
                final String pwNew1 = etChangePWNew1.getText().toString();
                final String pwNew2 = etChangePWNew2.getText().toString();
                final AuthCredential credential = EmailAuthProvider.getCredential(user.getEmail(), pwOld);

                if (! pwNew1.equals(pwNew2)) {
                    Toast.makeText(getApplicationContext(), "New passwords don't match", Toast.LENGTH_SHORT).show();
                }

                if (pwNew1.equals(pwOld)) {
                    Toast.makeText(getApplicationContext(), "New password cannot be the same as old password", Toast.LENGTH_SHORT).show();
                }

                if (pwNew1.length() < 6) {
                    Toast.makeText(getApplicationContext(), "Password must be at least 6 characters long", Toast.LENGTH_SHORT).show();
                }

                user.reauthenticate(credential).addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        user.updatePassword(pwNew1)
                                .addOnCompleteListener(new OnCompleteListener<Void>() {
                                    @Override
                                    public void onComplete(@NonNull Task<Void> task) {
                                        if (task.isSuccessful()) {
                                            Toast.makeText(ChangePWActivity.this, "Password is updated!", Toast.LENGTH_SHORT).show();
                                        } else {
                                            Toast.makeText(ChangePWActivity.this, "Failed to update password!", Toast.LENGTH_SHORT).show();
                                        }
                                    }
                                });
                    }
                });

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
                Intent intent = new Intent(ChangePWActivity.this, SettingsActivity.class);
                startActivity(intent);
                return true;

            case R.id.actionBarHome:
                Intent intent2 = new Intent(ChangePWActivity.this, HomeActivity.class);
                startActivity(intent2);
                return true;

            case R.id.actionBarLogout:
                auth.signOut();
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }


}
