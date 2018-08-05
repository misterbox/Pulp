package com.theskyegriffin.pulp.budgetsqueeze;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.theskyegriffin.pulp.View;

public class PulpFragmentPagerAdapter extends FragmentPagerAdapter {
    private final int PageCount = 3;
    private String[] tabTitles = new String[] { "Budget", "Categories", "History" };
    private final BudgetSqueezeViewModel viewModel;

    public PulpFragmentPagerAdapter(FragmentManager fragmentManager, BudgetSqueezeViewModel viewModel) {
        super(fragmentManager);
        this.viewModel = viewModel;
    }

    @Override
    public int getCount() {
        return PageCount;
    }

    @Override
    public Fragment getItem(int position) {
        View fragment;

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
            default:
                fragment = new BudgetFragment();
        }
        fragment.setViewModel(viewModel);

        return (Fragment) fragment;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return tabTitles[position];
    }
}
