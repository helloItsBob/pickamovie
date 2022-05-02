package com.app.pickamovie;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ServiceGenerator {

    private static MovieAPI movieAPI;

    public static MovieAPI getMovieAPI() {
        if (movieAPI == null) {
            movieAPI = new Retrofit.Builder()
                    .baseUrl(MovieAPI.URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()
                    .create(MovieAPI.class);
        }
        return movieAPI;
    }
}
