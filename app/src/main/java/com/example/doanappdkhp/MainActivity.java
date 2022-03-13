package com.example.doanappdkhp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TableLayout;

import com.google.android.material.tabs.TabLayout;

public class MainActivity extends AppCompatActivity {
    Button btnDangNhap;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnDangNhap = findViewById(R.id.btn_DangNhap);

        btnDangNhap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, TrangChinh.class));
            }
        });
    }
}