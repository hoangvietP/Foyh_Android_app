package com.example.foyh.activity;

import androidx.appcompat.app.AppCompatActivity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;

import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;

import com.example.foyh.R;
import com.example.foyh.fragment.FistFragment;
import com.example.foyh.fragment.QsFragment;
import com.example.foyh.fragment.SecondFragment;

import org.json.JSONArray;


public class step1 extends AppCompatActivity {
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTheme(R.style.Theme_AppCompat_Light_NoActionBar);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN
                , WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_step1);
        overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);

        JSONArray data = new JSONArray();
        loadFragment( new FistFragment(0,data));
        FragmentManager fm = getFragmentManager();
        FragmentTransaction fragmentTransaction = fm.beginTransaction();
        fragmentTransaction.replace(R.id.frameLayout1,new QsFragment("Mùa dâu gần nhấn của bạn \n bắt đầu vào ngày nào ?"));
        fragmentTransaction.commit(); // save the changes

    }


    private void loadFragment(Fragment fragment) {
// create a FragmentManager
        FragmentManager fm = getFragmentManager();
        FragmentTransaction fragmentTransaction = fm.beginTransaction();
        fragmentTransaction.replace(R.id.frameLayout,fragment);
        fragmentTransaction.commit(); // save the changes
    }
}