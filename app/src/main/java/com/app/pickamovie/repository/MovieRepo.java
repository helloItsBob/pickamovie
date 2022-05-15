package com.app.pickamovie.repository;

import android.app.Application;
import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.app.pickamovie.dao.MovieDao;
import com.app.pickamovie.dao.MovieDatabase;
import com.app.pickamovie.model.Movie;
import com.app.pickamovie.remoteDataSource.MovieAPI;
import com.app.pickamovie.remoteDataSource.MovieResponse;
import com.app.pickamovie.remoteDataSource.ServiceGenerator;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.internal.EverythingIsNonNull;

public class MovieRepo {

    private static MovieRepo instance;
    private final MovieDao movieDao;
    private final MutableLiveData<Movie> searchedMovie;
    private final LiveData<List<Movie>> allMovies;
    private final ExecutorService executorService;

    private MovieRepo(Application application) {
        searchedMovie = new MutableLiveData<>();
        MovieDatabase database = MovieDatabase.getInstance(application);
        movieDao = database.movieDao();
        allMovies = movieDao.getAllMovies();
        executorService = Executors.newFixedThreadPool(2);
    }

    public static synchronized MovieRepo getInstance(Application application) {
        if (instance == null) {
            instance = new MovieRepo(application);
        }
        return instance;
    }

    public LiveData<Movie> getSearchedMovie() {
        return searchedMovie;
    }

    public LiveData<List<Movie>> getAllMovies() {
        return allMovies;
    }

    public void insert(Movie movie) {
        executorService.execute(() -> movieDao.insert(movie));
    }

    public void delete(Movie movie) {
        executorService.execute(() -> movieDao.delete(movie));
    }

    public void deleteAllMovies() {
        executorService.execute(movieDao::deleteAllMovies);
    }

    public void searchForMovieRandomId() {
        MovieAPI movieAPI = ServiceGenerator.getMovieAPI();
        Call<MovieResponse> call = movieAPI.getMoviesByRandomId(MovieAPI.KEY3, generateRandomId(), "full");
        call.enqueue(new Callback<MovieResponse>() {
            @EverythingIsNonNull
            @Override
            public void onResponse(Call<MovieResponse> call, Response<MovieResponse> response) {
                Log.i("RESPONSE", response.code() + "");
                Log.i("CALL", call.request().url() + "");
                if (response.isSuccessful() && !response.body().getMovie().getResponse().equals("False")) {
                    searchedMovie.setValue(response.body().getMovie());
                    Log.i("RESPONSE", response.body().getMovie().getResponse());
                } else searchForMovieRandomId();
            }

            @EverythingIsNonNull
            @Override
            public void onFailure(Call<MovieResponse> call, Throwable t) {
                Log.i("RESPONSE", t.getMessage());
                Log.i("RETROFIT", "Something went wrong :(");
                Log.i("WHAT", call.request().toString());
            }
        });
    }

    private String generateRandomId() {
        int min = 1000000;
        int max = 9999999; // for 7-digit id's
        //generate random values from 1000000-9999999
        int randomInt = (int) (Math.random() * (max - min + 1) + min);

        return "tt" + randomInt;
    }
}
