package com.jacob.gameserver.server.service;

import com.jacob.gameserver.player.Player;
import com.jacob.gameserver.repository.PlayerRepository;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class PlayerService {

    private final PlayerRepository playerRepository;

    public PlayerService(PlayerRepository playerRepository) {
        this.playerRepository = playerRepository;
    }

    public Player createPlayer(String username, int rating) {

        if(playerRepository.findByUsername(username).isPresent()) {
            throw new RuntimeException("Username already exists");
        }

        Player player = new Player(username, rating);
        return playerRepository.save(player);
    }

    public Optional<Player> getPlayer(String username) {
        return playerRepository.findByUsername(username);
    }

    public List<Player> getAllPlayers() {
        return playerRepository.findAll();
    }

    public List<Player> getLeaderboard() {
        return playerRepository.findAllByOrderByRatingDesc();
    }
}