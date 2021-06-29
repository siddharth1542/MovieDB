package com.codewithsid.moviedb.adapter;

import android.view.View;
import android.widget.ImageView;
import android.widget.RatingBar;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.codewithsid.moviedb.R;

public class Popular_view_Holder extends RecyclerView.ViewHolder implements View.OnClickListener {

    ImageView imageViewPop;
    RatingBar ratingBarPop;

    OnMovieListner onMovieListnerPop;

    public Popular_view_Holder(@NonNull View itemView,OnMovieListner onMovieListnerPop) {
        super(itemView);
        this.onMovieListnerPop = onMovieListnerPop;


        imageViewPop = itemView.findViewById(R.id.movie_imgPop);
        ratingBarPop = itemView.findViewById(R.id.ratingBarPop);

        itemView.setOnClickListener(this);


    }

    @Override
    public void onClick(View v) {
        onMovieListnerPop.onMovieClick(getAdapterPosition());

    }
}
