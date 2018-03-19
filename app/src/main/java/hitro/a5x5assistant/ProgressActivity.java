package hitro.a5x5assistant;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
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
    TextView txtNoHistory;
    double ex1, ex2, ex3;
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

        if (dbHistory != null) {
            dbHistory.orderByChild("date").addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {

                    final FirebaseRecyclerAdapter adapter = new FirebaseRecyclerAdapter <History, ProgressHolder>
                            (History.class, R.layout.progress_card, ProgressHolder.class, dbHistory ) {
                        @Override
                        protected void populateViewHolder(ProgressHolder viewHolder, History model, int position) {

                            viewHolder.getTxtWorkout().setText(model.getWorkout());
                            viewHolder.getTxtDate().setText(model.getDate());

                            viewHolder.getTxtEx1DoneProg().setText(String.valueOf(model.isDoneEx1()));
                            viewHolder.getTxtEx2DoneProg().setText(String.valueOf(model.isDoneEx2()));
                            viewHolder.getTxtEx3DoneProg().setText(String.valueOf(model.isDoneEx3()));

                            if (model.getWorkout().equals("A")) {
                                viewHolder.getTxtEx1().setText(R.string.squat);
                                viewHolder.getTxtEx2().setText(R.string.bench);
                                viewHolder.getTxtEx3().setText(R.string.row);
                            }
                            else {
                                viewHolder.getTxtEx1().setText(R.string.squat);
                                viewHolder.getTxtEx2().setText(R.string.ohp);
                                viewHolder.getTxtEx3().setText(R.string.dl);
                            }

                            if (units.equals(getResources().getString(R.string.lb))){
                                viewHolder.getTxtEx1WProg().setText(String.valueOf(model.getEx1()));
                                viewHolder.getTxtEx2WProg().setText(String.valueOf(model.getEx2()));
                                viewHolder.getTxtEx3WProg().setText(String.valueOf(model.getEx3()));

                                viewHolder.getTxtUnit1Prog().setText(R.string.lb);
                                viewHolder.getTxtUnit2Prog().setText(R.string.lb);
                                viewHolder.getTxtUnit3Prog().setText(R.string.lb);
                            }
                            else {
                                ex1 = Math.round(model.getEx1() / 5.5) * 2.5;
                                ex2 = Math.round(model.getEx2() / 5.5) * 2.5;
                                ex3 = Math.round(model.getEx3() / 5.5) * 2.5;

                                viewHolder.getTxtEx1WProg().setText(String.valueOf(ex1));
                                viewHolder.getTxtEx2WProg().setText(String.valueOf(ex2));
                                viewHolder.getTxtEx3WProg().setText(String.valueOf(ex3));

                                viewHolder.getTxtUnit1Prog().setText(R.string.kg);
                                viewHolder.getTxtUnit2Prog().setText(R.string.kg);
                                viewHolder.getTxtUnit3Prog().setText(R.string.kg);
                            }
                        }
                    };
                    rv.setLayoutManager(new LinearLayoutManager(ProgressActivity.this));
                    rv.setAdapter(adapter);
                }

                @Override
                public void onCancelled(DatabaseError databaseError) {
                }
            });
        }
        else {
            txtNoHistory.setVisibility(View.VISIBLE);
        }
    }
}
