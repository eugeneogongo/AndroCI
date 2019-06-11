package com.example.apicommunicator.models;

import com.example.apicommunicator.BuildConfig;

public class GitHubConfigHelper {
    public static String getClientID() {
        return BuildConfig.GITHUB_CLIENT_ID;
    }

    public static String getSecret() {
        return BuildConfig.GITHUB_SECRET;
    }
}
