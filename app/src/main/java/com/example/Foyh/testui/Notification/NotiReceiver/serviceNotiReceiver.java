package com.example.Foyh.testui.Notification.NotiReceiver;

import android.content.Context;
import android.os.Build;

import androidx.annotation.RequiresApi;


import com.example.Foyh.testui.Data.classDT.DatabaseHandler;
import com.example.Foyh.testui.Data.classDT.SttThisMonth;

import org.json.JSONArray;
import org.json.JSONException;

public class serviceNotiReceiver {
    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    public Boolean getData(int bt, Context context) throws  JSONException {

        DatabaseHandler  databaseHandler = new DatabaseHandler(context);
        SttThisMonth sttThisMonth = databaseHandler.getSttThisMonth(1);
        String h =sttThisMonth.getNote();
        String nn = sttThisMonth.getNn();
        String[] listbh = sttThisMonth.getListBh().split(",");
        String[] bhtoday = sttThisMonth.getBhToday().split(",");
        JSONArray stt =new JSONArray();
        JSONArray sttv = new JSONArray();
        for (int i = 0; i <=listbh.length-1; i++) {
            if (!listbh[i].equals("")){
                stt.put(Integer.valueOf(listbh[i]));
            }
        }
        for (int i = 0; i <=bhtoday.length-1; i++) {
            if (!bhtoday[i].equals("")){
                sttv.put(Integer.valueOf(bhtoday[i]));
            }
        }
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
        databaseHandler.UpdateSttThisMonth(new SttThisMonth(1,sttThisMonth.getDateUpdate(),0,0,databaseHandler.getString(sttv),databaseHandler.getString(stt),h,nn),1);
        return true;
    }
}
