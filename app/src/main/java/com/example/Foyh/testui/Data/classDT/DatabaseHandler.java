package com.example.Foyh.testui.Data.classDT;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Build;
import android.util.Log;

import androidx.annotation.RequiresApi;

import org.json.JSONArray;
import org.json.JSONException;

import java.util.ArrayList;
import java.util.List;

public class DatabaseHandler extends SQLiteOpenHelper{
    private static final String DATABASE_NAME = "DataUser";
    private static final int DATABASE_VERSION = 1;

    //table stt this month
    private static final String TABLE_NAME3 = "DataSttThisMonth";
    private static final String KEY_DATE_UPDATE = "DateUpDate";
    private static final String KEY_ID_STT = "IdStt";
    private static final String KEY_COUNT_RT= "CountRt";
    private static final String KEY_COUNT_DT = "CountDt";
    private static final String KEY_BH_TODAY = "BhToday";
    private static final String KEY_LIST_BH = "ListBh";
    private static final String KEY_NOTE = "Note";
    private static final String KEY_NN = "Nn";
    // table bh
    private static final String TABLE_NAME2 = "DataBH";
    private static final String KEY_MONTH2 = "Month";
    private static final String KEY_DAY= "Day";
    private static final String KEY_BH = "BH";
    //table this month
    private static final String TABLE_NAME1 = "DataThisMonth";
    private static final String KEY_MONTH1 = "Month";
    private static final String KEY_LONGMONTH1= "LongMonth";
    private static final String KEY_LONGDT1 = "LongDt";
    private static final String KEY_BDRT1 = "BDRT";
    private static final String KEY_KTRT1 = "KTRT";
    // table du doan
    private static final String TABLE_NAME = "DataDudoan";
    private static final String KEY_MONTH = "Month";
    private static final String KEY_LONGMONTH= "LongMonth";
    private static final String KEY_LONGDT = "LongDt";
    private static final String KEY_BDRT = "BDRT";
    private static final String KEY_KTRT = "KTRT";
    private static final String KEY_BHDT = "BHDT";
    private static final String KEY_BHRT = "BHRT";

    //table user
    private static final String TABLE_NAME5 = "DataUser";
    private static final String KEY_NAME = "Name";
    private static final String KEY_BORN= "Born";
    private static final String KEY_TOKEN = "Token";
    private static final String KEY_ID = "Id";
    private static final String KEY_DAY1 = "Day";

    public DatabaseHandler(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        //create table du doan
        String create_DD_table = String.format("CREATE TABLE %s(%s INTEGER PRIMARY KEY, %s INTEGER, %s INTEGER, %s INTEGER,%s INTEGER,%s TEXT,%s TEXT)", TABLE_NAME,
                KEY_MONTH,KEY_LONGMONTH,KEY_LONGDT,KEY_BDRT,KEY_KTRT,KEY_BHRT,KEY_BHDT);
        db.execSQL(create_DD_table);

        //create table this month
        String create_thismonth_table = String.format("CREATE TABLE %s(%s INTEGER PRIMARY KEY, %s INTEGER, %s INTEGER, %s INTEGER,%s INTEGER)", TABLE_NAME1,
                KEY_MONTH1,KEY_LONGMONTH1,KEY_LONGDT1,KEY_BDRT1,KEY_KTRT1);
        db.execSQL(create_thismonth_table);


        //create table Bieu hien
        String create_BH_table = String.format("CREATE TABLE %s(%s INTEGER , %s INTEGER,%s TEXT)", TABLE_NAME2,
                KEY_MONTH2,KEY_DAY,KEY_BH);
        db.execSQL(create_BH_table);

        //create table stt This month
        String create_STTTM_table = String.format("CREATE TABLE %s(%s TEXT, %s INTEGER,%s INTEGER, %s INTEGER, %s TEXT,%s TEXT, %s TEXT,%s TEXT)", TABLE_NAME3,
                KEY_DATE_UPDATE,KEY_ID_STT,KEY_COUNT_RT,KEY_COUNT_DT,KEY_BH_TODAY,KEY_LIST_BH,KEY_NOTE,KEY_NN);
        db.execSQL(create_STTTM_table);

        //create table data user
        String create_USER_table = String.format("CREATE TABLE %s(%s INTEGER,%s TEXT, %s TEXT, %s TEXT,%s INTEGER)", TABLE_NAME5,KEY_ID,
                KEY_NAME,KEY_BORN,KEY_TOKEN,KEY_DAY1);
        db.execSQL(create_USER_table);


    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {


        onCreate(db);
    }

    //du doan
    // add new du doan
    public void addNewDD(Dudoan dudoan) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(KEY_MONTH, dudoan.getMonth());
        values.put(KEY_LONGMONTH, dudoan.getLongMonth());
        values.put(KEY_LONGDT, dudoan.getLongDt());
        values.put(KEY_BDRT, dudoan.getBdRt());
        values.put(KEY_KTRT, dudoan.getKtRt());
        values.put(KEY_BHDT, dudoan.getBhDt());
        values.put(KEY_BHRT, dudoan.getBhRt());
        db.insert(TABLE_NAME, null, values);
        db.close();
    }

    public Dudoan getDudoan() {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM DataDudoan WHERE Month >0",null);
        Dudoan dudoan = new Dudoan();
        if(cursor != null) {
            cursor.moveToFirst();
             dudoan = new Dudoan(cursor.getInt(0), cursor.getInt(1), cursor.getInt(2), cursor.getInt(3), cursor.getInt(4), cursor.getString(5), cursor.getString(6));
        }
            return dudoan;
    }

    public void updateDudoan(Dudoan dudoan,int month) {
        SQLiteDatabase db = getWritableDatabase();
        db.execSQL("UPDATE DataDudoan SET Month=?, LongMonth = ?, LongDt = ?, BdRt = ?, KtRt = ?, BhDt = ?,BhRt=? where Month = ?",
                new String[]{dudoan.getMonth()+"",dudoan.getLongMonth()+"",dudoan.getLongDt()+"",dudoan.getBdRt()+"",dudoan.getKtRt()+"",dudoan.getBhDt()+"",dudoan.getBhRt()+"",month+""});
        db.close();
    }

    //bh
    public void addBh(BieuHien bieuHien) {
        Log.d("Them BH",bieuHien.toString());
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(KEY_MONTH2, bieuHien.getMonth());
        values.put(KEY_DAY, bieuHien.getDay());
        values.put(KEY_BH, bieuHien.getBh());
        db.insert(TABLE_NAME2, null, values);
        db.close();
    }

    public List<BieuHien> getBieuhienMonth(int month) {
        List<BieuHien> Bieuhien = new ArrayList<>();
        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * from DataBH Where Month=?", new String[]{month+""});
        //Đến dòng đầu của tập dữ liệu
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            int m = cursor.getInt(0);
           int D = cursor.getInt(1);
           String Bh = cursor.getString(2);
            Bieuhien.add(new BieuHien(D,m,Bh));
            cursor.moveToNext();
        }
        cursor.close();
        return Bieuhien;
    }
    //this month
    public void addThisMonth(thisMonth thisMonth) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(KEY_MONTH1, thisMonth.getMonth());
        values.put(KEY_LONGMONTH1, thisMonth.getLongMonth());
        values.put(KEY_LONGDT1, thisMonth.getLongDt());
        values.put(KEY_BDRT1, thisMonth.getBdRt());
        values.put(KEY_KTRT1, thisMonth.getKtRt());
        db.insert(TABLE_NAME1, null, values);
        db.close();
    }
    @RequiresApi(api = Build.VERSION_CODES.N)
    public Dudoan matchDudoan(){
        //tinh rung trung
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor8 = db.query(TABLE_NAME1, new String[]{"AVG(BDRT)"},  "BDRT", null,null, null, null);
        if(cursor8 != null)
            cursor8.moveToFirst();
        int bdrt = (int) Math.round(cursor8.getDouble(0));


        Cursor cursor9 = db.query(TABLE_NAME1, new String[]{"AVG(KTRT)"},  "KTRT", null,null, null, null);
        if(cursor9 != null)
            cursor9.moveToFirst();
        int ktrt = (int) Math.round(cursor9.getDouble(0));


        // tinh TB Longmonth
        Cursor cursor2 = db.query(TABLE_NAME1, new String[]{"AVG(LongMonth)"},  "LongMonth", null,null, null, null);
        if(cursor2 != null)
            cursor2.moveToFirst();
        int TBLongMonth = (int) Math.round(cursor2.getDouble(0));
        //tinh tb dt
        Cursor cursor3 = db.query(TABLE_NAME1, new String[]{"AVG(LongDt)"},  "LongDt", null,null, null, null);
        if(cursor3 != null)
            cursor3.moveToFirst();
        int TBLongDt = (int) Math.round(cursor3.getDouble(0));
        //tinh Month
        Cursor cursor4 = db.query(TABLE_NAME1, new String[]{"MAX(Month)"},  "Month", null,null, null, null);
        if(cursor4 != null)
            cursor4.moveToFirst();
        int Month = cursor4.getInt(0)+1;
        //get bh
            //get all data
            List <Integer> bhrt = new ArrayList<>();
            List <Integer> bhdt = new ArrayList<>();
            List<thisMonth> thisMonths =getAllMonth();
            String bhrts="";
            String bhdts="";
            for (int i=0;i<=thisMonths.size()-1;i++){
                thisMonth thisMonth = thisMonths.get(i);
                Cursor cursor6 = db.rawQuery("SELECT BH from DataBH WHERE Month =? and Day > ? and Day< ? ",new String[]{thisMonth.getMonth()+"",(thisMonth.getBdRt()-3)+"",(thisMonth.getKtRt()+2)+""});
                //Đến dòng đầu của tập dữ liệu
                if(cursor6 != null) {
                    cursor6.moveToFirst();
                    while (!cursor6.isAfterLast()) {
                        String bhSt = cursor6.getString(0);
                        String[] bhs = bhSt.split(",");
                        for (int j = 0; j <= bhs.length - 1; j++) {
                            try {
                                if (!bhrt.contains(Integer.valueOf(bhs[j]))) {
                                    bhrt.add(Integer.valueOf(bhs[j]));
                                    bhrts = bhrts + "," + bhs[j];
                                }
                            }catch (NumberFormatException e){

                            }
                        }
                        cursor6.moveToNext();
                    }
                }
                cursor6.close();
                Cursor cursor7= db.rawQuery("SELECT BH from DataBH WHERE Month =? and  Day > ? and Day < ? ",new String[]{thisMonth.getMonth()+"",(thisMonth.getLongMonth()-5)+"",(thisMonth.getLongMonth()+thisMonth.getLongDt())+""});
                //Đến dòng đầu của tập dữ liệu
                cursor7.moveToFirst();
                if(cursor7 != null) {
                    while (!cursor7.isAfterLast()) {
                        String bhSt = cursor7.getString(0);
                        String[] bhs = bhSt.split(",");
                        for (int j = 0; j <= bhs.length - 1; j++) {
                            try {
                                if (!bhdt.contains(Integer.valueOf(bhs[j]))) {
                                    bhdt.add(Integer.valueOf(bhs[j]));
                                    bhdts = bhdts + "," + bhs[j];
                                }
                            }catch (NumberFormatException e){

                            }
                        }
                        cursor7.moveToNext();
                    }
                }
                cursor7.close();
            Log.d("bhdts",bhrts+" bh");
            getBieuhienMonth(thisMonth.getMonth()).forEach(p->  Log.d("thang ", thisMonth.getMonth()+"/"+p.toString()));
            }
        return new Dudoan(Month,TBLongMonth,TBLongDt,bdrt,ktrt,bhrts,bhdts);
    }
    public  void UpdateThisMonth(thisMonth thisMonth) {
        SQLiteDatabase db = getWritableDatabase();
        db.execSQL("UPDATE DataThisMonth SET Month=?,  LongMonth = ?, LongDT = ?,BDRT=?,KTRT=? where Month = ?",
                new String[]{String.valueOf(thisMonth.getMonth()), String.valueOf(thisMonth.getLongMonth()), String.valueOf(thisMonth.getLongDt()), String.valueOf(thisMonth.getBdRt()), String.valueOf(thisMonth.getKtRt()), String.valueOf(thisMonth.getMonth())});
    }

    public thisMonth getThisMonth(int month) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(TABLE_NAME1, null, KEY_MONTH1 + " = ?", new String[] { String.valueOf(month) },null, null, null);
        if(cursor != null)
            cursor.moveToFirst();
        thisMonth thisMonth = new thisMonth(cursor.getInt(0), cursor.getInt(1),cursor.getInt(2), cursor.getInt(3), cursor.getInt(4));
        return thisMonth;
    }
    public List<thisMonth> getAllMonth() {
        List<thisMonth> thisMonths = new ArrayList<>();
        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * from DataThisMonth ",null);
        //Đến dòng đầu của tập dữ liệu
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            int m = cursor.getInt(0);
            int D = cursor.getInt(1);
            int m1 = cursor.getInt(2);
            int D2 = cursor.getInt(3);
            int D3 = cursor.getInt(4);
            thisMonths.add(new thisMonth(m,D,m1,D2,D3));
            cursor.moveToNext();
        }
        cursor.close();
        return thisMonths;
    }

    //stt this month
    public void addSttThisMonth(SttThisMonth SttThisMonth) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(KEY_DATE_UPDATE, SttThisMonth.getDateUpdate());
        values.put(KEY_ID_STT,SttThisMonth.getId());
        values.put(KEY_COUNT_DT,SttThisMonth.getCountDt());
        values.put(KEY_COUNT_RT, SttThisMonth.getCountRT());
        values.put(KEY_BH_TODAY, SttThisMonth.getBhToday());
        values.put(KEY_NOTE, SttThisMonth.getNote());
        values.put(KEY_LIST_BH, SttThisMonth.getListBh());
        values.put(KEY_NN, SttThisMonth.getNn());
        db.insert(TABLE_NAME3, null, values);
        db.close();
    }
    public void UpdateSttThisMonth(SttThisMonth sttThisMonth,int id) {
        SQLiteDatabase db = getWritableDatabase();
        db.execSQL("UPDATE DataSttThisMonth SET DateUpDate=?,IdStt= ?,CountDt = ?,CountRt=?,BhToday=?,ListBh=?,Note=?,Nn=? where IdStt = ?",
                new String[]{ sttThisMonth.getDateUpdate(),1+"",String.valueOf(sttThisMonth.getCountDt()), String.valueOf(sttThisMonth.getCountRT()), String.valueOf(sttThisMonth.getBhToday()), String.valueOf(sttThisMonth.getListBh()), String.valueOf(sttThisMonth.getNote()), String.valueOf(sttThisMonth.getNn()),String.valueOf(id)});
    }
    public SttThisMonth getSttThisMonth(int Id) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(TABLE_NAME3, null, KEY_ID_STT+ " = ?", new String[] {String.valueOf(Id)},null, null, null);
        if(cursor != null)
            cursor.moveToFirst();
        SttThisMonth sttThisMonth = new SttThisMonth(Id,cursor.getString(0), cursor.getInt(2),cursor.getInt(3), cursor.getString(4), cursor.getString(5), cursor.getString(6), cursor.getString(7));
        return sttThisMonth;
    }


    // table user
    public void addUser(DataUser dataUser) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(KEY_ID, dataUser.getId());
        values.put(KEY_NAME, dataUser.getName());
        values.put(KEY_BORN,dataUser.getBorn());
        values.put(KEY_TOKEN, dataUser.getToken());
        values.put(KEY_DAY1, dataUser.getDay());
        db.insert(TABLE_NAME5, null, values);
        db.close();
    }

    public void UpdateUser(DataUser user1) {
        SQLiteDatabase db = getWritableDatabase();
        db.execSQL("UPDATE DataUser SET Id=?,Name=?,Born=?,Token=?,Day=? WHERE Id=?",
                new String[]{1+"",user1.getName(),user1.getBorn(),user1.getToken(),user1.getDay()+"",1+""});
    }
    public DataUser getDataUser(int id) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(TABLE_NAME5, null, KEY_ID+ " = ?", new String[] {id+""},null, null, null);
        DataUser dataUser = new DataUser();
        if(cursor != null) {
            cursor.moveToFirst();
             dataUser = new DataUser(cursor.getInt(0), cursor.getString(1), cursor.getString(2), cursor.getString(3), cursor.getInt(4));
        }
        return dataUser;
    }

    public String getString(JSONArray array){
        String data="";
        for (int i = 0; i <= array.length()-1; i++) {
            try {
                data=data+","+array.get(i);
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        return data;
    }
    public  JSONArray getJSONArray(String string){
        JSONArray arr = new JSONArray();
        String[] listbh = string.split(",");
        for (int i = 0; i <=listbh.length-1; i++) {
            if (!listbh[i].equals("")){
                arr.put(Integer.valueOf(listbh[i]));
            }
        }
        return arr;
    }
}