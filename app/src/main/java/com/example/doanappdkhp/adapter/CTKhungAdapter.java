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

import java.util.ArrayList;

public class CTKhungAdapter extends RecyclerView.Adapter<CTKhungAdapter.ThingViewHolder>{
    private Context mcontext;
    private LayoutInflater layoutInflater;
    private ArrayList<CTKhung> ctKhungs;

    public CTKhungAdapter(Context mcontext, ArrayList<CTKhung> ctKhungs) {
        this.mcontext = mcontext;
        this.ctKhungs = ctKhungs;
    }

    public void setCtKhungs(ArrayList<CTKhung> ctKhungs) {
        this.ctKhungs = ctKhungs;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ThingViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View mItemView = layoutInflater.from(parent.getContext()).inflate(R.layout.item_ctkhung, parent, false);
        return new ThingViewHolder(mItemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ThingViewHolder holder, int position) {
        CTKhung ctKhung = ctKhungs.get(position);
        holder.tvMaMHP.setText(ctKhung.getMaMHP());
        holder.tvTenMHHP.setText(ctKhung.getTenMHHP());
        holder.tvHocKy.setText(String.valueOf(ctKhung.getHocKy()));
    }

    @Override
    public int getItemCount() {
        return ctKhungs.size();
    }

    public class ThingViewHolder extends RecyclerView.ViewHolder {
        TextView tvMaMHP, tvTenMHHP, tvHocKy;

        public ThingViewHolder(@NonNull View itemView) {
            super(itemView);
            tvHocKy  = itemView.findViewById(R.id.tv_HocKyCTK);
            tvMaMHP = itemView.findViewById(R.id.tv_MaMHPCTK);
            tvTenMHHP = itemView.findViewById(R.id.tv_TenMHHPCTK);
        }
    }
}
