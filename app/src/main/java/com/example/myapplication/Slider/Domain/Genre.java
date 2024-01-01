package com.example.myapplication.Slider.Domain;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;


public class Genre {
    private List<GenreItems> genre;

    public List<GenreItems> getGenre() {
        return genre;
    }

    public void setGenre(List<GenreItems> genre) {
        this.genre = genre;
    }
}
