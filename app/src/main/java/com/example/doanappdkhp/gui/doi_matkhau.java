package com.example.doanappdkhp.gui;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.doanappdkhp.LoginActivity;
import com.example.doanappdkhp.R;
import com.example.doanappdkhp.api.ApiService;
import com.example.doanappdkhp.data_local.DataLocalManager;
import com.example.doanappdkhp.entity.MatKhau;

import at.favre.lib.crypto.bcrypt.BCrypt;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class doi_matkhau extends AppCompatActivity {
    EditText edtPassCu, edtPassNew, edtPassNew2;
    Button btnDoiMK;
    String passCu;
    String passnew, passnew2;
    String passHash;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doi_matkhau);
        edtPassCu = findViewById(R.id.edtPassCu);
        edtPassNew = findViewById(R.id.edtPassNew);
        edtPassNew2 = findViewById(R.id.edtPassNew2);
        btnDoiMK = findViewById(R.id.btnDoiMK);

        btnDoiMK.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clickDoiMK();
            }
        });
    }

    private void clickDoiMK() {
        passCu = edtPassCu.getText().toString().trim();
        passnew = edtPassNew.getText().toString().trim();
        passnew2 = edtPassNew2.getText().toString().trim();
        if (passCu.equals("")){
            Toast.makeText(doi_matkhau.this, "ô nhập không được để trống ", Toast.LENGTH_SHORT).show();
        }
        else if( !DataLocalManager.getPass().equals(passCu)){
            Log.d("pasCu = ", passCu);
            Toast.makeText(doi_matkhau.this, "Mật khẩu cũ sai", Toast.LENGTH_SHORT).show();
        }
        else if(!passnew.equals(passnew2)){
            Toast.makeText(doi_matkhau.this, "Mật khẩu mới không trùng khớp", Toast.LENGTH_SHORT).show();
        }
        else if (passnew.equals("") || passnew2.equals("")){
            Toast.makeText(doi_matkhau.this, "ô nhập không được để trống ", Toast.LENGTH_SHORT).show();
        }
        else{
            passHash = BCrypt.withDefaults().hashToString(12, passnew.toCharArray());
            updateMatKhau();

        }
    }

    public void updateMatKhau() {
        ApiService.apiService.updatMatKhau(DataLocalManager.getMSSV(), passHash).enqueue(new Callback<MatKhau>() {

            @Override
            public void onResponse(Call<MatKhau> call, Response<MatKhau> response) {
                Toast.makeText(doi_matkhau.this, "Doi mat khau thành công!!!", Toast.LENGTH_SHORT).show();
                DataLocalManager.setPass(passnew);
                startActivity(new Intent(doi_matkhau.this, LoginActivity.class));
                finish();
            }
            @Override
            public void onFailure(Call<MatKhau> call, Throwable t) {
                Toast.makeText(doi_matkhau.this, "Call api error DKHP", Toast.LENGTH_SHORT).show();
            }
        });
    }
}