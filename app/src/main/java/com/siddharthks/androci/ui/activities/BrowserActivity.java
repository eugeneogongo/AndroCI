package com.siddharthks.androci.ui.activities;

import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;

import com.siddharthks.apicommunicator.callbacks.IAPICallBack;
import com.siddharthks.apicommunicator.models.TravisTokens;

import static com.siddharthks.apicommunicator.restservices.LoginService.getTravisToken;
import static com.siddharthks.androci.common.Constants.CALLBACK_URL;
import static com.siddharthks.androci.common.Constants.PREFS_NAME;

public class BrowserActivity extends AppCompatActivity {

    private static String code = null;
    private static String public_travis_token = null;
    private static String private_travis_token = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_browser);
        processIntent();
    }

    protected void processIntent() {
        final Intent intentFromBrowser = getIntent();
        if (intentFromBrowser != null) {
            Uri uri = intentFromBrowser.getData();
            if (uri != null && uri.toString().startsWith(CALLBACK_URL)) {
                code = uri.getQueryParameter("code");

                getTravisToken(code, new IAPICallBack<TravisTokens>() {
                    @Override
                    public void onSuccess(@NonNull TravisTokens travisTokens) {
                        public_travis_token = travisTokens.getPublicToken();
                        private_travis_token = travisTokens.getPrivateToken();
                        SharedPreferences.Editor editor = getSharedPreferences(PREFS_NAME, MODE_PRIVATE).edit();
                        editor.putString("code", code);
                        editor.putString("public_travis_token", public_travis_token);
                        editor.putString("private_travis_token", private_travis_token);
                        editor.commit();
                        Intent intentToMainActivity = new Intent(BrowserActivity.this, MainActivity.class);
                        startActivity(intentToMainActivity);
                    }

                    @Override
                    public void onError(@NonNull String throwable) {

                    }
                });
            }
        }
    }
}
