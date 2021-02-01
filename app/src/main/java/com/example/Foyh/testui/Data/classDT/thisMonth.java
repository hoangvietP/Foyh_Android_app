package com.example.Foyh.testui.Data.classDT;

public class thisMonth {
    public int BdRt,KtRt,Month,LongMonth,LongDt;

    public thisMonth(int month,int longMonth, int longDt,int bdRt,int ktRt) {
        BdRt = bdRt;
        KtRt = ktRt;
        Month = month;
        LongMonth = longMonth;
        LongDt = longDt;
    }
    public  thisMonth(){}

    @Override
    public String toString() {
        return "thisMonth{" +
                "BdRt=" + BdRt +
                ", KtRt=" + KtRt +
                ", Month=" + Month +
                ", LongMonth=" + LongMonth +
                ", LongDt=" + LongDt +
                '}';
    }

    public void setBdRt(int bdRt) {
        BdRt = bdRt;
    }

    public void setKtRt(int ktRt) {
        KtRt = ktRt;
    }

    public void setMonth(int month) {
        Month = month;
    }

    public void setLongMonth(int longMonth) {
        LongMonth = longMonth;
    }

    public void setLongDt(int longDt) {
        LongDt = longDt;
    }

    public int getBdRt() {
        return BdRt;
    }

    public int getKtRt() {
        return KtRt;
    }

    public int getMonth() {
        return Month;
    }

    public int getLongMonth() {
        return LongMonth;
    }

    public int getLongDt() {
        return LongDt;
    }
}
