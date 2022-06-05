package com.example.doanappdkhp.entity;

import android.widget.TextView;

import java.time.format.DateTimeFormatter;
import java.util.Date;

public class Sinhvien {
    String MSSV;
    String DiaChi;
    String HoTen;
    String GioiTinh;
    Date NgaySinh;
    long NgaySinhTime;
    String SoDT;
    String KhoaHoc;
    String imageSV;
//Expected BEGIN_ARRAY but was BEGIN_OBJECT at line 1 column 2 path $
    public Sinhvien(String MSSV, String diaChi, String hoTen,  long ngaySinhTime, String gioiTinh, Date ngaySinh, String soDT, String khoaHoc) {
        this.MSSV = MSSV;
        DiaChi = diaChi;
        HoTen = hoTen;
        GioiTinh = gioiTinh;
        NgaySinh = ngaySinh;
        NgaySinhTime = ngaySinhTime;
        SoDT = soDT;
        KhoaHoc = khoaHoc;
//        this.imageSV = imageSV;
    }

    public long getNgaySinhTime() {
        return NgaySinhTime;
    }

    public void setNgaySinhTime(long ngaySinhTime) {
        NgaySinhTime = ngaySinhTime;
    }

    public Sinhvien(String MSSV, String diaChi, String hoTen, String gioiTinh, Date ngaySinh, String soDT, String khoaHoc) {
        this.MSSV = MSSV;
        DiaChi = diaChi;
        HoTen = hoTen;
        GioiTinh = gioiTinh;
        NgaySinh = ngaySinh;
        SoDT = soDT;
        KhoaHoc = khoaHoc;
    }

    public String getMSSV() {
        return MSSV;
    }

    public void setMSSV(String MSSV) {
        this.MSSV = MSSV;
    }

    public String getDiaChi() {
        return DiaChi;
    }

    public void setDiaChi(String diaChi) {
        DiaChi = diaChi;
    }

    public String getHoTen() {
        return HoTen;
    }

    public void setHoTen(String hoTen) {
        HoTen = hoTen;
    }

    public String getGioiTinh() {
        return GioiTinh;
    }

    public void setGioiTinh(String gioiTinh) {
        GioiTinh = gioiTinh;
    }

    public Date getNgaySinh() {
        return NgaySinh;
    }

    public void setNgaySinh(Date ngaySinh) {
        NgaySinh = ngaySinh;
    }

    public String getSoDT() {
        return SoDT;
    }

    public void setSoDT(String soDT) {
        SoDT = soDT;
    }

    public String getKhoaHoc() {
        return KhoaHoc;
    }

    public void setKhoaHoc(String khoaHoc) {
        KhoaHoc = khoaHoc;
    }

    public String getImageSV() {
        return imageSV;
    }

    public void setImageSV(String imageSV) {
        this.imageSV = imageSV;
    }



    @Override
    public String toString() {
        return "Sinhvien{" +
                "MSSV='" + MSSV + '\'' +
                ", DiaChi='" + DiaChi + '\'' +
                ", HoTen='" + HoTen + '\'' +
                ", GioiTinh='" + GioiTinh + '\'' +
                ", NgaySinh=" + NgaySinh +
                ", SoDT='" + SoDT + '\'' +
                ", KhoaHoc='" + KhoaHoc + '\'' +
                '}';
    }
}
