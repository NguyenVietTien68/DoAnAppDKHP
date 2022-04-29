package com.example.doanappdkhp.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.doanappdkhp.R;
import com.example.doanappdkhp.entity.CongNo;
import com.example.doanappdkhp.entity.NamHoc;

import java.util.ArrayList;

public class NamAdapter  extends RecyclerView.Adapter<NamAdapter.ThingViewHolder>{
    private Context mcontext;
    private LayoutInflater layoutInflater;
    private ArrayList<NamHoc> namHocs;

    public NamAdapter(Context mcontext, ArrayList<NamHoc> namHocs) {
        this.mcontext = mcontext;
        this.namHocs = namHocs;
    }

    public void setNamHocs(ArrayList<NamHoc> namHocs) {
        this.namHocs = namHocs;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public NamAdapter.ThingViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View mItemView = layoutInflater.from(parent.getContext()).inflate(R.layout.item_congno, parent, false);
        return new NamAdapter.ThingViewHolder(mItemView);
    }

    @Override
    public void onBindViewHolder(@NonNull NamAdapter.ThingViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }

    public class ThingViewHolder extends RecyclerView.ViewHolder {
        public ThingViewHolder(@NonNull View itemView) {
            super(itemView);
        }
    }
}
