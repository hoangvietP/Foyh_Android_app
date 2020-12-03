package com.example.foyh.fragment;

import android.annotation.SuppressLint;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Context;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.example.foyh.R;
import com.example.foyh.testui.Data.classDT.fileDAO;
import com.example.foyh.testui.Data.service.DataServiceMethod;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.FileNotFoundException;

@SuppressLint("ValidFragment")
public class ValuebhFragment extends Fragment {
    View view;
    int[] viewbh = {10,10,10,10,10,10,10};
    JSONObject stt=null;
    JSONArray sttv = null;
    JSONArray sttic = null;
    String conten="";
    private Context mContext;
    public ValuebhFragment(JSONObject stt) throws JSONException {
        this.stt=stt;
        this.conten= stt.getString("st");
        this.sttv = stt.getJSONArray("sttv");
        this.sttic = stt.getJSONArray("stt");
        for (int i=0;i<=sttv.length()-1;i++){
            this.viewbh[sttv.getInt(i)]= 1;
        }
    }
    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mContext=context;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.bh, container, false);
        ImageView nm,dl,dcl,tn,ca,mm,db;
        Button btn = this.view.findViewById(R.id.next_button1);
        nm =  this.view.findViewById(R.id.nm);
        dl =  this.view.findViewById(R.id.dl);
        dcl =  this.view.findViewById(R.id.dcl);
        tn =  this.view.findViewById(R.id.tn);
        ca =  this.view.findViewById(R.id.ca);
        db =  this.view.findViewById(R.id.db);
        mm =  this.view.findViewById(R.id.mm);

        Button btnPre = view.findViewById(R.id.pre_button);
        LinearLayout li = view.findViewById(R.id.linear1);
        Animation left = AnimationUtils.loadAnimation(mContext, R.anim.inleft);
        Animation right = AnimationUtils.loadAnimation(mContext, R.anim.inright);
        li.startAnimation(right);


        nm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (viewbh[0]==10) {
                    nm.setImageResource(R.drawable.iconclick6);
                    viewbh[0]=0;
                }else {
                    nm.setImageResource(R.drawable.icnotification1);
                    viewbh[0]=10;
                }
            }
        });
        db.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (viewbh[4]==10) {
                    db.setImageResource(R.drawable.iconclick9);
                    viewbh[4]=1;
                }else {
                    db.setImageResource(R.drawable.icnotification10);
                    viewbh[4]=10;
                }
            }
        });
        dl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (viewbh[5]==10) {
                    dl.setImageResource(R.drawable.iconclick3);
                    viewbh[5]=5;
                }else {
                    dl.setImageResource(R.drawable.icnotification6);
                    viewbh[5]=10;
                }
            }
        });
        dcl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (viewbh[6]==10) {
                    dcl.setImageResource(R.drawable.iconclick7);
                    viewbh[6]=3;
                }else {
                    dcl.setImageResource(R.drawable.icnotification9);
                    viewbh[6]=10;
                }
            }
        });
        mm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (viewbh[1]==10) {
                    mm.setImageResource(R.drawable.iconclick1);
                    viewbh[1]=1;
                }else {
                    mm.setImageResource(R.drawable.icnotification2);
                    viewbh[1]=10;
                }
            }
        });
        tn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (viewbh[2]==10) {
                    tn.setImageResource(R.drawable.iconclick10);
                    viewbh[2]=2;
                }else {
                    tn.setImageResource(R.drawable.icnotification8);
                    viewbh[2]=10;
                }
            }
        });
        ca.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (viewbh[3]==10) {
                    ca.setImageResource(R.drawable.iconclick11);
                    viewbh[3]=3;
                }else {
                    ca.setImageResource(R.drawable.icnotification4);
                    viewbh[3]=10;
                }
            }
        });
        Button cnbh = view.findViewById(R.id.cnbh);
        cnbh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                JSONArray bh = new JSONArray();
                for (int i=0;i<=viewbh.length-1;i++){
                    if (viewbh[i]!=10){
                        bh.put(i);
                    }

                }
                try {
                    new DataServiceMethod().saveStt(mContext,sttic,bh,conten);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });

        return view;
    }



}