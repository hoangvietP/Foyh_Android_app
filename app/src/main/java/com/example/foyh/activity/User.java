package com.example.foyh.activity;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.foyh.R;
import com.example.foyh.fragment.BhFragment;
import com.example.foyh.fragment.PiechartFragment;
import com.example.foyh.fragment.ValuebhFragment;
import com.example.foyh.fragment.ttFragment;
import com.example.foyh.testui.Data.classDT.fileDAO;
import com.example.foyh.testui.Data.service.DataServiceMethod;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.components.Description;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.utils.ColorTemplate;


import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

public class User extends AppCompatActivity {
//    private AnyChartView Chart;
    TextView date,day,ct;
    ImageView prf,next,pre;
    JSONArray ard = new JSONArray();
    Context context = this;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTheme(R.style.Theme_AppCompat_Light_NoActionBar);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN
                , WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_user);
        overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
        pre= findViewById(R.id.preic);
        ct= findViewById(R.id.ct);

        JSONArray bh = new JSONArray();
        fileDAO file = new fileDAO();
        JSONObject ob= null;

        try {
            ob = file.getData("thisMonthData.json","data",this);
            String dateUpdate= ob.getString("dayupdate");
             ard=ob.getJSONArray("dataday");
            JSONArray dt = ard;
            int daycount = dt.getInt(0);//today
            int month =dt.getInt(1);// count month
            int longMo = ard.getInt(2);
            int longdt= ard.getInt(3);
            JSONArray trt = ard.getJSONArray(4);
            next = findViewById(R.id.next);
            day= findViewById(R.id.day);
            day.setText("Ngày thứ: "+daycount+" của chu kì");
        } catch (JSONException e) {
            e.printStackTrace();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        try {
            loadFragment(new ValuebhFragment(getStt()));
            FragmentManager fm = getFragmentManager();
            FragmentTransaction fragmentTransaction = fm.beginTransaction();
            fragmentTransaction.replace(R.id.frameLayout1,new PiechartFragment(this,ard));
            fragmentTransaction.commit(); // save the changes
            setDate();
        } catch (JSONException e) {
            e.printStackTrace();
        }

        pre.setVisibility(View.INVISIBLE);
        prf= (ImageView)findViewById(R.id.prf);
        prf.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(User.this,profileActivity.class);
                startActivity(intent);
            }
        });
        pre.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentManager fm = getFragmentManager();
                FragmentTransaction fragmentTransaction = fm.beginTransaction();
                fragmentTransaction.replace(R.id.frameLayout1,new PiechartFragment(context,ard));
                fragmentTransaction.commit(); // save the changes
                pre.setVisibility(View.INVISIBLE);
                next.setVisibility(View.VISIBLE);
                ct.setVisibility(View.VISIBLE);
            }
        });

        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentManager fm = getFragmentManager();
                FragmentTransaction fragmentTransaction = fm.beginTransaction();
                fragmentTransaction.replace(R.id.frameLayout1,new ttFragment());
                fragmentTransaction.commit(); // save the changes
                setDate();
                pre.setVisibility(View.VISIBLE);
                next.setVisibility(View.INVISIBLE);
                ct.setVisibility(View.INVISIBLE);
            }
        });


    }
private  JSONObject getStt(){
    fileDAO file = new fileDAO();
    JSONObject objstt=null;
    try{
         objstt=  file.getData("stt.json","data",this);//get data from stt file

    }catch (FileNotFoundException | JSONException|NullPointerException d){

    }
    return objstt;
}

    private void loadFragment(Fragment fragment) {
// create a FragmentManager
        FragmentManager fm = getFragmentManager();
        FragmentTransaction fragmentTransaction = fm.beginTransaction();
        fragmentTransaction.replace(R.id.framebh,fragment);
        fragmentTransaction.commit(); // save the changes

    }


    public void setDate(){
        date= findViewById(R.id.date);
        date.setText(new DataServiceMethod().getDate());
    }

}