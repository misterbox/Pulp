package com.theskyegriffin.pulp.results;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.theskyegriffin.pulp.IView;
import com.theskyegriffin.pulp.IViewModel;
import com.theskyegriffin.pulp.databinding.ResultsFragBinding;

public class ResultsFragment extends Fragment implements IView {
    private ResultsViewModel viewModel;
    private ResultsFragBinding resultsFragBinding;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        resultsFragBinding = ResultsFragBinding.inflate(inflater, container, false);
        resultsFragBinding.setViewModel(viewModel);
        View root = resultsFragBinding.getRoot();

        return root;
    }

    @Override
    public void onResume() {
        super.onResume();
        viewModel.loadBudgetData();
    }

    @Override
    public void start() {

    }

    @Override
    public void setViewModel(IViewModel viewModel) {
        if (this.viewModel == null) {
            this.viewModel = (ResultsViewModel) viewModel;
        }
    }
}
