package com.example.Foyh.activity;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.Foyh.R;
import com.example.Foyh.fragment.loginFragment;
import com.example.Foyh.testui.Data.classDT.fileDAO;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.FileNotFoundException;

public class profileActivity extends AppCompatActivity {
    TextView bd,name;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTheme(R.style.Theme_AppCompat_Light_NoActionBar);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN
                , WindowManager.LayoutParams.FLAG_FULLSCREEN);

        overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);

        try {
           JSONObject data= new fileDAO().getDataOB("dataUser.json",this);
            setContentView(R.layout.profile_user);
            bd= findViewById(R.id.birthday);
            name = findViewById(R.id.fullname);
            String fullname = data.getString("fullname");
            String birthday = data.getString("birthday");
            bd.setText(birthday);
            name.setText(fullname);
        } catch (JSONException | FileNotFoundException e) {
            setContentView(R.layout.frp_layout);
            FragmentManager fm1 = getFragmentManager();
            FragmentTransaction fragmentTransaction1 = fm1.beginTransaction();
            fragmentTransaction1.replace(R.id.frameLayoutProfile,new loginFragment(profileActivity.this));
            fragmentTransaction1.commit();
            e.printStackTrace();
        }


    }
}