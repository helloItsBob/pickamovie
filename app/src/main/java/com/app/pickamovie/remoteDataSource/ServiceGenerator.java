package com.app.pickamovie.remoteDataSource;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ServiceGenerator {

    private static MovieAPI movieAPI;

    // request timeout
    final static OkHttpClient okHttpClient = new OkHttpClient.Builder()
            .connectTimeout(60, TimeUnit.SECONDS)
            .writeTimeout(60, TimeUnit.SECONDS)
            .readTimeout(60, TimeUnit.SECONDS)
            .build();

    public static MovieAPI getMovieAPI() {
        if (movieAPI == null) {
            movieAPI = new Retrofit.Builder()
                    .baseUrl(MovieAPI.URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .client(okHttpClient)
                    .build()
                    .create(MovieAPI.class);
        }
        return movieAPI;
    }
}
