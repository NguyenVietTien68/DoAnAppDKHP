package com.example.doanappdkhp.fragment;

import static com.example.doanappdkhp.MainActivity.mssv;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

import com.bumptech.glide.Glide;
import com.example.doanappdkhp.R;
import com.example.doanappdkhp.api.ApiService;
import com.example.doanappdkhp.entity.Sinhvien;
import com.example.doanappdkhp.entity.TaiKhoanSV;

import java.io.IOException;
import java.net.URISyntaxException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class InfoStudent extends Fragment {

    //-----get info sinhvien------------------------//
    private static final String TAG = "MainActivity";
    private List<Sinhvien> mlistSinhVien;

    TextView txtName, txtID, txtGioiTinh, txtNgaySinh, txtDiaChi, txtKhoaHoc, txtSoDT;
    public static String khoahocchung;
    ImageView imgSV;
    ProgressBar progressBar;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState){
        View view = inflater.inflate(R.layout.fragment_info_student, container, false);


        progressBar = view.findViewById(R.id.progressBarInfoSV);
        progressBar.setVisibility(View.VISIBLE);
        txtID = (TextView) view.findViewById(R.id.txtIdInfo);
        txtName = view.findViewById(R.id.txtNameInfo);
        txtDiaChi = view.findViewById(R.id.txtDiaChi);
        txtGioiTinh = view.findViewById(R.id.txtGioiTinh);
        txtNgaySinh = view.findViewById(R.id.txtNgaySinh);
        txtSoDT = view.findViewById(R.id.txtSoDT);
        txtKhoaHoc = view.findViewById(R.id.txtKhoaHoc);
        imgSV = view.findViewById(R.id.imgSinhVien);
        // Inflate the layout for this fragment
        //new Async().execute();
        mlistSinhVien = new ArrayList<>();
        getInfoSinhVien();
        return view;

    }

    private void getInfoSinhVien() {
        ApiService.apiService.getSinhVienTheoMSSV(mssv).enqueue(new Callback<List<Sinhvien>>() {
            @Override
            public void onResponse(Call<List<Sinhvien>> call, Response<List<Sinhvien>> response) {
                //Toast.makeText(getContext(), "Call api success!!!", Toast.LENGTH_SHORT).show();
                mlistSinhVien = response.body();
                //Toast.makeText(getContext(), ""+mlistSinhVien.size(), Toast.LENGTH_SHORT).show();
                if (mlistSinhVien != null ){

                    try {

                    for(Sinhvien sinhvien : mlistSinhVien){
//                        Log.e(TAG, "Revenue:\nMSSV: "+sinhvien.getMSSV()
//                                + "\nHoTen: "+ sinhvien.getHoTen()
//                                + "\nDiaChi: "+ sinhvien.getDiaChi()
//                                + "\nNgaySinh: "+ sinhvien.getNgaySinh());
                        Locale locale = new Locale("vietnam", "HN");
                        DateFormat dateFormat = DateFormat.getDateInstance(DateFormat.DEFAULT, locale);
                         //DateTimeFormatter formatter1 =DateTimeFormatter.ofPattern("dd/MM/yyyy", Locale.ROOT);
                        DateFormat formatter = new SimpleDateFormat("dd/MM/yyyy hh:mm:ss");
                        //Log.d("data", formatter.format(sinhvien.getNgaySinh()+""));
                        txtID.setText( sinhvien.getMSSV());
                        txtName.setText(sinhvien.getHoTen());
                        txtSoDT.setText(sinhvien.getSoDT());
                        txtGioiTinh.setText(sinhvien.getGioiTinh());
                        Date d = new Date();
                        int datesv =sinhvien.getNgaySinh().getDate();
                        Log.d("data",""+ formatter.format(sinhvien.getNgaySinh()));
                        //@SuppressLint({"NewApi", "LocalSuppress"})
                        //String datesv = Instant.EPOCH.atZone(ZONE).format(sinhvien.getNgaySinh());
                        //@SuppressLint({"NewApi", "Loc alSuppress"})
                        //String datesv = SimpleDateFormat.getDateTimeInstance().format(sinhvien.getNgaySinh());
                        txtNgaySinh.setText(datesv+"");
                        txtDiaChi.setText(sinhvien.getDiaChi());
                        txtKhoaHoc.setText(sinhvien.getKhoaHoc());
                        khoahocchung = sinhvien.getKhoaHoc();
                        Log.d("Khoa hocccccccc", khoahocchung);
                        Glide.with(InfoStudent.this).load(sinhvien.getImageSV()).into(imgSV);
                        progressBar.setVisibility(View.GONE);
                    }

                    }catch (Exception e){
                        e.printStackTrace();
                    }
                }
            }
            @Override
            public void onFailure(Call<List<Sinhvien>> call, Throwable t) {
                Toast.makeText(getContext(), "Call api error", Toast.LENGTH_SHORT).show();
            }
        });
    }

//    class Async extends AsyncTask<Void, Void, Void> {
//        String records = "",error="";
//        @Override
//        protected Void doInBackground(Void... voids) {
//            try
//            {
//                Class.forName("com.mysql.jdbc.Driver");
//                Connection con = DriverManager
//                        .getConnection("jdbc:mysql://192.168.0.4:3306/sqlquanlyhocphan","andro", "andro");
//                Statement statement = con.createStatement();
//                ResultSet resultSet = statement.executeQuery("SELECT * FROM sinhvien WHERE MSSV = 0001 ");
//                while(resultSet.next()) {
//                    records += resultSet.getString(1);
//                }
//            }
//            catch(Exception e)
//            {
//                error = e.toString();
//            }
//            return null;
//        }
//        @Override
//        protected void onPostExecute(Void aVoid) {
//            txtID.setText(records);
////            if(error != "")
////                errorText.setText(error);
//            super.onPostExecute(aVoid);
//        }
    //}
}