package com.example.foyh.testui.ortherThread;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.Toast;


import androidx.core.content.ContextCompat;

import com.example.foyh.activity.MainActivity;
import com.example.foyh.testui.Data.classDT.ConnectAsynchronously;
import com.example.foyh.testui.Data.classDT.fileDAO;
import com.example.foyh.testui.Data.service.DataServiceMethod;
import com.example.foyh.testui.Data.service.getDataMonth;
import com.example.foyh.testui.Notification.ExampleService;
import com.example.foyh.testui.Notification.Notifi;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.net.ConnectException;
import java.net.HttpURLConnection;

public class RegistrationTask extends AsyncTask<String, Void, String>{
    private Context context;

    public RegistrationTask(Context context){
        this.context= context;
    }

    @SuppressLint("WrongThread")
    @Override
    protected String doInBackground(String... params)  {


        getDataMonth gdt = new getDataMonth();
        JSONObject jsonObject= null;
        JSONObject nn = new JSONObject();
        try {
            nn.put("message","false");
        } catch (JSONException e) {
            e.printStackTrace();
        }
        jsonObject=nn;
        String data= "";
        fileDAO file=new fileDAO();
        try {
            jsonObject = gdt.getData(context);
        } catch (JSONException e) {
            e.printStackTrace();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        while (data.equals("") || data.equals("loss")){
            data = ConnectAsynchronously.connectAsynchronously(params[0],jsonObject);
            Log.d("data",data+"connect to server");
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        try {
             gdt.saveDataThisMonth(context);
        } catch (JSONException e) {
            e.printStackTrace();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        if (!data.equals("") || !data.equals("loss")){
            File fil = new File(context.getFilesDir(), "thisMonthData.json");
            FileWriter fileWriter = null;
            try {
                fileWriter = new FileWriter(fil);
                BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
                bufferedWriter.write(data);
                bufferedWriter.close();
            } catch (IOException | NullPointerException e) {
                e.printStackTrace();
            }
        //getdate
            DataServiceMethod dt = new DataServiceMethod();
            String dateUpdate = dt.getDate();
            JSONObject ob = null;
            try {
                ob = file.getDataOB("thisMonthData.json", context);
                ob.put("dayupdate", dateUpdate);
                ob.put("countRT", 0);
                ob.put("countDT", 0);
                ob.put("dayst", 0);
                JSONArray dtd = ob.getJSONArray("dataday");
                int month = dtd.getInt(0);
                int lm = dtd.getInt(1);
                int ldt = dtd.getInt(2);
                JSONArray rt = dtd.getJSONArray(3);

                JSONArray ndtd = new JSONArray();
                ndtd.put(0);
                ndtd.put(month);
                ndtd.put(lm);
                ndtd.put(ldt);
                ndtd.put(rt);

                ob.remove("dataday");
                ob.put("dataday", ndtd);
                JSONObject ob2 = new JSONObject();
                Log.d("save data api ", ob.toString());
                ob2.put("data", ob);
                file.saveData("thisMonthData.json", ob2, context);
            } catch (JSONException e) {
                e.printStackTrace();
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (NullPointerException e) {
                e.printStackTrace();
            }

            JSONObject k = new JSONObject();
            try {
                k.put("stt","1");
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

            new SynsDay(context).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR,"gh");
        }
        return data;
    }

    @Override
    protected void onPostExecute(String s) {
        super.onPostExecute(s);
        int requestCode =ConnectAsynchronously.getRequestCode();
        if(requestCode== HttpURLConnection.HTTP_OK){
            //sucess
            Log.d("Get data from api","done");
        }else {
            Log.d("false","done");
        }
    }
}