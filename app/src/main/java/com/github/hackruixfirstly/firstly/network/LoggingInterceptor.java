package com.github.hackruixfirstly.firstly.network;

import com.squareup.okhttp.Interceptor;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;

import java.io.IOException;

import timber.log.Timber;

/**
 * Created by trevor on 10/3/15.
 */
public class LoggingInterceptor implements Interceptor {
    @Override public Response intercept(Chain chain) throws IOException {
        Request request = chain.request();

        Timber.i("----->");
        long t1 = System.nanoTime();
        Timber.i(String.format("Sending request %s on %s%n%s", request.url(), chain.connection(), request.headers()));
        Timber.i(request.method());
        Timber.i("------");

        Response response = chain.proceed(request);

        long t2 = System.nanoTime();
        Timber.i("<-----");
        Timber.i(String.format("Received response for %s in %.1fms%n%s", response.request().url(), (t2 - t1) / 1e6d, response.headers()));
        Timber.i(Integer.toString(response.code()) + (response.isSuccessful() ? " OK" : " ERROR"));
        Timber.i("------");
        return response;
    }
}