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

import com.example.doanappdkhp.LoginActivity;
import com.example.doanappdkhp.R;
import com.example.doanappdkhp.adapter.CongNoAdapter;
import com.example.doanappdkhp.api.ApiService;
import com.example.doanappdkhp.data_local.DataLocalManager;
import com.example.doanappdkhp.entity.CongNo;
import com.example.doanappdkhp.fragment.MainForm;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class xem_congno extends AppCompatActivity {
    String[] hocky = {"1","2","3"};
    Spinner nam_spinner, hocky_spinner;
    Button btnGet;
    private static final String TAG = "MainActivity";
    List<CongNo> congNoList;
    RecyclerView rcv;
    CongNoAdapter adt;
    TextView tvTongTien;
    ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_xem_congno);

        progressBar = findViewById(R.id.progressBarCongNo);
        progressBar.setVisibility(View.GONE);
        tvTongTien = findViewById(R.id.tvTongTienCN);
        rcv = findViewById(R.id.rcv_CongNo);
        congNoList = new ArrayList<>();
        adt = new CongNoAdapter(getApplicationContext(), (ArrayList<CongNo>) congNoList);

        rcv.setAdapter(adt);
        rcv.setLayoutManager(new GridLayoutManager(xem_congno.this, 1));
        rcv.setHasFixedSize(true);

        btnGet = findViewById(R.id.btnLocCongNo);
        nam_spinner = findViewById(R.id.spinnerNamCongNo);
        hocky_spinner = findViewById(R.id.spinnerHocKyCongNo);

        ArrayAdapter adapternam = new ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item, MainForm.listNam);
        adapternam.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        nam_spinner.setAdapter(adapternam);

        ArrayAdapter adapterhocky = new ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item, hocky);
        adapterhocky.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        hocky_spinner.setAdapter(adapterhocky);

        btnGet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                progressBar.setVisibility(View.VISIBLE);
                getCongNo();
            }
        });

    }
    private void getCongNo() {
        ApiService.apiService.getCongNoTheoMSSV(DataLocalManager.getMSSV(),nam_spinner.getSelectedItem().toString(),Integer.parseInt(hocky_spinner.getSelectedItem().toString())).enqueue(new Callback<List<CongNo>>() {

            @Override
            public void onResponse(Call<List<CongNo>> call, Response<List<CongNo>> response) {
                ArrayList<Integer> lstTong = new ArrayList<>();
               // Toast.makeText(xem_congno.this, "Call api success!!!", Toast.LENGTH_SHORT).show();
                congNoList = response.body();

                //Toast.makeText(xem_congno.this, "" + response.body().size(), Toast.LENGTH_SHORT).show();
                if (congNoList != null) {
                    adt.setCongNos((ArrayList<CongNo>) congNoList);
                    progressBar.setVisibility(View.GONE);
                       int cn = congNoList.size();
                       try {
                      for (int i = 0; i < response.body().size(); i++) {
                            Integer a = congNoList.get(i).getSoTinChi();
                            lstTong.add(a);

                       }
                } catch (Exception e) {
                    e.printStackTrace();
                }
                    int tong = 0;
                    for (Integer b: lstTong) {
                        tong = tong + b;
                    }
                    DecimalFormat formatter = new DecimalFormat("###,###,###");
                    tvTongTien.setText(formatter.format(tong* 790000 )+" VNĐ");
                }
            }
            @Override
            public void onFailure(Call<List<CongNo>> call, Throwable t) {
                Toast.makeText(xem_congno.this, "Call api error", Toast.LENGTH_SHORT).show();
            }
        });
    }
}