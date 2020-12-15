package com.example.foyh.activity;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.NumberPicker;
import android.widget.Toast;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import com.example.foyh.R;
import com.example.foyh.fragment.PiechartFragment;
import com.example.foyh.testui.Data.service.DataServiceMethod;
import com.example.foyh.testui.ortherThread.RegistrationTask;
import com.example.foyh.testui.ortherThread.SynsDay;
import com.example.foyh.testui.ortherThread.SynsSave;

import org.json.JSONException;
import org.json.JSONObject;

public class profileActivity extends AppCompatActivity {
    EditText fname,us,ps;
    NumberPicker sn;
    Button save,huy;
    Context context = this;
    int snv =0;
    @RequiresApi(api = Build.VERSION_CODES.Q)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTheme(R.style.Theme_AppCompat_Light_NoActionBar);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN
                , WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.profile);
        overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
        fname= findViewById(R.id.addName);
        sn= findViewById(R.id.sn);
        us= findViewById(R.id.user);
        ps= findViewById(R.id.pass);
        save= findViewById(R.id.add);
        huy= findViewById(R.id.btnCancel);
        sn.setTextSize(45);
        sn.setMinValue(1960);
        sn.setMaxValue(Integer.parseInt(new DataServiceMethod().getYear()));
        sn.setValue(2020);
        sn.setOnValueChangedListener(new NumberPicker.OnValueChangeListener() {
            @Override
            public void onValueChange(NumberPicker picker, int oldVal, int newVal){
                String a= String.valueOf(newVal);
                snv= Integer.parseInt(a);
            }
        });
        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String namev = String.valueOf(fname.getText());
                String usv = String.valueOf(us.getText());
                String psv = String.valueOf(ps.getText());
                int stt=0;
                if (namev.equals("")){
                    stt=1;
                }
                if (usv.equals("")){
                    stt=1;
                }
                if (psv.equals("")){
                    stt=1;
                }
                if (stt==1){
                    Toast.makeText(context,"Vui lòng điền đầy đủ các mục !",
                            Toast.LENGTH_SHORT).show();
                }else if (stt==0){
                    JSONObject ob= new JSONObject();
                    try {
                        ob.put("username",usv);
                        ob.put("password",psv);
                        ob.put("fullname",namev);
                        ob.put("birthday",snv);
                        Log.d("profile",ob.toString());
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }

                    new SynsSave(context,ob,usv).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, " https://6cb32e37f7b2.ngrok.io/api/register");
                }
            }
        });
        huy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(profileActivity.this, User.class);
                startActivity(intent);
            }
        });


    }
}