package com.example.doanappdkhp.fragment;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.example.doanappdkhp.R;
import com.example.doanappdkhp.TrangChinh;
import com.example.doanappdkhp.entity.TaiKhoanSV;
import com.example.doanappdkhp.gui.DKHP;
import com.example.doanappdkhp.gui.doi_matkhau;
import com.example.doanappdkhp.gui.xem_congno;
import com.example.doanappdkhp.gui.xem_ctkhung;
import com.example.doanappdkhp.gui.xem_lich;
import com.example.doanappdkhp.gui.xem_diem;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link MainForm#newInstance} factory method to
 * create an instance of this fragment.
 */
public class MainForm extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private View view;
    Button btnChuyenCongNo, btnChuyenDoiMK, btnChuyenXemKhung, btnChuyenLich, btnChuyenXemDiem, btnChuyenDKHP;


    public MainForm() {
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
    public static MainForm newInstance(String param1, String param2) {
        MainForm fragment = new MainForm();
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
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view =  inflater.inflate(R.layout.fragment_main_form, container, false);

        btnChuyenDKHP = view.findViewById(R.id.btnChuyenDangKy);
        btnChuyenCongNo = view.findViewById(R.id.btnChuyenXemCongNo);
        btnChuyenDoiMK = view.findViewById(R.id.btnChuyenDoiMK);
        btnChuyenXemKhung = view.findViewById(R.id.btnChuyenXemKhung);
        btnChuyenLich = view.findViewById(R.id.btnChuyenXemLich);
        btnChuyenXemDiem = view.findViewById(R.id.btnChuyenXemDiem);

        btnChuyenDKHP.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity().getApplication(), DKHP.class);
                startActivity(intent);
            }
        });
        btnChuyenXemDiem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity().getApplication(), xem_diem.class);
                startActivity(intent);
            }
        });
        btnChuyenCongNo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity().getApplication(), xem_congno.class);
                startActivity(intent);
            }
        });
        btnChuyenDoiMK.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity().getApplication(), doi_matkhau.class);
                startActivity(intent);
            }
        });
        btnChuyenXemKhung.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity().getApplication(), xem_ctkhung.class);
                startActivity(intent);
            }
        });
        btnChuyenLich.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity().getApplication(), xem_lich.class);
                startActivity(intent);
            }
        });
        return view;
    }
}