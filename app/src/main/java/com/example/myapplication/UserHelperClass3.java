package com.example.myapplication;

public class UserHelperClass3 {
    String name, money;
    int totalTr;


    public UserHelperClass3() {
    }

    public UserHelperClass3(String name, String money, int totalTr) {
        this.name = name;
        this.money = money;
        this.totalTr = totalTr;

    }

    public int getTotalTr() {
        return totalTr;
    }

    public void setTotalTr2(int totalTr2) {
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

