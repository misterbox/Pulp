package com.theskyegriffin.pulp.fragments;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.theskyegriffin.pulp.BudgetSqueezeViewModel;
import com.theskyegriffin.pulp.R;

public class HistoryFragment extends Fragment implements com.theskyegriffin.pulp.fragments.View {
    private BudgetSqueezeViewModel viewModel;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_transaction_history, container, false);

        return view;
    }

    @Override
    public void setViewModel(@NonNull BudgetSqueezeViewModel viewModel) {
        this.viewModel = viewModel;
    }
}
