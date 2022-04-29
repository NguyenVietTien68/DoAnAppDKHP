package com.example.doanappdkhp.entity;

import java.io.Serializable;

public class TaiKhoanSV implements Serializable {
    String MaTaiKhoan;
    String Pass;

    public TaiKhoanSV(String maTaiKhoan) {
        MaTaiKhoan = maTaiKhoan;
    }

    public String getMaTaiKhoan() {
        return MaTaiKhoan;
    }

    public void setMaTaiKhoan(String maTaiKhoan) {
        MaTaiKhoan = maTaiKhoan;
    }

    public String getPass() {
        return Pass;
    }

    public void setPass(String pass) {
        Pass = pass;
    }

    @Override
    public String toString() {
        return "TaiKhoanSV{" +
                "MaTaiKhoan='" + MaTaiKhoan + '\'' +
                ", Pass='" + Pass + '\'' +
                '}';
    }
}
