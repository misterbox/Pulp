package com.theskyegriffin.pulp.budgetsqueeze;

import android.databinding.BaseObservable;
import android.databinding.Bindable;
import android.databinding.ObservableArrayList;
import android.databinding.ObservableList;
import android.support.annotation.NonNull;
import android.support.v4.util.ArrayMap;

import com.google.common.base.Predicate;
import com.google.common.collect.Collections2;
import com.google.common.collect.Lists;
import com.theskyegriffin.pulp.BR;
import com.theskyegriffin.pulp.data.BudgetRepository;
import com.theskyegriffin.pulp.data.ynab.Budget;
import com.theskyegriffin.pulp.data.ynab.Budgets;
import com.theskyegriffin.pulp.data.ynab.Category;
import com.theskyegriffin.pulp.data.ynab.CategoryGroup;
import com.theskyegriffin.pulp.data.ynab.CategoryGroups;
import com.theskyegriffin.pulp.data.ynab.ResponseWrapper;
import com.theskyegriffin.pulp.results.ResultsViewModel;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.UUID;

public class BudgetSqueezeViewModel extends BaseObservable  {
    private final String TAG = BudgetSqueezeViewModel.class.getSimpleName();
    private final BudgetRepository budgetRepository;
    private final ArrayMap<UUID, CategoryGroups> budgetCategoryMap = new ArrayMap<>();
    private Budget selectedBudget;
    private int maxTransactionHistoryMonths = 0;
    private int selectedTransactionHistoryMonths = 0;
    private Calendar transactionHistoryEnd;
    private String seekDisplay;
    private IBudgetCallback activity;
    private boolean loadingBudgets = false;
    private boolean loadingCategories = false;

    private boolean budgetSelected = false;
    private boolean categorySelected = false;
    private boolean historySelected = false;

    public final ObservableList<Budget> budgets = new ObservableArrayList<>();
    public final ObservableList<Category> categories = new ObservableArrayList<>();

    @Bindable
    public int getSeekValue() {
        return selectedTransactionHistoryMonths;
    }

    public void setSeekValue(int progress) {
        selectedTransactionHistoryMonths = progress;
        notifyPropertyChanged(BR.seekValue);
        setSeekDisplay(progress);
    }

    @Bindable
    public int getSeekMax() {
        return maxTransactionHistoryMonths;
    }

    @Bindable
    public String getSeekDisplay() {
        return seekDisplay;
    }

    public void setSeekDisplay(int progress) {
        seekDisplay = String.format("%s months", progress);
        notifyPropertyChanged(BR.seekDisplay);
    }

    public BudgetSqueezeViewModel(BudgetRepository repository, IBudgetCallback activity) {
        this.activity = activity;
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

    public void onCategoryChecked(Category category) {
        boolean checked = category.isChecked();
        category.setChecked(!checked);
        onCategoryChanged();
    }

    public void onCategoryChanged(){
        boolean result = false;

        for (Category category : categories) {
            result = result || category.isChecked();
        }

        categorySelected = result;
        onUserInput();
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

        budgetSelected = true;
        notifyPropertyChanged(BR._all);
        loadCategories();
        setTransactionHistoryDuration();
        onUserInput();
    }

    private void loadCategories() {
        final UUID selectedBudgetId = selectedBudget.getId();
        if (selectedBudget != null && !loadingCategories && !budgetCategoryMap.containsKey(selectedBudgetId)) {
            loadingCategories = true;
            budgetRepository.getCategories(selectedBudget, new BudgetRepository.RepositoryCallback<CategoryGroups>() {
                @Override
                public void onDataLoaded(ResponseWrapper<CategoryGroups> data) {
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
        ArrayList<Category> categoriesToAdd = new ArrayList<>();

        for (CategoryGroup group : selectedCategoryGroups.getCategoryGroups()) {
            if (!isDefaultCategoryGroup(group)) {
                categoriesToAdd.addAll(Arrays.asList(group.getCategories()));
            }
        }

        categories.clear();
        categories.addAll(categoriesToAdd);
        onCategoryChanged();
    }

    private boolean isDefaultCategoryGroup(CategoryGroup group) {
        String groupName = group.getName();

        return groupName.equals(CategoryGroup.CREDIT_CARD_PAYMENTS)
                || groupName.equals(CategoryGroup.INTERNAL_MASTER_CATEGORY)
                || groupName.equals(CategoryGroup.HIDDEN_CATEGORIES);
    }

    private void setTransactionHistoryDuration() {
        if (selectedBudget != null) {
            Date startDate = selectedBudget.getFirstMonth();
            Date budgetEnd = selectedBudget.getLastMonth();
            Calendar currentMonth = Calendar.getInstance();
            currentMonth.set(Calendar.DAY_OF_MONTH, 1);
            Date currentDate = currentMonth.getTime();
            Date endDate = currentDate.getTime() < budgetEnd.getTime() ? currentDate : budgetEnd;

            maxTransactionHistoryMonths = getMonths(startDate, endDate);
            setSeekValue(0);
            notifyPropertyChanged(BR._all);

            transactionHistoryEnd = Calendar.getInstance();
            transactionHistoryEnd.setTime(endDate);
        }
    }

    private int getMonths(Date startDate, Date endDate) {
        long milSeconds = endDate.getTime() - startDate.getTime();
        long seconds = milSeconds / 1000;
        int minutes = (int) seconds / 60;
        int hours = minutes / 60;
        int days = hours / 24;
        int months = days / 30;

        return months;
    }

    public void onUserInput() {
        boolean inputComplete = budgetSelected && categorySelected;
        activity.callback(inputComplete);
    }

    public static ResultsViewModel toResultsViewModel(BudgetSqueezeViewModel squeezeVm) {
        Calendar transactionHistoryStart = (Calendar) squeezeVm.transactionHistoryEnd.clone();
        transactionHistoryStart.add(Calendar.MONTH, -squeezeVm.selectedTransactionHistoryMonths);
        List<Category> selectedCategories = Lists.newArrayList(Collections2.filter(squeezeVm.categories,
                new Predicate<Category>() {
                    @Override
                    public boolean apply(@NonNull Category category) {
                        return category.isChecked();
                    }
                }
        ));
        ResultsViewModel resultsVm = new ResultsViewModel(squeezeVm.budgetRepository, squeezeVm.selectedBudget.getId(),
                transactionHistoryStart, squeezeVm.transactionHistoryEnd);
        resultsVm.setSelectedCategories(selectedCategories);

        return resultsVm;
    }
}
