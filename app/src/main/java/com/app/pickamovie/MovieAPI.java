package com.app.pickamovie;

// http://www.omdbapi.com/?apikey=29fd92e6&t=us // example
// --- >>> 29fd92e6 <<< --- API key

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface MovieAPI {

    String URL = "https://www.omdbapi.com";
    String KEY = "29fd92e6";

    @GET("/")
    Call<MovieResponse> getMovie(@Query("apikey") String key, @Query("t") String title);
}
