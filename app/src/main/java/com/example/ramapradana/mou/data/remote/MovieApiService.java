package com.example.ramapradana.mou.data.remote;

import com.example.ramapradana.mou.Reviews.ApiReview;
import com.example.ramapradana.mou.Trailer.ApiTrailer;
import com.example.ramapradana.mou.data.remote.ApiResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * Created by Rama Pradana on 2/10/2018.
 */

public interface MovieApiService {

    @GET("popular")
    Call<ApiResponse> getPopularMovie(@Query("api_key") String apiKey, @Query("page") String page);

    @GET("top_rated")
    Call<ApiResponse> getTopRatedMovie(@Query("api_key") String apiKey, @Query("page") String page);

    @GET("{movie_id}/videos")
    Call<ApiTrailer> getMovieTrailer(@Path("movie_id") int movie_id, @Query("api_key") String apiKey);

    @GET("{movie_id}/reviews")
    Call<ApiReview> getMovieReview(@Path("movie_id") int movie_id, @Query("api_key") String apiKey, @Query("page") String page);
}
