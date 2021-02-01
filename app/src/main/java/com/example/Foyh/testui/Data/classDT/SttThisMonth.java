package com.example.Foyh.testui.Data.classDT;

public class SttThisMonth {
    public String DateUpdate,BhToday,ListBh,Note,Nn;
    public int CountRT,CountDt,Id;


    public SttThisMonth(int Id,String dateUpdate, int countRT, int countDt, String bhToday, String listBh, String note, String nn) {
        DateUpdate = dateUpdate;
        BhToday = bhToday;
        ListBh = listBh;
        Note = note;
        Nn = nn;
        CountRT = countRT;
        CountDt = countDt;
        this.Id= Id;
    }
    public SttThisMonth(){}

    @Override
    public String toString() {
        return "SttThisMonth{" +
                "DateUpdate='" + DateUpdate + '\'' +
                ", BhToday='" + BhToday + '\'' +
                ", ListBh='" + ListBh + '\'' +
                ", Note='" + Note + '\'' +
                ", Nn='" + Nn + '\'' +
                ", CountRT=" + CountRT +
                ", CountDt=" + CountDt +
                ", Id=" + Id +
                '}';
    }

    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
    }

    public void setDateUpdate(String dateUpdate) {
        DateUpdate = dateUpdate;
    }

    public void setBhToday(String bhToday) {
        BhToday = bhToday;
    }

    public void setListBh(String listBh) {
        ListBh = listBh;
    }

    public void setNote(String note) {
        Note = note;
    }

    public void setNn(String nn) {
        Nn = nn;
    }

    public void setCountRT(int countRT) {
        CountRT = countRT;
    }

    public void setCountDt(int countDt) {
        CountDt = countDt;
    }

    public String getDateUpdate() {
        return DateUpdate;
    }

    public String getBhToday() {
        return BhToday;
    }

    public String getListBh() {
        return ListBh;
    }

    public String getNote() {
        return Note;
    }

    public String getNn() {
        return Nn;
    }

    public int getCountRT() {
        return CountRT;
    }

    public int getCountDt() {
        return CountDt;
    }
}
