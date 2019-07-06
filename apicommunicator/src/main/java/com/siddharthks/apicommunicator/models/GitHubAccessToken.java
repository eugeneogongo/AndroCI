package com.siddharthks.apicommunicator.models;

import com.google.gson.annotations.SerializedName;

public class GitHubAccessToken {
    @SerializedName("access_token")
    private String access_token;

    public String getGitHubAccessToken() {
        return access_token;
    }
}
