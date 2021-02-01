package com.example.Foyh.testui.Data.classDT;

import android.content.Context;
import android.util.Log;

public class ManagerDataBase {
    DatabaseHandler databaseHandler;
    Context context;
    public ManagerDataBase(Context context){
        this.context= context;
        databaseHandler = new DatabaseHandler(context);
    }
    public void addNewDudoan(Dudoan dudoan){
        databaseHandler.addNewDD(dudoan);
        Log.d("line 15 test database",databaseHandler.getDudoan().toString());
    }

}
