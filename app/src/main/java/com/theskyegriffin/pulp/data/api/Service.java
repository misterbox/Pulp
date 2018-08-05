package com.theskyegriffin.pulp.data.api;

import com.theskyegriffin.pulp.data.ynab.Budgets;
import com.theskyegriffin.pulp.data.ynab.CategoryGroups;
import com.theskyegriffin.pulp.data.ynab.ResponseWrapper;

import java.util.UUID;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface Service {
    @GET("budgets")
    Call<ResponseWrapper<Budgets>> getBudgets();
    @GET("budgets/{budgetId}/categories")
    Call<ResponseWrapper<CategoryGroups>> getCategories(@Path("budgetId") UUID budgetId);
}
