package com.jacob.gameserver.matchmaking;

import com.jacob.gameserver.player.Player;

import java.util.UUID;

public class Match {

    private final UUID matchId;
    private final Player playerOne;
    private final Player playerTwo;

    public Match(Player playerOne, Player playerTwo) {
        this.matchId = UUID.randomUUID();
        this.playerOne = playerOne;
        this.playerTwo = playerTwo;
    }

    public UUID getMatchId() {
        return matchId;
    }

    public Player getPlayerOne() {
        return playerOne;
    }

    public Player getPlayerTwo() {
        return playerTwo;
    }

    @Override
    public String toString() {
        return "Match{" +
                "matchId=" + matchId +
                ", playerOne=" + playerOne.getUsername() +
                ", playerTwo=" + playerTwo.getUsername() +
                '}';
    }
}
