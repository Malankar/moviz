package com.anywhere.Models;

import java.util.Arrays;
import java.util.List;

public class SeatsModel {
    List<String> seats;
    String movie;

    public SeatsModel(){

    }
    public SeatsModel(String[] seats, String movie) {
        this.seats = Arrays.asList(seats);
        this.movie = movie;
    }

    public List<String> getSeats() {
        return seats;
    }

    public String getMovie() {
        return movie;
    }
}
