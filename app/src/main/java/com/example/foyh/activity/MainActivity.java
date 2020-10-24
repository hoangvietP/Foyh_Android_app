package com.example.foyh.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.AsyncTask;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;

import com.example.foyh.R;
import com.example.foyh.testui.Data.classDT.fileDAO;
import com.example.foyh.testui.ortherThread.RegistrationTask;
import com.example.foyh.testui.ortherThread.SynsDay;

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

        test= (TextView) findViewById(R.id.profileCT);
        //write json file
//        JSONObject ob = new JSONObject();
//        JSONObject data= new JSONObject();
//        try {
//            data.put("name","Pro");
//            data.put("age","file");
//            ob.put("data",data);
//        } catch (JSONException e) {
//            e.printStackTrace();
//        }
//        fileDAO fileDAO =new fileDAO();
//        fileDAO.saveData("data.json",ob,this);
//
//
//
//        //get data
//        JSONObject dataJS=null;
//        try {
//            dataJS = fileDAO.getData("data.json","data",this);
//            test.setText(dataJS.getString("name")+dataJS.getString("age"));
//        } catch (JSONException | FileNotFoundException e) {
//            e.printStackTrace();
//        }


        new SynsDay(this,test).execute("gh");

    }
}