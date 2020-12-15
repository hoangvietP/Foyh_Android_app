package com.example.foyh.testui.ortherThread;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.Toast;

import com.example.foyh.testui.Data.classDT.ConnectAsynchronously;

import org.json.JSONException;
import org.json.JSONObject;

import java.net.HttpURLConnection;

public class SynsSave extends AsyncTask<String, Void, String> {
    Context context;
    JSONObject object;
    String us;
    public SynsSave(Context context, JSONObject object,String us){
        this.context= context;
        this.object= object;
        this.us=us;
        }
    String stt ="";
    @SuppressLint("WrongThread")
    @Override
    protected String doInBackground(String... params)  {
        int st=0;
        while (st==0){
             stt=ConnectAsynchronously.connectAsynchronously(params[0],object);
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            String[] split=stt.split("us");
            String[] split1=stt.split("false");
            if (split.length!=1){
                st=split.length;
            }
            if (split1.length!=1){
                st=split1.length;
            }
        }
        return stt;
    }

    @SuppressLint("LongLogTag")
    @Override
    protected void onPostExecute(String s) {
        Log.d("return from api register",stt);
        String[] split=stt.split("us");
        String[] split1=stt.split("false");
        if (split.length>=1){
            Toast.makeText(context,"Lưu thành công!",
                    Toast.LENGTH_SHORT).show();
        }
        if (split1.length>=1){
            Toast.makeText(context,"Tài khoản đã tồn tại!",
                    Toast.LENGTH_SHORT).show();
        }

    }

}
