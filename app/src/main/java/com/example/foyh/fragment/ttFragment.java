package com.example.foyh.fragment;

import android.annotation.SuppressLint;
import android.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.foyh.R;
import com.example.foyh.testui.Data.classDT.fileDAO;
import com.example.foyh.testui.Data.service.DataServiceMethod;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.FileNotFoundException;

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



        try {
            doin();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }


        return view;
    }
    public void doin() throws FileNotFoundException, JSONException {
        fileDAO file= new fileDAO();
        DataServiceMethod dataServiceMethod = new DataServiceMethod();

        JSONObject obj= null;
        obj = file.getData("thisMonthData.json","data",mContext);
        String dateUpdate= obj.getString("dayupdate");
        JSONArray ard=obj.getJSONArray("dataday");
        JSONArray dt = ard;
        int day = dt.getInt(0);
        int month =dt.getInt(1);
        int longMo = ard.getInt(2);
        int longdt= ard.getInt(3);
        JSONArray trt = ard.getJSONArray(4);


        JSONObject ob1=  file.getData("monthData.json","dataM",mContext);
        JSONArray ardt = ob1.getJSONArray("data");// arr chua cac obj data cua cac thang
        JSONObject artd1=ardt.getJSONObject(ardt.length()-1);// obj data cua thang nay
        JSONArray ardt1= artd1.getJSONArray("dataday");
        int lm= ardt1.getInt(1);
        int ldt= ardt1.getInt(2);

        JSONArray rttt = ardt1.getJSONArray(3);
        String mt="";
        String md = "";
        if (rttt.getInt(0)==0){
            mt= "Mùa trứng: "+dataServiceMethod.dateSetup(dateUpdate,trt.getInt(0))+" - "+dataServiceMethod.dateSetup(dateUpdate,trt.getInt(1));
        }else {
            mt= "Mùa trứng: "+dataServiceMethod.dateSetup(dateUpdate,rttt.getInt(0))+" - "+dataServiceMethod.dateSetup(dateUpdate,rttt.getInt(1));
        }

        if (lm==0){
            md= "Mùa dâu: "+dataServiceMethod.dateSetup(dateUpdate,longMo)+" - "+dataServiceMethod.dateSetup(dateUpdate,longMo+longdt);
        }else {
            md="Hành kinh ngày: " + String.valueOf(day - lm + 1);;
        }

        JSONObject ob=  file.getData("stt.json","data",mContext);
//        JSONArray stt = ob.getJSONArray("stt");
//        JSONArray sttv = ob.getJSONArray("sttv");
//        String h = ob.getString("st");
        String nn1 = ob.getString("nn");


        this.mt.setText(mt);
        this.md.setText(md);
        at.setText(new DataServiceMethod().dateSetup(dateUpdate,1)
                +" - "+new DataServiceMethod().dateSetup(dateUpdate,trt.getInt(0)-6)+"\n Và "+
                new DataServiceMethod().dateSetup(dateUpdate,trt.getInt(0)+6)+" - "+new DataServiceMethod().dateSetup(dateUpdate,longMo));
        kat.setText( new DataServiceMethod().dateSetup(dateUpdate,trt.getInt(0)-5)+" - "+new DataServiceMethod().dateSetup(dateUpdate,trt.getInt(0)+5));
       if(nn1.equals("Ngày không an toàn")){
           den.setImageResource(R.drawable.dx);

       }if (nn1.equals("Ngày an toàn")){
            den.setImageResource(R.drawable.dd);
        }

       this.nn.setText(nn1);
    }
}
