//package hitro.a5x5assistant;
//
//import android.content.Intent;
//import android.nfc.Tag;
//import android.os.Bundle;
//import android.support.design.widget.Snackbar;
//import android.support.v7.app.AppCompatActivity;
//import android.support.v7.widget.Toolbar;
//import android.text.TextUtils;
//import android.util.Log;
//import android.view.View;
//import android.widget.Button;
//import android.widget.EditText;
//import android.widget.Toast;
//
//
////firebase database imports
//import com.google.firebase.auth.FirebaseAuth;
//import com.google.firebase.auth.FirebaseUser;
//
//public class ProfileCreateActivity extends AppCompatActivity {
//
//    final String TAG = "ProfileCreateActivity";
//    private  String uid;
//    private EditText etName, etBodyweight, etSquat, etBench, etRow, etOHP, etDL;
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_profile_create);
//        Toolbar toolbar = findViewById(R.id.toolbar);
//        setSupportActionBar(toolbar);
//
//        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
//        uid = user.getUid();
//
//        Button createProfile = findViewById(R.id.btnCreateProfile);
//        etName = findViewById(R.id.etProfName);
//        etBodyweight =  findViewById(R.id.etProfBodyweight);
//        etSquat =  findViewById(R.id.etProfSquat);
//        etBench =  findViewById(R.id.etProfBench);
//        etRow =  findViewById(R.id.etProfRow);
//        etOHP =  findViewById(R.id.etProfOHP);
//        etDL =  findViewById(R.id.etProfDL);
//
//        createProfile.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                String name = etName.getText().toString().trim();
//                double body = Double.parseDouble(etBodyweight.getText().toString().trim());
//                double squat = Double.parseDouble(etSquat.getText().toString().trim());
//                double bench = Double.parseDouble(etBench.getText().toString().trim());
//                double row = Double.parseDouble(etRow.getText().toString().trim());
//                double ohp = Double.parseDouble(etOHP.getText().toString().trim());
//                double dl = Double.parseDouble(etDL.getText().toString().trim());
//
//                if (TextUtils.isEmpty(name) || TextUtils.isEmpty(etBodyweight.getText().toString())
//                        || TextUtils.isEmpty(etSquat.getText().toString()) ||
//                        TextUtils.isEmpty(etBench.getText().toString()) ||
//                        TextUtils.isEmpty(etRow.getText().toString()) ||
//                        TextUtils.isEmpty(etOHP.getText().toString()) ||
//                        TextUtils.isEmpty(etDL.getText().toString())){
//
//                    Toast toast = Toast.makeText(getApplicationContext(), "Please fill out all fields",
//                            Toast.LENGTH_LONG);
//                    toast.show();
//
//                } else {
//                    //stores profile in profiles table
//                    Profile profile = new Profile(body, squat, bench, row, ohp, dl);
//                    //dbProfiles.setValue(profile);
//                    Intent intent = new Intent(getApplicationContext(), HomeActivity.class);
//                    startActivity(intent);
//
//                }
//
//
//            }
//        });
//
//
//    }
//
//}
