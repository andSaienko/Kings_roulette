package com.example.kingsroulette;

import android.content.Intent;
import android.os.Bundle;
import android.webkit.WebView;
import android.widget.Button;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class GameShopActivity extends AppCompatActivity {

    Button one, two, three;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_shop);

        one = findViewById(R.id.bt10);
//        one.setOnClickListener(v -> );
    }

    void openPayment() {
        Intent intent = new Intent(this, MainActivity.class);

    }
}