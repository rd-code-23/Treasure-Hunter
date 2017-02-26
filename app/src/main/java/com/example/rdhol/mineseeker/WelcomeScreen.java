package com.example.rdhol.mineseeker;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;

import static com.example.rdhol.mineseeker.R.drawable.helm;

public class WelcomeScreen extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
      //  requestWindowFeature(Window.FEATURE_NO_TITLE);
      //  getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_welcome_screen);

        helmAnim();
        shipAnim();
        setupMainMenuButton();
    }
    private void setupMainMenuButton() {
        Button btn = (Button) findViewById(R.id.btn_MainMenu);
        btn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = MainMenu.makeIntent(WelcomeScreen.this);
                startActivity(intent);
                finish();
            }
        });
    }

    private void helmAnim(){
         ImageView helm = (ImageView) findViewById(R.id.image_Helm);
         Animation animation = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.spin);
        helm.startAnimation(animation);
    }

    private void shipAnim(){
        ImageView img = (ImageView) findViewById(R.id.img_Ship);
        Animation animation = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.move);
        img.startAnimation(animation);

    }

}