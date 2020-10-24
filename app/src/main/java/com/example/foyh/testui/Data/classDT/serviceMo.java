package com.example.foyh.testui.Data.classDT;


import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.FileWriter;
import java.util.ArrayList;

public class serviceMo {

    private static FileWriter file;
    //    public JSONObject getJsonDT(){
//        JSONParser parser = new JSONParser();
//        Object obj = null;
//        try {
//            obj = parser.parse(new FileReader("src/main/java/com/foyhWebAppApi/dudoanCK/data_C.json"));
//        } catch (IOException e) {
//            e.printStackTrace();
//        } catch (ParseException e) {
//            e.printStackTrace();
//        }
//        return (JSONObject) obj;
//    }
    public ArrayList<thisMo> getDataUS(JSONObject obj) throws JSONException {
        ArrayList<thisMo> dtUS= new ArrayList<>();
        JSONObject jsonObject = obj;
        JSONArray dataUser= null;
        try {
            dataUser = (JSONArray) jsonObject.getJSONArray("data");
        } catch (JSONException e) {
            e.printStackTrace();
        }
        for (int i=0;i<=dataUser.length()-1;i++){
            //get and set data to thisMo
            JSONArray arrM= null;
            arrM = (JSONArray) dataUser.get(i);
            JSONArray rt = (JSONArray) arrM.get(3);
            String rt1= String.valueOf(rt.get(0));
            String rt2= String.valueOf(rt.get(1));
            String nameM= String.valueOf(arrM.get(0));
            String LongM= String.valueOf(arrM.get(1));
            String Longdt= String.valueOf(arrM.get(2));
            int name = Integer.parseInt(nameM);
            int longdt = Integer.parseInt(Longdt);
            int longm= Integer.parseInt(LongM);
            int rtt1= Integer.parseInt(rt1);
            int rtt2= Integer.parseInt(rt2);
            int[] rtt = {rtt1,rtt2};
            thisMo mo1 = new thisMo(name,longm,longdt,rtt);
            //get and set data to bh folow day
            JSONArray bha= (JSONArray) arrM.get(4);
            for (int j=0;j<=bha.length()-1;j++){
                JSONArray bh = (JSONArray) bha.get(j);
                ArrayList<String> arrMe= new ArrayList<>();
                for (int k=0;k<=bh.length()-1;k++){
                    String bhd= String.valueOf(bh.get(k));
                    arrMe.add(bhd);
                }
                String[] BHD= arrMe.toArray(new String[0]);
                mo1.addBH(BHD,j);
            }
            dtUS.add(mo1);
        }
        return dtUS;
    }
//    public JSONObject dataFT(ArrayList<thisMo> dataUser){
//        //kiểm tra các tháng trc có sự lặp lại hay k
//        //arr tính điểm lặp lại lưu theo vị tri
//        ArrayList<Integer> arrSL= new ArrayList<>();
//        ArrayList<Integer> arrSDT= new ArrayList<>();
//        ArrayList<Integer> arrSLrt= new ArrayList<>();
//        int l1=0;
//        for (int i=0; i<= dataUser.size()-1;i++){
//            thisMo lastM = dataUser.get(i);
//            int l=0;
//            int k=0;
//            int m=0;
//            for (int j=0; j<=dataUser.size()-1;j++){
//                thisMo lastMo=dataUser.get(j);
//                // arrSrt
//                if (Arrays.equals(lastM.getWRT(),lastMo.getWRT())){
//                    try {
//                        l = arrSLrt.get(l1);
//                        arrSLrt.remove(l1);
//                        arrSLrt.add(l1,l+1);
//                    }catch (IndexOutOfBoundsException e){
//                        arrSLrt.add(l1,1);
//                    }
//                }
//
//                if (lastM.getLongMonth()==lastMo.getLongMonth()){
//                    try {
//                        k = arrSL.get(l1);
//                        arrSL.remove(l1);
//                        arrSL.add(l1,k+1);
//                    }catch (IndexOutOfBoundsException e){
//                        arrSL.add(l1,1);
//                    }
//                }
//
//                if (lastM.getLongDT()==lastMo.getLongDT()){
//                    try {
//                        m = arrSDT.get(l1);
//                        arrSDT.remove(l1);
//                        arrSDT.add(l1,m+1);
//                    }catch (IndexOutOfBoundsException e){
//                        arrSDT.add(l1,1);
//                    }
//                }
//            }
//            l1++;
//        }
////    arrSDT.stream().forEach(p-> System.out.println(p));
////        System.out.println("///");
////        arrSLrt.stream().forEach(p-> System.out.println(p));
////        System.out.println("///");
////        arrSL.stream().forEach(p-> System.out.println(p));
//        //chon ra so lap lai
//        int[] max= new int[3];
//        int m=0;
//        int m1=0;
//        int m2=0;
//        int m11=0;
//        int m12=0;
//        int m13=0;
//        int cTi=0; // thay bien dem i vi dem tu cuoi toi dau;
//        for (int i=0;i<=dataUser.size()-1;i++){
//            if (arrSDT.get(cTi)>m){
//                max[0]=cTi;
//                m=arrSDT.get(cTi);
//            }
//            if(arrSDT.get(cTi)==m){
//                m11=m11+1;
//                if (m11==4){
//                    max[0]=dataUser.size()-1;
//                }
//            }
//
//            if (arrSL.get(cTi)>m1){
//                max[1]=i;
//                m1=arrSL.get(cTi);
//            }
//            if(arrSL.get(cTi)==m1){
//                m12=m12+1;
//                if (m12==4){
//                    max[1]=dataUser.size()-1;
//                }
//            }
//
//            if (arrSLrt.get(cTi)>m2){
//                max[2]=cTi;
//                m2=arrSLrt.get(cTi);
//            }
//            if(arrSLrt.get(cTi)==m2){
//                m13=m13+1;
//                if (m13==4){
//                    max[2]=dataUser.size()-1;
//                }
//            }
//            cTi++;
//        }
//        //với dự đoán số ngày ưu tiên dùng số liệu tháng trc cải biến theo bh trong tháng
//        //du doan ngay ket thuc chu ki
//        serviceMo sv = new serviceMo();
//        ArrayList<String[]> topdtbh= sv.getTopBh(dataUser,"dt");
//        String[] dtop1= topdtbh.get(0);
//        String[] dtop2= topdtbh.get(1);
//        String[] dtop3= topdtbh.get(2);
//        //du doan ngay rt
//        ArrayList<String[]> topdtbh1= sv.getTopBh(dataUser,"rt");
//        String[] dtop11= topdtbh.get(0);
//        String[] dtop22= topdtbh.get(1);
//        String[] dtop33= topdtbh.get(2);
//
////
////        System.out.println("bh trc ket thuc chu ki");
////        System.out.println(dtop1[0]+"/"+dtop1[1]+"/"+dtop1[2]);
////        System.out.println(dtop2[0]+"/"+dtop2[1]+"/"+dtop2[2]);
////        System.out.println(dtop3[0]+"/"+dtop3[1]+"/"+dtop3[2]);
////        System.out.println("bh trc ngay rung trung");
////        System.out.println(dtop11[0]+"/"+dtop11[1]+"/"+dtop11[2]);
////        System.out.println(dtop22[0]+"/"+dtop22[1]+"/"+dtop22[2]);
////        System.out.println(dtop33[0]+"/"+dtop33[1]+"/"+dtop33[2]);
//        thisMo lastMo = dataUser.get(dataUser.size()-1);
//        thisMo mo5= new thisMo((lastMo.getNameM()+1),dataUser.get(max[1]).getLongMonth(),
//                dataUser.get(max[0]).getLongDT(),dataUser.get(max[2]).getWRT());
//
//        JSONArray wrt= new JSONArray();
//        wrt.add(mo5.getWRT()[0]);
//        wrt.add(mo5.getWRT()[1]);
//        JSONArray mooo= new JSONArray();
//        mooo.add(mo5.getNameM());
//        mooo.add(mo5.getLongMonth());
//        mooo.add(mo5.getLongDT());
//        mooo.add(wrt);
//
//        JSONArray bhn1 = new JSONArray();
//        bhn1.add(dtop1[0]);
//        bhn1.add(dtop1[1]);
//        bhn1.add(dtop1[2]);
//
//        JSONArray bhn2 = new JSONArray();
//        bhn2.add(dtop2[0]);
//        bhn2.add(dtop2[1]);
//        bhn2.add(dtop2[2]);
//
//        JSONArray bhn3 = new JSONArray();
//        bhn3.add(dtop3[0]);
//        bhn3.add(dtop3[1]);
//        bhn3.add(dtop3[2]);
//
//        JSONArray dt = new JSONArray();
//        dt.add(bhn1);
//        dt.add(bhn2);
//        dt.add(bhn3);
//
//
//
//        JSONArray bhn11 = new JSONArray();
//        bhn11.add(dtop11[0]);
//        bhn11.add(dtop11[1]);
//        bhn11.add(dtop11[2]);
//
//        JSONArray bhn22 = new JSONArray();
//        bhn22.add(dtop22[0]);
//        bhn22.add(dtop22[1]);
//        bhn22.add(dtop22[2]);
//
//        JSONArray bhn33 = new JSONArray();
//        bhn33.add(dtop33[0]);
//        bhn33.add(dtop33[1]);
//        bhn33.add(dtop33[2]);
//        JSONArray bhrt = new JSONArray();
//        bhrt.add(bhn11);
//        bhrt.add(bhn22);
//        bhrt.add(bhn33);
//        JSONObject datart= new JSONObject();
//        datart.put("bhdt",dt);
//        datart.put("bhrt",bhrt);
//        datart.put("dataday",mooo);
//        return datart;
////        System.out.println(mo5.toString());
//    }
//    public ArrayList<String[]> getTopBh(ArrayList<thisMo> dataUser, String stt){
//        ArrayList<String[]> rtdt= new ArrayList<>();
//        // tim BH trc dt va rt lap lai nhieu nhat;
//        ArrayList<String> day1= new ArrayList<>();
//        ArrayList<String> day2= new ArrayList<>();
//        ArrayList<String> day3= new ArrayList<>();
//
//        for (int i=0;i<=dataUser.size()-1;i++){
//            int fr=0;
//            thisMo moo= dataUser.get(i);
//            if (stt.equals("dt")){
//                fr = moo.getLongMonth()-moo.getLongDT();
//            }else{
//                fr = moo.getWRT()[0];
//            }
//            for(int j=fr-4;j<=fr-1;j++){
//                if (j==fr-4){ moo.getBh().get(j).getBH().stream().forEach(p->day1.add(p)); }
//                if (j==fr-3){ moo.getBh().get(j).getBH().stream().forEach(p->day2.add(p)); }
//                if (j==fr-2){ moo.getBh().get(j).getBH().stream().forEach(p->day3.add(p)); }
//            }
//        }
//        //arr to save data memories
//        String[] dtop1=new String[3];
//        String[] dtop2=new String[3];
//        String[] dtop3=new String[3];
//        ArrayList<Integer> s1= new ArrayList<>();
//        ArrayList<Integer> s2= new ArrayList<>();
//        ArrayList<Integer> s3= new ArrayList<>();
//        //day1
//        for (int i=0;i<=day1.size()-1;i++){
//            String bhs= day1.get(i);
//            for (int j=0;j<=day1.size()-1;j++){
//                String bhss= day1.get(j);
//                if (!bhss.equals("none") && bhss.equals(bhs)){
//                    try{
//                        int d=s1.get(i);
//                        s1.remove(i);
//                        s1.add(d+1);
//                    }catch (IndexOutOfBoundsException e){
//                        s1.add(1);
//                    }
//
//                }
//            }
//        }
//        //day2
//        for (int i=0;i<=day2.size()-1;i++){
//            String bhs= day2.get(i);
//            for (int j=0;j<=day2.size()-1;j++){
//                String bhss= day2.get(j);
//                if (!bhss.equals("none") && bhss.equals(bhs)){
//                    try{
//                        int d=s1.get(i);
//                        s2.remove(i);
//                        s2.add(d+1);
//                    }catch (IndexOutOfBoundsException e){
//                        s2.add(1);
//                    }
//
//                }
//            }
//        }
//        //day3
//        for (int i=0;i<=day3.size()-1;i++){
//            String bhs= day3.get(i);
//            for (int j=0;j<=day3.size()-1;j++){
//                String bhss= day3.get(j);
//                if (!bhss.equals("none") && bhss.equals(bhs)){
//                    try{
//                        int d=s1.get(i);
//                        s3.remove(i);
//                        s3.add(d+1);
//                    }catch (IndexOutOfBoundsException e){
//                        s3.add(1);
//                    }
//                }
//            }
//        }
//        //them top 3 bh vao mang bh
//        //dtop1
//        int top = 0;
//        while (top<=2) {
//            int maxS = 0;
//            for (int i = 0; i <= s1.size() - 1; i++) {
//                if (s1.get(i) > maxS) {
//                    maxS = s1.get(i);
//                    dtop1[top] = day1.get(i);
//                }
//            }
//            top++;
//            for (int i=0;i<=day1.size()-1;i++){
//                if (day1.get(i).equals(dtop1[top-1])){
//                    s1.remove(i);
//                    s1.add(i,0);
//                }
//            }
//        }
//        //dtop2
//        int top1 = 0;
//        while (top1<=2) {
//            int maxS = 0;
//            for (int i = 0; i <= s2.size() - 1; i++) {
//                if (s2.get(i) > maxS) {
//                    maxS = s2.get(i);
//                    dtop2[top1] = day2.get(i);
//                }
//            }
//            top1++;
//            for (int i=0;i<=day2.size()-1;i++){
//                if (day2.get(i).equals(dtop2[top1-1])){
//                    s2.remove(i);
//                    s2.add(i,0);
//                }
//            }
//        }
//        //dtop3
//        int top2 = 0;
//        while (top2<=2) {
//            int maxS = 0;
//            for (int i = 0; i <= s1.size() - 1; i++) {
//                if (s3.get(i) > maxS) {
//                    maxS = s3.get(i);
//                    dtop3[top2] = day3.get(i);
//                }
//            }
//            top2++;
//            for (int i=0;i<=day3.size()-1;i++){
//                if (day3.get(i).equals(dtop3[top2-1])){
//                    s3.remove(i);
//                    s3.add(i,0);
//                }
//            }
//        }
//
//        //add data to arr to return
//        rtdt.add(dtop1);
//        rtdt.add(dtop2);
//        rtdt.add(dtop3);
//        return  rtdt;
//    }
//    public JSONObject SetJsondt(thisMo mo1,thisMo mo2,thisMo mo3,thisMo mo4){
//
//        JSONArray day = new JSONArray();
//        for (int i=0;i<=40;i++){
//            JSONArray rr = new JSONArray();
//            rr.add("bh1");
//            rr.add("bh2");
//            day.add(rr);
//        }
//
//
//        JSONArray ar1 = new JSONArray();
//        ar1.add(mo1.getWRT()[0]);
//        ar1.add(mo1.getWRT()[1]);
//        JSONArray ar = new JSONArray();
//        ar.add(mo1.getNameM());
//        ar.add(mo1.getLongMonth());
//        ar.add(mo1.getLongDT());
//        ar.add(ar1);
//        ar.add(day);
//
//        JSONArray ar12 = new JSONArray();
//        ar12.add(mo2.getWRT()[0]);
//        ar12.add(mo2.getWRT()[1]);
//        JSONArray ar11 = new JSONArray();
//        ar11.add(mo2.getNameM());
//        ar11.add(mo2.getLongMonth());
//        ar11.add(mo2.getLongDT());
//        ar11.add(ar12);
//        ar11.add(day);
//
//
//
//        JSONArray ar13 = new JSONArray();
//        ar13.add(mo3.getWRT()[0]);
//        ar13.add(mo3.getWRT()[1]);
//        JSONArray ar22 = new JSONArray();
//        ar22.add(mo3.getNameM());
//        ar22.add(mo3.getLongMonth());
//        ar22.add(mo3.getLongDT());
//        ar22.add(ar13);
//        ar22.add(day);
//
//
//        JSONArray ar14 = new JSONArray();
//        ar14.add(mo4.getWRT()[0]);
//        ar14.add(mo4.getWRT()[1]);
//        JSONArray ar33 = new JSONArray();
//        ar33.add(mo4.getNameM());
//        ar33.add(mo4.getLongMonth());
//        ar33.add(mo4.getLongDT());
//        ar33.add(ar14);
//        ar33.add(day);
//
//
//
//        JSONArray dataUS= new JSONArray();
//        dataUS.add(ar);
//        dataUS.add(ar11);
//        dataUS.add(ar22);
//        dataUS.add(ar33);
//
//
//        JSONObject ob = new JSONObject();
//        ob.put("dataUser", dataUS);
////        try {
////
////            // Constructs a FileWriter given a file name, using the platform's default charset
////            file = new FileWriter("src/main/java/com/foyhWebAppApi/dudoanCK/data_C.json");
////            file.write(ob.toJSONString());
//////            CrunchifyLog("Successfully Copied JSON Object to File...");
////        } catch (IOException e) {
////            e.printStackTrace();
////
////        } finally {
////
////            try {
////                file.flush();
////                file.close();
////            } catch (IOException e) {
////                // TODO Auto-generated catch block
////                e.printStackTrace();
////                System.out.println("add data in data_method");
////            }
////        }
//
//        return ob;
//    }
}
