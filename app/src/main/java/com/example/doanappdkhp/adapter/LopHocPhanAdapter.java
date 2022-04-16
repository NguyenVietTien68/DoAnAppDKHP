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
import com.example.doanappdkhp.entity.LopHocPhan;
import com.example.doanappdkhp.my_interface.ICLickItemLHP;

import java.util.ArrayList;

public class LopHocPhanAdapter extends RecyclerView.Adapter<LopHocPhanAdapter.ThingViewHolder>{
    private Context mcontext;
    private LayoutInflater layoutInflater;
    private ArrayList<LopHocPhan> lopHocPhans;
    private ICLickItemLHP iClickItemLHP;
    boolean check =false;
    int selectedPosition = -1;
    public LopHocPhanAdapter(Context mcontext, ArrayList<LopHocPhan> lopHocPhans, ICLickItemLHP listener) {
        this.mcontext = mcontext;
        this.lopHocPhans = lopHocPhans;
        this.iClickItemLHP = listener;
    }

    public void setLopHocPhans(ArrayList<LopHocPhan> lopHocPhans) {
        this.lopHocPhans = lopHocPhans;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public LopHocPhanAdapter.ThingViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View mItemView = layoutInflater.from(parent.getContext()).inflate(R.layout.item_lophocphan, parent, false);
        return new LopHocPhanAdapter.ThingViewHolder(mItemView);
    }

    @Override
    public void onBindViewHolder(@NonNull LopHocPhanAdapter.ThingViewHolder holder, int position) {
        LopHocPhan lopHocPhan = lopHocPhans.get(position);
        holder.tvMaLopHP.setText(lopHocPhan.getMaLopHP());
        holder.tvSiSo.setText(String.valueOf(lopHocPhan.getSiSo()));
        holder.tvSLDaDangKy.setText(String.valueOf(lopHocPhan.getDaDangKy()));
        if(lopHocPhan.getSiSo() > lopHocPhan.getDaDangKy()){
            holder.tvTinhTrang.setText("Chờ đăng ký");
        }else if(lopHocPhan.getSiSo() == lopHocPhan.getDaDangKy()){
            holder.tvTinhTrang.setText("Chấp nhận mở lớp");
        }
        holder.btnChonMaLHP.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                iClickItemLHP.onClickItemLHP(lopHocPhan);
                //selectedPosition = holder.getAdapterPosition();
            }
        });



    }

    @Override
    public int getItemCount() {
        return lopHocPhans.size();
    }

    public class ThingViewHolder extends RecyclerView.ViewHolder {
        TextView tvMaLopHP, tvSiSo, tvSLDaDangKy, tvTinhTrang;
        Button btnChonMaLHP;

        public ThingViewHolder(@NonNull View itemView) {
            super(itemView);
            tvMaLopHP = itemView.findViewById(R.id.tv_MaLopHPDKHP);
            tvSiSo = itemView.findViewById(R.id.tv_SiSoDKHP);
            tvSLDaDangKy = itemView.findViewById(R.id.tv_SLDaDangKyDKHP);
            tvTinhTrang = itemView.findViewById(R.id.tv_TinhTrangDKHP);
            btnChonMaLHP = itemView.findViewById(R.id.btnChonLHP);
        }
    }
}
