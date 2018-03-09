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

    private String email;
    private RadioButton btnLB;
    private RadioButton btnKG;
    private CardView cvEmail;
    private FirebaseUser user;
    private SharedPreferences.Editor spEditor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        try {
            user = FirebaseAuth.getInstance().getCurrentUser();
        } catch (NullPointerException e) {
            startActivity(new Intent(this, LoginActivity.class));
        }

        setContentView(R.layout.activity_settings);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), WorkoutSelectActivity.class);
                startActivity(intent);
            }
        });

        email = user.getEmail();
        TextView txtEmail = findViewById(R.id.txtEmail);
        txtEmail.setText(email);
        btnLB = findViewById(R.id.btnLB);
        btnKG = findViewById(R.id.btnKG);
        cvEmail = findViewById(R.id.cvEmail);
        spEditor = sharedPref.edit();

        // checks radio button automatically (depending on units)
        if (units.equals("lb")) {
            btnLB.setChecked(true);
        } else {
            btnKG.setChecked(true);
        }

        btnLB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                btnLB.setChecked(true);
                btnKG.setChecked(false);
                spEditor.putString("units", getString(R.string.lb)).apply();
            }
        });

        btnKG.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                btnKG.setChecked(true);
                btnLB.setChecked(false);
                spEditor.putString("units", getString(R.string.kg)).apply();
                Log.i("units", sharedPref.getString("units", "lb"));
            }
        });


    }
}