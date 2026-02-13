package com.jacob.gameserver.server.service;

import com.jacob.gameserver.matchmaking.Match;
import com.jacob.gameserver.player.Player;
import com.jacob.gameserver.repository.PlayerRepository;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.concurrent.ConcurrentLinkedQueue;

@Service
public class MatchmakingService {

    private final PlayerRepository playerRepository;
    private final Queue<Player> queue = new ConcurrentLinkedQueue<>();
    private final Set<String> playersInQueue = new HashSet<>();
    private final Map<UUID, Match> activeMatches = new HashMap<>();

    public MatchmakingService(PlayerRepository playerRepository) {
        this.playerRepository = playerRepository;
    }

    private static final int K_FACTOR = 32;

    public synchronized Match joinQueue(Player player) {

        for(Match match : activeMatches.values()) {
            if(match.getPlayerOne().getUsername().equals(player.getUsername()) || match.getPlayerTwo().getUsername().equals(player.getUsername())) {
                   return match;
            }
        }

        if(playersInQueue.contains(player.getUsername())) {
            return null;
        }

        for(Player queuedPlayer : queue) {

            int ratingDifference = Math.abs(queuedPlayer.getRating() - player.getRating());
            int dynamicRange = 100 + (queue.size() * 50);

            if(ratingDifference <= dynamicRange) {
                queue.remove(queuedPlayer);
                playersInQueue.remove(queuedPlayer.getUsername());

                Match match = new Match(UUID.randomUUID(), queuedPlayer, player);
                activeMatches.put(match.getMatchId(), match);

                return match;
            }
        }

        queue.add(player);
        playersInQueue.add(player.getUsername());

        return null;
    }

    public Match getMatch(UUID matchId) {
        return activeMatches.get(matchId);
    }

    public Collection<Match> getActiveMatches() {
        return activeMatches.values();
    }

    private void updateRatings(Player winner, Player loser) {

        double expectedWinner = 1.0 / (1 + Math.pow(10, (loser.getRating() - winner.getRating()) / 400.0));

        double expectedLoser = 1.0 / (1 + Math.pow(10, (winner.getRating() - loser.getRating()) / 400.0));

        int newWinnerRating = (int) (winner.getRating() + K_FACTOR * (1 - expectedWinner));

        int newLoserRating = (int) (loser.getRating() + K_FACTOR * (0 - expectedLoser));

        winner.adjustRating(newWinnerRating - winner.getRating());
        loser.adjustRating(newLoserRating - loser.getRating());
    }

    public synchronized Match completeMatch(UUID matchId, String winnerUsername) {
        Match match = activeMatches.get(matchId);

        if(match == null) {
            return null;
        }

        Player playerOne = match.getPlayerOne();
        Player playerTwo = match.getPlayerTwo();

        Player winner;
        Player loser;

        if(playerOne.getUsername().equals(winnerUsername)) {
            winner = playerOne;
            loser = playerTwo;
        } else if(playerTwo.getUsername().equals(winnerUsername)) {
            winner = playerTwo;
            loser = playerOne;
        } else {
            return null;
        }

        updateRatings(winner, loser);
        playerRepository.save(winner);
        playerRepository.save(loser);

        activeMatches.remove(matchId);

        return match;
    }

    public synchronized void reset() {
        queue.clear();
        playersInQueue.clear();
        activeMatches.clear();
    }

}