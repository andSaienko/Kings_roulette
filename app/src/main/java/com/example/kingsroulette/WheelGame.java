package com.example.kingsroulette;

import android.annotation.SuppressLint;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.DecelerateInterpolator;
import android.view.animation.RotateAnimation;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Random;

public class WheelGame extends AppCompatActivity implements Animation.AnimationListener {
    private static final String[] Sector = {"0", "-5%", "-75%", "+300%", "0", "+20%", "-10%", "-100%", "-100%", "+10%"};
    TextView tvResult;
    float degrees;
    Button btRoll;
    ImageView ivRoulette;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        getWindow().addFlags(1024);
        requestWindowFeature(1);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        btRoll = (Button) findViewById(R.id.buttonRoll);

        ivRoulette = (ImageView) findViewById(R.id.rouletteImage);

        tvResult = (TextView) findViewById(R.id.resultView);

        rotate(getSavedDegrees());
    }

    @Override
    public void onAnimationStart(Animation animation) {
        btRoll.setClickable(false);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onAnimationEnd(Animation animation) {
        tvResult.setText("U WON: " + getCurrentSector());
        btRoll.setClickable(true);
        btRoll.setVisibility(View.VISIBLE);
    }

    @Override
    public void onAnimationRepeat(Animation animation) {

    }

    private String getCurrentSector() {
        return Sector[(int) (this.degrees / 36)];
    }

    public void onClickButtonRotation(View view) {
        int randomDegrees = new Random().nextInt(360) + 3600;
        rotate(randomDegrees);
    }

    public void rotate(float randomDegrees) {
        RotateAnimation rotateAnimation = new RotateAnimation(this.degrees,
                this.degrees + randomDegrees,
                1, 0.5f, 1, 0.5f);
        this.degrees = (this.degrees + randomDegrees) % 360;
        rotateAnimation.setDuration(2000);
        rotateAnimation.setFillAfter(true);
        rotateAnimation.setInterpolator(new DecelerateInterpolator());
        rotateAnimation.setAnimationListener(this);
        ivRoulette.setAnimation(rotateAnimation);
        ivRoulette.startAnimation(rotateAnimation);
    }

    public void saveDegrees(float degrees) {
        SharedPreferences sp = getSharedPreferences("SP_PREFS", MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();
        editor.putFloat("SAVED_DEGREES", degrees);
        editor.apply();
    }

    public Float getSavedDegrees() {
        SharedPreferences sp = getSharedPreferences("SP_PREFS", MODE_PRIVATE);
        return sp.getFloat("SAVED_DEGREES", degrees);
    }

    @Override
    protected void onStop() {
        saveDegrees(degrees);
        super.onStop();
    }
}
