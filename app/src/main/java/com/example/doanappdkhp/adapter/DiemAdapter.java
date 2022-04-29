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
import com.example.doanappdkhp.entity.CongNo;
import com.example.doanappdkhp.entity.Diem;
import com.example.doanappdkhp.my_interface.IClickMSSV;

import java.util.ArrayList;

public class DiemAdapter extends RecyclerView.Adapter<DiemAdapter.ThingViewHolder>{
    private Context mcontext;
    private LayoutInflater layoutInflater;
    private ArrayList<Diem> diems;

    public DiemAdapter(Context mcontext, ArrayList<Diem> diems) {
        this.mcontext = mcontext;
        this.diems = diems;

    }

    public void setDiems(ArrayList<Diem> diems) {
        this.diems = diems;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ThingViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View mItemView = layoutInflater.from(parent.getContext()).inflate(R.layout.item_diem, parent, false);
        return new DiemAdapter.ThingViewHolder(mItemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ThingViewHolder holder, int position) {
        Diem diem = diems.get(position);
        holder.tvNam.setText(diem.getNam());
        holder.tvHocky.setText(String.valueOf(diem.getHocky()));
        holder.tvSTC.setText(String.valueOf(diem.getSoTinChi()));
        holder.tvtenMHHP.setText(diem.getTenMHHP());
        holder.tvDiemTK.setText(String.valueOf(diem.getDiemTK()));
        holder.tvDiemGk.setText(String.valueOf(diem.getDiemGK()));
        holder.tvDiemTH.setText(String.valueOf(diem.getDiemTH()));
        holder.tvDiemCk.setText(String.valueOf(diem.getDiemCK()));

    }

    @Override
    public int getItemCount() {
        return diems.size();
    }

    public class ThingViewHolder extends RecyclerView.ViewHolder {
        TextView tvNam, tvHocky, tvtenMHHP, tvSTC, tvDiemTK, tvDiemGk, tvDiemTH, tvDiemCk;

        public ThingViewHolder(@NonNull View itemView) {
            super(itemView);
            tvNam = itemView.findViewById(R.id.tv_NamD);
            tvHocky = itemView.findViewById(R.id.tv_HocKyD);
            tvtenMHHP = itemView.findViewById(R.id.tv_TenMHHPD);
            tvSTC = itemView.findViewById(R.id.tv_SoTinChiD);
            tvDiemTK = itemView.findViewById(R.id.tv_DiemTkD);
            tvDiemGk = itemView.findViewById(R.id.tv_DiemGkD);
            tvDiemTH = itemView.findViewById(R.id.tv_DiemTHD);
            tvDiemCk = itemView.findViewById(R.id.tv_DiemCkD);
        }
    }
}
