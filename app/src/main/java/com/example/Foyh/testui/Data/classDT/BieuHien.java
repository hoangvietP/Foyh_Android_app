package com.example.Foyh.testui.Data.classDT;

public class BieuHien {
    public int day,month;
    public String Bh;
    public BieuHien(){}
    public BieuHien(int day, int month, String bh) {
        this.day = day;
        this.month = month;
        Bh = bh;
    }

    @Override
    public String toString() {
        return "BieuHien{" +
                "day=" + day +
                ", month=" + month +
                ", Bh='" + Bh + '\'' +
                '}';
    }

    public int getDay() {
        return day;
    }

    public int getMonth() {
        return month;
    }

    public String getBh() {
        return Bh;
    }

    public void setDay(int day) {
        this.day = day;
    }

    public void setMonth(int month) {
        this.month = month;
    }

    public void setBh(String bh) {
        Bh = bh;
    }
}
