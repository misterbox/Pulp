package com.theskyegriffin.pulp;

import android.databinding.BindingAdapter;
import android.widget.ListView;

import com.theskyegriffin.pulp.data.ynab.Budget;
import com.theskyegriffin.pulp.fragments.BudgetFragment;

import java.util.List;

public class BudgetListBindings {
    @BindingAdapter("budgets")
    public static void setBudgets(ListView listView, List<Budget> budgets) {
        BudgetFragment.BudgetAdapter adapter = (BudgetFragment.BudgetAdapter) listView.getAdapter();

        if (adapter != null) {
            adapter.replaceData(budgets);
        }
    }
}
