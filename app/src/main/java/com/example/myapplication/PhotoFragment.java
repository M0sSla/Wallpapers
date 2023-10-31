package com.example.myapplication;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.davemorrissey.labs.subscaleview.ImageSource;
import com.davemorrissey.labs.subscaleview.SubsamplingScaleImageView;
import com.example.myapplication.API.Model.PhotoDTO;
import com.example.myapplication.databinding.FragmentPhotoBinding;
import com.example.myapplication.databinding.FragmentPhotoListBinding;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.listener.SimpleImageLoadingListener;

import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.functions.BiConsumer;
import io.reactivex.schedulers.Schedulers;

public class PhotoFragment extends Fragment {
    FragmentPhotoBinding binding;
    private static final int REQUEST_PERMISSION_WRITE_STORAGE = 1111;
    private static final String EXTRA_URL = "PhotoActivity.EXTRA_URL";
    private SubsamplingScaleImageView imageView;
    private boolean isToolbarVisible;
    private Bitmap photo;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = FragmentPhotoBinding.inflate(inflater, container, false);

        getActivity().getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);

        ImageLoader.getInstance().loadImage("extra_url", new SimpleImageLoadingListener() {
            @Override
            public void onLoadingComplete(String imageUri, View view, Bitmap loadedImage) {
                if (!getActivity().isFinishing()) {
                    photo = loadedImage;
                    binding.image.setImage(ImageSource.cachedBitmap(loadedImage));
                    binding.progress.setVisibility(View.GONE);
                }
            }
        });

        return binding.getRoot();
    }
}
