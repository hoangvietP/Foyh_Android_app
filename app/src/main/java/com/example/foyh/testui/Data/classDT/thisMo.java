package com.example.foyh.testui.Data.classDT;



import java.util.ArrayList;

public class thisMo extends Month {
    private ArrayList<dayBH> bh= new ArrayList<>();

    public thisMo(int nameM, int longMonth, int longDT, int[] WRT) {
        super(nameM, longMonth, longDT, WRT);
    }

    public Boolean addBH(String[] bh, int dayC){
        try{
            dayBH daybh=this.bh.get(dayC);
            daybh.addBh(bh);
        }catch (IndexOutOfBoundsException e){
            dayBH day = new dayBH();
            day.addBh(bh);
            this.bh.add(day);
        }
        return true;
    }
    public ArrayList<dayBH> getBh() {
        return bh;
    }

    //find nearest BH
    public int testBH(int index, String Bh) {
        dayBH day;
        int stt=0;
        int rt = 101;
        if (index!=0) {
            for (int i = 0; i <= 4; i++) {
                try {
                    if (i < index && index - i - 1>=0) {
                        day = this.bh.get(index - i - 1);
                        if (day.getBH().equals(Bh)) {
                            rt = i;
                        }
                    }
                } catch (IndexOutOfBoundsException e) {
                    rt= 101;
                    //101 is error
                }
            }
            stt=1;
        }
        if ( index==0 || stt==1){
            for (int i = 0; i <= 4; i++) {
                try {
                    if (index+i-1 < this.bh.size()) {
                        day = this.bh.get(index + i - 1);
                        if (day.getBH().equals(Bh)) {
                            if (rt>=i){
                                rt=i;
                            }
                        }
                    }
                } catch (IndexOutOfBoundsException e) {
                    rt= 101;
                    //101 is error
                }
            }
        }
        return rt;
    }
}
