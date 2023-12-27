package com.example.myapplication.database.movies;
import com.github.javafaker.Faker;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class MovieDatabase {
    private List<MovieItem> allMovies;

    public MovieDatabase() {
        this.allMovies = new ArrayList<>();

        for (int i = 0; i < 20; i++) { //generateRandomMovie
            MovieItem randomMovie = generateRandomMovie();
            addMovie(randomMovie);
        }
    }

    // Helper method to generate a random movie using Faker library
    private static MovieItem generateRandomMovie() {
        Faker faker = new Faker();
        int id = faker.number().randomDigitNotZero();
        String title = faker.book().title();
        String genre = faker.book().genre();
        int durationMinutes = faker.number().numberBetween(60, 180);
        String director = faker.name().fullName();
        String[] actors = {faker.name().fullName(), faker.name().fullName(), faker.name().fullName()};
        String synopsis = faker.lorem().paragraph();
        String imageUrl = faker.internet().image();

        return new MovieItem(id, title, genre, durationMinutes, director, actors, synopsis, imageUrl);
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

