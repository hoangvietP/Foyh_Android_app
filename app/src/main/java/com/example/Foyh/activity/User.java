package com.example.Foyh.activity;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Context;
import android.icu.util.Calendar;
import android.os.Build;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.Foyh.R;
import com.example.Foyh.fragment.PiechartFragment;
import com.example.Foyh.fragment.ValuebhFragment;
import com.example.Foyh.testui.Data.classDT.DatabaseHandler;
import com.example.Foyh.testui.Data.service.DataServiceMethod;


import java.text.SimpleDateFormat;

public class User extends AppCompatActivity {
//    private AnyChartView Chart;
    View animate;
    String nnn1 ="";
    TextView nhacnho;
    Dialog dialog;
    Context context;
    int kk =1;
    LinearLayout linearLayout,linearLayout1;
    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTheme(R.style.Theme_AppCompat_Light_NoActionBar);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN
                , WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.home_user);
        overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
        TextView date = findViewById(R.id.date);
        setDate(date);
        DisplayMetrics displayMetrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        int height = displayMetrics.heightPixels;
        context= this;
        int width = displayMetrics.widthPixels;
        TextView tv = findViewById(R.id.date);
        TextView tv1 = findViewById(R.id.day);
        TextView tv2 = findViewById(R.id.sttDay);
        ImageView iconsttday = findViewById(R.id.iconSttDay);
        tv.setTextSize(height/90);
        tv1.setTextSize(height/125);
        tv2.setTextSize(height/150);



        DatabaseHandler databaseHandler = new DatabaseHandler(this);
        tv1.setText("Ngày thứ: "+new DataServiceMethod().getCountDay(databaseHandler.getSttThisMonth(1).getDateUpdate()));
        tv2.setText(databaseHandler.getSttThisMonth(1).getNn());
        if (databaseHandler.getSttThisMonth(1).getNn().equals("Ngày an toàn")){
            iconsttday.setImageResource(R.drawable.dd);
        }else {
            iconsttday.setImageResource(R.drawable.dx);
        }

        String note =databaseHandler.getSttThisMonth(1).getNote();
        String[] mt = note.split("Trứng");
        Boolean mt1 = note.equals("Sắp tới mùa trứng");
        String[] md = note.split("Hành kinh ngày");
        Boolean md1 = note.equals("Sắp tới mùa dâu");

        if (mt.length>1 || mt1==true){
            nnn1="Nếu k có kế hoạch mang thai \n hãy dùng các biện pháp an toàn\ntrong những ngày này!\n Click vào ĐÂY để xem thêm.";
            kk=2;
        }
        if (md.length>1 || md1==true){
            nnn1="Không nên quan hệ, lao động nặng,\n chú ý nghỉ ngơi,...\n click vào ĐÂY xem chi tiết.";
            kk=3;
        }
        loadFragment(new ValuebhFragment(new DatabaseHandler(this),this));
        FragmentManager fm = getFragmentManager();
            FragmentTransaction fragmentTransaction = fm.beginTransaction();
            fragmentTransaction.replace(R.id.frameLayout1,new PiechartFragment(this,this));
            fragmentTransaction.commit(); // save the changes

        nhacnho = findViewById(R.id.nhacnho);
        animate = findViewById(R.id.animate);
        animate.setVisibility(View.GONE);
        Log.d("nhac nho",nnn1+" nhc"+note.split("Sắp tới mùa trứng").length);
        if (!nnn1.equals("")){
        new CountDownTimer(3000, 100) {
            public void onTick(long millisUntilFinished) {

            }
            public void onFinish() {
                Thread.currentThread();
                nhacnho.setText(nnn1);
                nhacnho.setTextSize(10);
                animate.setVisibility(View.VISIBLE);
            }
        }.start();}
        Activity activity = this;
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        nhacnho.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (kk==2){
                    builder.setView(R.layout.dialog_rt);
                }else if (kk==3){
                    builder.setView(R.layout.dialog_md);
                }

                builder.setCancelable(true);
                dialog= builder.create();
                dialog.show();
            }
        });



////        ManagerDataBase managerDataBase = new ManagerDataBase(this);
////        managerDataBase.addNewDudoan(new Dudoan(1,30,4,13,18,"1,2,3","3,5,7"));
//        DatabaseHandler databaseHandler = new DatabaseHandler(this);
////        databaseHandler.addNewDD(new Dudoan(1,30,30,30,18,"1,2,3","3,5,7"));
////        databaseHandler.updateDudoan(new Dudoan(1,13,13,13,18,"1,2,3","3,5,7"),1);
////        databaseHandler.addUser(new DataUser(1,"Pham Hoang Viet","04/02/2000","token1"));
////        databaseHandler.addBh(new BieuHien(1,1,"22"));
////        List<BieuHien> bhs=databaseHandler.getBieuhienMonth(1);
////        bhs.forEach(e->Log.d("line146",e.toString()));
////        Log.d("line 139",databaseHandler.getDataUser(1).toString());
////        databaseHandler.addSttThisMonth(new SttThisMonth(1,"04/02/2000",0,0,"bhtoday","listbh","note","Nn"));
////        databaseHandler.UpdateSttThisMonth(new SttThisMonth(1,"10/02/2000",1,1,"bhtoday","listbh","note","Nn"),1);
////        databaseHandler.UpdateThisMonth(new thisMonth(1,30,5,12,15));
////        databaseHandler.addThisMonth(new thisMonth(1,30,4,13,15));
////        databaseHandler.addThisMonth(new thisMonth(2,25,4,13,15));
////        databaseHandler.addThisMonth(new thisMonth(3,29,4,13,15));
////
////        databaseHandler.addBh(new BieuHien(11,1,"12"));
////        databaseHandler.addBh(new BieuHien(12,1,"13"));
////        databaseHandler.addBh(new BieuHien(14,1,"14"));
////        databaseHandler.addBh(new BieuHien(15,1,"15"));
////
////        databaseHandler.addBh(new BieuHien(11,2,"22"));
////        databaseHandler.addBh(new BieuHien(12,2,"23"));
////        databaseHandler.addBh(new BieuHien(14,2,"24"));
////        databaseHandler.addBh(new BieuHien(16,2,"25"));
////
////        databaseHandler.addBh(new BieuHien(11,3,"32"));
////        databaseHandler.addBh(new BieuHien(12,3,"33"));
////        databaseHandler.addBh(new BieuHien(14,3,"34"));
////        databaseHandler.addBh(new BieuHien(16,3,"35"));
////
////
////        databaseHandler.addBh(new BieuHien(26,1,"12"));
////        databaseHandler.addBh(new BieuHien(30,1,"13"));
////        databaseHandler.addBh(new BieuHien(32,1,"14"));
////        databaseHandler.addBh(new BieuHien(20,2,"15"));
////        databaseHandler.addBh(new BieuHien(25,2,"12"));
////        databaseHandler.addBh(new BieuHien(27,2,"13"));
////        databaseHandler.addBh(new BieuHien(28,3,"14"));
////        databaseHandler.addBh(new BieuHien(31,3,"15"));
////
//////        databaseHandler.matchDudoan();
////        Log.d("Doi tuong du doan moi",databaseHandler.matchDudoan().toString());
    }

    private void loadFragment(Fragment fragment) {
// create a FragmentManager
        FragmentManager fm = getFragmentManager();
        FragmentTransaction fragmentTransaction = fm.beginTransaction();
        fragmentTransaction.replace(R.id.framebh,fragment);
        fragmentTransaction.commit(); // save the changes

    }


    @RequiresApi(api = Build.VERSION_CODES.N)
    public void setDate(TextView date1){
        String date=null;
        Calendar c = null;
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.N) {
            c = Calendar.getInstance();
            SimpleDateFormat df = new SimpleDateFormat("EE");
            date = df.format(c.getTime());
            date1.setText(date+", "+new DataServiceMethod().getDay()+" thg "+new DataServiceMethod().getMonth());
        }

    }

}