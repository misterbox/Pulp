package com.theskyegriffin.pulp;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.theskyegriffin.pulp.fragments.BudgetFragment;
import com.theskyegriffin.pulp.fragments.CategoryListFragment;
import com.theskyegriffin.pulp.fragments.HistoryFragment;
import com.theskyegriffin.pulp.fragments.View;

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
                fragment.setViewModel(viewModel);
                break;
            case 1:
                fragment =  new CategoryListFragment();
                fragment.setViewModel(viewModel);
                break;
            case 2:
                fragment = new HistoryFragment();
                fragment.setViewModel(viewModel);
                break;
            default:
                fragment = new BudgetFragment();
                fragment.setViewModel(viewModel);
        }

        return (Fragment) fragment;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return tabTitles[position];
    }
}
