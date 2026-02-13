package com.jacob.gameserver.controller;

import com.jacob.gameserver.matchmaking.Match;
import com.jacob.gameserver.server.service.MatchmakingService;
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

        return playerService.getPlayer(username).map(player -> {
            Match match = matchmakingService.joinQueue(player);
            return ResponseEntity.ok(match);
        }).orElse(ResponseEntity.notFound().build());

    }
}
