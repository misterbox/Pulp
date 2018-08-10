package com.theskyegriffin.pulp.results;

import android.content.Context;

import com.theskyegriffin.pulp.data.BudgetRepository;

public class ResultsViewModel {
    private final String TAG = ResultsViewModel.class.getSimpleName();
    private final BudgetRepository budgetRepository;
    private final Context context;

    public ResultsViewModel(BudgetRepository repository, Context context) {
        budgetRepository = repository;
        this.context = context;
    }
}
