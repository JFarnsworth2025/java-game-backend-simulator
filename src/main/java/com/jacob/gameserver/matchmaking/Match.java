package com.jacob.gameserver.matchmaking;

import com.jacob.gameserver.player.Player;
import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name="matches")
public class Match {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false)
    private UUID matchId;

    @ManyToOne(fetch = FetchType.EAGER)
    private Player playerOne;
    @ManyToOne(fetch = FetchType.EAGER)
    private Player playerTwo;

    protected Match() {}

    private String winnerUsername;
    private LocalDateTime createdAt;
    private LocalDateTime completedAt;
    private boolean completed;

    public Match(UUID matchId, Player playerOne, Player playerTwo) {
        this.matchId = matchId;
        this.playerOne = playerOne;
        this.playerTwo = playerTwo;
        this.completed = false;
        this.createdAt = LocalDateTime.now();
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

    public LocalDateTime getCreatedAt() { return createdAt; }
    public LocalDateTime getCompletedAt() { return completedAt; }

    public void complete(String winnerUsername) {
        this.winnerUsername = winnerUsername;
        this.completed = true;
        this.completedAt = LocalDateTime.now();
    }

    public String getWinnerUsername() {
        return winnerUsername;
    }

}
