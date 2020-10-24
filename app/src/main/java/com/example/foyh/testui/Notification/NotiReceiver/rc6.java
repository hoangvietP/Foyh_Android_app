package com.example.foyh.testui.Notification.NotiReceiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Build;

import androidx.annotation.RequiresApi;
import androidx.core.content.ContextCompat;

import com.example.foyh.testui.Data.service.DataServiceMethod;
import com.example.foyh.testui.Notification.ExampleService;

import org.json.JSONException;

import java.io.FileNotFoundException;

public class rc6  extends BroadcastReceiver {
    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Override
    public void onReceive(Context context, Intent intent) {

        serviceNotiReceiver sv = new serviceNotiReceiver();
        try {
            boolean bl = sv.getData(6,context);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }


        try {
            new DataServiceMethod().testData(context,0);
        } catch (JSONException e) {
            e.printStackTrace();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        Intent serviceIntent= new Intent(context, ExampleService.class);
        ContextCompat.startForegroundService(context, serviceIntent);

    }

}