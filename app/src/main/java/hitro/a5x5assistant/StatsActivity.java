package hitro.a5x5assistant;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;


public class StatsActivity extends BaseActivity {

    private static final String TAG = "StatsActivity";
    private EditText etBody, etSquat, etBench, etRow, etOHP, etDL;
    private EditText etValues[];
    private TextView[] etUnits;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stats);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(StatsActivity.this, WorkoutSelectActivity.class));
            }
        });

        units = sharedPref.getString("units", "");
        Button btnStats = findViewById(R.id.btnUpdStats);
        etBody = findViewById(R.id.etBW);
        etSquat = findViewById(R.id.etSquat);
        etBench = findViewById(R.id.etBench);
        etRow = findViewById(R.id.etRow);
        etOHP = findViewById(R.id.etOHP);
        etDL = findViewById(R.id.etDL);
        etValues = new EditText[] {etBody, etSquat, etBench, etRow, etOHP, etDL};
        etUnits = new TextView[] {findViewById(R.id.txtUnits1s), findViewById(R.id.txtUnits2s),
                                    findViewById(R.id.txtUnits3s), findViewById(R.id.txtUnits4s),
                                    findViewById(R.id.txtUnits5s), findViewById(R.id.txtUnits6s)};


        Intent intent = getIntent();
        final boolean isRegistered = intent.getBooleanExtra("IS_REGISTERED", true);
        if (isRegistered) {
            readAndSetValuesDB(etValues, etUnits, "");
        }
        else {
            for (EditText et : etValues) {
                et.setText(String.valueOf(0));
            }
        }


        //updates stats when "Update Stats" button is pressed
        btnStats.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                double bodyNew = Double.valueOf(etBody.getText().toString().trim());
                double squatNew = Double.valueOf(etSquat.getText().toString().trim());
                double benchNew = Double.valueOf(etBench.getText().toString().trim());
                double rowNew = Double.valueOf(etRow.getText().toString().trim());
                double ohpNew = Double.valueOf(etOHP.getText().toString().trim());
                double dlNew = Double.valueOf(etDL.getText().toString().trim());
                units = sharedPref.getString("units", "lb");

                if (units.equals(getString(R.string.lb))) {
                    if (bodyNew % 5 == 0 && squatNew % 5 == 0 && benchNew % 5 == 0 && rowNew % 5 == 0
                            && ohpNew % 5 == 0 && dlNew % 5 == 0) {

                        dbProfile.setValue(new Profile(bodyNew, squatNew, benchNew, rowNew, ohpNew, dlNew));
                        Toast.makeText(getApplicationContext(), "Stats successfully updated",
                                Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(getApplicationContext(), "Weights must be a multiple of 5",
                                Toast.LENGTH_LONG).show();
                    }
                }

                 else {
                    if (bodyNew % 2.5 == 0 && squatNew % 2.5 == 0 && benchNew % 2.5 == 0 &&
                            rowNew % 2.5 == 0 && ohpNew % 2.5 == 0 && dlNew % 2.5 == 0) {

                        //lb = round( (kg * 2.2) / 5) * 5
                        dbProfile.setValue( new Profile (
                                Math.round(bodyNew * 0.44) * 5, Math.round(squatNew * 0.44) * 5,
                                Math.round(benchNew * 0.44) * 5, Math.round(rowNew * 0.44) * 5,
                                Math.round(ohpNew * 0.44) * 5, Math.round(dlNew * 0.44) * 5
                        ));
                        Toast.makeText(getApplicationContext(), "Stats successfully updated",
                                Toast.LENGTH_SHORT).show();
                    }
                    else {
                        Toast.makeText(getApplicationContext(), "Weights must be a multiple of 2.5",
                                Toast.LENGTH_LONG).show();
                    }
                }
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        readAndSetValuesDB(etValues, etUnits, "");

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        dbProfile.removeEventListener(profileListener);

    }
}