package com.siddharthks.apicommunicator.interfaces;

import com.siddharthks.apicommunicator.models.TravisTokenRequest;
import com.siddharthks.apicommunicator.models.TravisAccessToken;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Headers;
import retrofit2.http.POST;

public interface ITravisClient {
    @Headers({
            "Content-Type: application/json",
            "User-Agent: MyClient/1.0.0"
    })
    @POST("auth/github")
    Call<TravisAccessToken> getAccessToken(
            @Body TravisTokenRequest travisTokenRequest
    );
}
