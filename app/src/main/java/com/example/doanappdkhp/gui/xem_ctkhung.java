package com.example.doanappdkhp.gui;

import static com.example.doanappdkhp.MainActivity.mssv;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.doanappdkhp.R;
import com.example.doanappdkhp.adapter.CTKhungAdapter;
import com.example.doanappdkhp.api.ApiService;
import com.example.doanappdkhp.entity.CTKhung;
import com.example.doanappdkhp.entity.Sinhvien;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class xem_ctkhung extends AppCompatActivity {
    private static final String TAG = "MainActivity";
    List<CTKhung> ctKhungArrayList;
    RecyclerView rcv;
    CTKhungAdapter adt;
    TextView tvChuyenNganh;
    ProgressBar progressBar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_xem_ctkhung);

        progressBar = findViewById(R.id.progressBarCTKhung);
        progressBar.setVisibility(View.VISIBLE);
        tvChuyenNganh = findViewById(R.id.tv_ChuyenNganhCTK);
        rcv = findViewById(R.id.rcv_CTK);
        ctKhungArrayList = new ArrayList<>();
        adt = new CTKhungAdapter(getApplicationContext(), (ArrayList<CTKhung>) ctKhungArrayList);
        rcv.setAdapter(adt);
        rcv.setLayoutManager(new GridLayoutManager(xem_ctkhung.this, 1));
        rcv.setHasFixedSize(true);
        getCTKhung();
    }

    private void getCTKhung() {
        ApiService.apiService.getCTKhungTheoMSSV(mssv).enqueue(new Callback<List<CTKhung>>() {
            @Override
            public void onResponse(Call<List<CTKhung>> call, Response<List<CTKhung>> response) {
                //Toast.makeText(xem_ctkhung.this, "Call api success!!!", Toast.LENGTH_SHORT).show();

                ctKhungArrayList = response.body();

                //Toast.makeText(xem_ctkhung.this, ""+ctKhungArrayList.size(), Toast.LENGTH_SHORT).show();
                if (ctKhungArrayList != null) {
                    adt.setCtKhungs((ArrayList<CTKhung>) ctKhungArrayList);
                       int ctk = ctKhungArrayList.size();
                            for(int i = 0; i< ctk; i++) {
                            tvChuyenNganh.setText(String.valueOf(ctKhungArrayList.get(i).getTenChuyenNganh()));
                            adt.notifyDataSetChanged();
                                progressBar.setVisibility(View.GONE);
//                           tvhocky.setText(String.valueOf(ctKhungArrayList.get(1).getHocKy()));
//                           tvmamhp.setText(ctKhungArrayList.get(1).getMaMHP());
//                            tvtenmhhp.setText(ctKhungArrayList.get(1).getTenMHHP());
//                        String maMHP = ctKhungArrayList.get(i).getMaMHP();
//                        Log.e(TAG, "Revenue:\nMSSV: " + ctKhungArrayList.get(i).getTenMHHP());
//                        String tenMHHP = ctKhungArrayList.get(i).getTenMHHP();
//                        int hocky = ctKhungArrayList.get(i).getHocKy();
//                            CTKhung ctKhung1 = new CTKhung(maMHP, tenMHHP, hocky);
//                            ctKhungArrayList.add(ctKhung1);
                        }
                }
            }
            @Override
            public void onFailure(Call<List<CTKhung>> call, Throwable t) {
                Toast.makeText(xem_ctkhung.this, "Call api error", Toast.LENGTH_SHORT).show();
            }
        });
    }

}