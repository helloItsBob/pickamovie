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


//    private String rated;
//    private String released;
//    private String runtime;
//    private String genre;
//    private String Director;
//    private String Writer;
//    private String Actors;
//    private String Plot;
//    private String Language;
//    private String Country;
//    private String Awards;
//    private String Poster;
//    private ArrayList<Rating> Ratings;
//    private String Metascore;
//    private String imdbVotes;
//    private String Type;
//    private String DVD;
//    private String BoxOffice;
//    private String Production;
//    private String Website;

    public Movie getMovie()
    {
        return new Movie(movieId, title, year, response, poster, type, plot, imdbRating);
    }
//, year, rated, released, runtime, genre
//    , Director, Writer, Actors,
//    Plot, Language, Country, Awards, Poster, Ratings, Metascore, imdbRating,
//    imdbVotes, imdbID, Type, DVD, BoxOffice, Production, Website, Response
}
