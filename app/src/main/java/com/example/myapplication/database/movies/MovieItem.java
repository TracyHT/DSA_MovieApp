package com.example.myapplication.database.movies;

public class MovieItem {
    private int id;
    private String title;
    private String genre;
    private int durationMinutes;
    private String director;
    private String[] actors;
    private String synopsis;

    private String imageUrl;

    // Constructors, getters, and setters
    // Getter and setter for id
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    // Getter and setter for title
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    // Getter and setter for genre
    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    // Getter and setter for durationMinutes
    public int getDurationMinutes() {
        return durationMinutes;
    }

    public void setDurationMinutes(int durationMinutes) {
        this.durationMinutes = durationMinutes;
    }

    // Getter and setter for director
    public String getDirector() {
        return director;
    }

    public void setDirector(String director) {
        this.director = director;
    }

    // Getter and setter for actors
    public String[] getActors() {
        return actors;
    }

    public void setActors(String[] actors) {
        this.actors = actors;
    }

    // Getter and setter for synopsis
    public String getSynopsis() {
        return synopsis;
    }

    public void setSynopsis(String synopsis) {
        this.synopsis = synopsis;
    }

    // Getter and setter for imageUrl
    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String synopsis) {
        this.imageUrl = synopsis;
    }
    // Constructor with basic information
    public MovieItem(int id, String title, String genre, int durationMinutes, String director, String[] actors, String synopsis, String imageUrl) {
        this.id = id;
        this.title = title;
        this.genre = genre;
        this.durationMinutes = durationMinutes;
        this.imageUrl = imageUrl;
        this.actors = actors;
        this.director = director;
        this.synopsis = synopsis;
    }

    // Additional methods as needed
}
