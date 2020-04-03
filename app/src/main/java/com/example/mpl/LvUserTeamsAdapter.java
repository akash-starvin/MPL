package com.example.mpl;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class LvUserTeamsAdapter extends BaseAdapter {
    Context mContext;
    LayoutInflater inflater;
    List<ModelUserTeams> modelList;
    ArrayList<ModelUserTeams> arrayList;


    public LvUserTeamsAdapter(Context context, List<ModelUserTeams> modelData) {
        this.mContext = context;
        this.modelList = modelData;
        inflater = LayoutInflater.from( mContext );
        this.arrayList = new ArrayList<ModelUserTeams>(  );
        this.arrayList.addAll( modelList );

    }
    public class ViewHolder
    {
        TextView mPackPoints,mPackName;
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
    public View getView(final int i, View view, ViewGroup viewGroup) {
        final ViewHolder holder;
        if(view == null)
        {
            holder = new ViewHolder();
            view = inflater.inflate( R.layout.strickeruserteam,  null);
            holder.mPackName =  view.findViewById( R.id.sutTvName);
            holder.mPackPoints=  view.findViewById( R.id.sutTvPoints);
            view.setTag( holder );
        }
        else
        {
            holder = (LvUserTeamsAdapter.ViewHolder)view.getTag();
        }
        try {
            holder.mPackName.setText(modelList.get(i).getName());
            holder.mPackPoints.setText(modelList.get(i).getPoints());
        }
        catch (Exception e){}



        return view;
    }
}
