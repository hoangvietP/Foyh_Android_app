package com.example.foyh.testui.Data.service;

import android.content.Context;
import android.util.Log;
import android.webkit.JsResult;

import com.example.foyh.testui.Data.classDT.fileDAO;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.FileNotFoundException;

public class getDataMonth {
    fileDAO file = new fileDAO();
    public JSONObject getData(Context context) throws JSONException, FileNotFoundException {
        JSONObject dataM= file.getData("monthData.json","dataM",context);
        JSONArray dataAr= dataM.getJSONArray("data");
        JSONArray dtrt = new JSONArray();
        if (dataAr.length()<5){
            if (dataAr.length()==1){
                for (int i=0;i<=3;i++){
                    dtrt.put(dataAr.getJSONObject(dataAr.length()-1));
                }
            }
            if (dataAr.length()==2){
                dtrt.put(dataAr.getJSONObject(0));
                dtrt.put(dataAr.getJSONObject(0));
                dtrt.put(dataAr.getJSONObject(0));
                dtrt.put(dataAr.getJSONObject(0));
            }
            if (dataAr.length()==3){
                dtrt.put(dataAr.getJSONObject(0));
                dtrt.put(dataAr.getJSONObject(0));
                dtrt.put(dataAr.getJSONObject(1));
                dtrt.put(dataAr.getJSONObject(1));
            }
            if (dataAr.length()==4){
                dtrt.put(dataAr.getJSONObject(0));
                dtrt.put(dataAr.getJSONObject(1));
                dtrt.put(dataAr.getJSONObject(2));
                dtrt.put(dataAr.getJSONObject(2));
            }
        }else if (dataAr.length()>=5){
            for (int i=dataAr.length()-4;i<=dataAr.length()-1;i++){
                dtrt.put(dataAr.getJSONObject(i));
            }
        }
        JSONObject dt = new JSONObject();
        dt.put("data",dtrt);
        Log.d("data send to api",dt.toString());
        return dt;
    }
    public boolean saveDataThisMonth(Context context) throws FileNotFoundException, JSONException {

        JSONObject dataM= file.getData("monthData.json","dataM",context);
        JSONArray dataAr= dataM.getJSONArray("data");
        Log.d("DATA in month file",dataM.toString());
        JSONObject obj = new JSONObject();
        JSONArray bh = new JSONArray();
        JSONArray dtm = new JSONArray();
        dtm.put(0);
        dtm.put(0);
        dtm.put(0);
        JSONArray rtt = new JSONArray();
        rtt.put(0);
        rtt.put(0);
        dtm.put(rtt);
        obj.put("dataday",dtm);
        obj.put("bh",bh);

        dataAr.put(obj);
        JSONObject dtt = new JSONObject();
        dtt.put("data",dataAr);
        JSONObject dtt1 = new JSONObject();
        dtt1.put("dataM",dtt);
        file.saveData("monthData.json",dtt1,context);
        return true;
    }
}
