package com.example.doanappdkhp.adapter;

import static com.example.doanappdkhp.adapter.LopHocPhanLTAdapter.ThingViewHolder.rdoButtonLT;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.provider.CalendarContract;
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
import com.example.doanappdkhp.entity.LopHocPhan;
import com.example.doanappdkhp.my_interface.ICLickItemLHP;

import java.util.ArrayList;

public class LopHocPhanAdapter extends RecyclerView.Adapter<LopHocPhanAdapter.ThingViewHolder>{
    private Context mcontext;
    private LayoutInflater layoutInflater;
    private ArrayList<LopHocPhan> lopHocPhans;
    private ICLickItemLHP iClickItemLHP;
    boolean check =false;
    int color = -1;
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

    @SuppressLint("NewApi")
    @Override
    public void onBindViewHolder(@NonNull LopHocPhanAdapter.ThingViewHolder holder, int position) {
        LopHocPhan lopHocPhan = lopHocPhans.get(position);
        holder.tvMaLopHP.setText(lopHocPhan.getMaLopHP());
        holder.tvSiSo.setText(String.valueOf(lopHocPhan.getSiSo()));
        holder.tvSLDaDangKy.setText(String.valueOf(lopHocPhan.getDaDangKy()));

        // 0.666666667 = 2/3
        if(lopHocPhan.getDaDangKy() < (0.666666667 * lopHocPhan.getSiSo())){
            holder.tvTinhTrang.setText("Chờ đăng ký");
        }else{
            holder.tvTinhTrang.setText("Chấp nhận mở lớp");
        }
        holder.btnChonMaLHP.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("NewApi")
            @Override
            public void onClick(View v) {
                //color = holder.getAdapterPosition();
                iClickItemLHP.onClickItemLHP(lopHocPhan);
                notifyDataSetChanged();

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
