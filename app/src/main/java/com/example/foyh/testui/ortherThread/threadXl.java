package com.example.foyh.testui.ortherThread;

import android.app.Notification;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.CountDownTimer;
import android.util.Log;
import android.widget.ImageView;
import android.widget.RemoteViews;

import androidx.core.app.NotificationCompat;
import androidx.core.content.ContextCompat;


import com.example.foyh.R;
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
//        try {
//                JSONObject dataJS=null;
//                dataJS = fileDAO.getData("notification.json","data",context);
//                JSONArray st=dataJS.getJSONArray("nocafition");
//                for (int i=0;i<=st.length()-1;i++){
//                    stt.add(st.getInt(i));
//                }
//            } catch (JSONException e) {
//            JSONObject ob = new JSONObject();
//            JSONObject ob1 = new JSONObject();
//            JSONArray obj = new JSONArray();
//            try {
//                for (int j = 0; j<=5;j++){
//                    stt.add(0);
//                    obj.put(0);
//                }
//                ob.put("nocafition",obj);
//                ob1.put("data",ob);
//            } catch (JSONException ii) {
//
//            }
//            fileDAO fileDAO =new fileDAO();
//            fileDAO.saveData("notification.json",ob1,context);
//            }catch (NullPointerException i){
//            JSONObject ob = new JSONObject();
//            JSONObject ob1 = new JSONObject();
//            JSONArray obj = new JSONArray();
//            try {
//                for (int j = 0; j<=5;j++){
//                    stt.add(0);
//                    obj.put(0);
//                }
//                ob.put("nocafition",obj);
//                ob1.put("data",ob);
//            } catch (JSONException ii) {
//
//            }
//            fileDAO fileDAO =new fileDAO();
//            fileDAO.saveData("notification.json",ob1,context);
//
//
//        }
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


    public Notification nocatifi() throws FileNotFoundException, JSONException {
        fileDAO file = new fileDAO();
        JSONObject ob=  file.getData("stt.json","data",context);
        JSONArray stt = ob.getJSONArray("stt");
        JSONArray sttv = ob.getJSONArray("sttv");
        String conten = ob.getString("st");
        Log.d("NOtification",conten);


        RemoteViews collapsedView = new RemoteViews(context.getPackageName(),
                R.layout.notification_collapsed);
        RemoteViews expandedView = new RemoteViews(context.getPackageName(),
                R.layout.notification_expanded);

        Intent clickIntent = new Intent(context, NotificationReceiver.class);
        PendingIntent clickPendingIntent = PendingIntent.getBroadcast(context,
                0, clickIntent, 0);

        Intent clickIntent1 = new Intent(context, rc1.class);
        PendingIntent clickPendingIntent1 = PendingIntent.getBroadcast(context,
                0, clickIntent1, 0);

        Intent clickIntent2 = new Intent(context, rc2.class);
        PendingIntent clickPendingIntent2 = PendingIntent.getBroadcast(context,
                0, clickIntent2, 0);

        Intent clickIntent3 = new Intent(context, rc3.class);
        PendingIntent clickPendingIntent3 = PendingIntent.getBroadcast(context,
                0, clickIntent3, 0);

        Intent clickIntent4 = new Intent(context, rc4.class);
        PendingIntent clickPendingIntent4 = PendingIntent.getBroadcast(context,
                0, clickIntent4, 0);

        Intent clickIntent5 = new Intent(context, rc5.class);
        PendingIntent clickPendingIntent5 = PendingIntent.getBroadcast(context,
                0, clickIntent5, 0);

        Intent clickIntent6 = new Intent(context, rc6.class);
        PendingIntent clickPendingIntent6 = PendingIntent.getBroadcast(context,
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
            if (g==true){
                switch (vl){
                    case 0 : idIm=R.drawable.ca;
                    break;
                    case 1 :idIm=R.drawable.ca;
                    break;
                    case 2 : idIm=R.drawable.ca;
                    break;
                    case 3 : idIm=R.drawable.ca;
                    break;
                    case 4 : idIm=R.drawable.ca;
                    break;
                    case 5 : idIm=R.drawable.ca;
                    break;
                    case 6 : idIm=R.drawable.ca;
                    break;
                    case 7 : idIm=R.drawable.ca;
                    break;
                    case 12 : idIm=R.drawable.ca;
                    break;
                    case 13 : idIm=R.drawable.ca;
                    break;

                }
            }else if (g==false){
                switch (vl){
                    case 0 : idIm=R.drawable.dd;
                    break;
                    case 1 :idIm=R.drawable.nm;
                    break;
                    case 2 : idIm=R.drawable.mm;
                    break;
                    case 3 : idIm=R.drawable.dn;
                    break;
                    case 4 : idIm=R.drawable.nn;
                    break;
                    case 5 : idIm=R.drawable.dl;
                    break;
                    case 6 : idIm=R.drawable.tb;
                    break;
                    case 7 : idIm=R.drawable.ca;
                    break;
                    case 12 : idIm=R.drawable.ca;
                    break;
                    case 13 : idIm=R.drawable.ca;
                    break;
                }
            }
            switch (i){
                case 0 : idV=R.id.image_view_expanded;
                break;
                case 1 :idV=R.id.image_view_expanded1;
                break;
                case 2 : idV=R.id.image_view_expanded2;
                break;
                case 3 : idV=R.id.image_view_expanded3;
                break;
                case 4 : idV=R.id.image_view_expanded4;
                break;
                case 5 : idV=R.id.image_view_expanded5;
                break;
                case 6 : idV=R.id.image_view_expanded6;
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

        //biuld notification
        Log.d("NOtification","done");
        notification = new NotificationCompat.Builder(context, CHANNEL_ID)
                .setSmallIcon(R.drawable.iconlogo)
                .setCustomContentView(collapsedView)
                .setCustomBigContentView(expandedView)
                .build();
        return notification;
    }
}