package com.anywhere.Models;

import java.util.HashMap;
import java.util.Map;

public class Movie {
    private String title;
    private String description;
    private String releaseDate;
    private String genre;
    private float rating;
    private String director;
    private String cast;

    public Movie(){

    }
    public Movie(String title, String description, String releaseDate, String genre, float rating, String director, String cast) {
        this.title = title;
        this.description = description;
        this.releaseDate = releaseDate;
        this.genre = genre;
        this.rating = Math.round(rating * 10) / 10.0f;
        this.director = director;
        this.cast = cast;
    }
    public Map<String, Object> toMap() {
        Map<String, Object> map = new HashMap<>();
        map.put("title", title);
        map.put("description", description);
        map.put("releaseDate", releaseDate);
        map.put("genre", genre);
        map.put("rating", rating);
        map.put("director", director);
        map.put("cast", cast);
        return map;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(String releaseDate) {
        this.releaseDate = releaseDate;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public float getRating() {
        return rating;
    }

    public void setRating(float rating) {
        this.rating = rating;
    }

    public String getDirector() {
        return director;
    }

    public void setDirector(String director) {
        this.director = director;
    }

    public String getCast() {
        return cast;
    }

    public void setCast(String cast) {
        this.cast = cast;
    }
}
