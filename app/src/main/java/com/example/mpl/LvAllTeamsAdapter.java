package com.example.mpl;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class LvAllTeamsAdapter extends BaseAdapter {

    Context mContext;
    LayoutInflater inflater;
    List<ModelAllTeams> modelList;
    ArrayList<ModelAllTeams> arrayList;


    public LvAllTeamsAdapter(Context context, List<ModelAllTeams> modelData) {
        this.mContext = context;
        this.modelList = modelData;
        inflater = LayoutInflater.from( mContext );
        this.arrayList = new ArrayList<ModelAllTeams>(  );
        this.arrayList.addAll( modelList );

    }
    public class ViewHolder
    {
        TextView mPackTeamName,mPackOwnerName;
        ImageView mPackTeamLogo;
        FloatingActionButton fab;
    }

    @Override
    public int getCount() {
        return modelList.size();
    }

    @Override
    public Object getItem(int i) {
        return modelList.get( i );
    }

    @Override
    public long getItemId(int i) {

        return i;
    }

    @Override
    public View getView(final int i, View view, final ViewGroup viewGroup) {
        final ViewHolder holder;
        if(view == null)
        {
            holder = new ViewHolder();
            view = inflater.inflate( R.layout.sticker_teams,  null);

            holder.mPackTeamName =  view.findViewById( R.id.stTvTeamName );
            holder.mPackOwnerName = view.findViewById(R.id.stTvOwnerName);
            holder.mPackTeamLogo = view.findViewById(R.id.stIvLogo);
            holder.fab = view.findViewById(R.id.floatingActionButton);

            view.setTag( holder );

        }
        else
        {
            holder = (LvAllTeamsAdapter.ViewHolder)view.getTag();
        }
        try {
            holder.mPackTeamName.setText(modelList.get(i).getTeamname());
            holder.mPackOwnerName.setText(modelList.get(i).getOwnername());
            Picasso.get().load(modelList.get(i).getTeamlogo()).into(holder.mPackTeamLogo);
        }
        catch (Exception e){}

        holder.fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Constants.TEAMNAME = modelList.get(i).getTeamname();
                Intent i = new Intent(mContext,SelectedTeamData.class);                mContext.startActivity(i);
            }
        });

        return view;
    }
}
