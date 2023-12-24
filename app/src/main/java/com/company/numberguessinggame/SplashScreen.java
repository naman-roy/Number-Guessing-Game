package com.company.numberguessinggame;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

public class SplashScreen extends AppCompatActivity {

    private TextView textView;
    private ImageView imageView;

    Animation animationImage, animationText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

        imageView=findViewById(R.id.imageView);
        textView=findViewById(R.id.textView);

        animationImage= AnimationUtils.loadAnimation(this,R.anim.image_animation);
        animationText= AnimationUtils.loadAnimation(this,R.anim.text_animation);

        imageView.setAnimation(animationImage);
        textView.setAnimation(animationText);

        new CountDownTimer(7000, 1000) {
            @Override
            public void onTick(long l) {

            }

            @Override
            public void onFinish() {

                startActivity(new Intent(SplashScreen.this,MainActivity.class));
                finish();
            }
        }.start();
    }
}