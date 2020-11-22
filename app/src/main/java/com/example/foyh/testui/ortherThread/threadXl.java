package com.example.foyh.testui.ortherThread;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.CountDownTimer;
import android.util.Log;
import android.widget.ImageView;
import android.widget.RemoteViews;

import androidx.core.app.NotificationCompat;
import androidx.core.content.ContextCompat;


import com.example.foyh.R;
import com.example.foyh.activity.MainActivity;
import com.example.foyh.testui.Data.classDT.fileDAO;
import com.example.foyh.testui.Notification.ExampleService;
import com.example.foyh.testui.Notification.NotiReceiver.NotificationReceiver;
import com.example.foyh.testui.Notification.NotiReceiver.rc1;
import com.example.foyh.testui.Notification.NotiReceiver.rc2;
import com.example.foyh.testui.Notification.NotiReceiver.rc3;
import com.example.foyh.testui.Notification.NotiReceiver.rc4;
import com.example.foyh.testui.Notification.NotiReceiver.rc5;
import com.example.foyh.testui.Notification.NotiReceiver.rc6;
import com.example.foyh.testui.Notification.Notifi;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.FileNotFoundException;
import java.util.ArrayList;

import static com.example.foyh.testui.Notification.Notifi.CHANNEL_ID;


public class threadXl extends Thread {
    Context context;
    ImageView img;
    public threadXl(Context context){
        this.context=context;
    }

    Intent serviceIntent;
    int k1=3;

        public void realtimeSt(ArrayList<Integer> stt){
            ((Notifi) context.getApplicationContext()).setNoti(stt);
            serviceIntent= new Intent(context, ExampleService.class);
            new CountDownTimer(3000, 100) {
                public void onTick(long millisUntilFinished) {
                    ArrayList<Integer> nt = new ArrayList<Integer>();
                    nt = ((Notifi) context.getApplicationContext()).getNoti();
                    int k = nt.get(0);
                    if (k != k1) {
                        ContextCompat.startForegroundService(context, serviceIntent);
                    }
                    k1 = k;
                }
                public void onFinish() {
                    Thread.currentThread();
                }
            }.start();
        }

    Notification notification;


   }