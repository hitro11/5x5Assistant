package hitro.a5x5assistant;

import android.content.Intent;
import android.nfc.Tag;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


//firebase database imports
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class ProfileCreateActivity extends AppCompatActivity {

    final String TAG = "ProfileCreateActivity";
    private DatabaseReference dbProfiles;
    private  String uid;
    private EditText etName, etBodyweight, etSquat, etBench, etRow, etOHP, etDL;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_create);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        uid = user.getUid();
        dbProfiles = FirebaseDatabase.getInstance().getReference("profiles"); //get reference to user node

        Button createProfile = (Button)findViewById(R.id.btnCreateProfile);
        etName = (EditText)findViewById(R.id.etProfName);
        etBodyweight =  (EditText)findViewById(R.id.etProfBodyweight);
        etSquat =  (EditText)findViewById(R.id.etProfSquat);
        etBench =  (EditText)findViewById(R.id.etProfBench);
        etRow =  (EditText)findViewById(R.id.etProfRow);
        etOHP =  (EditText)findViewById(R.id.etProfOHP);
        etDL =  (EditText)findViewById(R.id.etProfDL);

        createProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name = etName.getText().toString().trim();
                String bodyweight = etBodyweight.getText().toString().trim();
                String squat = etSquat.getText().toString().trim();
                String bench = etBench.getText().toString().trim();
                String row = etRow.getText().toString().trim();
                String ohp = etOHP.getText().toString().trim();
                String dl = etDL.getText().toString().trim();

                if (TextUtils.isEmpty(name) || TextUtils.isEmpty(name) || TextUtils.isEmpty(bodyweight)
                        || TextUtils.isEmpty(squat) || TextUtils.isEmpty(bench) || TextUtils.isEmpty(row)
                        || TextUtils.isEmpty(ohp) || TextUtils.isEmpty(dl)) {

                    Toast toast = Toast.makeText(getApplicationContext(), "Please fill out all fields", Toast.LENGTH_LONG);
                    toast.show();

                } else {
                    //stores profile in profiles table
                    Profile profile = new Profile(name, bodyweight, squat, bench, row, ohp, dl);
                    dbProfiles.child(uid).setValue(profile);
                    Intent intent = new Intent(getApplicationContext(), HomeActivity.class);
                    startActivity(intent);

                }


            }
        });


    }

}
