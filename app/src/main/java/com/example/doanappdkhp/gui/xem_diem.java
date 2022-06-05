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
import android.widget.Toast;

import com.example.doanappdkhp.LoginActivity;
import com.example.doanappdkhp.R;
import com.example.doanappdkhp.adapter.DiemAdapter;
import com.example.doanappdkhp.api.ApiService;
import com.example.doanappdkhp.data_local.DataLocalManager;
import com.example.doanappdkhp.entity.Diem;
import com.example.doanappdkhp.entity.MonHoc;
import com.example.doanappdkhp.entity.TaiKhoanSV;
import com.example.doanappdkhp.fragment.MainForm;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class xem_diem extends AppCompatActivity {
    String[] hocky = {"1","2","3"};
    Spinner nam_spinner, hocky_spinner;
    Button btnGet;
    private static final String TAG = "MainActivity";
    List<Diem> diemList;
    List<MonHoc> monHocLTList;
    List<MonHoc> monHocTHList;
    public  static List<String> listMonLT;
    public  static List<String> listMonTH ;
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
        progressBar.setVisibility(View.GONE);
        rcv = findViewById(R.id.rcv_Diem);
        diemList = new ArrayList<>();
        adt = new DiemAdapter(getApplicationContext(), (ArrayList<Diem>) diemList);
        rcv.setAdapter(adt);
        rcv.setLayoutManager(new GridLayoutManager(xem_diem.this, 1));
        rcv.setHasFixedSize(true);

        btnGet = findViewById(R.id.btnLocDiem);
        nam_spinner = findViewById(R.id.spinnerNamDiem);
        hocky_spinner = findViewById(R.id.spinnerHocKyDiem);

        ArrayAdapter adapternam = new ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item, MainForm.listNam);
        adapternam.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        nam_spinner.setAdapter(adapternam);

        ArrayAdapter adapterhocky = new ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item, hocky);
        adapterhocky.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        hocky_spinner.setAdapter(adapterhocky);

        listMonLT = new ArrayList<>();
        listMonTH = new ArrayList<>();

        btnGet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                progressBar.setVisibility(View.VISIBLE);
                getMonLyThuyet();
                getMonThucHanh();
                getDiem();

            }
        });

    }
    private void getMonLyThuyet() {
        ApiService.apiService.getMonLyThuyet(DataLocalManager.getMSSV(), nam_spinner.getSelectedItem().toString(), Integer.parseInt(hocky_spinner.getSelectedItem().toString())).enqueue(new Callback<List<MonHoc>>() {
            @Override
            public void onResponse(Call<List<MonHoc>> call, Response<List<MonHoc>> response) {
                //Toast.makeText(xem_diem.this, "Call api success!!!" + response.body().size(), Toast.LENGTH_SHORT).show();
                monHocLTList = response.body();
                if (monHocLTList != null) {
                    for (int i=0; i < monHocLTList.size();i++ ){
                        listMonLT.add(monHocLTList.get(i).getTenMHHP());
                    }

                }

            }

            @Override
            public void onFailure(Call<List<MonHoc>> call, Throwable t) {
                Toast.makeText(xem_diem.this, "Lỗi kết nối", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void getMonThucHanh() {
            ApiService.apiService.getMonThucHanh(DataLocalManager.getMSSV(), nam_spinner.getSelectedItem().toString(), Integer.parseInt(hocky_spinner.getSelectedItem().toString())).enqueue(new Callback<List<MonHoc>>() {
                @Override
                public void onResponse(Call<List<MonHoc>> call, Response<List<MonHoc>> response) {
                    //Toast.makeText(xem_diem.this, "Call api success!!!" + response.body().size(), Toast.LENGTH_SHORT).show();
                    monHocTHList = response.body();
                    if (monHocTHList != null) {
                        for (int i=0; i < monHocTHList.size();i++ ){
                            listMonTH.add(monHocTHList.get(i).getTenMHHP());
                            //Toast.makeText(xem_diem.this, "Mon hocTH:"+monHocTHList.get(i).getTenMHHP(), Toast.LENGTH_SHORT).show();
                        }
                    }
                }

                @Override
                public void onFailure(Call<List<MonHoc>> call, Throwable t) {
                    Toast.makeText(xem_diem.this, "Lỗi kết nối", Toast.LENGTH_SHORT).show();
                }
            });
        }
    private void getDiem() {
        ApiService.apiService.getDiemTheoMSSV(DataLocalManager.getMSSV(), nam_spinner.getSelectedItem().toString(), Integer.parseInt(hocky_spinner.getSelectedItem().toString())).enqueue(new Callback<List<Diem>>() {
            @Override
            public void onResponse(Call<List<Diem>> call, Response<List<Diem>> response) {
                 //Toast.makeText(xem_diem.this, "Call api success!!!"+ response.body().size(), Toast.LENGTH_SHORT).show();
                diemList = response.body();
                if (diemList != null) {
                adt.setDiems((ArrayList<Diem>) diemList);
                progressBar.setVisibility(View.GONE);
                }
            }
            @Override
            public void onFailure(Call<List<Diem>> call, Throwable t) {
                Toast.makeText(xem_diem.this, "Lỗi kết nối", Toast.LENGTH_SHORT).show();
            }
        });
    }
}