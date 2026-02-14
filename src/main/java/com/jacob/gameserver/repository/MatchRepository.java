package com.jacob.gameserver.repository;

import com.jacob.gameserver.matchmaking.Match;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface MatchRepository extends JpaRepository<Match, Long> {

    Optional<Match> findByMatchId(UUID matchId);

    List<Match> findByCompletedFalse();
    List<Match> findByCompletedTrue();
    List<Match> findByPlayerOneUsernameOrPlayerTwoUsername(String username1, String username2);

}
