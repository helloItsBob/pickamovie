package com.app.pickamovie.remoteDataSource;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface MovieAPI {

    String URL = "https://www.omdbapi.com";
    String KEY = "29fd92e6";
    String KEY2 = "35efa840"; // use any of these keys!
    String KEY3 = "fe53f97e";

    @GET("/")
    Call<MovieResponse> getMoviesByRandomId(@Query("apikey") String key, @Query("i") String movieId,
                                            @Query("plot") String plot);

    @GET("/")
    Call<SearchResponse> getMoviesBySearch(@Query("apikey") String key, @Query("s") String searchWord);
}
