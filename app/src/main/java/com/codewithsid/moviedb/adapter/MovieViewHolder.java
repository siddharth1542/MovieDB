package com.codewithsid.moviedb.adapter;

import android.view.View;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.codewithsid.moviedb.R;

public class MovieViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

    //widgets

    ImageView imageView;
    RatingBar ratingBar;
    //click Listner

    OnMovieListner onMovieListner;

    public MovieViewHolder(@NonNull View itemView , OnMovieListner onMovieListner) {
        super(itemView);
        this.onMovieListner = onMovieListner;


        imageView = itemView.findViewById(R.id.movie_img);
        ratingBar = itemView.findViewById(R.id.ratingBar);

        itemView.setOnClickListener(this);

    }
    @Override
    public void onClick(View v){
        onMovieListner.onMovieClick(getAdapterPosition());
    }
}
