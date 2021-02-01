package com.example.Foyh.testui.Data.service;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.icu.util.Calendar;
import android.os.Build;
import android.util.Log;

import androidx.annotation.RequiresApi;
import androidx.core.content.ContextCompat;


import com.example.Foyh.testui.Data.classDT.BieuHien;
import com.example.Foyh.testui.Data.classDT.DataUser;
import com.example.Foyh.testui.Data.classDT.DatabaseHandler;
import com.example.Foyh.testui.Data.classDT.Dudoan;
import com.example.Foyh.testui.Data.classDT.SttThisMonth;
import com.example.Foyh.testui.Data.classDT.thisMonth;
import com.example.Foyh.testui.Notification.ExampleService;

import org.json.JSONArray;
import org.json.JSONException;

import java.io.FileNotFoundException;
import java.text.SimpleDateFormat;

public class DataServiceMethod {
    @SuppressLint("NewApi")
    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    public String testData(Context context, int stt) throws JSONException, FileNotFoundException {
        int today=0;
        //data to save
        //get stt from stt file
        DatabaseHandler databaseHandler = new DatabaseHandler(context);
        Dudoan dudoan = databaseHandler.getDudoan();
        SttThisMonth sttThisMonth = databaseHandler.getSttThisMonth(1);
        DataUser dataUser = databaseHandler.getDataUser(1);
        thisMonth thisMonth = databaseHandler.getThisMonth(dudoan.getMonth());
        JSONArray sttv = databaseHandler.getJSONArray(sttThisMonth.getBhToday());
        today= getCountDay(sttThisMonth.getDateUpdate());
        int bdrt = thisMonth.getBdRt();
        int ktrt = thisMonth.getKtRt();
        int longMonth = thisMonth.getLongMonth();
        int longDT = thisMonth.getLongDt();
        JSONArray bhrt = databaseHandler.getJSONArray(dudoan.getBhRt());
        JSONArray bhdt = databaseHandler.getJSONArray(dudoan.getBhDt());
        if (bdrt ==0 && ktrt ==0 ) {
            int countBH=sttThisMonth.getCountRT();
            for (int i = 0; i <= sttv.length() - 1; i++) {
                for (int j = 0; j <= bhrt.length()-1;j++){
                    if (bhrt.get(j).equals(sttv.get(i))){
                        countBH++;
                        break;
                    }
                }
            }
            if ((countBH+sttThisMonth.getCountRT())>=2 && today >=10){
                Log.d("day rt",today+"");
                thisMonth.setBdRt(today);
                thisMonth.setKtRt(today+(dudoan.getKtRt()-dudoan.getBdRt()));
                dudoan.setKtRt(today+(dudoan.getKtRt()-dudoan.getBdRt()));
                dudoan.setBdRt(today);
            }else if (countBH<2 && today >=10){
                sttThisMonth.setCountRT(countBH);
            }
        }
        if (bdrt !=0 && ktrt !=0  && longMonth==0 ){
            int countBH=sttThisMonth.getCountDt();
            for (int i = 0; i <= sttv.length() - 1; i++) {
                for (int j = 0; j <= bhdt.length()-1;j++){
                    if (bhdt.get(j).equals(sttv.get(i))){
                        countBH+=1;
                    }
                    if (sttv.get(i).equals(12)){
                        Log.d("day dt",today+"");
                        thisMonth.setLongMonth(today);
                        dudoan.setLongMonth(today);
                        databaseHandler.UpdateThisMonth(thisMonth);
                        break;
                    }
                }
            }
           if (countBH >=2 && today>ktrt+5){
                sttThisMonth.setNn("Sắp tới mùa dâu");
            }else if (countBH<2 && today >ktrt+5){
                sttThisMonth.setCountDt(countBH);
            }
        }
        if (bdrt !=0 && ktrt !=0  && longMonth!=0 ){
            for (int i = 0; i <= sttv.length() - 1; i++) {
                if (sttv.get(i).equals(13)){
                    Log.d("kt Den thang","dt");
                    thisMonth.setLongDt(today-thisMonth.getLongMonth());
                    databaseHandler.UpdateThisMonth(thisMonth);
                    databaseHandler.updateDudoan(databaseHandler.matchDudoan(),dudoan.getMonth());
                    databaseHandler.addThisMonth(new thisMonth(dudoan.getMonth()+1,0,0,0,0));
                    databaseHandler.UpdateSttThisMonth(new SttThisMonth(1,getDate(),0,0,"","0,1,2,3,4,5,6,7,8,9","",""),1);
                    dataUser.setDay(1);
                    databaseHandler.UpdateUser(dataUser);
                    Log.d("data after match end",databaseHandler.getDudoan().toString());
                    Log.d("data after match end",databaseHandler.getDataUser(1).toString());
                    Log.d("data after match end",databaseHandler.getSttThisMonth(1).toString());
                    databaseHandler.getAllMonth().forEach(p->Log.d("all month",p.toString()));
                    Intent serviceIntent= new Intent(context, ExampleService.class);
                    ContextCompat.startForegroundService(context, serviceIntent);
                    return "";
                }
            }
        }

        if (today>dudoan.getBdRt()+5 && bdrt==0){
            Log.d("Rung trung","bdrt"+dudoan.getBdRt()+"/"+dudoan.getKtRt());
            thisMonth.setBdRt(dudoan.getBdRt());
            thisMonth.setKtRt(dudoan.getKtRt());
            databaseHandler.UpdateThisMonth(thisMonth);
        }
        //set note vaf nhac nho vaf thanh stt
        if (bdrt==0 && ktrt==0){
            Log.d("Truoc RT","129");
            if (today<dudoan.getBdRt()-2){
                sttThisMonth.setListBh("0,1,2,3,4,5,6,7,8,9");
                sttThisMonth.setNote("Rụng trứng: "+dateSetup(sttThisMonth.getDateUpdate(),dudoan.getBdRt())+" - "+dateSetup(sttThisMonth.getDateUpdate(),dudoan.getKtRt()));
                sttThisMonth.setNn("Ngày an toàn");
            }
            if (today>dudoan.getBdRt()){
                sttThisMonth.setListBh("0,1,2,3,4,5,6,7,8,9");
                sttThisMonth.setNote("Sắp tới mùa trứng");
                sttThisMonth.setNn("Ngày không an toàn");
            }
        }
        if (bdrt!=0 && ktrt!=0 && longMonth==0){
            Log.d("Dang hoac sau RT","140");
            if (today>=bdrt-4 && today<= ktrt+2){
                sttThisMonth.setListBh("0,1,2,3,4,5,6,7,8,9");
                sttThisMonth.setNote("Mùa Trứng");
                sttThisMonth.setNn("Ngày không an toàn, tỉ lệ thụ thai cao");
            }
            if (today> ktrt+2  && today<dudoan.getLongMonth()){
                sttThisMonth.setListBh("0,1,2,3,4,5,6,7,8,9");
                sttThisMonth.setNote("Mùa dâu: "+dateSetup(sttThisMonth.getDateUpdate(),dudoan.getLongMonth())+" - "+dateSetup(sttThisMonth.getDateUpdate(),dudoan.getLongMonth()+dudoan.getLongDt()));
                sttThisMonth.setNn("Ngày an toàn");
            }
            if (today>dudoan.getLongMonth()-7){
                sttThisMonth.setNote("Sắp tới mùa dâu");
                sttThisMonth.setNn("Ngày không an toàn");
                sttThisMonth.setListBh("12,1,2,3,4,5,6,7,8,9");
            }
        }
        if (longMonth!=0 && longDT ==0){
            Log.d("Dang hanh kinh","140");
            sttThisMonth.setListBh("13,1,2,3,4,5,6,7,8,9");
            sttThisMonth.setNote("Hành kinh ngày : "+(today-longMonth+1));
            sttThisMonth.setNn("Ngày an toàn");
        }

        //them bh hom nay
        Log.d("Day", "today:"+today+"/"+dataUser.getDay());
        if (today>dataUser.getDay()){
        if (!sttThisMonth.getBhToday().equals("")){
            databaseHandler.addBh(new BieuHien(today,dudoan.getMonth(),sttThisMonth.getBhToday()));
            databaseHandler.getBieuhienMonth(dudoan.getMonth()).forEach(p->Log.d("bh",p.toString()));
        }
            sttThisMonth.setBhToday("");
            dataUser.setDay(today);
        }
        databaseHandler.UpdateSttThisMonth(sttThisMonth,1);
        databaseHandler.UpdateUser(dataUser);
        databaseHandler.UpdateThisMonth(thisMonth);
        if (today>(dudoan.getLongMonth()+10)){
            if (longDT==0){
                thisMonth.setLongDt(dudoan.getLongDt());
            }
            if (longMonth==0){
                thisMonth.setLongMonth(dudoan.getLongMonth());
            }
            if (bdrt==0 && ktrt==0){
                thisMonth.setBdRt(dudoan.getBdRt());
                thisMonth.setKtRt(dudoan.getKtRt());
            }

            databaseHandler.addThisMonth(new thisMonth(dudoan.getMonth()+1,0,0,0,0));
            databaseHandler.UpdateSttThisMonth(new SttThisMonth(1,getDate(),0,0,"","0,1,2,3,4,5,6,7,8,9","",""),1);
            dataUser.setDay(1);
            databaseHandler.UpdateUser(dataUser);
            int monhth= dudoan.getMonth()+1;
            Log.d("month",monhth+"");
            dudoan.setMonth(monhth);
            databaseHandler.updateDudoan(dudoan,dudoan.getMonth()-1);
            Log.d("data du doan",dudoan.toString());
            Log.d("data after match",databaseHandler.getDudoan().toString());
            Log.d("data after match",databaseHandler.getDataUser(1).toString());
            Log.d("data after match",databaseHandler.getSttThisMonth(1).toString());
            databaseHandler.getAllMonth().forEach(p->Log.d("all month",p.toString()));
        }
        Log.d("data after match",databaseHandler.getDudoan().toString());
        Log.d("data after match",databaseHandler.getDataUser(1).toString());
        Log.d("data after match",databaseHandler.getSttThisMonth(1).toString());
        databaseHandler.getAllMonth().forEach(p->Log.d("all month",p.toString()));
//        databaseHandler.getBieuhienMonth(1).forEach(p->Log.d("BH",p.toString()));
        return "done";
    }
    @SuppressLint("LongLogTag")

    public String dateSetup(String datest, int lg){
        //get date start ck
        String[] splitDate1=datest.split("-");
        int year = Integer.parseInt(splitDate1[0].trim());
        int month = Integer.parseInt(splitDate1[1].trim());
        int day = Integer.parseInt(splitDate1[2].trim());
        String rt="Error";
        int[] monthD={31,0,31,30,31,30,31,31,30,31,30,31};
        int d= 0;
        if (month!=2){
            d= monthD[month-1];
        }else {
            if (year % 4 == 0){
                d= 29;
            }else {
                d=28;
            }
        }
        int daycl=d-day;
        if (daycl>=lg){
            rt= String.valueOf(day+lg)+"/"+month;
            return rt;
        }
        if (daycl<lg &&  month==12){
            int cl = lg-daycl;
            if (31 >= cl){
                rt= String.valueOf(cl)+"/"+ 1;
            }else {
                int d1=28;
                if (year % 4 == 0){
                    d1= 29;
                }else {
                    d1=28;
                }
                int c= cl-d1;
                rt= String.valueOf(c)+"/"+ 2;
            }
        }else if (daycl<lg &&  month==11){
            int cl = lg-daycl;
            if (cl==0){
                cl=1;
            }
            if (31 >= cl){
                rt= String.valueOf(cl)+"/"+ 12;
            }else {
                int c= cl-31;
                rt= String.valueOf(c)+"/"+ 1;
            }
        } else {
            int cl = lg-daycl;

            if (monthD[month-1]>= cl){
                rt= String.valueOf(cl)+"/"+ String.valueOf(month+1);
            }else if (31>= cl && month==11 ){
                rt= String.valueOf(cl)+"/"+ 1;
            }else {
                int c=cl- monthD[month-1];
                rt= String.valueOf(c)+"/"+ String.valueOf(month+2);
            }
        }
        return rt;
    }
    public int getCountDay(String dateUpdate){
        String[] splitDate1=dateUpdate.split("-");
        int year = Integer.parseInt(splitDate1[0].trim());
        int month = Integer.parseInt(splitDate1[1].trim());
        int dayc = Integer.parseInt(splitDate1[2].trim());

        //get count day ,by day up date and now
        //get date now
        int day=1;
        String formattedDate = null;
        java.util.Calendar c = null;
        c = java.util.Calendar.getInstance();
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        formattedDate = df.format(c.getTime());

        String[] splitDate=formattedDate.split("-");
//        String testdate="2020-12-1";
//        String[] splitDate=testdate.split("-");
        int year1 = Integer.parseInt(splitDate[0].trim());
        int month1 = Integer.parseInt(splitDate[1].trim());
        int day1 = Integer.parseInt(splitDate[2].trim());
        int[] monthD= new int[12];
        if (year==year1){

            if (year1%4==0){
                monthD= new int[]{31, 29, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};
            }else {
                monthD= new int[]{31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};
            }
            if (month == month1) {
                day = day1-dayc+1;
            }else if(month==month1-1){
                int dayfm1 = 0;
                dayfm1= monthD[month1-2];
                day=day1+(dayfm1-dayc)+1;
            } else if(month+2==month1){
                day = monthD[month-1]-dayc + monthD[month]+day1;
            }
        }else {
            if (year1%4==0){
                monthD= new int[]{31, 29, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};
            }else {
                monthD= new int[]{31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};
            }
            if (month == 12 && month1==1) {
                day = (31-dayc)+day1;
            }else if(month == 12 && month1==1){
                int dayfm1 = 0;
                dayfm1= monthD[11];
                day=day1+(dayfm1-dayc)+1;
            }else if(month == 11 && month1==1){
                int dayfm1 = monthD[10]-dayc+monthD[11];
                day=day1+dayfm1;
            }else {
            }
        }
        return day+1;
    }
    public String getDate(){
        String date=null;
        Calendar c = null;
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.N) {
            c = Calendar.getInstance();
            SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
            date = df.format(c.getTime());
        }

        return date;
    }
    public String getYear(){
        String date=null;
        Calendar c = null;
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.N) {
            c = Calendar.getInstance();
            SimpleDateFormat df = new SimpleDateFormat("yyyy");
            date = df.format(c.getTime());
        }

        return date;
    }

    public int getMonth(){
        String date=null;
        Calendar c = null;
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.N) {
            c = Calendar.getInstance();
            SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
            date = df.format(c.getTime());
        }
        String[] splitDate=date.split("-");
        int month1 = Integer.parseInt(splitDate[1].trim());
        return month1;
    }
    public int getH(){
        String date=null;
        Calendar c = null;
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.N) {
            c = Calendar.getInstance();
            SimpleDateFormat df = new SimpleDateFormat("HH");
            date = df.format(c.getTime());
        }
        String[] splitDate=date.split("-");
        int month1 = Integer.parseInt(splitDate[0].trim());
        return month1;
    }
    public int getM(){
        String date=null;
        Calendar c = null;
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.N) {
            c = Calendar.getInstance();
            SimpleDateFormat df = new SimpleDateFormat("mm");
            date = df.format(c.getTime());
        }
        String[] splitDate=date.split("-");
        int month1 = Integer.parseInt(splitDate[0].trim());
        return month1;
    }
    public int getS(){
        String date=null;
        Calendar c = null;
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.N) {
            c = Calendar.getInstance();
            SimpleDateFormat df = new SimpleDateFormat("ss");
            date = df.format(c.getTime());
        }
        String[] splitDate=date.split("-");
        int month1 = Integer.parseInt(splitDate[0].trim());
        return month1;
    }
    public String getDay(){
        String date=null;
        Calendar c = null;
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.N) {
            c = Calendar.getInstance();
            SimpleDateFormat df = new SimpleDateFormat("dd");
            date = df.format(c.getTime());
        }

        return date;
    }

}
