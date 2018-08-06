package com.theskyegriffin.pulp.budgetsqueeze;

import android.content.Context;
import android.databinding.BaseObservable;
import android.databinding.ObservableArrayList;
import android.databinding.ObservableList;
import android.support.v4.util.ArrayMap;
import android.util.Log;

import com.theskyegriffin.pulp.BR;
import com.theskyegriffin.pulp.data.BudgetRepository;
import com.theskyegriffin.pulp.data.ynab.Budget;
import com.theskyegriffin.pulp.data.ynab.Budgets;
import com.theskyegriffin.pulp.data.ynab.Category;
import com.theskyegriffin.pulp.data.ynab.CategoryGroup;
import com.theskyegriffin.pulp.data.ynab.CategoryGroups;
import com.theskyegriffin.pulp.data.ynab.ResponseWrapper;

import java.util.Arrays;
import java.util.List;
import java.util.UUID;

public class BudgetSqueezeViewModel extends BaseObservable  {
    private final String TAG = BudgetSqueezeViewModel.class.getSimpleName();
    private final BudgetRepository budgetRepository;
    public final ObservableList<Budget> budgets = new ObservableArrayList<>();
    public final ArrayMap<UUID, CategoryGroups> budgetCategoryMap = new ArrayMap<>();
    public final ObservableList<Category> categories = new ObservableArrayList<>();
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

    public void loadCategories() {
        final UUID selectedBudgetId = selectedBudget.getId();
        if (selectedBudget != null && !loadingCategories && !budgetCategoryMap.containsKey(selectedBudgetId)) {
            loadingCategories = true;
            budgetRepository.getCategories(selectedBudget, new BudgetRepository.RepositoryCallback<CategoryGroups>() {
                @Override
                public void onDataLoaded(ResponseWrapper<CategoryGroups> data) {
                    Log.d(TAG, "categories loaded");
                    CategoryGroups groups = data.getData();
                    budgetCategoryMap.put(selectedBudgetId, groups);
                    loadingCategories = false;
                    notifyPropertyChanged(BR._all);
                    setCategories();
                }

                @Override
                public void onDataNoAvailable() {

                }
            });
        }
        else if (selectedBudget != null && !loadingCategories) {
            setCategories();
        }
    }

    private void setCategories() {
        CategoryGroups selectedCategoryGroups = budgetCategoryMap.get(selectedBudget.getId());

        for (CategoryGroup group : selectedCategoryGroups.getCategoryGroups()) {
            if (!isDefaultCategoryGroup(group)) {
                categories.addAll(Arrays.asList(group.getCategories()));
            }
        }
    }

    private boolean isDefaultCategoryGroup(CategoryGroup group) {
        String groupName = group.getName();

        return groupName.equals(CategoryGroup.CREDIT_CARD_PAYMENTS)
                || groupName.equals(CategoryGroup.INTERNAL_MASTER_CATEGORY)
                || groupName.equals(CategoryGroup.HIDDEN_CATEGORIES);
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
