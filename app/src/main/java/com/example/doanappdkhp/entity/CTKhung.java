package com.example.doanappdkhp.entity;

public class CTKhung {
    String TenChuyenNganh;
    String MaMHP;
    String TenMHHP;
    int SoTinChi;

    public CTKhung(String tenChuyenNganh, String maMHP, String tenMHHP, int soTinChi) {
        TenChuyenNganh = tenChuyenNganh;
        MaMHP = maMHP;
        TenMHHP = tenMHHP;
        SoTinChi = soTinChi;
    }

    public String getTenChuyenNganh() {
        return TenChuyenNganh;
    }

    public void setTenChuyenNganh(String tenChuyenNganh) {
        TenChuyenNganh = tenChuyenNganh;
    }

    public String getMaMHP() {
        return MaMHP;
    }

    public void setMaMHP(String maMHP) {
        MaMHP = maMHP;
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
        return "CTKhung{" +
                "TenChuyenNganh='" + TenChuyenNganh + '\'' +
                ", MaMHP='" + MaMHP + '\'' +
                ", TenMHHP='" + TenMHHP + '\'' +
                ", SoTinChi=" + SoTinChi +
                '}';
    }
}

