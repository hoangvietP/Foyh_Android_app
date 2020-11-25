package com.example.foyh.testui.Notification;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.IBinder;
import android.util.Log;
import android.widget.ImageView;
import android.widget.RemoteViews;

import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.core.app.NotificationCompat;


import com.example.foyh.R;
import com.example.foyh.activity.MainActivity;
import com.example.foyh.testui.Data.classDT.fileDAO;
import com.example.foyh.testui.Notification.NotiReceiver.NotificationReceiver;
import com.example.foyh.testui.Notification.NotiReceiver.rc1;
import com.example.foyh.testui.Notification.NotiReceiver.rc2;
import com.example.foyh.testui.Notification.NotiReceiver.rc3;
import com.example.foyh.testui.Notification.NotiReceiver.rc4;
import com.example.foyh.testui.Notification.NotiReceiver.rc5;
import com.example.foyh.testui.Notification.NotiReceiver.rc6;
import com.example.foyh.testui.ortherThread.threadXl;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.FileNotFoundException;

import static com.example.foyh.testui.Notification.Notifi.CHANNEL_ID;


public class ExampleService extends Service {
    @Override
    public void onCreate() {
        super.onCreate();
    }
    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Notification notification= null;
        try {
            notification = nocatifi();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }
        startForeground(1, notification);
        return START_STICKY;
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

    public Notification nocatifi() throws FileNotFoundException, JSONException {
        fileDAO file = new fileDAO();
        JSONObject ob=  file.getData("stt.json","data",this);
        JSONArray stt = ob.getJSONArray("stt");
        JSONArray sttv = ob.getJSONArray("sttv");
        String conten = ob.getString("st");


        RemoteViews collapsedView = new RemoteViews(this.getPackageName(),
                R.layout.notification_collapsed);
        RemoteViews expandedView = new RemoteViews(this.getPackageName(),
                R.layout.notification_expanded);

        Intent clickIntent = new Intent(this, NotificationReceiver.class);
        PendingIntent clickPendingIntent = PendingIntent.getBroadcast(this,
                0, clickIntent, 0);

        Intent clickIntent1 = new Intent(this, rc1.class);
        PendingIntent clickPendingIntent1 = PendingIntent.getBroadcast(this,
                0, clickIntent1, 0);

        Intent clickIntent2 = new Intent(this, rc2.class);
        PendingIntent clickPendingIntent2 = PendingIntent.getBroadcast(this,
                0, clickIntent2, 0);

        Intent clickIntent3 = new Intent(this, rc3.class);
        PendingIntent clickPendingIntent3 = PendingIntent.getBroadcast(this,
                0, clickIntent3, 0);

        Intent clickIntent4 = new Intent(this, rc4.class);
        PendingIntent clickPendingIntent4 = PendingIntent.getBroadcast(this,
                0, clickIntent4, 0);

        Intent clickIntent5 = new Intent(this, rc5.class);
        PendingIntent clickPendingIntent5 = PendingIntent.getBroadcast(this,
                0, clickIntent5, 0);

        Intent clickIntent6 = new Intent(this, rc6.class);
        PendingIntent clickPendingIntent6 = PendingIntent.getBroadcast(this,
                0, clickIntent6, 0);

        //set Notification
        for (int i=0;i<= stt.length()-1;i++){
            boolean g=false;
            int vl = stt.getInt(i);
            for (int j=0; j<= sttv.length()-1;j++){
                if (vl==sttv.getInt(j)){
                    g=true;
                    continue;
                }
            }
            int idIm=0;
            int idV=0;
            String nameIcon="";
            if (g==true){
                switch (vl){
                    case 0 : idIm=R.drawable.iconclick6;
                        break;
                    case 1 :idIm=R.drawable.iconclick1;
                        break;
                    case 2 : idIm=R.drawable.iconclick10;
                        break;
                    case 3 : idIm=R.drawable.iconclick11;
                        break;
                    case 4 : idIm=R.drawable.iconclick2;
                        break;
                    case 5 : idIm=R.drawable.iconclick3;
                        break;
                    case 6 : idIm=R.drawable.iconclick4;
                        break;
                    case 7 : idIm=R.drawable.ca;
                        break;
                    case 12 : idIm=R.drawable.iconclick5;
                        break;
                    case 13 : idIm=R.drawable.iconclick5;
                        break;

                }
            }else if (g==false){
                switch (vl){
                    case 0 : idIm=R.drawable.icnotification1;
                    nameIcon="Nổi mụn";
                        break;
                    case 1 :idIm=R.drawable.icnotification2;
                        nameIcon="Mệt mỏi";
                        break;
                    case 2 : idIm=R.drawable.icnotification8;
                        nameIcon="Đau, tức ngưc";
                        break;
                    case 3 : idIm=R.drawable.icnotification4;
                        nameIcon="Chán ăn";
                        break;
                    case 4 : idIm=R.drawable.icnotification5;
                        nameIcon="Mệt mỏi";
                        break;
                    case 5 : idIm=R.drawable.icnotification6;
                        nameIcon="Đau lưng";
                        break;
                    case 6 : idIm=R.drawable.icnotification7;
                        nameIcon="Buồn";
                        break;
                    case 7 : idIm=R.drawable.icnotification9;
                        break;
                    case 12 : idIm=R.drawable.icnotification3;
                        nameIcon="Mùa dâu";
                        break;
                    case 13 : idIm=R.drawable.icnotification3;
                        break;
                }
            }
            switch (i){
                case 0 : idV=R.id.image_view_expanded;
                    expandedView.setTextViewText(R.id.tv00,nameIcon);
                    break;
                case 1 :idV=R.id.image_view_expanded1;
                    expandedView.setTextViewText(R.id.tv01,nameIcon);
                    break;
                case 2 : idV=R.id.image_view_expanded2;
                    expandedView.setTextViewText(R.id.tv02,nameIcon);
                    break;
                case 3 : idV=R.id.image_view_expanded3;
                    expandedView.setTextViewText(R.id.tv03,nameIcon);
                    break;
                case 4 : idV=R.id.image_view_expanded4;
                    expandedView.setTextViewText(R.id.tv04,nameIcon);
                    break;
                case 5 : idV=R.id.image_view_expanded5;
                    expandedView.setTextViewText(R.id.tv05,nameIcon);
                    break;
                case 6 : idV=R.id.image_view_expanded6;
                    expandedView.setTextViewText(R.id.tv06,nameIcon);
                    break;
            }
            expandedView.setImageViewResource(idV,idIm);
        }
        collapsedView.setTextViewText(R.id.text_view_collapsed_1,conten);
        expandedView.setTextViewText(R.id.text_view_expanded,conten);


        expandedView.setOnClickPendingIntent(R.id.image_view_expanded, clickPendingIntent);
        expandedView.setOnClickPendingIntent(R.id.image_view_expanded1, clickPendingIntent1);
        expandedView.setOnClickPendingIntent(R.id.image_view_expanded2, clickPendingIntent2);
        expandedView.setOnClickPendingIntent(R.id.image_view_expanded3, clickPendingIntent3);
        expandedView.setOnClickPendingIntent(R.id.image_view_expanded4, clickPendingIntent4);
        expandedView.setOnClickPendingIntent(R.id.image_view_expanded5, clickPendingIntent5);
        expandedView.setOnClickPendingIntent(R.id.image_view_expanded6, clickPendingIntent6);



        ///isdf

        Intent mainIntent = new Intent(this, MainActivity.class);
        PendingIntent contentIntent = PendingIntent.getActivity(this, 0, mainIntent, 0);

        // NotificationManager
        NotificationManager notificationManager =
                (NotificationManager) this.getSystemService(Context.NOTIFICATION_SERVICE);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            // For API 26 and above
            CharSequence channelName = "My Notification";
            int importance = NotificationManager.IMPORTANCE_DEFAULT;
            NotificationChannel channel = new NotificationChannel(CHANNEL_ID, channelName, importance);
            notificationManager.createNotificationChannel(channel);
        }

        // Prepare Notification
        NotificationCompat.Builder builder = new NotificationCompat.Builder(this, CHANNEL_ID)
                .setSmallIcon(R.drawable.iconlogo)
                .setCustomContentView(collapsedView)
                .setCustomBigContentView(expandedView)
                .setPriority(NotificationCompat.PRIORITY_DEFAULT)
                .setAutoCancel(true);

        return builder.build();

    }

}