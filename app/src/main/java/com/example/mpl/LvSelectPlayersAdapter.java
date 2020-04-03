package com.example.mpl;

import android.content.Context;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CompoundButton;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;


import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;
import java.util.List;

public class LvSelectPlayersAdapter extends BaseAdapter {
    Context mContext;
    LayoutInflater inflater;
    List<ModelSelectPlayers> modelList;
    ArrayList<ModelSelectPlayers> arrayList;
    String teamname;
    SetTeam setTeam = new SetTeam();
    int credit;
    public final static int MAX = 66;

    public LvSelectPlayersAdapter(Context context, List<ModelSelectPlayers> modelData) {
        this.mContext = context;
        this.modelList = modelData;
        inflater = LayoutInflater.from( mContext );
        this.arrayList = new ArrayList<ModelSelectPlayers>(  );
        this.arrayList.addAll( modelList );

    }
    public class ViewHolder
    {
        TextView mPackName , mPackCredit,tv;
        Switch mPackSelect;
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
       final  ViewHolder holder;//= new ViewHolder();
       if(view == null)
       {
            holder= new ViewHolder();
            view = inflater.inflate( R.layout.sticker_player_selection,  null);

            holder.mPackName =  view.findViewById( R.id.spsName );
            holder.tv = view.findViewById(R.id.textView2);
            holder.mPackCredit =  view.findViewById( R.id.spsCredits );
            holder.mPackSelect = view.findViewById(R.id.switch1);
            view.setTag( holder );

       }
       else {
            holder = (ViewHolder)view.getTag();
       }
        try {
            holder.mPackName.setText(modelList.get(i).getPname());

            if(modelList.get(i).getPcredits().equals("0"))
            {
                holder.mPackCredit.setText("N/A");
                holder.mPackSelect.setEnabled(false);
                holder.tv.setVisibility(View.INVISIBLE);
            }
            else {
                holder.mPackCredit.setText(modelList.get(i).getPcredits());
            }


        }
        catch (Exception e){
            e.printStackTrace();
        }

      holder.mPackSelect.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
          @Override
          public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

              credit= Integer.parseInt( modelList.get(i).getPcredits());
              teamname = modelList.get(i).getPteam();

              if (isChecked)
              {
                  if(Constants.TEAM1.equals(teamname) && (Constants.T1COUNT+1 <= 5) && (Constants.T1COUNT+1+Constants.T2COUNT)<=8){
                      if(Constants.CREDITS + credit>MAX) {
                          Toast toast = Toast.makeText(mContext,"Insufficient Credits", Toast.LENGTH_SHORT);
                          View view = toast.getView();
                          view.getBackground().setColorFilter(Color.rgb(255,0,0), PorterDuff.Mode.SRC_IN);
                          TextView text = view.findViewById(android.R.id.message);
                          text.setTextColor(Color.WHITE);
                          toast.show();
                          holder.mPackSelect.setChecked(false);
                      }
                      else
                      {
                          Constants.CREDITS = Constants.CREDITS + credit;
                          Constants.T1COUNT ++;
                          Constants.TOTALCOUNT++;
                          boolean flag = false;
                          for (int j = 0; j<Constants.TEAM_ARRY.length; j++) {
                              if (Constants.TEAM_ARRY[j].equals("x")) {
                                  Constants.TEAM_ARRY[j] = modelList.get(i).getPname();
                                  flag=true;
                              }
                          }
                          if(!flag)
                              Constants.TEAM_ARRY[Constants.TOTALCOUNT-1] = modelList.get(i).getPname();
                      }

                      Snackbar snackbar;
                      snackbar = Snackbar.make(buttonView, "Credits used : "  + Constants.CREDITS + "/66\nPlayers :" + Constants.TOTALCOUNT+"/8",Snackbar.LENGTH_SHORT);
                      View snackBarView = snackbar.getView();
                      snackBarView.setBackgroundColor(Color.parseColor("#64B5F6"));
                      snackbar.show();
                  }
                  else if(Constants.TEAM2.equals(teamname) && Constants.T2COUNT+1 <= 5 && (Constants.T1COUNT+Constants.T2COUNT+1)<=8){
                      if(Constants.CREDITS + credit>MAX) {
                          Toast toast = Toast.makeText(mContext,"Insufficient Credits", Toast.LENGTH_SHORT);
                          View view = toast.getView();
                          view.getBackground().setColorFilter(Color.rgb(255,0,0), PorterDuff.Mode.SRC_IN);
                          TextView text = view.findViewById(android.R.id.message);
                          text.setTextColor(Color.WHITE);
                          toast.show();
                          holder.mPackSelect.setChecked(false);
                      }
                      else
                      {
                          Constants.CREDITS = Constants.CREDITS + credit;
                          Constants.T2COUNT ++;
                          Constants.TOTALCOUNT++;
                          boolean flag = false;
                          for (int j = 0; j<Constants.TEAM_ARRY.length; j++) {
                              if (Constants.TEAM_ARRY[j].equals("x")) {
                                  Constants.TEAM_ARRY[j] = modelList.get(i).getPname();
                                  flag=true;
                              }
                          }
                          if(!flag)
                          Constants.TEAM_ARRY[Constants.TOTALCOUNT-1] = modelList.get(i).getPname();
                      }
                      Snackbar snackbar;
                      snackbar = Snackbar.make(buttonView, "Credits used : "  + Constants.CREDITS + "/66\nPlayers :" + Constants.TOTALCOUNT+"/8",Snackbar.LENGTH_SHORT);
                      View snackBarView = snackbar.getView();
                      snackBarView.setBackgroundColor(Color.parseColor("#64B5F6"));
                      snackbar.show();
                  }
                  else {
                      holder.mPackSelect.setChecked(false);
                      Toast toast = Toast.makeText(mContext,"Maximum 5 players from a team. 8 players in total", Toast.LENGTH_SHORT);
                      View view = toast.getView();
                      view.getBackground().setColorFilter(Color.rgb(255,0,0), PorterDuff.Mode.SRC_IN);
                      TextView text = view.findViewById(android.R.id.message);
                      text.setTextColor(Color.WHITE);
                      toast.show();
                  }
              }
              if(!isChecked)
              {
                  for (int j = 0; j<Constants.TEAM_ARRY.length; j++) {
                      if(Constants.TEAM_ARRY[j].equals(modelList.get(i).getPname())) {
                         Constants.TEAM_ARRY[j] = "x";
                      }
                  }

                  if(Constants.TEAM1.equals(teamname)){
                      Constants.T1COUNT --;
                      Constants.TOTALCOUNT--;
                      Constants.CREDITS = Constants.CREDITS - credit;
                      Snackbar snackbar;
                      snackbar = Snackbar.make(buttonView, "Credits used : "  + Constants.CREDITS + "/66\nPlayers :" + Constants.TOTALCOUNT+"/8",Snackbar.LENGTH_SHORT);
                      View snackBarView = snackbar.getView();
                      snackBarView.setBackgroundColor(Color.parseColor("#64B5F6"));
                      snackbar.show();
                  }
                  else if(Constants.TEAM2.equals(teamname)){
                      Constants.T2COUNT --;
                      Constants.TOTALCOUNT--;
                      Constants.CREDITS = Constants.CREDITS - credit;
                      Snackbar snackbar;
                      snackbar = Snackbar.make(buttonView, "Credits used : "  + Constants.CREDITS + "/66\nPlayers :" + Constants.TOTALCOUNT+"/8",Snackbar.LENGTH_SHORT);
                      View snackBarView = snackbar.getView();
                      snackBarView.setBackgroundColor(Color.parseColor("#64B5F6"));
                      snackbar.show();
                  }
              }
          }
      });
        return view;
    }
}
