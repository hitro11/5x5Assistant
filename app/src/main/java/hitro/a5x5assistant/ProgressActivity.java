package hitro.a5x5assistant;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.TextView;


import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class ProgressActivity extends BaseActivity {

    static final String TAG = "ProgressActivity";
    FirebaseAuth auth;
    ArrayList <Integer> dates;
    RecyclerView rv;
    HistoryA historyA;
    HistoryB historyB;
    TextView txtNoHistory;
    double ex1, ex2, ex3;
    String workout;
    DatabaseReference dbHistory;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_progress);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        rv = findViewById(R.id.progRV);
        dates = new ArrayList<>();
        txtNoHistory = findViewById(R.id.txtNoHistory);
        txtNoHistory.setVisibility(View.GONE);

        dbHistory = FirebaseDatabase.getInstance().getReference("history/" + uid);
        dbHistory.orderByChild("date").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                    HistoryA history = dataSnapshot.getValue(HistoryA.class);
                    if (history == null) {
                       txtNoHistory.setVisibility(View.VISIBLE);
                    }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
            }
        });
//
//        if (history != null) {
//            final FirebaseRecyclerAdapter adapter = new FirebaseRecyclerAdapter <History, ProgressHolder>
//                    (History.class, R.layout.progress_card, ProgressHolder.class, dbHistory ) {
//                @Override
//                protected void populateViewHolder(ProgressHolder viewHolder, History model, int position) {
//
//                    viewHolder.txtWorkout.setText(model.getWorkout());
//                    viewHolder.txtDate.setText(model.getDate());
//
//                    if (model.getWorkout().equals("A")) {
//                        if (units.equals("lb")){
//                            viewHolder.txtSquatW.setText(String.valueOf(model.getSquat()));
//                            viewHolder.txtBenchW.setText(String.valueOf(model.getBench()));
//                            viewHolder.txtRowW.setText(String.valueOf(model.getRow()));
//
//                        } else {
//                            squatWeight = Math.floor(model.getSquat()/2 - model.getSquat()/20);
//                            benchWeight = Math.floor(model.getBench()/2 - model.getBench()/20);
//                            rowWeight = Math.floor(model.getRow()/2 - model.getRow()/20);
//
//                            viewHolder.txtSquatW.setText(String.valueOf(squatWeight));
//                            viewHolder.txtBenchW.setText(String.valueOf(benchWeight));
//                            viewHolder.txtRowW.setText(String.valueOf(rowWeight));
//                        }
//                        viewHolder.txtSquatDone.setText(model.getDoneSquat());
//                        viewHolder.txtBenchDone.setText(model.getDoneBench());
//                        viewHolder.txtRowDone.setText(model.getDoneRow());
//
//                        viewHolder.txtOHPW.setVisibility(View.GONE);
//                        viewHolder.txtDLW.setVisibility(View.GONE);
//                        viewHolder.txtOHPDone.setVisibility(View.GONE);
//                        viewHolder.txtDLDone.setVisibility(View.GONE);
//                    }
//
//                    else if (model.getWorkout().equals("B")) {
//                        if (units.equals("lb")){
//                            viewHolder.txtSquatW.setText(String.valueOf(model.getSquat()));
//                            viewHolder.txtOHPW.setText(String.valueOf(model.getOhp()));
//                            viewHolder.txtDLW.setText(String.valueOf(model.getDl()));
//                        } else {
//                            squatWeight = Math.floor(model.getSquat()/2 - model.getSquat()/20);
//                            ohpWeight = Math.floor(model.getOhp()/2 - model.getOhp()/20);
//                            dlWeight = Math.floor(model.getDl()/2 - model.getDl()/20);
//
//                            viewHolder.txtSquatW.setText(String.valueOf(squatWeight));
//                            viewHolder.txtBenchW.setText(String.valueOf(ohpWeight));
//                            viewHolder.txtRowW.setText(String.valueOf(dlWeight));
//                        }
//                        viewHolder.txtSquatDone.setText(model.getDoneSquat());
//                        viewHolder.txtOHPDone.setText(model.getDoneOHP());
//                        viewHolder.txtDLDone.setText(model.getDoneDL());
//
//                        viewHolder.txtBenchW.setVisibility(View.GONE);
//                        viewHolder.txtRowW.setVisibility(View.GONE);
//                        viewHolder.txtBenchDone.setVisibility(View.GONE);
//                        viewHolder.txtOHPDone.setVisibility(View.GONE);
//                    }
//                }
//            };
//
//            rv.setLayoutManager(new LinearLayoutManager(this));
//            rv.setAdapter(adapter);
//        }
    }
}
