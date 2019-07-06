package com.siddharthks.apicommunicator.models.submodels;

import com.google.gson.annotations.SerializedName;

public class OrgsPermissions {
    @SerializedName("read")
    private boolean mRead;

    @SerializedName("sync")
    private boolean mSync;

    public boolean isRead() {
        return mRead;
    }

    public void setRead(boolean mRead) {
        this.mRead = mRead;
    }

    public boolean isSync() {
        return mSync;
    }

    public void setSync(boolean mSync) {
        this.mSync = mSync;
    }
}
