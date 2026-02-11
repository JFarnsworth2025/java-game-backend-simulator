package com.jacob.gameserver.server;

import com.jacob.gameserver.player.Player;
import com.jacob.gameserver.player.PlayerRegistry;

public class GameServerApplication {

    public static void main(String[] args) {
        System.out.println("Game Server Backend Starting...");

        PlayerRegistry registry = new PlayerRegistry();

        Player p1 = registry.registerPlayer("ShadowHunter");
        p1.setOnline(true);

        System.out.println("All players:");
        registry.getAllPlayers().forEach(System.out::println);
    }

}
