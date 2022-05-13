package com.app.pickamovie.repository;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.app.pickamovie.model.Search;
import com.app.pickamovie.remoteDataSource.MovieAPI;
import com.app.pickamovie.remoteDataSource.SearchResponse;
import com.app.pickamovie.remoteDataSource.ServiceGenerator;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.internal.EverythingIsNonNull;

public class SearchRepo {

    private static SearchRepo instance;
    private final MutableLiveData<Search> searchedMovie;

    private SearchRepo() {
        searchedMovie = new MutableLiveData<>();
    }

    public static synchronized SearchRepo getInstance() {
        if (instance == null) {
            instance = new SearchRepo();
        }
        return instance;
    }

    public LiveData<Search> getMovieSearched() {
        return searchedMovie;
    }

    public void searchByWords(String searchWord) {
        MovieAPI movieAPI = ServiceGenerator.getMovieAPI();
        Call<SearchResponse> call = movieAPI.getMoviesBySearch(MovieAPI.KEY, searchWord);
        call.enqueue(new Callback<SearchResponse>() {
            @EverythingIsNonNull
            @Override
            public void onResponse(Call<SearchResponse> call, Response<SearchResponse> response) {
                Log.i("RESPONSE", response.code() + "");
                Log.i("CALL", call.request().url() + "");
                if (response.isSuccessful()) {
                    searchedMovie.setValue(response.body().getSearchedMovies());
                    Log.i("RESPONSE", response.body().search+ "");

                }
            }

            @EverythingIsNonNull
            @Override
            public void onFailure(Call<SearchResponse> call, Throwable t) {
                Log.i("RESPONSE", t.getMessage());
                Log.i("RETROFIT", "Something went wrong :(");
            }
        });
    }

}
