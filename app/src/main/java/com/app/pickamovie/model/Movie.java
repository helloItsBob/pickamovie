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

    // constructor for get methods
    public Movie(@NonNull String id, String title, String year, String response, String poster,
                 String type, String plot, String imdbRating) {
        this.id = id;
        this.title = title;
        this.year = year;
        this.response = response;
        this.poster = poster;
        this.type = type;
        this.plot = plot;
        this.imdbRating = imdbRating;
        this.isFavorite = false;
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

    // to transfer bundle
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
