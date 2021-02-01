package com.example.Foyh.testui.Notification;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.media.AudioAttributes;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Build;
import android.os.IBinder;
import android.widget.RemoteViews;

import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.core.app.NotificationCompat;


import com.example.Foyh.R;
import com.example.Foyh.activity.MainActivity;
import com.example.Foyh.testui.Data.classDT.DatabaseHandler;
import com.example.Foyh.testui.Data.classDT.Dudoan;
import com.example.Foyh.testui.Data.classDT.SttThisMonth;
import com.example.Foyh.testui.Notification.NotiReceiver.NotificationReceiver;
import com.example.Foyh.testui.Notification.NotiReceiver.rc1;
import com.example.Foyh.testui.Notification.NotiReceiver.rc2;
import com.example.Foyh.testui.Notification.NotiReceiver.rc3;
import com.example.Foyh.testui.Notification.NotiReceiver.rc4;
import com.example.Foyh.testui.Notification.NotiReceiver.rc5;
import com.example.Foyh.testui.Notification.NotiReceiver.rc6;
import com.example.Foyh.testui.Notification.NotiReceiver.rc7;
import com.example.Foyh.testui.Notification.NotiReceiver.rc8;
import com.example.Foyh.testui.Notification.NotiReceiver.rc9;
import com.example.Foyh.testui.ortherThread.SynsDay;

import org.json.JSONArray;
import org.json.JSONException;

import java.io.FileNotFoundException;

import static com.example.Foyh.testui.Notification.Notifi.CHANNEL_ID;


public class ExampleService extends Service {
    int idIm=0;
    int idV=0;
    @Override
    public void onCreate() {
        super.onCreate();
        new SynsDay(context).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR,"gh");
    }
    Context context= this;
    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Notification notification= null;
//        new SynsDay(this).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR,"gh");

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
    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public Notification nocatifi() throws FileNotFoundException, JSONException {
        //get data
        DatabaseHandler databaseHandler = new DatabaseHandler(this);
        Dudoan dudoan = databaseHandler.getDudoan();
        SttThisMonth sttThisMonth = databaseHandler.getSttThisMonth(1);
        JSONArray stt = databaseHandler.getJSONArray(sttThisMonth.getListBh());
        JSONArray sttv = databaseHandler.getJSONArray(sttThisMonth.getBhToday());


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



        Intent clickIntent7 = new Intent(this, rc7.class);
        PendingIntent clickPendingIntent7 = PendingIntent.getBroadcast(this,
                0, clickIntent7, 0);


        Intent clickIntent8 = new Intent(this, rc8.class);
        PendingIntent clickPendingIntent8 = PendingIntent.getBroadcast(this,
                0, clickIntent8, 0);

        Intent clickIntent9 = new Intent(this, rc9.class);
        PendingIntent clickPendingIntent9 = PendingIntent.getBroadcast(this,
                0, clickIntent9, 0);

        //set Notification
        for (int i=0;i<= 9;i++){
            boolean g=false;
            int vl = stt.getInt(i);
            for (int j=0; j<= sttv.length()-1;j++){
                if (vl==sttv.getInt(j)){
                    g=true;
                    continue;
                }
            }

            String nameIcon="";
            if (g==true){
                switch (vl){
                    case 0 : idIm=R.drawable.iconclick111;
                        break;
                    case 1 :idIm=R.drawable.iconclick110;
                        break;
                    case 2 : idIm=R.drawable.iconclick11;
                        break;
                    case 3 : idIm=R.drawable.iconclick1;
                        break;
                    case 4 : idIm=R.drawable.iconclick2;
                        break;
                    case 5 : idIm=R.drawable.iconclick4;
                        break;
                    case 6 : idIm=R.drawable.iconclick3;
                        break;
                    case 7 : idIm=R.drawable.iconclick9;
                        break;
                    case 8 : idIm=R.drawable.iconclick6;
                        break;
                    case 9 : idIm=R.drawable.iconclick10;
                        break;
                    case 12 : idIm=R.drawable.iconclick5;
                        break;

                }
            }else if (g==false){
                switch (vl){
                    case 0 : idIm=R.drawable.icnotification111;
                    nameIcon="Tôi ổn";
                        break;
                    case 3 :idIm=R.drawable.icnotification2;
                        nameIcon="Mệt mỏi";
                        break;
                    case 1 : idIm=R.drawable.icnotification110;
                        nameIcon="Cáu, giận";
                        break;
                    case 2 : idIm=R.drawable.icnotification4;
                        nameIcon="Thèm ăn";
                        break;
                    case 4 : idIm=R.drawable.icnotification5;
                        nameIcon="Mất ngủ";
                        break;
                    case 6 : idIm=R.drawable.icnotification6;
                        nameIcon="Đau lưng";
                        break;
                    case 5 : idIm=R.drawable.icnotification7;
                        nameIcon="Buồn";
                        break;
                    case 7 : idIm=R.drawable.icnotification10;
                        nameIcon="Đau bụng, dưới";
                        break;
                    case 12 : idIm=R.drawable.icnotification3;
                        nameIcon="Mùa dâu";
                        break;
                    case 8 : idIm=R.drawable.icnotification1;
                        nameIcon="Nổi Mụn";
                        break;
                    case 9 : idIm=R.drawable.icnotification8;
                        nameIcon="Tức ngực" ;
                        break;
                    case 10 : idIm=R.drawable.icnotification9;
                        nameIcon="Dịch nhờn" ;
                        break;
                    case 13 : idIm=R.drawable.icnotification113;
                    nameIcon="Kt mùa dâu";
                        break;
                }
            }
            switch (i){
                case 0 : idV=R.id.image_view_expanded1;
                    expandedView.setTextViewText(R.id.tv00,nameIcon);
                    break;
                case 1 :idV=R.id.image_view_expanded2;
                    expandedView.setTextViewText(R.id.tv01,nameIcon);
                    break;
                case 2 : idV=R.id.image_view_expanded3;
                    expandedView.setTextViewText(R.id.tv02,nameIcon);
                    break;
                case 3 : idV=R.id.image_view_expanded4;
                    expandedView.setTextViewText(R.id.tv03,nameIcon);
                    break;
                case 4 : idV=R.id.image_view_expanded5;
                    expandedView.setTextViewText(R.id.tv04,nameIcon);
                    break;
                case 5 : idV=R.id.image_view_expanded6;
                    expandedView.setTextViewText(R.id.tv05,nameIcon);
                    break;
                case 6 : expandedView.setImageViewResource(R.id.image_view_expanded7,idIm);
                 expandedView.setTextViewText(R.id.tv06,nameIcon);
                case 7 : expandedView.setImageViewResource(R.id.image_view_expanded8,idIm);
                    expandedView.setTextViewText(R.id.tv07,nameIcon);
                case 8 : expandedView.setImageViewResource(R.id.image_view_expanded9,idIm);
                    expandedView.setTextViewText(R.id.tv08,nameIcon);
                case 9 : idV=R.id.image_view_expanded10;
                    expandedView.setTextViewText(R.id.tv09,nameIcon);
                    break;
            }
            expandedView.setImageViewResource(idV,idIm);
        }
        collapsedView.setTextViewText(R.id.text_view_collapsed_1,sttThisMonth.getNote());
        collapsedView.setTextViewText(R.id.nn,sttThisMonth.getNn());
        expandedView.setTextViewText(R.id.conten,sttThisMonth.getNote());
        expandedView.setTextViewText(R.id.nn,sttThisMonth.getNn());





        expandedView.setOnClickPendingIntent(R.id.image_view_expanded1, clickPendingIntent);
        expandedView.setOnClickPendingIntent(R.id.image_view_expanded2, clickPendingIntent1);
        expandedView.setOnClickPendingIntent(R.id.image_view_expanded3, clickPendingIntent2);
        expandedView.setOnClickPendingIntent(R.id.image_view_expanded4, clickPendingIntent3);
        expandedView.setOnClickPendingIntent(R.id.image_view_expanded5, clickPendingIntent4);
        expandedView.setOnClickPendingIntent(R.id.image_view_expanded6, clickPendingIntent5);
        expandedView.setOnClickPendingIntent(R.id.image_view_expanded7, clickPendingIntent6);
        expandedView.setOnClickPendingIntent(R.id.image_view_expanded8, clickPendingIntent7);
        expandedView.setOnClickPendingIntent(R.id.image_view_expanded9, clickPendingIntent8);
        expandedView.setOnClickPendingIntent(R.id.image_view_expanded10, clickPendingIntent9);
        Intent mainIntent = new Intent(this, MainActivity.class);
        PendingIntent contentIntent = PendingIntent.getActivity(this, 0, mainIntent, 0);

        // NotificationManager
        NotificationManager notificationManager =
                (NotificationManager) this.getSystemService(Context.NOTIFICATION_SERVICE);




        Uri soundUri = Uri.parse(
                "android.resource://" +
                        getApplicationContext().getPackageName() +
                        "/" +
                        R.raw.message);

        AudioAttributes audioAttributes = new AudioAttributes.Builder()
                .setContentType(AudioAttributes.CONTENT_TYPE_SONIFICATION)
                .setUsage(AudioAttributes.USAGE_ALARM)
                .build();

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            // For API 26 and above
            CharSequence channelName = "My Notification";
            int importance = NotificationManager.IMPORTANCE_DEFAULT;
            NotificationChannel channel = new NotificationChannel(CHANNEL_ID, channelName, importance);
            channel.setSound(soundUri, audioAttributes);
            notificationManager.createNotificationChannel(channel);
        }
        // Prepare Notification
        NotificationCompat.Builder builder = new NotificationCompat.Builder(this, CHANNEL_ID)
                .setSmallIcon(R.drawable.iconlogo)
                .setCustomContentView(collapsedView)
                .setCustomBigContentView(expandedView)
                .setPriority(NotificationCompat.PRIORITY_DEFAULT)
                .setAutoCancel(true)
                .setSound(Uri.parse("android.resource://"
                        + context.getPackageName() + "/" + R.raw.message));

        return builder.build();

    }

}