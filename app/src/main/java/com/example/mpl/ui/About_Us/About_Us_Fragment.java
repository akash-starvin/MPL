package com.example.mpl.ui.About_Us;

import android.os.Bundle;
import android.text.Layout;
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

public class About_Us_Fragment extends Fragment {

    private ShareViewModel shareViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        shareViewModel = ViewModelProviders.of(this).get(ShareViewModel.class);
        View root = inflater.inflate(R.layout.fragment_share, container, false);
         TextView textView = root.findViewById(R.id.fsTvAboutUs);
         String s = "Every year final semester BCA students are required to present a project to the university. Students are grouped and for about 4 months work on bringing their project up to the required standards. The problem that nobody seems to notice is that these projects are never used before or after they are valued. The countless hours spent by the students just don't justify the payoff.\n\n" +
                 "We didn't want our project to be the same. We wanted our project to be used at least once before it was valued. So, inheriting some features form the famous app 'Dream11' we decided to make a Volleyball fantasy league app to be used during the college's annual volleyball tournament 'MPL'.\n\n" +
                 "The fact that you're reading this means that our vision has turned into reality and we hope you are enjoying the app. This project would not have been possible without the support of our department, our classmates and the Principal. Huge thanks to the developers behind Android Studio, Google Firebase and of course, Stack Overflow.\n" +
                 "Thank you for downloading the MPL app.\n" +
                 "\n" +
                 "- Ansel Gonsalves\n" +
                 "- Akash Dsouza\n" +
                 "- Stuart Olivera \n";
        textView.setText(s);
        return root;
    }
}