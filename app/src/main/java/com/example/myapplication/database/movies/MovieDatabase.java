package com.example.myapplication.database.movies;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class MovieDatabase {
    private List<MovieItem> allMovies;

    public MovieDatabase() {
        this.allMovies = new ArrayList<>();
        addMovie(new MovieItem(1,"Drifting Home", "Action",120,"Hiroyasu Ishida", new String[]{"Actor 1"},"Kosuke and Natsume, sixth-grade students, play in an apartment building that will soon be destroyed over the summer holiday. They get evolved and entangled in a bizarre phenomenon. All they can see is a vast sea all around them.","https://upload.wikimedia.org/wikipedia/en/thumb/f/f9/Drifting_Home_Poster.jpg/220px-Drifting_Home_Poster.jpg",4));
        addMovie(new MovieItem(2,"Love Again", "Romance",104,"James C. Strouse", new String[]{"Actor 1"},"Something","https://m.media-amazon.com/images/M/MV5BNjQwZDIyNjAtZGQxMC00OTUwLWFiOWYtNzg2NDc5Mjc1MDQ5XkEyXkFqcGdeQXVyMTAxNzQ1NzI@._V1_.jpg",4));

    }
    public void addMovie(MovieItem movie) {
        allMovies.add(movie);
    }

    public List<MovieItem> getAllMovies() {
        return new ArrayList<>(allMovies);
    }

    // Method to find movies based on a specified attribute and value
    public List<MovieItem> findMoviesByAttribute(String attribute, String value) {
        List<MovieItem> foundMovies = new ArrayList<>();
        for (MovieItem movie : allMovies) {
            switch (attribute.toLowerCase()) {
                case "title":
                    if (movie.getTitle().equalsIgnoreCase(value)) {
                        foundMovies.add(movie);
                    }
                    break;
                case "genre":
                    if (movie.getGenre().equalsIgnoreCase(value)) {
                        foundMovies.add(movie);
                    }
                    break;
                // Add more cases for other attributes as needed
            }
        }
        return foundMovies;
    }

    // You can add more search methods for different attributes as needed
}

