package com.example.foyh.testui.Data.classDT;

import android.content.Context;
import android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;

public class fileDAO {
    public boolean saveData(String name, JSONObject ob, Context context){
        File file = new File(context.getFilesDir(), name);
        FileWriter fileWriter = null;
        try {
            fileWriter = new FileWriter(file);
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
            bufferedWriter.write(ob.toString());
            bufferedWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }
    public boolean saveDataString(String name, String ob, Context context){
        File file = new File(context.getFilesDir(), name);
        FileWriter fileWriter = null;
        try {
            fileWriter = new FileWriter(file);
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
            bufferedWriter.write(ob);
            bufferedWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    public JSONObject getData(String name, String nameobj, Context context) throws JSONException, FileNotFoundException {
        FileInputStream is = null;
        JSONObject jsonData=null;
        is = context.openFileInput(name);
        BufferedReader br= new BufferedReader(new InputStreamReader(is));
        StringBuilder sb= new StringBuilder();
        String s= null;
        while(true) {
            try {
                if (!(( s = br.readLine())!=null)) break;
            } catch (IOException e) {
                e.printStackTrace();
            }
            sb.append(s);
            sb.append("\n");
        }

        JSONObject jsonRoot = null;
        try {
            jsonRoot = new JSONObject(sb.toString());
            jsonData = jsonRoot.getJSONObject(nameobj);
        } catch (JSONException e) {
            e.printStackTrace();
            jsonData.put("message",false);
        }
        return jsonData;
    }

    public JSONObject getDataOB(String name, Context context) throws JSONException, FileNotFoundException {
        FileInputStream is = null;
        JSONObject jsonData=null;
        is = context.openFileInput(name);
        BufferedReader br= new BufferedReader(new InputStreamReader(is));
        StringBuilder sb= new StringBuilder();
        String s= null;
        while(true) {
            try {
                if (!(( s = br.readLine())!=null)) break;
            } catch (IOException e) {
                e.printStackTrace();
            }
            sb.append(s);
            sb.append("\n");
        }
        JSONObject jsonRoot = null;
        try {
            jsonRoot = new JSONObject(sb.toString());
        } catch (JSONException e) {
            e.printStackTrace();
            jsonData.put("message",false);
        }
        return jsonRoot;
    }


}
