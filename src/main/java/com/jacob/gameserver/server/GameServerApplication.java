package com.jacob.gameserver.server;

import com.jacob.gameserver.matchmaking.MatchmakingService;
import com.jacob.gameserver.player.Player;
import com.jacob.gameserver.player.PlayerRegistry;

public class GameServerApplication {

    public static void main(String[] args) {
        System.out.println("Game Server Backend Starting...");

        PlayerRegistry registry = new PlayerRegistry();
        MatchmakingService matchmaking = new MatchmakingService();

        Player p1 = registry.registerPlayer("ShadowHunter");
        Player p2 = registry.registerPlayer("NovaStrike");
        Player p3 = registry.registerPlayer("LowRankPlayer");

        p1.setOnline(true);
        p2.setOnline(true);
        p3.setOnline(true);

        p1.updateRating(1000);
        p2.updateRating(1100);
        p3.updateRating(800);

        matchmaking.joinQueue(p1);
        matchmaking.joinQueue(p2);
        matchmaking.joinQueue(p3);

        matchmaking.attemptMatch();
    }

}
