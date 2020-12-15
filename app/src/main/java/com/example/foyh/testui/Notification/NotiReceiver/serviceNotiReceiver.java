package com.example.foyh.testui.Notification.NotiReceiver;

import android.content.Context;
import android.os.Build;

import androidx.annotation.RequiresApi;


import com.example.foyh.testui.Data.classDT.fileDAO;
import com.example.foyh.testui.Data.service.DataServiceMethod;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.FileNotFoundException;

public class serviceNotiReceiver {
    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    public Boolean getData(int bt, Context context) throws FileNotFoundException, JSONException {
        fileDAO file = new fileDAO();
        JSONObject ob=  file.getData("stt.json","data",context);
        JSONArray stt = ob.getJSONArray("stt");
        JSONArray sttv = ob.getJSONArray("sttv");
        String h = ob.getString("st");
        String nn = ob.getString("nn");
        boolean st=false;
        for (int i=0;i<=sttv.length()-1;i++){
            if (sttv.getInt(i)==stt.getInt(bt)){
                st=true;
                continue;
            }
        }
        if (st==true){
            for (int i=0;i<=sttv.length()-1;i++){
                if (sttv.getInt(i)==stt.getInt(bt)){
                    sttv.remove(i);
                    continue;
                }
            }
        }else if (st==false){
            sttv.put(stt.getInt(bt));
        }
        new DataServiceMethod().saveStt(context,stt,sttv,h,nn);
        return true;
    }
}
