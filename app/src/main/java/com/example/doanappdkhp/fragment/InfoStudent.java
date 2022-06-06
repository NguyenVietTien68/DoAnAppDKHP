package com.example.doanappdkhp.fragment;

import static android.content.Context.MODE_PRIVATE;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;

import com.bumptech.glide.Glide;
import com.example.doanappdkhp.LoginActivity;
import com.example.doanappdkhp.R;
import com.example.doanappdkhp.api.ApiService;
import com.example.doanappdkhp.data_local.DataLocalManager;
import com.example.doanappdkhp.entity.Sinhvien;
import com.example.doanappdkhp.gui.DKHP;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.logging.Logger;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class InfoStudent extends Fragment {

    //-----get info sinhvien------------------------//
    private static final String TAG = "MainActivity";
    private List<Sinhvien> mlistSinhVien;

    TextView txtName, txtID, txtGioiTinh, txtNgaySinh, txtDiaChi, txtKhoaHoc, txtSoDT;
    Button btnDangXuat;
    public static String khoahocchung;
    ImageView imgSV;
    ProgressBar progressBar;
    String mssvI;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState){
        View view = inflater.inflate(R.layout.fragment_info_student, container, false);
        txtID = view.findViewById(R.id.txtIdInfo);
        txtName = view.findViewById(R.id.txtNameInfo);
        txtDiaChi =  view.findViewById(R.id.txtDiaChi);
        txtGioiTinh =  view.findViewById(R.id.txtGioiTinh);
        txtNgaySinh =  view.findViewById(R.id.txtNgaySinh);
        txtSoDT =  view.findViewById(R.id.txtSoDT);
        txtKhoaHoc =  view.findViewById(R.id.txtKhoaHoc);
        imgSV =  view.findViewById(R.id.imgSinhVien);
        mssvI = DataLocalManager.getMSSV();
        btnDangXuat = view.findViewById(R.id.btnDangXuat);
        progressBar = view.findViewById(R.id.progressBarInfoSV);
        progressBar.setVisibility(View.VISIBLE);

        // Inflate the layout for this fragment
        //new Async().execute();
        mlistSinhVien = new ArrayList<>();
        getInfoSinhVien();
        dangxuat();
        return view;
    }

    private void dangxuat() {
        btnDangXuat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
                builder.setTitle("Thông báo");
                builder.setMessage("Xác nhận đăng xuất!!!");
                builder.setPositiveButton("Có", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        SharedPreferences preferences = getActivity().getSharedPreferences("checkbox", MODE_PRIVATE);
                        SharedPreferences.Editor editor = preferences.edit();
                        editor.putString("api", "false");
                        editor.apply();
                        Intent intent = new Intent(getActivity().getApplicationContext(), LoginActivity.class);
                        startActivity(intent);
                    }
                });
                builder.setNegativeButton("Không", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
//                        Toast.makeText(getApplicationContext(),
//                                "No Button Clicked",Toast.LENGTH_SHORT).show();
                    }
                });
                AlertDialog dialog = builder.create();
                dialog.show();
            }
        });
    }

    private void getInfoSinhVien() {
        ApiService.apiService.getSinhVienTheoMSSV(mssvI).enqueue(new Callback<List<Sinhvien>>() {
            @Override
            public void onResponse(Call<List<Sinhvien>> call, Response<List<Sinhvien>> response) {
                //Toast.makeText(getContext(), "Call api success!!!", Toast.LENGTH_SHORT).show();
                mlistSinhVien = response.body();
                Log.d("key", mlistSinhVien.toString());
                //Toast.makeText(getContext(), ""+mlistSinhVien.size(), Toast.LENGTH_SHORT).show();
                if (mlistSinhVien != null ){
                    try {
                    for(Sinhvien sinhvien : mlistSinhVien){

                        Locale locale = new Locale("vietnam", "HN");
                        DateFormat dateFormat = DateFormat.getDateInstance(DateFormat.DEFAULT, locale);
                        DateFormat formatter = new SimpleDateFormat("dd/MM/yyyy hh:mm:ss");
                        txtID.setText( sinhvien.getMSSV());
                        txtName.setText(sinhvien.getHoTen());
                        txtSoDT.setText(sinhvien.getSoDT());
                        txtGioiTinh.setText(sinhvien.getGioiTinh());
                        Date d = new Date();
                        int datesv =sinhvien.getNgaySinh().getDate();
                        Log.d("data",""+ formatter.format(sinhvien.getNgaySinh()));
                        txtNgaySinh.setText(datesv+"");
                        txtDiaChi.setText(sinhvien.getDiaChi());
                        txtKhoaHoc.setText(sinhvien.getKhoaHoc());
                        khoahocchung = sinhvien.getKhoaHoc();
                        Log.d("Khoa hocccccccc", khoahocchung);
                        String imgeSV = sinhvien.getImageSV();
                        Glide.with(InfoStudent.this).load(imgeSV).into(imgSV);
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