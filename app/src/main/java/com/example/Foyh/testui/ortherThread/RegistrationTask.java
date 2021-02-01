package com.example.Foyh.testui.ortherThread;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;


import com.example.Foyh.testui.Data.classDT.ConnectAsynchronously;

import org.json.JSONObject;

import java.net.HttpURLConnection;

public class RegistrationTask extends AsyncTask<String, Void, String>{
    private Context context;
    private String token;
    public RegistrationTask(Context context){
        this.context= context;
    }

    @SuppressLint("WrongThread")
    @Override
    protected String doInBackground(String... params)  {
        JSONObject jsonObject = new JSONObject();


        String data= "";
        while (data.equals("") || data.equals("loss")){
            data = ConnectAsynchronously.connectAsynchronously(params[0],jsonObject,token);
            Log.d("data",data+"connect to server");
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
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