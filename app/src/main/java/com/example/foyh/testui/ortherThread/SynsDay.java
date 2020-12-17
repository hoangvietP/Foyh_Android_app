package com.example.foyh.testui.ortherThread;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Build;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.RequiresApi;
import androidx.core.content.ContextCompat;


import com.example.foyh.testui.Data.classDT.fileDAO;
import com.example.foyh.testui.Data.service.DataServiceMethod;
import com.example.foyh.testui.Notification.ExampleService;
import com.example.foyh.testui.Notification.Notifi;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

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
        fileDAO file=new fileDAO();
        setStt(0);
        int k=0;
        int i=0;
       while (i>=0 && i!=1) {
           int hh= new DataServiceMethod().getH();
           int mm = new DataServiceMethod().getM();
           int ss = new DataServiceMethod().getS();
           try {
               JSONObject ik =file.getData("statush.json","data",context);
               i = Integer.parseInt(String.valueOf(ik.get("stt")));
           } catch (JSONException e) {
               e.printStackTrace();
           } catch (FileNotFoundException e) {
               e.printStackTrace();
           }
           if (hh==00 && mm==00 && ss==01){
               try {
                   serviceIntent= new Intent(context, ExampleService.class);
                   try {
                       new DataServiceMethod().testData(context,0);
                   } catch (FileNotFoundException e) {
                       e.printStackTrace();
                   }
                   ContextCompat.startForegroundService(context, serviceIntent);
               } catch (JSONException  e) {
                   e.printStackTrace();
               }
           }
           try {
               Thread.sleep(1000);
           } catch (InterruptedException e) {
               e.printStackTrace();
           }
       }
        return null;
    }

    @Override
    protected void onPostExecute(String aString) {
        super.onPostExecute(aString);
    }
    public void setStt(int stt){
        fileDAO file=new fileDAO();
        JSONObject k = new JSONObject();
        try {
            k.put("stt",stt+"");
        } catch (JSONException e) {
            e.printStackTrace();
        }
        JSONObject da = new JSONObject();
        try {
            da.put("data",k);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        file.saveData("statush.json", da, context);
    }
}