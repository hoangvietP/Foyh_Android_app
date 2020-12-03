package com.example.foyh.fragment;

import android.annotation.SuppressLint;
import android.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.NumberPicker;
import android.widget.TextView;

import com.example.foyh.R;

@SuppressLint("ValidFragment")
public class QsFragment extends Fragment {
    View view;
    View view1;
    String qs="";
    int l=0;
    private Context mContext;
    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mContext=context;
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        if (!qs.equals("loss")){
            view = inflater.inflate(R.layout.quesition, container, false);
            TextView tv = view.findViewById(R.id.quessition);
            tv.setText(qs);
            l=1;
            Animation left = AnimationUtils.loadAnimation(mContext, R.anim.in_left_zoom);
            view.startAnimation(left);
        }
        return view;
    }

    public QsFragment(String qs){
        this.qs=qs;

    }
}
