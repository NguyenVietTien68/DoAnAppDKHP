package com.example.doanappdkhp.entity;

public class Diem {
    String Nam;
    int hocky;
    String TenMHHP;
    int SoTinChi;
    float DiemTK;
    float DiemGK;
    float DiemTH;
    float DiemCK;


    public String getNam() {
        return Nam;
    }

    public void setNam(String nam) {
        Nam = nam;
    }

    public int getHocky() {
        return hocky;
    }

    public void setHocky(int hocky) {
        this.hocky = hocky;
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

    public float getDiemTK() {
        return DiemTK;
    }

    public void setDiemTK(float diemTK) {
        DiemTK = diemTK;
    }

    public float getDiemGK() {
        return DiemGK;
    }

    public void setDiemGK(float diemGK) {
        DiemGK = diemGK;
    }

    public float getDiemTH() {
        return DiemTH;
    }

    public void setDiemTH(float diemTH) {
        DiemTH = diemTH;
    }

    public float getDiemCK() {
        return DiemCK;
    }

    public void setDiemCK(float diemCK) {
        DiemCK = diemCK;
    }


    @Override
    public String toString() {
        return "Diem{" +
                "Nam='" + Nam + '\'' +
                ", hocky=" + hocky +
                ", TenMHHP='" + TenMHHP + '\'' +
                ", SoTinChi=" + SoTinChi +
                ", DiemTK=" + DiemTK +
                ", DiemGK=" + DiemGK +
                ", DiemTH=" + DiemTH +
                ", DiemCK=" + DiemCK +
                '}';
    }
}
