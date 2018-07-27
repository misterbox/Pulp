package com.theskyegriffin.pulp.Fragments;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.theskyegriffin.pulp.CategoryListAdapter;
import com.theskyegriffin.pulp.R;

public class CategoryListFragment extends Fragment {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_category_list, container, false);
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
}
