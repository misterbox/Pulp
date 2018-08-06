package com.theskyegriffin.pulp.budgetsqueeze;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.theskyegriffin.pulp.data.ynab.Category;
import com.theskyegriffin.pulp.databinding.CategoriesFragBinding;
import com.theskyegriffin.pulp.databinding.CategoryListItemBinding;

import java.util.ArrayList;
import java.util.List;

public class CategoryListFragment extends Fragment implements com.theskyegriffin.pulp.View {
    private BudgetSqueezeViewModel viewModel;
    CategoriesFragBinding categoriesFragBinding;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onResume() {
        super.onResume();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        categoriesFragBinding = CategoriesFragBinding.inflate(inflater, container, false);
        categoriesFragBinding.setViewModel(viewModel);
        View root = categoriesFragBinding.getRoot();

        return root;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        setupListAdapter();
    }

    @Override
    public void start() {
    }

    @Override
    public void setViewModel(@NonNull BudgetSqueezeViewModel viewModel) {
        this.viewModel = viewModel;
    }

    private void setupListAdapter() {
        RecyclerView recyclerView = categoriesFragBinding.rvCategories;
        List<Category> categories = new ArrayList<>();
        CategoryListAdapter adapter = new CategoryListAdapter(categories, viewModel);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
    }

    public static class CategoryAdapter extends BaseAdapter {
        private List<Category> categories;
        private BudgetSqueezeViewModel viewModel;

        public CategoryAdapter(List<Category> categories, BudgetSqueezeViewModel viewModel) {
            this.categories = categories;
            this.viewModel = viewModel;
        }

        public void replaceData(List<Category> categories) {
            setList(categories);
        }

        private void setList(List<Category> categories) {
            this.categories = categories;
            notifyDataSetChanged();
        }

        @Override
        public int getCount() {
            return categories != null ? categories.size() : 0;
        }

        @Override
        public Category getItem(int i) {
            return categories.get(i);
        }

        @Override
        public long getItemId(int i) {
            return i;
        }

        @Override
        public View getView(int i, View view, ViewGroup viewGroup) {
            Category category = getItem(i);
            CategoryListItemBinding binding;

            if (view == null) {
                LayoutInflater inflater = LayoutInflater.from(viewGroup.getContext());
                binding = CategoryListItemBinding.inflate(inflater, viewGroup, false);
            }
            else {
                binding = DataBindingUtil.getBinding(view);
            }

            binding.setCategory(category);
            binding.setViewModel(viewModel);

            return binding.getRoot();
        }
    }
}
