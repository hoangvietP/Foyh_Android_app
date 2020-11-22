package com.example.foyh.testui.Notification;

import android.app.Application;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.os.Build;

import java.util.ArrayList;

public class Notifi extends Application {
    public static final String CHANNEL_ID = "exampleService";
    @Override
    public void onCreate() {
        super.onCreate();
        createNotificationChannel();
    }
    private void createNotificationChannel() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            NotificationChannel serviceChannel = new NotificationChannel(
                    CHANNEL_ID,
                    "Example Service",
                    NotificationManager.IMPORTANCE_DEFAULT
            );
            NotificationManager manager = getSystemService(NotificationManager.class);
            manager.createNotificationChannel(serviceChannel);
        }
    }

    private ArrayList<Integer> Noti;// du lieu trang thai cua thanh thong bao

    public void setNoti(ArrayList<Integer> noti) {
        Noti = noti;
    }

    public ArrayList<Integer> getNoti() {
        return Noti;
    }
}
