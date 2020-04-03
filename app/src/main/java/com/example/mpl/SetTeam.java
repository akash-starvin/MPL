package com.example.mpl;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;
import java.util.Observable;
import java.util.Observer;


public class SetTeam extends AppCompatActivity {



    ArrayList<ModelSelectPlayers> arrayList = new ArrayList<ModelSelectPlayers>();
    LvSelectPlayersAdapter adapter;
    ModelSelectPlayers model;
    ListView listView;
    ProgressBar prg;
    TextView cred;
    Integer c;
    String fbname , fbteam, fbcredits, status, fbt1,fbt2,disp;
    TextView tv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_set_team);
        listView = findViewById(R.id.astLvSelectPlayers);
        prg = findViewById(R.id.progressBar2);


        final Query matchdataQuery = FirebaseDatabase.getInstance().getReference(Constants.TBL_MATCHES_DATA).child(Constants.POSITION);
        matchdataQuery.addValueEventListener(getMatchVLE);

        final Query getPlayersQuery = FirebaseDatabase.getInstance().getReference(Constants.TBL_PLAYERS);
        getPlayersQuery.addValueEventListener(getPlayersVLE);

        tv=findViewById(R.id.textView3);

        new Timer().scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
               prg.setProgress(Constants.CREDITS);
          //      Toast.makeText(SetTeam.this, Constants.CREDITS, Toast.LENGTH_SHORT).show();
            }
        }, 0, 1000);// 1000 milliseconds=1 second


    }




    ValueEventListener getPlayersVLE = new ValueEventListener() {
        @Override
        public void onDataChange(DataSnapshot dataSnapshot) {
            try {
                arrayList.clear();
                for (DataSnapshot postSnapshot : dataSnapshot.getChildren()) {
                    fbname = "" + postSnapshot.child("name").getValue();
                    fbcredits = "" + postSnapshot.child("credits").getValue();
                    fbteam = "" + postSnapshot.child("team").getValue();
                    if (fbteam.equals(fbt1)) {
                        model = new ModelSelectPlayers(fbname, fbteam, fbcredits);
                        arrayList.add(model);
                    }
                    if (fbteam.equals(fbt2)) {
                        model = new ModelSelectPlayers(fbname, fbteam, fbcredits);
                        arrayList.add(model);
                    }

                }
                adapter = new LvSelectPlayersAdapter(getApplicationContext(), arrayList);
                listView.setAdapter(adapter);
            } catch (Exception e) {
            }
        }

        @Override
        public void onCancelled(DatabaseError databaseError) {

        }
    };

    ValueEventListener getMatchVLE = new ValueEventListener() {
        @Override
        public void onDataChange(DataSnapshot dataSnapshot) {
            try {
                status = "" + dataSnapshot.child("status").getValue();
                fbt1 =  "" + dataSnapshot.child("team1").getValue();
                fbt2 =  "" + dataSnapshot.child("team2").getValue();
               if (!status.equals("Select Players")) {
                    finish();
               }


            } catch (Exception e) {
                Log.e("--**-*-*e", e.toString());
            }
        }
        @Override
        public void onCancelled(DatabaseError databaseError) {

        }
    };
}
