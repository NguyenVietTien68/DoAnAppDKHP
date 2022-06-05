package com.example.doanappdkhp.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.example.doanappdkhp.R;
import com.example.doanappdkhp.entity.CTKhung;
import com.example.doanappdkhp.entity.MonHocPhan;
import com.example.doanappdkhp.gui.DKHP;
import com.example.doanappdkhp.gui.xem_diem;
import com.example.doanappdkhp.my_interface.IClickItemMHP;

import java.util.ArrayList;

public class MonHocPhanAdapter extends RecyclerView.Adapter<MonHocPhanAdapter.ThingViewHolder>{
    private Context mcontext;
    private LayoutInflater layoutInflater;
    private ArrayList<MonHocPhan> monHocPhans;
    private IClickItemMHP iClickItemMHP;
    int selectedPosition = -1;
    boolean check = false;

    public MonHocPhanAdapter(Context mcontext, ArrayList<MonHocPhan> monHocPhans,IClickItemMHP listener ) {
        this.mcontext = mcontext;
        this.monHocPhans = monHocPhans;
        this.iClickItemMHP = listener;
    }

    public void setMonHocPhans(ArrayList<MonHocPhan> monHocPhans) {
        this.monHocPhans = monHocPhans;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public MonHocPhanAdapter.ThingViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View mItemView = layoutInflater.from(parent.getContext()).inflate(R.layout.item_monhocphan, parent, false);
        return new MonHocPhanAdapter.ThingViewHolder(mItemView);
    }

    @Override
    public void onBindViewHolder(@NonNull MonHocPhanAdapter.ThingViewHolder holder, int position) {
        MonHocPhan monHocPhan = monHocPhans.get(position);
        holder.tvTenMHHP.setText(monHocPhan.getTenMHHP());
        holder.tvMaMonHP.setText(monHocPhan.getMaMHP());
        holder.tvSoTinChi.setText(String.valueOf(monHocPhan.getSoTinChi()));
        holder.tvHPYeuCau.setText(monHocPhan.getHocPhanYeuCau());
        holder.tvBatBuoc.setText(monHocPhan.getBatBuoc());
        String a = monHocPhan.getMaMHP();

        holder.rdoButtonMHP.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("NewApi")
            @Override
            public void onClick(View v) {
                if (check) {
                    holder.rdoButtonMHP.setChecked(false);
                    check = false;
                    DKHP.mhp = "";

                    //write code for when button is unchecked
                } else {
                    check = true;
                    holder.rdoButtonMHP.setChecked(true);

                    //write code for when button is checked
                }
            }
        });
        holder.rdoButtonMHP.setChecked(position == selectedPosition);
        holder.rdoButtonMHP.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked ){
                    selectedPosition = holder.getAdapterPosition();
                    iClickItemMHP.onClickItemMHP(monHocPhan);
                }

            }
        });

    }


//
//    public void release(){
//        mcontext = null;
//    }
    @Override
    public int getItemCount() {
        return monHocPhans.size();
    }

    public class ThingViewHolder extends RecyclerView.ViewHolder {
        TextView tvTenMHHP, tvMaMonHP, tvSoTinChi, tvHPYeuCau, tvBatBuoc;
        RadioButton rdoButtonMHP;
         Button btnChon;
        ConstraintLayout constraintLayout;
        public ThingViewHolder(@NonNull View itemView) {
            super(itemView);
            tvTenMHHP = itemView.findViewById(R.id.tv_TenMHHPDKHP);
            tvMaMonHP = itemView.findViewById(R.id.tv_MaMonHPDKHP);
            tvSoTinChi = itemView.findViewById(R.id.tv_STCDKHP);
            tvHPYeuCau = itemView.findViewById(R.id.tv_HocPhanYeuCauDKHP);
            tvBatBuoc = itemView.findViewById(R.id.tv_BatBuocDKHP);
            constraintLayout = itemView.findViewById(R.id.conStranlayoutMHP);
            rdoButtonMHP = itemView.findViewById(R.id.radioButtonMHP);
        }
    }
}
