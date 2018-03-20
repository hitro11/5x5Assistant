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

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class ProgressActivity extends BaseActivity {

    static final String TAG = "ProgressActivity";
    FirebaseAuth auth;
    ArrayList <Integer> dates;
    RecyclerView rv;
    TextView txtNoHistory;
    double ex1, ex2, ex3;
    DatabaseReference dbHistory;
    String DB_DATE_FORMAT = "yyyy-MMM-dd HH:mm:ss";
    String DISP_DATE_FORMAT = "EEE dd/MM/yyyy";
    Date dTemp;

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

                            //String date = model.getDate();
                            //date = date.substring(0,11);
                            SimpleDateFormat sdf = new SimpleDateFormat(DB_DATE_FORMAT);
                            try {
                                dTemp = sdf.parse(model.getDate());
                            } catch (ParseException e) {
                                e.printStackTrace();
                            }
                            sdf.applyPattern(DISP_DATE_FORMAT);
                            String date = sdf.format(dTemp);
                            viewHolder.getTxtDate().setText(date);

                            viewHolder.getTxtEx1DoneProg().setText(exerciseDoneFormatter(model.isDoneEx1()));
                            viewHolder.getTxtEx2DoneProg().setText(exerciseDoneFormatter(model.isDoneEx2()));
                            viewHolder.getTxtEx3DoneProg().setText(exerciseDoneFormatter(model.isDoneEx3()));

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


                                viewHolder.getTxtEx1WProg().setText(String.format(LB_FORMAT,model.getEx1()));
                                viewHolder.getTxtEx2WProg().setText(String.format(LB_FORMAT,model.getEx2()));
                                viewHolder.getTxtEx3WProg().setText(String.format(LB_FORMAT,model.getEx3()));

                                viewHolder.getTxtUnit1Prog().setText(R.string.lb);
                                viewHolder.getTxtUnit2Prog().setText(R.string.lb);
                                viewHolder.getTxtUnit3Prog().setText(R.string.lb);
                            }
                            else {
                                ex1 = Math.round(model.getEx1() / 5.5) * 2.5;
                                ex2 = Math.round(model.getEx2() / 5.5) * 2.5;
                                ex3 = Math.round(model.getEx3() / 5.5) * 2.5;

                                viewHolder.getTxtEx1WProg().setText(String.format(KG_FORMAT, ex1));
                                viewHolder.getTxtEx2WProg().setText(String.format(KG_FORMAT, ex2));
                                viewHolder.getTxtEx3WProg().setText(String.format(KG_FORMAT, ex3));

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

    String exerciseDoneFormatter (boolean isDone) {
        if (isDone) {
            return "\u2714";
        }
        else {
            return "\u2716";
        }
    }
}
