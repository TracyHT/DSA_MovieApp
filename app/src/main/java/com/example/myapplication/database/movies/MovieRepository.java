/*  GROUP 17
    Ngo Le Thien An - ITITDK21030
    Huynh Thanh Thuy - ITITIU21325
    Cao Hoang Khoi Nguyen - ITITDK21048
    Nguyen Dinh Thang - ITITIU21309
    Purpose: This Java class represents a movie repository, acting as an intermediary between the application and the `MovieDatabase`. It provides methods to retrieve all movies and filter movies based on specific attributes, returning the results as LiveData for real-time data observation.
*/
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
