package com.example.foyh.fragment;

import android.annotation.SuppressLint;
import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.NumberPicker;
import android.widget.TextView;

import com.example.foyh.R;

import org.json.JSONArray;

@SuppressLint("ValidFragment")
public class QsFragment extends Fragment {
    View view;
    String qs="";

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        NumberPicker np;
        view = inflater.inflate(R.layout.quesition, container, false);
        TextView tv = view.findViewById(R.id.quessition);
        tv.setText(qs);
        return view;
    }

    public QsFragment(String qs){
        this.qs=qs;
    }
}
