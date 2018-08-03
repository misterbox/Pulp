package com.theskyegriffin.pulp;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;

import com.theskyegriffin.pulp.data.BudgetRepository;
import com.theskyegriffin.pulp.data.api.Client;
import com.theskyegriffin.pulp.data.api.Service;

public class BudgetSqueezeActivity extends AppCompatActivity {
    private Service apiService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.budget_squeeze_activity);

        final String API_TOKEN = getApplicationContext().getString(R.string.api_token);
        apiService = Client.getApiClient(API_TOKEN);
        BudgetSqueezeViewModel viewModel = new BudgetSqueezeViewModel(BudgetRepository.getInstance(apiService), getApplicationContext());

        ViewPager viewPager = findViewById(R.id.viewPager);
        viewPager.setAdapter(new PulpFragmentPagerAdapter(getSupportFragmentManager(), viewModel));

        TabLayout tabLayout = findViewById(R.id.tabLayout);
        tabLayout.setupWithViewPager(viewPager);
    }
}
