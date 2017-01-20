package com.example.coma.carrosel;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

/**
 * Created by coma on 20/01/2017.
 */

public class PagerAdapter extends FragmentPagerAdapter {
    private String tabTitles[] = new String[] {"Donut", "KitKat", "Lollipop", "Marshmallow"};

    public CharSequence getPageTitle(int position) {
        return tabTitles[position];
    }

    public PagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        if (position == 0) {
            return new DonutFragment();
        } else if (position == 1) {
            return new KitkatFragment();
        } else if (position == 2) {
            return new LollipopFragment();
        } else {
            return new MarshmallowFragment();
        }
    }



    @Override
    public int getCount() {return 4;}
}


