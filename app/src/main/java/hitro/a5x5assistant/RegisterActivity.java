package hitro.a5x5assistant;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

//firebase auth imports
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

//firebase database imports
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import com.google.firebase.auth.FirebaseUser;


public class RegisterActivity extends AppCompatActivity {

    private static final String TAG = "LoginAcivity";

    private EditText etEmail;
    private EditText etPW1;
    private EditText etPW2;
    private EditText etName;
    private Button reg;

    private FirebaseAuth mAuth;
    private ProgressBar progressBar;

    private DatabaseReference mFirebaseDatabase;
    private FirebaseDatabase mFirebaseInstance;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        // set up editTexts and extract text
        etEmail = (EditText) findViewById(R.id.etEmail);
        etPW1 = (EditText) findViewById(R.id.etPW1);
        etPW2 = (EditText) findViewById(R.id.etPW2);
        etName = (EditText) findViewById(R.id.etName);
        etEmail.setGravity(Gravity.CENTER);
        etPW1.setGravity(Gravity.CENTER);
        etPW2.setGravity(Gravity.CENTER);
        etName.setGravity(Gravity.CENTER);
        reg = (Button) findViewById(R.id.btnRegister);

        progressBar = (ProgressBar) findViewById(R.id.progressBar);

        mAuth = FirebaseAuth.getInstance();


        reg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String email = etEmail.getText().toString().trim();
                String password = etPW1.getText().toString().trim();
                final String name = etName.getText().toString();

                if (TextUtils.isEmpty(email)) {
                    Toast.makeText(getApplicationContext(), "Enter email address", Toast.LENGTH_SHORT).show();
                    return;
                }

                if (TextUtils.isEmpty(password)) {
                    Toast.makeText(getApplicationContext(), "Enter password", Toast.LENGTH_SHORT).show();
                    return;
                }

                if (password.length() < 6) {
                    Toast.makeText(getApplicationContext(), "Password too short, enter minimum 6 characters!", Toast.LENGTH_SHORT).show();
                    return;
                }

                //progressBar.setVisibility(View.VISIBLE);
                //create user
                mAuth.createUserWithEmailAndPassword(email, password)
                        .addOnCompleteListener(RegisterActivity.this, new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                Toast.makeText(RegisterActivity.this, "Successfully created new account", Toast.LENGTH_SHORT).show();

                                if (!task.isSuccessful()) {
                                    Toast.makeText(RegisterActivity.this, "Authentication failed." + task.getException(),
                                            Toast.LENGTH_SHORT).show();
                                } else {

                                   final FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
                                    String uid, email;
                                    uid = user.getUid();

                                    //firebase database references
                                    mFirebaseInstance = FirebaseDatabase.getInstance();
                                    mFirebaseDatabase = mFirebaseInstance.getReference("users"); //get reference to user node

                                    User userObj = new User(name);
                                    mFirebaseDatabase.child(uid).setValue(userObj);

                                    startActivity(new Intent(RegisterActivity.this, LoginActivity.class));
                                    finish();
                                }
                            }
                        });
                }
            });
        }

    @Override
    protected void onResume() {
        super.onResume();
    }
}
