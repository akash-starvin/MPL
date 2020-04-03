package com.example.mpl;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class LvCurrentRankingAdapter extends BaseAdapter {
    Context mContext;
    LayoutInflater inflater;
    List<ModelRanking> modelList;
    ArrayList<ModelRanking> arrayList;


    public LvCurrentRankingAdapter(Context context, List<ModelRanking> modelData) {
        this.mContext = context;
        this.modelList = modelData;
        inflater = LayoutInflater.from( mContext );
        this.arrayList = new ArrayList<ModelRanking>(  );
        this.arrayList.addAll( modelList );

    }
    public class ViewHolder
    {
        TextView mPackRank,mPackName,mPackTotal;
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
            view = inflater.inflate( R.layout.stickerranking,  null);
            holder.mPackName =  view.findViewById( R.id.srTvName );
            holder.mPackTotal = view.findViewById(R.id.srTvTotal);
            holder.mPackRank = view.findViewById(R.id.srTvRank);
            view.setTag( holder );
        }
        else
        {
            holder = (LvCurrentRankingAdapter.ViewHolder)view.getTag();
        }
        try {
            holder.mPackName.setText(modelList.get(i).getUname());
            holder.mPackTotal.setText(modelList.get(i).getTotal());
            holder.mPackRank.setText("#"+modelList.get(i).getRank());
        }
        catch (Exception e){}



        return view;
    }
}
