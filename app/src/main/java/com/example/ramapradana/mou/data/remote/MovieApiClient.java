package com.example.ramapradana.mou.data.remote;

import com.google.gson.Gson;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Rama Pradana on 2/10/2018.
 */

public class MovieApiClient {
    private static MovieApiService INSTANCE_POPULAR;
    private static MovieApiService INSTANCE_TOPRATED;
    private static MovieApiService INSTANCE_TRAILER;
    private static MovieApiService INSTANCE_REVIEWS;
    private static final String API_BASE_URL = "https://api.themoviedb.org/3/movie/";

    public static MovieApiService getMoviePopularApiService(){
        if (INSTANCE_POPULAR == null){
            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl(API_BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create(new Gson()))
                    .build();

            INSTANCE_POPULAR = retrofit.create(MovieApiService.class);
        }
        return INSTANCE_POPULAR;
    }

    public static MovieApiService getMovieTopRatedApiService(){
        if(INSTANCE_TOPRATED == null){
            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl(API_BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create(new Gson()))
                    .build();

            INSTANCE_TOPRATED = retrofit.create(MovieApiService.class);
        }
        return INSTANCE_TOPRATED;
    }

    public static MovieApiService getMovieTrailerApiService(){
        if (INSTANCE_TRAILER == null){
            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl(API_BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create(new Gson()))
                    .build();

            INSTANCE_TRAILER = retrofit.create(MovieApiService.class);
        }

        return  INSTANCE_TRAILER;
    }

    public static MovieApiService getMovieReviewApiService(){
        if (INSTANCE_REVIEWS == null){
            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl(API_BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create(new Gson()))
                    .build();

            INSTANCE_REVIEWS = retrofit.create(MovieApiService.class);
        }

        return INSTANCE_REVIEWS;
    }


}

