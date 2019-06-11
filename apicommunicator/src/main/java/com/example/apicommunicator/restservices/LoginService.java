package com.example.apicommunicator.restservices;

import android.support.annotation.Nullable;

import com.example.apicommunicator.callbacks.IAPICallBack;
import com.example.apicommunicator.interfaces.IGitHubClient;
import com.example.apicommunicator.interfaces.ITravisClient;
import com.example.apicommunicator.models.GitHubAccessToken;
import com.example.apicommunicator.models.TravisAccessToken;
import com.example.apicommunicator.models.TravisTokenRequest;
import com.example.apicommunicator.models.TravisTokens;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static com.example.apicommunicator.models.GitHubConfigHelper.getClientID;
import static com.example.apicommunicator.models.GitHubConfigHelper.getSecret;
import static com.example.apicommunicator.restservices.APIClient.getPrivateClient;
import static com.example.apicommunicator.restservices.APIClient.getPublicClient;

public class LoginService {

    public static String CLIENT_ID = getClientID();
    public static String CLIENT_SECRET = getSecret();

    /**
     * First we obtain the GitHub access_token in getTravisToken() and
     * upon success, we proceed to exchange it for Public Travis access_token
     * and Private Travis access_token in getPublicToken() & getPrivateToken() respectively
     * @param code
     * @param callback
     */
    public static void getTravisToken(String code, @Nullable final IAPICallBack callback){
        Retrofit.Builder builder = new Retrofit.Builder()
                .baseUrl("https://github.com/")
                .addConverterFactory(GsonConverterFactory.create());
        Retrofit retrofit = builder.build();
        IGitHubClient githubClient = retrofit.create(IGitHubClient.class);
        Call<GitHubAccessToken> accessTokenCall = githubClient.getAccessToken(
                CLIENT_ID,
                CLIENT_SECRET,
                code
        );
        accessTokenCall.enqueue(new Callback<GitHubAccessToken>() {
            @Override
            public void onResponse(Call<GitHubAccessToken> call, Response<GitHubAccessToken> response) {
                String github_token = response.body().getGitHubAccessToken();
                getPublicToken(github_token, callback);
            }

            @Override
            public void onFailure(Call<GitHubAccessToken> call, Throwable t) {
                if(t != null && t.getMessage() != null) {
                    callback.onError(t.getMessage());
                }
            }
        });
    }

    static void getPublicToken(final String github_token, @Nullable final IAPICallBack callback){
        Retrofit retrofit = getPublicClient();
        ITravisClient travisClient =   retrofit.create(ITravisClient.class);
        TravisTokenRequest travisTokenRequest= new TravisTokenRequest();
        travisTokenRequest.setGitHubToken(github_token);
        Call<TravisAccessToken> accessTokenCall = travisClient.getAccessToken(
                travisTokenRequest
        );
        accessTokenCall.enqueue(new Callback<TravisAccessToken>() {
            @Override
            public void onResponse(Call<TravisAccessToken> call, Response<TravisAccessToken> response) {
                String public_travis_token = response.body().getTravisAccessToken();
                getPrivateToken(github_token, public_travis_token, callback);
            }

            @Override
            public void onFailure(Call<TravisAccessToken> call, Throwable t) {
                if(t != null && t.getMessage() != null) {
                    callback.onError(t.getMessage());
                }
            }
        });
    }

    static void getPrivateToken(String github_token, final String public_travis_token, @Nullable final IAPICallBack callback){
        Retrofit retrofit = getPrivateClient();
        ITravisClient travisClient = retrofit.create(ITravisClient.class);
        TravisTokenRequest travisTokenRequest= new TravisTokenRequest();
        travisTokenRequest.setGitHubToken(github_token);
        Call<TravisAccessToken> accessTokenCall = travisClient.getAccessToken(
                travisTokenRequest
        );
        accessTokenCall.enqueue(new Callback<TravisAccessToken>() {
            @Override
            public void onResponse(Call<TravisAccessToken> call, Response<TravisAccessToken> response) {
                String private_travis_token = response.body().getTravisAccessToken();
                TravisTokens token = new TravisTokens(public_travis_token,private_travis_token);
                callback.onSuccess(token);
            }

            @Override
            public void onFailure(Call<TravisAccessToken> call, Throwable t) {
                if(t != null && t.getMessage() != null) {
                    callback.onError(t.getMessage());
                }
            }
        });
    }
}