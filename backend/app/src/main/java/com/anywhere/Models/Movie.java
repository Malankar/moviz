package com.anywhere.Models;

import java.util.*;

public class Movie {
    private String title;
    private String description;
    private String releaseDate;
    private List<String> genre;
    private double rating;
    private String director;
    private String cast;

    private String imageUrl;

//    List<String> languages;

    public Movie(){

    }
    public Movie(String title, String description,String imageUrl, String releaseDate, Double rating, String director, String[] genre, String cast) {
        this.title = title;
        this.description = description;
        this.imageUrl =imageUrl;
        this.releaseDate = releaseDate;
        this.rating = Math.round(rating * 10) / 10.0f;
        this.director = director;
//        this.languages= Arrays.asList(lang);
        this.genre = Arrays.asList(genre);
        this.cast = cast;
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

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public void setReleaseDate(String releaseDate) {
        this.releaseDate = releaseDate;
    }

    public List<String> getGenre() {
        return genre;
    }

    public void setGenre(List<String> genre) {
        this.genre = genre;
    }

    public double getRating() {
        return rating;
    }


    public String getDirector() {
        return director;
    }

    public void setDirector(String director) {
        this.director = director;
    }

    public void setRating(double rating) {
        this.rating = rating;
    }

//    public List<String> getLanguages() {
//        return languages;
//    }
//
//    public void setLanguages(List<String> languages) {
//        this.languages = languages;
//    }

    public String getCast() {
        return cast;
    }

    public void setCast(String cast) {
        this.cast = cast;
    }

    @Override
    public String toString() {
        return "Movie{" +
                "title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", imageUrl='" + imageUrl + '\'' +
                ", releaseDate='" + releaseDate + '\'' +
                ", genre='" + genre + '\'' +
                ", rating=" + rating +
                ", director='" + director + '\'' +
                ", cast='" + cast + '\'' +
//                ", languages=" + languages +
                '}';
    }
}
