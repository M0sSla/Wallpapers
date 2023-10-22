package com.example.myapplication.API;

import com.example.myapplication.API.Model.DateDTO;
import com.example.myapplication.API.Model.PhotoDTO;

import java.util.List;

import io.reactivex.Single;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface NasaApi {

    @GET("natural/all")
    Single<List<DateDTO>> getDateswithPhoto();

    @GET("natural/date/{date}")
    Single<List<PhotoDTO>> getPhotosForDate(@Path("date") String date);
}
