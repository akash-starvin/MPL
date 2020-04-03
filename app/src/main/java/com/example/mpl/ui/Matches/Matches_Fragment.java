package com.example.mpl.ui.Matches;

import android.annotation.SuppressLint;
import android.app.DownloadManager;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.annotation.NonNull;
import androidx.core.app.NotificationCompat;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.example.mpl.Constants;
import com.example.mpl.Home_Page;
import com.example.mpl.LvMatchesAdapter;
import com.example.mpl.ModelDataMatches;
import com.example.mpl.R;
import com.example.mpl.SelectedMatch;
import com.example.mpl.SetTeam;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.util.ArrayList;

public class Matches_Fragment extends Fragment {

    ArrayList<ModelDataMatches> arrayList = new ArrayList<ModelDataMatches>();
    LvMatchesAdapter adapter;
    ModelDataMatches model;
    ListView listView;
    TextView tvmessage;

    private HomeViewModel homeViewModel;
    String t1, t2, status, mname, date;
    String[] arrayUrl = new String[25];
    String[] arrayName = new String[25];

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        homeViewModel = ViewModelProviders.of(this).get(HomeViewModel.class);
        View root = inflater.inflate(R.layout.fragment_matches, container, false);

        listView = root.findViewById(R.id.fmLvMatches);
        tvmessage = root.findViewById(R.id.fmTvMessage);

        final Query imgQuery = FirebaseDatabase.getInstance().getReference(Constants.TBL_TEAM_DATA);
        imgQuery.addValueEventListener(getimgVLE);

        final Query matchmessageQuery = FirebaseDatabase.getInstance().getReference("Message");
        matchmessageQuery.addValueEventListener(getMessageVLE);


        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(getActivity(), SelectedMatch.class);
                Constants.POSITION = String.valueOf(position+1);
                startActivity(intent);
            }
        });
        return root;
    }

    ValueEventListener getMessageVLE = new ValueEventListener() {
        @Override
        public void onDataChange(DataSnapshot dataSnapshot) {
            try {
                tvmessage.setText(""+dataSnapshot.getValue());
            } catch (Exception e) {
            }
        }

        @Override
        public void onCancelled(DatabaseError databaseError) {

        }
    };

    ValueEventListener getimgVLE = new ValueEventListener() {
        @Override
        public void onDataChange(DataSnapshot dataSnapshot) {
            try {
                String img, name;
                int i = 0;
                for (DataSnapshot postSnapshot : dataSnapshot.getChildren()) {
                    img = "" + postSnapshot.child("img").getValue();
                    name = "" + postSnapshot.child("teamname").getValue();
                    arrayUrl[i] = img;
                    arrayName[i] = name;
                    i++;
                }
                final Query matchdataQuery = FirebaseDatabase.getInstance().getReference(Constants.TBL_MATCHES_DATA);
                matchdataQuery.addValueEventListener(getMatchesVLE);

            } catch (Exception e) {
            }
        }

        @Override
        public void onCancelled(DatabaseError databaseError) {

        }
    };

    ValueEventListener getMatchesVLE = new ValueEventListener() {
        @Override
        public void onDataChange(DataSnapshot dataSnapshot) {
            try {
                int l1, l2;
                arrayList.clear();
                for (DataSnapshot postSnapshot : dataSnapshot.getChildren()) {
                    t1 = "" + postSnapshot.child("team1").getValue();
                    t2 = "" + postSnapshot.child("team2").getValue();
                    status = "" + postSnapshot.child("status").getValue();
                    mname = "" + postSnapshot.child("matchname").getValue();
                    date = "" + postSnapshot.child("date").getValue();
                    l1 = check(t1);
                    l2 = check(t2);
                    model = new ModelDataMatches(t1, t2, arrayUrl[l1],arrayUrl[l2], mname, status,date);
                    arrayList.add(model);
                }
                adapter = new LvMatchesAdapter(getActivity(), arrayList);
                listView.setAdapter(adapter);
            } catch (Exception e) {
            }
        }

        @Override
        public void onCancelled(DatabaseError databaseError) {

        }
    };

    public int check(String name){
        for (int i=0; i<10; i++) {
            if (name.equals(arrayName[i]))
                return i;
        }
       return 9;
    }

}