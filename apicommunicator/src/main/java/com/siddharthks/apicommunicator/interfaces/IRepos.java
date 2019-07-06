package com.siddharthks.apicommunicator.interfaces;


import com.siddharthks.apicommunicator.models.ReposResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.Query;

public interface IRepos {
    @Headers({
            "Travis-API-Version: 3",
            "User-Agent: MyClient/1.0.0"
    })
    @GET("repos")
    Call<ReposResponse> getRepos(@Header("Authorization") String authorization, @Query("include") String include);
}
