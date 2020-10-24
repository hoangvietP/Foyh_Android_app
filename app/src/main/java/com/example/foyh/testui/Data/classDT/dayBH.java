package com.example.foyh.testui.Data.classDT;

import java.util.ArrayList;

public class dayBH {
    private ArrayList<String> BH= new ArrayList<>();

    public void addBh(String[] bh){
        for(int i=0;i<=bh.length-1;i++){
            this.BH.add(bh[i]);
        }
    }

    public ArrayList<String> getBH() {
        return BH;
    }

    @Override
    public String toString() {
        return  BH +"";
    }
}
