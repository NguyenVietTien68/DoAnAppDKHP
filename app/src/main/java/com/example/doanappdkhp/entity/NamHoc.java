package com.example.doanappdkhp.entity;

import java.util.ArrayList;

public class NamHoc {


    String Nam;

    public String getNam() {
        return Nam;
    }

    public void setNam(String nam) {
        Nam = nam;
    }

    public NamHoc(String nam) {
        Nam = nam;
    }

    @Override
    public String toString() {
        return  Nam ;
    }
}
