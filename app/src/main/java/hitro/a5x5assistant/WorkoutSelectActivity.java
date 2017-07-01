package hitro.a5x5assistant;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import com.google.firebase.auth.FirebaseAuth;

public class WorkoutSelectActivity extends BaseActivity {

    Button workoutA, workoutB;
    private FirebaseAuth auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_workout_select);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        auth = FirebaseAuth.getInstance();

        workoutA = (Button)findViewById(R.id.button_wa);
        workoutA.setOnClickListener(new Button.OnClickListener() {
            public void onClick(View view){
                Intent intent = new Intent(WorkoutSelectActivity.this, WorkoutAActivity.class);
                startActivity(intent);
            }
        });

        workoutB = (Button)findViewById(R.id.button_wb);
        workoutB.setOnClickListener(new Button.OnClickListener() {
            public void onClick(View view){
                Intent intent = new Intent(WorkoutSelectActivity.this, WorkoutBActivity.class);
                startActivity(intent);
            }
        });
    }

    @Override
    public void onResume () {
        super.onResume();

    }
}
