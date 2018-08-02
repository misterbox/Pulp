package com.theskyegriffin.pulp;

import android.databinding.BindingAdapter;
import android.widget.ListView;

import com.theskyegriffin.pulp.data.ynab.Budget;

import java.util.List;

public class BudgetListBindings {
    @BindingAdapter("app:budgets")
    public static void setBudgets(ListView listView, List<Budget> budgets) {

    }
}
