package dev.nhaiden.lab10_tankapp;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;
import java.util.Locale;

import dev.nhaiden.lab10_tankapp.model.Tankvorgang;

public class TankvorgangAdapter extends RecyclerView.Adapter<TankvorgangAdapter.TankvorgangViewHolder> {
    private final List<Tankvorgang> tankvorgangList;
    private final TankvorgangOnClickListener onClickListener;

    public TankvorgangAdapter (List<Tankvorgang> tankvorgangList, TankvorgangOnClickListener onClickListener) {
        this.tankvorgangList = tankvorgangList;
        this.onClickListener = onClickListener;
    }

    @NonNull
    @Override
    public TankvorgangViewHolder onCreateViewHolder (@NonNull ViewGroup parent, int viewType) {
        return new TankvorgangViewHolder(LayoutInflater.from(parent.getContext())
                .inflate(R.layout.activity_detai_list_item, parent, false), onClickListener);
    }

    @Override
    public void onBindViewHolder (@NonNull TankvorgangViewHolder holder, int position) {
        Tankvorgang tankvorgang = tankvorgangList.get(position);
        holder.date.setText(tankvorgang.getTankvorgang_datum().toString());
        holder.oldKm.setText(String.format(Locale.ENGLISH, "%d", tankvorgang.getAlterKMStand()));
        holder.newKm.setText(String.format(Locale.ENGLISH, "%d", tankvorgang.getNeuerKMStand()));
        holder.liters.setText(String.format(Locale.ENGLISH, "%.2f", tankvorgang.getGetankteLiter()));
        holder.pricePerLiter.setText(String.format(Locale.ENGLISH, "%.2f", tankvorgang.getPreisPerLiter()));
    }

    @Override
    public int getItemCount () {
        return tankvorgangList.size();
    }

    public static class TankvorgangViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        CardView cardView;
        TextView date;
        TextView oldKm;
        TextView newKm;
        TextView liters;
        TextView pricePerLiter;
        TankvorgangOnClickListener onClickListener;

        public TankvorgangViewHolder (@NonNull View itemView, TankvorgangOnClickListener onClickListener) {
            super(itemView);
            this.onClickListener = onClickListener;
            cardView = itemView.findViewById(R.id.cv_tankvorgang);
            date = itemView.findViewById(R.id.tv_date);
            oldKm = itemView.findViewById(R.id.tv_old_km);
            newKm = itemView.findViewById(R.id.tv_new_km);
            liters = itemView.findViewById(R.id.tv_liters);
            pricePerLiter = itemView.findViewById(R.id.tv_price_per_liter);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick (View v) {
            onClickListener.onClick(getAdapterPosition());
        }
    }
}