package hitro.a5x5assistant;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.RadioButton;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class SettingsActivity extends BaseActivity {

    private FirebaseAuth auth;
    private String email;
    private RadioButton btnLB;
    private RadioButton btnKG;
    private CardView cvEmail, cvPW;
    private SharedPreferences.Editor spEditor;
    private String units;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(SettingsActivity.this, WorkoutSelectActivity.class);
                startActivity(intent);
            }
        });

        units = sharedPref.getString("units", "");
        auth = FirebaseAuth.getInstance();
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        email = user.getEmail().trim();
        TextView txtEmail = (TextView) findViewById(R.id.txtEmail);
        txtEmail.setText(email);
        btnLB = (RadioButton) findViewById(R.id.btnLB);
        btnKG = (RadioButton) findViewById(R.id.btnKG);
        cvEmail = (CardView) findViewById(R.id.cvEmail);
        cvPW = (CardView) findViewById(R.id.cvPW);
        spEditor = sharedPref.edit();

        cvEmail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(SettingsActivity.this, ChangeEmailActivity.class);
                startActivity(intent);
            }
        });

        cvPW.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(SettingsActivity.this, ChangePWActivity.class);
                startActivity(intent);
            }
        });

        // checks radio button automatically (depending on units)
        if (units.equals("lb")) {
            btnLB.setChecked(true);
        } else {
            btnKG.setChecked(true);
        }

        btnLB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                btnKG.setChecked(false);
                spEditor.putString("units", "lb");
                spEditor.apply();
            }
        });

        btnKG.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                btnLB.setChecked(false);
                spEditor.putString("units", "kg");
                spEditor.apply();
            }
        });

        Log.i("units", units);
    }
}