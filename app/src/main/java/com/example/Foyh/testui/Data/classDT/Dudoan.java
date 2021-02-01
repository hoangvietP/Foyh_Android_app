package com.example.Foyh.testui.Data.classDT;

public class Dudoan {
    public int month,longMonth,longDt,bdRt,ktRt;
    public String bhRt,bhDt;
    public  Dudoan(){

    }
    public Dudoan(int month, int longMonth, int longDt, int bdRt, int ktRt, String bhRt, String bhDt) {
        this.month = month;
        this.longMonth = longMonth;
        this.longDt = longDt;
        this.bdRt = bdRt;
        this.ktRt = ktRt;
        this.bhRt = bhRt;
        this.bhDt = bhDt;
    }
    public Dudoan duDoanCK(){
        Dudoan kqdd = new Dudoan();
        //tinh toan du doan

        return kqdd;
    }
    @Override
    public String toString() {
        return "Dudoan{" +
                "month=" + month +
                ", longMonth=" + longMonth +
                ", longDt=" + longDt +
                ", bdRt=" + bdRt +
                ", ktRt=" + ktRt +
                ", bhRt='" + bhRt + '\'' +
                ", bhDt='" + bhDt + '\'' +
                '}';
    }

    public void setMonth(int month) {
        this.month = month;
    }

    public void setLongMonth(int longMonth) {
        this.longMonth = longMonth;
    }

    public void setLongDt(int longDt) {
        this.longDt = longDt;
    }

    public void setBdRt(int bdRt) {
        this.bdRt = bdRt;
    }

    public void setKtRt(int ktRt) {
        this.ktRt = ktRt;
    }

    public void setBhRt(String bhRt) {
        this.bhRt = bhRt;
    }

    public void setBhDt(String bhDt) {
        this.bhDt = bhDt;
    }

    public int getMonth() {
        return month;
    }

    public int getLongMonth() {
        return longMonth;
    }

    public int getLongDt() {
        return longDt;
    }

    public int getBdRt() {
        return bdRt;
    }

    public int getKtRt() {
        return ktRt;
    }

    public String getBhRt() {
        return bhRt;
    }

    public String getBhDt() {
        return bhDt;
    }
}
