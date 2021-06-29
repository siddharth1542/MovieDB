package com.codewithsid.moviedb.adapter;

import android.graphics.Movie;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.codewithsid.moviedb.R;
import com.codewithsid.moviedb.models.MovieModel;
import com.codewithsid.moviedb.utils.Credential;

import java.util.List;

public class MovieRecyclerView extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private List<MovieModel> mMovies;
    private OnMovieListner onMovieListner;

    private static final int DISPLAY_POP =1;
    private static final int DISPLAY_SEARCH =2;



    public MovieRecyclerView(OnMovieListner onMovieListner) {
        this.onMovieListner = onMovieListner;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
       View view = null;
       if(viewType == DISPLAY_SEARCH){
           view = LayoutInflater.from(parent.getContext()).inflate(R.layout.movie_list,parent,false);
           return new MovieViewHolder(view,onMovieListner);
       }else{
           view = LayoutInflater.from(parent.getContext()).inflate(R.layout.popular_movies_layout,
                   parent,false);
           return new Popular_view_Holder(view,onMovieListner);
       }
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int i) {

        int itemViewType = getItemViewType(i);

        if(itemViewType == DISPLAY_SEARCH){
            //vote avarage is over 10,and our rating bar is over 5 star divivding by 2
            ((MovieViewHolder) holder).ratingBar.setRating(mMovies.get(i).getVote_average()/2);


            //ImageView: using Glide Library


            //we need to get the run time and the category
            //we need to get change the api response

            Glide.with(holder.itemView.getContext())
                    .load("https://image.tmdb.org/t/p/w500/"+mMovies.get(i).getPoster_path())
                    .into(((MovieViewHolder) holder).imageView);

        }else{

            //vote avarage is over 10,and our rating bar is over 5 star divivding by 2
            ((Popular_view_Holder) holder).ratingBarPop.setRating(mMovies.get(i).getVote_average()/2);
            //ImageView: using Glide Library


            //we need to get the run time and the category
            //we need to get change the api response

            Glide.with(holder.itemView.getContext())
                    .load("https://image.tmdb.org/t/p/w500/"+mMovies.get(i).getPoster_path())
                    .into(((Popular_view_Holder) holder).imageViewPop);

        }

        Log.d("Tag","okkkkkkkk");




    }

    @Override
    public int getItemCount() {
        if (mMovies != null) {
            return mMovies.size();
        }
        return 0;
    }

    public void setmMovies(List<MovieModel> mMovies) {
        this.mMovies = mMovies;
        notifyDataSetChanged();
    }
    //getting the id of the movie Clicked
    public  MovieModel getSelectedMovie(int position){
        if(mMovies!= null){
            if(mMovies.size()>0){
                return mMovies.get(position);
            }
        }
        return null;
    }


    @Override
    public int getItemViewType(int position){
        if(Credential.POPULAR){
            return DISPLAY_POP;
        }
        else
            return DISPLAY_SEARCH;
    }
}

