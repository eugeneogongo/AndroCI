package com.siddharthks.apicommunicator.callbacks;

import androidx.annotation.NonNull;

public interface IAPICallBack<T> {
    void onSuccess(@NonNull T value);

    void onError(@NonNull String errorMessage);
}
