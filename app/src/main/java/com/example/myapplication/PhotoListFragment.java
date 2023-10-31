package com.example.myapplication;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.myapplication.API.Model.PhotoDTO;
import com.example.myapplication.databinding.FragmentPhotoListBinding;

import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.functions.BiConsumer;
import io.reactivex.schedulers.Schedulers;

public class PhotoListFragment extends Fragment {
    FragmentPhotoListBinding binding;
    CompositeDisposable disposable = new CompositeDisposable();
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = FragmentPhotoListBinding.inflate(inflater, container, false);

        AdapterPhoto adapter = new AdapterPhoto();
        binding.list
                .setLayoutManager(
                        new LinearLayoutManager(getContext(),
                                LinearLayoutManager.VERTICAL,
                                false)
                );
        binding.list.setAdapter(adapter);

        App app = (App) requireActivity().getApplication();
        String date = "";
        if (getArguments() != null) {
            date = getArguments().getString("date");
        }
        disposable.add(app.getNasaService().getApi().getPhotosForDate(date)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new BiConsumer<List<PhotoDTO>, Throwable>() {
                    @Override
                    public void accept(List<PhotoDTO> photoDTOS, Throwable throwable) throws Exception {
                        if (throwable != null) {
                            Toast.makeText(getActivity(), "data loading error", Toast.LENGTH_SHORT).show();
                        } else {
                            adapter.setData(photoDTOS);
                        }
                    }
                })
        );


        return binding.getRoot();
    }

    @Override
    public void onDestroyView() {
        disposable.dispose();
        super.onDestroyView();
    }
}
