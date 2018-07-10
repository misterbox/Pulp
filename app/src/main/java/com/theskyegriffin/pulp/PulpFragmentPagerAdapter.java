package com.theskyegriffin.pulp;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

public class PulpFragmentPagerAdapter extends FragmentPagerAdapter {
    final int PageCount = 3;
    private String[] tabTitles = new String[] { "Categories", "Transaction History", "Results" };

    public PulpFragmentPagerAdapter(FragmentManager fragmentManager) {
        super(fragmentManager);
    }

    @Override
    public int getCount() {
        return PageCount;
    }

    @Override
    public Fragment getItem(int position) {
        Fragment fragment;

        switch (position) {
            case 0:
                fragment =  new CategoryListFragment();
                break;
            case 1:
                fragment = new HistoryFragment();
                break;
            default:
                fragment = new ResultsFragment();
        }

        return fragment;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return tabTitles[position];
    }
}
