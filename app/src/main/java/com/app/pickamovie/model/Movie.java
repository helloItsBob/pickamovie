package com.app.pickamovie.model;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.google.gson.annotations.SerializedName;

@Entity(tableName = "movie_table")
public class Movie implements Parcelable {

    @PrimaryKey()
    @NonNull
    @SerializedName("imdbID")
    private final String id;
    @SerializedName("Title")
    private final String title;
    @SerializedName("Year")
    private final String year;
    private String response;
    @SerializedName("Poster")
    private final String poster;
    @SerializedName("Type")
    private final String type;
    private String plot;
    private String imdbRating;

    private boolean isFavorite;
//    private final String rated;
//    private final String released;
//    private final String runtime;
//    private final String genre;
//    private final String Director;
//    private final String Writer;
//    private final String Actors;
//    private final String Plot;
//    private final String Language;
//    private final String Country;
//    private final String Awards;
//    private final String Poster;
//    private final ArrayList<Rating> Ratings;
//    private final String Metascore;
//    private final String imdbVotes;
//    private final String imdbID;
//    private final String Type;
//    private final String DVD;
//    private final String BoxOffice;
//    private final String Production;
//    private final String Website;
//    private final String Response;

//, String director, String writer, String actors, String plot,
//    String language, String country, String awards, String poster,
//    ArrayList<Rating> ratings, String metascore, String imdbRating, String imdbVotes,
//    String imdbID, String type, String DVD, String boxOffice, String production,
//    String website, String response

    // constructor for get methods
    public Movie(@NonNull String id, String title, String year,//, String rated, String released, String runtime,String genre
                 String response, String poster, String type, String plot, String imdbRating) {
        this.id = id;
        this.title = title;
        this.year = year;
        this.response = response;
        this.poster = poster;
        this.type = type;
        this.plot = plot;
        this.imdbRating = imdbRating;
        this.isFavorite = false;

//        this.rated = rated;
//        this.released = released;
//        this.runtime = runtime;
//        this.genre = genre;
//        this.Director = director;
//        this.Writer = writer;
//        this.Actors = actors;
//        this.Plot = plot;
//        this.Language = language;
//        this.Country = country;
//        this.Awards = awards;
//        this.Ratings = ratings;
//        this.Metascore = metascore;
//        this.imdbRating = imdbRating;
//        this.imdbVotes = imdbVotes;
//        this.imdbID = imdbID;
//        this.Type = type;
//        this.DVD = DVD;
//        this.BoxOffice = boxOffice;
//        this.Production = production;
//        this.Website = website;
//        this.Response = response;
    }

    // constructor for search
    public Movie(String title, String year, @NonNull String imdbID, String type, String poster)
    {
        this.title = title;
        this.year = year;
        this.id = imdbID;
        this.type = type;
        this.poster = poster;
    }

    protected Movie(Parcel in) {
        id = in.readString();
        title = in.readString();
        year = in.readString();
        response = in.readString();
        poster = in.readString();
        type = in.readString();
        plot = in.readString();
        imdbRating = in.readString();
    }

    public static final Creator<Movie> CREATOR = new Creator<Movie>() {
        @Override
        public Movie createFromParcel(Parcel in) {
            return new Movie(in);
        }

        @Override
        public Movie[] newArray(int size) {
            return new Movie[size];
        }
    };

    @NonNull
    public String getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getYear() {
        return year;
    }

    public String getResponse() {
        return response;
    }

    public String getPoster() {
        return poster;
    }

    public String getType() {
        return type;
    }

    public String getPlot() {
        return plot;
    }

    public String getImdbRating() {
        return imdbRating;
    }

    public boolean isFavorite() {
        return isFavorite;
    }

    public void setFavorite(boolean favorite) {
        isFavorite = favorite;
    }

    //    public String getRated() {
//        return rated;
//    }
//
//    public String getReleased() {
//        return released;
//    }
//
//    public String getRuntime() {
//        return runtime;
//    }
//
//    public String getGenre() {
//        return genre;
//    }

//    public String getDirector() {
//        return Director;
//    }
//
//    public String getWriter() {
//        return Writer;
//    }
//
//    public String getActors() {
//        return Actors;
//    }
//
//    public String getPlot() {
//        return Plot;
//    }
//
//    public String getLanguage() {
//        return Language;
//    }
//
//    public String getCountry() {
//        return Country;
//    }
//
//    public String getAwards() {
//        return Awards;
//    }
//
//    public String getPoster() {
//        return Poster;
//    }
//
//    public ArrayList<Rating> getRatings() {
//        return Ratings;
//    }
//
//    public String getMetascore() {
//        return Metascore;
//    }
//
//
//    public String getImdbVotes() {
//        return imdbVotes;
//    }
//
//    public String getImdbID() {
//        return imdbID;
//    }
//
//    public String getType() {
//        return Type;
//    }
//
//    public String getDVD() {
//        return DVD;
//    }
//
//    public String getBoxOffice() {
//        return BoxOffice;
//    }
//
//    public String getProduction() {
//        return Production;
//    }
//
//    public String getWebsite() {
//        return Website;
//    }
//
//    public String getResponse() {
//        return Response;
//    }


    @Override
    public String toString() {
        return "Movie{" +
                "title='" + title + '\'' +
                ", year='" + year + '\'' +
                '}';
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(id);
        parcel.writeString(title);
        parcel.writeString(year);
        parcel.writeString(response);
        parcel.writeString(poster);
        parcel.writeString(type);
        parcel.writeString(plot);
        parcel.writeString(imdbRating);
    }
}
