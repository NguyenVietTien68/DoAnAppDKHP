package com.example.doanappdkhp.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
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
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link InfoStudent#newInstance} factory method to
 * create an instance of this fragment.
 */
public class InfoStudent extends Fragment {
    List<TaiKhoanSV> mListTaiKhoanSv;

    //-----get info sinhvien------------------------//
    private static final String TAG = "MainActivity";
    private List<Sinhvien> mlistSinhVien;
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private TextView txtName, txtID, txtGioiTinh, txtNgaySinh, txtDiaChi, txtKhoaHoc, txtSoDT;
    ImageView imgSV;

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public InfoStudent() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment Main_Form.
     */
    // TODO: Rename and change types and number of parameters
    public static InfoStudent newInstance(String param1, String param2) {
        InfoStudent fragment = new InfoStudent();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState){
        View view = inflater.inflate(R.layout.fragment_info_student, container, false);


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
        mListTaiKhoanSv = new ArrayList<>();
        getInfoSinhVien();
        return view;

    }

    private void getInfoSinhVien() {
        ApiService.apiService.getSinhVienTheoMSSV("0001").enqueue(new Callback<List<Sinhvien>>() {
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
                        DateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
                        txtID.setText( sinhvien.getMSSV());
                        txtName.setText(sinhvien.getHoTen());
                        txtSoDT.setText(sinhvien.getSoDT());
                        txtGioiTinh.setText(sinhvien.getGioiTinh());
                        String datesv = formatter.format(sinhvien.getNgaySinh().getDate());
                        txtNgaySinh.setText(datesv);
                        txtDiaChi.setText(sinhvien.getDiaChi());
                        txtKhoaHoc.setText(sinhvien.getKhoaHoc());
                        Glide.with(InfoStudent.this).load(sinhvien.getImageSV()).into(imgSV);
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