package com.example.foyh.fragment;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.foyh.R;
import com.example.foyh.testui.ortherThread.Login;
import com.example.foyh.testui.ortherThread.SynsSave;

import org.json.JSONException;
import org.json.JSONObject;

@SuppressLint("ValidFragment")
public class loginFragment extends Fragment {
    View view;
    private Context mContext;
    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mContext=context;
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.acount, container, false);
        EditText us= view.findViewById(R.id.user);
        EditText ps= view.findViewById(R.id.pass);
        Button dn = view.findViewById(R.id.add);
        Button dk = view.findViewById(R.id.btnCancel);
        dk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentManager fm1 = getFragmentManager();
                FragmentTransaction fragmentTransaction1 = fm1.beginTransaction();
                fragmentTransaction1.replace(R.id.frameLayoutProfile,new registerFragment(activity));
                fragmentTransaction1.commit();
            }
        });

        dn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String usv = String.valueOf(us.getText());
                String psv = String.valueOf(ps.getText());
                int stt=0;
                if (usv.equals("")){
                    stt=1;
                }
                if (psv.equals("")){
                    stt=1;
                }
                if (stt==1){
                    Toast.makeText(mContext,"Vui lòng điền đầy đủ các mục !",
                            Toast.LENGTH_SHORT).show();
                }else if (stt==0){
                    JSONObject ob= new JSONObject();
                    try {
                        ob.put("username",usv);
                        ob.put("password",psv);
                        Log.d("Login",ob.toString());
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                    new Login(mContext,ob,activity).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, "https://60ae5ea189bf.ngrok.io/api/login");
                }
            }
        });


        return view;
    }
    Activity activity;
    public loginFragment(Activity activity){
        this.activity = activity;

    }
}
