package hitro.a5x5assistant;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

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


public class ProgressActivity extends BaseActivity {

    static final String TAG = "ProgressActivity";
    private FirebaseAuth auth;
    FirebaseUser user;
    String uid;
    DatabaseReference dbHistory;
    ArrayList <Integer> dates;
    RecyclerView rv;
    History history;
    TextView txtNoHistory;


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
        dbHistory = FirebaseDatabase.getInstance().getReference("history").child(uid);
        txtNoHistory = (TextView)findViewById(R.id.txtNoHistory);
        txtNoHistory.setVisibility(View.GONE);

        dbHistory.orderByChild("date").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                    history = dataSnapshot.getValue(History.class);
                    if (history == null) {
                       txtNoHistory.setVisibility(View.VISIBLE);
                    }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
            }
        });

        if (history != null) {
            final FirebaseRecyclerAdapter adapter = new FirebaseRecyclerAdapter <History, ProgressHolder>
                    (History.class, R.layout.progress_card, ProgressHolder.class, dbHistory ) {
                @Override
                protected void populateViewHolder(ProgressHolder viewHolder, History model, int position) {

                    viewHolder.txtWorkout.setText(model.getWorkout());
                    viewHolder.txtDate.setText(model.getDate());

                    if (model.getWorkout().equals("A")) {
                        viewHolder.txtSquatW.setText(String.valueOf(model.getSquat()));
                        viewHolder.txtBenchW.setText(String.valueOf(model.getBench()));
                        viewHolder.txtRowW.setText(String.valueOf(model.getRow()));
                        viewHolder.txtSquatDone.setText(model.getDoneSquat());
                        viewHolder.txtBenchDone.setText(model.getDoneBench());
                        viewHolder.txtRowDone.setText(model.getDoneRow());

                        viewHolder.txtOHPW.setVisibility(View.GONE);
                        viewHolder.txtDLW.setVisibility(View.GONE);
                        viewHolder.txtOHPDone.setVisibility(View.GONE);
                        viewHolder.txtDLDone.setVisibility(View.GONE);

                    } else {
                        viewHolder.txtSquatW.setText(String.valueOf(model.getSquat()));
                        viewHolder.txtOHPW.setText(String.valueOf(model.getOhp()));
                        viewHolder.txtDLW.setText(String.valueOf(model.getRow()));
                        viewHolder.txtSquatDone.setText(model.getDoneSquat());
                        viewHolder.txtOHPDone.setText(model.getDoneOHP());
                        viewHolder.txtDLDone.setText(model.getDoneDL());

                        viewHolder.txtBenchW.setVisibility(View.GONE);
                        viewHolder.txtRowW.setVisibility(View.GONE);
                        viewHolder.txtBenchDone.setVisibility(View.GONE);
                        viewHolder.txtOHPDone.setVisibility(View.GONE);
                    }
                }
            };

            rv.setLayoutManager(new LinearLayoutManager(this));
            rv.setAdapter(adapter);
        }
    }
}
