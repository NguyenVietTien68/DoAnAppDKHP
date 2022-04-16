package com.example.doanappdkhp.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.RadioButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.doanappdkhp.R;
import com.example.doanappdkhp.entity.LopHocPhanTH;
import com.example.doanappdkhp.my_interface.IClickItemLHPTH;

import java.text.DateFormat;
import java.util.ArrayList;

public class LopHocPhanTHAdapter extends RecyclerView.Adapter<LopHocPhanTHAdapter.ThingViewHolder>{
    private Context mcontext;
    private LayoutInflater layoutInflater;
    private ArrayList<LopHocPhanTH> lopHocPhanTHs;
    private IClickItemLHPTH iClickItemLHPTH;
    int selectedPosition = -1;
    boolean check = false;

    public LopHocPhanTHAdapter(Context mcontext, ArrayList<LopHocPhanTH> lopHocPhanTHs, IClickItemLHPTH iClickItemLHPTH) {
        this.mcontext = mcontext;
        this.lopHocPhanTHs = lopHocPhanTHs;
        this.iClickItemLHPTH = iClickItemLHPTH;
    }

    public void setLopHocPhanTHs(ArrayList<LopHocPhanTH> lopHocPhanTHs) {
        this.lopHocPhanTHs = lopHocPhanTHs;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public LopHocPhanTHAdapter.ThingViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View mItemView = layoutInflater.from(parent.getContext()).inflate(R.layout.item_lophocphanth, parent, false);
        return new LopHocPhanTHAdapter.ThingViewHolder(mItemView);
    }

    @Override
    public void onBindViewHolder(@NonNull LopHocPhanTHAdapter.ThingViewHolder holder, int position) {
        LopHocPhanTH lopHocPhanTH = lopHocPhanTHs.get(position);
        holder.tvGiangVien.setText(lopHocPhanTH.getHoTen());
        holder.tvNgayHoc.setText(lopHocPhanTH.getNgayHoc());
        holder.tvPhongHoc.setText(lopHocPhanTH.getPhongHoc());
        holder.tvTietHoc.setText(lopHocPhanTH.getTietHoc());
        holder.tvMaNhom.setText(lopHocPhanTH.getMaNhom());
        holder.tvNgayBatDau.setText(DateFormat.getDateInstance().format(lopHocPhanTH.getNgayBatDau()));

        holder.rdoButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (check) {
                    holder.rdoButton.setChecked(false);
                    check = false;

                    //write code for when button is unchecked
                } else {
                    check = true;
                    holder.rdoButton.setChecked(true);

                    //write code for when button is checked
                }
            }
        });
        holder.rdoButton.setChecked(position == selectedPosition);
        holder.rdoButton.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked ){
                    selectedPosition = holder.getAdapterPosition();
                    iClickItemLHPTH.onClickItemTH(lopHocPhanTH);
                }

            }
        });
    }
    @Override
    public long getItemId(int position) {
        //pass position
        return position;
    }
    @Override
    public int getItemViewType(int position) {
        //pass position
        return position;
    }
    @Override
    public int getItemCount() {
        return lopHocPhanTHs.size();
    }

    public class ThingViewHolder extends RecyclerView.ViewHolder {
        TextView tvMaNhom, tvGiangVien, tvNgayHoc, tvPhongHoc, tvTietHoc, tvNgayBatDau;
        Button btnChonLTvsTH;
        RadioButton rdoButton;
        public ThingViewHolder(@NonNull View itemView) {
            super(itemView);
            tvMaNhom = itemView.findViewById(R.id.tv_MaNhomTH);
            tvGiangVien = itemView.findViewById(R.id.tv_GiangVienTH);
            tvNgayHoc = itemView.findViewById(R.id.tv_NgayHocTH);
            tvPhongHoc = itemView.findViewById(R.id.tv_PhongTH);
            tvTietHoc = itemView.findViewById(R.id.tv_TietHocTH);
            tvNgayBatDau = itemView.findViewById(R.id.tv_NgayBatDauTH);
            //btnChonLTvsTH = itemView.findViewById(R.id.btn_ChonNhomLTvsTH);
            rdoButton = itemView.findViewById(R.id.radioButtonLHPTH);
        }
    }
}
