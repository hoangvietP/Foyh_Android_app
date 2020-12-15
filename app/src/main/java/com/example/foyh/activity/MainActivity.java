package com.example.foyh.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;

import com.example.foyh.R;
import com.example.foyh.testui.Data.classDT.fileDAO;
import com.example.foyh.testui.Data.service.DataServiceMethod;
import com.example.foyh.testui.Notification.ExampleService;
import com.example.foyh.testui.ortherThread.SynsDay;

import org.json.JSONException;

import java.io.FileNotFoundException;

public class MainActivity extends AppCompatActivity {
    TextView test;
    Context context = this;    boolean us = true;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTheme(R.style.Theme_AppCompat_Light_NoActionBar);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN
                , WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_main);
        overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
        fileDAO file= new fileDAO();
        new CountDownTimer(2000, 100) {
                public void onTick(long millisUntilFinished) {

                }
                public void onFinish() {
                    Thread.currentThread();
                    try {
                        file.getDataOB("thisMonthData.json",context);
                    }catch (FileNotFoundException | JSONException e){
                        us=false;
                    }
                    if (us==true){
                        try {
                            new DataServiceMethod().testData(context,0);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        } catch (FileNotFoundException e) {
                            e.printStackTrace();
                        }
                        Intent serviceIntent= new Intent(context, ExampleService.class);
                        ContextCompat.startForegroundService(context, serviceIntent);
                        Intent intent = new Intent(MainActivity.this, User.class);
                        startActivity(intent);
                    }else if(us==false){
                        Intent intent = new Intent(MainActivity.this, step1.class);
                        startActivity(intent);
                    }
                }
            }.start();


    }
}