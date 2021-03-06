package com.example.doanappdkhp.entity;

public class CongNo {
    String TenMHHP;
    int SoTinChi;

    public CongNo(String tenMHHP, int soTinChi) {
        TenMHHP = tenMHHP;
        SoTinChi = soTinChi;
    }

    public String getTenMHHP() {
        return TenMHHP;
    }

    public void setTenMHHP(String tenMHHP) {
        TenMHHP = tenMHHP;
    }

    public int getSoTinChi() {
        return SoTinChi;
    }

    public void setSoTinChi(int soTinChi) {
        SoTinChi = soTinChi;
    }

    @Override
    public String toString() {
        return "CongNo{" +
                "TenMHHP='" + TenMHHP + '\'' +
                ", SoTinChi=" + SoTinChi +
                '}';
    }
}
