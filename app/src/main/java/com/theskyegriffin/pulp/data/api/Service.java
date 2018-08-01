package com.theskyegriffin.pulp.data.api;

import com.theskyegriffin.pulp.data.ynab.Budgets;
import com.theskyegriffin.pulp.data.ynab.ResponseWrapper;

import retrofit2.Call;
import retrofit2.http.GET;

public interface Service {
    @GET("budgets")
    Call<ResponseWrapper<Budgets>> getBudgets();
}
