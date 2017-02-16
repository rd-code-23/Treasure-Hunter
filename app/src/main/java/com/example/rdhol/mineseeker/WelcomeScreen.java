package com.example.rdhol.mineseeker;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;

public class WelcomeScreen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome_screen);

        setupDiamondLeftAnim();
        setupDiamondRightAnim();
        setupMainMenuButton();
    }

    private void setupMainMenuButton() {
        Button btn = (Button) findViewById(R.id.btn_MainMenu);
        btn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = MainMenu.makeIntent(WelcomeScreen.this);
                startActivity(intent);
            }
        });
    }

    private void setupDiamondLeftAnim() {
        ImageView diamond = (ImageView) findViewById(R.id.image_DiamondLeft);
        Animation animation = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.fade_out);
        diamond.startAnimation(animation);
    }

    private void setupDiamondRightAnim() {
        ImageView diamond = (ImageView) findViewById(R.id.image_DiamondRight);
        Animation animation = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.fade_out);
        diamond.startAnimation(animation);
    }
}
