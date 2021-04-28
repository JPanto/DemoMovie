package com.example.androidtest.ui.adapters;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.androidtest.R;
import com.example.androidtest.interactor.Movie;
import com.example.androidtest.network.ApiClient;

import java.util.List;

public class RecyclerMovieAdapter extends RecyclerView.Adapter<RecyclerMovieAdapter.MyViewHolder> {

    private List<Movie> movieList;
    private Context context;
    Dialog myDialog;

    public RecyclerMovieAdapter(List<Movie> movieList, Context context) {
        this.movieList = movieList;
        this.context = context;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_movie, parent, false);
        MyViewHolder vHolder = new MyViewHolder(view);

        // Dialog ini

        myDialog = new Dialog(context);
        myDialog.setContentView(R.layout.dialog_movie);
        myDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

        vHolder.iv_cover.setOnClickListener((View.OnClickListener) v -> {
            TextView dialog_movie_tv = (TextView) myDialog.findViewById(R.id.tv_movie);
            TextView dialog_desc_tv = (TextView) myDialog.findViewById(R.id.tv_desc);
            TextView dialog_rate_tv = (TextView) myDialog.findViewById(R.id.tv_rate);
            ImageView dialog = (ImageView) myDialog.findViewById(R.id.iv_cover);

            dialog_movie_tv.setText(movieList.get(vHolder.getAdapterPosition()).getOriginalTitle());
            dialog_desc_tv.setText(movieList.get(vHolder.getAdapterPosition()).getOverview());
            dialog_rate_tv.setText(movieList.get(vHolder.getAdapterPosition()).getVoteAverage().toString());
            Glide.with(context)
                    .load(ApiClient.IMAGE_BASE_URL+movieList.get(vHolder.getAdapterPosition()).getPosterPath())
                    .fitCenter()
                    .circleCrop()
                    .into(dialog);
            myDialog.show();
        });

        return vHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

        Glide.with(context)
                .load(ApiClient.IMAGE_BASE_URL+movieList.get(position).getPosterPath())
                .fitCenter()
                .into(holder.iv_cover);
    }

    @Override
    public int getItemCount() {
        return movieList.size();
    }


    public class MyViewHolder extends  RecyclerView.ViewHolder {

        ImageView iv_cover;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            iv_cover = itemView.findViewById(R.id.iv_cover);

        }
    }



}