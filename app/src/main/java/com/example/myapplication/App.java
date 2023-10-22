package com.example.myapplication;

import android.app.Application;

import com.example.myapplication.API.NasaService;
import com.nostra13.universalimageloader.cache.memory.impl.LRULimitedMemoryCache;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;

public class App extends Application {

    private NasaService nasaService;

    @Override
    public void onCreate() {
        super.onCreate();

        nasaService = new NasaService();
        DisplayImageOptions defaultOptions = new DisplayImageOptions.Builder()
                .cacheInMemory(true)
                .build();
        ImageLoaderConfiguration configuration = new ImageLoaderConfiguration.Builder(this)
                .defaultDisplayImageOptions(defaultOptions)
                .memoryCache(new LRULimitedMemoryCache(25 * 1024 * 1024))
                .memoryCacheSize(25 * 1024 * 1024)
                .build();
        ImageLoader.getInstance().init(configuration);
    }

    public NasaService getNasaService() { return nasaService; }
}
