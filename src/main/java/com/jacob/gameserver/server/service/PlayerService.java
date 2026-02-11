package com.jacob.gameserver.server.service;

import com.jacob.gameserver.player.Player;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class PlayerService {

    private final Map<String, Player> players = new HashMap<>();

    public Player createPlayer(String username, int rating) {
        Player player = new Player(username, rating);
        players.put(username, player);
        return player;
    }

    public Player getPlayer(String username) {
        return players.get(username);
    }

}