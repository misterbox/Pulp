package com.theskyegriffin.pulp.budgetsqueeze;

import android.content.Context;
import android.databinding.BaseObservable;
import android.databinding.ObservableArrayList;
import android.databinding.ObservableArrayMap;
import android.databinding.ObservableList;
import android.util.Log;

import com.theskyegriffin.pulp.BR;
import com.theskyegriffin.pulp.data.BudgetRepository;
import com.theskyegriffin.pulp.data.ynab.Budget;
import com.theskyegriffin.pulp.data.ynab.Budgets;
import com.theskyegriffin.pulp.data.ynab.CategoryGroups;
import com.theskyegriffin.pulp.data.ynab.ResponseWrapper;

import java.util.Arrays;
import java.util.List;
import java.util.UUID;

public class BudgetSqueezeViewModel extends BaseObservable  {
    private final String TAG = BudgetSqueezeViewModel.class.getSimpleName();
    private final BudgetRepository budgetRepository;
    public final ObservableList<Budget> budgets = new ObservableArrayList<>();
    public final ObservableArrayMap<UUID, CategoryGroups> budgetCategoryMap = new ObservableArrayMap<>();
    private Budget selectedBudget;
    private Context context;
    private boolean loadingBudgets = false;
    private boolean loadingCategories = false;

    public BudgetSqueezeViewModel(BudgetRepository repository, Context context) {
        this.context = context.getApplicationContext();
        budgetRepository = repository;
    }

    public void start() {
        loadBudgets();
    }

    public void loadBudgets() {
        if (budgets.isEmpty() && !loadingBudgets) {
            loadingBudgets = true;
            budgetRepository.getBudgets(new BudgetRepository.RepositoryCallback<Budgets>() {
                @Override
                public void onDataLoaded(ResponseWrapper<Budgets> data) {
                    Log.d(TAG, "budgets loaded");
                    List<Budget> c = Arrays.asList(data.getData().getBudgets());
                    budgets.clear();
                    budgets.addAll(c);
                    loadingBudgets = false;
                    notifyPropertyChanged(BR.budget);
                }

                @Override
                public void onDataNoAvailable() {

                }
            });
        }
    }

    public void loadCategoriesForBudget(final UUID budgetId) {
        if (selectedBudget != null && !loadingCategories && !budgetCategoryMap.containsKey(budgetId)) {
            loadingCategories = true;
            budgetRepository.getCategories(selectedBudget, new BudgetRepository.RepositoryCallback<CategoryGroups>() {
                @Override
                public void onDataLoaded(ResponseWrapper<CategoryGroups> data) {
                    Log.d(TAG, "categories loaded");
                    CategoryGroups groups = data.getData();
                    budgetCategoryMap.put(budgetId, groups);
                    loadingCategories = false;
                    notifyPropertyChanged(BR._all);
                }

                @Override
                public void onDataNoAvailable() {

                }
            });
        }
    }

    public void onBudgetClicked(Budget clickedBudget) {
        for (Budget budget : budgets) {
            if (budget.getId() == clickedBudget.getId()) {
                budget.setSelected(true);
                selectedBudget = budget;
            }
            else {
                budget.setSelected(false);
            }
        }

        notifyPropertyChanged(BR._all);
        loadCategoriesForBudget(selectedBudget.getId());
    }
}
