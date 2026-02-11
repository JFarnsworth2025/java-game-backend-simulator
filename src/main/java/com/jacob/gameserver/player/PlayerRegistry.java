package com.jacob.gameserver.player;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class PlayerRegistry {

    private final Map<UUID, Player> players = new HashMap<>();

    public Player registerPlayer(String username) {
        Player player = new Player(username, 1000);
        players.put(player.getId(), player);
        return player;
    }

    public Player getPlayer(UUID id) {
        return players.get(id);
    }

    public Collection<Player> getAllPlayers() {
        return players.values();
    }

    public void removePlayer(UUID id) {
        players.remove(id);
    }

}
