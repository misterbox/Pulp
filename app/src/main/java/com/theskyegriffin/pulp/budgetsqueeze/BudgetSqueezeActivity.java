package com.theskyegriffin.pulp.budgetsqueeze;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.theskyegriffin.pulp.IViewModel;
import com.theskyegriffin.pulp.R;
import com.theskyegriffin.pulp.ViewModelHolder;
import com.theskyegriffin.pulp.data.BudgetRepository;
import com.theskyegriffin.pulp.data.api.Client;
import com.theskyegriffin.pulp.data.api.Service;
import com.theskyegriffin.pulp.results.ResultsActivity;
import com.theskyegriffin.pulp.results.ResultsViewModel;
import com.theskyegriffin.pulp.util.ActivityUtils;

public class BudgetSqueezeActivity extends AppCompatActivity implements IBudgetCallback {
    private Service apiService;
    public static final String BUDGET_VIEWMODEL_TAG = "BUDGET_VIEWMODEL_TAG";
    private FloatingActionButton fab;
    private BudgetSqueezeViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.budget_squeeze_activity);

        final String API_TOKEN = getApplicationContext().getString(R.string.api_token);
        apiService = Client.getApiClient(API_TOKEN);
        viewModel = findOrCreateViewModel();

        ViewPager viewPager = findViewById(R.id.viewPager);
        viewPager.setAdapter(new PulpFragmentPagerAdapter(getSupportFragmentManager(), (IViewModel) viewModel));

        TabLayout tabLayout = findViewById(R.id.tabLayout);
        tabLayout.setupWithViewPager(viewPager);

        setupFab();
    }

    private BudgetSqueezeViewModel findOrCreateViewModel() {
        final BudgetSqueezeViewModel viewModel;

        @SuppressWarnings("unchecked")
        ViewModelHolder<BudgetSqueezeViewModel> retainedViewModel =
                (ViewModelHolder<BudgetSqueezeViewModel>) getSupportFragmentManager()
                .findFragmentByTag(BUDGET_VIEWMODEL_TAG);

        if (retainedViewModel != null && retainedViewModel.getViewModel() != null) {
            viewModel = retainedViewModel.getViewModel();
        }
        else {
            viewModel = new BudgetSqueezeViewModel(BudgetRepository.getInstance(apiService), this);
            ActivityUtils.addFragmentToActivity(getSupportFragmentManager(), ViewModelHolder.createContainer(viewModel), BUDGET_VIEWMODEL_TAG);
        }

        return viewModel;
    }

    private void setupFab() {
        fab = findViewById(R.id.fab_squeeze_budget);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Snackbar.make(v, "FAB clicked", Snackbar.LENGTH_SHORT).show();
                ResultsViewModel resultsViewModel = BudgetSqueezeViewModel.toResultsViewModel(viewModel);
                ActivityUtils.addFragmentToActivity(getSupportFragmentManager(),
                        ViewModelHolder.createContainer(resultsViewModel),
                        ResultsActivity.RESULTS_VIEWMODEL_TAG);

                Intent intent = new Intent(getApplicationContext(), ResultsActivity.class);
                startActivity(intent);
            }
        });
    }

    @Override
    public void callback(boolean inputComplete) {
        if (inputComplete) {
            fab.setVisibility(View.VISIBLE);
        }
        else {
            fab.setVisibility(View.GONE);
        }
    }
}
