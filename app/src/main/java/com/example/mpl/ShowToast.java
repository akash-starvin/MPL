package com.example.mpl;

import android.content.Context;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.text.Html;
import android.view.Gravity;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class ShowToast {
    public ShowToast(Context context, String info) {
        Toast toast = Toast.makeText(context,info, Toast.LENGTH_SHORT);
        View view = toast.getView();
        view.getBackground().setColorFilter(Color.parseColor("#64B5F6"), PorterDuff.Mode.SRC_IN);
        TextView text = view.findViewById(android.R.id.message);
        text.setTextColor(Color.WHITE);
        toast.show();
    }
}
