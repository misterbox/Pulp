package com.theskyegriffin.pulp.budgetsqueeze;

import android.databinding.DataBindingUtil;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.theskyegriffin.pulp.data.ynab.Category;
import com.theskyegriffin.pulp.databinding.CategoryListItemBinding;

import java.util.List;

public class CategoryListAdapter extends RecyclerView.Adapter<CategoryListAdapter.ViewHolder> {
    private List<Category> categories;
    private BudgetSqueezeViewModel viewModel;

    public CategoryListAdapter(List<Category> categories, BudgetSqueezeViewModel viewModel) {
        this.categories = categories;
        this.viewModel = viewModel;
    }

    @NonNull
    @Override
    public CategoryListAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        CategoryListItemBinding binding = CategoryListItemBinding.inflate(inflater, parent, false);
        binding.setViewModel(viewModel);

        return new ViewHolder(binding.getRoot());
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, int i) {
        Category category = getItem(i);
        CategoryListItemBinding binding = DataBindingUtil.getBinding(viewHolder.categoryView);
        binding.setCategory(category);
    }

    @Override
    public int getItemCount() {
        return categories != null ? categories.size() : 0;
    }

    public void replaceData(List<Category> categories) {
        setList(categories);
    }

    private void setList(List<Category> categories) {
        this.categories = categories;
        notifyDataSetChanged();
    }

    private Category getItem(int i) {
        return categories.get(i);
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        View categoryView;

        ViewHolder(View view) {
            super(view);

            categoryView = view;
        }
    }
}
