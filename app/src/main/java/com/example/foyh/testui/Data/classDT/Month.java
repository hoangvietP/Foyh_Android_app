package com.example.foyh.testui.Data.classDT;


import java.util.Arrays;


public class Month {
    private int nameM;
    private int longMonth;
    private int longDT;
    private int[] WRT= new int[2];

    public Month(int nameM, int longMonth, int longDT, int[] WRT) {
        this.nameM = nameM;
        this.longMonth = longMonth;
        this.longDT = longDT;
        this.WRT = WRT;
    }

    public int[] getWRT() {
        return WRT;
    }

    public void setWRT(int[] WRT) {
        this.WRT = WRT;
    }

    public int getNameM() {
        return nameM;
    }

    public void setNameM(int nameM) {
        this.nameM = nameM;
    }

    public int getLongMonth() {
        return longMonth;
    }

    public void setLongMonth(int longMonth) {
        this.longMonth = longMonth;
    }

    public int getLongDT() {
        return longDT;
    }

    public void setLongDT(int longDT) {
        this.longDT = longDT;
    }

    @Override
    public String toString() {
        return "Month{" +
                "nameM=" + nameM +
                ", longMonth=" + longMonth +
                ", longDT=" + longDT +
                ", WRT=" + Arrays.toString(WRT) +
                '}';
    }
}
