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

public class LVCommentsAdapter extends BaseAdapter {
    Context mContext;
    LayoutInflater inflater;
    List<ModelComments> modelList;
    ArrayList<ModelComments> arrayList;


    public LVCommentsAdapter(Context context, List<ModelComments> modelData) {
        this.mContext = context;
        this.modelList = modelData;
        inflater = LayoutInflater.from( mContext );
        this.arrayList = new ArrayList<ModelComments>(  );
        this.arrayList.addAll( modelList );

    }
    public class ViewHolder
    {
        TextView mPackcomment,mPackdate,mPackmName;
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
            view = inflater.inflate( R.layout.sticker_comment,  null);

            holder.mPackcomment =  view.findViewById( R.id.scTvComment );
            holder.mPackdate = view.findViewById(R.id.scTvTime);
            holder.mPackmName = view.findViewById(R.id.scTvName);



            view.setTag( holder );

        }
        else
        {
            holder = (LVCommentsAdapter.ViewHolder)view.getTag();
        }
        try {
            holder.mPackcomment.setText(modelList.get(i).getComment()+"\n");
            holder.mPackdate.setText(modelList.get(i).getTime());
            holder.mPackmName.setText(modelList.get(i).getName());

        }
        catch (Exception e){}

        return view;
    }
}
