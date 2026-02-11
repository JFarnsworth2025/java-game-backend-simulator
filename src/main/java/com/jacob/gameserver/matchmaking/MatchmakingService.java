package com.jacob.gameserver.matchmaking;

import com.jacob.gameserver.player.Player;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class MatchmakingService {

    private final List<Player> queue = new ArrayList<>();
    private static final int RATING_THRESHOLD = 100;

    public Match joinQueue(Player player) {
        if(!player.isOnline()) {
            throw new IllegalStateException("Player must be online to join matchmaking.");
        }

        queue.add(player);
        System.out.println(player.getUsername() + " joined matchmaking");
        return null;
    }

    public Match attemptMatch() {
        for(int i = 0; i < queue.size(); i++) {
            Player p1 = queue.get(i);

            for(int j = i + 1; j < queue.size(); j++) {
                Player p2 = queue.get(j);

                if(Math.abs(p1.getRating() - p2.getRating()) <= RATING_THRESHOLD) {
                    queue.remove(p1);
                    queue.remove(p2);

                    Match match = new Match(UUID.randomUUID(), p1, p2);
                    System.out.println("Match Created:" + match);
                    return match;
                }
            }
        }

        System.out.println("No suitable match found.");
        return null;
    }

    public int getQueueSize() {
        return queue.size();
    }
}
