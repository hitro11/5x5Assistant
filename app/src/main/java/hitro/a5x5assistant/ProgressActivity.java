package hitro.a5x5assistant;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;


public class ProgressActivity extends AppCompatActivity {

    RecyclerView rv;
    FirebaseRecyclerAdapter adapter;

    FirebaseDatabase firebaseDB;
    DatabaseReference firebaseDBR;
    String uid;
    FirebaseUser user;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_progress);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        rv = (RecyclerView)findViewById(R.id.progRV);

        user = FirebaseAuth.getInstance().getCurrentUser();
        uid = user.getUid();
        firebaseDB = FirebaseDatabase.getInstance();
        firebaseDBR = firebaseDB.getReference("history").child(uid);

        adapter = new FirebaseRecyclerAdapter <History, ProgressHolder>
                (History.class, R.layout.progress_card, ProgressHolder.class, firebaseDBR ) {
            @Override
            protected void populateViewHolder(ProgressHolder viewHolder, History model, int position) {

                viewHolder.txtWorkout.setText(model.getWorkout());
                viewHolder.txtDate.setText(model.getDate());

                viewHolder.txtSquatW.setText(model.getSquat());
                viewHolder.txtBenchW.setText(model.getBenchW());
                viewHolder.txtRowW.setText(model.getRowW());
                viewHolder.txtOHPW.setText(model.getOhpW());
                viewHolder.txtDLW.setText(model.getDlW());

                viewHolder.txtSquatDone.setText(model.getDoneSquat());
                viewHolder.txtBenchDone.setText(model.getDoneBench());
                viewHolder.txtRowDone.setText(model.getDoneRow());
                viewHolder.txtOHPDone.setText(model.getDoneOHP());
                viewHolder.txtDLDone.setText(model.getDoneDL());
            }
        };

        rv.setLayoutManager(new LinearLayoutManager(this));
        rv.setAdapter(adapter);
    }

}
