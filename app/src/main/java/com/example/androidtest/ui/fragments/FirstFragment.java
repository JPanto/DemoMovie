package com.example.androidtest.ui.fragments;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.androidtest.ui.adapters.RecyclerMovieAdapter;
import com.example.androidtest.R;
import com.example.androidtest.interactor.Movie;
import com.example.androidtest.interfaces.MovieListContract;
import com.example.androidtest.presenters.MoviePresenter;

import java.util.ArrayList;
import java.util.List;

public class FirstFragment extends Fragment implements MovieListContract.View {

    private MoviePresenter moviePresenter;
    private RecyclerView rvMovieList;
    private List<Movie> movieList;
    private RecyclerMovieAdapter recyclerMovieAdapter;
    private ProgressBar pbLoading;
    private int pageNo = 1;

    private GridLayoutManager gridLayoutManager;

    private Context globalContext = null;


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        globalContext = this.getActivity();
    }

    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {
        super.onCreateView(inflater,container,savedInstanceState);
        View v = inflater.inflate(R.layout.fragment_first, container, false);
        if(v!=null){
            rvMovieList = (RecyclerView) v.findViewById(R.id.rv_movies);
            pbLoading = (ProgressBar) v.findViewById(R.id.pb_loading);
        }
        return v;
    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        movieList = new ArrayList<>();
        gridLayoutManager = new GridLayoutManager(globalContext, 3);
        rvMovieList.setLayoutManager(gridLayoutManager);
        rvMovieList.setHasFixedSize(true);

        moviePresenter = new MoviePresenter(this);
        moviePresenter.requestDataFromServer();
    }

    @Override
    public void showProgress() {
        pbLoading.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideProgress() {
        pbLoading.setVisibility(View.GONE);
    }

    @Override
    public void setDataToRecyclerview(List<Movie> movieListArray) {
        movieList.addAll(movieListArray);
        recyclerMovieAdapter = new RecyclerMovieAdapter(movieList, globalContext);
        rvMovieList.setAdapter(recyclerMovieAdapter);
    }

    @Override
    public void onResponseFailure(Throwable throwable) {

    }
}