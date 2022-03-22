package com.example.doanappdkhp.gui;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.doanappdkhp.R;

public class xem_lich extends AppCompatActivity {
    String[] nam = {"2021-2022", "2022-2023", "2023-2024", "2024-2025"};
    String[] hocky = {"1","2","3"};
    Spinner nam_spinner, hocky_spinner;
    TextView txtTest;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_xem_lich);

        nam_spinner = findViewById(R.id.spinnerNam);
        hocky_spinner = findViewById(R.id.spinnerHocKy);
        txtTest = findViewById(R.id.txtText);

        ArrayAdapter adapternam = new ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item, nam);
        adapternam.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        nam_spinner.setAdapter(adapternam);

        ArrayAdapter adapterhocky = new ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item, hocky);
        adapterhocky.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);


        nam_spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                hocky_spinner.setAdapter(adapterhocky);
                txtTest.setText(nam_spinner.getSelectedItem().toString());
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
    }
}