package com.codewithsid.moviedb;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.appcompat.widget.SearchView;

import android.content.Intent;
import android.graphics.Movie;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import android.widget.Toast;

import androidx.appcompat.widget.Toolbar;

import com.codewithsid.moviedb.R;
import com.codewithsid.moviedb.adapter.MovieRecyclerView;
import com.codewithsid.moviedb.adapter.OnMovieListner;
import com.codewithsid.moviedb.models.MovieModel;
import com.codewithsid.moviedb.request.MovieApi;
import com.codewithsid.moviedb.request.Service;
import com.codewithsid.moviedb.response.MovieSearchResponse;
import com.codewithsid.moviedb.utils.Credential;
import com.codewithsid.moviedb.viewModels.MovieListViewModel;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MovieListActivity extends AppCompatActivity implements OnMovieListner {

    // RecyclerView
    private RecyclerView recyclerView;
    private MovieRecyclerView movieRecyclerViewAdapter;



    //view model
    private MovieListViewModel mViewModel;

    boolean  isPopular  = true;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        //Search view
        SetupSearchView();

        recyclerView = findViewById(R.id.recyclerV);


        mViewModel = new  ViewModelProvider(this).get(MovieListViewModel.class);



        //Getting Popular
        mViewModel.searchMoviePop(1);

        ConfigureRecyclerView();

        ObservePopularMovies();

        ObserveAnyChange();

    }

    private void ObservePopularMovies() {

        mViewModel.getPop().observe(this, new Observer<List<MovieModel>>() {
            @Override
            public void onChanged(List<MovieModel> movieModels) {
                //observing for any data change
                if(movieModels != null ){
                    for (MovieModel model:movieModels){
                        //get the data in the log
                        Log.v("Tag","onChanged: "+model.getTitle());

                        movieRecyclerViewAdapter.setmMovies(movieModels);


                    }
                }

            }
        });

    }


    //*************************************************
    //Observing any data change
    private void ObserveAnyChange(){

        mViewModel.getMovies().observe(this, new Observer<List<MovieModel>>() {
            @Override
            public void onChanged(List<MovieModel> movieModels) {
               //observing for any data change
                if(movieModels != null ){
                    for (MovieModel model:movieModels){
                        //get the data in the log
                        Log.v("Tag","onChanged: "+model.getTitle());

                        movieRecyclerViewAdapter.setmMovies(movieModels);


                    }
                }

            }
        });

    }

    //4-calling method in main Activity

//     private void searchMovieApi(String query,int pageNumber){
//        mViewModel.searchMovieApi(query,pageNumber);
//    }

    //5-Initializing recycler View and adding data to it

    private void ConfigureRecyclerView(){
        //Live data can't be passed via the constructor,,,,,mViewModel.getMovies() ckontain live data......
        movieRecyclerViewAdapter = new MovieRecyclerView(this);

        recyclerView.setAdapter(movieRecyclerViewAdapter);

        recyclerView.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false));
        Log.d("Tag","AAya");




        // RecyclerView Pagination

        // Loading next page of api response

        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(@NonNull RecyclerView recyclerView, int newState) {
                if(recyclerView.canScrollVertically(1)){
                    //here we need to diaplay  the next search result in the next page of api
                    mViewModel.searchNextpage();
                }
            }
        });










    }

    @Override
    public void onMovieClick(int position) {

         // Toast.makeText(this, "The Position "+position, Toast.LENGTH_SHORT).show();

        //we don't need postion of the movie in recyclerview
        //we need the Id of the Movie in order to Get All its detail
        Intent intent = new Intent(this,MovieDetails.class);
        intent.putExtra("movie",movieRecyclerViewAdapter.getSelectedMovie(position));
        startActivity(intent);


    }

    @Override
    public void onCategoryClick(String Category) {

    }

//Get data from searchview & query the api to get the results
    private void SetupSearchView() {

        final SearchView searchView = findViewById(R.id.search_view);
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                mViewModel.searchMovieApi(
                        query,
                        1
                );
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });


        searchView.setOnSearchClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                isPopular = false;
            }
        });


    }



//**********************************************


//    private void GetRetrofitResponse() {
//
//        MovieApi movieApi = Service.getMovieApi();
//
//        Call<MovieSearchResponse> responseCall = movieApi
//                .searchMovie(
//                        Credential.API_KEY,
//                        "Action",
//                        1);
//
//        responseCall.enqueue(new Callback<MovieSearchResponse>() {
//            @Override
//            public void onResponse(Call<MovieSearchResponse> call, Response<MovieSearchResponse> response) {
//                if(response.code()==200){
//                    Log.v("Tag","the response  " +response.body().toString());
//
//               List<MovieModel> movies = new ArrayList<>(response.body().getMovies());
//
//
//                    for(MovieModel movie:movies) {
//                        Log.v("Tag", "the release date" + movie.getPoster_path());
//
//                    }
//                }else{
//                    try {
//                        Log.v("Tag","error"+ response.errorBody().toString());
//                    } catch (Exception e) {
//                        e.printStackTrace();
//                    }
//                }
//
//
//            }
//
//            @Override
//           public void onFailure(Call<MovieSearchResponse> call, Throwable t) {
//
//            }
//        });
//
//    }
//
//    private void getRetrofitResponseAccordingToId() {
//
//        MovieApi movieApi = Service.getMovieApi();
//
//        Call<MovieModel> responseCall = movieApi.getMovie(
//                550,
//                Credential.API_KEY
//        );
//        responseCall.enqueue(new Callback<MovieModel>() {
//            @Override
//            public void onResponse(Call<MovieModel> call, Response<MovieModel> response) {
//                if (response.code() == 200) {
//                    MovieModel movies = response.body();
//
//                    Log.v("Tag", "the Response" + movies.getTitle());
//                } else {
//                    try {
//                        Log.v("Tag", "Error " + response.errorBody().toString());
//                    } catch (Exception e) {
//                        e.printStackTrace();
//                    }
//                }
//            }
//
//            @Override
//            public void onFailure(Call<MovieModel> call, Throwable t) {
//
//            }
//        });


    }


//}



//making search with id