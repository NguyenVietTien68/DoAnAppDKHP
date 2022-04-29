package com.example.doanappdkhp.fragment;

import static com.example.doanappdkhp.MainActivity.mssv;
import static com.example.doanappdkhp.gui.DKHP.adapternam;
import static com.example.doanappdkhp.gui.DKHP.nam_spinner;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.doanappdkhp.R;
import com.example.doanappdkhp.TrangChinh;
import com.example.doanappdkhp.api.ApiService;
import com.example.doanappdkhp.entity.Sinhvien;
import com.example.doanappdkhp.entity.TaiKhoanSV;
import com.example.doanappdkhp.gui.DKHP;
import com.example.doanappdkhp.gui.doi_matkhau;
import com.example.doanappdkhp.gui.xem_congno;
import com.example.doanappdkhp.gui.xem_ctkhung;
import com.example.doanappdkhp.gui.xem_lich;
import com.example.doanappdkhp.gui.xem_diem;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class MainForm extends Fragment {

    List<Sinhvien> mlistSinhVien;
    private View view;
    Button btnChuyenCongNo, btnChuyenDoiMK, btnChuyenXemKhung, btnChuyenLich, btnChuyenXemDiem, btnChuyenDKHP;
    ProgressBar progressBar;
    TextView tvName, tvMssv;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view =  inflater.inflate(R.layout.fragment_main_form, container, false);

        progressBar = view.findViewById(R.id.progressBarMainForm);
        progressBar.setVisibility(View.VISIBLE);
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
    private void getInfoSinhVien() {
        ApiService.apiService.getSinhVienTheoMSSV(mssv).enqueue(new Callback<List<Sinhvien>>() {
            @Override
            public void onResponse(Call<List<Sinhvien>> call, Response<List<Sinhvien>> response) {
                //Toast.makeText(getContext(), "Call api success!!!", Toast.LENGTH_SHORT).show();
                mlistSinhVien = response.body();
                //Toast.makeText(getContext(), ""+mlistSinhVien.size(), Toast.LENGTH_SHORT).show();
                if (mlistSinhVien != null ){
                    try {
                        for(Sinhvien sinhvien : mlistSinhVien){
                            tvName.setText(sinhvien.getHoTen());
                            tvMssv.setText(sinhvien.getMSSV());
                            progressBar.setVisibility(View.GONE);
                        }

                    }catch (Exception e){
                        e.printStackTrace();
                    }
                }
            }
            @Override
            public void onFailure(Call<List<Sinhvien>> call, Throwable t) {
                Toast.makeText(getContext(), "Call api error", Toast.LENGTH_SHORT).show();
            }
        });
    }
}