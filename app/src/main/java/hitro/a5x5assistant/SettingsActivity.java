package hitro.a5x5assistant;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.RadioButton;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class SettingsActivity extends AppCompatActivity {

    private FirebaseAuth auth;
    private String email;
    private RadioButton btnLB;
    private RadioButton btnKG;
    private CardView cvEmail, cvPW;

    public static int units; //tracks units (0 = lb, 1 = kg)

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

        auth = FirebaseAuth.getInstance();;
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        email = user.getEmail().trim();
        TextView txtEmail = (TextView) findViewById(R.id.txtEmail);
        txtEmail.setText(email);
        btnLB = (RadioButton)findViewById(R.id.btnLB);
        btnKG = (RadioButton) findViewById(R.id.btnKG);
        cvEmail = (CardView) findViewById(R.id.cvEmail);
        cvPW = (CardView) findViewById(R.id.cvPW);

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

        // checks radiobutton automatically (depending on units)
        if (units == 0) {
            btnLB.setChecked(true);
        }
        else btnKG.setChecked(true);

        btnLB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                units = 0; //changes units to kg if clicked.
                btnKG.setChecked(false);
            }
        });

        btnKG.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                units = 1; //changes units to kg if clicked.
                btnLB.setChecked(false);
            }
        });
    }


    @Override
    public void onResume () {
        super.onResume();

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

        // checks radiobutton automatically (depending on units)
        if (units == 0) {
            btnLB.setChecked(true);
        }
        else btnKG.setChecked(true);

        btnLB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                units = 0; //changes units to kg if clicked.
                btnKG.setChecked(false);
            }
        });

        btnKG.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                units = 1; //changes units to kg if clicked.
                btnLB.setChecked(false);
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
                Intent intent = new Intent(SettingsActivity.this, SettingsActivity.class);
                startActivity(intent);
                return true;

            case R.id.actionBarHome:
                Intent intent2 = new Intent(SettingsActivity.this, HomeActivity.class);
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
