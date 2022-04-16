package com.example.doanappdkhp.gui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import com.example.doanappdkhp.R;
import com.example.doanappdkhp.adapter.CTKhungAdapter;
import com.example.doanappdkhp.adapter.CongNoAdapter;
import com.example.doanappdkhp.api.ApiService;
import com.example.doanappdkhp.entity.CTKhung;
import com.example.doanappdkhp.entity.CongNo;
import com.example.doanappdkhp.entity.KiemTraLichHoc;

import org.json.JSONException;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class xem_congno extends AppCompatActivity {
    private static final String TAG = "MainActivity";
    List<CongNo> congNoList;
    RecyclerView rcv;
    CongNoAdapter adt;
    TextView tvTongTien;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_xem_congno);

        tvTongTien = findViewById(R.id.tvTongTienCN);
        rcv = findViewById(R.id.rcv_CongNo);
        congNoList = new ArrayList<>();
        adt = new CongNoAdapter(getApplicationContext(), (ArrayList<CongNo>) congNoList);
        getCongNo();
        rcv.setAdapter(adt);
        rcv.setLayoutManager(new GridLayoutManager(xem_congno.this, 1));
        rcv.setHasFixedSize(true);

    }
    private void getCongNo() {
        ApiService.apiService.getCongNoTheoMSSV("0001").enqueue(new Callback<List<CongNo>>() {

            @Override
            public void onResponse(Call<List<CongNo>> call, Response<List<CongNo>> response) {
                ArrayList<Integer> lstTong = new ArrayList<>();
               // Toast.makeText(xem_congno.this, "Call api success!!!", Toast.LENGTH_SHORT).show();
                congNoList = response.body();
                adt.setCongNos((ArrayList<CongNo>) congNoList);
                //Toast.makeText(xem_congno.this, "" + response.body().size(), Toast.LENGTH_SHORT).show();
                if (congNoList != null) {
                       int cn = congNoList.size();
                       try {
                      for (int i = 0; i < response.body().size(); i++) {
                          //Toast.makeText(xem_congno.this, "" + congNoList.get(i).getSoTinChi(), Toast.LENGTH_SHORT).show();
                            //tvTongTien.setText(String.valueOf((congNoList.get(i).getSoTinChi())*790000));
                            Integer a = congNoList.get(i).getSoTinChi();
                            lstTong.add(a);

//                            String tenMHHP = congNoArrayList.get(i).getTenMHHP();
//
//                            int sTC = congNoArrayList.get(i).getSoTinChi();
//                            String nam = congNoArrayList.get(i).getNam();
//                            int hocKy = congNoArrayList.get(i).getHocKy();
//                            Log.e(TAG, "Revenue:\nMSSV: " + congNoArrayList.get(i).getTenMHHP());
//                            Toast.makeText(xem_congno.this, "" + hocKy, Toast.LENGTH_SHORT).show();
//                            CongNo congNo = new CongNo(tenMHHP, sTC, nam, hocKy);
//                            congNoArrayList.add(congNo);
//                            adt.getItemCount();
//                            adt.notifyDataSetChanged();

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