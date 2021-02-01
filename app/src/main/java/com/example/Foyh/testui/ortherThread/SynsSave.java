package com.example.Foyh.testui.ortherThread;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.util.Log;
import android.view.LayoutInflater;
import android.widget.Toast;

import com.example.Foyh.R;
import com.example.Foyh.activity.profileActivity;
import com.example.Foyh.testui.Data.classDT.ConnectAsynchronously;
import com.example.Foyh.testui.Data.classDT.fileDAO;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.FileNotFoundException;

public class SynsSave extends AsyncTask<String, Void, String> {
    Context context;
    JSONObject object;
    String us;
    public SynsSave(Context context, JSONObject object,String us,Activity activity){
        this.context= context;
        this.object= object;
        this.us=us;
        this.activity=activity;
        }
    String stt ="";
    Activity activity;
    Dialog dialog;
    private String token="";
    @Override
    protected void onPreExecute() {
        AlertDialog.Builder builder = new AlertDialog.Builder(activity);
        LayoutInflater inflater = activity.getLayoutInflater();
        builder.setView(inflater.inflate(R.layout.loading_dialog,null));
        builder.setCancelable(true);
        dialog= builder.create();
        dialog.show();
               try {
            JSONObject obj = new fileDAO().getData("token.json","token",context);
            token = String.valueOf(obj.get("tok"));
        } catch (JSONException e) {
            e.printStackTrace();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }



    }
    @SuppressLint("WrongThread")
    @Override
    protected String doInBackground(String... params)  {
        int st=0;
        while (st==0&& st<=20){
             stt=ConnectAsynchronously.connectAsynchronously(params[0],object,token);
            try {
                Thread.sleep(1000);
                st++;
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
        String[] split=stt.split(us);
        String[] split1=stt.split("false");
        if (split.length>1){
            dialog.dismiss();
            Toast.makeText(context,"Lưu thành công!",
                    Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(context, profileActivity.class);
            context.startActivity(intent);
        }
        if (split1.length>1){
            dialog.dismiss();
            Toast.makeText(context,"Tài khoản đã tồn tại!",
                    Toast.LENGTH_SHORT).show();
        }

    }

}
