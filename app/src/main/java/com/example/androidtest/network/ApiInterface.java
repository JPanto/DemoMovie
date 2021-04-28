package com.example.androidtest.network;

import com.example.androidtest.interactor.MovieList;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ApiInterface {
    @GET("movie/popular")
    Call<MovieList> getPopularMovies(@Query("api_key") String apiKey, @Query("page") int pageNo, @Query("language") String lang);
}
