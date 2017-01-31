package com.example.bilal.madical.model.adapter;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.example.bilal.madical.model.UserFragment.ProductUser;
import com.example.bilal.madical.model.UserFragment.ReportsUser;
import com.example.bilal.madical.model.UserFragment.TodayList;

/**
 * Provides the appropriate {@link Fragment} for a view pager.
 */
public class SimpleFragmentPagerAdapter extends FragmentPagerAdapter {

    final int PAGE_COUNT = 3;
    private String tabTitles[] = new String[] { "Today To List", "Products", "Reports" };
    private Context context;

    public SimpleFragmentPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public CharSequence getPageTitle(int position) {
        // Generate title based on item position
        return tabTitles[position];
    }


    @Override
    public Fragment getItem(int position) {
        //return PageFragment.newInstance(position + 1);

        if (position == 0) {

            return new TodayList();
        }
        else if (position == 1) {
            return new ProductUser();
        }
        else {
            return new ReportsUser();
        }


    }

    @Override
    public int getCount() {
        return 3;
    }
}