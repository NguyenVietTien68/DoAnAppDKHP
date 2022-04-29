package com.example.doanappdkhp.gui;

import static com.example.doanappdkhp.MainActivity.mssv;

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
import com.example.doanappdkhp.adapter.LichHocAdapter;
import com.example.doanappdkhp.api.ApiService;
import com.example.doanappdkhp.entity.LichHoc;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class xem_lich extends AppCompatActivity {
    String[] nam = {"2021-2022", "2022-2023", "2023-2024", "2024-2025"};
    String[] hocky = {"1","2","3"};
    Spinner nam_spinner, hocky_spinner;
    TextView txtTest;
    Button btnGet;

    private static final String TAG = "MainActivity";
    List<LichHoc> lichHocList;
    RecyclerView rcv;
    LichHocAdapter adt;
    ProgressBar progressBar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_xem_lich);


        progressBar = findViewById(R.id.progressBarLich);
        progressBar.setVisibility(View.GONE);
        rcv = findViewById(R.id.rcv_lichHoc);
        lichHocList = new ArrayList<>();
        adt = new LichHocAdapter(getApplicationContext(), (ArrayList<LichHoc>) lichHocList);
        rcv.setAdapter(adt);
        rcv.setLayoutManager(new GridLayoutManager(xem_lich.this, 1));
        rcv.setHasFixedSize(true);

        //--------------------------//---------------------------------//
        btnGet = findViewById(R.id.btnLocLichHoc);
        nam_spinner = findViewById(R.id.spinnerNam);
        hocky_spinner = findViewById(R.id.spinnerHocKy);
//        txtTest = findViewById(R.id.txtText);


        ArrayAdapter adapternam = new ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item, nam);
        adapternam.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        nam_spinner.setAdapter(adapternam);

        ArrayAdapter adapterhocky = new ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item, hocky);
        adapterhocky.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        hocky_spinner.setAdapter(adapterhocky);


        btnGet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                progressBar.setVisibility(View.VISIBLE);
//                txtTest.setText(nam_spinner.getSelectedItem().toString());
                getLichHoc();
            }
        });
    }

    private void getLichHoc() {
        ApiService.apiService.getLichHoc(Integer.parseInt(hocky_spinner.getSelectedItem().toString()), nam_spinner.getSelectedItem().toString(),mssv).enqueue(new Callback<List<LichHoc>>() {

            @Override
            public void onResponse(Call<List<LichHoc>> call, Response<List<LichHoc>> response) {
//                Toast.makeText(xem_lich.this, "Call api success!!!"+ response.body().size(), Toast.LENGTH_SHORT).show();
                lichHocList = response.body();
                if (lichHocList != null) {
                    adt.setLichHocs((ArrayList<LichHoc>) lichHocList);
                    progressBar.setVisibility(View.GONE);
                }
            }
            @Override
            public void onFailure(Call<List<LichHoc>> call, Throwable t) {
                Toast.makeText(xem_lich.this, "Call api error", Toast.LENGTH_SHORT).show();
            }
        });
    }
}