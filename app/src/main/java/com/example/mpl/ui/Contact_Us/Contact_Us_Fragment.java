package com.example.mpl.ui.Contact_Us;

import android.content.Intent;
import android.os.Bundle;
import android.text.Html;
import android.text.method.LinkMovementMethod;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.example.mpl.Constants;
import com.example.mpl.MBFeedback;
import com.example.mpl.R;
import com.example.mpl.ShowToast;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Contact_Us_Fragment extends Fragment {

    private SendViewModel sendViewModel;
    String SFeedback,SStar=null;
    Button btnsub;
    EditText etn,etp,etf;
    RatingBar rb1;

    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference myRef = database.getReference(Constants.TBL_USER_FEEDBACK);

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        sendViewModel =
                ViewModelProviders.of(this).get(SendViewModel.class);
        View root = inflater.inflate(R.layout.fragment_send, container, false);


        btnsub=root.findViewById(R.id.fsBtnSend);
        etf=root.findViewById(R.id.fsEtFeedback);
        rb1=root.findViewById(R.id.fsRbRating);

       root.findViewById(R.id.fsFabEmail).setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               Intent Email = new Intent(Intent.ACTION_SEND);
               Email.setType("text/email");
               Email.putExtra(Intent.EXTRA_EMAIL, new String[]{"milagresapp@gmail.com"});  //developer 's email
               Email.putExtra(Intent.EXTRA_SUBJECT, "Feedback from "+Constants.NAME); // Email 's Subject
               startActivity(Intent.createChooser(Email, "Choose Mail Client : "));
           }
       });



        btnsub.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getdata();
            }
        });
        rb1.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
            @Override
            public void onRatingChanged(RatingBar ratingBar, float v, boolean b) {
                SStar=String.valueOf(v);
            }
        });

        return root;
    }


    public void insertdata() {
        MBFeedback mb = new MBFeedback(Constants.NAME,SFeedback,SStar,Constants.PHONE);
        myRef.push().setValue(mb);
       new ShowToast(getActivity(), "Thank you for your feedback.");
       etf.setText("");
    }

    private void getdata() {
        boolean flag = true;
        if (etf.getText().toString().isEmpty()) {
            etf.setError("Please enter your feedback");
            flag = false;
        }
        if(SStar==null)
        {
            new ShowToast(getActivity(), "Rate us out of 5");
            flag=false;
        }
        if(flag==true) {
            SFeedback = etf.getText().toString();
            insertdata();
        }

    }
}