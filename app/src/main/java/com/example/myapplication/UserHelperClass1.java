package com.example.myapplication;

public class UserHelperClass1 {
    String regName, regPassword;

    public UserHelperClass1() {
    }

    public UserHelperClass1(String regName, String regPassword) {
        this.regName = regName;
        this.regPassword = regPassword;
    }

    public String getRegName() {
        return regName;
    }

    public void setRegName(String regName) {
        this.regName = regName;
    }

    public String getRegPassword() {
        return regPassword;
    }

    public void setRegPassword(String regPassword) {
        this.regPassword = regPassword;
    }
}
