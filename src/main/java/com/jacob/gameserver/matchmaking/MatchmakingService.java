package com.jacob.gameserver.matchmaking;

import com.jacob.gameserver.player.Player;

import java.util.LinkedList;
import java.util.Queue;

public class MatchmakingService {

    private final Queue<Player> queue = new LinkedList<>();

    public void joinQueue(Player player) {
        if(!player.isOnline()) {
            throw new IllegalStateException("Player must be online to join matchmaking.");
        }

        queue.add(player);
        System.out.println(player.getUsername() + " joined matchmaking.");
    }

    public void attemptMatch() {
        if(queue.size() < 2) {
            System.out.println("Not enough players to create a match.");
            return;
        }

        Player p1 = queue.poll();
        Player p2 = queue.poll();

        System.out.println("Match created between: ");
        System.out.println(" - " + p1.getUsername());
        System.out.println(" - " + p2.getUsername());
    }

    public int getQueueSize() {
        return queue.size();
    }
}
