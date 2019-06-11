package com.example.apicommunicator.restservices;

import android.support.annotation.Nullable;

import com.example.apicommunicator.callbacks.IAPICallBack;
import com.example.apicommunicator.interfaces.IOrgs;
import com.example.apicommunicator.models.OrgResponse;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

import static com.example.apicommunicator.restservices.APIClient.getPrivateClient;
import static com.example.apicommunicator.restservices.APIClient.getPublicClient;
public class OrgsService {

    public static void getIndividualOrg(String public_travis_token,  String private_travis_token,int id, @Nullable final IAPICallBack callback){
        String public_authorization_token;
        String private_authorization_token;
        Retrofit retrofit;
        Call<OrgResponse> orgsResponseCall;

        if(public_travis_token != null){
            public_authorization_token = "token " + public_travis_token;
            retrofit = getPublicClient();
            IOrgs orgs = retrofit.create(IOrgs.class);
            orgsResponseCall = orgs.getIndividualOrg(
                    id,
                    public_authorization_token
            );
        }
        else{
            private_authorization_token = "token " + private_travis_token;
            retrofit = getPrivateClient();
            IOrgs orgs = retrofit.create(IOrgs.class);
            orgsResponseCall = orgs.getIndividualOrg(
                    id,
                    private_authorization_token
            );
        }
        // Yet to deal if both tokens are null
        orgsResponseCall.enqueue(new Callback<OrgResponse>() {
            @Override
            public void onResponse(Call<OrgResponse> call, Response<OrgResponse> response) {
                callback.onSuccess(response.body());
            }

            @Override
            public void onFailure(Call<OrgResponse> call, Throwable t) {
                callback.onError(t.getMessage());
            }
        });
    }
}
