package com.example.rdhol.mineseeker;


import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.widget.Button;

public class GameCell {

    private boolean hasTreasure;
    private Button button;
    private boolean treasureRevealed;
    private boolean isScanPoint;

    GameCell(Button button) {
        this(button, false);
    }

    private GameCell(Button button, boolean hasTreasure) {
        this.button = button;
        this.hasTreasure = hasTreasure;
        this.treasureRevealed = false;
        this.isScanPoint= false;
    }

    public void lockButtonSize() {
        //lock button size
        final int btnWidth = button.getWidth();
        final int btnHeight = button.getHeight();
        button.setMinWidth(btnWidth);
        button.setMaxWidth(btnWidth);
        button.setMinHeight(btnHeight);
        button.setMaxHeight(btnHeight);
    }

    public boolean scanForTreasure(Context context) {
        //if cell has hidden treasure, show it then finish
        if (hasTreasure && !treasureRevealed) {
            displayTreasureImage(context);
            this.treasureRevealed = true;
            return true;
        } else {
            this.isScanPoint = true;
            return false;
        }
    }

    private void displayTreasureImage(Context context) {
        Resources resources = context.getResources();
        Bitmap origBitmap = BitmapFactory.decodeResource(resources,
                R.drawable.diamond256);
        Bitmap scaledBitmap = Bitmap.createScaledBitmap(origBitmap,
                button.getWidth(), button.getHeight(), true);
        button.setBackground(new BitmapDrawable(resources, scaledBitmap));
    }

    public void displayText(String test) {
        button.setText(test);
    }


    public boolean tryGiveTreasure() {
        if (!this.hasTreasure) {
            this.hasTreasure = true;
            return true;
        } else {
            return false;
        }
    }

    public boolean hasHiddenTreasure() {
        return (hasTreasure && !treasureRevealed);
    }
}
