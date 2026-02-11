package com.jacob.gameserver.controller;

import com.jacob.gameserver.server.service.MatchmakingService;
import com.jacob.gameserver.player.Player;
import com.jacob.gameserver.matchmaking.Match;
import com.jacob.gameserver.server.service.PlayerService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/matchmaking")
public class MatchmakingController {

    private final MatchmakingService matchmakingService;
    private final PlayerService playerService;

    public MatchmakingController(MatchmakingService matchmakingService, PlayerService playerService) {
        this.matchmakingService = matchmakingService;
        this.playerService = playerService;
    }

    @PostMapping("/join")
    public ResponseEntity<?> joinQueue(@RequestParam String username) {

        Player player = playerService.getPlayer(username);

        if(player == null) {
            return ResponseEntity.notFound().build();
        }

        Match match = matchmakingService.joinQueue(player);

        if(match == null) {
            return ResponseEntity.ok("Joined Queue or already waiting...");
        }

        return ResponseEntity.ok(match);
    }
}
