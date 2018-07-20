package com.theskyegriffin.pulp;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

public class PulpFragmentPagerAdapter extends FragmentPagerAdapter {
    final int PageCount = 4;
    private String[] tabTitles = new String[] { "Budget", "Categories", "History", "Results" };

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
                fragment = new BudgetFragment();
                break;
            case 1:
                fragment =  new CategoryListFragment();
                break;
            case 2:
                fragment = new HistoryFragment();
                break;
            case 3:
                fragment = new ResultsFragment();
                break;
            default:
                fragment = new BudgetFragment();
        }

        return fragment;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return tabTitles[position];
    }
}
