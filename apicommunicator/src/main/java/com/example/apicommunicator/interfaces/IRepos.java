package com.example.apicommunicator.interfaces;

import com.example.apicommunicator.models.ReposResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Headers;

public interface IRepos {
    @Headers({
            "Travis-API-Version: 3",
            "User-Agent: MyClient/1.0.0"
    })
    @GET("repos")
    Call<ReposResponse> getUser(@Header("Authorization") String authorization);
}
