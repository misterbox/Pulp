package com.theskyegriffin.pulp;

import android.content.Context;
import android.databinding.BaseObservable;
import android.databinding.Bindable;

import com.theskyegriffin.pulp.data.BudgetRepository;
import com.theskyegriffin.pulp.data.ynab.Budget;
import com.theskyegriffin.pulp.data.ynab.Budgets;
import com.theskyegriffin.pulp.data.ynab.ResponseWrapper;

import java.util.ArrayList;
import java.util.Arrays;

public class BudgetSqueezeViewModel extends BaseObservable {
    private final BudgetRepository budgetRepository;
    public final ArrayList<Budget> budgets = new ArrayList<Budget>();
    private Context context;

    public BudgetSqueezeViewModel(BudgetRepository repository, Context context) {
        this.context = context.getApplicationContext();
        budgetRepository = repository;
    }

    @Bindable
    public ArrayList<String> getBudgetNames() {
        ArrayList<String> names = new ArrayList<String>();

        for (Budget budget : budgets) {
            names.add(budget.getName());
        }

        return names;
    }

    public void start() {
        loadBudgets();
    }

    private void loadBudgets() {
        budgetRepository.getBudgets(new BudgetRepository.RepositoryCallback<Budgets>() {
            @Override
            public void onDataLoaded(ResponseWrapper<Budgets> data) {
                budgets.addAll(Arrays.asList(data.getData().getBudgets()));
            }

            @Override
            public void onDataNoAvailable() {

            }
        });
    }
}
