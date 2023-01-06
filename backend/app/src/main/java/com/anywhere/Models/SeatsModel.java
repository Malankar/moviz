package com.anywhere.Models;

import java.util.List;
import java.util.Map;

public class SeatsModel {
    List<String> seats;
    String movie;

    Map<String,List<String>> users;

    public SeatsModel(){

    }

    public SeatsModel(List<String> seats, String movie, Map<String, List<String>> users) {
        this.seats = seats;
        this.movie = movie;
        this.users = users;
    }

    public List<String> getSeats() {
        return seats;
    }

    public void setSeats(List<String> seats) {
        this.seats = seats;
    }

    public String getMovie() {
        return movie;
    }

    public void setMovie(String movie) {
        this.movie = movie;
    }

    public Map<String, List<String>> getUsers() {
        return users;
    }

    public void setUsers(Map<String, List<String>> users) {
        this.users = users;
    }

    @Override
    public String toString() {
        return "SeatsModel{" +
                "seats=" + seats +
                ", movie='" + movie + '\'' +
                ", users=" + users +
                '}';
    }
}
