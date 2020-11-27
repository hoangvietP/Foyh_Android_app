package com.example.foyh.fragment;

import android.annotation.SuppressLint;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.NumberPicker;
import android.widget.TextView;

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
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.step1, container, false);
        np = view.findViewById(R.id.np);
        np1 = view.findViewById(R.id.np1);
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
            np.setMinValue(5);
            np.setMaxValue(20);
            np1.setMinValue(8);
            np1.setMaxValue(25);
        }
        np1.setWrapSelectorWheel(true);
        np.setWrapSelectorWheel(true);
        Button btn = view.findViewById(R.id.next_button);
        np.setOnValueChangedListener(new NumberPicker.OnValueChangeListener() {
            @Override
            public void onValueChange(NumberPicker picker, int oldVal, int newVal){
                rt[0]=newVal;
                if (newVal==0){
                    rt[0]=1;
                }
                if (stt!=0){
                    rt[0]=10;
                    np1.setMinValue(newVal+4);
                    np1.setMaxValue(newVal+8);
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
                    rt[1]=15;
                    np.setMinValue(newVal-4);
                    np.setMaxValue(newVal-8);
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
                    sc.setDt(rt[0],rt[1]);
                    fragmentTransaction.replace(R.id.frameLayout, sc);
                    Fragment fr = new QsFragment("Chu kỳ kinh nguyệt của bạn \n thường kéo dài bao lâu ?");
                    fragmentTransaction.replace(R.id.frameLayout1, fr);
                    fragmentTransaction.commit();
                }else {
                    JSONArray rtt = new JSONArray();
                    rtt.put(rt[0]);
                    rtt.put(rt[1]);
                    data.put(rtt);
                    FragmentManager fm = getFragmentManager();
                    FragmentTransaction fragmentTransaction = fm.beginTransaction();
                    fragmentTransaction.replace(R.id.frameLayout, new BhFragment(data));
                    Fragment fr = new QsFragment("Hãy chọn các biểu hiện \n gần mùa dâu (đến tháng)\n thường thấy của bạn");
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