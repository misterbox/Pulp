package com.theskyegriffin.pulp.api;

import com.google.gson.Gson;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static java.lang.String.format;

public class Client {
    private static Service API_CLIENT;
    private static String API_TOKEN;

    static {
        setupApiClient();
    }

    private static void setupApiClient() {
        Gson gsonConverter = new Gson();
        Retrofit.Builder builder = new Retrofit.Builder()
                .baseUrl(Constants.BASE_API_URL)
                .addConverterFactory(GsonConverterFactory.create(gsonConverter));
        OkHttpClient httpClient = new OkHttpClient.Builder()
                .addInterceptor(new HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
                .addInterceptor(new Interceptor() {
                    @Override
                    public Response intercept(Chain chain) throws IOException {
                        Request request = chain.request();
                        Request newRequest = request.newBuilder()
                                .addHeader("Authorization", format("Bearer %s", API_TOKEN))
                                .build();

                        return chain.proceed(newRequest);
                    }
                }).build();
        builder.client(httpClient);

        API_CLIENT = builder.build().create(Service.class);
    }

    public static Service getApiClient(String apiToken) {
        API_TOKEN = apiToken;

        return API_CLIENT;
    }
}
