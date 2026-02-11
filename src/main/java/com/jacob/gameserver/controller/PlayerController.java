package com.jacob.gameserver.controller;

import com.jacob.gameserver.player.Player;
import com.jacob.gameserver.server.service.PlayerService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class PlayerController {

    private final PlayerService playerService;

    public PlayerController(PlayerService playerService) {
        this.playerService = playerService;
    }

   @PostMapping("/players")
    public ResponseEntity<Player> createPlayer(@RequestParam String username) {
       return ResponseEntity.ok(playerService.createPlayer(username));
   }

   @GetMapping("/players")
    public ResponseEntity<Player> getPlayer(@RequestParam String username) {
        Player player = playerService.getPlayer(username);

        if(player == null) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(player);
   }
}