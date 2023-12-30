// MoviesViewModel.java
package com.example.myapplication.ui.movies;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;
import com.example.myapplication.database.movies.MovieItem;
import com.example.myapplication.database.movies.MovieRepository;

import java.util.List;

public class MoviesViewModel extends ViewModel {

    private final MovieRepository movieRepository;

    public MoviesViewModel() {
        movieRepository = new MovieRepository();
    }

    public LiveData<List<MovieItem>> getAllMovies() {
        return movieRepository.getAllMovies();
    }

    public LiveData<List<MovieItem>> findMoviesByGenre(String genre) {
        return movieRepository.findMoviesByAttribute("genre", genre);
    }
    // Add methods for specific genres
    public LiveData<List<MovieItem>> getDramaMovies() {
        return movieRepository.findMoviesByAttribute("genre", "Drama");
    }

    public LiveData<List<MovieItem>> getComedyMovies() {
        return movieRepository.findMoviesByAttribute("genre", "Comedy");
    }

    public LiveData<List<MovieItem>> getActionMovies() {
        return movieRepository.findMoviesByAttribute("genre", "Action");
    }

    public LiveData<List<MovieItem>> getRomanceMovies() {
        return movieRepository.findMoviesByAttribute("genre", "Romance");
    }
}
