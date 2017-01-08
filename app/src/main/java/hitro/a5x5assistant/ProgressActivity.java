package hitro.a5x5assistant;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Iterator;


public class ProgressActivity extends AppCompatActivity {

    private FirebaseAuth auth;
    FirebaseUser user;
    String uid;
    FirebaseDatabase firebaseDB;
    DatabaseReference firebaseDBR;
    ArrayList <Integer> dates;
    RecyclerView rv;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_progress);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        rv = (RecyclerView)findViewById(R.id.progRV);

        auth = FirebaseAuth.getInstance();
        user = FirebaseAuth.getInstance().getCurrentUser();
        uid = user.getUid();
        dates = new ArrayList<>();
        firebaseDB = FirebaseDatabase.getInstance();
        firebaseDBR = firebaseDB.getReference("history").child(uid);


        firebaseDBR.orderByKey().limitToFirst(1).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                    History history = dataSnapshot.getValue(History.class);
                    dates.add(history.date);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
            }
        });

        final FirebaseRecyclerAdapter adapter = new FirebaseRecyclerAdapter <History, ProgressHolder>
                (History.class, R.layout.progress_card, ProgressHolder.class, firebaseDBR ) {
            @Override
            protected void populateViewHolder(ProgressHolder viewHolder, History model, int position) {

                viewHolder.txtWorkout.setText(model.getWorkout());
                viewHolder.txtDate.setText(Integer.toString(model.getDate() - 17128));

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


    @Override
    public void onResume () {
        super.onResume();

        firebaseDBR.orderByKey().limitToFirst(1).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                History history = dataSnapshot.getValue(History.class);
                dates.add(history.date);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
            }
        });

        final FirebaseRecyclerAdapter adapter = new FirebaseRecyclerAdapter <History, ProgressHolder>
                (History.class, R.layout.progress_card, ProgressHolder.class, firebaseDBR ) {
            @Override
            protected void populateViewHolder(ProgressHolder viewHolder, History model, int position) {

                viewHolder.txtWorkout.setText(model.getWorkout());
                viewHolder.txtDate.setText(Integer.toString(model.getDate() - 17128));

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
                Intent intent = new Intent(ProgressActivity.this, SettingsActivity.class);
                startActivity(intent);
                return true;

            case R.id.actionBarHome:
                Intent intent2 = new Intent(ProgressActivity.this, HomeActivity.class);
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
