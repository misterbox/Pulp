package com.theskyegriffin.pulp.budgetsqueeze;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.theskyegriffin.pulp.IView;
import com.theskyegriffin.pulp.IViewModel;
import com.theskyegriffin.pulp.databinding.TransactionHistoryFragBinding;

public class HistoryFragment extends Fragment implements IView {
    private BudgetSqueezeViewModel viewModel;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        TransactionHistoryFragBinding binding = TransactionHistoryFragBinding.inflate(inflater, container, false);
        binding.setViewModel(viewModel);
        View root = binding.getRoot();

        return root;
    }

    @Override
    public void start() {

    }

    @Override
    public void setViewModel(@NonNull IViewModel viewModel) {
        this.viewModel = (BudgetSqueezeViewModel) viewModel;
    }
}
