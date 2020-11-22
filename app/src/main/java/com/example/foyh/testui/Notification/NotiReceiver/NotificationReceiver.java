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


public class NotificationReceiver extends BroadcastReceiver {
    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Override
    public void onReceive(Context context, Intent intent) {
//        ArrayList<Integer> nt= new ArrayList<Integer>();
//        nt =((Notifi) context.getApplicationContext()).getNoti();
//        int k=nt.get(0);
//        if (k==0){k=1;}else {k=0;}
//        nt.set(0,k);
//        ((Notifi) context.getApplicationContext()).setNoti(nt);
//        new threadXl(context).realtimeSt(nt);


        serviceNotiReceiver sv = new serviceNotiReceiver();
        try {
            boolean bl = sv.getData(0,context);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }
        Intent serviceIntent= new Intent(context, ExampleService.class);
        ContextCompat.startForegroundService(context, serviceIntent);

    }

}
