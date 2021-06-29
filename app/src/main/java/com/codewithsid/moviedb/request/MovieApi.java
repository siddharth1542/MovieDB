package com.codewithsid.moviedb.request;

import com.codewithsid.moviedb.models.MovieModel;
import com.codewithsid.moviedb.response.MovieSearchResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface MovieApi {

    //search for movies
    @GET("3/search/movie")
    Call<MovieSearchResponse> searchMovie(
            @Query("api_key") String api_key,
            @Query("query") String query,
            @Query("page") int page
    );

    //Get popular Movie
    //https://api.themoviedb.org/3/movie/popular
    // ?api_key=e3769daa94599884c8233ae849529479&page=1
    @GET("/3/movie/popular")
    Call<MovieSearchResponse> getPopular(
            @Query("api_key")String key,
            @Query("page") int page
    );






    //making search with ID
    // https://api.themoviedb.org/3/movie/550?api_key=e3769daa94599884c8233ae849529479

    @GET("3/movie/{movie_id}?")
    Call<MovieModel> getMovie(

            @Path("movie_id") int movie_id,
            @Query("api_key") String api_key
    );


}
