package com.theskyegriffin.pulp.budgetsqueeze;

import android.content.Context;
import android.databinding.BaseObservable;
import android.databinding.ObservableArrayList;
import android.databinding.ObservableList;

import com.theskyegriffin.pulp.BR;
import com.theskyegriffin.pulp.data.BudgetRepository;
import com.theskyegriffin.pulp.data.ynab.Budget;
import com.theskyegriffin.pulp.data.ynab.Budgets;
import com.theskyegriffin.pulp.data.ynab.ResponseWrapper;

import java.util.Arrays;
import java.util.List;

public class BudgetSqueezeViewModel extends BaseObservable  {
    private final String TAG = BudgetSqueezeViewModel.class.getSimpleName();
    private final BudgetRepository budgetRepository;
    public final ObservableList<Budget> budgets = new ObservableArrayList<>();
    private Budget selectedBudget;
    private Context context;

    public BudgetSqueezeViewModel(BudgetRepository repository, Context context) {
        this.context = context.getApplicationContext();
        budgetRepository = repository;
    }

    public void start() {
        loadBudgets();
    }

    private void loadBudgets() {
        if (budgets.isEmpty()) {
            budgetRepository.getBudgets(new BudgetRepository.RepositoryCallback<Budgets>() {
                @Override
                public void onDataLoaded(ResponseWrapper<Budgets> data) {
                    List<Budget> c = Arrays.asList(data.getData().getBudgets());
                    budgets.clear();
                    budgets.addAll(c);
                    notifyPropertyChanged(BR.budget);
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
    }
}
