package com.example.mpl.ui.All_Teams;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;

import com.example.mpl.Constants;
import com.example.mpl.LvAllTeamsAdapter;
import com.example.mpl.LvMatchesAdapter;
import com.example.mpl.ModelAllTeams;
import com.example.mpl.ModelDataMatches;
import com.example.mpl.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class All_Teams_Fragment extends Fragment {

    ArrayList<ModelAllTeams> arrayList = new ArrayList<ModelAllTeams>();
    LvAllTeamsAdapter adapter;
    ModelAllTeams model;
    ListView listView;
    private GalleryViewModel galleryViewModel;
    String img,name,owner;

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        galleryViewModel = ViewModelProviders.of(this).get(GalleryViewModel.class);
        View root = inflater.inflate(R.layout.fragment_gallery, container, false);
        listView= root.findViewById(R.id.fgLvAllTeams);
        final Query teamQuery = FirebaseDatabase.getInstance().getReference(Constants.TBL_TEAM_DATA);
        teamQuery.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                arrayList.clear();
                for (DataSnapshot post : dataSnapshot.getChildren())
                {
                    img=""+ post.child("img").getValue();
                    name=""+ post.child("teamname").getValue();
                    owner=""+ post.child("owner").getValue();
                    model = new ModelAllTeams(name,img,owner);
                    arrayList.add(model);
                }

                adapter = new LvAllTeamsAdapter(getActivity(), arrayList);
                listView.setAdapter(adapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        return root;
    }
}