package com.example.doanappdkhp.entity;

public class MonHoc {
    String TenMHHP;

    public MonHoc(String tenMHHP) {
        TenMHHP = tenMHHP;
    }

    public String getTenMHHP() {
        return TenMHHP;
    }

    public void setTenMHHP(String tenMHHP) {
        TenMHHP = tenMHHP;
    }

    @Override
    public String toString() {
        return "MonHoc{" +
                "TenMHHP='" + TenMHHP + '\'' +
                '}';
    }
}
