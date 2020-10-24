package com.example.foyh.testui.ortherThread;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Build;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.RequiresApi;
import androidx.core.content.ContextCompat;


import com.example.foyh.testui.Data.service.DataServiceMethod;
import com.example.foyh.testui.Notification.ExampleService;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.FileNotFoundException;
import java.util.ArrayList;

public class SynsDay  extends AsyncTask<String, Void, String> {

    ProgressDialog progressDialog;
    Context context;
    TextView text;
    ArrayList<Integer> stt=null;

    public SynsDay(Context context, TextView text) {
        this.context = context;
        this.text=text;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
    }
    Intent serviceIntent;
    int k1=3;
    @SuppressLint("WrongThread")
    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Override
    protected String doInBackground(String... strings) {

        int i=0;
       while (i>=0) {
           new RegistrationTask(context).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR,"https://47dd89dadac7.ngrok.io/api/dudoan");
           try {
               serviceIntent= new Intent(context, ExampleService.class);
               try {
                   new DataServiceMethod().testData(context,0);
               } catch (FileNotFoundException e) {
                   e.printStackTrace();
               }
               ContextCompat.startForegroundService(context, serviceIntent);
               Thread.sleep(10000000);

           } catch (InterruptedException | JSONException e) {
               e.printStackTrace();
           }
       }
        return null;
    }

    @Override
    protected void onPostExecute(String aString) {
        super.onPostExecute(aString);
    }
}