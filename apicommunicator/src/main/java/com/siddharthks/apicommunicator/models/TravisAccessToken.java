package com.siddharthks.apicommunicator.models;

import com.google.gson.annotations.SerializedName;

public class TravisAccessToken {
    @SerializedName("access_token")
    private String access_token;

    public String getTravisAccessToken() {
        return access_token;
    }
}
