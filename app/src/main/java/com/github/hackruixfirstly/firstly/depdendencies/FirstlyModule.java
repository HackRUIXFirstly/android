package com.github.hackruixfirstly.firstly.depdendencies;

import android.content.Context;

import com.github.hackruixfirstly.firstly.FirstlyApplication;
import com.github.hackruixfirstly.firstly.network.FirstlyAPI;
import com.github.hackruixfirstly.firstly.network.LoggingInterceptor;
import com.squareup.okhttp.OkHttpClient;

import javax.inject.Named;
import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import retrofit.GsonConverterFactory;
import retrofit.Retrofit;

/**
 * Created by trevor on 10/3/15.
 */

@Module
public class FirstlyModule {

    private Context context;

    public FirstlyModule(FirstlyApplication application) {
        this.context = application;
    }

    @Provides
    @Singleton
    public Context provideApplicationContext() {
        return context;
    }

    @Provides
    @Named("endpoint")
    public String provideEndpoint() {
        return "http://firstly.shiggy.xyz"; //TODO no hardcoding.
    }

    @Provides
    @Singleton
    public OkHttpClient provideOkHttp(Context context) {
        OkHttpClient client = new OkHttpClient();

        client.interceptors().add(new LoggingInterceptor());

        return client;
    }

    @Provides
    @Singleton
    public FirstlyAPI provideAPI(@Named("endpoint") String endpoint, OkHttpClient client) {
        Retrofit retrofit = new Retrofit.Builder().baseUrl(endpoint).client(client).addConverterFactory(GsonConverterFactory.create()).build();

        return retrofit.create(FirstlyAPI.class);
    }

}
