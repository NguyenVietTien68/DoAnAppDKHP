package com.example.doanappdkhp.gui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.doanappdkhp.R;
import com.example.doanappdkhp.adapter.CTKhungAdapter;
import com.example.doanappdkhp.api.ApiService;
import com.example.doanappdkhp.data_local.DataLocalManager;
import com.example.doanappdkhp.entity.CTKhung;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class xem_ctkhung extends AppCompatActivity {
    String[] hocky = {"1","2","3","4","5","6","7","8"};
    Spinner hocky_spinner;
    Button btnGet;
    private static final String TAG = "MainActivity";
    List<CTKhung> ctKhungArrayList;
    RecyclerView rcv;
    CTKhungAdapter adt;
    TextView tvChuyenNganh;
    TextView tvTongSTC;
    ProgressBar progressBar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_xem_ctkhung);

        progressBar = findViewById(R.id.progressBarCTKhung);
        progressBar.setVisibility(View.GONE);
        tvChuyenNganh = findViewById(R.id.tv_ChuyenNganhCTK);
        tvTongSTC = findViewById(R.id.tv_TongSTC_CTK);
        rcv = findViewById(R.id.rcv_CTK);
        ctKhungArrayList = new ArrayList<>();
        adt = new CTKhungAdapter(getApplicationContext(), (ArrayList<CTKhung>) ctKhungArrayList);
        rcv.setAdapter(adt);
        rcv.setLayoutManager(new GridLayoutManager(xem_ctkhung.this, 1));
        rcv.setHasFixedSize(true);

        btnGet = findViewById(R.id.btnLocCTKhung);
        hocky_spinner = findViewById(R.id.spinnerHocKyCTKhung);

        ArrayAdapter adapterhocky = new ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item, hocky);
        adapterhocky.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        hocky_spinner.setAdapter(adapterhocky);

        btnGet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                progressBar.setVisibility(View.VISIBLE);
                getCTKhung();
                tvChuyenNganh.setText("");
            }
        });

    }

    private void getCTKhung() {
        ApiService.apiService.getCTKhungTheo(DataLocalManager.getMSSV(), Integer.parseInt(hocky_spinner.getSelectedItem().toString())).enqueue(new Callback<List<CTKhung>>() {
            @Override
            public void onResponse(Call<List<CTKhung>> call, Response<List<CTKhung>> response) {
                ArrayList<Integer> lstTong = new ArrayList<>();
                //Toast.makeText(xem_ctkhung.this, "Call api success!!!", Toast.LENGTH_SHORT).show();
                ctKhungArrayList = response.body();
                //Toast.makeText(xem_ctkhung.this, ""+ctKhungArrayList.size(), Toast.LENGTH_SHORT).show();
                if (ctKhungArrayList != null) {
                    adt.setCtKhungs((ArrayList<CTKhung>) ctKhungArrayList);
                    progressBar.setVisibility(View.GONE);
                       int ctk = ctKhungArrayList.size();
                    try {
                            for(int i = 0; i< ctk; i++) {
                            tvChuyenNganh.setText(String.valueOf(ctKhungArrayList.get(i).getTenChuyenNganh()));
                                Integer a = ctKhungArrayList.get(i).getSoTinChi();
                                lstTong.add(a);
                            adt.notifyDataSetChanged();
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    int tong = 0;
                    for (Integer b: lstTong) {
                        tong = tong + b;
                    }

                    tvTongSTC.setText(tong+"");
                }
            }
            @Override
            public void onFailure(Call<List<CTKhung>> call, Throwable t) {
                Toast.makeText(xem_ctkhung.this, "Call api error", Toast.LENGTH_SHORT).show();
            }
        });
    }

}