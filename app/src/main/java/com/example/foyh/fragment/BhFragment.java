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
import android.widget.NumberPicker;
import android.widget.TextView;

import androidx.appcompat.widget.LinearLayoutCompat;

import com.example.foyh.R;
import com.example.foyh.activity.MainActivity;
import com.example.foyh.testui.Data.service.DataServiceMethod;
import org.json.JSONArray;
import org.json.JSONException;



@SuppressLint("ValidFragment")
public class BhFragment extends Fragment {
    View view;
    int[] viewbh = {114,114,114,114,114,114,114,114,114,114};
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
        ImageView img1,img2,img3,img4,img5,img6,img7,img8,img9,img10;
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

        Button btnPre = view.findViewById(R.id.pre_button);
        LinearLayout li = view.findViewById(R.id.linear1);
        Animation left = AnimationUtils.loadAnimation(mContext, R.anim.inleft);
        Animation right = AnimationUtils.loadAnimation(mContext, R.anim.inright);
        li.startAnimation(right);
        btn.startAnimation(left);
        btnPre.startAnimation(right);




        new CountDownTimer(1000, 100) {
            public void onTick(long millisUntilFinished) {

            }
            public void onFinish() {
                Thread.currentThread();
                FragmentManager fm = getFragmentManager();
                FragmentTransaction fragmentTransaction = fm.beginTransaction();
                String nc="";
                nc="Chọn các biểu hiện bạn\n thường gặp gần mùa dâu\n và mùa trứng !";
                fragmentTransaction.replace(R.id.frameLayout1,new QsFragment(nc));
                fragmentTransaction.commit();
            }
        }.start();
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
        bh1.put(viewbh[9]);
        bh2.put(viewbh[6]);
        bh2.put(viewbh[7]);
        bh2.put(viewbh[8]);

        bhdt.put(bh);
        bhdt.put(bh1);
        bhdt.put(bh2);

        bhrt.put(bh);
        bhrt.put(bh1);
        bhrt.put(bh2);
        Log.d("data Fist",data.toString());
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