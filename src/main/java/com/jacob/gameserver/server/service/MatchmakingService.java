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

        queue.add(player);
        playersInQueue.add(player.getUsername());

        if(queue.size() >= 2) {
            Player playerOne = queue.poll();
            Player playerTwo = queue.poll();

            playersInQueue.remove(playerOne.getUsername());
            playersInQueue.remove(playerTwo.getUsername());

            Match match = new Match(UUID.randomUUID(), playerOne, playerTwo);
            activeMatches.add(match);

            return match;
        }

        return null;
    }

    public List<Match> getActiveMatches() {
        return activeMatches;
    }

}
