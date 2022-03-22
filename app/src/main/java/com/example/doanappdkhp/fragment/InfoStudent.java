package com.example.doanappdkhp.fragment;

import android.os.AsyncTask;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

import com.example.doanappdkhp.R;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link InfoStudent#newInstance} factory method to
 * create an instance of this fragment.
 */
public class InfoStudent extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private TextView txtName, txtID, txtGioiTinh, txtNgaySinh, txtDiaChi, txtKhoaHoc, txtSoDT;

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
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_info_student, container, false);
        txtID = (TextView) view.findViewById(R.id.txtIdInfo);
        // Inflate the layout for this fragment
        new Async().execute();
        return view;
    }

    class Async extends AsyncTask<Void, Void, Void> {
        String records = "",error="";
        @Override
        protected Void doInBackground(Void... voids) {
            try
            {
                Class.forName("com.mysql.jdbc.Driver");
                Connection con = DriverManager
                        .getConnection("jdbc:mysql://192.168.0.4:3306/sqlquanlyhocphan","andro", "andro");
                Statement statement = con.createStatement();
                ResultSet resultSet = statement.executeQuery("SELECT * FROM sinhvien WHERE MSSV = 0001 ");
                while(resultSet.next()) {
                    records += resultSet.getString(1);
                }
            }
            catch(Exception e)
            {
                error = e.toString();
            }
            return null;
        }
        @Override
        protected void onPostExecute(Void aVoid) {
            txtID.setText(records);
//            if(error != "")
//                errorText.setText(error);
            super.onPostExecute(aVoid);
        }
    }
}