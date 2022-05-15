package com.app.pickamovie.remoteDataSource;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface MovieAPI {

    String URL = "https://www.omdbapi.com";
    String KEY = "29fd92e6";
    String KEY2 = "35efa840"; // use any of these keys!
    String KEY3 = "fe53f97e";

    // Unfortunately, other requests like movie genre are not possible

    // REQUEST FOR HOME FRAGMENT
    @GET("/")
    Call<MovieResponse> getMoviesByRandomId(@Query("apikey") String key, @Query("i") String movieId,
                                            @Query("plot") String plot);

    // REQUEST FOR SEARCH FRAGMENT
    @GET("/")
    Call<SearchResponse> getMoviesBySearch(@Query("apikey") String key, @Query("s") String searchWord);

    @GET("/")
    Call<SearchResponse> getMoviesBySearchAndPage(@Query("apikey") String key, @Query("s") String searchWord,
                                                  @Query("page") int page);

    @GET("/")
    Call<SearchResponse> getMoviesBySearchAndType(@Query("apikey") String key, @Query("s") String searchWord,
                                                         @Query("type") String type);

    @GET("/")
    Call<SearchResponse> getMoviesBySearchAndPageAndType(@Query("apikey") String key, @Query("s") String searchWord,
                                                         @Query("type") String type, @Query("page") int page);

}
