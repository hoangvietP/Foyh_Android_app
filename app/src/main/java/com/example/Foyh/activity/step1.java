package com.example.Foyh.activity;

import androidx.appcompat.app.AppCompatActivity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.database.CursorIndexOutOfBoundsException;
import android.os.Bundle;
import android.util.Log;
import android.view.Window;
import android.view.WindowManager;

import com.example.Foyh.R;
import com.example.Foyh.fragment.FistFragment;
import com.example.Foyh.testui.Data.classDT.DataUser;
import com.example.Foyh.testui.Data.classDT.DatabaseHandler;

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
        String token = "";
        try {
            DataUser dataUser = new DatabaseHandler(this).getDataUser(1);
            token= dataUser.getToken();
        } catch (CursorIndexOutOfBoundsException e) {
            token = "";
            new DatabaseHandler(this).addUser(new DataUser(1,"User","2000","none",0));
            Log.d("add User",new DatabaseHandler(this).getDataUser(1).toString());
            e.printStackTrace();
        }
        if (token.equals("")) {
            Intent intent = new Intent(step1.this, profileActivity.class);
            startActivity(intent);
        } else if (token.equals("none") || !token.equals("")) {
            JSONArray data = new JSONArray();
            loadFragment(new FistFragment(0, data));
        }

        // save the changes
    }
    private void loadFragment(Fragment fragment) {
// create a FragmentManager
        FragmentManager fm = getFragmentManager();
        FragmentTransaction fragmentTransaction = fm.beginTransaction();
        fragmentTransaction.replace(R.id.frameLayout,fragment);
        fragmentTransaction.commit(); // save the changes
    }
}