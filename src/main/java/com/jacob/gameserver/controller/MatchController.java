package com.jacob.gameserver.controller;

import com.jacob.gameserver.matchmaking.Match;
import com.jacob.gameserver.server.service.MatchmakingService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/matches")
public class MatchController {

    private final MatchmakingService matchmakingService;

    public MatchController(MatchmakingService matchmakingService) {
        this.matchmakingService = matchmakingService;
    }

    @PostMapping("/{matchId}/complete")
    public ResponseEntity<?> completeMatch(@PathVariable UUID matchId, @RequestParam String winner) {

        Match match = matchmakingService.completeMatch(matchId, winner);

        if(match == null) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok("Match completed and ratings updated.");
    }

    @GetMapping
    public ResponseEntity<?> getActiveMatches() {
        return ResponseEntity.ok(matchmakingService.getActiveMatches());
    }

    @PostMapping("/reset")
    public ResponseEntity<?> resetSystem() {
        matchmakingService.reset();
        return ResponseEntity.ok("System reset.");
    }

}
