package com.example.foyh.fragment;

import android.annotation.SuppressLint;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.NumberPicker;
import android.widget.TextView;
import com.example.foyh.R;
import com.example.foyh.activity.MainActivity;
import com.example.foyh.testui.Data.service.DataServiceMethod;
import org.json.JSONArray;
import org.json.JSONException;



@SuppressLint("ValidFragment")
public class BhFragment extends Fragment {
    View view;
    int[] viewbh = {0,0,0,0,0,0,0};
    JSONArray data= new JSONArray();

    private Context mContext;
    public BhFragment(JSONArray data){
        this.data=data;
    }
    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mContext=context;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.step4, container, false);
        ImageView nm,dl,dcl,tn,ca,mm,db;
        Button btn = this.view.findViewById(R.id.next_button1);
        nm =  this.view.findViewById(R.id.nm);
        dl =  this.view.findViewById(R.id.dl);
        dcl =  this.view.findViewById(R.id.dcl);
        tn =  this.view.findViewById(R.id.tn);
        ca =  this.view.findViewById(R.id.ca);
        db =  this.view.findViewById(R.id.db);
        mm =  this.view.findViewById(R.id.mm);

        nm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (viewbh[0]==0) {
                    nm.setImageResource(R.drawable.iconclick6);
                    viewbh[0]=1;
                }else {
                    nm.setImageResource(R.drawable.icnotification1);
                    viewbh[0]=0;
                }
            }
        });
        db.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (viewbh[1]==0) {
                    db.setImageResource(R.drawable.iconclick9);
                    viewbh[1]=2;
                }else {
                    db.setImageResource(R.drawable.icnotification10);
                    viewbh[1]=0;
                }
            }
        });
        dl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (viewbh[2]==0) {
                    dl.setImageResource(R.drawable.iconclick3);
                    viewbh[2]=3;
                }else {
                    dl.setImageResource(R.drawable.icnotification6);
                    viewbh[2]=0;
                }
            }
        });
        dcl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (viewbh[3]==0) {
                    dcl.setImageResource(R.drawable.iconclick7);
                    viewbh[3]=4;
                }else {
                    dcl.setImageResource(R.drawable.icnotification9);
                    viewbh[3]=0;
                }
            }
        });
        mm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (viewbh[4]==0) {
                    mm.setImageResource(R.drawable.iconclick1);
                    viewbh[4]=5;
                }else {
                    mm.setImageResource(R.drawable.icnotification2);
                    viewbh[4]=0;
                }
            }
        });
        tn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (viewbh[5]==0) {
                    tn.setImageResource(R.drawable.iconclick10);
                    viewbh[5]=6;
                }else {
                    tn.setImageResource(R.drawable.icnotification8);
                    viewbh[5]=0;
                }
            }
        });
        ca.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (viewbh[6]==0) {
                    ca.setImageResource(R.drawable.iconclick11);
                    viewbh[6]=7;
                }else {
                    ca.setImageResource(R.drawable.icnotification4);
                    viewbh[6]=0;
                }
            }
        });
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onEndGetFistData();
            }
        });
        return view;
    }
    @SuppressLint("LongLogTag")
    public void onEndGetFistData(){
        DataServiceMethod dt = new DataServiceMethod();
        JSONArray  bh = new JSONArray();
        JSONArray bhdt = new JSONArray();
        JSONArray bhdt1 = new JSONArray();
        JSONArray bhrt = new JSONArray();
        JSONArray  bh1 = new JSONArray();
        JSONArray  bh2 = new JSONArray();
        bh.put(viewbh[0]);
        bh.put(viewbh[1]);
        bh.put(viewbh[2]);
        bh1.put(viewbh[3]);
        bh1.put(viewbh[4]);
        bh1.put(viewbh[5]);
        bh2.put(viewbh[6]);
        bh2.put(114);
        bh2.put(114);

        bhdt.put(bh);
        bhdt.put(bh1);
        bhdt.put(bh2);

        bhrt.put(bh);
        bhrt.put(bh1);
        bhrt.put(bh2);
        Log.d("fist data user :line172 BhFragment",bhdt.toString()+"\n"+bhrt.toString()+"\n"+data.toString());
        try {
            JSONArray arr = (JSONArray) data.get(0);
            JSONArray arr1 = (JSONArray) data.get(3);
            dt.setData(0,mContext,dt.getYear()+"-"+arr.get(1)+"-"+arr.get(0),  Integer.parseInt(String.valueOf(arr1.get(0)))
                    ,Integer.parseInt(String.valueOf(arr1.get(1)))
                    ,Integer.parseInt(String.valueOf(data.get(1))),0,0,1,
                    Integer.parseInt(String.valueOf(data.get(2))),bhrt,bhdt,0);
            dt.saveData(mContext,0,0,0,0,bhdt1,3);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        Intent intent = new Intent(mContext, MainActivity.class);
        startActivity(intent);
    }
}