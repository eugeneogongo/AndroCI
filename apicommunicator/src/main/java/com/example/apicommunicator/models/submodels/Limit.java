package com.example.apicommunicator.models.submodels;

import com.google.gson.annotations.SerializedName;

public class Limit {
    @SerializedName("@href")
    private String mHREF;

    @SerializedName("offset")
    private int mOffset;

    @SerializedName("limit")
    private int mLimit;

    public String getHREF() {
        return mHREF;
    }

    public void setHREF(String mHREF) {
        this.mHREF = mHREF;
    }

    public int getOffset() {
        return mOffset;
    }

    public void setOffset(int mOffset) {
        this.mOffset = mOffset;
    }

    public int getLimit() {
        return mLimit;
    }

    public void setLimit(int mLimit) {
        this.mLimit = mLimit;
    }
}
