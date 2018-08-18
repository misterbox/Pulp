package com.theskyegriffin.pulp.results;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.theskyegriffin.pulp.R;
import com.theskyegriffin.pulp.ViewModelHolder;
import com.theskyegriffin.pulp.data.api.Client;
import com.theskyegriffin.pulp.data.api.Service;
import com.theskyegriffin.pulp.util.ActivityUtils;

public class ResultsActivity extends AppCompatActivity {
    private Service apiService;
    private ResultsViewModel viewModel;
    public static final String RESULTS_VIEWMODEL_TAG = "RESULTS_VIEWMODEL_TAG";

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.results_activity);

        final String API_TOKEN = getApplicationContext().getString(R.string.api_token);
        apiService = Client.getApiClient(API_TOKEN);

        ResultsFragment resultsFragment = findOrCreateViewFragment();
        viewModel = findViewModel();
        resultsFragment.setViewModel(viewModel);
    }

    private ResultsFragment findOrCreateViewFragment() {
        ResultsFragment fragment = (ResultsFragment) getSupportFragmentManager().findFragmentById(R.id.fl_content);

        if (fragment == null) {
            fragment = new ResultsFragment();
            ActivityUtils.addFragmentToActivity(getSupportFragmentManager(), fragment, R.id.fl_content);
        }

        return fragment;
    }

    private ResultsViewModel findViewModel() {
        @SuppressWarnings("unchecked")
        ViewModelHolder<ResultsViewModel> retainedViewModel =
                (ViewModelHolder<ResultsViewModel>) getSupportFragmentManager()
                        .findFragmentByTag(RESULTS_VIEWMODEL_TAG);

        return retainedViewModel.getViewModel();
    }
}
