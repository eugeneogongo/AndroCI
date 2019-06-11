package com.example.apicommunicator.interfaces;

import com.example.apicommunicator.models.OrgResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.Path;


public interface IOrgs {
    @Headers({
            "Travis-API-Version: 3",
            "User-Agent: MyClient/1.0.0"
    })
    @GET("org/{id}")
    Call<OrgResponse> getIndividualOrg(@Path("id") int id, @Header("Authorization") String authorization);
}
