package com.jacob.gameserver.server.service;

import com.jacob.gameserver.matchmaking.Match;
import com.jacob.gameserver.player.Player;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.concurrent.ConcurrentLinkedQueue;

@Service
public class MatchmakingService {

    private final Queue<Player> queue = new ConcurrentLinkedQueue<>();
    private final Set<String> playersInQueue = new HashSet<>();
    private final List<Match> activeMatches = new ArrayList<>();

    public synchronized Match joinQueue(Player player) {

        if(playersInQueue.contains(player.getUsername())) {
            return null;
        }

        for(Player queuedPlayer : queue) {

            int ratingDifference = Math.abs(queuedPlayer.getRating() - player.getRating());

            if(ratingDifference <= 100) {
                queue.remove(queuedPlayer);
                playersInQueue.remove(queuedPlayer.getUsername());

                Match match = new Match(UUID.randomUUID(), queuedPlayer, player);
                activeMatches.add(match);

                return match;
            }
        }

        queue.add(player);
        playersInQueue.add(player.getUsername());

        return null;
    }

    public List<Match> getActiveMatches() {
        return activeMatches;
    }

}
