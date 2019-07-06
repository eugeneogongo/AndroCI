package com.siddharthks.apicommunicator.models;

public class TravisTokens {
    String publicToken;
    String privateToken;

    public TravisTokens(String publicToken, String privateToken) {
        this.publicToken = publicToken;
        this.privateToken = privateToken;
    }

    public String getPublicToken() {
        return publicToken;
    }

    public String getPrivateToken() {
        return privateToken;
    }
}
