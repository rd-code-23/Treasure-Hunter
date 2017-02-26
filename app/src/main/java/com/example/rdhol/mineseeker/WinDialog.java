package com.example.rdhol.mineseeker;

import android.app.Dialog;
import android.content.DialogInterface;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatDialogFragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;

import static android.app.AlertDialog.THEME_DEVICE_DEFAULT_LIGHT;
import static android.app.AlertDialog.THEME_HOLO_LIGHT;


public class WinDialog extends AppCompatDialogFragment {


    public Dialog onCreateDialog(Bundle savedInstanceState) {
        //create the view
        View v = LayoutInflater.from(getActivity()).inflate(R.layout.win_layout, null);

        //create button listener
        DialogInterface.OnClickListener listener = new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {

                switch (which) {
                    case DialogInterface.BUTTON_POSITIVE:
                        getActivity().finish();
                        break;
                }
            }
        };

        // build the alert dialog
        return new AlertDialog.Builder(getActivity()).setTitle("YOU WON")
                .setView(v)
                .setPositiveButton(android.R.string.ok, listener)
                .create();
    }
}
