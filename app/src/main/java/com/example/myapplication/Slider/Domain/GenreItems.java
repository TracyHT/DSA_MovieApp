package com.example.myapplication.Slider.Domain;

public class GenreItems {
    private String name;
    private Integer id;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public GenreItems(String name, Integer id) {
        this.name = name;
        this.id = id;
    }
}
