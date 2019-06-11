package com.example.apicommunicator.callbacks;

import android.support.annotation.NonNull;

public interface IAPICallBack<T> {
    void onSuccess(@NonNull T value);

    void onError(@NonNull String errorMessage);
}