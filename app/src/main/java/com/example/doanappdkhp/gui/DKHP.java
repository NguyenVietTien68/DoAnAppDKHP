package com.example.doanappdkhp.gui;

import static com.example.doanappdkhp.MainActivity.mssv;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ProgressBar;
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
import com.example.doanappdkhp.entity.NamHoc;
import com.example.doanappdkhp.entity.SLDaDangKy;
import com.example.doanappdkhp.entity.LopHocPhan;
import com.example.doanappdkhp.entity.LopHocPhanLT;
import com.example.doanappdkhp.entity.MonHocPhan;
import com.example.doanappdkhp.fragment.InfoStudent;
import com.example.doanappdkhp.my_interface.ICLickItemLHP;
import com.example.doanappdkhp.my_interface.IClickItemLHPDDK;
import com.example.doanappdkhp.my_interface.IClickItemLHPTH;
import com.example.doanappdkhp.my_interface.IClickItemMHP;
import com.example.doanappdkhp.my_interface.IClickItemNhomLT;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DKHP extends AppCompatActivity {
//    public static NamHoc namHoc;
    public  static ArrayList<String> listNam ;
    public static ArrayAdapter adapternam;
    String bien;
    private static final String TAG = "MainActivity";
    String[] hocky1 = {"08", "09", "10","11"};
    String[] hocky2 = {"12", "01", "02","03","04", "05"};
    String[] hocky3 = {"06", "07"};
    String[] nam = {"2021-2022", "2022-2023", "2023-2024", "2024-2025"};
    String[] hocky = {"1", "2", "3"};
    ProgressBar progressBarMHP, progressBarLHP, progressBarLT, progressBarTH, progressBarDelete;
    public static Spinner nam_spinner;
    public static Spinner hocky_spinner;
    Button btnGetDSMHP, btnChonMaMHP;
    public  static String mhp, lhp, nhomlt,nhomth, lhpddk;
    int slDDK, siso;
    int ddk;
    public static int slLT, slTH, slnhomLT, slnhomTH;
    String malhp;
    int mtq, ktmtq;
    int kiemTraLT, kiemTraTH;
    int soluong;
    String namkt;
    int hockykt;
    Button btnDKHP;
    String lhpddkkt;
    RadioButton rdoButton;
    boolean check  = false;

    List<KiemTraLichHoc> kiemTraLichHocList;
    List<LHPDaDangKy> lhpDaDangKyList;
    List<LopHocPhanLT> lopHocPhanLTList;
    List<LopHocPhanTH>lopHocPhanTHList;
    List<MonHocPhan> monHocPhanList;
    List<LopHocPhan> lopHocPhanList;
    List<NamHoc> namHocList = new ArrayList<>();
    RecyclerView rcvMHP, rcvLHP, rcvLHPLT, rcvLHPDDK, rcvLHPTH;
    LHPDaDangKyAdapter adtLHPDDK;
    LopHocPhanLTAdapter adtLT;
    LopHocPhanTHAdapter adtTH;
    MonHocPhanAdapter adtMHP;
    LopHocPhanAdapter adtLHP;

    LocalDate dateToday;

    @SuppressLint("NewApi")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dkhp);

        progressBarMHP = findViewById(R.id.progressBarMHP);
        progressBarMHP.setVisibility(View.GONE);
        progressBarLHP = findViewById(R.id.progressBarLHP);
        progressBarLHP.setVisibility(View.GONE);
        progressBarLT = findViewById(R.id.progressBarLT);
        progressBarLT.setVisibility(View.GONE);
        progressBarTH = findViewById(R.id.progressBarTH);
        progressBarTH.setVisibility(View.GONE);
        progressBarDelete = findViewById(R.id.progressBarDelete);
        progressBarDelete.setVisibility(View.GONE);
        rdoButton = findViewById(R.id.radioButtonLHPLT);
        
        //----------lấy tháng hiện tại so sánh ----------------------//
        dateToday = LocalDate.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM");
        String datefomat = formatter.format(dateToday);
        Log.d("Month:---------",datefomat+"");

//        for (int i = 0; i<hocky2.length; i++){
//            //hocky2[i].toString();
//            ky2 = hocky2[i];
//            Log.d("hihihihi",ky2);
//            if (datefomat.contains(ky2)){
//                Toast.makeText(DKHP.this, "heheheheheheh", Toast.LENGTH_SHORT).show();
//            }
//        }

        //Log.d("HocKi2", );

        getNamHoc();
        listNam = new ArrayList<>();

//-------------------Đăng kí học phần cho sinh viên-------------------------//
        btnDKHP = findViewById(R.id.btnDKHP);

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


        adapternam = new ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item,listNam);
        adapternam.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        nam_spinner.setAdapter(adapternam);


        ArrayAdapter adapterhocky = new ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item, hocky);
        adapterhocky.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        hocky_spinner.setAdapter(adapterhocky);

        hockykt  = Integer.parseInt(hocky_spinner.getSelectedItem().toString());

        //namkt = nam_spinner.getSelectedItem().toString();
        btnDKHP.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                for (int i = 0; i<hocky1.length; i++){
//                    //hocky2[i].toString();
//                    String ky1;
//                    ky1 = hocky1[i];
//                    //Toast.makeText(DKHP.this, "HKKT"+Integer.parseInt(hocky_spinner.getSelectedItem().toString()), Toast.LENGTH_SHORT).show();
//                    Log.d("hihihihi",ky1);
//                    if (datefomat.contains(ky1) && Integer.parseInt(hocky_spinner.getSelectedItem().toString())==2 || Integer.parseInt(hocky_spinner.getSelectedItem().toString())==3){
//                        Toast.makeText(DKHP.this, "hết thời gian gian đăng ký", Toast.LENGTH_SHORT).show();
//                        return;
//                    }else{
//                        for (int u = 0; u<hocky2.length; u++){
//                            //hocky2[i].toString();
//                            String ky2;
//                            ky2 = hocky2[u];
//                            //Toast.makeText(DKHP.this, "HKKT"+Integer.parseInt(hocky_spinner.getSelectedItem().toString()), Toast.LENGTH_SHORT).show();
//                            Log.d("hihihihi",ky2);
//                            if (datefomat.contains(ky2) && Integer.parseInt(hocky_spinner.getSelectedItem().toString())==1){
//                                Toast.makeText(DKHP.this, "hết thời gian gian đăng ký", Toast.LENGTH_SHORT).show();
//                                return;
//                            }else{
//                                for (int j = 0; j<hocky3.length; j++){
//                                    //hocky2[i].toString();
//                                    String ky3;
//                                    ky3 = hocky3[j];
//                                    //Toast.makeText(DKHP.this, "HKKT"+Integer.parseInt(hocky_spinner.getSelectedItem().toString()), Toast.LENGTH_SHORT).show();
//                                    Log.d("hihihihi",ky3);
//                                    if (datefomat.contains(ky3) && Integer.parseInt(hocky_spinner.getSelectedItem().toString())==1 || Integer.parseInt(hocky_spinner.getSelectedItem().toString())==2){
//                                        Toast.makeText(DKHP.this, "hết thời gian gian đăng ký", Toast.LENGTH_SHORT).show();
//                                        return;
//                                    }
//                                }
//                            }
//
//                        }
//                    }
//
//                }


                if(mtq >ktmtq){
                    Toast.makeText(DKHP.this, "Sinh viên chưa học môn tiên quyết", Toast.LENGTH_SHORT).show();
                }else if(kiemTraLT >0){
                    Toast.makeText(DKHP.this, "Trùng lịch học lý thuyết", Toast.LENGTH_SHORT).show();
                }else if(kiemTraTH > 0){
                    Toast.makeText(DKHP.this, "Trùng lịch học thực hành", Toast.LENGTH_SHORT).show();

                }
                //khi lớp học phần có cả lý thuyết vs thực hành
                else if(slTH >0 && slLT >0 && slnhomTH >0 && slnhomLT >0){
                    //----Kiểm tra xem sinh viên chọn cả 2 nhóm TH vs LT hay chưa
                    if(slTH >0 && slLT >0 && slnhomTH >0 && nhomlt==""){
                        Toast.makeText(DKHP.this, "Chưa chọn nhóm lý thuyết", Toast.LENGTH_SHORT).show();
                    }else if (slTH >0 && slLT >0 && slnhomLT > 0 && nhomth == "" ){
                        Toast.makeText(DKHP.this, "Chưa chọn nhóm thực hành", Toast.LENGTH_SHORT).show();
                    }else{
                        updateSLSVDKHP();
                        postDKHPTH();
                        postDKHPLT();
                        adtMHP.notifyDataSetChanged();

                    }
                    //khi lớp học phần chỉ có lý thuyết
                }else if(slLT >0 && slTH ==0 && slnhomLT >0){
                    updateSLSVDKHP();
                    postDKHPLT();
                    //khi lớp học phần chỉ có thực hành
                }else if (slTH >0 && slLT ==0 && slnhomTH >0){
                    updateSLSVDKHP();
                    postDKHPTH();
                }
                mhp = "";
                lhp = "";
                nhomlt = "";
                nhomth = "";
                lhpddk = "";
                getDSLopHocPhanTH();
                getDSMonHocPhan();
                getDSLopHocPhan();
                getDSLopHocPhanLT();
                getDSLopHocPhanDDK();
            }
        });
//-------------------------Chọn học kì vs Năm rồi nhấn lọc-----------------------------///
        btnGetDSMHP.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                progressBarMHP.setVisibility(View.VISIBLE);
                adtLHP.notifyDataSetChanged();
                adtLHPDDK.notifyDataSetChanged();
                adtTH.notifyDataSetChanged();
                adtLT.notifyDataSetChanged();
                adtMHP.notifyDataSetChanged();
                mhp = "";
                lhp = "";
                nhomlt = "";
                nhomth = "";
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
                progressBarLHP.setVisibility(View.VISIBLE);
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

                progressBarLT.setVisibility(View.VISIBLE);
                progressBarTH.setVisibility(View.VISIBLE);
                getLopHocPhan();
                lhp = lopHocPhan.getMaLopHP();
                slDDK = lopHocPhan.getDaDangKy();
                Toast.makeText(DKHP.this, "sl lhp ddk = "+slDDK, Toast.LENGTH_SHORT).show();
                siso = lopHocPhan.getSiSo();
                rcvLHP.post(new Runnable() {
                    @Override
                    public void run() {
                        adtLHP.notifyDataSetChanged();
                        adtLT.notifyDataSetChanged();
                        adtTH.notifyDataSetChanged();
                        if (siso == slDDK) {
                            Toast.makeText(DKHP.this, "Lớp đã đầy", Toast.LENGTH_SHORT).show();
                        }
                        else {
                            btnDKHP.setEnabled(false);
                            //Toast.makeText(DKHP.this, "So lượng lhp Thực Hành = "+slTH, Toast.LENGTH_SHORT).show();
                           // Toast.makeText(DKHP.this, "So lượng lhp ly thuyet = "+slLT, Toast.LENGTH_SHORT).show();
                            getDSLopHocPhanLT();
                            getDSLopHocPhanTH();
                            getMonTienQuyet();
                            getKiemTraMonTienQuyet();
                            //Toast.makeText(DKHP.this, "Mtq= "+ mtq + "KTmtq= "+ ktmtq, Toast.LENGTH_SHORT).show();
                        }
                    }
                });

            }
        });
        rcvLHP.setAdapter(adtLHP);
        rcvLHP.setLayoutManager(new GridLayoutManager(DKHP.this, 1));
        rcvLHP.setHasFixedSize(true);
        adtLHP.notifyDataSetChanged();


//--------------------DS Lớp học phần ly thuyet ------------------------------------------//
        rcvLHPLT = findViewById(R.id.rcv_LopHocPhanLT);
        lopHocPhanLTList = new ArrayList<>();
        adtLT = new LopHocPhanLTAdapter(getApplicationContext(), (ArrayList<LopHocPhanLT>) lopHocPhanLTList, new IClickItemNhomLT() {
            @Override
            public void onClickItemNhomLTvsTH(LopHocPhanLT lopHocPhanLT) {
                nhomlt = lopHocPhanLT.getMaNhom();
                slnhomLT = lopHocPhanLT.getMaNhom().length();
                getKiemTraTrungLichHocLT();
                btnDKHP.setEnabled(true);
                rcvLHPLT.post(new Runnable() {
                    @Override
                    public void run() {
                        adtLT.notifyDataSetChanged();

                        //Toast.makeText(DKHP.this, "Nhom Lt = "+slnhomLT, Toast.LENGTH_SHORT).show();
                        //Toast.makeText(DKHP.this, "LichHocTrung = "+kiemTraLT, Toast.LENGTH_SHORT).show();
                    }

                });


            }
        });

        rcvLHPLT.setAdapter(adtLT);
        rcvLHPLT.setLayoutManager(new GridLayoutManager(DKHP.this, 1));
        rcvLHPLT.setHasFixedSize(true);
//--------------------DS Lớp học phần thực hành------------------------------------------//
        rcvLHPTH = findViewById(R.id.rcv_LopHocPhanTH);
        lopHocPhanTHList = new ArrayList<>();

        adtTH = new LopHocPhanTHAdapter(getApplicationContext(), (ArrayList<LopHocPhanTH>) lopHocPhanTHList, new IClickItemLHPTH() {
            @Override
            public void onClickItemTH(LopHocPhanTH lopHocPhanTH) {
                nhomth = lopHocPhanTH.getMaNhom();
                slnhomTH = lopHocPhanTH.getMaNhom().length();
                getKiemTraTrungLichHocTH();
                btnDKHP.setEnabled(true);
                rcvLHPTH.post(new Runnable() {
                    @Override
                    public void run() {
                        adtTH.notifyDataSetChanged();
                        //Toast.makeText(DKHP.this, "Nhom TH = "+slnhomTH, Toast.LENGTH_SHORT).show();
                        //Toast.makeText(DKHP.this, "LichHocTrung = "+kiemTraTH, Toast.LENGTH_SHORT).show();
                    }
                });

                //------------------------------Kiểm tra môn tiên quyết----------------------------------//
            }
        });
        rcvLHPTH.setAdapter(adtTH);
        rcvLHPTH.setLayoutManager(new GridLayoutManager(DKHP.this, 1));
        rcvLHPTH.setHasFixedSize(true);

//----------------------------------Lớp học phần đã đăng ký------------- Delete------------------------------//
        rcvLHPDDK = findViewById(R.id.rcv_LHPDDK);
        lhpDaDangKyList = new ArrayList<>();
        adtLHPDDK = new LHPDaDangKyAdapter(getApplicationContext(), (ArrayList<LHPDaDangKy>) lhpDaDangKyList, new IClickItemLHPDDK() {
            @Override
            public void onCliCkItemLHPDDK(LHPDaDangKy lhpDaDangKy) {
                adtLHPDDK.notifyDataSetChanged();
                lhpddk = lhpDaDangKy.getMaLopHP();
                getLopHocPhan();

            //----------------Dialog thông báo--------------------------------------//
                AlertDialog.Builder builder = new AlertDialog.Builder(DKHP.this);
                builder.setTitle("Thông báo");
                builder.setMessage("Xác nhận hủy môn học phần!!!");
                builder.setPositiveButton("Có", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        progressBarDelete.setVisibility(View.VISIBLE);

                        getDSLopHocPhanDDK();
                        getDSLopHocPhan();
                        getDSMonHocPhan();
                        adtLHPDDK.notifyDataSetChanged();
                        rcvLHPDDK.post(new Runnable() {
                            @Override
                            public void run() {
                                adtLHPDDK.notifyDataSetChanged();
                                adtLHP.notifyDataSetChanged();
                                adtMHP.notifyDataSetChanged();
                                deleteLHPDDK();
                                updateLHPDDK();

                            }
                        });
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
        rcvLHPDDK.setAdapter(adtLHPDDK);
        rcvLHPDDK.setLayoutManager(new GridLayoutManager(DKHP.this, 1));
        rcvLHPDDK.setHasFixedSize(true);
        adtLHPDDK.notifyDataSetChanged();
    }


    public void getDSMonHocPhan() {
        ApiService.apiService.getDSMonHocPhan(mssv, Integer.parseInt(hocky_spinner.getSelectedItem().toString()), nam_spinner.getSelectedItem().toString()).enqueue(new Callback<List<MonHocPhan>>() {
            @Override
            public void onResponse(Call<List<MonHocPhan>> call, Response<List<MonHocPhan>> response) {
                Toast.makeText(DKHP.this, "Mon hoc phan "+ response.body().size(), Toast.LENGTH_SHORT).show();
                monHocPhanList = response.body();
                if (response.isSuccessful()){
                    if (monHocPhanList != null) {
                        adtMHP.setMonHocPhans((ArrayList<MonHocPhan>) monHocPhanList);
                        progressBarMHP.setVisibility(View.GONE);
                    }
                }
            }
            @Override
            public void onFailure(Call<List<MonHocPhan>> call, Throwable t) {
                Toast.makeText(DKHP.this, "Call api error MHP", Toast.LENGTH_SHORT).show();
            }
        });
    }

//---------------Lấy một lớp học phần-----------------------------------------------//
    public void getLopHocPhan() {
        ApiService.apiService.getLopHocPhan(lhpddk).enqueue(new Callback<List<LopHocPhan>>() {
            @Override
            public void onResponse(Call<List<LopHocPhan>> call, Response<List<LopHocPhan>> response) {
                //Toast.makeText(DKHP.this, "LopHocPhan "+ response.body().size(), Toast.LENGTH_SHORT).show();
                lopHocPhanList = response.body();
                if (lopHocPhanList != null) {
                    for (int i = 0; i< response.body().size(); i++){
                        ddk = lopHocPhanList.get(i).getDaDangKy();
                        malhp =  lopHocPhanList.get(i).getMaLopHP();
                        Toast.makeText(DKHP.this, "DDk = "+ddk, Toast.LENGTH_SHORT).show();
                    }
                }
            }
            @Override
            public void onFailure(Call<List<LopHocPhan>> call, Throwable t) {
                Toast.makeText(DKHP.this, "Call api error LHP", Toast.LENGTH_SHORT).show();
            }
        });
    }
//---------------Lấy danh sách lớp học phần-----------------------------------------------//
    public void getDSLopHocPhan() {
        ApiService.apiService.getDSLopHocPhan(mhp).enqueue(new Callback<List<LopHocPhan>>() {
            @Override
            public void onResponse(Call<List<LopHocPhan>> call, Response<List<LopHocPhan>> response) {
                //Toast.makeText(DKHP.this, "LopHocPhan "+ response.body().size(), Toast.LENGTH_SHORT).show();
                lopHocPhanList = response.body();
                if (response.isSuccessful()){
                    if (lopHocPhanList != null) {
                        adtLHP.setLopHocPhans((ArrayList<LopHocPhan>) lopHocPhanList);
                    }
                    progressBarLHP.setVisibility(View.GONE);
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
        ApiService.apiService.getKiemTraMonTienQuyet(mssv,lhp).enqueue(new Callback<List<MonTienQuyet>>() {

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
    public void getKiemTraTrungLichHocLT() {

        ApiService.apiService.getKiemTraTrungLichHoc(mssv,Integer.parseInt(hocky_spinner.getSelectedItem().toString()), String.valueOf(nam_spinner.getSelectedItem()),lhp, nhomlt ).enqueue(new Callback<List<KiemTraLichHoc>>() {

            @Override
            public void onResponse(Call<List<KiemTraLichHoc>> call, Response<List<KiemTraLichHoc>> response) {
                //Toast.makeText(DKHP.this, "Kiểm tra lich hoc ", Toast.LENGTH_SHORT).show();
                    kiemTraLT = response.body().size();

            }
            @Override
            public void onFailure(Call<List<KiemTraLichHoc>> call, Throwable t) {
                Toast.makeText(DKHP.this, "Call api error LHP", Toast.LENGTH_SHORT).show();
            }
        });
    }
    public void getKiemTraTrungLichHocTH() {

        ApiService.apiService.getKiemTraTrungLichHoc(mssv,Integer.parseInt(hocky_spinner.getSelectedItem().toString()), String.valueOf(nam_spinner.getSelectedItem()),lhp, nhomth ).enqueue(new Callback<List<KiemTraLichHoc>>() {
            @Override
            public void onResponse(Call<List<KiemTraLichHoc>> call, Response<List<KiemTraLichHoc>> response) {
                //Toast.makeText(DKHP.this, "Kiểm tra lich hoc ", Toast.LENGTH_SHORT).show();
                kiemTraTH = response.body().size();

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
                slLT = response.body().size();
                lopHocPhanLTList = response.body();
                if (response.isSuccessful()){
                    adtLT.notifyDataSetChanged();
                    if (lopHocPhanLTList != null) {
                        adtLT.setLopHocPhanLTvsTHs((ArrayList<LopHocPhanLT>) lopHocPhanLTList);
                        progressBarLT.setVisibility(View.GONE);

                    }
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
                slTH = response.body().size();
                lopHocPhanTHList = response.body();
                if (response.isSuccessful()){
                    if (lopHocPhanTHList != null) {
                        adtTH.setLopHocPhanTHs((ArrayList<LopHocPhanTH>) lopHocPhanTHList);
                        progressBarTH.setVisibility(View.GONE);
                    }
                }

            }
            @Override
            public void onFailure(Call<List<LopHocPhanTH>> call, Throwable t) {
                Toast.makeText(DKHP.this, "Call api error LHP", Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void getDSLopHocPhanDDK() {
        ApiService.apiService.getDSLopHocPhanDDK(mssv, Integer.parseInt(hocky_spinner.getSelectedItem().toString()), nam_spinner.getSelectedItem().toString()).enqueue(new Callback<List<LHPDaDangKy>>() {
            @Override
            public void onResponse(Call<List<LHPDaDangKy>> call, Response<List<LHPDaDangKy>> response) {
                //Toast.makeText(DKHP.this, "Mon hoc phan "+ response.body().size(), Toast.LENGTH_SHORT).show();

                lhpDaDangKyList = response.body();
                adtLHPDDK.notifyDataSetChanged();
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

    public void postDKHPLT() {
        ApiService.apiService.dkhpLT(mssv, lhp, nhomlt).enqueue(new Callback<DKHPSV>() {

            @Override
            public void onResponse(Call<DKHPSV> call, Response<DKHPSV> response) {
                Toast.makeText(DKHP.this, "DKHP LT thành công!!!", Toast.LENGTH_SHORT).show();
                getDSMonHocPhan();
                getDSLopHocPhanDDK();
            }
            @Override
            public void onFailure(Call<DKHPSV> call, Throwable t) {
                Toast.makeText(DKHP.this, "Call api error DKHP", Toast.LENGTH_SHORT).show();
            }
        });
    }
    public void postDKHPTH() {
        ApiService.apiService.dkhpTH(mssv, lhp, nhomth).enqueue(new Callback<DKHPSV>() {

            @Override
            public void onResponse(Call<DKHPSV> call, Response<DKHPSV> response) {
                Toast.makeText(DKHP.this, "DKHP TH thành công!!!", Toast.LENGTH_SHORT).show();
                getDSMonHocPhan();
                getDSLopHocPhanDDK();
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
        ApiService.apiService.updateSLSVDKHP( ddk-1, lhpddk).enqueue(new Callback<SLDaDangKy>() {

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
    public void deleteLHPDDK() {
        ApiService.apiService.deleteLHPDDK(mssv, lhpddk).enqueue(new Callback<DeleteLHPDDK>() {
            @Override
            public void onResponse(Call<DeleteLHPDDK> call, Response<DeleteLHPDDK> response) {
                Toast.makeText(DKHP.this, "hủy lớp thành công!!!"+ lhpddk, Toast.LENGTH_SHORT).show();
                    getDSMonHocPhan();
                    getDSLopHocPhanDDK();
                    progressBarDelete.setVisibility(View.GONE);
            }
            @Override
            public void onFailure(Call<DeleteLHPDDK> call, Throwable t) {
                Toast.makeText(DKHP.this, "Call api error DKHP", Toast.LENGTH_SHORT).show();
            }
        });
    }
    public void getNamHoc() {
        ApiService.apiService.getNamHocs().enqueue(new Callback<List<NamHoc>>() {
            @Override
            public void onResponse(Call<List<NamHoc>> call, Response<List<NamHoc>> response) {
                namHocList = response.body();
                Toast.makeText(DKHP.this, "Size Nam hoc"+ response.body().size(), Toast.LENGTH_SHORT).show();

                String khoahocTach = InfoStudent.khoahocchung.substring(0,4);
                Log.d("Khoa hoc Tach", khoahocTach);
                int vitri = 0;
                for (int i = 0; i < namHocList.size(); i++) {
                    String namhocs = namHocList.get(i).getNam();
                    //Log.d("Nam hoc", namhocs);
                    String tachnam  = namhocs.substring(0, 4);
                    Log.d("Nam sau khi cat", tachnam);
                    if(khoahocTach.equals(tachnam)){
                        vitri = i;
                        Log.d("vitri",vitri+"");
                    }
                }
                for (int j = vitri; j<vitri+4; j++ ){

                    listNam.add(namHocList.get(j).getNam()+"");
                    Log.d("dasdasdasd : ", listNam+"");
                    //namdung = namHocList.get(j).getNam().toCharArray();

                }
                adapternam.notifyDataSetChanged();
            }
            @Override
            public void onFailure(Call<List<NamHoc>> call, Throwable t) {
                Toast.makeText(DKHP.this, "Call api error LHP", Toast.LENGTH_SHORT).show();
            }
        });
    }

}