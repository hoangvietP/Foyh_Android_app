package com.example.foyh.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.foyh.R;
import com.example.foyh.testui.Data.service.DataServiceMethod;
import com.example.foyh.testui.Notification.ExampleService;
import com.example.foyh.testui.ortherThread.RegistrationTask;
import com.example.foyh.testui.ortherThread.SynsDay;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class startActivity extends AppCompatActivity {
    EditText name;
    RadioGroup grGender;
    RadioButton male,female;
    TextView next,pre;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setTheme(R.style.Theme_AppCompat_Light_NoActionBar);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN
                , WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_start);
        overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);

        name=findViewById(R.id.name);
        grGender = findViewById(R.id.grGender);
        male = findViewById(R.id.nam);
        female = findViewById(R.id.nu);
        next= findViewById(R.id.next);
        pre=findViewById(R.id.pre);
        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int checkedRadioId = grGender.getCheckedRadioButtonId();
                if(checkedRadioId== R.id.nam) {

                } else if(checkedRadioId== R.id.nu) {
                    setContentView(R.layout.activity_female);
                }
            }
        });
        pre.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

    }
    public void test(View v){
        JSONArray bhdt = new JSONArray();
        JSONArray bhrt = new JSONArray();
        JSONArray bh = new JSONArray();
        bh.put(1);
        bh.put(2);
        bh.put(3);
        bhdt.put(bh);
        bhdt.put(bh);
        bhdt.put(bh);
        bhrt.put(bh);
        bhrt.put(bh);
        bhrt.put(bh);
        JSONArray bhdt1 = new JSONArray();

        DataServiceMethod dt = new DataServiceMethod();
        try {
            dt.setData(0,this,"2020-10-30",15,20,7,0,0,1,34,bhrt,bhdt,0);
            dt.saveData(this,0,0,0,0,bhdt1,1);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
    public void startService(View v) {
        Intent serviceIntent= new Intent(this, ExampleService.class);
        ContextCompat.startForegroundService(this, serviceIntent);

    }
    public void stopService(View v) {
        Intent serviceIntent = new Intent(this, ExampleService.class);
        stopService(serviceIntent);
    }
}