package com.example.doanappdkhp.entity;

import java.io.Serializable;

public class MonHocPhan implements Serializable {
    String MaMHP;
    String TenMHHP;
    int SoTinChi;
    String HocPhanYeuCau;
    String BatBuoc;

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

    public String getHocPhanYeuCau() {
        return HocPhanYeuCau;
    }

    public void setHocPhanYeuCau(String hocPhanYeuCau) {
        HocPhanYeuCau = hocPhanYeuCau;
    }

    public String getBatBuoc() {
        return BatBuoc;
    }

    public void setBatBuoc(String batBuoc) {
        BatBuoc = batBuoc;
    }
}
