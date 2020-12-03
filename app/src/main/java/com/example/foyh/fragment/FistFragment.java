package com.example.foyh.fragment;

import android.annotation.SuppressLint;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Context;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.NumberPicker;
import android.widget.TextView;

import androidx.appcompat.widget.LinearLayoutCompat;

import com.example.foyh.R;
import com.example.foyh.testui.Data.service.DataServiceMethod;

import org.json.JSONArray;

@SuppressLint("ValidFragment")
public class FistFragment extends Fragment {
    View view;
    int stt=0;
    NumberPicker np,np1;
    JSONArray data = new JSONArray();
    DataServiceMethod dt = new DataServiceMethod();
    int[] rt = new int[2];

    private Context mContext;
    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mContext=context;
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.step1, container, false);
        np = view.findViewById(R.id.np);
        np1 = view.findViewById(R.id.np1);
        Button btn = view.findViewById(R.id.next_button);
        // anim
        LinearLayoutCompat ln = view.findViewById(R.id.linear1);
        Animation left = AnimationUtils.loadAnimation(mContext, R.anim.inleft);
        Animation right = AnimationUtils.loadAnimation(mContext, R.anim.inright);
        ln.startAnimation(left);
        btn.startAnimation(right);
        new CountDownTimer(1000, 100) {
            public void onTick(long millisUntilFinished) {

            }
            public void onFinish() {
                Thread.currentThread();
                FragmentManager fm = getFragmentManager();
                FragmentTransaction fragmentTransaction = fm.beginTransaction();
                String nc="";
                if (stt==0){
                    nc="Mùa dâu gần nhấn của bạn \n bắt đầu vào ngày nào ?";
                }if (stt==1){
                    nc="Bạn thường rụng trứng\n trong khoảng ngày thứ \nbao nhiêu của chu kỳ";
                }
                fragmentTransaction.replace(R.id.frameLayout1,new QsFragment(nc));
                fragmentTransaction.commit();
            }
        }.start();
        if (stt==0){
            np.setMinValue(1);
            int[] monthD= new int[]{31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};
            np.setMaxValue(monthD[dt.getMonth()-1]);
            np.setValue(Integer.parseInt(dt.getDay()));


            np1.setMinValue(dt.getMonth()-2);
            int o=dt.getMonth()+2;
                if (dt.getMonth()+1==13){
                    np1.setMinValue(2);
                    np1.setMaxValue(12);
                }else if (dt.getMonth()+2==13){
                    np1.setMinValue(1);
                    np1.setMaxValue(12);
                }else {
                np1.setMaxValue(dt.getMonth()+2);
            }

            np1.setValue(dt.getMonth());


        }else {
            TextView one = view.findViewById(R.id.one);
            TextView tow = view.findViewById(R.id.tow);
            one.setText("Từ ngày");tow.setText("Đến ngày");
            np.setMinValue(10);
            np.setMaxValue(20);
            np1.setMinValue(8);
            np1.setMaxValue(25);
        }
        np1.setWrapSelectorWheel(true);
        np.setWrapSelectorWheel(true);

        np.setOnValueChangedListener(new NumberPicker.OnValueChangeListener() {
            @Override
            public void onValueChange(NumberPicker picker, int oldVal, int newVal){
                rt[0]=newVal;
                if (newVal==0){
                    rt[0]=1;
                }
                if (stt!=0){
                    if (newVal==0){
                        rt[0]=18;
                    }else {
                        rt[0]=newVal;
                    }

                    np1.setMinValue(newVal+2);
                    np1.setMaxValue(newVal+6);
                    np1.setValue(20);
                }

            }
        });

        np1.setOnValueChangedListener(new NumberPicker.OnValueChangeListener() {
            @Override
            public void onValueChange(NumberPicker picker, int oldVal, int newVal){
                rt[1]=newVal;
                if (newVal==0){
                    rt[1]=1;
                }
                if (stt!=0){
                    if (newVal==0){
                        rt[1]=20;
                    }else {
                        rt[1] = newVal;
                    }
                }

            }
        });
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (stt==0) {
                    FragmentManager fm = getFragmentManager();
                    FragmentTransaction fragmentTransaction = fm.beginTransaction();
                    SecondFragment sc = new SecondFragment(0);

                    if (rt[0]==0){
                        rt[0]= Integer.parseInt(dt.getDay());
                    }
                    if (rt[1]==0){
                        rt[1]= dt.getMonth();
                    }
                    sc.setDt(rt[0],rt[1]);
                    fragmentTransaction.replace(R.id.frameLayout1, new QsFragment("loss"));
                    fragmentTransaction.replace(R.id.frameLayout, sc);
                    fragmentTransaction.commit();
                }else {
                    JSONArray rtt = new JSONArray();
                    if (rt[0]==0){
                        rt[0]= 18;
                    }
                    if (rt[1]==0){
                        rt[1]=20;
                    }
                    rtt.put(rt[0]);
                    rtt.put(rt[1]);
                    data.put(rtt);
                    FragmentManager fm = getFragmentManager();
                    FragmentTransaction fragmentTransaction = fm.beginTransaction();
                    fragmentTransaction.replace(R.id.frameLayout, new BhFragment(data));
                    Fragment fr = new QsFragment("loss");
                    fragmentTransaction.replace(R.id.frameLayout1, fr);
                    fragmentTransaction.commit();
                }
            }
        });

        return view;
    }

    public FistFragment(int stt,JSONArray data){
        this.stt=stt;
        this.data=data;
    }
}