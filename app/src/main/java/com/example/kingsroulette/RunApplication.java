package com.example.kingsroulette;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

import com.google.firebase.FirebaseApp;
import com.google.firebase.remoteconfig.FirebaseRemoteConfig;
import com.google.firebase.remoteconfig.FirebaseRemoteConfigSettings;

public class RunApplication extends Application {
    private FirebaseRemoteConfig mFirebaseRemoteConfig;
    final String TAG = "";
    public static String importedUrl;
    public static Boolean importedPass;
    public static Boolean isLoaded = false;

    @Override
    public void onCreate() {
        super.onCreate();

        FirebaseApp.initializeApp(this);

        mFirebaseRemoteConfig = FirebaseRemoteConfig.getInstance();

        mFirebaseRemoteConfig.setDefaultsAsync(R.xml.remote_config_defaults);
        mFirebaseRemoteConfig.setConfigSettingsAsync(new FirebaseRemoteConfigSettings.Builder().setMinimumFetchIntervalInSeconds(1666).build());
        mFirebaseRemoteConfig.fetchAndActivate().addOnCompleteListener(task -> {
            Log.i(TAG, "taskComplete " + task.isComplete());
            if (!mFirebaseRemoteConfig.getString("web_link").equals("")) {
                importedUrl = mFirebaseRemoteConfig.getString("web_link");
                saveUrl(getApplicationContext(), "SAVE_URL", importedUrl);
                Log.i(TAG, "mydata " + importedUrl);
            }
            if (!mFirebaseRemoteConfig.getString("game_pass").equals("")) {
                importedPass = mFirebaseRemoteConfig.getBoolean("game_pass");
                Log.i(TAG, "mydata " + importedPass);
                savePass(getApplicationContext(), "SAVE_PASS", importedPass);
            }
            isLoaded = true;
        });
    }

    public static void saveUrl(Context context, String path, String link) {
        SharedPreferences sp = context.getSharedPreferences("SP_PREFS", MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();
        editor.putString("SAVE_URL", link);
        editor.apply();
    }

    public static String getUrl(Context context, String path) {
        SharedPreferences sp = context.getSharedPreferences("SP_PREFS", MODE_PRIVATE);
        return sp.getString(path, "");
    }

    public static void savePass(Context context, String path, Boolean pass) {
        SharedPreferences sp = context.getSharedPreferences("SP_PREFS", MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();
        editor.putBoolean("SAVE_PASS", pass);
        editor.apply();
    }

    public static boolean getPass(Context context, String path) {
        SharedPreferences sp = context.getSharedPreferences("SP_PREFS", MODE_PRIVATE);
        return sp.getBoolean(path, true);
    }
}

