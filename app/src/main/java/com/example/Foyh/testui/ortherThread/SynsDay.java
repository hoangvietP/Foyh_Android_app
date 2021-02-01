package com.example.Foyh.testui.ortherThread;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Build;
import android.util.Log;

import androidx.annotation.RequiresApi;
import androidx.core.content.ContextCompat;


import com.example.Foyh.testui.Data.service.DataServiceMethod;
import com.example.Foyh.testui.Notification.ExampleService;

import org.json.JSONException;

import java.io.FileNotFoundException;
import java.util.ArrayList;

public class SynsDay  extends AsyncTask<String, Void, String> {

    Context context;
    ArrayList<Integer> stt=null;

    public SynsDay(Context context) {
        this.context = context;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
    }

    Intent serviceIntent;
    @SuppressLint("WrongThread")
    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Override
    protected String doInBackground(String... strings) {
        int k=0;
        int i=0;
       while (i>=0 && i!=1) {
           int hh= new DataServiceMethod().getH();
           int mm = new DataServiceMethod().getM();
           int ss = new DataServiceMethod().getS();
//           if (hh==00 && mm==00 && ss==01) {
               try {
                   serviceIntent = new Intent(context, ExampleService.class);
                   try { new DataServiceMethod().testData(context, 0);
                   } catch (FileNotFoundException e) {
                       e.printStackTrace();
                   }
                   ContextCompat.startForegroundService(context, serviceIntent);
               } catch (JSONException e) {
                   e.printStackTrace();
               }
//           }
           try {
               Thread.sleep(10800000);
           } catch (InterruptedException e) {
               e.printStackTrace();
           }
           Log.d("---------------","-------------");
       }
        return null;
    }

    @Override
    protected void onPostExecute(String aString) {
        super.onPostExecute(aString);
    }

}