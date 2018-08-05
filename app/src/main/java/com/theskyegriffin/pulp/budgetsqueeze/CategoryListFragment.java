package com.theskyegriffin.pulp.budgetsqueeze;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.theskyegriffin.pulp.R;

public class CategoryListFragment extends Fragment implements com.theskyegriffin.pulp.View {
    private BudgetSqueezeViewModel viewModel;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onResume() {
        super.onResume();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.categories_frag, container, false);
        Context context = getContext();
        RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.rv_categories);
        String[] categories = new String[] {
                "Food",
                "Electricity",
                "Rent",
                "Car Maintenance",
                "Food",
                "Electricity",
                "Rent",
                "Car Maintenance",
                "Food",
                "Electricity",
                "Rent",
                "Car Maintenance",
                "Food",
                "Electricity",
                "Rent",
                "Car Maintenance",
        };
        CategoryListAdapter categoryAdapter = new CategoryListAdapter(categories);
        recyclerView.setAdapter(categoryAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(context));

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
