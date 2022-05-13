package com.app.pickamovie.viewModel;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.app.pickamovie.model.Movie;
import com.app.pickamovie.repository.MovieRepo;

import java.util.List;

public class MovieViewModel extends AndroidViewModel {

    private final MovieRepo repository;

    public MovieViewModel(Application application) {
        super(application);
        repository = MovieRepo.getInstance(application);
    }

    public LiveData<List<Movie>> getAllMovies() {
        return repository.getAllMovies();
    }

    public void insert(final Movie movie) {
        repository.insert(movie);
    }

    public void delete(final Movie movie) {
        repository.delete(movie);
    }

    public void deleteAllMovies() {
        repository.deleteAllMovies();
    }

    public LiveData<Movie> getSearchedMovie() {
        return repository.getSearchedMovie();
    }

    public void searchForMovieByRandomId(int times) {
        for (int i = 0; i < times; i++) {
            repository.searchForMovieRandomId();
        }
    }
}
