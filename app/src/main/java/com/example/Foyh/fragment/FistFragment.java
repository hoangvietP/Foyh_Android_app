package com.example.Foyh.fragment;

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

import com.example.Foyh.R;
import com.example.Foyh.testui.Data.service.DataServiceMethod;

import org.json.JSONArray;

@SuppressLint("ValidFragment")
public class FistFragment extends Fragment {
    View view;
    int stt=0;
    TextView alertText;
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
    Animation left;
    @SuppressLint("ResourceAsColor")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.step1, container, false);
        np = view.findViewById(R.id.np);
        np1 = view.findViewById(R.id.np1);
        TextView two = view.findViewById(R.id.tow);
        TextView one = view.findViewById(R.id.one);
        alertText = view.findViewById(R.id.textAlert);
        Button btn = view.findViewById(R.id.next_button);
        // anim
        LinearLayoutCompat ln = view.findViewById(R.id.linear1);
        left = AnimationUtils.loadAnimation(mContext, R.anim.inleft);
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
                    nc="Mùa dâu gần nhất của bạn \n bắt đầu vào ngày nào ?";
                }if (stt==1){
                    nc="Mùa trứng của bạn kéo dài\n trong khoảng ngày thứ \nbao nhiêu của chu kì?";
                }
                fragmentTransaction.replace(R.id.frameLayout1,new QsFragment(nc));
                fragmentTransaction.commit();
            }
        }.start();
        int[] monthD= new int[]{31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};
        if (stt==0){

            alertText.setText("Mùa dâu (những ngày có kinh nguyệt, hay đến tháng). \nVí dụ: tôi bắt đầu đến tháng vào ngày 12/1/2021 ");
            alertText.startAnimation(left);
            np1.setMinValue(1);

            np1.setMaxValue(Integer.parseInt(String.valueOf(monthD[dt.getMonth()-1])));
            np1.setValue(Integer.parseInt(String.valueOf(monthD[dt.getMonth()-1])));
            np.setMaxValue(12);
            np.setMinValue(1);
            np.setValue(dt.getMonth());
            np1.setValue(Integer.parseInt(dt.getDay()));


        }else {
            alertText.setText("Mùa trứng (những ngày có biểu hiện dịch nhờn, đau ngực,ham muốn, đau bụng dưới,vùng sương chậu... ). \nVí dụ: tôi thường rụng trứng,hoăc có biểu hiện trên \n từ ngày thứ 15 đến ngày thứ 20 của chu kì.\n" +
                    "* Nếu không nhớ rõ thì cứ nhấn tiếp theo nhé!");

            np.setMinValue(10);
            np.setMaxValue(20);
            np1.setMinValue(12);
            np1.setMaxValue(25);
            np.setValue(14);
            np1.setValue(18);
             one.setText("Từ ngày thứ");
             two.setText("Đến ngày");
        }
        np.setOnValueChangedListener(new NumberPicker.OnValueChangeListener() {
            @Override
            public void onValueChange(NumberPicker picker, int oldVal, int newVal){
                if (stt==0){
                    rt[1]=newVal;
                    if (newVal==0){
                        rt[1]=dt.getMonth();
                    }

                    if(newVal==12 && dt.getMonth()==1) {
                        np1.setMinValue(1);
                        np1.setMaxValue(monthD[newVal-1]);
                    }else if ((newVal-dt.getMonth())>2 || newVal>dt.getMonth() && newVal!=12){
                        np1.setMaxValue(1);
                        np1.setMinValue(0);
                    }else {
                        np1.setMinValue(1);
                        np1.setMaxValue(monthD[newVal-1]);
                    }
                }
                if (stt!=0){
                    if (newVal==0){
                        rt[0]=14;
                    }else {
                        rt[0]=newVal;
                        np1.setValue(newVal + 4);
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
                        rt[1]=18;
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
                    int check=0;
                    int m = dt.getMonth();
                    int day = Integer.parseInt(dt.getDay());


                    String date="";
                    if (rt[1]==0){
                        rt[1]= dt.getMonth();
                    }
                    if (rt[0]==0){
                        rt[0]=day;
                    }
                    if (m==1 && rt[1]==12){
                        date= (Integer.parseInt(new DataServiceMethod().getYear())-1)+"-"+rt[1]+"-"+rt[0];
                    }else {
                        date=new DataServiceMethod().getYear()+"-"+rt[1]+"-"+rt[0];
                    }
                    Log.d("Date ",date);
                    if ((new DataServiceMethod().getCountDay(date))>37){
                        check=1;
                    }
                    if (rt[1]>dt.getMonth() || rt[0] >  Integer.parseInt(dt.getDay())){
                        check=1;
                    }
                    if (check==1){
                        alertText.setTextColor(R.color.Crimson);
                        alertText.startAnimation(left);
                        alertText.setText("Vui lòng chọn ngày tháng nhỏ hơn hoặc bằng "+m+"/"+day+
                                "\nVà tổng số ngày từ ngày được chọn tới "+m+"/"+day+" nhỏ hơn 38 ngày");
                    }else if (check!=1) {
                        FragmentManager fm = getFragmentManager();
                        FragmentTransaction fragmentTransaction = fm.beginTransaction();
                        SecondFragment sc = new SecondFragment(0);
                        sc.setDt(rt[0], rt[1]);
                        fragmentTransaction.replace(R.id.frameLayout1, new QsFragment("loss"));
                        fragmentTransaction.replace(R.id.frameLayout, sc);
                        fragmentTransaction.commit();
                    }
                }else {
                    if (rt[1]==0){
                        rt[1]=18;
                    }
                    if (rt[0]==0){
                        rt[0]=14;
                    }
                    if (rt[0]<rt[1]) {
                        JSONArray rtt = new JSONArray();
                        rtt.put(rt[0]);
                        rtt.put(rt[1]);
                        data.put(rtt);
                        FragmentManager fm = getFragmentManager();
                        FragmentTransaction fragmentTransaction = fm.beginTransaction();
                        fragmentTransaction.replace(R.id.frameLayout, new BhFragment(data));
                        Fragment fr = new QsFragment("loss");
                        fragmentTransaction.replace(R.id.frameLayout1, fr);
                        fragmentTransaction.commit();
                    }else {
                        alertText.startAnimation(left);
                        alertText.setTextColor(R.color.Crimson);
                        alertText.setText("Vui lòng chọn ngày bắt đầu rụng trứng nhỏ hơn ngày kết thúc!");
                    }
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