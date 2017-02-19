package com.example.rdhol.mineseeker;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import static com.example.rdhol.mineseeker.Options.ERASE_TIMES_PLAYED;
import static com.example.rdhol.mineseeker.Options.MINE_NUM_KEY;

public class MainMenu extends AppCompatActivity {
    public static final int OPTIONS_REQUEST = 1;

    public static Intent makeIntent(Context context) {
        return new Intent(context, MainMenu.class);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_menu);

        setupOptionsButton();
        setupHelpButton();
        setupPlayGameButton();
    }

    private void setupPlayGameButton() {
        Button btn = (Button) findViewById(R.id.btn_PlayGame);
        btn.setBackgroundColor(Color.rgb(200, 200, 255));
        btn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = PlayGameActivity.makeIntent(MainMenu.this);
                startActivity(intent);
            }
        });
    }

    private void setupHelpButton() {
        Button btn = (Button) findViewById(R.id.btn_Help);
        btn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = HelpActivity.makeIntent(MainMenu.this);
                startActivity(intent);
            }
        });
    }

    private void setupOptionsButton() {
        Button btn = (Button) findViewById(R.id.btn_Options);
        btn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = Options.makeIntent(MainMenu.this);
                startActivityForResult(intent, OPTIONS_REQUEST);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == Activity.RESULT_CANCELED) {
            return;
        }
        switch (requestCode) {
            case OPTIONS_REQUEST:
                break;
            default:
                break;
        }
    }
}