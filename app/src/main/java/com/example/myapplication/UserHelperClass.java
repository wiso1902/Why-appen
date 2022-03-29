package com.example.myapplication;

public class UserHelperClass {
    String name, tr, time;


    public UserHelperClass() {
    }

    public UserHelperClass(String name, String tr, String time) {
        this.name = name;
        this.tr = tr;
        this.time = time;

    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getName() {

        return name;
    }

    public void setName(String name) {

        this.name = name;
    }

    public String getTr() {

        return tr;
    }

    public void setTr(String tr) {

        this.tr = tr;
    }

}
