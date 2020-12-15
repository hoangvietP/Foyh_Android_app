package com.example.foyh.fragment;

import android.annotation.SuppressLint;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.core.content.ContextCompat;

import com.example.foyh.R;
import com.example.foyh.activity.User;
import com.example.foyh.testui.Data.classDT.fileDAO;
import com.example.foyh.testui.Data.service.DataServiceMethod;
import com.example.foyh.testui.Notification.ExampleService;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.FileNotFoundException;

@SuppressLint("ValidFragment")
public class ValuebhFragment extends Fragment {
    View view;
    int[] viewbh = {114,114,114,114,114,114,114,114,114,114};
    JSONObject stt=null;
    JSONArray sttv = null;
    JSONArray sttic = null;
    String nn="";
    String conten="";
    private Context mContext;
    public ValuebhFragment(JSONObject stt) throws JSONException {
        this.stt=stt;
        this.conten= stt.getString("st");
        this.nn= stt.getString("nn");
        this.sttv = stt.getJSONArray("sttv");
        this.sttic = stt.getJSONArray("stt");
        String log="";
        for (int i=0;i<=sttv.length()-1;i++){
            this.viewbh[sttv.getInt(i)]= sttv.getInt(i);
        }
    }
    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mContext=context;
    }
    ImageView img1,img2,img3,img4,img5,img6,img7,img8,img9,img10;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.bh, container, false);
        Button btn = this.view.findViewById(R.id.next_button1);
        img1 =  this.view.findViewById(R.id.image_view_expanded1);
        img2 =  this.view.findViewById(R.id.image_view_expanded2);
        img3 =  this.view.findViewById(R.id.image_view_expanded3);
        img4 =  this.view.findViewById(R.id.image_view_expanded4);
        img5 =  this.view.findViewById(R.id.image_view_expanded5);
        img6 =  this.view.findViewById(R.id.image_view_expanded6);
        img7 =  this.view.findViewById(R.id.image_view_expanded7);
        img8 =  this.view.findViewById(R.id.image_view_expanded8);
        img9 =  this.view.findViewById(R.id.image_view_expanded9);
        img10 =  this.view.findViewById(R.id.image_view_expanded10);

        LinearLayout li = view.findViewById(R.id.linear1);
        Animation right = AnimationUtils.loadAnimation(mContext, R.anim.inright);
        li.startAnimation(right);

        try {
            setview();
        } catch (JSONException e) {
            e.printStackTrace();
        }
        img1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (viewbh[0]==114) {
                    img1.setImageResource(R.drawable.iconclick111);
                    viewbh[0]=1;
                }else {
                    img1.setImageResource(R.drawable.icnotification111);
                    viewbh[0]=114;
                }
            }
        });
        img2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (viewbh[1]==114) {
                    img2.setImageResource(R.drawable.iconclick110);
                    viewbh[1]=2;
                }else {
                    img2.setImageResource(R.drawable.icnotification110);
                    viewbh[1]=114;
                }
            }
        });
        img3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (viewbh[2]==114) {
                    img3.setImageResource(R.drawable.iconclick11);
                    viewbh[2]=3;
                }else {
                    img3.setImageResource(R.drawable.icnotification4);
                    viewbh[2]=114;
                }
            }
        });
        img4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (viewbh[3]==114) {
                    img4.setImageResource(R.drawable.iconclick8);
                    viewbh[3]=4;
                }else {
                    img4.setImageResource(R.drawable.icnotification2);
                    viewbh[3]=114;
                }
            }
        });
        img5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (viewbh[4]==114) {
                    img5.setImageResource(R.drawable.iconclick2);
                    viewbh[4]=5;
                }else {
                    img5.setImageResource(R.drawable.icnotification5);
                    viewbh[4]=114;
                }
            }
        });
        img6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (viewbh[5]==114) {
                    img6.setImageResource(R.drawable.iconclick4);
                    viewbh[5]=6;
                }else {
                    img6.setImageResource(R.drawable.icnotification7);
                    viewbh[5]=114;
                }
            }
        });
        img7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (viewbh[6]==114) {
                    img7.setImageResource(R.drawable.iconclick3);
                    viewbh[6]=7;
                }else {
                    img7.setImageResource(R.drawable.icnotification6);
                    viewbh[6]=114;
                }
            }
        });
        img8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (viewbh[7]==114) {
                    img8.setImageResource(R.drawable.iconclick9);
                    viewbh[7]=8;
                }else {
                    img8.setImageResource(R.drawable.icnotification10);
                    viewbh[7]=114;
                }
            }
        });
        img9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (viewbh[8]==114) {
                    img9.setImageResource(R.drawable.iconclick6);
                    viewbh[8]=9;
                }else {
                    img9.setImageResource(R.drawable.icnotification1);
                    viewbh[8]=114;
                }
            }
        });
        img10.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (viewbh[9]==114) {
                    img10.setImageResource(R.drawable.iconclick10);
                    viewbh[9]=10;
                }else {
                    img10.setImageResource(R.drawable.icnotification8);
                    viewbh[9]=114;
                }
            }
        });

        Button cnbh = view.findViewById(R.id.cnbh);
        cnbh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                JSONArray bh = new JSONArray();
                for (int i=0;i<=viewbh.length-1;i++){
                    if (viewbh[i]!=114){
                        bh.put(i);
                    }

                }
                try {
                    new DataServiceMethod().saveStt(mContext,sttic,bh,conten,nn);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                serviceIntent= new Intent(mContext, ExampleService.class);
                ContextCompat.startForegroundService(mContext, serviceIntent);
                Toast.makeText(mContext,"Đã cập nhật biểu hiện!",
                        Toast.LENGTH_SHORT).show();
            }
        });

        return view;
    }

        Intent serviceIntent;

    public void setview() throws JSONException {
        for (int i=0;i<=sttv.length()-1;i++){

            switch (sttv.getInt(i)){
                case 0 :
                    img1.setImageResource(R.drawable.iconclick111);
                    break;
                case 1 :
                    img2.setImageResource(R.drawable.iconclick110);
                    break;
                case 2 :
                    img3.setImageResource(R.drawable.iconclick11);
                    break;
                case 3 :
                    img4.setImageResource(R.drawable.iconclick1);
                    break;
                case 4 :
                    img5.setImageResource(R.drawable.iconclick2);
                    break;
                case 5 : img6.setImageResource(R.drawable.iconclick4);
                    break;
                case 6 : img7.setImageResource(R.drawable.iconclick3);
                    break;
                case 7 : img8.setImageResource(R.drawable.iconclick9);
                    break;
                case 8 : img9.setImageResource(R.drawable.iconclick6);
                    break;
                case 9 : img10.setImageResource(R.drawable.iconclick10);
                    break;
                case 12 : img1.setImageResource(R.drawable.iconclick5);
                    break;

            }



        }
    }

}