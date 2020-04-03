package com.example.mpl.ui.Season_Ranking;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.example.mpl.Constants;
import com.example.mpl.LvCurrentRankingAdapter;
import com.example.mpl.ModelRanking;
import com.example.mpl.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class Season_Ranking_Fragment extends Fragment {

    private SlideshowViewModel slideshowViewModel;
    ArrayList<ModelRanking> arrayList = new ArrayList<ModelRanking>();
    LvCurrentRankingAdapter adapter;
    ModelRanking model;
    ListView listview;
    int rank;
    String fbname,fbtotal;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        slideshowViewModel = ViewModelProviders.of(this).get(SlideshowViewModel.class);
        View root = inflater.inflate(R.layout.fragment_slideshow, container, false);

        listview =  root.findViewById(R.id.fsLvGlobalRanking);

        final Query matchrankQuery = FirebaseDatabase.getInstance().getReference(Constants.TBL_GLOBAL_LEADERBOARD).orderByChild("total").limitToLast(50);
        matchrankQuery.addValueEventListener(getRankVLE);

        return root;
    }

    ValueEventListener getRankVLE = new ValueEventListener() {
        @Override
        public void onDataChange(DataSnapshot dataSnapshot) {
            try {
                arrayList.clear();
                rank = (int)dataSnapshot.getChildrenCount();
                for(DataSnapshot postsnapshot : dataSnapshot.getChildren())
                {
                    fbname = "" + postsnapshot.child("name").getValue();
                    fbtotal = "" + postsnapshot.child("total").getValue();
                    model = new ModelRanking(""+rank,fbname,fbtotal);
                    arrayList.add(model);
                    rank--;
                }
                adapter = new LvCurrentRankingAdapter(getActivity(), arrayList);
                listview.setAdapter(adapter);
            } catch (Exception e) {
                Log.e("getuserleaEe",e.toString());
            }
        }

        @Override
        public void onCancelled(DatabaseError databaseError) {

        }
    };

}