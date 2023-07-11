package com.habibfr.budget_buddy;

import android.content.Intent;
import android.text.Layout;
import android.view.ContextThemeWrapper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class TransaksiAdapterLaporan extends RecyclerView.Adapter<TransaksiAdapterLaporan.TransaksiViewHolder> {
    private List<Transaksi> transaksiList;

    public TransaksiAdapterLaporan(List<Transaksi> transaksiList) {
        this.transaksiList = transaksiList;
    }

    @NonNull
    @Override
    public TransaksiViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_laporan, parent, false);
        return new TransaksiViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull TransaksiViewHolder holder, int position) {
        Transaksi transaksi = transaksiList.get(position);

//        String txtjudul = transaksi.getTransaction_id() + " " + transaksi.getTitle();
        holder.judul.setText(transaksi.getTitle());
        holder.amount.setText(String.valueOf(transaksi.getAmount()));
        holder.date.setText(transaksi.getDate());
        if (transaksi.getType().equalsIgnoreCase("Keluar")) {
            holder.imgCar.setImageResource(R.drawable.img_calendar);
            holder.rowLaporan.setBackground(ContextCompat.getDrawable(holder.rowLaporan.getContext(), R.drawable.rectangle_bg_gray_301_radius_20));

        }

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(holder.amount.getContext(), DetailActivity.class);
                intent.putExtra("user_id", transaksi.getUser_id());
                intent.putExtra("transaction_id",transaksi.getTransaction_id());
                holder.amount.getContext().startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return transaksiList.size();
    }

    public static class TransaksiViewHolder extends RecyclerView.ViewHolder {
        TextView judul, amount, date;
        ImageView imgCar;
        LinearLayout rowLaporan;

        public TransaksiViewHolder(View itemView) {
            super(itemView);
            judul = itemView.findViewById(R.id.txtShopping);
            amount = itemView.findViewById(R.id.txtAmountLaporan);
            date = itemView.findViewById(R.id.txtTanggalLaporan);
            rowLaporan = itemView.findViewById(R.id.linearLayoutRowLaporan);
            imgCar = itemView.findViewById(R.id.btnCar);
        }
    }
}
