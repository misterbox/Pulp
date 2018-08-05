package com.theskyegriffin.pulp.budgetsqueeze;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;

import com.theskyegriffin.pulp.data.ynab.Budget;
import com.theskyegriffin.pulp.databinding.BudgetListItemBinding;
import com.theskyegriffin.pulp.databinding.BudgetsFragBinding;

import java.util.ArrayList;
import java.util.List;

public class BudgetFragment extends Fragment implements com.theskyegriffin.pulp.View {
    private BudgetSqueezeViewModel viewModel;
    BudgetsFragBinding budgetsFragBinding;

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
        budgetsFragBinding = BudgetsFragBinding.inflate(inflater, container, false);
        budgetsFragBinding.setViewModel(viewModel);
        View root = budgetsFragBinding.getRoot();

        return root;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        setupListAdapter();
    }

    @Override
    public void start() {
        viewModel.loadBudgets();
    }

    @Override
    public void setViewModel(@NonNull BudgetSqueezeViewModel viewModel) {
        if (this.viewModel == null) {
            this.viewModel = viewModel;
        }
    }

    private void setupListAdapter() {
        ListView budgetListView = budgetsFragBinding.budgetList;
        List<Budget> budgets = new ArrayList<Budget>();
        BudgetAdapter adapter = new BudgetAdapter(budgets, viewModel);
        budgetListView.setAdapter(adapter);
    }

    public static class BudgetAdapter extends BaseAdapter {
        private List<Budget> budgets;
        private BudgetSqueezeViewModel viewModel;

        public BudgetAdapter(List<Budget> budgets, BudgetSqueezeViewModel viewModel) {
            this.budgets = budgets;
            this.viewModel = viewModel;
        }

        public void replaceData(List<Budget> budgets) {
            setList(budgets);
        }

        private void setList(List<Budget> budgets) {
            this.budgets = budgets;
            notifyDataSetChanged();
        }

        @Override
        public int getCount() {
            return budgets != null ? budgets.size() : 0;
        }

        @Override
        public Budget getItem(int i) {
            return budgets.get(i);
        }

        @Override
        public long getItemId(int i) {
            return i;
        }

        @Override
        public View getView(int i, View view, ViewGroup viewGroup) {
            Budget budget = getItem(i);
            BudgetListItemBinding binding;

            if (view == null) {
                LayoutInflater inflater = LayoutInflater.from(viewGroup.getContext());
                binding = BudgetListItemBinding.inflate(inflater, viewGroup, false);
            }
            else {
                binding = DataBindingUtil.getBinding(view);
            }
            binding.setBudget(budget);
            binding.setViewModel(viewModel);

            return binding.getRoot();
        }
    }
}
