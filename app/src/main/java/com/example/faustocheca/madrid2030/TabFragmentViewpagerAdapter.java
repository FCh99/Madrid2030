package com.example.faustocheca.madrid2030;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.ArrayList;

/**
 * Created by faustocheca on 12/5/16.
 */
public class TabFragmentViewpagerAdapter extends FragmentPagerAdapter {


    ArrayList<Fragment>fragments;
    ArrayList<String>titles;


    public TabFragmentViewpagerAdapter(FragmentManager fm, ArrayList<Fragment> fragments, ArrayList<String> titles) {
        super(fm);
        this.fragments = fragments;
        this.titles = titles;
    }


    @Override
    public Fragment getItem(int position) {
        return fragments.get(position);
    }

    @Override
    public int getCount() {
        return fragments.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return titles.get(position);
    }
}
