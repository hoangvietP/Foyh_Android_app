package com.example.foyh.fragment;
import android.annotation.SuppressLint;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.NumberPicker;
import android.widget.Toast;

import com.example.foyh.R;

import org.json.JSONArray;

@SuppressLint("ValidFragment")
public class SecondFragment extends Fragment {
    private View view;
    private int rt=0;
    private int k=1;
    private JSONArray data = new JSONArray();
    public void setDt(int day,int month){
        JSONArray ar = new JSONArray();
        ar.put(day);
        ar.put(month);
        data.put(ar);
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        NumberPicker np;
        view = inflater.inflate(R.layout.step2, container, false);
        np = view.findViewById(R.id.number);
        np.setMinValue(1);
        np.setMaxValue(50);
        np.setWrapSelectorWheel(true);
        Button btn = view.findViewById(R.id.next_button1);
        np.setOnValueChangedListener(new NumberPicker.OnValueChangeListener() {
            @Override
            public void onValueChange(NumberPicker picker, int oldVal, int newVal){
                rt=newVal;
                if (newVal==0){
                    rt=1;
                }
            }
        });
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentManager fm = getFragmentManager();
                FragmentTransaction fragmentTransaction = fm.beginTransaction();
                String qs="";
                data.put(rt);
                switch (k){
                    case 0: qs="Mùa dâu (kỳ kinh nguyệt) của bạn \n thường kéo dài bao lâu ?";

                    break;
                    case 1: qs="Một chu kỳ của bạn \n thường kéo dài bao nhiêu ngày ?";

                        break;
                    case 2: qs="Bạn thường rụng trứng \n  từ ngày bao nhiêu đến... ? ";
                        FragmentManager fm1 = getFragmentManager();
                        FragmentTransaction fragmentTransaction1 = fm1.beginTransaction();
                        fragmentTransaction1.replace(R.id.frameLayout,new FistFragment(1,data));
                        fragmentTransaction1.commit();
                        break;
                }
                Fragment fr = new QsFragment(qs);
                fragmentTransaction.replace(R.id.frameLayout1,fr);
                fragmentTransaction.commit();
                k++;
            }
        });
        return view;
    }

}
