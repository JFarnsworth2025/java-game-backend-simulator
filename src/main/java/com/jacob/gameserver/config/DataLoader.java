package com.jacob.gameserver.config;

import com.jacob.gameserver.server.service.PlayerService;
import org.springframework.stereotype.Component;

@Component
public class DataLoader {

    private final PlayerService playerService;

    public DataLoader(PlayerService playerService) {
        this.playerService = playerService;
    }

    public void run(String... args) {
        playerService.createPlayer("Alice", 1000);
        playerService.createPlayer("Bob", 1050);
        playerService.createPlayer("Charlie", 1500);

        System.out.println("Test players loaded.");
    }

}
