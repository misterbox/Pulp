package com.theskyegriffin.pulp.results;

import com.theskyegriffin.pulp.data.BudgetRepository;
import com.theskyegriffin.pulp.data.ynab.Category;

import java.util.Calendar;
import java.util.List;
import java.util.UUID;

public class ResultsViewModel {
    private final String TAG = ResultsViewModel.class.getSimpleName();
    private final BudgetRepository budgetRepository;
    private final UUID budgetId;
    private final Calendar transactionHistoryStart;
    private final Calendar transactionHistoryEnd;
    private List<Category> selectedCategories;

    public ResultsViewModel(BudgetRepository repository, UUID budgetId, Calendar transactionHistoryStart, Calendar transactionHistoryEnd) {
        budgetRepository = repository;
        this.budgetId = budgetId;
        this.transactionHistoryStart = transactionHistoryStart;
        this.transactionHistoryEnd = transactionHistoryEnd;
    }

    public List<Category> getSelectedCategories() {
        return selectedCategories;
    }

    public void setSelectedCategories(List<Category> selectedCategories) {
        this.selectedCategories = selectedCategories;
    }
}
