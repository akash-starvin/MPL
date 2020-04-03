package com.example.mpl.ui.How_to_Play;

import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.example.mpl.R;

public class How_to_Play_Fragment extends Fragment {

    private ToolsViewModel toolsViewModel;
    TextView tv1,tv2,tv3,tv4,tv5;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        toolsViewModel =
                ViewModelProviders.of(this).get(ToolsViewModel.class);
        View root = inflater.inflate(R.layout.fragment_tools, container, false);

        tv1 = root.findViewById(R.id.textView12);
        tv1.setTextColor(Color.rgb(255,0,0));
        tv1.setText("Notice:\n" +
                "You are required to maintain an active internet connection.\n" +
                "The match score page must be open when the match ends or else your points will not be reflected on the leaderboard." +
                "The app may have bugs. If you find any please report them in the contact us section.");

        tv2 = root.findViewById(R.id.textView15);
        tv2.setText("Select any match you want to play which is displayed in the list. You can create the team only when the status of the match is “Select players” if the status of the match changes to “live” or “Ended” you are restricted from creating a team. Click on the set team button once you select a match");

        tv3 = root.findViewById(R.id.textView17);
        tv3.setText("Once you click on the set team button you will be taken to the next page where you are allowed to select any  8  players from both the teams. Keep an eye on  the number of players added to your team and available credits. The maximum credit given to you to select players is 66.");

        tv4 = root.findViewById(R.id.textView20);
        tv4.setText("Once you finish selecting all the 8 players you have to choose one Captain and Vice captain from the team you have already selected. Your captain will get 2 times the points while your vice captain will get 1.5 times the points. Choose wisely.");

        tv5 = root.findViewById(R.id.textView24);
        tv5.setText("Service Ace : \t\t10 points\n" +
                    "Defence : \t\t8 points\n" +
                    "Block : \t\t6 points\n" +
                    "Spike/Drop : \t\t4 points\n" +
                    "Service Foul : \t\t-4 points\n" +
                    "Foul : \t\t-2 points");

        return root;
    }
}