package com.example.mpl;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.mpl.ui.Matches.Matches_Fragment;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class SelectCaptainViceCaptain extends AppCompatActivity {

    Spinner scap,svc;
    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference myRef = database.getReference(Constants.TBL_CURRENT_LEADERBOARD);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_captain_vice_captain);

        scap =  findViewById(R.id.spcaptain);
        ArrayAdapter<String> spinner1ArrayAdapter = new ArrayAdapter<String>(this,   android.R.layout.simple_spinner_dropdown_item, Constants.TEAM_ARRY);
        scap.setAdapter(spinner1ArrayAdapter);

        svc =  findViewById(R.id.spvicecaptain);
        ArrayAdapter<String> spinner2ArrayAdapter = new ArrayAdapter<String>(this,   android.R.layout.simple_spinner_dropdown_item, Constants.TEAM_ARRY);
        svc.setAdapter(spinner2ArrayAdapter);

        findViewById(R.id.ascvcBtnSave).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!Constants.STATUS.equals("Select Players"))
                {
                    new ShowToast(SelectCaptainViceCaptain.this, "Match has already started");
                    Intent i = new Intent(getApplicationContext(),Home_Page.class);
                    startActivity(i);
                    finish();
                }
                else
                {
                    if(scap.getSelectedItemId()==svc.getSelectedItemId())
                        new ShowToast(SelectCaptainViceCaptain.this, "Captain and Vice Captain cannot be the same");
                    else
                    {
                        sort();
                        MBCurrentMatchLeaderboard mb = new MBCurrentMatchLeaderboard(Constants.TEAM_ARRY[0],Constants.TEAM_ARRY[1],Constants.TEAM_ARRY[2],Constants.TEAM_ARRY[3],Constants.TEAM_ARRY[4],Constants.TEAM_ARRY[5],Constants.TEAM_ARRY[6],Constants.TEAM_ARRY[7],Constants.PHONE,Constants.POSITION,"0","0","0","0","0","0","0","0",0,Constants.NAME);
                        myRef.child("Leaderboard-"+Constants.POSITION).child(Constants.PHONE).setValue(mb);
                        new ShowToast(SelectCaptainViceCaptain.this,"Team Created");
                        Intent i = new Intent(getApplicationContext(), Home_Page.class);
                        finishAffinity();
                        startActivity(i);
                    }
                }
            }


        });

        findViewById(R.id.ascvcBtnTeamPreview).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(),CourtLayoutPreview.class) ;
                startActivity(i);
            }
        });
    }

    public void sort() {

        String temp1,temp2;
        String cap,vc;

        cap =""+ scap.getSelectedItem();
        vc =""+ svc.getSelectedItem();

        for (int i=0; i<Constants.TEAM_ARRY.length;i++)
        {
            if (Constants.TEAM_ARRY[i].equals(cap)){
                temp1 = Constants.TEAM_ARRY[i];
                Constants.TEAM_ARRY[i] = Constants.TEAM_ARRY[0];
                Constants.TEAM_ARRY[0] = temp1;
            }
        }
        for (int i=0; i<Constants.TEAM_ARRY.length;i++)
        {
            if (Constants.TEAM_ARRY[i].equals(vc)){
                temp2 = Constants.TEAM_ARRY[i];
                Constants.TEAM_ARRY[i] = Constants.TEAM_ARRY[1];
                Constants.TEAM_ARRY[1] = temp2;
            }
        }
    }
}
