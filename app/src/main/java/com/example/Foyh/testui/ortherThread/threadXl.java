package com.example.Foyh.testui.ortherThread;

import android.content.Context;
import android.util.Log;


public class threadXl extends Thread {
    Context context;
    public threadXl(Context context){
        this.context=context;
    }
        public void realtimeSt() throws InterruptedException {
            while (true){
                Log.d("count","ok");
                Thread.sleep(2000);
            }
        }



   }