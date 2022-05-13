package com.app.pickamovie.model;

import java.util.ArrayList;

public class Search {

    private ArrayList<Movie> searchMovie;
    private String totalResults;
    private String response;

    public Search(ArrayList<Movie> searchMovie, String totalResults, String response)
    {
        this.searchMovie = searchMovie;
        this.totalResults = totalResults;
        this.response = response;
    }

    public ArrayList<Movie> getSearchMovie() {
        return searchMovie;
    }

    public String getTotalResults() {
        return totalResults;
    }

    public String getResponse() {
        return response;
    }
}
