package com.app.pickamovie.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import com.app.pickamovie.model.Movie;

import java.util.List;

@Dao
public interface MovieDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insert(Movie movie);

    @Update
    void update(Movie movie);

    @Delete
    void delete(Movie movie);

    @Query("DELETE FROM movie_table")
    void deleteAllMovies();

    @Query("SELECT * FROM movie_table ORDER BY title ASC") // sort alphabetically
    LiveData<List<Movie>> getAllMovies();

    @Query("SELECT EXISTS (SELECT 1 FROM movie_table WHERE id == :movieId)")
    boolean exists (String movieId);
}
