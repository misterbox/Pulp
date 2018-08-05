package com.theskyegriffin.pulp.budgetsqueeze;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;

import com.theskyegriffin.pulp.R;
import com.theskyegriffin.pulp.ViewModelHolder;
import com.theskyegriffin.pulp.data.BudgetRepository;
import com.theskyegriffin.pulp.data.api.Client;
import com.theskyegriffin.pulp.data.api.Service;
import com.theskyegriffin.pulp.util.ActivityUtils;

public class BudgetSqueezeActivity extends AppCompatActivity {
    private Service apiService;
    public static final String BUDGET_VIEWMODEL_TAG = "BUDGET_VIEWMODEL_TAG";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.budget_squeeze_activity);

        final String API_TOKEN = getApplicationContext().getString(R.string.api_token);
        apiService = Client.getApiClient(API_TOKEN);
        BudgetSqueezeViewModel viewModel = findOrCreateViewModel();

        ViewPager viewPager = findViewById(R.id.viewPager);
        viewPager.setAdapter(new PulpFragmentPagerAdapter(getSupportFragmentManager(), viewModel));

        TabLayout tabLayout = findViewById(R.id.tabLayout);
        tabLayout.setupWithViewPager(viewPager);
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
            viewModel = new BudgetSqueezeViewModel(BudgetRepository.getInstance(apiService), getApplicationContext());
            ActivityUtils.addFragmentToActivity(getSupportFragmentManager(), ViewModelHolder.createContainer(viewModel), BUDGET_VIEWMODEL_TAG);
        }

        return viewModel;
    }
}
