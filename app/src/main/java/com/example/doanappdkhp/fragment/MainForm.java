package com.example.doanappdkhp.fragment;

import static com.example.doanappdkhp.R.drawable.calendar;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;

import androidx.activity.OnBackPressedCallback;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.doanappdkhp.LoginActivity;
import com.example.doanappdkhp.R;
import com.example.doanappdkhp.api.ApiService;
import com.example.doanappdkhp.data_local.DataLocalManager;
import com.example.doanappdkhp.entity.NamHoc;
import com.example.doanappdkhp.entity.Sinhvien;
import com.example.doanappdkhp.gui.DKHP;
import com.example.doanappdkhp.gui.doi_matkhau;
import com.example.doanappdkhp.gui.xem_congno;
import com.example.doanappdkhp.gui.xem_ctkhung;
import com.example.doanappdkhp.gui.xem_lich;
import com.example.doanappdkhp.gui.xem_diem;
import com.github.ybq.android.spinkit.style.RotatingCircle;
import com.github.ybq.android.spinkit.style.RotatingPlane;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class MainForm extends Fragment {

    private static final Object MODE_PRIVATE = "MODE_PRIVATE";
    List<Sinhvien> mlistSinhVien;
    private View view, view1;
    Button btnChuyenCongNo, btnChuyenDoiMK, btnChuyenXemKhung, btnChuyenLich, btnChuyenXemDiem, btnChuyenDKHP;
    ProgressDialog progressDialog;
    TextView tvName, tvMssv;
    public  static ArrayList<String> listNam ;
    public static ArrayAdapter adapternam;
    List<NamHoc> namHocList = new ArrayList<>();
    String khoahoc;
    boolean homePressed = true, doubleBackToExitPressedOnce = false;
    @SuppressLint({"ResourceType", "NewApi"})
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        view =  inflater.inflate(R.layout.fragment_main_form, container, false);

        mlistSinhVien = new ArrayList<>();
        listNam = new ArrayList<>();
        progressDialog = new ProgressDialog(getContext());

        progressDialog.show();
        progressDialog.setContentView(R.layout.processbar_main_form);

        tvName = view.findViewById(R.id.txtNameInfoMain);
        tvMssv = view.findViewById(R.id.txtIdInfoMain);
        btnChuyenDKHP = view.findViewById(R.id.btnChuyenDangKy);
        btnChuyenCongNo = view.findViewById(R.id.btnChuyenXemCongNo);
        btnChuyenDoiMK = view.findViewById(R.id.btnChuyenDoiMK);
        btnChuyenXemKhung = view.findViewById(R.id.btnChuyenXemKhung);
        btnChuyenLich = view.findViewById(R.id.btnChuyenXemLich);
        btnChuyenXemDiem = view.findViewById(R.id.btnChuyenXemDiem);
        mlistSinhVien = new ArrayList<>();

        btnChuyenDKHP.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity().getApplication(), DKHP.class);
                startActivity(intent);
            }
        });
        btnChuyenXemDiem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity().getApplication(), xem_diem.class);
                startActivity(intent);
            }
        });
        btnChuyenCongNo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity().getApplication(), xem_congno.class);
                startActivity(intent);
            }
        });
        btnChuyenDoiMK.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity().getApplication(), doi_matkhau.class);
                startActivity(intent);
            }
        });
        btnChuyenXemKhung.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity().getApplication(), xem_ctkhung.class);
                startActivity(intent);
            }
        });
        btnChuyenLich.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity().getApplication(), xem_lich.class);
                startActivity(intent);
            }
        });
        getInfoSinhVien();

        return view;
    }
    public void getListNamHoc() {
        ApiService.apiService.getNamHocs().enqueue(new Callback<List<NamHoc>>() {
            @Override
            public void onResponse(Call<List<NamHoc>> call, Response<List<NamHoc>> response) {
                namHocList = response.body();
                //Toast.makeText(getContext(), "Size Nam hoc"+ response.body().size(), Toast.LENGTH_SHORT).show();
//                Log.d("Khoa hoc", DataLocalManager.getUser().getKhoaHoc());

                String khoahocTach = khoahoc.substring(0,4);
                //Log.d("Khoa hoc Tach", khoahocTach);
                int vitri = 0;
                for (int i = 0; i < namHocList.size(); i++) {
                    String namhocs = namHocList.get(i).getNam();
                    //Log.d("Nam hoc", namhocs);
                    String tachnam  = namhocs.substring(0, 4);
                    Log.d("Nam sau khi cat", tachnam);
                    if(khoahocTach.equals(tachnam)){
                        vitri = i;
                        //Log.d("vitri",vitri+"");
                    }
                }
//                int slotKH = 4;
//                if(vitri +4 !=  ){
//                    for (int j = vitri; j< vitri+ 4; j++){
//                        listNam.add(namHocList.get(j).getNam()+"");
//                    }
//                }else {
//                    listNam.clear();
//                    if(vitri +3 !=0 ){
//                        for (int j = vitri; j< vitri+ 3; j++){
//                            listNam.add(namHocList.get(j).getNam()+"");
//                        }
//                    }else {
//                        listNam.clear();
//                        if(vitri +2 !=0 ) {
//                            for (int j = vitri; j < vitri + 2; j++) {
//                                listNam.add(namHocList.get(j).getNam() + "");
//                            }
//                        }
//                    }
//                }
                for (int j = vitri; j< vitri+ 4; j++){
                   // if ((vitri +4) !=0) {
                        listNam.add(namHocList.get(j).getNam() + "");
//                        listNam.add(namHocList.get(2).getNam() + "");
//                        listNam.add(namHocList.get(3).getNam() + "");
//                        listNam.add(namHocList.get(4).getNam() + "");
                    }
//                    }else {
//                        Toast.makeText(getContext(), "hihihuihuhihiouhigg", Toast.LENGTH_SHORT).show();
//                        if ((vitri +3) !=0){
//                            listNam.add(namHocList.get(vitri +0).getNam()+"");
//                            listNam.add(namHocList.get(vitri +1).getNam()+"");
//                            listNam.add(namHocList.get(vitri +2).getNam()+"");
//                        }else {
//                            if ((vitri +2) !=0){
//                                listNam.add(namHocList.get(vitri +0).getNam()+"");
//                                listNam.add(namHocList.get(vitri +1).getNam()+"");
//                            }else{
//                                if ((vitri +1) !=0){
//                                    listNam.add(namHocList.get(vitri +0).getNam()+"");
//                                }
//                            }
//
//                        }
                    }
                    //Log.d("dasdasdasd : ", listNam+"");
                    //namdung = namHocList.get(j).getNam().toCharArray();
               //}
            //}
            @Override
            public void onFailure(Call<List<NamHoc>> call, Throwable t) {
                Toast.makeText(getContext(), "Call api error LHP", Toast.LENGTH_SHORT).show();
            }
        });
    }
    private void getInfoSinhVien() {
        ApiService.apiService.getSinhVienTheoMSSV(DataLocalManager.getMSSV()).enqueue(new Callback<List<Sinhvien>>() {
            @Override
            public void onResponse(Call<List<Sinhvien>> call, Response<List<Sinhvien>> response) {

                //Toast.makeText(getContext(), "Call api success!!!", Toast.LENGTH_SHORT).show();
                mlistSinhVien = response.body();
                Log.d("okokokokokoaasdasdas","hhuhu "+ mlistSinhVien);
                //Toast.makeText(getContext(), ""+mlistSinhVien.size(), Toast.LENGTH_SHORT).show();
                if (mlistSinhVien != null ){
                    getListNamHoc();
                    try {
                        for(Sinhvien sinhvien : mlistSinhVien){
                            tvName.setText(sinhvien.getHoTen());
                            tvMssv.setText(sinhvien.getMSSV());
                            khoahoc = sinhvien.getKhoaHoc();
                            Sinhvien sinhvien1 = new Sinhvien(sinhvien.getMSSV(), sinhvien.getDiaChi(), sinhvien.getHoTen(), sinhvien.getGioiTinh(), sinhvien.getNgaySinh(), sinhvien.getSoDT(), sinhvien.getKhoaHoc());
                            DataLocalManager.setUser(sinhvien1);
                            progressDialog.dismiss();
                        }

                    }catch (Exception e){
                        e.printStackTrace();
                    }
                }
            }
            @Override
            public void onFailure(Call<List<Sinhvien>> call, Throwable t) {
//                Log.d("loiroi","dm "+ t.getMessage());
                Toast.makeText(getContext(), "Lỗi kết nối", Toast.LENGTH_SHORT).show();
            }
        });
    }

    //-------Xử lý việc nhấn nứt back ở màn hình android để thoát ứng dụng------------
    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        OnBackPressedCallback callback = new OnBackPressedCallback(true) {
            @Override
            public void handleOnBackPressed() {
                if (homePressed) {
                    if (doubleBackToExitPressedOnce) {
//                        MainForm.super.getActivity().onBackPressed();
                        getActivity().moveTaskToBack(true);
                        return;
                    }
                    doubleBackToExitPressedOnce = true;
                    Toast.makeText(getContext(), "Chạm lần nữa để thoát", Toast.LENGTH_SHORT).show();
                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            doubleBackToExitPressedOnce = false;
                        }
                    }, 2000);
                } else {
                    homePressed = true;
                    //getActivity().moveTaskToBack(true);
                }
            }
        };
        requireActivity().getOnBackPressedDispatcher().addCallback(this, callback);
    }

}