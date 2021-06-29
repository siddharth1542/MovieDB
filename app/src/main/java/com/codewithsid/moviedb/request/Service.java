package com.codewithsid.moviedb.request;

import com.codewithsid.moviedb.utils.Credential;


import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public  class Service {

    private static Retrofit.Builder retrofitbuilder = new Retrofit.Builder()
            .baseUrl(Credential.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create());

    private static Retrofit retrofit = retrofitbuilder.build();

    private static MovieApi movieApi = retrofit.create(MovieApi.class);

    public static MovieApi  getMovieApi(){
        return movieApi;
    }

}
