package com.example.mpl;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class CourtLayoutPreview extends AppCompatActivity {

    TextView p1,p2,p3,p4,p5,p6,p7,p8,s1,s2,s3,s4,s5,s6,s7,s8;

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

        p1.setText(Constants.TEAM_ARRY[0]);
        p2.setText(Constants.TEAM_ARRY[1]);
        p3.setText(Constants.TEAM_ARRY[2]);
        p4.setText(Constants.TEAM_ARRY[3]);
        p5.setText(Constants.TEAM_ARRY[4]);
        p6.setText(Constants.TEAM_ARRY[5]);
        p7.setText(Constants.TEAM_ARRY[6]);
        p8.setText(Constants.TEAM_ARRY[7]);
        s1.setVisibility(View.INVISIBLE);
        s2.setVisibility(View.INVISIBLE);
        s3.setVisibility(View.INVISIBLE);
        s4.setVisibility(View.INVISIBLE);
        s5.setVisibility(View.INVISIBLE);
        s6.setVisibility(View.INVISIBLE);
        s7.setVisibility(View.INVISIBLE);
        s8.setVisibility(View.INVISIBLE);
    }
}
