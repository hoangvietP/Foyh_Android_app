package com.example.Foyh.fragment;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.Fragment;
import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;


import com.example.Foyh.R;
import com.example.Foyh.testui.Data.classDT.DatabaseHandler;
import com.example.Foyh.testui.Data.classDT.Dudoan;
import com.example.Foyh.testui.Data.classDT.SttThisMonth;
import com.example.Foyh.testui.Data.classDT.thisMonth;
import com.example.Foyh.testui.Data.service.DataServiceMethod;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.components.Description;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.highlight.Highlight;
import com.github.mikephil.charting.listener.OnChartValueSelectedListener;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.FileNotFoundException;
import java.util.ArrayList;

@SuppressLint("ValidFragment")
public class PiechartFragment extends Fragment {
    View view;       PieChart chart;
    private Context mContext;
    TextView tv,at,kat,md,mt;
    LinearLayout linearLayout,linearLayout1;
    Activity activity;
    Dialog dialog;
    JSONObject objCT = new JSONObject();
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        try {
            objCT = getCt();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }
        view = inflater.inflate(R.layout.pie_chart, container, false);
        at= view.findViewById(R.id.at);
        kat= view.findViewById(R.id.kat);
        md= view.findViewById(R.id.md);
        mt= view.findViewById(R.id.mt);
        chart = (PieChart) view.findViewById(R.id.piechart);
        chart.setRotationEnabled(true);
        chart.setDescription(new Description());
        chart.setHoleRadius(50f);
        chart.setTransparentCircleAlpha(80);
        chart.setCenterTextSize(10);
        chart.setCenterTextColor(R.color.colorPink);
        chart.setEntryLabelTextSize(10f);
        chart.setOnChartValueSelectedListener(new OnChartValueSelectedListener() {
            @Override
            public void onValueSelected(Entry e, Highlight h) {

                AlertDialog.Builder builder = new AlertDialog.Builder(activity);
                LayoutInflater inflater = activity.getLayoutInflater();
                if (h.getX()==1.0){
                    View viewDialog = inflater.inflate(R.layout.dialog_rungtrung, container, false);
                    builder.setView(viewDialog);
                    builder.setCancelable(true);
                    TextView tvv = viewDialog.findViewById(R.id.mtt);
                    try {
                        tvv.setText(objCT.get("mt")+"");
                    } catch (JSONException ex) {
                        ex.printStackTrace();
                    }

                    dialog= builder.create();
                    dialog.show();
                     linearLayout= viewDialog.findViewById(R.id.chuy);
                    linearLayout1 = viewDialog.findViewById(R.id.dh);
                    linearLayout.setVisibility(View.GONE);
                    linearLayout1.setVisibility(View.GONE);
                    tv = viewDialog.findViewById(R.id.xemthem);
                    tv.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        tv.setVisibility(view.GONE);
                        linearLayout.setVisibility(View.VISIBLE);
                        linearLayout1.setVisibility(View.VISIBLE);
                    }
                });


                }
                if (h.getX()==0.0){
                    View viewDialog = inflater.inflate(R.layout.dialog_muadau, container, false);
                    builder.setView(viewDialog);
                    builder.setCancelable(true);
                    dialog= builder.create();
                    dialog.show();
                    linearLayout= viewDialog.findViewById(R.id.chuy1);
                    linearLayout1 = viewDialog.findViewById(R.id.dh1);
                    linearLayout.setVisibility(View.GONE);
                    linearLayout1.setVisibility(View.GONE);
                    TextView tvv = viewDialog.findViewById(R.id.mdd);
                    try {
                        tvv.setText(objCT.get("dt")+"");
                    } catch (JSONException ex) {
                        ex.printStackTrace();
                    }
                    tv = viewDialog.findViewById(R.id.xemthem1);
                    tv.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            tv.setVisibility(view.GONE);

                            linearLayout.setVisibility(View.VISIBLE);
                            linearLayout1.setVisibility(View.VISIBLE);
                        }
                    });
                }

            }

            @Override
            public void onNothingSelected() {

            }
        });
        chart.setTouchEnabled(true);
        try {
            setupChart();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }

        try {
            doin();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return view;
    }

    public PiechartFragment(Context mContext ,Activity activity){
        this.mContext= mContext;
            this.activity = activity;
    }

    public void setupChart() throws FileNotFoundException, JSONException {


        DatabaseHandler databaseHandler = new DatabaseHandler(mContext);
        Dudoan dudoan = databaseHandler.getDudoan();
        int longmonth = dudoan.getLongMonth();
        int longdt = dudoan.getLongDt();
        int bdrt = dudoan.getBdRt();
        int ktrt = dudoan.getKtRt();

        ArrayList<PieEntry> visi = new ArrayList<>();
        PieEntry pi = new PieEntry(ktrt-bdrt,ktrt-bdrt+" Ngày",R.drawable.eggicon);
        visi.add(new PieEntry(longdt,longdt+" Ngày"));
        visi.add(pi);
        visi.add(new PieEntry((bdrt-4)+(longmonth-ktrt-4),(bdrt-4)+(longmonth-ktrt-4)+" Ngày"));
        visi.add(new PieEntry(longmonth-((bdrt-4)+(longmonth-ktrt-4)),longmonth-((bdrt-4)+(longmonth-ktrt-4))+" Ngày"));
        PieDataSet pieDataSet = new PieDataSet(visi,"click vào biểu đồ");
        pieDataSet.setFormSize(10);
        int[] colorss = new int[]{R.color.Tomato, R.color.Orange, R.color.MediumSeaGreen,R.color.CornflowerBlue};
        pieDataSet.setColors(colorss,mContext);
        pieDataSet.setValueTextSize(10f);
        pieDataSet.setLabel("click vào biểu đồ");
        pieDataSet.setDrawValues(false);
        PieData pieData = new PieData(pieDataSet);
        pieData.setValueTextColor(Color.WHITE);

        chart.setData(pieData);
        chart.getDescription().setEnabled(false);
        chart.getLegend().setEnabled(false);
        chart.setCenterText("Tổng: "+longmonth+" ngày");
        chart.animateY(1000);
    }



    public void doin() throws FileNotFoundException, JSONException {

        DataServiceMethod dataServiceMethod = new DataServiceMethod();
        DatabaseHandler databaseHandler = new DatabaseHandler(mContext);
        Dudoan dudoan = databaseHandler.getDudoan();
        SttThisMonth sttThisMonth = databaseHandler.getSttThisMonth(1);
        thisMonth thisMonth = databaseHandler.getThisMonth(dudoan.getMonth());
        int today= new DataServiceMethod().getCountDay(sttThisMonth.getDateUpdate());
        int bdrt = dudoan.getBdRt();
        int ktrt = dudoan.getKtRt();
        int longMonth = dudoan.getLongMonth();
        int longDT = dudoan.getLongDt();
        String dateUpdate = sttThisMonth.getDateUpdate();
        String mt="";
        String md = "";
            mt= "Mùa trứng: "+dataServiceMethod.dateSetup(dateUpdate,bdrt)+" - "+dataServiceMethod.dateSetup(dateUpdate,ktrt);

        if (thisMonth.getLongMonth() ==0){
            md= "Mùa dâu: "+dataServiceMethod.dateSetup(dateUpdate,longMonth)+" - "+dataServiceMethod.dateSetup(dateUpdate,longMonth+longDT);
        }else {
            md="Hành kinh ngày: " + String.valueOf(today - thisMonth.getLongMonth() + 1);;
        }

        this.mt.setText(mt);
        this.md.setText(md);
        at.setText("An toàn: "+new DataServiceMethod().dateSetup(dateUpdate,1)
                +" - "+new DataServiceMethod().dateSetup(dateUpdate,(bdrt-4))+"\n Và "+
                new DataServiceMethod().dateSetup(dateUpdate,ktrt+4)+" - "+new DataServiceMethod().dateSetup(dateUpdate,longMonth));
        kat.setText( "K An toàn: "+new DataServiceMethod().dateSetup(dateUpdate,(bdrt-4))+" - "+new DataServiceMethod().dateSetup(dateUpdate,ktrt+4));
    }
    public  JSONObject getCt() throws FileNotFoundException, JSONException {
        DataServiceMethod dataServiceMethod = new DataServiceMethod();
        DatabaseHandler databaseHandler = new DatabaseHandler(mContext);
        Dudoan dudoan = databaseHandler.getDudoan();
        SttThisMonth sttThisMonth = databaseHandler.getSttThisMonth(1);
        thisMonth thisMonth = databaseHandler.getThisMonth(dudoan.getMonth());
        int today= new DataServiceMethod().getCountDay(sttThisMonth.getDateUpdate());
        int bdrt = dudoan.getBdRt();
        int ktrt = dudoan.getKtRt();
        int longMonth = dudoan.getLongMonth();
        int longDT = dudoan.getLongDt();
        String dateUpdate = sttThisMonth.getDateUpdate();
        String mt="";
        String md = "";
        mt= "Mùa trứng: "+dataServiceMethod.dateSetup(dateUpdate,bdrt)+" - "+dataServiceMethod.dateSetup(dateUpdate,ktrt);
        md= "Mùa dâu: "+dataServiceMethod.dateSetup(dateUpdate,longMonth)+" - "+dataServiceMethod.dateSetup(dateUpdate,longMonth+longDT);
        JSONObject objDT = new JSONObject();
        objDT.put("dt",md);
        objDT.put("mt",mt);
        return objDT;
    }
}
