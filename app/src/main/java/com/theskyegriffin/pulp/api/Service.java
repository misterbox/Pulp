package com.theskyegriffin.pulp.api;

import com.theskyegriffin.pulp.ynab.Budgets;
import com.theskyegriffin.pulp.ynab.ResponseWrapper;

import retrofit2.Call;
import retrofit2.http.GET;

public interface Service {
    @GET("budgets")
    Call<ResponseWrapper<Budgets>> getBudgets();
}
