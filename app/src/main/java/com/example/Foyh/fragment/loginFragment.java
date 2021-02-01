package com.example.Foyh.fragment;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Context;
import android.content.Intent;
import android.database.CursorIndexOutOfBoundsException;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.Foyh.R;
import com.example.Foyh.activity.step1;
import com.example.Foyh.testui.Data.classDT.DataUser;
import com.example.Foyh.testui.Data.classDT.DatabaseHandler;
import com.example.Foyh.testui.ortherThread.Login;

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
        Button bq = view.findViewById(R.id.btn);
        dk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentManager fm1 = getFragmentManager();
                FragmentTransaction fragmentTransaction1 = fm1.beginTransaction();
                fragmentTransaction1.replace(R.id.frameLayoutProfile,new registerFragment(activity));
                fragmentTransaction1.commit();
            }
        });
        bq.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Boolean us=true;
                try {
                    DataUser dataUser = new DatabaseHandler(mContext).getDataUser(1);
                    if (dataUser.getToken().equals("none")){
                        us=false;
                    }
                }catch (CursorIndexOutOfBoundsException e){
                    us=false;
                }

                if (us==false) {
                    Intent intent = new Intent(mContext, step1.class);
                    startActivity(intent);
                }
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
                    new Login(mContext,ob,activity).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, "http://foyh.tk/api/login");
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
