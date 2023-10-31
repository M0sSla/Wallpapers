package com.example.myapplication;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.API.Model.PhotoDTO;
import com.example.myapplication.databinding.ItemPhotoBinding;

import java.util.ArrayList;
import java.util.List;

public class AdapterPhoto
        extends RecyclerView.Adapter<AdapterPhoto.PhotoViewHolder> {
    ArrayList<PhotoDTO> data = new ArrayList<>();

    public void setData(List<PhotoDTO> data) {
        this.data.clear();
        this.data.addAll(data);
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public PhotoViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ItemPhotoBinding binding = ItemPhotoBinding.inflate(LayoutInflater.from(parent.getContext()),
                parent, false);
        return new PhotoViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull PhotoViewHolder holder, int position) {
        PhotoDTO dto = data.get(position);
        holder.binding.text.setText(dto.getDate());
        holder.binding.item.setOnClickListener(v -> {
            Navigation.findNavController(v)
                    .navigate(R.id.action_photoListFragment_to_photoFragment);
        });
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    class PhotoViewHolder extends RecyclerView.ViewHolder {
        ItemPhotoBinding binding;

        public PhotoViewHolder(ItemPhotoBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }
}
