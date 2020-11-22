package com.example.foyh.testui.Data.service;

import android.content.Context;
import android.icu.util.Calendar;
import android.os.AsyncTask;
import android.os.Build;
import android.util.Log;

import androidx.annotation.RequiresApi;


import com.example.foyh.testui.Data.classDT.fileDAO;
import com.example.foyh.testui.ortherThread.RegistrationTask;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.FileNotFoundException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

public class DataServiceMethod {
    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    public String testData(Context context, int stt) throws JSONException, FileNotFoundException {
        //data to save
        //get stt from stt file
        fileDAO file = new fileDAO();
        JSONObject dateStatus= new JSONObject();
        try{
        dateStatus = file.getData("dateStatus.json","dd",context);
        }catch (NullPointerException | JSONException |FileNotFoundException ee){
            JSONObject d = new JSONObject();
            JSONObject dd= new JSONObject();
            d.put("d",1);
            dd.put("dd",d);
            file.saveData("dateStatus.json",dd,context);
        }
        int dateStt= dateStatus.getInt("d");
        String conten="";
        JSONArray sttArr=new JSONArray();
        JSONArray sttvArr=new JSONArray();
        try{
            JSONObject objstt=  file.getData("stt.json","data",context);//get data from stt file
            sttArr= objstt.getJSONArray("stt");
            sttvArr= objstt.getJSONArray("sttv");
        }catch (FileNotFoundException d){
            saveStt(context,sttArr,sttvArr,"null");
        }

        int dayst=0;
        //test data
        JSONArray bhrtt = new JSONArray();
        JSONArray bhrt11 = new JSONArray();
        bhrt11.put(1);
        bhrt11.put(2);
        bhrt11.put(4);
        JSONArray bhrt22 = new JSONArray();
        bhrt22.put(5);
        bhrt22.put(6);
        bhrt22.put(8);
        JSONArray bhrt33 = new JSONArray();
        bhrt33.put(2);
        bhrt33.put(4);
        bhrt33.put(5);
        bhrtt.put(bhrt11);
        bhrtt.put(bhrt22);
        bhrtt.put(bhrt33);

        JSONArray bhdtt = new JSONArray();
        JSONArray bhdt11 = new JSONArray();
        bhdt11.put(2);
        bhdt11.put(3);
        bhdt11.put(4);
        JSONArray bhdt22 = new JSONArray();
        bhdt22.put(1);
        bhdt22.put(3);
        bhdt22.put(4);
        JSONArray bhdt33 = new JSONArray();
        bhdt33.put(3);
        bhdt33.put(4);
        bhdt33.put(5);
        bhdtt.put(bhdt11);
        bhdtt.put(bhdt22);
        bhdtt.put(bhdt33);

//        setData(context,"2020-10-28",14,19,7,2,2,1,34,bhrtt,bhdtt,0);
        ArrayList<Integer> noti= new ArrayList<Integer>();
        //test data
        JSONObject ob=  file.getData("thisMonthData.json","data",context);
        String dateUpdate= ob.getString("dayupdate");
        JSONArray ard=ob.getJSONArray("dataday");


        JSONArray dt = ard;
        int day = dt.getInt(0);//today
        int month =dt.getInt(1);// count month
        int longMo = ard.getInt(2);
        int longdt= ard.getInt(3);
        JSONArray trt = ard.getJSONArray(4);
        int[] dayrt= {trt.getInt(0),trt.getInt(1)};


        //set get data this month
        try {
            JSONObject dataM= file.getData("monthData.json","dataM",context);
            JSONArray dataAr= dataM.getJSONArray("data");
        }catch (FileNotFoundException e){
            e.printStackTrace();
            JSONArray bhfld= new JSONArray();
            JSONArray bh1 = new JSONArray();
            bhfld.put(bh1);
            saveData(context,0,0,0,0,bhfld,month);
        }

        //lay bh trong ngay
        JSONObject ob1=  file.getData("monthData.json","dataM",context);
        JSONArray ardt = ob1.getJSONArray("data");// arr chua cac obj data cua cac thang
        JSONObject artd1=ardt.getJSONObject(ardt.length()-1);// obj data cua thang nay
        JSONArray ardt1= artd1.getJSONArray("dataday");
        int lm= ardt1.getInt(1);
        int ldt= ardt1.getInt(2);
        JSONArray rttt= ardt1.getJSONArray(3);
        JSONArray bhday= artd1.getJSONArray("bh");
        if (dateStt!=day){
            //put bh to bh theo ngay
            bhday.put(sttvArr);
        }

//        JSONArray bh = bhday.getJSONArray(bhday.length()-1);
        JSONArray bh= sttvArr;

        try{
            bh.getInt(0);
        }catch (JSONException i){
            //neu k co bh trong ngay return lai ARRlist
            JSONObject oj= new JSONObject();
            oj.put("fale","null");
//            return oj;

        }
        //lay du lieu ve bh
        JSONArray bhdt= ob.getJSONArray("bhdt");
        JSONArray bhdt1 = bhdt.getJSONArray(0);
        JSONArray bhdt2 = bhdt.getJSONArray(1);
        JSONArray bhdt3 = bhdt.getJSONArray(2);

        JSONArray bhrt= ob.getJSONArray("bhrt");
        JSONArray bhrt1 = bhrt.getJSONArray(0);
        JSONArray bhrt2 = bhrt.getJSONArray(1);
        JSONArray bhrt3 = bhrt.getJSONArray(2);

        // gan bh theo ngay vao 1 mang
        int[] bhrtn1= new int[bhrt1.length()];
        for (int i =0 ; i<=bhrt1.length()-1;i++){
            bhrtn1[i]= bhrt1.getInt(i);
        }
        int[] bhrtn2= new int[bhrt2.length()];
        for (int i =0 ; i<=bhrt2.length()-1;i++){
            bhrtn2[i]= bhrt2.getInt(i);
        }
        int[] bhrtn3= new int[bhrt3.length()];
        for (int i =0 ; i<=bhrt3.length()-1;i++){
            bhrtn3[i]= bhrt3.getInt(i);
        }

        int[] bhdtn1= new int[bhdt1.length()];
        for (int i =0 ; i<=bhdt1.length()-1;i++){
            bhdtn1[i]= bhdt1.getInt(i);
        }
        int[] bhdtn2= new int[bhdt2.length()];
        for (int i =0 ; i<=bhdt2.length()-1;i++){
            bhdtn2[i]= bhdt2.getInt(i);
        }
        int[] bhdtn3= new int[bhdt3.length()];
        for (int i =0 ; i<=bhdt3.length()-1;i++){
            bhdtn3[i]= bhdt3.getInt(i);
        }
            //if Rt-5 < day < Rt
        //count rt

        int countrt= ob.getInt("countRT");
        int countdt = ob.getInt("countDT");
        int countRT=0;
        int countDt=0;
        if (ob.getInt("dayst")+1==day || ob.getInt("dayst")+2==day) {
                 countDt = countdt;
                 countRT = countrt;
        }
        if( day >dayrt[0] - 6 && day < dayrt[0]+6){
            //Bh RT true mỗi một bh đúng +BHRTcount
                for(int i=0;i<= bh.length()-1;i++){
                    int st=0;
                    if (bh.getInt(i)!=0){
                        for (int j=0;j<= bhrtn1.length-1;j++){
                            if (bh.getInt(i)==bhrtn1[j]){
                                countRT++;
                                st++;
                            }
                        }
                        if (st!=0){
                            continue;
                        }
                        for (int j=0;j<= bhrtn2.length-1;j++){
                            if (bh.getInt(i)==bhrtn2[j]){
                                countRT++;
                                st++;
                            }
                        }
                        if (st!=0){
                            continue;
                        }
                        for (int j=0;j<= bhrtn3.length-1;j++){
                            if (bh.getInt(i)==bhrtn3[j]){
                                countRT++;
                                st++;
                            }
                        }
                    }
                }
                if (countRT>=2 && rttt.getInt(0)==0 && dayrt[0]>day+3){
                    trt.remove(0);
                    trt.put(0,day+3);
                    trt.put(1,day+3+(dayrt[1] - dayrt[0]));
                } else if (countRT==1){
                    countrt=countRT+countrt;
                    dayst=day;
                }
                //else save countRT;
                //chinh lai du doan == today+3

//            if RT-3 < day < RT-2 uu tien bh ngay thu 2
//            if RT-2 < day < RT-1 uu tien bh ngay thu 1
//            Bh false
//            theo dung du doan
        }

        if( day >longMo - 10 && day < longMo+10){
            //Bh DT true mỗi một bh đúng +BHDTcount
            for(int i=0;i<= bh.length()-1;i++){
                int st=0;
                if (bh.getInt(i)!=0 ){
                    if (bh.getInt(i)==12){
                        countDt=12;
                        Log.d("bh","12");
                        continue;
                    }
                    if (bh.getInt(i)==13){
                        countDt=13;
                        continue;
                    }
                    for (int j=0;j<= bhdtn1.length-1;j++){
                        if (bh.getInt(i)==bhdtn1[j]){
                            countDt++;
                            st++;
                        }
                    }
                    if (st!=0){
                        continue;
                    }
                    for (int j=0;j<= bhdtn2.length-1;j++){
                        if (bh.getInt(i)==bhdtn2[j]){
                            countDt++;
                            st++;
                        }
                    }
                    if (st!=0){
                        continue;
                    }
                    for (int j=0;j<= bhdtn3.length-1;j++){
                        if (bh.getInt(i)==bhdtn3[j]){
                            countDt++;
                            st++;
                        }
                    }
                }
            }

            if (countDt>=2 && countDt!=12 && countDt!=13 && day+3<longMo-longdt && ldt==0 && lm==0){
                longMo=day+3;
            } else  if(countDt==12){
                longMo= day;
                if (lm==0){
                    lm=longMo;
                }
            } else  if(countDt==13){
                longdt= day- lm;
                if (ldt==0){
                    ldt=longdt;
                }
            } else if (countDt==1){
                countdt= countDt+countdt;
                dayst= day;
            }
            //else save countRT;
            //chinh lai du doan == today+3

//            if RT-3 < day < RT-2 uu tien bh ngay thu 2
//            if RT-2 < day < RT-1 uu tien bh ngay thu 1
//            Bh false
//            theo dung du doan
        }
        if (day>= longMo+15 && lm==0){
            lm=longMo;
            ldt=longdt;
        }
        if (day>= longMo+longdt+5 && ldt==0){ //neu nguoi dung co chu ki kn keo dai hon du daon 5 ngay
            // se tu dong nhap vao longdt nhu du doan // phan nay se them cn thong bao neu ng dung gap van de ve suc khoe
            ldt=longdt;
        }
        if (day==dayrt[0] && rttt.getInt(0)==0 && countrt!=0){
            rttt.remove(0);
            rttt.put(0,dayrt[0]);
            rttt.put(1,dayrt[1]);
        }if (day==dayrt[0]+5 && rttt.getInt(0)==0){
            rttt.remove(0);
            rttt.put(0,dayrt[0]);
            rttt.put(1,dayrt[1]);
        }
            //if Rt < day< kt RT
                // TB => Dt + RT[1]= ngay ket thuc RT

            //if kt Rt< day < DT -7
                //nhap vao DT ==> save today = day DT
                //Bh DT true
                    //if day < DT-3 uu tien bh ngay thu 3
                    // true Bh => DT sau 3 ngay + change day DT dudoan -> today+3
                    //if DT-3 < day < DT-2 uu tien bh ngay thu 2
                    //if DT-2 < day < DT-1 uu tien bh ngay thu 1

            //if DT< day < ktDT
                //Test Kt DT true => save today = dayDt
                // Test Kt DT false and day = ktDt + 5 => save Kt DT as Dudoan

            //if day = Rt
                //Test Bhcount >= 2 and today <= dayRT+5 ==> save today = day RT
                // else ==> day RT = day Rt(du doan)

            //if day= kt RT
                // theo chinh sua hoac cai dat san

            // if day = DT
                // if day > day DT + 7 ==> // test QH true ==> // change noti -> maybe mangthai mode
                                            // BH false ==> // change TB --> tb ng dung kiem tra sk ==> gia han them gioi han+15
                                                                                //ng dung k gia han ==> no action
                                            // no action ==> save dayDT = dayDt(dudoan)
                // su co => save to=dayDT

            // if day = Kt Dt
                //Test KtDT true=> send data to server =reponse=> save thisMo
                //false and today = ktDT+5 => Tb ==> bieu hien bat thuong => action true => gia han test+7
                                                                            //false => ktDT= ktDT(dudoan)

//
//
//
        setData(day,context,dateUpdate,trt.getInt(0),trt.getInt(1),longdt,countrt,countdt,month,longMo,bhrt,bhdt,0);
        saveData(context,rttt.getInt(0),rttt.getInt(1),ldt,lm,bhday,month);
        if (ldt!=0){
            new RegistrationTask(context).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR,"https://057d2c97280a.ngrok.io/api/dudoan");

        }
        //conten dt,rt


        if (day<dayrt[0]){
             conten ="Rụng trứng: "+ dateSetup(dateUpdate,dayrt[0])+" - "+dateSetup(dateUpdate,dayrt[1]);
             //them bh rt
            JSONArray sttDt= new JSONArray();
            sttDt.put(0);sttDt.put(1);sttDt.put(2);
            sttDt.put(3);sttDt.put(4);sttDt.put(5);
            sttDt.put(6);
            sttArr=sttDt;
        }else if (day>=dayrt[0] && day<= dayrt[1]){
            conten= "Rụng trứng ngày thứ: "+ String.valueOf(day-dayrt[0]+1);
            JSONArray sttDt= new JSONArray();
            sttDt.put(0);sttDt.put(1);sttDt.put(2);
            sttDt.put(3);sttDt.put(4);sttDt.put(5);
            sttDt.put(6);
            sttArr=sttDt;
        } else if (day>dayrt[1]  && day<=longMo && lm==0){
            conten= "Kỳ hành kinh: "+dateSetup(dateUpdate,longMo)+" - "+dateSetup(dateUpdate,longMo+longdt);
            // bhdt
            JSONArray sttDt= new JSONArray();
            sttDt.put(0);sttDt.put(1);sttDt.put(2);
            sttDt.put(3);sttDt.put(4);sttDt.put(5);
            sttDt.put(12);
            sttArr=sttDt;
        } else if ( day<lm+longdt+7 && day>= lm && lm!=0){
            conten="Hành kinh ngày: "+ String.valueOf(day-lm+1);
            JSONArray sttDt= new JSONArray();
            sttDt.put(0);sttDt.put(1);sttDt.put(2);
            sttDt.put(3);sttDt.put(4);sttDt.put(5);
            sttDt.put(13);
            sttArr=sttDt;
        } else {
            conten="Kỳ hành kinh có thể đến trong vài ngày tới";
            JSONArray sttDt= new JSONArray();
            sttDt.put(0);sttDt.put(1);sttDt.put(2);
            sttDt.put(3);sttDt.put(4);sttDt.put(5);
            sttDt.put(12);
            sttArr=sttDt;
        }
        //if on next day => reset sttvstt
        if (dateStt!=day){
            JSONArray bhh = new JSONArray();
            sttvArr=bhh;

        }
        saveStt(context,sttArr,sttvArr,conten);
        return conten;
    }
    public void setData(int da,Context context, String dateUpdate, int bgrt, int ktrt, int longdt, int countRT, int countDT, int nameMo, int longMo, JSONArray bhrt, JSONArray bhdt, int dayst) throws JSONException {
        JSONObject obj = new JSONObject();
        JSONArray dtm = new JSONArray();


        //date up date
//        int day=getCountDay(dateUpdate);
        int day = da+1;
        dtm.put(day);
        dtm.put(nameMo);
        dtm.put(longMo);
        dtm.put(longdt);
        JSONArray rtt = new JSONArray();
        rtt.put(bgrt);
        rtt.put(ktrt);
        dtm.put(rtt);
        obj.put("dataday",dtm);

        obj.put("bhrt",bhrt);
        obj.put("bhdt",bhdt);

        obj.put("dayupdate",dateUpdate);
        obj.put("countRT",countRT);
        obj.put("countDT",countDT);
        obj.put("dayst",dayst);
        JSONObject data= new JSONObject();
        data.put("data",obj);
        fileDAO file = new fileDAO();
        file.saveData("thisMonthData.json",data,context);
        JSONObject d = new JSONObject();
        JSONObject dd= new JSONObject();
        d.put("d",day-1);
        dd.put("dd",d);
        file.saveData("dateStatus.json",dd,context);
        Log.d("thisMonthData",data.toString());
    }
    public void saveData(Context context, int bgrt, int ktrt, int longdt, int longMo, JSONArray bh, int month) throws JSONException {
        JSONObject obk = new JSONObject();
        JSONArray arr = new JSONArray();
        JSONObject obj = new JSONObject();
        JSONArray dtm = new JSONArray();
        dtm.put(month);
        dtm.put(longMo);
        dtm.put(longdt);
        JSONArray rtt = new JSONArray();
        rtt.put(bgrt);
        rtt.put(ktrt);
        dtm.put(rtt);
        obj.put("dataday",dtm);
        obj.put("bh",bh);
        arr.put(obj);
        obk.put("data",arr);
        JSONObject obb= new JSONObject();
        obb.put("dataM",obk);
        fileDAO file = new fileDAO();
        file.saveData("monthData.json",obb,context);
        Log.d("monthData",obb.toString());
    }
    public void saveStt(Context context, JSONArray stt, JSONArray sttv, String st) throws JSONException {
        JSONObject obb1= new JSONObject();
        obb1.put("stt",stt);
        obb1.put("sttv",sttv);
        obb1.put("st",st);
        JSONObject obb= new JSONObject();
        obb.put("data",obb1);
        fileDAO file = new fileDAO();
        Log.d("data on stt file",obb1.toString());
        file.saveData("stt.json",obb,context);
    }
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
        }else if (daycl<lg &&  month==12){
            int cl = lg-daycl;
            if (monthD[month+1]>= cl){
                rt= String.valueOf(cl)+"/"+ String.valueOf(month+1);
            }else {
                int c= cl-monthD[month+1];
                rt= String.valueOf(c)+"/"+ String.valueOf(month+2);
            }
        }
        else {
            int cl = lg-daycl;
            if (monthD[month-1]>= cl){
                rt= String.valueOf(cl)+"/"+ String.valueOf(month+1);
            }else {
                int c=cl- monthD[month];
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
        Calendar c = null;
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.N) {
            c = Calendar.getInstance();
            SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
            formattedDate = df.format(c.getTime());

        }
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
                Log.d("Error"," get date for stt Notificatio at DataServiceMothod");
            }
        }
        return day;
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
}
