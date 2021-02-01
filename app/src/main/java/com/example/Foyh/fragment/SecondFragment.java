package com.example.Foyh.fragment;
import android.annotation.SuppressLint;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.NumberPicker;
import android.widget.TextView;

import androidx.appcompat.widget.LinearLayoutCompat;

import com.example.Foyh.R;
import com.example.Foyh.activity.MainActivity;

import org.json.JSONArray;

@SuppressLint("ValidFragment")
public class SecondFragment extends Fragment {
    private View view;
    private int rt=0;
    private int k=0;
    private JSONArray data = new JSONArray();
    public void setDt(int day,int month){
        JSONArray ar = new JSONArray();
        ar.put(day);
        ar.put(month);
        data.put(ar);
    }
    TextView alertText;
    private Context mContext;
    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mContext=context;
    }
    public void setData(JSONArray data){
        this.data= data;
    }
    public SecondFragment(int k){
        this.k =k;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        NumberPicker np;
        view = inflater.inflate(R.layout.step2, container, false);
        np = view.findViewById(R.id.number);
//anim
        alertText = view.findViewById(R.id.textAlert);
        new CountDownTimer(1000, 100) {
            public void onTick(long millisUntilFinished) {

            }
            public void onFinish() {
                Thread.currentThread();
                FragmentManager fm = getFragmentManager();
                FragmentTransaction fragmentTransaction = fm.beginTransaction();
                String nc="";
                if (k==0){
                    alertText.setText("Ngày đèn đỏ (đến tháng) của bạn \n" +
                            "kéo dài trong khoảng bao nhiêu ngày?\n" +
                            "Ví dụ: Tôi thường có kinh trong khoảng 4 ngày!");
                    nc="Trung bình mùa dâu của bạn\n kéo dài bao nhiêu ngày ?";
                }if (k==1){
                    alertText.setText("Một chu kì kinh nguyệt được tính từ \nngày đầu tiên có kinh của tháng trước tới\n ngày đầu tiên có kinh của tháng này.\n" +
                            "\nVí dụ: tháng trước tôi có kinh vào ngày mồng 3\n" +
                            "và tháng này tôi có kinh vào ngày mồng 5 \n" +
                            "vậy 1 chu kì của tôi kéo dài khoảng 32 ngày. ");
                    nc="Một chu kỳ của bạn \n thường kéo dài bao nhiêu ngày ?";
                }
                fragmentTransaction.replace(R.id.frameLayout1,new QsFragment(nc));
                fragmentTransaction.commit();
            }
        }.start();
        Button btn = view.findViewById(R.id.next_button);
        Button btnPre = view.findViewById(R.id.pre_button);
        LinearLayoutCompat li = view.findViewById(R.id.linear1);
        Animation left = AnimationUtils.loadAnimation(mContext, R.anim.inleft);
        Animation right = AnimationUtils.loadAnimation(mContext, R.anim.inright);
        li.startAnimation(right);
        btn.startAnimation(left);
        btnPre.startAnimation(right);

        np.setMinValue(4);
        np.setMaxValue(8);
        np.setValue(5);
        if (k==1) {
            np.setMinValue(25);
            np.setMaxValue(37);
            np.setValue(25);
        }
        np.setWrapSelectorWheel(true);

        np.setOnValueChangedListener(new NumberPicker.OnValueChangeListener() {
            @Override
            public void onValueChange(NumberPicker picker, int oldVal, int newVal){
                rt=newVal;
                if (newVal==0){
                    rt=1;
                }
            }
        });

        btnPre.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(mContext, MainActivity.class);
                startActivity(intent);
            }
        });
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentManager fm = getFragmentManager();
                FragmentTransaction fragmentTransaction = fm.beginTransaction();
                String qs="";
                switch (k){
                    case 0:

                        if (rt==0){
                            rt=5;
                        }
                        data.put(rt);
                        FragmentManager f = getFragmentManager();
                        FragmentTransaction fragmentTransactio = f.beginTransaction();
                        SecondFragment sc= new SecondFragment(1);
                        sc.setData(data);
                        fragmentTransactio.replace(R.id.frameLayout1,new QsFragment("loss"));
                        fragmentTransactio.replace(R.id.frameLayout,sc);
                        fragmentTransactio.commit();
                    break;
                    case 1:
                        if (rt==0){
                            rt=25;
                        }
                        data.put(rt);
                        FragmentManager fm1 = getFragmentManager();
                        FragmentTransaction fragmentTransaction1 = fm1.beginTransaction();
                        fragmentTransaction1.replace(R.id.frameLayout1,new QsFragment("loss"));
                        fragmentTransaction1.replace(R.id.frameLayout,new FistFragment(1,data));
                        fragmentTransaction1.commit();
                        break;
                }
                fragmentTransaction.commit();
                k++;
            }
        });
        return view;
    }

}
