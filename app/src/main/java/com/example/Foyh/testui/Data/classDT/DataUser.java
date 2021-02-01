package com.example.Foyh.testui.Data.classDT;

public class DataUser {
    public String name,born,token;
    public int id,day;



    public  DataUser(){

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public DataUser(int id,String name, String born, String token,int day ) {
        this.name = name;
        this.born = born;
        this.token = token;
        this.id = id;
        this.day= day;
    }

    @Override
    public String toString() {
        return "DataUser{" +
                "name='" + name + '\'' +
                ", born='" + born + '\'' +
                ", token='" + token + '\'' +
                ", id=" + id +
                ", day=" + day +
                '}';
    }

    public int getDay() {
        return day;
    }

    public void setDay(int day) {
        this.day = day;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setBorn(String born) {
        this.born = born;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getName() {
        return name;
    }

    public String getBorn() {
        return born;
    }

    public String getToken() {
        return token;
    }
}
