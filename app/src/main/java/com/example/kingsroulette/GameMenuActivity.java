package com.example.kingsroulette;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class GameMenuActivity extends AppCompatActivity {

    Button btPlay, btShop;
    Intent intent;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_menu);

        btPlay = findViewById(R.id.btPlay);
        btShop = findViewById(R.id.btShop);

        btPlay.setOnClickListener(view -> openGame());
        btShop.setOnClickListener(view -> openShop());

    }

    void openGame() {
        intent = new Intent(this, WheelGame.class);
        startActivity(intent);
    }

    void openShop() {
        intent = new Intent(this, GameShopActivity.class);
        startActivity(intent);
    }

    public void onBackPressed() {

        new AlertDialog.Builder(this)
                .setMessage("Are you sure you want to exit?")
                .setCancelable(false)
                .setPositiveButton("yes", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        GameMenuActivity.this.finish();
                    }
                })
                .setNegativeButton("no", null)
                .show();
    }
}
