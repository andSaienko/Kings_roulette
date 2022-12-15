package com.example.kingsroulette;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.widget.ProgressBar;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import com.google.firebase.remoteconfig.FirebaseRemoteConfig;

public class StartScreen extends AppCompatActivity {
    ProgressBar progressBar;
    static String TAG = "";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.start_screen);
        progressBar = findViewById(R.id.progressBar);

        new Handler().postDelayed(() -> {
            Intent intent;
            Log.i(TAG, "importedPass: " + RunApplication.getUrl(this, "SAVE_URL"));
            if(RunApplication.importedPass) {
                intent = new Intent(StartScreen.this, GameMenuActivity.class);
            } else {
                intent = new Intent(StartScreen.this, MainActivity.class);
            }
            startActivity(intent);
            finish();
        }, 4000);
    }
}
