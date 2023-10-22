package com.example.myapplication;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.myapplication.API.Model.DateDTO;
import com.example.myapplication.databinding.FragmentMainBinding;

import java.util.List;
import java.util.Objects;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.functions.BiConsumer;
import io.reactivex.schedulers.Schedulers;

public class MainFragment extends Fragment {
    private FragmentMainBinding binding;
    CompositeDisposable disposable = new CompositeDisposable();

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = FragmentMainBinding.inflate(inflater, container, false);

        AdapterMain adapter = new AdapterMain();
        binding.list.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));
        binding.list.setAdapter(adapter);

        App app = (App) requireActivity().getApplication();

        disposable.add(app.getNasaService().getApi().getDateswithPhoto()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new BiConsumer<List<DateDTO>, Throwable>() {
                    @Override
                    public void accept(List<DateDTO> dateDTOS, Throwable throwable) throws Exception {
                        if (throwable != null) {
                            Toast.makeText(getActivity(), "Data loading error", Toast.LENGTH_SHORT).show();
                        } else {
                            adapter.setData(dateDTOS);
                        }
                    }
                }));

        return binding.getRoot();
    }

    @Override
    public void onDestroy() {
        disposable.dispose();
        super.onDestroy();
    }

}
