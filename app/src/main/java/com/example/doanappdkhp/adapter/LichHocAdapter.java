package com.example.doanappdkhp.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.doanappdkhp.R;
import com.example.doanappdkhp.entity.CTKhung;
import com.example.doanappdkhp.entity.LichHoc;

import java.util.ArrayList;

public class LichHocAdapter extends RecyclerView.Adapter<LichHocAdapter.ThingViewHolder>{
    private Context mcontext;
    private LayoutInflater layoutInflater;
    private ArrayList<LichHoc> lichHocs;

    public LichHocAdapter(Context mcontext, ArrayList<LichHoc> lichHocs) {
        this.mcontext = mcontext;
        this.lichHocs = lichHocs;
    }

    public void setLichHocs(ArrayList<LichHoc> lichHocs) {
        this.lichHocs = lichHocs;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public LichHocAdapter.ThingViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View mItemView = layoutInflater.from(parent.getContext()).inflate(R.layout.item_lichhoc, parent, false);
        return new LichHocAdapter.ThingViewHolder(mItemView);
    }

    @Override
    public void onBindViewHolder(@NonNull LichHocAdapter.ThingViewHolder holder, int position) {
        LichHoc lichHoc = lichHocs.get(position);
        holder.tvMaLopHp.setText(lichHoc.getMaLopHP());
        holder.tvTietHoc.setText(lichHoc.getTietHoc());
        holder.tvNgayHoc.setText(lichHoc.getNgayHoc());
        holder.tvPhongHoc.setText(lichHoc.getPhongHoc());
        holder.tvNhom.setText(lichHoc.getNhom());
        holder.tvTenMHHP.setText(lichHoc.getTenMHHP());
        holder.tvHoTen.setText(lichHoc.getHoTen());

    }

    @Override
    public int getItemCount() {
        return lichHocs.size();
    }

    public class ThingViewHolder extends RecyclerView.ViewHolder {
        TextView tvMaLopHp, tvTietHoc, tvNgayHoc, tvPhongHoc, tvNhom, tvTenMHHP, tvHoTen;
        public ThingViewHolder(@NonNull View itemView) {
            super(itemView);
            tvMaLopHp  =itemView.findViewById(R.id.tv_MaLopHPLH);
            tvTietHoc  =itemView.findViewById(R.id.tv_TietHocLH);
            tvNgayHoc  =itemView.findViewById(R.id.tv_NgayHocLH);
            tvPhongHoc  =itemView.findViewById(R.id.tv_PhongHocLH);
            tvNhom  =itemView.findViewById(R.id.tv_NhomLH);
            tvTenMHHP  =itemView.findViewById(R.id.tv_TenMHHPLH);
            tvHoTen  =itemView.findViewById(R.id.tv_HoTenLH);
        }
    }
}
