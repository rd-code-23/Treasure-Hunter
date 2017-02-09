package com.example.rdhol.mineseeker;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class WelcomeScreen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome_screen);

        setupMainMenuButton();
    }


    private void setupMainMenuButton(){
        Button btn = (Button) findViewById(R.id.btn_MainMenu);
        btn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(WelcomeScreen.this, MainMenu.class);
                startActivity(intent);

            }
        });
    }



}
