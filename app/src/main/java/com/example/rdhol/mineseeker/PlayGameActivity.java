package com.example.rdhol.mineseeker;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import java.security.InvalidParameterException;
import java.util.Random;

public class PlayGameActivity extends AppCompatActivity {

    int numOfCols;
    int numOfRows;
    private GameCell[][] gamecells;
    private int numOfTreasures;
    private int numOfScansUsed;
    private int numOfTreasuresFound;

    public static Intent makeIntent(Context context) {
        return new Intent(context, PlayGameActivity.class);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_play_game);

        setupGameCells();
    }

    private void setupGameCells() {

        //TODO: GET THE USERS SELECTED BOARD SIZE from options menu, treasure count
        //TODO: MOVE code into a separate class, this method is way to large
        //TODO: HANDLE saving  on exit

        numOfRows = 7;
        numOfCols = 5;

        gamecells = new GameCell[numOfRows][numOfCols];
        TableLayout cells = (TableLayout) findViewById(R.id.tableForGameCells);
        for (int row = 0; row < numOfRows; row++) {
            TableRow tableRow = new TableRow(this);
            tableRow.setLayoutParams(new TableLayout.LayoutParams(
                    TableLayout.LayoutParams.MATCH_PARENT,
                    TableLayout.LayoutParams.MATCH_PARENT,
                    1.0f));

            cells.addView(tableRow);
            for (int col = 0; col < numOfCols; col++) {
                Button button = new Button(this);
                button.setLayoutParams(new TableRow.LayoutParams(
                        TableRow.LayoutParams.MATCH_PARENT,
                        TableRow.LayoutParams.MATCH_PARENT,
                        1.0f));

                //no padding to reduce text clipping
                button.setPadding(0, 0, 0, 0);
                final int finalRow = row;
                final int finalCol = col;
                button.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        gameCellClicked(finalCol, finalRow);
                    }
                });
                gamecells[row][col] = new GameCell(button);
                tableRow.addView(button);
            }
        }
        //randomly place treasure
        numOfTreasures = 5;
        if (numOfTreasures > (numOfRows * numOfCols)) {
            throw new InvalidParameterException();
        }
        Random rand = new Random();
        for (int i = 0; i < numOfTreasures; i++) {
            int rand1 = rand.nextInt(numOfRows);
            int rand2 = rand.nextInt(numOfCols);
            //if we try to add a treasure to a cell that is full, decrement to ensure correctness
            if (!gamecells[rand1][rand2].tryGiveTreasure()) {
                i--;
            }
        }

        numOfTreasuresFound = 0;
        numOfScansUsed = 0;
        updateUI();
    }

    private void gameCellClicked(int col, int row) {
        lockButtonSizes();
        final GameCell gameCell = gamecells[row][col];
        boolean treasureFound = gameCell.scanForTreasure(this);
        if (treasureFound) {
            numOfTreasuresFound++;
            //Toast.makeText(this, "Treasure Found", Toast.LENGTH_SHORT).show();
        } else {
            //TODO: CHANGE SCAN NUMBER TO CHANGE AUTOMATICALLY
            //TODO: Dont increment scanCount if the gameCell has a scan number on it
            scanForTreasure(col, row);
            numOfScansUsed++;
            // Toast.makeText(this, "Scanning", Toast.LENGTH_SHORT).show();
        }
        updateUI();
    }

    private void updateUI() {
        TextView txtNumOfTreasuresFound = (TextView) findViewById(R.id.txtNumOfTreasuresFound);
        txtNumOfTreasuresFound.setText(numOfTreasuresFound + " of " + numOfTreasures + " treasures found");
        TextView txtNumOfScansUsed = (TextView) findViewById(R.id.txtNumOfScansUsed);
        txtNumOfScansUsed.setText(numOfScansUsed + " Scans used");

        //TODO: move gameCell array to a gameBoard class
        //go through the gamecells and update UI of scan points


    }

    private void scanForTreasure(int col, int row) {
        int numOfTreasureFoundInScan = 0;
        for (int i = 0; i < row; i++) {
            if (gamecells[i][col].hasHiddenTreasure()) {
                numOfTreasureFoundInScan++;
            }
        }
        for (int i = row; i < numOfRows; i++) {
            if (gamecells[i][col].hasHiddenTreasure()) {
                numOfTreasureFoundInScan++;
            }
        }

        for (int i = 0; i < col; i++) {
            if (gamecells[row][i].hasHiddenTreasure()) {
                numOfTreasureFoundInScan++;
            }
        }
        for (int i = row; i < numOfCols; i++) {
            if (gamecells[row][i].hasHiddenTreasure()) {
                numOfTreasureFoundInScan++;
            }
        }
        gamecells[row][col].displayText("" + numOfTreasureFoundInScan);
        // Toast.makeText(this, numOfTreasureFoundInScan + "", Toast.LENGTH_SHORT).show();

    }

    private void lockButtonSizes() {
        //lock button sizes
        for (int row = 0; row < numOfRows; row++) {
            for (int col = 0; col < numOfCols; col++) {
                gamecells[row][col].lockButtonSize();
            }
        }
    }
}


