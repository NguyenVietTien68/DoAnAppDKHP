package com.example.doanappdkhp.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.doanappdkhp.R;
import com.example.doanappdkhp.entity.LHPDaDangKy;
import com.example.doanappdkhp.entity.MonHocPhan;
import com.example.doanappdkhp.my_interface.IClickItemLHPDDK;
import com.example.doanappdkhp.my_interface.IClickItemMHP;

import java.util.ArrayList;

public class LHPDaDangKyAdapter extends RecyclerView.Adapter<LHPDaDangKyAdapter.ThingViewHolder>{
    private Context mcontext;
    private LayoutInflater layoutInflater;
    private ArrayList<LHPDaDangKy> lhpDaDangKys;
    private IClickItemLHPDDK iClickItemLHPDDK;

    public LHPDaDangKyAdapter(Context mcontext, ArrayList<LHPDaDangKy> lhpDaDangKys, IClickItemLHPDDK listener) {
        this.mcontext = mcontext;
        this.lhpDaDangKys = lhpDaDangKys;
        this.iClickItemLHPDDK = listener;
    }

    public void setLhpDaDangKys(ArrayList<LHPDaDangKy> lhpDaDangKys) {
        this.lhpDaDangKys = lhpDaDangKys;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public LHPDaDangKyAdapter.ThingViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View mItemView = layoutInflater.from(parent.getContext()).inflate(R.layout.item_lhpddk, parent, false);
        return new LHPDaDangKyAdapter.ThingViewHolder(mItemView);
    }

    @Override
    public void onBindViewHolder(@NonNull LHPDaDangKyAdapter.ThingViewHolder holder, int position) {
        LHPDaDangKy lhpDaDangKy = lhpDaDangKys.get(position);
        holder.tvMaLHP.setText(lhpDaDangKy.getMaLopHP());
        holder.tvTenMhHP.setText(lhpDaDangKy.getTenMHHP());
        holder.tvNhom.setText(lhpDaDangKy.getNhom());
        holder.tvGiangVien.setText(lhpDaDangKy.getHoTen());
        holder.btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                iClickItemLHPDDK.onCliCkItemLHPDDK(lhpDaDangKy);
            }
        });
    }

    @Override
    public int getItemCount() {
        return lhpDaDangKys.size();
    }

    public class ThingViewHolder extends RecyclerView.ViewHolder {
        TextView tvMaLHP, tvTenMhHP, tvNhom, tvGiangVien;
        Button btnDelete;
        public ThingViewHolder(@NonNull View itemView) {
            super(itemView);
            tvMaLHP = itemView.findViewById(R.id.tv_MaLHPDDK);
            tvTenMhHP = itemView.findViewById(R.id.tv_TenMHHPDDK);
            tvNhom = itemView.findViewById(R.id.tv_NhomDDK);
            tvGiangVien = itemView.findViewById(R.id.tv_GiangVienDDK);
            btnDelete = itemView.findViewById(R.id.btnDeleteLHPDDK);
        }
    }
}
