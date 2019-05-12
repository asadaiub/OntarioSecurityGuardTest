package com.techplato.ontariosecurityguardtest;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

public class SplashActivity extends AppCompatActivity {
    ImageView splashIV;
    TextView splashTitleTV;
    Animation from_up, fade_in;
    SharedPreferences preferences;
    boolean isfirstTime;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        splashIV = findViewById(R.id.splashIV);
        splashTitleTV = findViewById(R.id.splashTitleTV);
        splashTitleTV.setVisibility(View.INVISIBLE);
        preferences = getSharedPreferences(Constants.PREFERANCE_NAME, MODE_PRIVATE);
        isfirstTime = preferences.getBoolean("isFirstTime", true);


        from_up = AnimationUtils.loadAnimation(this, R.anim.from_up);
        fade_in = AnimationUtils.loadAnimation(this, R.anim.fade_in);
        splashIV.setAnimation(from_up);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                splashTitleTV.setVisibility(View.VISIBLE);
                splashTitleTV.setAnimation(fade_in);
            }
        }, 830);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                goToHomepage();
            }
        }, 1900);
    }

    public void goToHomepage() {
        if (isfirstTime) {
            SharedPreferences.Editor editor = getSharedPreferences(Constants.PREFERANCE_NAME, MODE_PRIVATE).edit();
            editor.putBoolean("isFirstTime", false);
            editor.apply();
            Intent i = new Intent(SplashActivity.this, WizardActivity.class);
            startActivity(i);
            this.finish();
        } else {
            startActivity(new Intent(SplashActivity.this, AppActivity.class));
            this.finish();
        }

    }


}
