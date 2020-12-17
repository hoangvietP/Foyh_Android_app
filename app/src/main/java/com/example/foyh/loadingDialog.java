package com.example.foyh;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.view.LayoutInflater;

public class loadingDialog {
    Activity activity;
    AlertDialog dialog;
    public loadingDialog(Activity activity){
        this.activity = activity;
    }

    void start(){
        AlertDialog.Builder builder = new AlertDialog.Builder(activity);
        LayoutInflater inflater = activity.getLayoutInflater();
        builder.setView(inflater.inflate(R.layout.loading_dialog,null));
        builder.setCancelable(true);
        dialog= builder.create();
        dialog.show();
    }
    void dimiss(){
        dialog.dismiss();
    }
}
