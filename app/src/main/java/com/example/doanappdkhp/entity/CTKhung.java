package com.example.doanappdkhp.entity;

public class CTKhung {
    String TenChuyenNganh;
    String MaMHP;
    String TenMHHP;
    int HocKy;

    public CTKhung(String tenChuyenNganh, String maMHP, String tenMHHP, int hocKy) {
        TenChuyenNganh = tenChuyenNganh;
        MaMHP = maMHP;
        TenMHHP = tenMHHP;
        HocKy = hocKy;
    }


    public CTKhung(String maMHP, String tenMHHP, int hocKy) {
        MaMHP = maMHP;
        TenMHHP = tenMHHP;
        HocKy = hocKy;
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

    public int getHocKy() {
        return HocKy;
    }

    public void setHocKy(int hocKy) {
        HocKy = hocKy;
    }

    @Override
    public String toString() {
        return "CTKhung{" +
                "TenChuyenNganh='" + TenChuyenNganh + '\'' +
                ", MaMHP='" + MaMHP + '\'' +
                ", TenMHHP='" + TenMHHP + '\'' +
                ", HocKy=" + HocKy +
                '}';
    }
}

