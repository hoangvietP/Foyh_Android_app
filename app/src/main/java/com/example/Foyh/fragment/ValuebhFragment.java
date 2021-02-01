package com.example.Foyh.fragment;

import android.annotation.SuppressLint;
import android.app.Fragment;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.core.content.ContextCompat;

import com.example.Foyh.R;
import com.example.Foyh.testui.Data.classDT.DatabaseHandler;
import com.example.Foyh.testui.Data.classDT.SttThisMonth;
import com.example.Foyh.testui.Notification.ExampleService;

import org.json.JSONArray;
import org.json.JSONException;

@SuppressLint("ValidFragment")
public class ValuebhFragment extends Fragment {
    View view;
    int[] viewbh = {114,114,114,114,114,114,114,114,114,114};
    JSONArray sttv = null;
    JSONArray sttic = null;
    TextView tv00;
    DatabaseHandler databaseHandler;
    SttThisMonth sttThisMonth;
    Context context;
    private Context mContext;
    public ValuebhFragment(DatabaseHandler databaseHandler,Context context){
         this.databaseHandler = databaseHandler;
         this.context = context;
        this.sttThisMonth = databaseHandler.getSttThisMonth(1);
        this.sttv= databaseHandler.getJSONArray(sttThisMonth.getBhToday());
        this.sttic= databaseHandler.getJSONArray(sttThisMonth.getListBh());

    }
    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mContext=context;
    }
    ImageView img1,img2,img3,img4,img5,img6,img7,img8,img9,img10;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.bh, container, false);
        tv00 = view.findViewById(R.id.tv00);
        img1 =  this.view.findViewById(R.id.image_view_expanded1);
        img2 =  this.view.findViewById(R.id.image_view_expanded2);
        img3 =  this.view.findViewById(R.id.image_view_expanded3);
        img4 =  this.view.findViewById(R.id.image_view_expanded4);
        img5 =  this.view.findViewById(R.id.image_view_expanded5);
        img6 =  this.view.findViewById(R.id.image_view_expanded6);
        img7 =  this.view.findViewById(R.id.image_view_expanded7);
        img8 =  this.view.findViewById(R.id.image_view_expanded8);
        img9 =  this.view.findViewById(R.id.image_view_expanded9);
        img10 =  this.view.findViewById(R.id.image_view_expanded10);

        LinearLayout li = view.findViewById(R.id.linear1);
        Animation right = AnimationUtils.loadAnimation(mContext, R.anim.inright);
        li.startAnimation(right);

        try {
            setview();
            if (sttic.getInt(0)==12){

                tv00.setText("Hái dâu");
                if (viewbh[0]==114) {
                    img1.setImageResource(R.drawable.icnotification3);
                }else {
                    img1.setImageResource(R.drawable.icnotification3);
                    viewbh[0]=114;
                }
            }else if (sttic.getInt(0)==13){
                tv00.setText("Hết mùa dâu");
                if (viewbh[0]==114) {
                    img1.setImageResource(R.drawable.icnotification113);
                }else {
                    img1.setImageResource(R.drawable.icnotification113);
                    viewbh[0]=114;
                }
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }


        img1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {

                    if (sttic.getInt(0)==12){

                        tv00.setText("Hái dâu");
                        if (viewbh[0]==114) {
                            img1.setImageResource(R.drawable.iconclick5);
                            viewbh[0]=12;
                        }else {
                            img1.setImageResource(R.drawable.icnotification3);
                            viewbh[0]=114;
                        }
                    }else if (sttic.getInt(0)==13){
                        tv00.setText("Hết mùa dâu");
                        if (viewbh[0]==114) {
                            img1.setImageResource(R.drawable.icnotification113);
                            viewbh[0]=13;
                        }else {
                            img1.setImageResource(R.drawable.icnotification113);
                            viewbh[0]=114;
                        }
                    } else {
                        tv00.setText("Tôi ổn");
                        if (viewbh[0]==114) {
                            img1.setImageResource(R.drawable.iconclick111);
                            viewbh[0]=1;
                        }else {
                            img1.setImageResource(R.drawable.icnotification111);
                            viewbh[0]=114;
                        }
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });
        img2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (viewbh[1]==114) {
                    img2.setImageResource(R.drawable.iconclick110);
                    viewbh[1]=2;
                }else {
                    img2.setImageResource(R.drawable.icnotification110);
                    viewbh[1]=114;
                }
            }
        });
        img3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (viewbh[2]==114) {
                    img3.setImageResource(R.drawable.iconclick11);
                    viewbh[2]=3;
                }else {
                    img3.setImageResource(R.drawable.icnotification4);
                    viewbh[2]=114;
                }
            }
        });
        img4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (viewbh[3]==114) {
                    img4.setImageResource(R.drawable.iconclick8);
                    viewbh[3]=4;
                }else {
                    img4.setImageResource(R.drawable.icnotification2);
                    viewbh[3]=114;
                }
            }
        });
        img5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (viewbh[4]==114) {
                    img5.setImageResource(R.drawable.iconclick2);
                    viewbh[4]=5;
                }else {
                    img5.setImageResource(R.drawable.icnotification5);
                    viewbh[4]=114;
                }
            }
        });
        img6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (viewbh[5]==114) {
                    img6.setImageResource(R.drawable.iconclick4);
                    viewbh[5]=6;
                }else {
                    img6.setImageResource(R.drawable.icnotification7);
                    viewbh[5]=114;
                }
            }
        });
        img7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (viewbh[6]==114) {
                    img7.setImageResource(R.drawable.iconclick3);
                    viewbh[6]=7;
                }else {
                    img7.setImageResource(R.drawable.icnotification6);
                    viewbh[6]=114;
                }
            }
        });
        img8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (viewbh[7]==114) {
                    img8.setImageResource(R.drawable.iconclick9);
                    viewbh[7]=8;
                }else {
                    img8.setImageResource(R.drawable.icnotification10);
                    viewbh[7]=114;
                }
            }
        });
        img9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (viewbh[8]==114) {
                    img9.setImageResource(R.drawable.iconclick6);
                    viewbh[8]=9;
                }else {
                    img9.setImageResource(R.drawable.icnotification1);
                    viewbh[8]=114;
                }
            }
        });
        img10.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (viewbh[9]==114) {
                    img10.setImageResource(R.drawable.iconclick10);
                    viewbh[9]=10;
                }else {
                    img10.setImageResource(R.drawable.icnotification8);
                    viewbh[9]=114;
                }
            }
        });

        Button cnbh = view.findViewById(R.id.cnbh);
        cnbh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                JSONArray bh = new JSONArray();
                for (int i=0;i<=viewbh.length-1;i++){
                    if (viewbh[i]!=114){
                        if (viewbh[i]==12){
                            bh.put(12);
                        }else if(viewbh[i]==13){
                            bh.put(13);
                        }else {
                        bh.put(i);}
                    }

                }

                sttThisMonth.setBhToday(new DatabaseHandler(mContext).getString(bh));
                databaseHandler.UpdateSttThisMonth(sttThisMonth,1);
                Log.d("Cap nhat BH",databaseHandler.getSttThisMonth(1).toString());
                serviceIntent= new Intent(mContext, ExampleService.class);
                ContextCompat.startForegroundService(mContext, serviceIntent);
                Toast.makeText(mContext,"Đã cập nhật biểu hiện!",
                        Toast.LENGTH_SHORT).show();
            }
        });

        return view;
    }

        Intent serviceIntent;

    public void setview() throws JSONException {
        for (int i=0;i<=sttv.length()-1;i++){

            switch (sttv.getInt(i)){
                case 0 :
                    img1.setImageResource(R.drawable.iconclick111);
                    break;
                case 1 :
                    img2.setImageResource(R.drawable.iconclick110);
                    break;
                case 2 :
                    img3.setImageResource(R.drawable.iconclick11);
                    break;
                case 3 :
                    img4.setImageResource(R.drawable.iconclick1);
                    break;
                case 4 :
                    img5.setImageResource(R.drawable.iconclick2);
                    break;
                case 5 : img6.setImageResource(R.drawable.iconclick4);
                    break;
                case 6 : img7.setImageResource(R.drawable.iconclick3);
                    break;
                case 7 : img8.setImageResource(R.drawable.iconclick9);
                    break;
                case 8 : img9.setImageResource(R.drawable.iconclick6);
                    break;
                case 9 : img10.setImageResource(R.drawable.iconclick10);
                    break;
                case 12 : img1.setImageResource(R.drawable.iconclick5);
                    break;
            }



        }
    }

}