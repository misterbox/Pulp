package com.theskyegriffin.pulp;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;

public class ViewModelHolder<VM> extends Fragment {
    private VM viewModel;

    public ViewModelHolder() {}

    public static <VM> ViewModelHolder createContainer(@NonNull VM viewModel) {
        ViewModelHolder<VM> container = new ViewModelHolder<>();
        container.setViewModel(viewModel);

        return container;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRetainInstance(true);
    }

    @Nullable public VM getViewModel() {
        return viewModel;
    }

    public void setViewModel(@NonNull VM viewModel) {
        this.viewModel = viewModel;
    }
}
