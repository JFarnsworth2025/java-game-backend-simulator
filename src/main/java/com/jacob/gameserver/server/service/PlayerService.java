package com.jacob.gameserver.server.service;

import com.jacob.gameserver.player.Player;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class PlayerService {

    private final Map<String, Player> players = new HashMap<>();

    public List<Player> getLeaderboard() {
        return players.values().stream().sorted((p1, p2) -> Integer.compare(p2.getRating(), p1.getRating())).toList();
    }

    public Collection<Player> getAllPlayers() {
        return players.values();
    }

    public Player createPlayer(String username, int rating) {
        Player player = new Player(username, rating);
        players.put(username, player);
        return player;
    }

    public Player getPlayer(String username) {
        return players.get(username);
    }

}