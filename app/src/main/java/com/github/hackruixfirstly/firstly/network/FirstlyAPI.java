package com.github.hackruixfirstly.firstly.network;

import com.google.gson.JsonObject;

import org.json.JSONObject;

import retrofit.Call;
import retrofit.http.GET;

/**
 * Created by trevor on 10/3/15.
 */
public interface FirstlyAPI {


    @GET("/api")
    Call<JsonObject> getTest();

}
