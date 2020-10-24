package com.example.foyh.testui.Notification;
import android.app.Notification;
import android.app.Service;
import android.content.Intent;
import android.os.Build;
import android.os.IBinder;
import android.widget.ImageView;

import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;


import com.example.foyh.testui.ortherThread.threadXl;

import org.json.JSONException;

import java.io.FileNotFoundException;


public class ExampleService extends Service {
    ImageView nm;
    private static final String KEY_TEXT_REPLY = "key_text_reply";
    @Override
    public void onCreate() {
        super.onCreate();
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
//        RemoteViews notificationLayout = new RemoteViews(getPackageName(), R.layout.smallnoti);
//
//        String input = intent.getStringExtra("inputExtra");
//        Intent notificationIntent = new Intent(this, MainActivity.class);
//        PendingIntent pendingIntent = PendingIntent.getActivity(this,
//                0, notificationIntent, 0);
//        Intent broad = new Intent(this,NotificationReceiver.class);
//        broad.putExtra("inputExtra",input);
//        PendingIntent actionInt=PendingIntent.getBroadcast(this,
//                0,broad,PendingIntent.FLAG_UPDATE_CURRENT);
//        Notification notification = new NotificationCompat.Builder(this, CHANNEL_ID)
//                .setContentTitle("Foyh")
//                .setContentText(input)
//                .setSmallIcon(R.drawable.ic_android)
//                .setContentIntent(pendingIntent)
//                .setBadgeIconType(NotificationCompat.BADGE_ICON_SMALL)
//                .addAction(R.drawable.ic_android,"btn1",actionInt)
//                .addAction(R.drawable.ic_android,"btn2",actionInt)
//                .addAction(R.drawable.ic_android,"btn1",actionInt)
//                .setAutoCancel( true )
//                .build();
//        String agg=intent.getStringExtra("h");
//        if (agg.equals(1)){
//            notification= new threadXl(this).nocatifi("haha");
//        }else {
//            notification= new threadXl(this).nocatifi("ha");
//        }
//        ArrayList<Integer> nt= new ArrayList<Integer>();
//        nt =((Notifi) this.getApplicationContext()).getNoti();
//        int k=nt.get(0);
        Notification notification= null;
        try {
            notification = new threadXl(this).nocatifi();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }
        startForeground(1, notification);
        return START_NOT_STICKY;
    }
    @Override
    public void onDestroy() {
        super.onDestroy();
    }
    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }
}