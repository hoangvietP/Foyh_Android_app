package com.example.foyh.fragment;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.NumberPicker;
import android.widget.Toast;

import com.example.foyh.R;
import com.example.foyh.activity.User;
import com.example.foyh.testui.Data.service.DataServiceMethod;
import com.example.foyh.testui.ortherThread.SynsSave;

import org.json.JSONException;
import org.json.JSONObject;

@SuppressLint("ValidFragment")
public class registerFragment extends Fragment {
    EditText fname,us,ps;
    NumberPicker sn;
    Button save,huy;
    int snv =0;
    View view;
    private Context mContext;
    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mContext=context;
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.profile, container, false);
        fname= view.findViewById(R.id.addName);
        sn= view.findViewById(R.id.sn);
        us= view.findViewById(R.id.user);
        ps= view.findViewById(R.id.pass);
        save= view.findViewById(R.id.add);
        huy= view.findViewById(R.id.btnCancel);
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
                    Toast.makeText(mContext,"Vui lòng điền đầy đủ các mục !",
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

                    new SynsSave(mContext,ob,usv,activity).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, "https://60ae5ea189bf.ngrok.io/api/register");
                }
            }
        });
        huy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentManager fm1 = getFragmentManager();
                FragmentTransaction fragmentTransaction1 = fm1.beginTransaction();
                fragmentTransaction1.replace(R.id.frameLayoutProfile,new loginFragment(activity));
                fragmentTransaction1.commit();
            }
        });


        return view;
    }
    Activity activity;

    public registerFragment(Activity activity){
        this.activity = activity;

    }
}
