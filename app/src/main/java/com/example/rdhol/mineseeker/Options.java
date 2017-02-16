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
import android.widget.Spinner;

import java.security.InvalidParameterException;

public class Options extends AppCompatActivity {

    public static final String OPTIONS_PREFS_KEY = "optionsPrefs";

    //public static final String BOARD_SIZE_KEY = "boardSize";
    public static final String BOARD_SIZE_OPTION_KEY = "getBoardPosition";
    public static final int FOUR_BY_SIX = 0;
    public static final int FIVE_BY_TEN = 1;
    public static final int SIX_BY_FIFTEEN = 2;
    public static final int DEFAULT_BOARD_SIZE = Options.FOUR_BY_SIX;

    // public static final String MINE_NUMBER = "mineNumber";
    public static final String MINE_NUM_KEY = "getMinePosition";
    public static final int SIX_MINES = 0;
    public static final int TEN_MINES = 1;
    public static final int FIFTEEN_MINES = 2;
    public static final int TWENTY_MINES = 3;
    public static final String ERASE_TIMES_PLAYED = "erasePlays";
    public static final int RESET_PLAYS = 1;
    public static int DEFAULT_MINE_NUM = Options.SIX_MINES;

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
        SharedPreferences sharedPref = getSharedPreferences(OPTIONS_PREFS_KEY, Context.MODE_PRIVATE);

        int boardSpinVal = sharedPref.getInt(BOARD_SIZE_OPTION_KEY, DEFAULT_BOARD_SIZE);
        boardSizeSpin.setSelection(boardSpinVal);
        // sharedPref = getSharedPreferences(OPTIONS_PREFS_KEY, Context.MODE_PRIVATE);
        int mineSpinVal = sharedPref.getInt(MINE_NUM_KEY, Options.DEFAULT_MINE_NUM);
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
        ArrayAdapter<String> myAdapter = new ArrayAdapter<>(Options.this,
                android.R.layout.simple_list_item_1,
                getResources().getStringArray(R.array.Spin_Board_sizes));
        myAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        boardSizeSpin.setAdapter(myAdapter);
        boardSizeSpin.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                save(OPTIONS_PREFS_KEY, BOARD_SIZE_OPTION_KEY, position);
                Intent returnIntent = new Intent();
                switch (position) {
                    case FOUR_BY_SIX:
                        returnIntent.putExtra(BOARD_SIZE_OPTION_KEY, FOUR_BY_SIX);
                        setResult(Activity.RESULT_OK, returnIntent);
                        break;
                    case FIVE_BY_TEN:
                        returnIntent.putExtra(BOARD_SIZE_OPTION_KEY, FIVE_BY_TEN);
                        setResult(Activity.RESULT_OK, returnIntent);
                        break;
                    case SIX_BY_FIFTEEN:
                        returnIntent.putExtra(BOARD_SIZE_OPTION_KEY, SIX_BY_FIFTEEN);
                        setResult(Activity.RESULT_OK, returnIntent);
                        break;
                    default:
                        throw new InvalidParameterException();
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });
    }

    private void setupMineNumberSpin() {
        mineNumSpin = (Spinner) findViewById(R.id.spin_MineNum);
        ArrayAdapter<String> myAdapter = new ArrayAdapter<>(Options.this,
                android.R.layout.simple_list_item_1,
                getResources().getStringArray(R.array.Spin_Mine_Num));
        myAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        mineNumSpin.setAdapter(myAdapter);
        mineNumSpin.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                save(OPTIONS_PREFS_KEY, MINE_NUM_KEY, position);
                Intent returnIntent = new Intent();
                switch (position) {
                    case SIX_MINES:
                        returnIntent.putExtra(MINE_NUM_KEY, SIX_MINES);
                        setResult(Activity.RESULT_OK, returnIntent);
                        break;
                    case TEN_MINES:
                        returnIntent.putExtra(MINE_NUM_KEY, TEN_MINES);
                        setResult(Activity.RESULT_OK, returnIntent);
                        break;
                    case FIFTEEN_MINES:
                        returnIntent.putExtra(MINE_NUM_KEY, FIFTEEN_MINES);
                        setResult(Activity.RESULT_OK, returnIntent);
                        break;
                    case TWENTY_MINES:
                        returnIntent.putExtra(MINE_NUM_KEY, TWENTY_MINES);
                        setResult(Activity.RESULT_OK, returnIntent);
                        break;
                    default:
                        throw new InvalidParameterException();
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });
    }

    private void save(String prefName, String key, int value) {
        SharedPreferences sharedPref = getSharedPreferences(prefName, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPref.edit();
        editor.clear();
        editor.putInt(key, value);
        editor.commit();
    }
}
