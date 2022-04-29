package com.example.doanappdkhp.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.doanappdkhp.R;
import com.example.doanappdkhp.entity.LopHocPhanLT;
import com.example.doanappdkhp.gui.DKHP;
import com.example.doanappdkhp.my_interface.IClickItemNhomLT;

import java.text.DateFormat;
import java.util.ArrayList;

public class LopHocPhanLTAdapter extends RecyclerView.Adapter<LopHocPhanLTAdapter.ThingViewHolder>{
    private Context mcontext;
    private LayoutInflater layoutInflater;
    private ArrayList<LopHocPhanLT> lopHocPhanLTS;
    private IClickItemNhomLT iClickItemNhomLT;
    int selectedPosition = -1;
    boolean check = false;

    public LopHocPhanLTAdapter(Context mcontext, ArrayList<LopHocPhanLT> lopHocPhanLTS, IClickItemNhomLT listener) {
        this.mcontext = mcontext;
        this.lopHocPhanLTS = lopHocPhanLTS;
        this.iClickItemNhomLT = listener;
    }

    public void setLopHocPhanLTvsTHs(ArrayList<LopHocPhanLT> lopHocPhanLTS) {
        this.lopHocPhanLTS = lopHocPhanLTS;
        notifyDataSetChanged();
    }
    @NonNull
    @Override
    public LopHocPhanLTAdapter.ThingViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View mItemView = layoutInflater.from(parent.getContext()).inflate(R.layout.item_lophocphanltvsth, parent, false);
        return new LopHocPhanLTAdapter.ThingViewHolder(mItemView);
    }

    @Override
    public void onBindViewHolder(@NonNull LopHocPhanLTAdapter.ThingViewHolder holder, int position) {
        LopHocPhanLT lopHocPhanLT = lopHocPhanLTS.get(position);
        holder.tvGiangVien.setText(lopHocPhanLT.getHoTen());
        holder.tvNgayHoc.setText(lopHocPhanLT.getNgayHoc());
        holder.tvPhongHoc.setText(lopHocPhanLT.getPhongHoc());
        holder.tvTietHoc.setText(lopHocPhanLT.getTietHoc());
        holder.tvMaNhom.setText(lopHocPhanLT.getMaNhom());
        holder.tvNgayBatDau.setText(DateFormat.getDateInstance().format(lopHocPhanLT.getNgayBatDau()));

//        holder.btnChonLTvsTH.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                iClickItemNhomLTvsTH.onClickItemNhomLTvsTH(lopHocPhanLTvsTH);
//            }
//        });
        //holder.rdoButton.setChecked(false);
        holder.rdoButtonLT.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (check) {
                    holder.rdoButtonLT.setChecked(false);
                    check = false;
                    DKHP.nhomlt = "";
                    Toast.makeText(mcontext.getApplicationContext(), "Chưa chọn lớp LT", Toast.LENGTH_SHORT).show();
                    //write code for when button is unchecked
                } else {
                    check = true;
                    holder.rdoButtonLT.setChecked(true);
                    DKHP.nhomlt = DKHP.nhomlt;

                    //write code for when button is checked
                }
            }
        });
        holder.rdoButtonLT.setChecked(position == selectedPosition);
        holder.rdoButtonLT.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked ){
                    selectedPosition = holder.getAdapterPosition();
                    iClickItemNhomLT.onClickItemNhomLTvsTH(lopHocPhanLT);
                }
                //notifyDataSetChanged();
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
        return lopHocPhanLTS.size();
    }

    public static class ThingViewHolder extends RecyclerView.ViewHolder {
        TextView tvMaNhom, tvGiangVien, tvNgayHoc, tvPhongHoc, tvTietHoc, tvNgayBatDau;
        Button btnChonLTvsTH;
        public static RadioButton rdoButtonLT;

        public ThingViewHolder(@NonNull View itemView) {
            super(itemView);
            tvMaNhom = itemView.findViewById(R.id.tv_MaNhomLTTH);
            tvGiangVien = itemView.findViewById(R.id.tv_GiangVienLTTH);
            tvNgayHoc = itemView.findViewById(R.id.tv_NgayHocLTTH);
            tvPhongHoc = itemView.findViewById(R.id.tv_PhongLTTH);
            tvTietHoc = itemView.findViewById(R.id.tv_TietHocLTTH);
            tvNgayBatDau = itemView.findViewById(R.id.tv_NgayBatDauLTTH);
            //btnChonLTvsTH = itemView.findViewById(R.id.btn_ChonNhomLTvsTH);
            rdoButtonLT = itemView.findViewById(R.id.radioButtonLHPLT);

        }
    }
}
