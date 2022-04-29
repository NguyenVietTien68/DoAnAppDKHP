package com.example.doanappdkhp.gui;

import static com.example.doanappdkhp.MainActivity.mssv;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.doanappdkhp.MainActivity;
import com.example.doanappdkhp.R;
import com.example.doanappdkhp.adapter.CongNoAdapter;
import com.example.doanappdkhp.adapter.DiemAdapter;
import com.example.doanappdkhp.api.ApiService;
import com.example.doanappdkhp.entity.CongNo;
import com.example.doanappdkhp.entity.Diem;
import com.example.doanappdkhp.entity.TaiKhoanSV;
import com.example.doanappdkhp.my_interface.IClickMSSV;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class xem_diem extends AppCompatActivity {
    private static final String TAG = "MainActivity";
    List<Diem> diemList;
    RecyclerView rcv;
    DiemAdapter adt;
    String value1;
    TaiKhoanSV taiKhoanSV;
    ProgressBar progressBar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_xem_diem);

        progressBar = findViewById(R.id.progressBarDiem);
        progressBar.setVisibility(View.VISIBLE);
        rcv = findViewById(R.id.rcv_Diem);
        diemList = new ArrayList<>();
        adt = new DiemAdapter(getApplicationContext(), (ArrayList<Diem>) diemList);
        rcv.setAdapter(adt);
        rcv.setLayoutManager(new GridLayoutManager(xem_diem.this, 1));
        rcv.setHasFixedSize(true);
        getDiem();
    }



    private void getDiem() {

        ApiService.apiService.getDiemTheoMSSV(mssv).enqueue(new Callback<List<Diem>>() {
            @Override
            public void onResponse(Call<List<Diem>> call, Response<List<Diem>> response) {
                 Toast.makeText(xem_diem.this, "Call api success!!!"+ response.body().size(), Toast.LENGTH_SHORT).show();
                diemList = response.body();
                if (diemList != null) {
                adt.setDiems((ArrayList<Diem>) diemList);
                progressBar.setVisibility(View.GONE);
                }
            }
            @Override
            public void onFailure(Call<List<Diem>> call, Throwable t) {
                Toast.makeText(xem_diem.this, "Call api error", Toast.LENGTH_SHORT).show();
            }
        });
    }
}