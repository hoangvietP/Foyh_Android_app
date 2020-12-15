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
import android.widget.Toast;

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
        TextView one = view.findViewById(R.id.one);
        TextView two = view.findViewById(R.id.tow);
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
        int[] monthD= new int[]{31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};
        if (stt==0){
            np1.setMinValue(1);

            np1.setMaxValue(Integer.parseInt(dt.getDay()));
            np1.setValue(Integer.parseInt(dt.getDay()));


            String[] nums= {(dt.getMonth()-2)+"",(dt.getMonth()-1)+"",(dt.getMonth())+""};
            int day = Integer.parseInt(dt.getDay());
            if (day+monthD[dt.getMonth()-1]>35) {
                nums=new String[]{(dt.getMonth()-1)+"",(dt.getMonth())+""};
            }

            if (dt.getMonth()==1){
                nums= new String[]{ "11",  "12", "1"};
            }else if (dt.getMonth()==2){
                nums= new String[]{"12",  "1", "2"};
            }

            np.setMaxValue(nums.length-1);
            np.setMinValue(0);
            np.setValue(dt.getMonth());
            np.setWrapSelectorWheel(false);
            np.setDisplayedValues(nums);
            np.setDescendantFocusability(NumberPicker.FOCUS_BLOCK_DESCENDANTS);


        }else {
            np.setMinValue(10);
            np.setMaxValue(20);
            np1.setMinValue(12);
            np1.setMaxValue(25);
             one.setText("Từ ngày thứ");
             two.setText("Đến ngày");
        }
        np.setOnValueChangedListener(new NumberPicker.OnValueChangeListener() {
            @Override
            public void onValueChange(NumberPicker picker, int oldVal, int newVal){
                if (stt==0){
                int day = Integer.parseInt(dt.getDay());
                int daycl=0;
                int month=0;
                if (day+monthD[dt.getMonth()-1]<=35) {
                    daycl = 35 - (day + monthD[dt.getMonth() - 1]);
                    if (newVal == 0) {
                        np1.setMinValue(monthD[dt.getMonth() - 2]-daycl);
                        np1.setMaxValue(monthD[dt.getMonth() - 2]);
                    }else if (newVal==1){
                        np1.setMinValue(1);
                        np1.setMaxValue(monthD[dt.getMonth() - 1]);
                    }else if (newVal==2){
                        np1.setMinValue(1);
                        np1.setMaxValue(day);
                    }
                    if (dt.getMonth()!=1 && dt.getMonth()!=2){
                        if (newVal==0){
                            month=dt.getMonth() - 2;
                        }else if (newVal==1){
                            month=dt.getMonth() - 1;
                        }else if (newVal==2){
                            month=dt.getMonth();
                        }
                    }else if (dt.getMonth()==1){
                        if (newVal==0){
                            month=11;
                        }else if (newVal==1){
                            month=12;
                        }else if (newVal==2){
                            month=1;
                        }
                    }else if (dt.getMonth()==2){
                        if (newVal==0){
                            month=12;
                        }else if (newVal==1){
                            month=1;
                        }else if (newVal==2){
                            month=2;
                        }
                    }
                }else {
                    daycl= 35-day;
                    if (newVal == 0) {
                        np1.setMinValue(monthD[dt.getMonth()-1]-daycl);
                        np1.setMaxValue(monthD[dt.getMonth() - 1]);
                    }else if (newVal==1){
                        np1.setMinValue(1);
                        np1.setMaxValue(day);
                    }
                    if (dt.getMonth()!=1 && dt.getMonth()!=2){
                        if (newVal==0){
                            month=dt.getMonth() - 1;
                        }else if (newVal==1){
                            month=dt.getMonth();
                        }
                    }else if (dt.getMonth()==1){
                        if (newVal==0){
                            month=12;
                        }else if (newVal==1){
                            month=1;
                        }
                    }else if (dt.getMonth()==2){
                        if (newVal==0){
                            month=1;
                        }else if (newVal==1){
                            month=2;
                        }
                    }
                }
                rt[1]=month;
                if (newVal==0){
                    rt[1]=dt.getMonth()-1;
                }if (newVal==1){
                    rt[1]=dt.getMonth();
                }
                }
                if (stt!=0){
                    if (newVal==0){
                        rt[0]=18;
                    }else {
                        rt[0]=newVal;
                    }

                }

            }
        });

        np1.setOnValueChangedListener(new NumberPicker.OnValueChangeListener() {
            @Override
            public void onValueChange(NumberPicker picker, int oldVal, int newVal){
                if (stt==0){
                    rt[0]=newVal;
                    if (newVal==0){
                        rt[0]= Integer.parseInt(dt.getDay());
                    }
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