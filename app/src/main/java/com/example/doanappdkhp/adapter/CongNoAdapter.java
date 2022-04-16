package com.example.doanappdkhp.adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.doanappdkhp.R;
import com.example.doanappdkhp.entity.CTKhung;
import com.example.doanappdkhp.entity.CongNo;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

public class CongNoAdapter extends RecyclerView.Adapter<CongNoAdapter.ThingViewHolder>{
    private Context mcontext;
    private LayoutInflater layoutInflater;
    private ArrayList<CongNo> congNos;

    public CongNoAdapter(Context mcontext, ArrayList<CongNo> congNos) {
        this.mcontext = mcontext;
        this.congNos = congNos;
    }

    public void setCongNos(ArrayList<CongNo> congNos) {
        this.congNos = congNos;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public CongNoAdapter.ThingViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View mItemView = layoutInflater.from(parent.getContext()).inflate(R.layout.item_congno, parent, false);
        return new CongNoAdapter.ThingViewHolder(mItemView);
    }

    @Override
    public void onBindViewHolder(@NonNull CongNoAdapter.ThingViewHolder holder, int position) {
        CongNo congNo = congNos.get(position);
        holder.tvTenMHHP.setText(congNo.getTenMHHP());
        holder.tvSTC.setText(String.valueOf(congNo.getSoTinChi()));
        holder.tvNam.setText(congNo.getNam());
        holder.tvHocKy.setText(String.valueOf(congNo.getHocKy()));
        DecimalFormat formatter = new DecimalFormat("###,###,###");
        holder.tvTienCN.setText(formatter.format(790000* congNo.getSoTinChi())+ " VNĐ");
    }

    @Override
    public int getItemCount() {
        //return congNos.size();
        if(congNos != null){
            return congNos.size();
        }
        return 0;
    }

    public class ThingViewHolder extends RecyclerView.ViewHolder {
        TextView tvTenMHHP, tvSTC, tvNam, tvHocKy, tvTienCN, tvTong;

        public ThingViewHolder(@NonNull View itemView) {
            super(itemView);

            tvTenMHHP = itemView.findViewById(R.id.tv_tenMHHPCN);
            tvHocKy = itemView.findViewById(R.id.tv_HocKyCN);
            tvNam = itemView.findViewById(R.id.tv_NamCN);
            tvSTC = itemView.findViewById(R.id.tv_SoTCCN);
            tvTienCN = itemView.findViewById(R.id.tv_TienCN);
        }
    }
}
