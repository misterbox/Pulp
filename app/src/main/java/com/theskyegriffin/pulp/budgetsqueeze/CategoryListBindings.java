package com.theskyegriffin.pulp.budgetsqueeze;

import android.databinding.BindingAdapter;
import android.support.v7.widget.RecyclerView;

import com.theskyegriffin.pulp.data.ynab.Category;

import java.util.List;

public class CategoryListBindings {
    @BindingAdapter("categories")
    public static void setCategories(RecyclerView view, List<Category> categories) {
        CategoryListAdapter adapter = (CategoryListAdapter) view.getAdapter();

        if (adapter != null) {
            adapter.replaceData(categories);
        }
    }
}
