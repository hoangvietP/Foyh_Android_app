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

@SuppressLint("ValidFragment")
public class FistFragment extends Fragment {
    View view;
    NumberPicker np,np1;
    int[] rt = new int[2];
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.step1, container, false);
        np = view.findViewById(R.id.np);
        np1 = view.findViewById(R.id.np1);
        if (stt==0){
            np.setMinValue(1);
            np.setMaxValue(31);
            np1.setMinValue(1);
            np1.setMaxValue(12);

        }else {
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
            }
        });

        np1.setOnValueChangedListener(new NumberPicker.OnValueChangeListener() {
            @Override
            public void onValueChange(NumberPicker picker, int oldVal, int newVal){
                rt[1]=newVal;
                if (newVal==0){
                    rt[1]=1;
                }
            }
        });
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (stt==0) {
                    FragmentManager fm = getFragmentManager();
                    FragmentTransaction fragmentTransaction = fm.beginTransaction();
                    fragmentTransaction.replace(R.id.frameLayout, new SecondFragment());
                    Fragment fr = new QsFragment("Chu kỳ kinh nguyệt của bạn \n thường kéo dài bao lâu ?");
                    fragmentTransaction.replace(R.id.frameLayout1, fr);
                    fragmentTransaction.commit();
                }else {
                    FragmentManager fm = getFragmentManager();
                    FragmentTransaction fragmentTransaction = fm.beginTransaction();
                    fragmentTransaction.replace(R.id.frameLayout, new BhFragment());
                    Fragment fr = new QsFragment("Hãy chọn 3 biểu hiện gần\n chu kỳ kinh nguyệt của bạn");
                    fragmentTransaction.replace(R.id.frameLayout1, fr);
                    fragmentTransaction.commit();
                }
            }
        });

        return view;
    }
    int stt=0;
    public FistFragment(int stt){
        this.stt=stt;
    }
}