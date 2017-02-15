package com.example.rdhol.mineseeker;

import android.content.Context;
import android.content.Intent;
import android.os.PersistableBundle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

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
        numOfTreasuresFound = 0;
        numOfScansUsed = 0;

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

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);

        //create save string here
        outState.putString("", "");
    }

    @Override
    public void onRestoreInstanceState(Bundle savedInstanceState, PersistableBundle persistentState) {
        super.onRestoreInstanceState(savedInstanceState, persistentState);

    }

    private void gameCellClicked(int col, int row) {
        lockButtonSizes();
        final GameCell gameCellClicked = gamecells[row][col];

        //scanForTreasure has a side effect of turning the gamecell
        // into a scanpoint if there is no treasure
        boolean isScanPoint = gameCellClicked.isScanPoint();
        boolean treasureFound = gameCellClicked.scanForTreasure(this);
        if (treasureFound) {
            numOfTreasuresFound++;
            Toast.makeText(this, "Treasure Found", Toast.LENGTH_SHORT).show();
        } else if (!isScanPoint) {
            numOfScansUsed++;
        } else {
            Toast.makeText(this, "Is a scanpoint no need to increment" +
                    numOfScansUsed, Toast.LENGTH_SHORT).show();

        }
        updateUI();
    }

    private void updateUI() {
        TextView txtNumOfTreasuresFound = (TextView) findViewById(R.id.txtNumOfTreasuresFound);
        txtNumOfTreasuresFound.setText(numOfTreasuresFound + " of " +
                numOfTreasures + " treasures found");
        TextView txtNumOfScansUsed = (TextView) findViewById(R.id.txtNumOfScansUsed);
        txtNumOfScansUsed.setText(numOfScansUsed + " Scans used");

        //TODO: move gameCell array to a gameBoard class
        //go through the gamecells and update UI of scan points
        for (int row = 0; row < gamecells.length; row++) {
            for (int col = 0; col < gamecells[row].length; col++) {
                if (gamecells[row][col].isScanPoint()) {
                    scanRowAndCol(col, row);
                }
            }
        }

    }

    private void scanRowAndCol(int col, int row) {
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


