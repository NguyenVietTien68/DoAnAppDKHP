package com.example.doanappdkhp.gui;


import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.doanappdkhp.LoginActivity;
import com.example.doanappdkhp.R;
import com.example.doanappdkhp.adapter.LHPDaDangKyAdapter;
import com.example.doanappdkhp.adapter.LopHocPhanAdapter;
import com.example.doanappdkhp.adapter.LopHocPhanLTAdapter;
import com.example.doanappdkhp.adapter.LopHocPhanTHAdapter;
import com.example.doanappdkhp.adapter.MonHocPhanAdapter;
import com.example.doanappdkhp.api.ApiService;
import com.example.doanappdkhp.data_local.DataLocalManager;
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
import com.example.doanappdkhp.fragment.MainForm;
import com.example.doanappdkhp.my_interface.ICLickItemLHP;
import com.example.doanappdkhp.my_interface.IClickItemLHPDDK;
import com.example.doanappdkhp.my_interface.IClickItemLHPTH;
import com.example.doanappdkhp.my_interface.IClickItemMHP;
import com.example.doanappdkhp.my_interface.IClickItemNhomLT;

import java.nio.channels.DatagramChannel;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DKHP extends AppCompatActivity {

    ArrayAdapter adapternam;
    String monthfomat;
    int monthCheck;
    String namFomat;
    int namCheck;
    private static final String TAG = "MainActivity";
    String hockyCheck;
    String[] hocky = {"1", "2", "3"};
    ProgressBar progressBarMHP, progressBarLHP, progressBarLT, progressBarTH, progressBarDelete;
     Spinner nam_spinner;
     Spinner hocky_spinner;
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
    public static Button btnDKHP;
    String lhpddkkt;
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

//-------------------l????y tha??ng v????i n??m hi????n ta??i so sa??nh ------------------------------//
        dateToday = LocalDate.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM");
        DateTimeFormatter formatterNam = DateTimeFormatter.ofPattern("yyyy");
        monthfomat = formatter.format(dateToday);
         monthCheck = Integer.parseInt(monthfomat);
        namFomat = formatterNam.format(dateToday);
        namCheck = Integer.parseInt(namFomat);
        if(monthCheck <= 5){
            hockyCheck = "2";
            namCheck = namCheck-1;
        }
        else if (monthCheck >5 && monthCheck < 8){
            hockyCheck = "3";
            namCheck = namCheck-1;
        }
        else if (monthCheck >7 && monthCheck < 13){
            hockyCheck = "1";
            namCheck = namCheck;
        }

//-------------------????ng k?? h???c ph???n cho sinh vi??n-------------------------//
        btnDKHP = findViewById(R.id.btnDKHP);

        btnGetDSMHP = findViewById(R.id.btnLocDSMonHocPhan);
        nam_spinner = findViewById(R.id.spinnerNamMHP);
        hocky_spinner = findViewById(R.id.spinnerHocKyMHP);

        adapternam = new ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item, MainForm.listNam);
        adapternam.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        nam_spinner.setAdapter(adapternam);
        nam_spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String namClick = parent.getItemAtPosition(position).toString();
                DataLocalManager.setNam(namClick);
                Log.d("Namcclickat:---------",DataLocalManager.getNam().substring(0,4)+"");
                clickDKHPP(DataLocalManager.getHocKy(), DataLocalManager.getNam().substring(0,4));

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        ArrayAdapter adapterhocky = new ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item, hocky);
        adapterhocky.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        hocky_spinner.setAdapter(adapterhocky);

        hockykt  = Integer.parseInt(hocky_spinner.getSelectedItem().toString());
        hocky_spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String hockyClick = parent.getItemAtPosition(position).toString();
                DataLocalManager.setHocKy(hockyClick);
                Log.d("HocKy:---------",DataLocalManager.getHocKy()+"");
                clickDKHPP(DataLocalManager.getHocKy(), DataLocalManager.getNam().substring(0,4));

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        //namkt = nam_spinner.getSelectedItem().toString();


//-------------------------Ch???n h???c k?? vs N??m r???i nh???n l???c-----------------------------///
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

//--------------------DS M??n h???c ph???n--------------------------------------------------------------//
        rcvMHP = findViewById(R.id.rcv_MonHocPhan);
        monHocPhanList = new ArrayList<>();
        adtMHP = new MonHocPhanAdapter(getApplicationContext(), (ArrayList<MonHocPhan>) monHocPhanList, new IClickItemMHP() {
            @Override
            public void onClickItemMHP(MonHocPhan monHocPhan) {
                progressBarLHP.setVisibility(View.VISIBLE);
                //oclickChonMHP(monHocPhan);
                mhp = monHocPhan.getMaMHP();
                adtLHP.notifyDataSetChanged();
                adtLT.notifyDataSetChanged();
                adtTH.notifyDataSetChanged();
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
//--------------------DS L???p h???c ph???n-----------------------------------------------------------------//
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
                //Toast.makeText(DKHP.this, "sl lhp ddk = "+slDDK, Toast.LENGTH_SHORT).show();
                siso = lopHocPhan.getSiSo();
                rcvLHP.post(new Runnable() {
                    @Override
                    public void run() {
                        lopHocPhanLTList.clear();
                        lopHocPhanTHList.clear();
                        adtLHP.notifyDataSetChanged();
                        adtLT.notifyDataSetChanged();
                        adtTH.notifyDataSetChanged();

                        if (siso == slDDK) {
                            Toast.makeText(DKHP.this, "L???p ???? ?????y", Toast.LENGTH_SHORT).show();
                            progressBarLT.setVisibility(View.GONE);
                            progressBarTH.setVisibility(View.GONE);
                        }
                        else {
                            btnDKHP.setEnabled(false);
                            //Toast.makeText(DKHP.this, "So l?????ng lhp Th???c H??nh = "+slTH, Toast.LENGTH_SHORT).show();
                           // Toast.makeText(DKHP.this, "So l?????ng lhp ly thuyet = "+slLT, Toast.LENGTH_SHORT).show();
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


//--------------------DS L???p h???c ph???n ly thuyet ------------------------------------------//
        rcvLHPLT = findViewById(R.id.rcv_LopHocPhanLT);
        lopHocPhanLTList = new ArrayList<>();
        adtLT = new LopHocPhanLTAdapter(getApplicationContext(), (ArrayList<LopHocPhanLT>) lopHocPhanLTList, new IClickItemNhomLT() {
            @Override
            public void onClickItemNhomLTvsTH(LopHocPhanLT lopHocPhanLT) {

//                btnDKHP.setEnabled(true);
//                btnDKHP.setBackgroundTintList(ColorStateList.valueOf(Color.rgb(255,124,0)));
                rcvLHPLT.post(new Runnable() {
                    @Override
                    public void run() {
                        adtLT.notifyDataSetChanged();
                        nhomlt = lopHocPhanLT.getMaNhom();
                        slnhomLT = lopHocPhanLT.getMaNhom().length();
                        getKiemTraTrungLichHocLT();
                        //Toast.makeText(DKHP.this, "Nhom Lt = "+slnhomLT, Toast.LENGTH_SHORT).show();
                        //Toast.makeText(DKHP.this, "LichHocTrung = "+kiemTraLT, Toast.LENGTH_SHORT).show();
                    }

                });


            }
        });

        rcvLHPLT.setAdapter(adtLT);
        rcvLHPLT.setLayoutManager(new GridLayoutManager(DKHP.this, 1));
        rcvLHPLT.setHasFixedSize(true);
//--------------------DS L???p h???c ph???n th???c h??nh------------------------------------------//
        rcvLHPTH = findViewById(R.id.rcv_LopHocPhanTH);
        lopHocPhanTHList = new ArrayList<>();

        adtTH = new LopHocPhanTHAdapter(getApplicationContext(), (ArrayList<LopHocPhanTH>) lopHocPhanTHList, new IClickItemLHPTH() {
            @Override
            public void onClickItemTH(LopHocPhanTH lopHocPhanTH) {
//                btnDKHP.setEnabled(true);
//                btnDKHP.setBackgroundTintList(ColorStateList.valueOf(Color.rgb(255,124,0)));
                rcvLHPTH.post(new Runnable() {
                    @Override
                    public void run() {
                        adtTH.notifyDataSetChanged();
                        nhomth = lopHocPhanTH.getMaNhom();
                        slnhomTH = lopHocPhanTH.getMaNhom().length();
                        getKiemTraTrungLichHocTH();
                        //Toast.makeText(DKHP.this, "Nhom TH = "+slnhomTH, Toast.LENGTH_SHORT).show();
                        //Toast.makeText(DKHP.this, "LichHocTrung = "+kiemTraTH, Toast.LENGTH_SHORT).show();
                    }
                });

//------------------------------Ki???m tra m??n ti??n quy???t----------------------------------//
            }
        });
        rcvLHPTH.setAdapter(adtTH);
        rcvLHPTH.setLayoutManager(new GridLayoutManager(DKHP.this, 1));
        rcvLHPTH.setHasFixedSize(true);

//----------------------------------L???p h???c ph???n ???? ????ng k??------------- Delete------------------------------//
        rcvLHPDDK = findViewById(R.id.rcv_LHPDDK);
        lhpDaDangKyList = new ArrayList<>();
        adtLHPDDK = new LHPDaDangKyAdapter(getApplicationContext(), (ArrayList<LHPDaDangKy>) lhpDaDangKyList, new IClickItemLHPDDK() {
            @Override
            public void onCliCkItemLHPDDK(LHPDaDangKy lhpDaDangKy) {
                adtLHPDDK.notifyDataSetChanged();
                lhpddk = lhpDaDangKy.getMaLopHP();
                getLopHocPhan();

            //----------------Dialog th??ng b??o--------------------------------------//
                AlertDialog.Builder builder = new AlertDialog.Builder(DKHP.this);
                builder.setTitle("Th??ng b??o");
                builder.setMessage("X??c nh???n h???y m??n h???c ph???n!!!");
                builder.setPositiveButton("C??", new DialogInterface.OnClickListener() {
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
                                updateLHPDDK();
                                deleteLHPDDK();
                            }
                        });
                    }
                });
                builder.setNegativeButton("Kh??ng", new DialogInterface.OnClickListener() {
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
//----------X???? ly?? ph????n ????ng ky?? ho??c ph????n-------------------------------------------------------------//

    private void clickDKHPP(String hockyClick, String namClick) {
        btnDKHP.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                rcvMHP.post(new Runnable() {
                    @Override
                    public void run() {

                        Log.d("namcheck:", +namCheck+"");
                            if (Integer.parseInt(namClick) != namCheck || hockyClick != hockyCheck){
                                Toast.makeText(DKHP.this, "kh??ng ??u??ng th????i gian ????ng ky??", Toast.LENGTH_SHORT).show();
                            }else {
                                if (mtq > ktmtq) {
                                    Toast.makeText(DKHP.this, "Sinh vi??n ch??a h???c m??n ti??n quy???t", Toast.LENGTH_SHORT).show();
                                } else if (kiemTraLT > 0) {
                                    Toast.makeText(DKHP.this, "Tr??ng l???ch h???c l?? thuy???t", Toast.LENGTH_SHORT).show();
                                } else if (kiemTraTH > 0) {
                                    Toast.makeText(DKHP.this, "Tr??ng l???ch h???c th???c h??nh", Toast.LENGTH_SHORT).show();
                                }
                                //khi l???p h???c ph???n c?? c??? l?? thuy???t vs th???c h??nh
                                else if (slTH > 0 && slLT > 0 && slnhomTH > 0 && slnhomLT > 0) {
                                    //----Ki???m tra xem sinh vi??n ch???n c??? 2 nh??m TH vs LT hay ch??a
                                    if (slTH > 0 && slLT > 0 && slnhomTH > 0 && nhomlt == "") {
                                        Toast.makeText(DKHP.this, "Ch??a ch???n nh??m l?? thuy???t", Toast.LENGTH_SHORT).show();
                                    } else if (slTH > 0 && slLT > 0 && slnhomLT > 0 && nhomth == "") {
                                        Toast.makeText(DKHP.this, "Ch??a ch???n nh??m th???c h??nh", Toast.LENGTH_SHORT).show();
                                    } else {
                                        updateSLSVDKHP();
                                        postDKHPTH();
                                        postDKHPLT();
                                        adtMHP.notifyDataSetChanged();
                                    }
                                    //khi l???p h???c ph???n ch??? c?? l?? thuy???t
                                } else if (slLT > 0 && slTH == 0 && slnhomLT > 0) {
                                    updateSLSVDKHP();
                                    postDKHPLT();
                                    //khi l???p h???c ph???n ch??? c?? th???c h??nh
                                } else if (slTH > 0 && slLT == 0 && slnhomTH > 0) {
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
                    }
                });
                    }
                });

    }
    public void getDSMonHocPhan() {
        ApiService.apiService.getDSMonHocPhan(DataLocalManager.getMSSV(), Integer.parseInt(hocky_spinner.getSelectedItem().toString()), nam_spinner.getSelectedItem().toString()).enqueue(new Callback<List<MonHocPhan>>() {
            @Override
            public void onResponse(Call<List<MonHocPhan>> call, Response<List<MonHocPhan>> response) {
                //Toast.makeText(DKHP.this, "Mon hoc phan "+ response.body().size(), Toast.LENGTH_SHORT).show();
                monHocPhanList = response.body();
                if (response.isSuccessful()){
                    if (monHocPhanList != null) {
                        adtMHP.setMonHocPhans((ArrayList<MonHocPhan>) monHocPhanList);
                        progressBarMHP.setVisibility(View.GONE);
//                        lopHocPhanList.clear();
                        lopHocPhanLTList.clear();
                        lopHocPhanTHList.clear();

                    }
                }
            }
            @Override
            public void onFailure(Call<List<MonHocPhan>> call, Throwable t) {
                Toast.makeText(DKHP.this, "L????i k????t n????i", Toast.LENGTH_SHORT).show();
            }
        });
    }

//---------------L???y m???t l???p h???c ph???n-----------------------------------------------//
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
                        //Toast.makeText(DKHP.this, "DDk = "+ddk, Toast.LENGTH_SHORT).show();
                    }
                }
            }
            @Override
            public void onFailure(Call<List<LopHocPhan>> call, Throwable t) {
                Toast.makeText(DKHP.this, "L????i k????t n????i", Toast.LENGTH_SHORT).show();
            }
        });
    }
//---------------L???y danh s??ch l???p h???c ph???n-----------------------------------------------//
    public void getDSLopHocPhan() {
        ApiService.apiService.getDSLopHocPhan(mhp, nam_spinner.getSelectedItem().toString(), Integer.parseInt(hocky_spinner.getSelectedItem().toString())).enqueue(new Callback<List<LopHocPhan>>() {
            @Override
            public void onResponse(Call<List<LopHocPhan>> call, Response<List<LopHocPhan>> response) {
                //Toast.makeText(DKHP.this, "LopHocPhan "+ response.body().size(), Toast.LENGTH_SHORT).show();
                lopHocPhanList = response.body();
                if (response.isSuccessful()){
                    if (lopHocPhanList != null) {
                        adtLHP.setLopHocPhans((ArrayList<LopHocPhan>) lopHocPhanList);
                        lopHocPhanLTList.clear();
                        lopHocPhanTHList.clear();
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
//---------------L???y m??n ti??n quy???t------------------------------------------------//
    public void getMonTienQuyet() {
        ApiService.apiService.getMonTienQuyet(lhp).enqueue(new Callback<List<MonTienQuyet>>() {
            @Override
            public void onResponse(Call<List<MonTienQuyet>> call, Response<List<MonTienQuyet>> response) {
                //Toast.makeText(DKHP.this, "M??n Ti??n Quy???t "+ response.body().size(), Toast.LENGTH_SHORT).show();
                    mtq = response.body().size();
            }
            @Override
            public void onFailure(Call<List<MonTienQuyet>> call, Throwable t) {
                Toast.makeText(DKHP.this, "L????i k????t n????i", Toast.LENGTH_SHORT).show();
            }
        });
    }
//---------------Ki???m tra m??n ti??n quy???t------------------------------------------------//
    public void getKiemTraMonTienQuyet() {
        ApiService.apiService.getKiemTraMonTienQuyet(DataLocalManager.getMSSV(),lhp).enqueue(new Callback<List<MonTienQuyet>>() {
            @Override
            public void onResponse(Call<List<MonTienQuyet>> call, Response<List<MonTienQuyet>> response) {
                //Toast.makeText(DKHP.this, "Ki???m tra m??n Ti??n Quy???t "+ response.body().size(), Toast.LENGTH_SHORT).show();
                ktmtq = response.body().size();
            }
            @Override
            public void onFailure(Call<List<MonTienQuyet>> call, Throwable t) {
                Toast.makeText(DKHP.this, "Call api error LHP", Toast.LENGTH_SHORT).show();
            }
        });
    }
//-----------------Ki???m Tra l???ch h???c b??? tr??ng---------------------------------------------------//
    public void getKiemTraTrungLichHocLT() {

        ApiService.apiService.getKiemTraTrungLichHoc(DataLocalManager.getMSSV(),Integer.parseInt(hocky_spinner.getSelectedItem().toString()), String.valueOf(nam_spinner.getSelectedItem()),lhp, nhomlt ).enqueue(new Callback<List<KiemTraLichHoc>>() {

            @Override
            public void onResponse(Call<List<KiemTraLichHoc>> call, Response<List<KiemTraLichHoc>> response) {
                //Toast.makeText(DKHP.this, "Ki???m tra lich hoc ", Toast.LENGTH_SHORT).show();
                    kiemTraLT = response.body().size();

            }
            @Override
            public void onFailure(Call<List<KiemTraLichHoc>> call, Throwable t) {
                Toast.makeText(DKHP.this, "L????i k????t n????i", Toast.LENGTH_SHORT).show();
            }
        });
    }
    public void getKiemTraTrungLichHocTH() {

        ApiService.apiService.getKiemTraTrungLichHoc(DataLocalManager.getMSSV(),Integer.parseInt(hocky_spinner.getSelectedItem().toString()), String.valueOf(nam_spinner.getSelectedItem()),lhp, nhomth ).enqueue(new Callback<List<KiemTraLichHoc>>() {
            @Override
            public void onResponse(Call<List<KiemTraLichHoc>> call, Response<List<KiemTraLichHoc>> response) {
                //Toast.makeText(DKHP.this, "Ki???m tra lich hoc ", Toast.LENGTH_SHORT).show();
                kiemTraTH = response.body().size();

            }
            @Override
            public void onFailure(Call<List<KiemTraLichHoc>> call, Throwable t) {
                Toast.makeText(DKHP.this, "L????i k????t n????i", Toast.LENGTH_SHORT).show();
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
                Toast.makeText(DKHP.this, "L????i k????t n????i", Toast.LENGTH_SHORT).show();
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
                Toast.makeText(DKHP.this, "L????i k????t n????i", Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void getDSLopHocPhanDDK() {
        ApiService.apiService.getDSLopHocPhanDDK(DataLocalManager.getMSSV(), Integer.parseInt(hocky_spinner.getSelectedItem().toString()), nam_spinner.getSelectedItem().toString()).enqueue(new Callback<List<LHPDaDangKy>>() {
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
                Toast.makeText(DKHP.this, "L????i k????t n????i", Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void postDKHPLT() {
        ApiService.apiService.dkhpLT(DataLocalManager.getMSSV(), lhp, nhomlt).enqueue(new Callback<DKHPSV>() {

            @Override
            public void onResponse(Call<DKHPSV> call, Response<DKHPSV> response) {
                Toast.makeText(DKHP.this, "DKHP th??nh c??ng!", Toast.LENGTH_SHORT).show();

                //finish();
//                overridePendingTransition(0, 0);
//                startActivity(getIntent());
//                overridePendingTransition(0, 0);
                lopHocPhanTHList.clear();
                lopHocPhanLTList.clear();
                getDSMonHocPhan();
                getDSLopHocPhanDDK();
            }
            @Override
            public void onFailure(Call<DKHPSV> call, Throwable t) {
                Toast.makeText(DKHP.this, "L????i k????t n????i", Toast.LENGTH_SHORT).show();
            }
        });
    }
    public void postDKHPTH() {
        ApiService.apiService.dkhpTH(DataLocalManager.getMSSV(), lhp, nhomth).enqueue(new Callback<DKHPSV>() {

            @Override
            public void onResponse(Call<DKHPSV> call, Response<DKHPSV> response) {
                //Toast.makeText(DKHP.this, "DKHP TH th??nh c??ng!", Toast.LENGTH_SHORT).show();

                //finish();
//                overridePendingTransition(0, 0);
//                startActivity(getIntent());
//                overridePendingTransition(0, 0);
                lopHocPhanTHList.clear();
                lopHocPhanLTList.clear();
                getDSMonHocPhan();
                getDSLopHocPhanDDK();
            }
            @Override
            public void onFailure(Call<DKHPSV> call, Throwable t) {
                Toast.makeText(DKHP.this, "L????i k????t n????i", Toast.LENGTH_SHORT).show();
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
                Toast.makeText(DKHP.this, "L????i k????t n????i", Toast.LENGTH_SHORT).show();
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
                Toast.makeText(DKHP.this, "L????i k????t n????i", Toast.LENGTH_SHORT).show();
            }
        });
    }
    public void deleteLHPDDK() {
        ApiService.apiService.deleteLHPDDK(DataLocalManager.getMSSV(), lhpddk).enqueue(new Callback<DeleteLHPDDK>() {
            @Override
            public void onResponse(Call<DeleteLHPDDK> call, Response<DeleteLHPDDK> response) {
                Toast.makeText(DKHP.this, "H???y l???p th??nh c??ng!", Toast.LENGTH_SHORT).show();
                    getDSMonHocPhan();
                    getDSLopHocPhanDDK();
                    progressBarDelete.setVisibility(View.GONE);
            }
            @Override
            public void onFailure(Call<DeleteLHPDDK> call, Throwable t) {
                Toast.makeText(DKHP.this, "L????i k????t n????i", Toast.LENGTH_SHORT).show();
            }
        });
    }


}