package com.example.foyh.fragment;

import android.annotation.SuppressLint;
import android.app.Fragment;
import android.content.Context;
import android.graphics.drawable.Drawable;
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
import java.util.Collections;
import java.util.List;

@SuppressLint("ValidFragment")
public class PiechartFragment extends Fragment {
    View view;        TextView date,day;PieChart chart;
    private Context mContext;
JSONArray ard= new JSONArray();
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.pie_chart, container, false);

        chart = (PieChart) view.findViewById(R.id.piechart);
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



        return view;
    }

    public PiechartFragment(Context mContext,JSONArray ard){
        this.mContext= mContext;
            this.ard= ard;
    }

    public void setupChart() throws FileNotFoundException, JSONException {
        JSONArray dt = ard;
        int daycount = dt.getInt(0);//today
        int month =dt.getInt(1);// count month
        int longMo = ard.getInt(2);
        int longdt= ard.getInt(3);
        JSONArray trt = ard.getJSONArray(4);



        ArrayList<PieEntry> visi = new ArrayList<>();
        visi.add(new PieEntry(longdt,"Mùa dâu"));
        visi.add(new PieEntry(trt.getInt(1)-trt.getInt(0),"Mùa trứng"));
        visi.add(new PieEntry(trt.getInt(1)-trt.getInt(0)+10,"Không an toàn"));
        visi.add(new PieEntry(longMo-(trt.getInt(1)-trt.getInt(0)+10),"An toàn"));

//        List<Integer> colors = new ArrayList<>();
//        List<Integer> colors1 = new ArrayList<>();
//        int at=0;
//        int rt=0;
//        int md=0;
//        for (int i=1; i<=longMo+longdt;i++){
//            visi.add(new PieEntry(1));
//            if (i<trt.getInt(0)-5){
//                colors1.add(R.color.white);
//                colors.add(R.color.white);
//                at++;
//            }else if (i>= trt.getInt(0) && i< trt.getInt(1)+5){
//                colors1.add(R.color.colorPrimary);
//                colors.add(R.color.colorPrimary);
//                rt++;
//            }else if (i> trt.getInt(1)+5 && i< longMo){
//                colors1.add(R.color.white);
//                colors.add(R.color.white);
//                at++;
//            }else if (i>= longMo && i< longMo+longdt){
//                colors1.add(R.color.colorAccent);
//                colors.add(R.color.colorAccent);
//                md++;
//            }
//        }
        PieDataSet pieDataSet = new PieDataSet(visi,"");
        pieDataSet.setFormSize(0);
        pieDataSet.setColors(ColorTemplate.COLORFUL_COLORS);
//        pieDataSet.setColors(colors1);
        pieDataSet.setValueTextSize(10f);

        pieDataSet.setValueTextColors(Collections.singletonList(R.color.white));

        PieData pieData = new PieData(pieDataSet);

        chart.setData(pieData);
        chart.getDescription().setEnabled(false);
        chart.setCenterText("Tổng: "+longMo+" ngày");
        chart.setCenterTextColor(R.color.white);
        chart.animate();
    }
}
