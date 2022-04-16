package com.example.doanappdkhp.entity;

public class DKHPSV {
    String MSSV;
    String MaLopHP;
    String Nhom;

    public DKHPSV(String MSSV, String maLopHP, String nhom) {
        this.MSSV = MSSV;
        MaLopHP = maLopHP;
        Nhom = nhom;
    }

    public String getMSSV() {
        return MSSV;
    }

    public void setMSSV(String MSSV) {
        this.MSSV = MSSV;
    }

    public String getMaLopHP() {
        return MaLopHP;
    }

    public void setMaLopHP(String maLopHP) {
        MaLopHP = maLopHP;
    }

    public String getNhom() {
        return Nhom;
    }

    public void setNhom(String nhom) {
        Nhom = nhom;
    }

    @Override
    public String toString() {
        return "DKHPSV{" +
                "MSSV='" + MSSV + '\'' +
                ", MaLopHP='" + MaLopHP + '\'' +
                ", Nhom='" + Nhom + '\'' +
                '}';
    }
}

