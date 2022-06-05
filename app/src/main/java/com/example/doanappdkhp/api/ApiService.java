package com.example.doanappdkhp.api;

import com.example.doanappdkhp.entity.CTKhung;
import com.example.doanappdkhp.entity.CongNo;
import com.example.doanappdkhp.entity.DKHPSV;
import com.example.doanappdkhp.entity.DeleteLHPDDK;
import com.example.doanappdkhp.entity.KiemTraLichHoc;
import com.example.doanappdkhp.entity.LHPDaDangKy;
import com.example.doanappdkhp.entity.LopHocPhanTH;
import com.example.doanappdkhp.entity.MatKhau;
import com.example.doanappdkhp.entity.MonHoc;
import com.example.doanappdkhp.entity.MonTienQuyet;
import com.example.doanappdkhp.entity.NamHoc;
import com.example.doanappdkhp.entity.SLDaDangKy;
import com.example.doanappdkhp.entity.Diem;
import com.example.doanappdkhp.entity.LichHoc;
import com.example.doanappdkhp.entity.LopHocPhan;
import com.example.doanappdkhp.entity.LopHocPhanLT;
import com.example.doanappdkhp.entity.MonHocPhan;
import com.example.doanappdkhp.entity.Sinhvien;
import com.example.doanappdkhp.entity.TaiKhoanSV;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.List;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface ApiService {

    Gson gson = new GsonBuilder()
            .setDateFormat("dd-MM-yyyy")
            .create();


    ApiService apiService = new Retrofit.Builder()
            .baseUrl("https://dkhpiuh.herokuapp.com/test/")
//            .baseUrl("172.24.32.1:3000/test/")
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()
            .create(ApiService.class);

    @GET("taikhoansv")
    Call<List<TaiKhoanSV>> getTaiKhoanSv();

    @GET("sinhvien")
    Call<List<Sinhvien>> convertSinhVien();

    @GET("sinhvien/{mssv}")
    Call<List<Sinhvien>> getSinhVienTheoMSSV(@Path("mssv") String mssv);

    @GET("namhoc")
    Call<List<NamHoc>> getNamHocs();

    @GET("laydiem/{mssv}/{nam}/{hocky}")
    Call<List<Diem>> getDiemTheoMSSV(@Path("mssv") String mssv,
                                     @Path("nam") String nam,
                                     @Path("hocky") int hocky);

    @GET("laymonlt/{mssv}/{nam}/{hocky}")
    Call<List<MonHoc>> getMonLyThuyet(@Path("mssv") String mssv,
                                      @Path("nam") String nam,
                                      @Path("hocky") int hocky);

    @GET("laymonth/{mssv}/{nam}/{hocky}")
    Call<List<MonHoc>> getMonThucHanh(@Path("mssv") String mssv,
                                      @Path("nam") String nam,
                                      @Path("hocky") int hocky);

    @GET("layctkhung/{mssv}/{hocky}")
    Call<List<CTKhung>> getCTKhungTheo(@Path("mssv") String mssv,
                                           @Path("hocky") int hocky);

    @GET("laycongno/{mssv}/{nam}/{hocky}")
    Call<List<CongNo>> getCongNoTheoMSSV(@Path("mssv") String mssv,
                                         @Path("nam") String nam,
                                         @Path("hocky") int hocky);

    @GET("laylichhoc/{HocKy}/{Nam}/{MSSV}")
    Call<List<LichHoc>> getLichHoc(@Path("HocKy") int hocky,
                                   @Path("Nam") String nam,
                                   @Path("MSSV") String mssv);

    @GET("laydsmhp/{mssv}/{hocky}/{nam}")
    Call<List<MonHocPhan>> getDSMonHocPhan(@Path("mssv") String mssv,
                                      @Path("hocky") int hocky,
                                      @Path("nam") String nam);

    @GET("laylhp/{malhp}")
    Call<List<LopHocPhan>> getLopHocPhan(@Path("malhp") String mssv);

    @GET("laydslhp/{mamhp}")
    Call<List<LopHocPhan>> getDSLopHocPhan(@Path("mamhp") String mssv);

    @GET("laydslhplt/{malhp}")
    Call<List<LopHocPhanLT>> getDSLopHocPhanLT(@Path("malhp") String mssv);

    @GET("laydslhpth/{malhp}")
    Call<List<LopHocPhanTH>> getDSLopHocPhanTH(@Path("malhp") String mssv);

    @GET("laydslhpddk/{mssv}/{hocky}/{nam}")
    Call<List<LHPDaDangKy>> getDSLopHocPhanDDK(@Path("mssv") String mssv,
                                            @Path("hocky") int hocky,
                                            @Path("nam") String nam);

    @POST("dkhplt")
    Call<DKHPSV> dkhpLT(@Query("MSSV") String mssv,
                            @Query("MaLopHP") String malhp,
                            @Query("Nhom") String nhom);
    @POST("dkhpth")
    Call<DKHPSV> dkhpTH(@Query("MSSV") String mssv,
                      @Query("MaLopHP") String malhp,
                      @Query("Nhom") String nhom);
    @POST("updatesoluongsvdk")
    Call<SLDaDangKy> updateSLSVDKHP(@Query("DaDangKy") int dadangky,
                                    @Query("MaLopHP") String malhp);
    @DELETE("deletelhpddk/{mssv}/{malhp}")
    Call<DeleteLHPDDK> deleteLHPDDK(@Path("mssv") String mssv,
                                      @Path("malhp") String malhp);
//--------Lấy môn tiên quyết-----------------------------------//
    @GET("laymontienquyet/{malhp}")
    Call<List<MonTienQuyet>> getMonTienQuyet(@Path("malhp") String malhp);

    @GET("kiemtramontienquyet/{mssv}/{malhp}")
    Call<List<MonTienQuyet>> getKiemTraMonTienQuyet(@Path("mssv") String mssv,
                                                           @Path("malhp") String malhp);
    @GET("kiemtratrunglichhoc/{mssv}/{hocky}/{nam}/{malhp}/{nhom}")
    Call<List<KiemTraLichHoc>> getKiemTraTrungLichHoc(@Path("mssv") String mssv,
                                                      @Path("hocky") int hocky,
                                                      @Path("nam") String nam,
                                                      @Path("malhp") String malhp,
                                                      @Path("nhom") String nhom);
    @PUT("matkhau")
    Call<MatKhau> updatMatKhau(@Query("MaTaiKhoan") String mtk,
                         @Query("Pass") String pass);
//    @POST("dkhp")
//    Call<List<DKHPSV>> dkhp(@Body DKHPSV dkhpsv);
//    @POST("loginsv")
//    Call<List<LopHocPhanLTvsTH>> postsv(@Query("MSSV") String mssv,
//                                        Query("DiaChi") String diachi);

}
