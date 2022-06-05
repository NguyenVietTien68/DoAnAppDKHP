package com.example.doanappdkhp;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.doanappdkhp.api.ApiService;
import com.example.doanappdkhp.data_local.DataLocalManager;
import com.example.doanappdkhp.entity.TaiKhoanSV;
import com.example.doanappdkhp.my_interface.IClickMSSV;

import java.util.ArrayList;
import java.util.List;

import at.favre.lib.crypto.bcrypt.BCrypt;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity {
    public static CheckBox btnSaveLogin;
    Button btnDangNhap;
    EditText edtMSSV, edtPass;
    private Context mcontext;
    List<TaiKhoanSV> mListTaiKhoanSv;
    private TaiKhoanSV mtk;
    public static String mssv, pass;
    //TaiKhoanSV tk;
    private IClickMSSV iClickMSSV;
    ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        progressBar = findViewById(R.id.progressBarLogin);
        progressBar.setVisibility(View.GONE);
        btnSaveLogin = findViewById(R.id.btnSaveLogin);
        edtMSSV = findViewById(R.id.edtMSSV);
        edtPass = findViewById(R.id.edtPASS);
        mListTaiKhoanSv = new ArrayList<>();
        //getInfoSinhVien();
        btnDangNhap = findViewById(R.id.btn_DangNhap);
        getListTaiKhoanSV();

        //---------Lưu tài khoản sau khi login---------------------------
        SharedPreferences preferences = getSharedPreferences("checkbox", MODE_PRIVATE);
        String checkbox = preferences.getString("api","");
        if(checkbox.equals("true")){
            startActivity(new Intent(LoginActivity.this, TrangChinh.class));

        }else {
            //Toast.makeText(MainActivity.this, "Plase sign in", Toast.LENGTH_SHORT).show();
        }
        btnSaveLogin.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked){
                    SharedPreferences preferences = getSharedPreferences("checkbox", MODE_PRIVATE);
                    SharedPreferences.Editor editor = preferences.edit();
                    editor.putString("api", "true");
                    editor.apply();
                }else{
                    SharedPreferences preferences = getSharedPreferences("checkbox", MODE_PRIVATE);
                    SharedPreferences.Editor editor = preferences.edit();
                    editor.putString("api", "");
                    editor.apply();
                }
            }
        });

        btnDangNhap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                progressBar.setVisibility(View.VISIBLE);
                clickLogin();
            }
        });
    }

    private void clickLogin() {

         mssv = edtMSSV.getText().toString();
         pass = edtPass.getText().toString();
        DataLocalManager.setPass(pass);
        DataLocalManager.setMSSV(mssv);

        if(mListTaiKhoanSv == null || mListTaiKhoanSv.isEmpty()){
           // Toast.makeText(MainActivity.this, "error", Toast.LENGTH_SHORT).show();
            return;
        }
        boolean isHasUser = false;
        for(TaiKhoanSV taiKhoanSV: mListTaiKhoanSv){
            //------Check hash pass----------------------//
            at.favre.lib.crypto.bcrypt.BCrypt.Result checkpass = BCrypt.verifyer().verify(pass.toCharArray(), taiKhoanSV.getPass());
            //-----So sánh mtk xem giống vs edtmtk, hash pass ok-------
            if (mssv.equals(taiKhoanSV.getMaTaiKhoan()) && checkpass.verified){
                isHasUser = true;
                mtk = taiKhoanSV;
                break;
            }
        }
        if(isHasUser){
            getListTaiKhoanSV();
           startActivity(new Intent(LoginActivity.this, TrangChinh.class));
            //Toast.makeText(LoginActivity.this, "Đặng nhập thành công!", Toast.LENGTH_SHORT).show();
            progressBar.setVisibility(View.GONE);
        }else{
            Toast.makeText(LoginActivity.this, "Mssv hoặc pass sai", Toast.LENGTH_SHORT).show();
            progressBar.setVisibility(View.INVISIBLE);
        }

    }

    private void getListTaiKhoanSV() {
        ApiService.apiService.getTaiKhoanSv().enqueue(new Callback<List<TaiKhoanSV>>() {
            @Override
            public void onResponse(Call<List<TaiKhoanSV>> call, Response<List<TaiKhoanSV>> response) {
                //clickLogin();
                mListTaiKhoanSv = response.body();
            }
            @Override
            public void onFailure(Call<List<TaiKhoanSV>> call, Throwable t) {

                Toast.makeText(LoginActivity.this, "Lỗi kết nối "+ t.getMessage() , Toast.LENGTH_SHORT).show();
            }
        });
    }


}