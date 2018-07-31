package com.theskyegriffin.pulp;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;

import com.theskyegriffin.pulp.api.Client;
import com.theskyegriffin.pulp.api.Service;
import com.theskyegriffin.pulp.ynab.Budgets;
import com.theskyegriffin.pulp.ynab.ResponseWrapper;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {
    private Service apiService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ViewPager viewPager = findViewById(R.id.viewPager);
        viewPager.setAdapter(new PulpFragmentPagerAdapter(getSupportFragmentManager()));

        TabLayout tabLayout = findViewById(R.id.tabLayout);
        tabLayout.setupWithViewPager(viewPager);

        final String API_TOKEN = getApplicationContext().getString(R.string.api_token);
        apiService = Client.getApiClient(API_TOKEN);
        Call<ResponseWrapper<Budgets>> call = apiService.getBudgets();
        call.enqueue(new Callback<ResponseWrapper<Budgets>>() {
            @Override
            public void onResponse(Call<ResponseWrapper<Budgets>> call, Response<ResponseWrapper<Budgets>> response) {
                if (response.isSuccessful()) {
                    ResponseWrapper<Budgets> responseWrapper = response.body();
                }
            }

            @Override
            public void onFailure(Call<ResponseWrapper<Budgets>> call, Throwable t) {

            }
        });
    }
}
