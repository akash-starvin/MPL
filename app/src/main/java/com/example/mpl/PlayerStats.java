package com.example.mpl;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

public class PlayerStats extends AppCompatActivity {

    TextView player,def,ace,spike,block,foul,foulserve;
    String sdef,sace,sspike,sblock,sfoul,sfoulserve;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_player_stats);

        player=findViewById (R.id.tvplayername);
        def=findViewById (R.id.tvdefence);
        ace=findViewById (R.id.tvace);
        spike=findViewById (R.id.tvspike);
        block=findViewById (R.id.tvblock);
        foul=findViewById (R.id.tvfoul);
        foulserve=findViewById (R.id.tvfoulserve);

        player.setText(Constants.PLAYER_NAME);

        final Query playerstatsQuery = FirebaseDatabase.getInstance().getReference(Constants.TBL_PLAYER_STATS).child(Constants.PLAYER_NAME);
        playerstatsQuery.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                sdef=""+ dataSnapshot.child("defence").getValue();
                sace=""+ dataSnapshot.child("ace").getValue();
                sspike=""+ dataSnapshot.child("spike").getValue();
                sblock=""+ dataSnapshot.child("block").getValue();
                sfoul=""+ dataSnapshot.child("foul").getValue();
                sfoulserve=""+ dataSnapshot.child("foulserve").getValue();

                if(sdef.equals("null"))
                    sdef="0";
                if(sace.equals("null"))
                    sace="0";
                if(sspike.equals("null"))
                    sspike="0";
                if(sblock.equals("null"))
                    sblock="0";
                if(sfoul.equals("null"))
                    sfoul="0";
                if(sfoulserve.equals("null"))
                    sfoulserve="0";

                def.setText(sdef);
                ace.setText(sace);
                spike.setText(sspike);
                block.setText(sblock);
                foul.setText(sfoul);
                foulserve.setText(sfoulserve);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });




    }
}
