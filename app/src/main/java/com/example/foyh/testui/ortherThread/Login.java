package com.example.foyh.testui.ortherThread;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;
import android.view.LayoutInflater;
import android.widget.Toast;

import com.example.foyh.R;
import com.example.foyh.activity.profileActivity;
import com.example.foyh.loadingDialog;
import com.example.foyh.testui.Data.classDT.ConnectAsynchronously;
import com.example.foyh.testui.Data.classDT.fileDAO;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.FileNotFoundException;

public class Login extends AsyncTask<String, Void, String> {
    Context context;
    JSONObject object;
    Boolean lg = true;
    String token;
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

            stt= ConnectAsynchronously.connectAsynchronously(params[0],object);
            try {
                Thread.sleep(1000);
                st++;
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            String[] split=stt.split("accessToken");
            String[] split1=stt.split("loss");
            if (split.length>1 && split1.length==1){
                break;
            }else if (split1.length>1){
                lg=false;
                break;
            }
            if (st==19){
                internet=false;
            }
        }
        return stt;
    }

    @SuppressLint("LongLogTag")
    @Override
    protected void onPostExecute(String s) {
        String[] toke1= stt.split(",");
        String[] toke2= toke1[0].split(":");
        token=toke2[1];
        Log.d("token",token);

//        try {
//            JSONObject obj = new fileDAO().getData("token.json","token",context);
//            token = String.valueOf(obj.get("tok"));
//        } catch (JSONException e) {
//            e.printStackTrace();
//        } catch (FileNotFoundException e) {
//            e.printStackTrace();
//        }
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
        if (lg==true){
            dialog.dismiss();
            Toast.makeText(context,"Đăng nhập thành công!",
                    Toast.LENGTH_SHORT).show();
            Log.d("Login","success");
            JSONObject tk = new JSONObject();
            try {
                JSONObject t = new JSONObject();
                t.put("tok",token);
                tk.put("token",t);
            } catch (JSONException e) {
                e.printStackTrace();
            }
            new fileDAO().saveData("token.json",tk,context);
        }
    }
}
