package com.example.mpl;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class SelectedTeamData extends AppCompatActivity {
    String p,s;
    ArrayList<ModelUserTeams> arrayList = new ArrayList<ModelUserTeams>();
    LvUserTeamsAdapter adapter;
    ModelUserTeams model;
    ListView listView;
    String players[] = new String[15];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_player_teams);
        listView = findViewById(R.id.aptLvUserTeams);
        final Query userleaderboardQuery = FirebaseDatabase.getInstance().getReference(Constants.TBL_PLAYERS);
        userleaderboardQuery.addValueEventListener(getuserleaderboardVLE);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Constants.PLAYER_NAME = players[position];
                Intent i = new Intent(getApplicationContext(),PlayerStats.class);
                startActivity(i);
            }
        });

    }
    ValueEventListener getuserleaderboardVLE = new ValueEventListener() {
        @Override
        public void onDataChange(DataSnapshot dataSnapshot) {
            try {
                arrayList.clear();
                int i=0;
                for (DataSnapshot data : dataSnapshot.getChildren()) {
                    Log.e("-*-*",""+data.child("team").getValue());
                    Log.e("-*-*",""+Constants.TEAMNAME);
                    if (data.child("team").getValue().equals(Constants.TEAMNAME))
                    {
                        p = "" + data.child("name").getValue();
                        s = "Credits : " +  data.child("credits").getValue();
                        model = new ModelUserTeams(p, s);
                        arrayList.add(model);
                        players[i]="" + data.child("name").getValue();
                        i++;
                    }

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
