package com.example.androidtest.network;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiClient {

    public static final String BASE_URL = "https://api.themoviedb.org/3/";
    public static Retrofit retrofit = null;
    public static final String API_KEY = "7c4246b0e6e352d2ac83fb57675916ae";
    public static final String IMAGE_BASE_URL = "https://image.tmdb.org/t/p/w200";

    public static Retrofit getClient(){
        if (retrofit == null) {
            retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofit;
    }
}
