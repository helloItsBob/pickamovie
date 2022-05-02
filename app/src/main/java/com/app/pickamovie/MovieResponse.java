package com.app.pickamovie;

import com.google.gson.annotations.SerializedName;

public class MovieResponse {
    @SerializedName("Title")
    private String title;
    @SerializedName("Year")
    private String year;
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
//    private String imdbRating;
//    private String imdbVotes;
//    private String imdbID;
//    private String Type;
//    private String DVD;
//    private String BoxOffice;
//    private String Production;
//    private String Website;
//    private String Response;

    public Movie getMovie()
    {
        return new Movie(title, year);
    }
//, year, rated, released, runtime, genre
//    , Director, Writer, Actors,
//    Plot, Language, Country, Awards, Poster, Ratings, Metascore, imdbRating,
//    imdbVotes, imdbID, Type, DVD, BoxOffice, Production, Website, Response
}
