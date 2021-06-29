package com.codewithsid.moviedb;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;

public class AppExecutors {

    //Pattern Singlton

    private static AppExecutors instance;

    public static AppExecutors getInstance(){
        if(instance==null){
            instance = new AppExecutors();
        }
        return instance;
    }



    private final ScheduledExecutorService mNetworkIO = Executors.newScheduledThreadPool(3);

    public ScheduledExecutorService networkIO(){
        return mNetworkIO;

    }
}
