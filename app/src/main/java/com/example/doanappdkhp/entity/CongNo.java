package com.example.doanappdkhp.entity;

public class CongNo {
    String TenMHHP;
    int SoTinChi;
    String Nam;
    int HocKy;

    public CongNo(String tenMHHP, int soTinChi, String nam, int hocKy) {
        TenMHHP = tenMHHP;
        SoTinChi = soTinChi;
        Nam = nam;
        HocKy = hocKy;
    }

    public int getHocKy() {
        return HocKy;
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

    public void setHocKy(int hocKy) {
        HocKy = hocKy;
    }

    public String getNam() {
        return Nam;
    }

    public void setNam(String nam) {
        Nam = nam;
    }

    @Override
    public String toString() {
        return "CongNo{" +
                "TenMHHP='" + TenMHHP + '\'' +
                ", SoTinChi='" + SoTinChi + '\'' +
                ", Nam='" + Nam + '\'' +
                ", HocKy='" + HocKy + '\'' +
                '}';
    }
}
