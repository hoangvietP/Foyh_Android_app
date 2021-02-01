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
import com.example.Foyh.activity.MainActivity;
import com.example.Foyh.testui.Data.classDT.ConnectAsynchronously;
import com.example.Foyh.testui.Data.classDT.DataUser;
import com.example.Foyh.testui.Data.classDT.DatabaseHandler;

import org.json.JSONObject;

public class Login extends AsyncTask<String, Void, String> {
    Context context;
    JSONObject object;
    Boolean us=true;
    Boolean lg = true;
    private String token;
    Activity activity;
    public Login(Context context, JSONObject object, Activity activity){
        this.context= context;
        this.object= object;
        this.activity = activity;
    }
    Dialog dialog;
    @Override
    protected void onPreExecute() {
        AlertDialog.Builder builder = new AlertDialog.Builder(activity);
        LayoutInflater inflater = activity.getLayoutInflater();
        builder.setView(inflater.inflate(R.layout.loading_dialog,null));
        builder.setCancelable(true);
        dialog= builder.create();
        dialog.show();
    }
    String stt ="";
    Boolean internet=true;
    @SuppressLint("WrongThread")
    @Override
    protected String doInBackground(String... params)  {
        int st=0;
        while (st>=0 && st<=20){
            stt= ConnectAsynchronously.connectAsynchronously(params[0],object,token);
            try {
                Thread.sleep(1000);
                st++;
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            String[] split=stt.split("accessToken");
            String[] split1=stt.split("loss");
            if (split.length>1 && split1.length==1){
            String[] toke1= stt.split(",");
            String[] toke2= toke1[0].split(":");
            token=toke2[1];
            break;
            }else if (split1.length>1){
                lg=false;
                break;
            }
            if (st==19){
                internet=false;
                break;
            }
        }
        return stt;
    }

    @SuppressLint("LongLogTag")
    @Override
    protected void onPostExecute(String s) {
        if (internet==true){
            String[] toke1= stt.split(",");
            String[] toke2= toke1[0].split(":");
            token=toke2[1];
            Log.d("token",token);
        }

        if (lg==false){
            dialog.dismiss();
            Toast.makeText(context,"Tài khoản mật khẩu không chính xác!",
                    Toast.LENGTH_SHORT).show();
            Log.d("Login","false");
        }
        if (internet==false){
            dialog.dismiss();
            Toast.makeText(context,"Không thể kết nối tới máy chủ!",
                    Toast.LENGTH_SHORT).show();
        }
        if (lg==true && internet==true){
            Toast.makeText(context,"Đăng nhập thành công!",
                    Toast.LENGTH_SHORT).show();
            Log.d("Login","success");
            DataUser dataUser = new DatabaseHandler(context).getDataUser(1);
            dataUser.setToken(token);
            new DatabaseHandler(context).UpdateUser(dataUser);
            Log.d("DataUser",new DatabaseHandler(context).getDataUser(1).toString());
            if (us==true) {
                Intent intent = new Intent(context, MainActivity.class);
                context.startActivity(intent);
            }
        }
        dialog.dismiss();
    }
}
