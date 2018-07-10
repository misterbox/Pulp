package com.theskyegriffin.pulp;

import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        String[] categories = new String[] {
                "Food",
                "Electricity",
                "Rent",
                "Car Maintenance"
        };

        ViewPager viewPager = findViewById(R.id.viewPager);
        viewPager.setAdapter(new PulpFragmentPagerAdapter(getSupportFragmentManager()));

        TabLayout tabLayout = findViewById(R.id.tabLayout);
        tabLayout.setupWithViewPager(viewPager);

//        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.rv_categories);
//        CategoryListAdapter categoryAdapter = new CategoryListAdapter(categories);
//        recyclerView.setAdapter(categoryAdapter);
//        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }
}
