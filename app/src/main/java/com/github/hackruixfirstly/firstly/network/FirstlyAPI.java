package com.github.hackruixfirstly.firstly.network;

import com.github.hackruixfirstly.firstly.models.AuthRequest;
import com.github.hackruixfirstly.firstly.models.Experience;
import com.squareup.okhttp.ResponseBody;


import java.util.List;

import retrofit.Call;
import retrofit.http.Body;
import retrofit.http.GET;
import retrofit.http.POST;

/**
 * Created by trevor on 10/3/15.
 */
public interface FirstlyAPI {

    @POST("auth/facebook")
    Call<ResponseBody> auth(@Body AuthRequest authRequest);

    @GET("experience")
    Call<List<Experience>> getExperiences();

    @POST("experience")
    Call<Experience> postExperience(@Body Experience experience);

}
