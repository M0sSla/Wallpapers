package com.example.myapplication.API;

import androidx.annotation.NonNull;

import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

import java.io.IOException;

import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class NasaService {
    public static String KEY = "gc3M72MFeCDhSRzQJTSf1uWt7zyjPbPVn7BhtjaN";

    NasaApi api;

    public NasaService() {
        Retrofit retrofit = createRetrofit();
        api = retrofit.create(NasaApi.class);
    }

    public NasaApi getApi() {
        return api;
    }


    private OkHttpClient createOkHttpClient() {
        OkHttpClient.Builder builder = new OkHttpClient.Builder();
        builder.addInterceptor(new Interceptor() {
            @NonNull
            public Response intercept(@NonNull Chain chain) throws IOException {
                final Request originalRequest = chain.request();
                final HttpUrl url = originalRequest.url()
                        .newBuilder()
                        .addQueryParameter("api_key", KEY)
                        .build();
                final Request newRequest = originalRequest.newBuilder().url(url).build();
                return chain.proceed(newRequest);
            }
        });
        return builder.build();
    }

    private Retrofit createRetrofit() {
        return new Retrofit.Builder()
                .baseUrl("https://api.nasa.gov/EPIC/api/")
                .addConverterFactory(GsonConverterFactory.create())
                .client(createOkHttpClient())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();
    }
}
