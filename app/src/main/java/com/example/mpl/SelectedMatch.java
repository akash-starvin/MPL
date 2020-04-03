package com.example.mpl;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.RemoteViews;
import android.widget.TextView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.Locale;
import java.util.Random;

public class SelectedMatch extends AppCompatActivity {

    String t1, t2, status,fbcomment,fbname,fbtime;
    TextView p1,p2,s1,s2,te1,te2,commentary;
    Button btnset,btnmyteam;
    EditText etcomment;
    FloatingActionButton btnsend;
    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference myRef = database.getReference(Constants.TBL_COMMENTS);
    ArrayList<ModelComments> arrayList = new ArrayList<ModelComments>();
    LVCommentsAdapter adapter;
    ModelComments model;
    ListView listView;
    int tota;
    SharedPreferences sh1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_selected_match);

        p1 = findViewById(R.id.asmTvT1Point);
        p2 = findViewById(R.id.asmTvT2Point);
        s1 = findViewById(R.id.asmTvT1Set);
        s2 = findViewById(R.id.asmTvT2Set);
        te1 = findViewById(R.id.asmTvTeam1);
        te2 = findViewById(R.id.asmTvTeam2);
        btnset =findViewById(R.id.asmBtnSetTeam);
        etcomment=findViewById(R.id.asmEtcomment);
        btnsend=findViewById(R.id.asmBtnsend);
        commentary=findViewById(R.id.TvCommentary);
        listView = findViewById(R.id.asmLvComments);
        btnmyteam = findViewById(R.id.asmBtnMyTeam);

        final Query CommentaryQuery = FirebaseDatabase.getInstance().getReference("Commentary").child(Constants.POSITION);
        CommentaryQuery.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                commentary.setText(""+dataSnapshot.getValue());
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });



        findViewById(R.id.asmBtnLeaderboard).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(),MatchLeaderboard.class);
                startActivity(i);
            }
        });

        btnsend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String userInput;
                if(etcomment.getText().toString().isEmpty())
                    etcomment.setError("Enter comment");
                else {
                    userInput = etcomment.getText().toString().toLowerCase();
                    ArrayList<String> badWords = new ArrayList<>();
                    badWords.add("bvc");
                    badWords.add("fuck");
                    badWords.add("bitch");
                    for (String badWord : badWords){
                        if(userInput.contains(badWord)){
                            userInput = userInput.replace(badWord, "****");
                        }
                    }
                    DateFormat df = new SimpleDateFormat("hh:mm:ss a");
                    String date = df.format(Calendar.getInstance().getTime());
                    MBComments mb = new MBComments(Constants.NAME, userInput, date);
                    myRef.child(Constants.POSITION).push().setValue(mb);
                    etcomment.setText("");
                }
            }
        });

        if(!Constants.POSITION.equals("null")) {
            final Query matchdataQuery = FirebaseDatabase.getInstance().getReference(Constants.TBL_MATCHES_DATA).child(Constants.POSITION);
            matchdataQuery.addValueEventListener(getMatchVLE);

            final Query matchcommentquery = FirebaseDatabase.getInstance().getReference(Constants.TBL_COMMENTS).child(Constants.POSITION).limitToLast(15);
            matchcommentquery.addValueEventListener(getcommentsVLE);

        }


        btnmyteam.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Constants.RANK_PHONE = Constants.PHONE;
                Intent intent = new Intent(getApplicationContext(), CourtLayout.class);
                startActivity(intent);
            }
        });


        btnset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Constants.TOTALCOUNT=0;
                Constants.T1COUNT=0;
                Constants.T2COUNT=0;
                Constants.CREDITS=0;
                for (int i=0;i<Constants.TEAM_ARRY.length;i++)
                    Constants.TEAM_ARRY [i]="";
                Intent intent = new Intent(getApplicationContext(),SetTeam2.class);
                startActivity(intent);
                SelectedMatch.this.finish();
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
                String pt1 = "" + dataSnapshot.child("t1point").getValue();
                String pt2 = ""+ dataSnapshot.child("t2point").getValue();
                String st1 = "" + dataSnapshot.child("t1set").getValue();
                String st2 = ""+ dataSnapshot.child("t2set").getValue();
                p1.setText(pt1);
                p2.setText(pt2);
                s1.setText(st1);
                s2.setText(st2);
                te1.setText(t1);
                te2.setText(t2);

                Constants.TEAM1 = t1;
                Constants.TEAM2 = t2;
                findViewById(R.id.asmBtnLeaderboard).setEnabled(true);
                if (status.equals("Select Players")) {
                    btnset.setEnabled(true);
                    btnset.setVisibility(View.VISIBLE);
                    btnmyteam.setVisibility(View.VISIBLE);
                }
                else if (status.equals("Ended")) {
                    btnset.setVisibility(View.INVISIBLE);
                    btnmyteam.setVisibility(View.VISIBLE);
                    new ShowToast(getApplicationContext(),"Uploading Final Score. Please Wait for 3 seconds");
                    Intent i = new Intent(getApplicationContext(),MatchLeaderboard.class);
                    startActivity(i);
                }
                else if(status.equals("Live")){
                    btnmyteam.setVisibility(View.VISIBLE);
                    btnset.setVisibility(View.INVISIBLE);
                    NotificationCompat.Builder builder = new NotificationCompat.Builder(SelectedMatch.this,"CHANNELID" )
                            .setSmallIcon(R.drawable.logo)
                            .setContentTitle(t1+" vs "+t2)
                            .setContentText("("+st1+") "+pt1+" - "+pt2+" ("+st2+")")
                            .setSound(null)
                            .setPriority(NotificationCompat.PRIORITY_LOW);
                    NotificationManagerCompat notificationManager = NotificationManagerCompat.from(SelectedMatch.this);

                    // notificationId is a unique int for each notification that you must define
                    notificationManager.notify(2, builder.build());
                }
                else if(status.equals("Upcoming")){
                    btnset.setEnabled(false);
                    btnmyteam.setEnabled(false);
                }
                else if (status.equals("Finished")) {
                    btnset.setVisibility(View.INVISIBLE);
                    btnmyteam.setVisibility(View.VISIBLE);

                }
                else if (status.equals("Cancelled")) {
                    btnset.setEnabled(false);
                    btnmyteam.setEnabled(false);
                    findViewById(R.id.asmBtnLeaderboard).setEnabled(false);
                }

            } catch (Exception e) {
                Log.e("getMatchVLEe",e.toString());
            }
        }

        @Override
        public void onCancelled(DatabaseError databaseError) {

        }
    };



    ValueEventListener getcommentsVLE = new ValueEventListener() {
        @Override
        public void onDataChange(DataSnapshot dataSnapshot) {
            try {
                arrayList.clear();
                for (DataSnapshot post : dataSnapshot.getChildren() )
                {
                    fbcomment = ""+post.child("comment").getValue();
                    fbname  = ""+post.child("name").getValue();
                    fbtime = ""+post.child("time").getValue();
                    model = new ModelComments(fbname,fbcomment,fbtime);
                    arrayList.add(model);
                }
                adapter = new LVCommentsAdapter(getApplicationContext(), arrayList);
                listView.setAdapter(adapter);
            } catch (Exception e) {
                Log.e("getMatchVLEe",e.toString());
            }
        }

        @Override
        public void onCancelled(DatabaseError databaseError) {

        }
    };



}
