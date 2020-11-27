package com.example.foyh.testui.Data.classDT;

import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.net.ConnectException;
import java.net.HttpRetryException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;

public class ConnectAsynchronously {

    private static int requestCode;
    private static String myMessage;

    public static String connectAsynchronously(String uri, JSONObject jobj) {
        String result = "";
        try {
            //Connect
            HttpURLConnection urlConnection = (HttpURLConnection) ((new URL(uri).openConnection()));
            urlConnection.setRequestProperty("Content-Type", "application/json; charset=UTF-8");
            urlConnection.setRequestProperty("Accept", "application/json");
            urlConnection.setRequestMethod("POST");
            urlConnection.connect();

            //Write
            OutputStream outputStream = urlConnection.getOutputStream();
            BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(outputStream, "UTF-8"));
            writer.write(jobj.toString());
            writer.close();
            outputStream.close();
            requestCode = urlConnection.getResponseCode();
            myMessage = urlConnection.getResponseMessage();
            //Read
            BufferedReader bufferedReader = null;
            int stt =0;
            try {
                 bufferedReader = new BufferedReader(new InputStreamReader(urlConnection.getInputStream(), "UTF-8"));
            }catch (FileNotFoundException k){
                stt=1;
            }
            if (stt==0){
                String line;
                StringBuilder sb = new StringBuilder();
                while ((line = bufferedReader.readLine()) != null) {
                    sb.append(line);
                }
                bufferedReader.close();
                result = sb.toString();
            }
            urlConnection.disconnect();
        } catch (FileNotFoundException  e) {
            e.printStackTrace();
            result="loss";
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            result="loss";
        } catch (ProtocolException e) {
            e.printStackTrace();
            result="loss";
        } catch (MalformedURLException e) {
            e.printStackTrace();
            result="loss";
        } catch (IOException e) {
            e.printStackTrace();
            result="loss";
        }


        return result;
    }


    public static int getRequestCode() {

        return requestCode;
    }

    public static String getRequestMessage() {

        return myMessage;
    }
}