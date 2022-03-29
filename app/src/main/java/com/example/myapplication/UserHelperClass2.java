package com.example.myapplication;

public class UserHelperClass2 {
    String name, money, totalTr;


    public UserHelperClass2() {
    }

    public UserHelperClass2(String name, String money, String totalTr) {
        this.name = name;
        this.money = money;
        this.totalTr = totalTr;

    }

    public String getTotalTr() {
        return totalTr;
    }

    public void setTotalTr2(String totalTr2) {
        this.totalTr = totalTr2;
    }

    public String getMoney() {

        return money;
    }

    public void setMoney(String money) {

        this.money = money;
    }

    public String getName() {

        return name;
    }

    public void setName(String name) {

        this.name = name;
    }

}

