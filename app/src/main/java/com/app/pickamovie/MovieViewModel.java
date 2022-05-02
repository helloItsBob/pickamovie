package com.app.pickamovie;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

public class MovieViewModel extends ViewModel {

    private MovieRepo repository;

    public MovieViewModel() {
        repository = MovieRepo.getInstance();
    }

    public LiveData<Movie> getSearchedMovie() {
        return repository.getSearchedMovie();
    }

    public void searchForMovie(String title) {
        repository.searchForMovie(title);
    }
}
