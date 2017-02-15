package com.example.rdhol.mineseeker;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import static android.R.attr.data;
import static com.example.rdhol.mineseeker.Options.BOARD_SIZE;
import static com.example.rdhol.mineseeker.Options.ERASE_TIMES_PLAYED;
import static com.example.rdhol.mineseeker.Options.GET_MINE_POSITION;
import static com.example.rdhol.mineseeker.Options.MINE_NUMBER;
import static com.example.rdhol.mineseeker.Options.SAVE_BOARD_SIZE;
import static com.example.rdhol.mineseeker.Options.SAVE_MINE_NUMBER;

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
                int getBoardSize = data.getIntExtra(BOARD_SIZE, -1);
                //TODO:save boardsize and other vars to shared prefs
                SharedPreferences prefs = this.getSharedPreferences("prefs", Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = prefs.edit();

                if (getBoardSize != -1)
                    Toast.makeText(getApplicationContext(), "you received board position " + getBoardSize, Toast.LENGTH_SHORT).show(); //just for testing to make sure we get right values

                int getTreasureNumber = data.getIntExtra(MINE_NUMBER, -1);
                if (getTreasureNumber != -1)
                    Toast.makeText(getApplicationContext(), "you received Mine position " + getTreasureNumber, Toast.LENGTH_SHORT).show();

                int getEraseTimesPlayed = data.getIntExtra(ERASE_TIMES_PLAYED, -1);
                if (getEraseTimesPlayed != -1)
                    Toast.makeText(getApplicationContext(), "erase plays values: " + getEraseTimesPlayed, Toast.LENGTH_SHORT).show();


                editor.commit();
                break;
            default:
                break;
        }

    }

}
