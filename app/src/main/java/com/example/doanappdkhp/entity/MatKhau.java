package com.example.doanappdkhp.entity;

public class MatKhau {
    String MaTaiKhoan;
    String Pass;

    public MatKhau(String maTaiKhoan, String pass) {
        MaTaiKhoan = maTaiKhoan;
        Pass = pass;
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
        return "MatKhau{" +
                "MaTaiKhoan='" + MaTaiKhoan + '\'' +
                ", Pass='" + Pass + '\'' +
                '}';
    }
}
