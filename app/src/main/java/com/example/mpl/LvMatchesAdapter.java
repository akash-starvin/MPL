package com.example.mpl;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class LvMatchesAdapter extends BaseAdapter {
    Context mContext;
    LayoutInflater inflater;
    List<ModelDataMatches> modelList;
    ArrayList<ModelDataMatches> arrayList;


    public LvMatchesAdapter(Context context, List<ModelDataMatches> modelData) {
        this.mContext = context;
        this.modelList = modelData;
        inflater = LayoutInflater.from( mContext );
        this.arrayList = new ArrayList<ModelDataMatches>(  );
        this.arrayList.addAll( modelList );

    }
    public class ViewHolder
    {
        TextView mPackT1,mPackT2,mPackmName,mPackDate,mPackStatus;
        ImageView mPackImg1, mPackImg2;
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
            view = inflater.inflate( R.layout.sticker_all_matches,  null);

            holder.mPackT1 =  view.findViewById( R.id.samTeam1Name );
            holder.mPackT2 = view.findViewById(R.id.samTeam2Name);
            holder.mPackmName = view.findViewById(R.id.samMatchName);
            holder.mPackStatus = view.findViewById(R.id.fmTvStatus);
            holder.mPackDate=view.findViewById(R.id.fmTvDate);
            holder.mPackImg1 = view.findViewById(R.id.samTeam1Image);
            holder.mPackImg2 = view.findViewById(R.id.samTeam2Image);


            view.setTag( holder );

        }
        else
        {
            holder = (ViewHolder)view.getTag();
        }
        try {
            holder.mPackT1.setText(modelList.get(i).getT1());
            holder.mPackT2.setText(modelList.get(i).getT2());
            holder.mPackmName.setText(modelList.get(i).getMname());
            holder.mPackStatus.setText(modelList.get(i).getStatus());
            holder.mPackDate.setText(modelList.get(i).getDate());
            Picasso.get().load(modelList.get(i).getImg1()).into(holder.mPackImg1);
            Picasso.get().load(modelList.get(i).getImg2()).into(holder.mPackImg2);
            if (modelList.get(i).getStatus().equals("Live"))
                holder.mPackStatus.setTextColor(Color.parseColor("#FF0000"));

            if(modelList.get(i).getStatus().equals("Select Players"))
                holder.mPackStatus.setTextColor(Color.parseColor("#008000"));

            if(modelList.get(i).getStatus().equals("Upcoming"))
                holder.mPackStatus.setTextColor(Color.parseColor("#F1AD24"));
        }
        catch (Exception e){}

        return view;
    }


}
