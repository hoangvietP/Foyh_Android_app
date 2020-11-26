package com.example.foyh.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationManagerCompat;
import androidx.core.content.ContextCompat;

import android.app.Notification;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;

import com.example.foyh.R;
import com.example.foyh.testui.Data.classDT.fileDAO;
import com.example.foyh.testui.Notification.ExampleService;
import com.example.foyh.testui.ortherThread.RegistrationTask;
import com.example.foyh.testui.ortherThread.SynsDay;
import com.example.foyh.testui.ortherThread.threadXl;

import org.json.JSONException;
import org.json.JSONObject;
import java.io.FileNotFoundException;

public class MainActivity extends AppCompatActivity {
    TextView test;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTheme(R.style.Theme_AppCompat_Light_NoActionBar);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN
                , WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_main);
        overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);

//        fileDAO file= new fileDAO();
//        boolean us = true;
//        try {
//            file.getDataOB("thisMonthData.json",this);
//        }catch (FileNotFoundException | JSONException e){
//            // run start activity
//            us=false;
////            Intent intent = new Intent(MainActivity.this, startActivity.class);
////            startActivity(intent);
//        }
//        if (us==true){
//            Intent intent = new Intent(MainActivity.this, step1.class);
//            startActivity(intent);
//        }else if(us==false){
//            Intent intent = new Intent(MainActivity.this, step1.class);
//            startActivity(intent);
//        }

        Intent intent = new Intent(MainActivity.this, step1.class);
        startActivity(intent);
    }
}