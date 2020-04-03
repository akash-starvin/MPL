package com.example.mpl;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

public class CourtLayout extends AppCompatActivity {

    TextView p1,p2,p3,p4,p5,p6,p7,p8,s1,s2,s3,s4,s5,s6,s7,s8;
    String[] play = new String[] {"captain","vicecaptain","player3","player4","player5","player6","player7","player8"};
    String[] pts = new String[] { "s1", "s2", "s3", "s4", "s5", "s6", "s7", "s8",};
    String[] player = new String[8];
    String[] score = new String[8];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_court_layout);

        p1 = findViewById(R.id.p1);
        p2 = findViewById(R.id.p2);
        p3 = findViewById(R.id.p3);
        p4 = findViewById(R.id.p4);
        p5 = findViewById(R.id.p5);
        p6 = findViewById(R.id.p6);
        p7 = findViewById(R.id.p7);
        p8 = findViewById(R.id.p8);
        s1 = findViewById(R.id.s1);
        s2 = findViewById(R.id.s2);
        s3 = findViewById(R.id.s3);
        s4 = findViewById(R.id.s4);
        s5 = findViewById(R.id.s5);
        s6 = findViewById(R.id.s6);
        s7 = findViewById(R.id.s7);
        s8 = findViewById(R.id.s8);

        final Query userleaderboardQuery = FirebaseDatabase.getInstance().getReference(Constants.TBL_CURRENT_LEADERBOARD).child("Leaderboard-"+Constants.POSITION).child(Constants.RANK_PHONE);
        userleaderboardQuery.addValueEventListener(getuserleaderboardVLE);
    }

    ValueEventListener getuserleaderboardVLE = new ValueEventListener() {
        @Override
        public void onDataChange(DataSnapshot dataSnapshot) {
            try {
                for (int i=0;i<play.length;i++) {
                    player[i] = "" + dataSnapshot.child(play[i]).getValue();
                    score[i] = "" + dataSnapshot.child(pts[i]).getValue();
                    if (i==0)
                        player[i]+=" (C)";
                    if (i==1)
                        player[i]+=" (VC)";
                    if (player[i].equals("null"))
                    {
                        new ShowToast(getApplicationContext(),"No team data available");
                    }
                }
                p1.setText(player[0]);
                p2.setText(player[1]);
                p3.setText(player[2]);
                p4.setText(player[3]);
                p5.setText(player[4]);
                p6.setText(player[5]);
                p7.setText(player[6]);
                p8.setText(player[7]);
                s1.setText(score[0]);
                s2.setText(score[1]);
                s3.setText(score[2]);
                s4.setText(score[3]);
                s5.setText(score[4]);
                s6.setText(score[5]);
                s7.setText(score[6]);
                s8.setText(score[7]);
            } catch (Exception e) {
                Log.e("getuserleaderboardVLEe",e.toString());
            }
        }

        @Override
        public void onCancelled(DatabaseError databaseError) {

        }
    };
}
