// MovieRepository.java
package com.example.myapplication.database.movies;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import java.util.List;

public class MovieRepository {

    private final MovieDatabase movieDatabase;

    public MovieRepository() {
        movieDatabase = new MovieDatabase();
    }

    // Get all movies
    public LiveData<List<MovieItem>> getAllMovies() {
        MutableLiveData<List<MovieItem>> moviesLiveData = new MutableLiveData<>();
        moviesLiveData.postValue(movieDatabase.getAllMovies());
        return moviesLiveData;
    }

    // Filter movies based on attribute and value
    public LiveData<List<MovieItem>> findMoviesByAttribute(String attribute, String value) {
        MutableLiveData<List<MovieItem>> moviesLiveData = new MutableLiveData<>();
        moviesLiveData.postValue(movieDatabase.findMoviesByAttribute(attribute, value));
        return moviesLiveData;
    }
}
