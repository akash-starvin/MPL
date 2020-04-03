package com.example.mpl;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class MatchLeaderboard extends AppCompatActivity {

    String t1, t2, status,fbranktotal,fbrankname;
    String[] players = new String[10];
    String[] points = new String[]{"0","0","0","0","0","0","0","0","0","0"};
    String fbtotal, fbname,fbscore;
    int total=0,cap,vc,rank,x,fbtot=0;
    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference myRef = database.getReference(Constants.TBL_CURRENT_LEADERBOARD);
    DatabaseReference myRef2 = database.getReference(Constants.TBL_GLOBAL_LEADERBOARD);
    ArrayList<ModelRanking> arrayList = new ArrayList<ModelRanking>();
    LvCurrentRankingAdapter adapter;
    ModelRanking model;
    int tota;
    SharedPreferences sh;
    ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_match_leaderboard);
        listView = findViewById(R.id.amlLvRanking);
        if(!Constants.POSITION.equals("null")) {

            final Query userleaderboardQuery = FirebaseDatabase.getInstance().getReference(Constants.TBL_CURRENT_LEADERBOARD).child("Leaderboard-" + Constants.POSITION).child(Constants.PHONE);
            userleaderboardQuery.addValueEventListener(getuserleaderboardVLE);

        }

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if(!Constants.STATUS.equals("Select Players")) {
                    Constants.RANK_PHONE = arrayList.get(position).getPhone();
                    Intent intent = new Intent(getApplicationContext(), CourtLayout.class);
                    startActivity(intent);
                }
                else
                {
                    new ShowToast(getApplicationContext(),"User teams will be available after match starts");
                }
            }
        });

    }

    ValueEventListener getMatchVLE = new ValueEventListener() {
        @Override
        public void onDataChange(DataSnapshot dataSnapshot) {
            try {
                t1 = "" + dataSnapshot.child("team1").getValue();
                t2 = "" + dataSnapshot.child("team2").getValue();
                status = "" + dataSnapshot.child("status").getValue();
                Constants.TEAM1 = t1;
                Constants.TEAM2 = t2;

                if (status.equals("Ended")) {
                    sh = getSharedPreferences("login",MODE_PRIVATE);
                    int tot;
                    tot =  Constants.FBGLOBAL + total;

                    MBGlobalLeaderBoard mb = new MBGlobalLeaderBoard(Constants.NAME,tot);
                    myRef2.child(Constants.PHONE).setValue(mb);
                    Intent i = new Intent(getApplicationContext(),Home_Page.class);
                    finishAffinity();
                    startActivity(i);
                }

            } catch (Exception e) {
                Log.e("getMatchVLEe",e.toString());
            }
        }

        @Override
        public void onCancelled(DatabaseError databaseError) {

        }
    };


    ValueEventListener getuserleaderboardVLE = new ValueEventListener() {
        @Override
        public void onDataChange(DataSnapshot dataSnapshot) {
            try {
                players[0] = "" + dataSnapshot.child("captain").getValue();
                players[1] = "" + dataSnapshot.child("vicecaptain").getValue();
                players[2] = "" + dataSnapshot.child("player3").getValue();
                players[3] = "" + dataSnapshot.child("player4").getValue();
                players[4] = "" + dataSnapshot.child("player5").getValue();
                players[5] = "" + dataSnapshot.child("player6").getValue();
                players[6] = "" + dataSnapshot.child("player7").getValue();
                players[7] = "" + dataSnapshot.child("player8").getValue();
                points[0] = "" + dataSnapshot.child("s1").getValue();
                points[1] = "" + dataSnapshot.child("s2").getValue();
                points[2] = "" + dataSnapshot.child("s3").getValue();
                points[3] = "" + dataSnapshot.child("s4").getValue();
                points[4] = "" + dataSnapshot.child("s5").getValue();
                points[5] = "" + dataSnapshot.child("s6").getValue();
                points[6] = "" + dataSnapshot.child("s7").getValue();
                points[7] = "" + dataSnapshot.child("s8").getValue();

                fbtotal = "" + dataSnapshot.child("total").getValue();

                final Query playerpointsQuery = FirebaseDatabase.getInstance().getReference(Constants.TBL_PLAYER_POINTS).child(Constants.POSITION);
                playerpointsQuery.addValueEventListener(getplayerpointsVLE);

            } catch (Exception e) {
                Log.e("getuserleaderboardVLEe",e.toString());
            }
        }

        @Override
        public void onCancelled(DatabaseError databaseError) {

        }
    };

    ValueEventListener getplayerpointsVLE = new ValueEventListener() { //--------------------------------------------------------------------------------------------------------------
        @Override
        public void onDataChange(DataSnapshot dataSnapshot) {
            try {
                total=0;
                for(DataSnapshot postsnapshot : dataSnapshot.getChildren())
                {
                    for (int i=0;i<players.length;i++) {
                        fbscore = "" + postsnapshot.child("score").getValue();
                        fbname = "" + postsnapshot.getKey();
                        if (fbname.equals(players[i])) {
                            points[i] = fbscore;
                            x = Integer.parseInt(fbscore);
                            total += x;
                        }
                    }
                }
                cap = Integer.parseInt( points[0]);
                vc = Integer.parseInt( points[1]);
                total+= cap+(vc/2);
                fbtot=total;
                cap*=2;
                vc*=1.5;

                SharedPreferences sp = getSharedPreferences("total",MODE_PRIVATE);
                SharedPreferences.Editor myEdit = sp.edit();
                myEdit.putInt("tot",total);
                myEdit.commit();

                MBCurrentMatchLeaderboard mb = new MBCurrentMatchLeaderboard(players[0],players[1],players[2],players[3],players[4],players[5],players[6],players[7],Constants.PHONE,Constants.POSITION,""+ cap,"" + vc,points[2],points[3],points[4],points[5],points[6],points[7],total,Constants.NAME);
                myRef.child("Leaderboard-"+Constants.POSITION).child(Constants.PHONE).setValue(mb);

                final Query matchdataQuery = FirebaseDatabase.getInstance().getReference(Constants.TBL_MATCHES_DATA).child(Constants.POSITION);
                matchdataQuery.addListenerForSingleValueEvent(getMatchVLE);

                final Query matchrankQuery = FirebaseDatabase.getInstance().getReference(Constants.TBL_CURRENT_LEADERBOARD).child("Leaderboard-"+Constants.POSITION).orderByChild("total");
                matchrankQuery.addValueEventListener(getRankVLE);

            } catch (Exception e) {
                Log.e("getplayerpointsVLE e",e.toString());
            }
        }

        @Override
        public void onCancelled(DatabaseError databaseError) {

        }
    };

    ValueEventListener getRankVLE = new ValueEventListener() {
        @Override
        public void onDataChange(DataSnapshot dataSnapshot) {
            try {
                arrayList.clear();
                rank = (int)dataSnapshot.getChildrenCount();
                for(DataSnapshot postsnapshot : dataSnapshot.getChildren())
                {
                    fbrankname = "" + postsnapshot.child("name").getValue();
                    fbranktotal = "" + postsnapshot.child("total").getValue();
                    model = new ModelRanking(""+rank,fbrankname,fbranktotal,"" + postsnapshot.child("phone").getValue());
                    arrayList.add(model);
                    rank--;
                }
                adapter = new LvCurrentRankingAdapter(getApplicationContext(), arrayList);
                listView.setAdapter(adapter);
            } catch (Exception e) {
                Log.e("getRankVLE",e.toString());
            }
        }

        @Override
        public void onCancelled(DatabaseError databaseError) {

        }
    };
}
