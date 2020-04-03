package com.example.mpl;

import androidx.lifecycle.ViewModelProviders;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class Team1 extends Fragment {


    ArrayList<ModelSelectPlayers> arrayList = new ArrayList<ModelSelectPlayers>();
    LvSelectPlayersAdapter adapter;
    ModelSelectPlayers model;
    ListView listView;
    String fbname , fbteam, fbcredits, status, fbt1,fbt2;
    FloatingActionButton fbtn;

    private Team1ViewModel mViewModel;

    public static Team1 newInstance() {
        return new Team1();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.team1_fragment, container, false);
        listView = root.findViewById(R.id.lvTeam1);
        fbtn = root.findViewById(R.id.t1btn);



        final Query matchdataQuery = FirebaseDatabase.getInstance().getReference(Constants.TBL_MATCHES_DATA).child(Constants.POSITION);
        matchdataQuery.addValueEventListener(getMatchVLE);

        final Query getPlayersQuery = FirebaseDatabase.getInstance().getReference(Constants.TBL_PLAYERS);
        getPlayersQuery.addValueEventListener(getPlayersVLE);

        fbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (Constants.TOTALCOUNT!=8)
                    new ShowToast(getActivity(), "Please Select 8 Players");
                else
                {
                    Intent i = new Intent(getActivity(),SelectCaptainViceCaptain.class);
                    startActivity(i);
                }
            }
        });



        return root;
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

                }
                adapter = new LvSelectPlayersAdapter(getActivity(), arrayList);
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
                Constants.STATUS = status;
            } catch (Exception e) {
                Log.e("--**-*-*e", e.toString());
            }
        }
        @Override
        public void onCancelled(DatabaseError databaseError) {

        }
    };

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = ViewModelProviders.of(this).get(Team1ViewModel.class);

    }



}
