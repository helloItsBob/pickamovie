package com.app.pickamovie;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.internal.EverythingIsNonNull;

public class MovieRepo {

    private static MovieRepo instance;
    private final MutableLiveData<Movie> searchedMovie;

    private MovieRepo() {
        searchedMovie = new MutableLiveData<>();
    }

    public static synchronized MovieRepo getInstance() {
        if (instance == null) {
            instance = new MovieRepo();
        }
        return instance;
    }

    public LiveData<Movie> getSearchedMovie() {
        return searchedMovie;
    }

    public void searchForMovie(String movieTitle) {
        MovieAPI movieAPI = ServiceGenerator.getMovieAPI();
        Call<MovieResponse> call = movieAPI.getMovie(MovieAPI.KEY, movieTitle);
        call.enqueue(new Callback<MovieResponse>() {
            @EverythingIsNonNull
            @Override
            public void onResponse(Call<MovieResponse> call, Response<MovieResponse> response) {
                Log.i("RESPONSE", response.code() + "");
                Log.i("CALL", call.request().url() + "");
                if (response.isSuccessful()) {
                    searchedMovie.setValue(response.body().getMovie());
                    Log.i("RESPONSE", response.body()+"");

                }
            }

            @EverythingIsNonNull
            @Override
            public void onFailure(Call<MovieResponse> call, Throwable t) {
                Log.i("RESPONSE", t.getMessage());
                Log.i("RETROFIT", "Something went wrong :(");
            }
        });
    }
}
