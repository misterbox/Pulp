package com.theskyegriffin.pulp;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioButton;
import android.widget.RadioGroup;

public class BudgetFragment extends Fragment {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view =  inflater.inflate(R.layout.fragment_budget, container, false);
        Context context =  getContext();
        RadioGroup radioGroup = view.findViewById(R.id.rg_budget);
        int numberOfBudgets = 2;

        for (int i = 0; i < numberOfBudgets; i++) {
            RadioButton radioButton = new RadioButton(context);
            radioButton.setText("Budget " + i);
            radioButton.setId(View.generateViewId());
            radioGroup.addView(radioButton);
        }

        return view;
    }
}
