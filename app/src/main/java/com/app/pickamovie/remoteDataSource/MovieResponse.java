package com.app.pickamovie.remoteDataSource;

import com.app.pickamovie.model.Movie;
import com.google.gson.annotations.SerializedName;

public class MovieResponse {

    @SerializedName("imdbID")
    private String movieId;
    @SerializedName("Title")
    private String title;
    @SerializedName("Year")
    private String year;
    @SerializedName("Response")
    private String response;
    @SerializedName("Poster")
    private String poster;
    @SerializedName("Type")
    private String type;
    @SerializedName("Plot")
    private String plot;
    @SerializedName("imdbRating")
    private String imdbRating;

    public Movie getMovie()
    {
        return new Movie(movieId, title, year, response, poster, type, plot, imdbRating);
    }
}
