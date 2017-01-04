package hitro.a5x5assistant;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;

public class GuideActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_guide);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        //button inits
        Button sq = (Button)findViewById(R.id.btnSquat);
        Button bp = (Button)findViewById(R.id.btnBP);
        Button row = (Button)findViewById(R.id.btnRow);
        Button ohp = (Button)findViewById(R.id.btnOHP);
        Button dl = (Button)findViewById(R.id.btnDL);


        sq.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick (View v) {
                Uri uri = Uri.parse("http://www.exrx.net/WeightExercises/Quadriceps/BBSquat.html");
                Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                startActivity(intent);
            }
        });

        bp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick (View v) {
                Uri uri = Uri.parse("http://www.exrx.net/WeightExercises/PectoralSternal/BBBenchPress.html");
                Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                startActivity(intent);
            }
        });

        row.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick (View v) {
                Uri uri = Uri.parse("http://www.exrx.net/WeightExercises/BackGeneral/BBBentOverRow.html");
                Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                startActivity(intent);
            }
        });

        ohp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick (View v) {
                Uri uri = Uri.parse("http://www.exrx.net/WeightExercises/DeltoidAnterior/BBMilitaryPress.html");
                Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                startActivity(intent);
            }
        });

        dl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick (View v) {
                Uri uri = Uri.parse("http://www.exrx.net/WeightExercises/ErectorSpinae/BBDeadlift.html");
                Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                startActivity(intent);
            }
        });
    }

}
