package com.jacob.gameserver.matchmaking;

import com.jacob.gameserver.player.Player;
import jakarta.persistence.*;

import java.util.UUID;

@Entity
@Table(name="matches")
public class Match {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false)
    private final UUID matchId;

    @ManyToOne
    private final Player playerOne;
    @ManyToOne
    private final Player playerTwo;


    private String winnerUsername;

    private boolean completed;

    public Match(UUID matchId, Player playerOne, Player playerTwo) {
        this.matchId = matchId;
        this.playerOne = playerOne;
        this.playerTwo = playerTwo;
        this.completed = false;
    }

    public Long getId() { return id; }

    public UUID getMatchId() {
        return matchId;
    }

    public Player getPlayerOne() {
        return playerOne;
    }

    public Player getPlayerTwo() {
        return playerTwo;
    }

    public boolean isCompleted() { return completed; }

    public void complete(String winnerUsername) {
        this.winnerUsername = winnerUsername;
        this.completed = true;
    }

    public String getWinnerUsername() {
        return winnerUsername;
    }

}
