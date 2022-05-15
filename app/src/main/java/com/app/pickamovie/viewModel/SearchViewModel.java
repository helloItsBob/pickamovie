package com.app.pickamovie.viewModel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.app.pickamovie.model.Search;
import com.app.pickamovie.repository.SearchRepo;

public class SearchViewModel extends ViewModel {

    private final SearchRepo repository;

    public SearchViewModel() {
        repository = SearchRepo.getInstance();
    }

    public LiveData<Search> getMoviesBySearch() {
        return repository.getMovieSearched();
    }

    public void searchForMovieByWords(String searchString) {
        repository.searchByWords(searchString);
    }

    public void searchForMovieByWordsAndPage(String searchString, int page) {
        repository.searchByWordsAndPage(searchString, page);
    }

    public void searchForMovieByWordsAndType(String searchString, String type) {
        repository.searchByWordsAndType(searchString, type);
    }

    public void searchForMovieByWordsAndPageAndType(String searchString, String type, int page) {
        repository.searchByWordsAndPageAndType(searchString, type, page);
    }
}
