package com.example.apicommunicator.interfaces;
import com.example.apicommunicator.models.GitHubAccessToken;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.Headers;
import retrofit2.http.POST;

public interface IGitHubClient {
    @Headers("Accept: application/json")
    @POST("login/oauth/access_token")
    @FormUrlEncoded
    Call<GitHubAccessToken> getAccessToken(
            @Field("client_id") String clientID,
            @Field("client_secret") String clientSecret,
            @Field("code") String code
    );
}
