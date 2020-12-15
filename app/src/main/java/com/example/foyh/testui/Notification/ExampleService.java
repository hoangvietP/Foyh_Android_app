package com.example.foyh.testui.Notification;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Handler;
import android.os.IBinder;
import android.util.Log;
import android.widget.ImageView;
import android.widget.RemoteViews;

import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.core.app.NotificationCompat;
import androidx.core.content.ContextCompat;


import com.example.foyh.R;
import com.example.foyh.activity.MainActivity;
import com.example.foyh.testui.Data.classDT.fileDAO;
import com.example.foyh.testui.Data.service.DataServiceMethod;
import com.example.foyh.testui.Notification.NotiReceiver.NotificationReceiver;
import com.example.foyh.testui.Notification.NotiReceiver.rc1;
import com.example.foyh.testui.Notification.NotiReceiver.rc2;
import com.example.foyh.testui.Notification.NotiReceiver.rc3;
import com.example.foyh.testui.Notification.NotiReceiver.rc4;
import com.example.foyh.testui.Notification.NotiReceiver.rc5;
import com.example.foyh.testui.Notification.NotiReceiver.rc6;
import com.example.foyh.testui.Notification.NotiReceiver.rc7;
import com.example.foyh.testui.Notification.NotiReceiver.rc8;
import com.example.foyh.testui.Notification.NotiReceiver.rc9;
import com.example.foyh.testui.ortherThread.SynsDay;
import com.example.foyh.testui.ortherThread.threadXl;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.FileNotFoundException;
import java.util.Date;

import static com.example.foyh.testui.Notification.Notifi.CHANNEL_ID;


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
    public Notification nocatifi() throws FileNotFoundException, JSONException {
        fileDAO file = new fileDAO();
        JSONObject ob=  file.getData("stt.json","data",this);
        JSONArray stt = ob.getJSONArray("stt");
        JSONArray sttv = ob.getJSONArray("sttv");
        String conten = ob.getString("st");
        String nn = ob.getString("nn");
        JSONObject obj=  file.getData("thisMonthData.json","data",this);
        String dateUpdate= obj.getString("dayupdate");
        JSONArray ard=obj.getJSONArray("dataday");
        JSONArray dt = ard;
        int day = dt.getInt(0);
        int month =dt.getInt(1);
        int longMo = ard.getInt(2);
        int longdt= ard.getInt(3);
        JSONArray trt = ard.getJSONArray(4);



        JSONObject ob1=  file.getData("monthData.json","dataM",this);
        JSONArray ardt = ob1.getJSONArray("data");// arr chua cac obj data cua cac thang
        JSONObject artd1=ardt.getJSONObject(ardt.length()-1);// obj data cua thang nay
        JSONArray ardt1= artd1.getJSONArray("dataday");
        int lm= ardt1.getInt(1);
        int ldt= ardt1.getInt(2);
        JSONArray rttt = ardt1.getJSONArray(3);




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
                        nameIcon="Chán ăn";
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
        collapsedView.setTextViewText(R.id.text_view_collapsed_1,conten);
        collapsedView.setTextViewText(R.id.nn,nn);
        expandedView.setTextViewText(R.id.conten,conten);
        expandedView.setTextViewText(R.id.nn,nn);


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

//        DataServiceMethod dataServiceMethod = new DataServiceMethod();
//        int[] dayrt = {trt.getInt(0),trt.getInt(1)};
//        if (day < dayrt[1]+3  && day> dayrt[0]-3) {
//            expandedView.setImageViewResource(R.id.tticon,R.drawable.bcs);
//            //them bh rt
//        }else if (day >= dayrt[0] && day <= dayrt[1]) {
////            expandedView.setImageViewResource(R.id.tticon,R.drawable.iconbvs);
//        }else if (day >= longMo-2 && lm == 0) {
//            expandedView.setImageViewResource(R.id.tticon,R.drawable.iconbvs);
//        }else if (day > lm-2  && day <= lm+longdt+2 && lm != 0) {
//            expandedView.setImageViewResource(R.id.tticon,R.drawable.iconbvs);
//        }else if (day>longMo &&ldt==0 && lm == 0 && day<longMo+7){
////            expandedView.setImageViewResource(R.id.tticon,R.drawable.iconbvs);
//        }else if (ldt!=0 && lm != 0){
////            expandedView.setImageViewResource(R.id.tticon,R.drawable.iconbvs);
//        }

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