package com.example.doanappdkhp.adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.doanappdkhp.R;
import com.example.doanappdkhp.entity.Diem;
import com.example.doanappdkhp.gui.xem_diem;

import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

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
        holder.tvSTC.setText(String.valueOf(diem.getSoTinChi()));
        holder.tvtenMHHP.setText(diem.getTenMHHP());
        holder.tvDiemTK.setText(String.valueOf(diem.getDiemTK()));
        holder.tvDiemGk.setText(String.valueOf(diem.getDiemGK()));
        holder.tvDiemTH.setText(String.valueOf(diem.getDiemTH()));
        holder.tvDiemCk.setText(String.valueOf(diem.getDiemCK()));

        String monLT = xem_diem.listMonLT.toString();
        String monTH = xem_diem.listMonTH.toString();
        //float diemTKTH = (float) 2.4356346464;
        float diemTKTH = (float) (( ((0.5*diem.getDiemCK())+(0.3*diem.getDiemGK())+(0.2*diem.getDiemTK()))* (diem.getSoTinChi()-1)+(diem.getDiemTH()))/diem.getSoTinChi());
        float diemTKLT = (float) ((0.5*diem.getDiemCK())+(0.3*diem.getDiemGK())+(0.2*diem.getDiemTK()));
        //----------------Nếu là môn thực hành-----------------------
        if (monLT.contains(diem.getTenMHHP()) && monTH.contains(diem.getTenMHHP())){
            DecimalFormat format = new DecimalFormat("0.##");
            format.setRoundingMode(RoundingMode.DOWN); // Choose your Rounding Mode
            //System.out.println(format.format(diemTKTH));
            holder.tvTongKet.setText( format.format(diemTKTH));
            holder.tvDiemTH.setVisibility(View.VISIBLE);
            holder.tvViewDiemTH.setVisibility(View.VISIBLE);
            //Log.d("Ten môn",diem.getTenMHHP());
        }
        //----------------Nếu là môn lý thuyết-----------------------
        else if (monLT.contains(diem.getTenMHHP())){
            holder.tvTongKet.setText(String.valueOf( diemTKLT));
            holder.tvDiemTH.setVisibility(View.GONE);
            holder.tvViewDiemTH.setVisibility(View.GONE);
        }
        if (diem.getDiemCK() == 0){
            holder.tvTongKet.setText("");
            holder.tvXepLoai.setText("");

        }
        else if (diemTKLT >=8 || diemTKTH >=8){
            holder.tvXepLoai.setText("Giỏi");
        }else if (diemTKLT<8 && diemTKLT>=6.5 || diemTKTH<8 && diemTKTH>=6.5){
            holder.tvXepLoai.setText("Khá");
        }else if(diemTKLT<6.5 || diemTKTH<6.5){
            holder.tvXepLoai.setText("Trung bình");
        }
    }

    @Override
    public int getItemCount() {
        return diems.size();
    }

    public class ThingViewHolder extends RecyclerView.ViewHolder {
        TextView tvtenMHHP, tvSTC, tvDiemTK, tvDiemGk, tvDiemTH, tvDiemCk, tvTongKet, tvXepLoai, tvViewDiemTH;

        public ThingViewHolder(@NonNull View itemView) {
            super(itemView);
            tvtenMHHP = itemView.findViewById(R.id.tv_TenMHHPD);
            tvSTC = itemView.findViewById(R.id.tv_SoTinChiD);
            tvDiemTK = itemView.findViewById(R.id.tv_DiemTkD);
            tvDiemGk = itemView.findViewById(R.id.tv_DiemGkD);
            tvDiemTH = itemView.findViewById(R.id.tv_DiemTHD);
            tvDiemCk = itemView.findViewById(R.id.tv_DiemCkD);
            tvTongKet = itemView.findViewById(R.id.tv_TongKet);
            tvXepLoai = itemView.findViewById(R.id.tv_XepLoai);
            tvViewDiemTH = itemView.findViewById(R.id.tv_diemTH_View);

        }
    }
}
