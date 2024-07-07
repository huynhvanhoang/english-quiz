package com.testing.leaderboard.repository;

import com.testing.leaderboard.model.LeaderboardEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LeaderboardRepository extends JpaRepository<LeaderboardEntity, Long> {

    List<LeaderboardEntity> findByQuizIdOrderByScoreDesc(Long quizId);

    LeaderboardEntity findByUserIdAndQuizId(Long userId, Long quizId);

    @Query("SELECT COUNT(l) + 1 FROM LeaderboardEntity l WHERE l.quizId = ?1 AND l.score > ?2")
    int calculateRank(Long quizId, int score);

}
