package com.jacob.gameserver.player;

import java.util.UUID;

public class Player {

    private final UUID id;
    private String username;
    private int rating;
    private boolean online;

    public Player(String username, int i) {
        this.id = UUID.randomUUID();
        this.username = username;
        this.rating = 1000;
        this.online = false;
    }

    public UUID getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public int getRating() {
        return rating;
    }

    public boolean isOnline() {
        return online;
    }

    public void setOnline(boolean online) {
        this.online = online;
    }

    public void updateRating(int newRating) {
        this.rating = newRating;
    }

    @Override
    public String toString() {
        return "Player{" +
                "id=" + id +
                ", rating=" + rating +
                ", online=" + online +
                "}";
    }
}
