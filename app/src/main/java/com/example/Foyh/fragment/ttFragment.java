package com.example.Foyh.fragment;

import android.annotation.SuppressLint;
import android.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.Foyh.R;

@SuppressLint("ValidFragment")
public class ttFragment extends Fragment {
    View view;
    ImageView den;
    TextView md,mt,at,kat,nn;

    private Context mContext;
    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mContext=context;
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.chitiet, container, false);
         md = view.findViewById(R.id.md);
         mt = view.findViewById(R.id.mt);
        at= view.findViewById(R.id.at);
        kat = view.findViewById(R.id.kat);
        den= view.findViewById(R.id.den);
        nn = view.findViewById(R.id.nn);


//
//        try {
//            doin();
//        } catch (FileNotFoundException e) {
//            e.printStackTrace();
//        } catch (JSONException e) {
//            e.printStackTrace();
//        }


        return view;
    }

}
