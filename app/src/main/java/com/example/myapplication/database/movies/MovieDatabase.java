/*  GROUP 17
    Ngo Le Thien An - ITITDK21030
    Huynh Thanh Thuy - ITITIU21325
    Cao Hoang Khoi Nguyen - ITITDK21048
    Nguyen Dinh Thang - ITITIU21309
    Purpose: This Java class represents a movie database, storing information about movies such as title, genre, duration, director, cast, synopsis, image URL, and rating. It includes methods for adding movies, retrieving all movies, and finding movies based on specific attributes like title or genre.
*/
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
        addMovie(new MovieItem(3, "The Shawshank Redemption", "Drama", 150,"Morgan Freeman", new String[]{"Actor 1"}, "Synopsis. In 1947, Andy Dufresne (Tim Robbins), a banker in Maine, is convicted of murdering his wife and her lover, a golf pro. Since the state of Maine has no death penalty, he is given two consecutive life sentences and sent to the notoriously harsh Shawshank Prison.", "https://moviesapi.ir/images/tt0111161_poster.jpg", 4));
        addMovie(new MovieItem(4, "Chicken Run: Dawn of the Nugget", "Comedy", 102, "Zachary Levi", new String[]{"Actor 1"}, "A band of fearless chickens flock together to save poultry-kind from an unsettling new threat: a nearby farm that's cooking up something suspicious.", "https://upload.wikimedia.org/wikipedia/en/b/b7/Chicken_run_dawn_of_the_nugget.jpg", 4));
        addMovie(new MovieItem(5, "The Godfather", "Drama", 200, "Francis Ford Coppola", new String[]{"Actor 1"}, "It is the first installment in The Godfather trilogy, chronicling the Corleone family under patriarch Vito Corleone (Brando) from 1945 to 1955. It focuses on the transformation of his youngest son, Michael Corleone (Pacino), from reluctant family outsider to ruthless mafia boss.", "https://m.media-amazon.com/images/M/MV5BM2MyNjYxNmUtYTAwNi00MTYxLWJmNWYtYzZlODY3ZTk3OTFlXkEyXkFqcGdeQXVyNzkwMjQ5NzM@._V1_FMjpg_UX1000_.jpg", 5));
        addMovie(new MovieItem(6, "The Dark Knight", "Drama", 170, "Christian Bale", new String[]{"Actor 1"}, "The plot follows the vigilante Batman, police lieutenant James Gordon, and district attorney Harvey Dent, who form an alliance to dismantle organized crime in Gotham City. Their efforts are derailed by the Joker, an anarchistic mastermind who seeks to test how far Batman will go to save the city from chaos.", "https://moviesapi.ir/images/tt0468569_poster.jpg", 4));
        addMovie(new MovieItem(7,"Fast and Furious 1", "Action",120,"James Wan", new String[]{"Actor 1"},"Fast & Furious (also known as The Fast and the Furious) is an American media franchise centered on a series of action films that are largely concerned with street racing, heists, spies, and family. The franchise also includes short films, a television series, toys, video games, live shows, and theme park attractions.","https://cdn.tuoitre.vn/ttc/r/2021/07/12/poster-qua-nhanh-qua-nguy-hiem-phan-9-1626069151.jpg",4));
        addMovie(new MovieItem(8,"Fast and Furious 2", "Action",120,"James Wan", new String[]{"Actor 1"},"Fast & Furious (also known as The Fast and the Furious) is an American media franchise centered on a series of action films that are largely concerned with street racing, heists, spies, and family. The franchise also includes short films, a television series, toys, video games, live shows, and theme park attractions.","https://play-lh.googleusercontent.com/v66ZA_nbkTfqilquWbKfB43SOmMcyHoz44sb1HMNqfIeEwmSsghqtmkEbPzZ7qoStrM=w240-h480-rw",4));
        addMovie(new MovieItem(9,"Through My Window", "Romance",104,"Marçal Forès", new String[]{"Actor 1"},"Raquel's longtime crush on her next-door neighbor turns into something more when he starts developing feelings for her, despite his family's objections. Watch all you want. Julio Peña and Clara Galle star in this adaptation of Ariana Godoy's popular YA novel, also featuring Pilar Castro.","https://m.media-amazon.com/images/M/MV5BMmNkYWMwZjQtNDQyZC00OTgxLTk0ZWEtNmMwNzQ1MmQ2ODYyXkEyXkFqcGdeQXVyMTEzMTI1Mjk3._V1_FMjpg_UX1000_.jpg",4));
        addMovie(new MovieItem(10, "Leo", "Comedy", 102, "Lokesh Kanagaraj", new String[]{"Actor 1"}, "Leos are the natural leaders of the zodiac, as magnificent and striking as the Lion that embodies their sign. Leos are radiantly joyful, liberal with their appeal and endowments. They are fiercely proud and confident. They love and live life to the fullest rather than being in charge at home, work, and play.", "https://m.media-amazon.com/images/M/MV5BZDNiZWI3ZmItMjY4OS00Mjk5LThlNjktMmMwZDM1OTEwOGVmXkEyXkFqcGdeQXVyMDc5ODIzMw@@._V1_QL75_UX190_CR0,0,190,281_.jpg", 4));
        addMovie(new MovieItem(11,"365 Days", "Romance",104,"Tomasz Mandez", new String[]{"Actor 1"},"A woman falls victim to a dominant mafia boss, who imprisons her and gives her one year to fall in love with him. Watch all you want. This erotic drama is based on the bestselling novel \"365 dni\" by author Blanka Lipinska.","https://upload.wikimedia.org/wikipedia/en/3/32/365_Dni_film_poster.png",4));
        addMovie(new MovieItem(12, "The Super Mario Bros", "Comedy", 102, "Aaron Horvath", new String[]{"Actor 1"}, "The film features an origin story for the brothers Mario and Luigi, Italian-American plumbers who are transported to an alternate world and become entangled in a battle between the Mushroom Kingdom, led by Princess Peach, and the Koopas, led by Bowser.", "https://m.media-amazon.com/images/M/MV5BOTJhNzlmNzctNTU5Yy00N2YwLThhMjQtZDM0YjEzN2Y0ZjNhXkEyXkFqcGdeQXVyMTEwMTQ4MzU5._V1_FMjpg_UX1000_.jpg", 4));
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

    public MovieItem findMovieById(int id) {
        for (MovieItem movie : allMovies) {
            if (movie.getId() == id) {
                return movie;
            }
        }
        return null; // Return null if the movie with the given id is not found
    }

    // You can add more search methods for different attributes as needed
}

