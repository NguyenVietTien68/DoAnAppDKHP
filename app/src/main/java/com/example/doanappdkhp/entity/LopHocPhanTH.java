package com.example.doanappdkhp.entity;

import java.util.Date;

public class LopHocPhanTH {
    String MaNhom;
    String HoTen;
    String NgayHoc;
    String PhongHoc;
    String TietHoc;
    Date NgayBatDau;

    public String getMaNhom() {
        return MaNhom;
    }

    public void setMaNhom(String maNhom) {
        MaNhom = maNhom;
    }

    public String getHoTen() {
        return HoTen;
    }

    public void setHoTen(String hoTen) {
        HoTen = hoTen;
    }

    public String getNgayHoc() {
        return NgayHoc;
    }

    public void setNgayHoc(String ngayHoc) {
        NgayHoc = ngayHoc;
    }

    public String getPhongHoc() {
        return PhongHoc;
    }

    public void setPhongHoc(String phongHoc) {
        PhongHoc = phongHoc;
    }

    public String getTietHoc() {
        return TietHoc;
    }

    public void setTietHoc(String tietHoc) {
        TietHoc = tietHoc;
    }

    public Date getNgayBatDau() {
        return NgayBatDau;
    }

    public void setNgayBatDau(Date ngayBatDau) {
        NgayBatDau = ngayBatDau;
    }
}
