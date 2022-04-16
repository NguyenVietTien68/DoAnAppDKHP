package com.example.doanappdkhp.gui;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.doanappdkhp.R;
import com.example.doanappdkhp.adapter.LHPDaDangKyAdapter;
import com.example.doanappdkhp.adapter.LopHocPhanAdapter;
import com.example.doanappdkhp.adapter.LopHocPhanLTAdapter;
import com.example.doanappdkhp.adapter.LopHocPhanTHAdapter;
import com.example.doanappdkhp.adapter.MonHocPhanAdapter;
import com.example.doanappdkhp.api.ApiService;
import com.example.doanappdkhp.entity.DKHPSV;
import com.example.doanappdkhp.entity.DeleteLHPDDK;
import com.example.doanappdkhp.entity.KiemTraLichHoc;
import com.example.doanappdkhp.entity.LHPDaDangKy;
import com.example.doanappdkhp.entity.LopHocPhanTH;
import com.example.doanappdkhp.entity.MonTienQuyet;
import com.example.doanappdkhp.entity.SLDaDangKy;
import com.example.doanappdkhp.entity.LopHocPhan;
import com.example.doanappdkhp.entity.LopHocPhanLT;
import com.example.doanappdkhp.entity.MonHocPhan;
import com.example.doanappdkhp.my_interface.ICLickItemLHP;
import com.example.doanappdkhp.my_interface.IClickItemLHPDDK;
import com.example.doanappdkhp.my_interface.IClickItemLHPTH;
import com.example.doanappdkhp.my_interface.IClickItemMHP;
import com.example.doanappdkhp.my_interface.IClickItemNhomLT;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DKHP extends AppCompatActivity {
    private static final String TAG = "MainActivity";
    String[] nam = {"2021-2022", "2022-2023", "2023-2024", "2024-2025"};
    String[] hocky = {"1", "2", "3"};
    Spinner nam_spinner, hocky_spinner;
    Button btnGetDSMHP, btnChonMaMHP;
    String mhp, lhp, nhomltvsth, lhpddk;
    int slDDK, siso;
    int mtq, ktmtq;
    int kiemTraLH;
    int soluong;
    String namkt;
    int hockykt;
    Button btnDKHP;
    String lhpddkkt;
    RadioGroup rdoGroup;
    RadioButton rdoButton;
    boolean check  = false;


    List<KiemTraLichHoc> kiemTraLichHocList;
    List<LHPDaDangKy> lhpDaDangKyList;
    List<LopHocPhanLT> lopHocPhanLTList;
    List<LopHocPhanTH>lopHocPhanTHList;
    List<MonHocPhan> monHocPhanList;
    List<LopHocPhan> lopHocPhanList;
    RecyclerView rcvMHP, rcvLHP, rcvLHPLT, rcvLHPDDK, rcvLHPTH;
    LHPDaDangKyAdapter adtLHPDDK;
    LopHocPhanLTAdapter adtLHPLTTH;
    LopHocPhanTHAdapter adtTH;
    MonHocPhanAdapter adtMHP;
    LopHocPhanAdapter adtLHP;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dkhp);

        rdoButton = findViewById(R.id.radioButtonLHPLT);
        //rdoGroup = findViewById(R.id.rdoGroup);

//-------------------Đăng kí học phần cho sinh viên-------------------------//
        btnDKHP = findViewById(R.id.btnDKHP);
        btnDKHP.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                 if(mtq >ktmtq){
                    Toast.makeText(DKHP.this, "Sinh viên chưa học môn tiên quyết", Toast.LENGTH_SHORT).show();
                }else if(kiemTraLH >0){
                    Toast.makeText(DKHP.this, "Trùng lịch học", Toast.LENGTH_SHORT).show();
                }
                else{

                     //////
                    updateSLSVDKHP();
                    postDKHP();
                }
                mhp = "";
                lhp = "";
                nhomltvsth = "";
                lhpddk = "";
                getDSLopHocPhanTH();
                getDSMonHocPhan();
                getDSLopHocPhan();
                getDSLopHocPhanLT();
                getDSLopHocPhanDDK();
            }
        });
//        Bundle bundle = getIntent().getExtras();
//
//        if (bundle==null){
//            return;
//        }
//        MonHocPhan monHocPhan = (MonHocPhan) bundle.get("mamhp");
//        //TextView tvMaMHP = findViewById(R.id.tv_MaMonHPDKHP);
//        a = monHocPhan.getMaMHP()

        btnGetDSMHP = findViewById(R.id.btnLocDSMonHocPhan);
        nam_spinner = findViewById(R.id.spinnerNamMHP);
        hocky_spinner = findViewById(R.id.spinnerHocKyMHP);

        ArrayAdapter adapternam = new ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item, nam);
        adapternam.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        nam_spinner.setAdapter(adapternam);

        ArrayAdapter adapterhocky = new ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item, hocky);
        adapterhocky.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        hocky_spinner.setAdapter(adapterhocky);

        hockykt  = Integer.parseInt(hocky_spinner.getSelectedItem().toString());
        namkt = nam_spinner.getSelectedItem().toString();
//-------------------------Chọn học kì vs Năm rồi nhấn lọc-----------------------------///
        btnGetDSMHP.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mhp = "";
                lhp = "";
                nhomltvsth = "";
                lhpddk = "";
                getDSLopHocPhanTH();
                getDSMonHocPhan();
                getDSLopHocPhan();
                getDSLopHocPhanLT();
                getDSLopHocPhanDDK();

            }
        });

        kiemTraLichHocList = new ArrayList<>();

//--------------------DS Môn học phần--------------------------------------------------------------//
        rcvMHP = findViewById(R.id.rcv_MonHocPhan);
        monHocPhanList = new ArrayList<>();
        adtMHP = new MonHocPhanAdapter(getApplicationContext(), (ArrayList<MonHocPhan>) monHocPhanList, new IClickItemMHP() {
            @Override
            public void onClickItemMHP(MonHocPhan monHocPhan) {
                //oclickChonMHP(monHocPhan);
                mhp = monHocPhan.getMaMHP();
                rcvMHP.post(new Runnable() {
                    @Override
                    public void run() {
                        adtMHP.notifyDataSetChanged();
                    }
                });
                getDSLopHocPhan();
            }
        });
        rcvMHP.setAdapter(adtMHP);
        rcvMHP.setLayoutManager(new GridLayoutManager(DKHP.this, 1));
        rcvMHP.setHasFixedSize(true);
//--------------------DS Lớp học phần-----------------------------------------------------------------//
        rcvLHP = findViewById(R.id.rcv_LopHocPhan);
        lopHocPhanList = new ArrayList<>();
        adtLHP = new LopHocPhanAdapter(getApplicationContext(), (ArrayList<LopHocPhan>) lopHocPhanList, new ICLickItemLHP() {
            @Override
            public void onClickItemLHP(LopHocPhan lopHocPhan) {
                lhp = lopHocPhan.getMaLopHP();
                slDDK = lopHocPhan.getDaDangKy();
                siso = lopHocPhan.getSiSo();
                rcvLHP.post(new Runnable() {
                    @Override
                    public void run() {
                        adtLHP.notifyDataSetChanged();
                    }
                });
                if (siso == slDDK) {
                    Toast.makeText(DKHP.this, "Lớp đã đầy", Toast.LENGTH_SHORT).show();
                }
                else {
                    btnDKHP.setEnabled(false);
                    Toast.makeText(DKHP.this, "So lượng lhp Thực Hành = "+soluong, Toast.LENGTH_SHORT).show();
                    getDSLopHocPhanLT();
                    getDSLopHocPhanTH();
                    getMonTienQuyet();
                    getKiemTraMonTienQuyet();
                    Toast.makeText(DKHP.this, "Mtq= "+ mtq + "KTmtq= "+ ktmtq, Toast.LENGTH_SHORT).show();
                }
            }
        });
        rcvLHP.setAdapter(adtLHP);
        rcvLHP.setLayoutManager(new GridLayoutManager(DKHP.this, 1));
        rcvLHP.setHasFixedSize(true);

//--------------------DS Lớp học phần ly thuyet ------------------------------------------//
        rcvLHPLT = findViewById(R.id.rcv_LopHocPhanLTvsTH);
        lopHocPhanLTList = new ArrayList<>();
        adtLHPLTTH = new LopHocPhanLTAdapter(getApplicationContext(), (ArrayList<LopHocPhanLT>) lopHocPhanLTList, new IClickItemNhomLT() {
            @Override
            public void onClickItemNhomLTvsTH(LopHocPhanLT lopHocPhanLT) {
                nhomltvsth = lopHocPhanLT.getMaNhom();
                getKiemTraTrungLichHoc();
                btnDKHP.setEnabled(true);
                rcvLHPLT.post(new Runnable() {
                    @Override
                    public void run() {
                        adtLHPLTTH.notifyDataSetChanged();
                        Toast.makeText(DKHP.this, "LichHocTrung = "+kiemTraLH, Toast.LENGTH_SHORT).show();
                    }

                });
                //Toast.makeText(DKHP.this, "nam = "+namkt, Toast.LENGTH_SHORT).show();
                //Toast.makeText(DKHP.this, "hocky = "+hockykt, Toast.LENGTH_SHORT).show();
                //Toast.makeText(DKHP.this, "lop1 = "+lhp, Toast.LENGTH_SHORT).show();
                //Toast.makeText(DKHP.this, "nhom1 = "+nhomltvsth, Toast.LENGTH_SHORT).show();
                //Toast.makeText(DKHP.this, "lop2 = "+lhp, Toast.LENGTH_SHORT).show();
                //Toast.makeText(DKHP.this, "nhom2 = "+nhomltvsth, Toast.LENGTH_SHORT).show();
                //------------------------------Kiểm tra môn tiên quyết----------------------------------//

            }
        });

        rcvLHPLT.setAdapter(adtLHPLTTH);
        rcvLHPLT.setLayoutManager(new GridLayoutManager(DKHP.this, 1));
        rcvLHPLT.setHasFixedSize(true);
//--------------------DS Lớp học phần thực hành------------------------------------------//
        rcvLHPTH = findViewById(R.id.rcv_LopHocPhanTH);
        lopHocPhanTHList = new ArrayList<>();
        adtTH = new LopHocPhanTHAdapter(getApplicationContext(), (ArrayList<LopHocPhanTH>) lopHocPhanTHList, new IClickItemLHPTH() {
            @Override
            public void onClickItemTH(LopHocPhanTH lopHocPhanTH) {
                nhomltvsth = lopHocPhanTH.getMaNhom();
                soluong = lopHocPhanTH.getMaNhom().length();
                getKiemTraTrungLichHoc();
                btnDKHP.setEnabled(true);
                rcvLHPTH.post(new Runnable() {
                    @Override
                    public void run() {
                        adtTH.notifyDataSetChanged();
                    }
                });
                //Toast.makeText(DKHP.this, "nam = "+namkt, Toast.LENGTH_SHORT).show();
                //Toast.makeText(DKHP.this, "hocky = "+hockykt, Toast.LENGTH_SHORT).show();
                //Toast.makeText(DKHP.this, "lop1 = "+lhp, Toast.LENGTH_SHORT).show();
                //Toast.makeText(DKHP.this, "nhom1 = "+nhomltvsth, Toast.LENGTH_SHORT).show();
                //Toast.makeText(DKHP.this, "lop2 = "+lhp, Toast.LENGTH_SHORT).show();
                //Toast.makeText(DKHP.this, "nhom2 = "+nhomltvsth, Toast.LENGTH_SHORT).show();
                Toast.makeText(DKHP.this, "LichHocTrung = "+kiemTraLH, Toast.LENGTH_SHORT).show();
                //------------------------------Kiểm tra môn tiên quyết----------------------------------//
            }
        });
        rcvLHPTH.setAdapter(adtTH);
        rcvLHPTH.setLayoutManager(new GridLayoutManager(DKHP.this, 1));
        rcvLHPTH.setHasFixedSize(true);

//----------------------------------Lớp học phần đã đăng ký-------------------------------------------//
        rcvLHPDDK = findViewById(R.id.rcv_LHPDDK);
        lhpDaDangKyList = new ArrayList<>();
        adtLHPDDK = new LHPDaDangKyAdapter(getApplicationContext(), (ArrayList<LHPDaDangKy>) lhpDaDangKyList, new IClickItemLHPDDK() {
            @Override
            public void onCliCkItemLHPDDK(LHPDaDangKy lhpDaDangKy) {
            //----------------Dialog thông báo--------------------------------------//
                AlertDialog.Builder builder = new AlertDialog.Builder(DKHP.this);
                builder.setTitle("Thông báo");
                builder.setMessage("Xác nhận hủy môn học phần!!!");
                builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        lhpddk = lhpDaDangKy.getMaLopHP();
                        getDSLopHocPhanDDK();
                        getDSLopHocPhan();
                        deleteLHPDDK();
                        updateLHPDDK();

                    }
                });
                builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(getApplicationContext(),
                                "No Button Clicked",Toast.LENGTH_SHORT).show();
                    }
                });
                AlertDialog dialog = builder.create();
                dialog.show();
            }
        });
        rcvLHPDDK.setAdapter(adtLHPDDK);
        rcvLHPDDK.setLayoutManager(new GridLayoutManager(DKHP.this, 1));
        rcvLHPDDK.setHasFixedSize(true);
    }


    public void getDSMonHocPhan() {
        ApiService.apiService.getDSMonHocPhan("0001", Integer.parseInt(hocky_spinner.getSelectedItem().toString()), nam_spinner.getSelectedItem().toString()).enqueue(new Callback<List<MonHocPhan>>() {
            @Override
            public void onResponse(Call<List<MonHocPhan>> call, Response<List<MonHocPhan>> response) {
                Toast.makeText(DKHP.this, "Mon hoc phan "+ response.body().size(), Toast.LENGTH_SHORT).show();
                monHocPhanList = response.body();
                if (monHocPhanList != null) {
                    adtMHP.setMonHocPhans((ArrayList<MonHocPhan>) monHocPhanList);
                }
            }
            @Override
            public void onFailure(Call<List<MonHocPhan>> call, Throwable t) {
                Toast.makeText(DKHP.this, "Call api error MHP", Toast.LENGTH_SHORT).show();
            }
        });
    }

//    @Override
//    protected void onDestroy() {
//        super.onDestroy();
//        if(adtMHP != null){
//            adtMHP.release();
//        }
//    }
//    public void oclickChonMHP(MonHocPhan monHocPhan) {
//        Intent intent = new Intent(this, getClass());
//        Bundle bundle = new Bundle();
//        bundle.putSerializable("mamhp",monHocPhan);
//        intent.putExtras(bundle);
//
//        //startActivity(intent);
//
//    }
    //String b = monHocPhan.getMaMHP();
    public void getDSLopHocPhan() {
        ApiService.apiService.getDSLopHocPhan(mhp).enqueue(new Callback<List<LopHocPhan>>() {

            @Override
            public void onResponse(Call<List<LopHocPhan>> call, Response<List<LopHocPhan>> response) {
                //Toast.makeText(DKHP.this, "LopHocPhan "+ response.body().size(), Toast.LENGTH_SHORT).show();
                lopHocPhanList = response.body();
                if (lopHocPhanList != null) {
                    adtLHP.setLopHocPhans((ArrayList<LopHocPhan>) lopHocPhanList);
                }
            }
            @Override
            public void onFailure(Call<List<LopHocPhan>> call, Throwable t) {
                Toast.makeText(DKHP.this, "Call api error LHP", Toast.LENGTH_SHORT).show();
            }
        });
    }
    //---------------Lấy môn tiên quyết------------------------------------------------//
    public void getMonTienQuyet() {
        ApiService.apiService.getMonTienQuyet(lhp).enqueue(new Callback<List<MonTienQuyet>>() {
            @Override
            public void onResponse(Call<List<MonTienQuyet>> call, Response<List<MonTienQuyet>> response) {
                //Toast.makeText(DKHP.this, "Môn Tiên Quyết "+ response.body().size(), Toast.LENGTH_SHORT).show();
                    mtq = response.body().size();
            }
            @Override
            public void onFailure(Call<List<MonTienQuyet>> call, Throwable t) {
                Toast.makeText(DKHP.this, "Call api error LHP", Toast.LENGTH_SHORT).show();
            }
        });
    }
    //---------------Kiểm tra môn tiên quyết------------------------------------------------//
    public void getKiemTraMonTienQuyet() {
        ApiService.apiService.getKiemTraMonTienQuyet("0001",lhp).enqueue(new Callback<List<MonTienQuyet>>() {

            @Override
            public void onResponse(Call<List<MonTienQuyet>> call, Response<List<MonTienQuyet>> response) {
                //Toast.makeText(DKHP.this, "Kiểm tra môn Tiên Quyết "+ response.body().size(), Toast.LENGTH_SHORT).show();

                ktmtq = response.body().size();
            }
            @Override
            public void onFailure(Call<List<MonTienQuyet>> call, Throwable t) {
                Toast.makeText(DKHP.this, "Call api error LHP", Toast.LENGTH_SHORT).show();
            }
        });
    }
    //-----------------Kiểm Tra lịch học bị trùng---------------------------------------------------//
    public void getKiemTraTrungLichHoc() {

        ApiService.apiService.getKiemTraTrungLichHoc("0001",1, namkt,lhp, nhomltvsth ).enqueue(new Callback<List<KiemTraLichHoc>>() {

            @Override
            public void onResponse(Call<List<KiemTraLichHoc>> call, Response<List<KiemTraLichHoc>> response) {
                //Toast.makeText(DKHP.this, "Kiểm tra lich hoc ", Toast.LENGTH_SHORT).show();
                    kiemTraLH = response.body().size();

            }
            @Override
            public void onFailure(Call<List<KiemTraLichHoc>> call, Throwable t) {
                Toast.makeText(DKHP.this, "Call api error LHP", Toast.LENGTH_SHORT).show();
            }
        });
    }
    public void getDSLopHocPhanLT() {
        ApiService.apiService.getDSLopHocPhanLT(lhp).enqueue(new Callback<List<LopHocPhanLT>>() {

            @Override
            public void onResponse(Call<List<LopHocPhanLT>> call, Response<List<LopHocPhanLT>> response) {
                //Toast.makeText(DKHP.this, "LyThuyet va ThucHanh "+ response.body().size(), Toast.LENGTH_SHORT).show();
                lopHocPhanLTList = response.body();
                adtLHPLTTH.notifyDataSetChanged();
                if (lopHocPhanLTList != null) {
                    adtLHPLTTH.setLopHocPhanLTvsTHs((ArrayList<LopHocPhanLT>) lopHocPhanLTList);

                }
            }
            @Override
            public void onFailure(Call<List<LopHocPhanLT>> call, Throwable t) {
                Toast.makeText(DKHP.this, "Call api error LHP", Toast.LENGTH_SHORT).show();
            }
        });
    }
    public void getDSLopHocPhanTH() {
        ApiService.apiService.getDSLopHocPhanTH(lhp).enqueue(new Callback<List<LopHocPhanTH>>() {

            @Override
            public void onResponse(Call<List<LopHocPhanTH>> call, Response<List<LopHocPhanTH>> response) {
                //Toast.makeText(DKHP.this, "LyThuyet va ThucHanh "+ response.body().size(), Toast.LENGTH_SHORT).show();
                lopHocPhanTHList = response.body();
                if (lopHocPhanTHList != null) {
                    adtTH.setLopHocPhanTHs((ArrayList<LopHocPhanTH>) lopHocPhanTHList);
                }
            }
            @Override
            public void onFailure(Call<List<LopHocPhanTH>> call, Throwable t) {
                Toast.makeText(DKHP.this, "Call api error LHP", Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void getDSLopHocPhanDDK() {
        ApiService.apiService.getDSLopHocPhanDDK("0001", Integer.parseInt(hocky_spinner.getSelectedItem().toString()), nam_spinner.getSelectedItem().toString()).enqueue(new Callback<List<LHPDaDangKy>>() {
            @Override
            public void onResponse(Call<List<LHPDaDangKy>> call, Response<List<LHPDaDangKy>> response) {
                //Toast.makeText(DKHP.this, "Mon hoc phan "+ response.body().size(), Toast.LENGTH_SHORT).show();

                lhpDaDangKyList = response.body();
                int ddk = response.body().size();
                if (lhpDaDangKyList != null) {
                    adtLHPDDK.setLhpDaDangKys((ArrayList<LHPDaDangKy>) lhpDaDangKyList);
                }
            }
            @Override
            public void onFailure(Call<List<LHPDaDangKy>> call, Throwable t) {
                Toast.makeText(DKHP.this, "Call api error MHP", Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void postDKHP() {
        ApiService.apiService.dkhp("0001", lhp, nhomltvsth).enqueue(new Callback<DKHPSV>() {

            @Override
            public void onResponse(Call<DKHPSV> call, Response<DKHPSV> response) {
                Toast.makeText(DKHP.this, "DKHP success!!!", Toast.LENGTH_SHORT).show();

            }
            @Override
            public void onFailure(Call<DKHPSV> call, Throwable t) {
                Toast.makeText(DKHP.this, "Call api error DKHP", Toast.LENGTH_SHORT).show();
            }
        });
    }
    public void updateSLSVDKHP() {
        ApiService.apiService.updateSLSVDKHP( slDDK + 1, lhp).enqueue(new Callback<SLDaDangKy>() {

            @Override
            public void onResponse(Call<SLDaDangKy> call, Response<SLDaDangKy> response) {
                //Toast.makeText(DKHP.this, "Call api success!!!", Toast.LENGTH_SHORT).show();

            }
            @Override
            public void onFailure(Call<SLDaDangKy> call, Throwable t) {
                Toast.makeText(DKHP.this, "Call api error DKHP", Toast.LENGTH_SHORT).show();
            }
        });
    }
    public void updateLHPDDK() {
        ApiService.apiService.updateSLSVDKHP( slDDK-1, lhpddk).enqueue(new Callback<SLDaDangKy>() {

            @Override
            public void onResponse(Call<SLDaDangKy> call, Response<SLDaDangKy> response) {
                Toast.makeText(DKHP.this, "Call api success!!!", Toast.LENGTH_SHORT).show();

            }
            @Override
            public void onFailure(Call<SLDaDangKy> call, Throwable t) {
                Toast.makeText(DKHP.this, "Call api error DKHP", Toast.LENGTH_SHORT).show();
            }
        });
    }
    public void deleteLHPDDK() {
        ApiService.apiService.deleteLHPDDK("0001", lhpddk).enqueue(new Callback<DeleteLHPDDK>() {

            @Override
            public void onResponse(Call<DeleteLHPDDK> call, Response<DeleteLHPDDK> response) {
                Toast.makeText(DKHP.this, "delete success!!!"+ lhpddk, Toast.LENGTH_SHORT).show();

            }
            @Override
            public void onFailure(Call<DeleteLHPDDK> call, Throwable t) {
                Toast.makeText(DKHP.this, "Call api error DKHP", Toast.LENGTH_SHORT).show();
            }
        });
    }

}