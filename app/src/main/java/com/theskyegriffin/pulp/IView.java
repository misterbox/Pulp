package com.theskyegriffin.pulp;

import com.theskyegriffin.pulp.budgetsqueeze.BudgetSqueezeViewModel;

public interface IView {
    void start();
    void setViewModel(BudgetSqueezeViewModel viewModel);
}
