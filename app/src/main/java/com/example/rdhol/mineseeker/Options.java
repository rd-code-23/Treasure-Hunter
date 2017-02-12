package com.example.rdhol.mineseeker;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import static android.R.attr.data;
import static android.R.attr.paddingStart;
import static android.R.attr.value;

public class Options extends AppCompatActivity {

    public static final String BOARD_SIZE = "boardSize";
    public static final String SAVE_BOARD_SIZE = "saveBoardSize";
    public static final String GET_BOARD_POSITION = "getboardPosition";
    public static final int FOUR_BY_SIX = 0;
    public static final int FIVE_BY_TEN = 1;
    public static final int SIX_BY_FIFTEEN = 2;

    public static final String MINE_NUMBER = "mineNumber";
    public static final String SAVE_MINE_NUMBER = "saveMineNumber";
    public static final String GET_MINE_POSITION = "getMinePosition";
    public static final int SIX_MINES = 0;
    public static final int TEN_MINES = 1;
    public static final int FIFTEEN_MINES = 2;
    public static final int TWENTY_MINES = 3;

    public static final String ERASE_TIMES_PLAYED = "erasePlays";
    public static final int RESET_PLAYS = 1;

    Spinner mineNumSpin;
    Spinner boardSizeSpin;

    public static Intent makeIntent(Context context) {
        return new Intent(context, Options.class);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_options);

        setupBoardSizeSpin();
        setupMineNumberSpin();
        setupEraseTimesPlayedButton();
        loadSpinners();
    }

    private void loadSpinners() {
        SharedPreferences sharedPref = getSharedPreferences(SAVE_BOARD_SIZE, Context.MODE_PRIVATE);
        int boardSpinVal = sharedPref.getInt(GET_BOARD_POSITION, -1);
        if (boardSpinVal != -1)
            boardSizeSpin.setSelection(boardSpinVal);

        sharedPref = getSharedPreferences(SAVE_MINE_NUMBER, Context.MODE_PRIVATE);
        int mineSpinVal = sharedPref.getInt(GET_MINE_POSITION, -1);
        if (mineSpinVal != -1)
            mineNumSpin.setSelection(mineSpinVal);
    }

    private void setupEraseTimesPlayedButton() {
        Button btn = (Button) findViewById(R.id.btn_EraseTimesPlayed);
        btn.setOnClickListener(new View.OnClickListener() {
            Intent returnIntent = new Intent();
            @Override
            public void onClick(View v) {
                returnIntent.putExtra(ERASE_TIMES_PLAYED, RESET_PLAYS);
                setResult(Activity.RESULT_OK, returnIntent);
            }
        });

    }

    private void setupBoardSizeSpin() {

        boardSizeSpin = (Spinner) findViewById(R.id.spin_BoardSize);

        ArrayAdapter<String> myAdapter = new ArrayAdapter<String>(Options.this,
                android.R.layout.simple_list_item_1, getResources().getStringArray(R.array.Spin_Board_sizes));

        myAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        boardSizeSpin.setAdapter(myAdapter);

        boardSizeSpin.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                save(SAVE_BOARD_SIZE, GET_BOARD_POSITION, position);

                Intent returnIntent = new Intent();

                if (position == FOUR_BY_SIX) {
                    returnIntent.putExtra(BOARD_SIZE, FOUR_BY_SIX);
                    setResult(Activity.RESULT_OK, returnIntent);
                }

                if (position == FIVE_BY_TEN) {
                    returnIntent.putExtra(BOARD_SIZE, FIVE_BY_TEN);
                    setResult(Activity.RESULT_OK, returnIntent);
                }

                if (position == SIX_BY_FIFTEEN) {
                    returnIntent.putExtra(BOARD_SIZE, SIX_BY_FIFTEEN);
                    setResult(Activity.RESULT_OK, returnIntent);
                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    private void setupMineNumberSpin() {

        mineNumSpin = (Spinner) findViewById(R.id.spin_MineNum);

        ArrayAdapter<String> myAdapter = new ArrayAdapter<String>(Options.this,
                android.R.layout.simple_list_item_1, getResources().getStringArray(R.array.Spin_Mine_Num));

        myAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        mineNumSpin.setAdapter(myAdapter);

        mineNumSpin.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                save(SAVE_MINE_NUMBER, GET_MINE_POSITION, position);

                Intent returnIntent = new Intent();

                if (position == SIX_MINES) {
                    returnIntent.putExtra(MINE_NUMBER, SIX_MINES);
                    setResult(Activity.RESULT_OK, returnIntent);
                }

                if (position == TEN_MINES) {
                    returnIntent.putExtra(MINE_NUMBER, TEN_MINES);
                    setResult(Activity.RESULT_OK, returnIntent);
                }

                if (position == FIFTEEN_MINES) {
                    returnIntent.putExtra(MINE_NUMBER, FIFTEEN_MINES);
                    setResult(Activity.RESULT_OK, returnIntent);
                }

                if (position == TWENTY_MINES) {
                    returnIntent.putExtra(MINE_NUMBER, TWENTY_MINES);
                    setResult(Activity.RESULT_OK, returnIntent);
                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    private void save(String file, String subFile, int position) {
        SharedPreferences sharedPref = getSharedPreferences(file, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPref.edit();

        editor.putInt(subFile, position);
        editor.apply();
    }


}
