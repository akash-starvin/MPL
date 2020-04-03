package com.example.mpl.ui.SetTeam;

import android.content.Context;

import androidx.annotation.Nullable;
import androidx.annotation.StringRes;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.example.mpl.Constants;
import com.example.mpl.R;
import com.example.mpl.Team1;
import com.example.mpl.Team2;

/**
 * A [FragmentPagerAdapter] that returns a fragment corresponding to
 * one of the sections/tabs/pages.
 */
public class SectionsPagerAdapter extends FragmentPagerAdapter {

  //  @StringRes
  //  private static final int[] TAB_TITLES = new int[]{, R.string.tab_text_2};
    private final Context mContext;

    public SectionsPagerAdapter(Context context, FragmentManager fm) {
        super(fm);
       mContext = context;
    }

    @Override
    public Fragment getItem(int position) {
        // getItem is called to instantiate the fragment for the given page.
        // Return a PlaceholderFragment (defined as a static inner class below).
        Fragment frag=null;
        switch (position)
        { case 0: frag = new Team1();
            break;
            case 1: frag = new Team2();
            break;
        }
        return frag;
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        if (position==0)
        return Constants.TEAM1;
        else
            return Constants.TEAM2;
    }

    @Override
    public int getCount() {
        // Show 2 total pages.
        return 2;
    }
}