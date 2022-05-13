package com.app.pickamovie.remoteDataSource;

import com.app.pickamovie.model.Movie;
import com.app.pickamovie.model.Search;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class SearchResponse {

    @SerializedName("Search")
    public ArrayList<Movie> search;
    public String totalResults;
    @SerializedName("Response")
    public String response;

    public Search getSearchedMovies()
    {
        return new Search(search, totalResults, response);
    }

    @Override
    public String toString() {
        return "SearchResponse{" +
                "search=" + search +
                ", totalResults='" + totalResults + '\'' +
                ", response='" + response + '\'' +
                '}';
    }
}
