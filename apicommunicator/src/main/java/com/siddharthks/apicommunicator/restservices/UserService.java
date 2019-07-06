package com.siddharthks.apicommunicator.restservices;

import androidx.annotation.Nullable;

import com.siddharthks.apicommunicator.R;
import com.siddharthks.apicommunicator.callbacks.IAPICallBack;
import com.siddharthks.apicommunicator.interfaces.IUser;
import com.siddharthks.apicommunicator.models.LibApp;
import com.siddharthks.apicommunicator.models.UserResponse;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

import static com.siddharthks.apicommunicator.restservices.APIClient.getPrivateClient;
import static com.siddharthks.apicommunicator.restservices.APIClient.getPublicClient;

public class UserService {

    public static void getIndividualUser(String public_travis_token,  String private_travis_token, int id, @Nullable final IAPICallBack callback){
        String public_authorization_token;
        String private_authorization_token;
        Retrofit retrofit;
        Call<UserResponse> userResponseCall;

        if(public_travis_token != null){
            public_authorization_token = "token " + public_travis_token;
            retrofit = getPublicClient();
            IUser user = retrofit.create(IUser.class);
            userResponseCall = user.getIndividualUser(
                id,
                public_authorization_token
            );
        }
        else{
            private_authorization_token = "token " + private_travis_token;
            retrofit = getPrivateClient();
            IUser user = retrofit.create(IUser.class);
            userResponseCall = user.getIndividualUser(
                id,
                private_authorization_token
            );
        }

        userResponseCall.enqueue(new Callback<UserResponse>() {
            @Override
            public void onResponse(Call<UserResponse> call, Response<UserResponse> response) {
                if(response != null){
                    callback.onSuccess(response.body());
                }
                else{
                    callback.onError(LibApp.getContext().getString(R.string.null_general_response));
                }
            }

            @Override
            public void onFailure(Call<UserResponse> call, Throwable t) {
                if(t != null && t.getMessage() != null){
                    callback.onError(t.getMessage());
                }
                else{
                    callback.onError(LibApp.getContext().getString(R.string.null_failure_response));
                }
            }
        });
    }
}
