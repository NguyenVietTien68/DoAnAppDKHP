package com.example.doanappdkhp.data_local;

import android.content.Context;

import com.example.doanappdkhp.entity.MonHoc;
import com.example.doanappdkhp.entity.Sinhvien;
import com.google.gson.Gson;
import com.google.gson.JsonArray;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class DataLocalManager {
    private static final String PREF_NAME_PASS = "PREF_NAME_PASS";
    private static final String PREF_NAME_MSSV = "PREF_NAME_MSSV";
    private static final String PREF_NAME_STATUS = "PREF_NAME_STATUS";
    private static final String PREF_OBJECT_USER = "PREF_OBJECT_USER";
    private static final String PREF_LIST_MONHOC = "PREF_LIST_MONHOC";
    private static final String PREF_NAME_HOCKY = "PREF_NAME_HOCKY";
    private static final String PREF_NAME_NAM = "PREF_NAME_NAM";
    private static DataLocalManager instance;
    private MySharePreferrences mySharePreferrences;

    public static  void init(Context context){
        instance = new DataLocalManager();
        instance.mySharePreferrences = new MySharePreferrences(context);
    }

    public static DataLocalManager getInstance(){
        if (instance == null){
            instance = new DataLocalManager();
        }
        return instance;
    }
    public static void setMSSV(String mssv){
        DataLocalManager.getInstance().mySharePreferrences.putString(PREF_NAME_MSSV, mssv);
    }

    public static String getMSSV(){
        return DataLocalManager.getInstance().mySharePreferrences.getString(PREF_NAME_MSSV);
    }
    public static void setPass(String pass){
        DataLocalManager.getInstance().mySharePreferrences.putString(PREF_NAME_PASS, pass);
    }

    public static String getPass(){
        return DataLocalManager.getInstance().mySharePreferrences.getString(PREF_NAME_PASS);
    }
    public static void setLogout(String status){
        DataLocalManager.getInstance().mySharePreferrences.putString(PREF_NAME_STATUS, status);
    }

    public static String getLogout(){
        return DataLocalManager.getInstance().mySharePreferrences.getString(PREF_NAME_STATUS);
    }
    public static void setUser(Sinhvien user){
        Gson gson = new Gson();
        String strUser = gson.toJson(user);
        DataLocalManager.getInstance().mySharePreferrences.putString(PREF_OBJECT_USER, strUser);
    }
    public static Sinhvien getUser(){
        String strUser =  DataLocalManager.getInstance().mySharePreferrences.getString(PREF_OBJECT_USER);
        Gson gson = new Gson();
        Sinhvien user = gson.fromJson(strUser, Sinhvien.class);
        return user;
    }
    public static void setListMonHoc(List<String> listMH){
        Gson gson = new Gson();
        JsonArray jsonArray = gson.toJsonTree(listMH).getAsJsonArray();
        String strJsonArray = jsonArray.toString();
        DataLocalManager.getInstance().mySharePreferrences.putString(PREF_LIST_MONHOC, strJsonArray);
    }
    public static List<MonHoc> getListMonHoc(){
        String strJsonArray =  DataLocalManager.getInstance().mySharePreferrences.getString(PREF_LIST_MONHOC);
        List<MonHoc> listMH = new ArrayList<>();
        try {
            JSONArray jsonArray = new JSONArray(strJsonArray);
            JSONObject jsonObject;
            MonHoc monhoc;
            Gson gson = new Gson();
            for (int i = 0; i< jsonArray.length(); i++){
                jsonObject = jsonArray.getJSONObject(i);
                monhoc = gson.fromJson(jsonObject.toString(), MonHoc.class);
                listMH.add(monhoc);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return listMH;
    }
    public static void setHocKy(String hocky){
        DataLocalManager.getInstance().mySharePreferrences.putString(PREF_NAME_HOCKY, hocky);
    }

    public static String getHocKy(){
        return DataLocalManager.getInstance().mySharePreferrences.getString(PREF_NAME_HOCKY);
    }
    public static void setNam(String nam){
        DataLocalManager.getInstance().mySharePreferrences.putString(PREF_NAME_NAM, nam);
    }

    public static String getNam(){
        return DataLocalManager.getInstance().mySharePreferrences.getString(PREF_NAME_NAM);
    }
}
