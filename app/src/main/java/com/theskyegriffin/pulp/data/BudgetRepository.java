package com.theskyegriffin.pulp.data;

import android.support.annotation.NonNull;

import com.theskyegriffin.pulp.data.api.Service;
import com.theskyegriffin.pulp.data.ynab.Budget;
import com.theskyegriffin.pulp.data.ynab.Budgets;
import com.theskyegriffin.pulp.data.ynab.CategoryGroups;
import com.theskyegriffin.pulp.data.ynab.ResponseWrapper;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class BudgetRepository {
    private static BudgetRepository INSTANCE = null;
    private final Service apiService;

    private BudgetRepository(Service apiService) {
        this.apiService = apiService;
    }

    public static BudgetRepository getInstance(Service apiService) {
        if (INSTANCE == null) {
            INSTANCE = new BudgetRepository(apiService);
        }

        return INSTANCE;
    }

    public static void destroyInstance() {
        INSTANCE = null;
    }

    public void getBudgets(@NonNull final RepositoryCallback callback) {
        Call<ResponseWrapper<Budgets>> call = apiService.getBudgets();
        call.enqueue(new Callback<ResponseWrapper<Budgets>>() {
            @Override
            public void onResponse(Call<ResponseWrapper<Budgets>> call, Response<ResponseWrapper<Budgets>> response) {
                if (response.isSuccessful()) {
                    callback.onDataLoaded(response.body());
                }
            }

            @Override
            public void onFailure(Call<ResponseWrapper<Budgets>> call, Throwable t) {
                callback.onDataNoAvailable();
            }
        });
    }

    public void getCategories(@NonNull final Budget budget, @NonNull final RepositoryCallback callback) {
        Call<ResponseWrapper<CategoryGroups>> call = apiService.getCategories(budget.getId());
        call.enqueue(new Callback<ResponseWrapper<CategoryGroups>>() {

            @Override
            public void onResponse(Call<ResponseWrapper<CategoryGroups>> call, Response<ResponseWrapper<CategoryGroups>> response) {
                if (response.isSuccessful()) {
                    callback.onDataLoaded(response.body());
                }
            }

            @Override
            public void onFailure(Call<ResponseWrapper<CategoryGroups>> call, Throwable t) {
                callback.onDataNoAvailable();
            }
        });
    }

    public interface RepositoryCallback<T> {
        void onDataLoaded(ResponseWrapper<T> data);
        void onDataNoAvailable();
    }
}

