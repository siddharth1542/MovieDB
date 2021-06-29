package com.codewithsid.moviedb.viewModels;



import android.provider.ContactsContract;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.codewithsid.moviedb.models.MovieModel;
import com.codewithsid.moviedb.repository.MovieRepository;

import java.util.List;

public class MovieListViewModel extends ViewModel {
     //this class is used for VIEWMODEL

       private MovieRepository movieRepository;

//constructer
    public MovieListViewModel() {
       movieRepository = MovieRepository.getInstance();
    }

    public LiveData<List<MovieModel>> getMovies() {
        return  movieRepository.getMovies();
    }

    public LiveData<List<MovieModel>> getPop() {
        return  movieRepository.getPop();
    }

    //2- calling Method in viewModel
    public void searchMovieApi(String query,int pageNumber){
        movieRepository.searchMovieApi(query,pageNumber);
    }

    public void searchMoviePop(int pageNumber){
        movieRepository.searchMoviePop(pageNumber);
    }

    public void searchNextpage(){
        movieRepository.searchNextPage();
    }
}
