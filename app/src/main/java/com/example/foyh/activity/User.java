package com.example.foyh.activity;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.foyh.R;
import com.example.foyh.fragment.BhFragment;
import com.example.foyh.fragment.ValuebhFragment;
import com.example.foyh.testui.Data.classDT.fileDAO;
import com.example.foyh.testui.Data.service.DataServiceMethod;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.components.Description;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.utils.ColorTemplate;


import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

public class User extends AppCompatActivity {
//    private AnyChartView Chart;
    PieChart chart;
    TextView date,day;
    ImageView prf;
    String[] vl = {"Mùa dâu","Mùa Trứng","Ngày thường"};
    int[] dd ={5,6,20};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTheme(R.style.Theme_AppCompat_Light_NoActionBar);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN
                , WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_user);
        overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);






        chart = (PieChart) findViewById(R.id.piechart);
        chart.setRotationEnabled(true);
        chart.setDescription(new Description());
        chart.setHoleRadius(38f);
        chart.setTransparentCircleAlpha(0);
        chart.setCenterTextSize(10);
        chart.setEntryLabelTextSize(9f);
        chart.setClickable(true);
        Legend legend=chart.getLegend();
        legend.setForm(Legend.LegendForm.CIRCLE);
        legend.setPosition(Legend.LegendPosition.BELOW_CHART_CENTER);
        try {
            setupChart();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }

        JSONArray bh = new JSONArray();
        try {
            loadFragment(new ValuebhFragment(getStt()));
        } catch (JSONException e) {
            e.printStackTrace();
        }


        prf= (ImageView)findViewById(R.id.prf);
        prf.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(User.this,profileActivity.class);
                startActivity(intent);
            }
        });

    }
private  JSONObject getStt(){
    fileDAO file = new fileDAO();
    JSONObject objstt=null;
    try{
         objstt=  file.getData("stt.json","data",this);//get data from stt file

    }catch (FileNotFoundException | JSONException|NullPointerException d){

    }
    return objstt;
}

    private void loadFragment(Fragment fragment) {
// create a FragmentManager
        FragmentManager fm = getFragmentManager();
        FragmentTransaction fragmentTransaction = fm.beginTransaction();
        fragmentTransaction.replace(R.id.framebh,fragment);
        fragmentTransaction.commit(); // save the changes
        setDate();

    }
    public void setupChart() throws FileNotFoundException, JSONException {

        fileDAO file = new fileDAO();
        JSONObject ob=  file.getData("thisMonthData.json","data",this);
        String dateUpdate= ob.getString("dayupdate");
        JSONArray ard=ob.getJSONArray("dataday");

        JSONArray dt = ard;
        int daycount = dt.getInt(0);//today
        int month =dt.getInt(1);// count month
        int longMo = ard.getInt(2);
        int longdt= ard.getInt(3);
        JSONArray trt = ard.getJSONArray(4);
        int[] dayrt= {trt.getInt(0),trt.getInt(1)};

        day= findViewById(R.id.day);
        day.setText("Ngày thứ: "+daycount+" của chu kì");



        ArrayList<PieEntry> visi = new ArrayList<>();
        visi.add(new PieEntry(longdt,"Mùa dâu"));
        visi.add(new PieEntry(-trt.getInt(0)+trt.getInt(1),"Mùa trứng"));
        visi.add(new PieEntry(longMo-(-trt.getInt(0)+trt.getInt(1)),"Ngày an toàn"));

        PieDataSet pieDataSet = new PieDataSet(visi,"");
        pieDataSet.setFormSize(20);
        pieDataSet.setColors(ColorTemplate.COLORFUL_COLORS);
        pieDataSet.setValueTextColor(R.color.white);
        pieDataSet.setValueTextSize(10f);
        List<Integer> colors = new ArrayList<>();
        colors.add(R.color.white);
        colors.add(R.color.white);
        colors.add(R.color.white);
        pieDataSet.setValueTextColors(colors);

        PieData pieData = new PieData(pieDataSet);

        chart.setData(pieData);
        chart.getDescription().setEnabled(false);
        chart.setCenterText("Tổng: 30 ngày");
        chart.setCenterTextColor(R.color.white);
        chart.animate();
    }

    public void setDate(){
        date= findViewById(R.id.date);
        date.setText(new DataServiceMethod().getDate());
    }

}