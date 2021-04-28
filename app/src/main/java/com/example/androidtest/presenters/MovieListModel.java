package com.example.androidtest.presenters;

import android.util.Log;

import com.example.androidtest.interactor.Movie;
import com.example.androidtest.interactor.MovieList;
import com.example.androidtest.interfaces.MovieListContract;
import com.example.androidtest.network.ApiClient;
import com.example.androidtest.network.ApiInterface;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MovieListModel implements MovieListContract.Model {

    private final String TAG = "MovieListModel";
    private int pageNo = 1;
    private String language = "es";

    @Override
    public void getMovieList(final OnFinishedListener onFinishedListener, int pageNo) {

        ApiInterface apiService = ApiClient.getClient().create(ApiInterface.class);

        Call<MovieList> call = apiService.getPopularMovies(ApiClient.API_KEY, pageNo, language);

        call.enqueue(new Callback<MovieList>() {
            @Override
            public void onResponse(Call<MovieList> call, Response<MovieList> response) {
                List<Movie> movies = response.body().getMovies();
                Log.e(TAG, "Number of movies received: "+ movies.size());

                onFinishedListener.onFinished(movies);
            }

            @Override
            public void onFailure(Call<MovieList> call, Throwable t) {

                Log.e(TAG, t.toString());
                onFinishedListener.onFailure(t);

            }
        });

    }

}
