package com.example.doanappdkhp;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.doanappdkhp.api.ApiService;
import com.example.doanappdkhp.entity.TaiKhoanSV;
import com.example.doanappdkhp.gui.xem_diem;
import com.example.doanappdkhp.my_interface.IClickMSSV;

import java.util.ArrayList;
import java.util.List;

import at.favre.lib.crypto.bcrypt.BCrypt;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {
    Button btnDangNhap;
    EditText edtMSSV, edtPass;
    private Context mcontext;
    List<TaiKhoanSV> mListTaiKhoanSv;
    private TaiKhoanSV mtk;
    //TaiKhoanSV tk;
    private IClickMSSV iClickMSSV;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        edtMSSV = findViewById(R.id.edtMSSV);
        edtPass = findViewById(R.id.edtPASS);
        mListTaiKhoanSv = new ArrayList<>();

        btnDangNhap = findViewById(R.id.btn_DangNhap);

        btnDangNhap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                clickLogin();

            }
        });
    }

    private void clickLogin() {
        getListTaiKhoanSV();
        String mssv = edtMSSV.getText().toString().trim();
        String pass = edtPass.getText().toString().trim();


        if(mListTaiKhoanSv == null || mListTaiKhoanSv.isEmpty()){
            Toast.makeText(MainActivity.this, "error", Toast.LENGTH_SHORT).show();
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
            //startActivity(new Intent(MainActivity.this, TrangChinh.class));
            Intent intent = new Intent(MainActivity.this, TrangChinh.class);
            Bundle bundle = new Bundle();
            TaiKhoanSV tk = new TaiKhoanSV(mssv);
            bundle.putSerializable("mssv",tk);
            intent.putExtras(bundle);
            //iClickMSSV.onClickMSSV(mtk);
            startActivity(intent);



            Toast.makeText(MainActivity.this, "Đặng nhập thành công!", Toast.LENGTH_SHORT).show();
        }else{
            Toast.makeText(MainActivity.this, "Mssv hoặc pass sai", Toast.LENGTH_SHORT).show();
        }
    }

//    private boolean isValid(String clearTextPassword, String hashedPass) {
//        // returns true if password matches hash
//        return BCrypt.checkpw(clearTextPassword, hashedPass);
//    }
    private void getListTaiKhoanSV() {
        ApiService.apiService.getTaiKhoanSv().enqueue(new Callback<List<TaiKhoanSV>>() {
            @Override
            public void onResponse(Call<List<TaiKhoanSV>> call, Response<List<TaiKhoanSV>> response) {

                mListTaiKhoanSv = response.body();
                //Toast.makeText(MainActivity.this, "" + mListTaiKhoanSv.get(1), Toast.LENGTH_SHORT).show();
            }
            @Override
            public void onFailure(Call<List<TaiKhoanSV>> call, Throwable t) {
                Toast.makeText(MainActivity.this, "call api error", Toast.LENGTH_SHORT).show();
            }
        });
    }

}