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
import com.example.foyh.fragment.loginFragment;
import com.example.foyh.fragment.registerFragment;
import com.example.foyh.testui.Data.service.DataServiceMethod;
import com.example.foyh.testui.ortherThread.RegistrationTask;
import com.example.foyh.testui.ortherThread.SynsDay;
import com.example.foyh.testui.ortherThread.SynsSave;

import org.json.JSONException;
import org.json.JSONObject;

public class profileActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTheme(R.style.Theme_AppCompat_Light_NoActionBar);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN
                , WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.profile_user);
        overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
        FragmentManager fm1 = getFragmentManager();
        FragmentTransaction fragmentTransaction1 = fm1.beginTransaction();
        fragmentTransaction1.replace(R.id.frameLayoutProfile,new loginFragment(profileActivity.this));
        fragmentTransaction1.commit();
    }
}