package com.example.myapplication;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.API.Model.DateDTO;
import com.example.myapplication.databinding.ItemDateBinding;

import java.util.ArrayList;
import java.util.List;

public class AdapterMain extends RecyclerView.Adapter<AdapterMain.DateViewHolder> {

    ArrayList<DateDTO> data = new ArrayList<>();

    public void setData(List<DateDTO> data) {
        this.data.clear();
        this.data.addAll(data);
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public DateViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ItemDateBinding binding = ItemDateBinding.inflate(LayoutInflater.from(parent.getContext()),
                parent, false);

        return new DateViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull DateViewHolder holder, int position) {
        DateDTO dateDTO = data.get(position);
        DateDTO date = data.get(position);
        holder.binding.text.setText(date.getDate());
        holder.binding.item.setOnClickListener(v -> {
            // переход на фото лист фрагмент
        });
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    class DateViewHolder extends RecyclerView.ViewHolder {

        ItemDateBinding binding;

        public DateViewHolder(ItemDateBinding binding) {
            super(binding.getRoot());
            this.binding = binding;

        }
    }
}
