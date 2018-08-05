package com.theskyegriffin.pulp.budgetsqueeze;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.theskyegriffin.pulp.R;

public class HistoryFragment extends Fragment implements com.theskyegriffin.pulp.View {
    private BudgetSqueezeViewModel viewModel;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.transaction_history_frag, container, false);

        return view;
    }

    @Override
    public void start() {

    }

    @Override
    public void setViewModel(@NonNull BudgetSqueezeViewModel viewModel) {
        this.viewModel = viewModel;
    }
}
