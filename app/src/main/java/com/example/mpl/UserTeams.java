package com.example.mpl;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class UserTeams extends AppCompatActivity {

    String p,s;
    ArrayList<ModelUserTeams> arrayList = new ArrayList<ModelUserTeams>();
    LvUserTeamsAdapter adapter;
    ModelUserTeams model;
    ListView listView;

    String[] play = new String[] {"captain","vicecaptain","player3","player4","player5","player6","player7","player8"};
    String[] pts = new String[] { "s1", "s2", "s3", "s4", "s5", "s6", "s7", "s8",};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_player_teams);
        listView = findViewById(R.id.aptLvUserTeams);
        final Query userleaderboardQuery = FirebaseDatabase.getInstance().getReference(Constants.TBL_CURRENT_LEADERBOARD).child("Leaderboard-"+Constants.POSITION).child(Constants.RANK_PHONE);
        userleaderboardQuery.addValueEventListener(getuserleaderboardVLE);

    }

    ValueEventListener getuserleaderboardVLE = new ValueEventListener() {
        @Override
        public void onDataChange(DataSnapshot dataSnapshot) {
            try {
                arrayList.clear();
                for (int i=0;i<play.length;i++) {
                    p = "" + dataSnapshot.child(play[i]).getValue();
                    s = "" + dataSnapshot.child(pts[i]).getValue();
                    if (i==0)
                        p+=" (C)";
                    if (i==1)
                        p+=" (VC)";
                    if (p.equals("null"))
                    {
                        new ShowToast(getApplicationContext(),"No team data available");
                    }
                    model = new ModelUserTeams(p, s);
                    arrayList.add(model);
                }
            adapter = new LvUserTeamsAdapter(getApplicationContext(), arrayList);
            listView.setAdapter(adapter);

            } catch (Exception e) {
                Log.e("getuserleaderboardVLEe",e.toString());
            }
        }

        @Override
        public void onCancelled(DatabaseError databaseError) {

        }
    };
}
