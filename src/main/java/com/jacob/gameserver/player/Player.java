package com.jacob.gameserver.player;

public class Player {

    private final String username;
    private int rating;

    public Player(String username, int rating) {
        this.username = username;
        this.rating = rating;
    }

    public String getUsername() {
        return username;
    }

    public int getRating() {
        return rating;
    }

    public void adjustRating(int amount) {
        this.rating += amount;
    }

}
