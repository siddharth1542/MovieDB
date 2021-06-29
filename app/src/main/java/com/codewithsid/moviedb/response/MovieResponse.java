package com.codewithsid.moviedb.response;


import com.codewithsid.moviedb.models.MovieModel;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

//this class is for requesting single movie object
public class MovieResponse {
    // 1-finding  the movie object

    @SerializedName("results")
   @Expose()
    private MovieModel movie;


    public MovieModel getMovie(){
        return movie;
    }

    @Override
    public String toString() {
        return "MovieResponse{" +
                "movie=" + movie +
                '}';
    }
}
