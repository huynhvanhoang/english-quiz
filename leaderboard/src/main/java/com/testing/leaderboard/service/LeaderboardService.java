package com.testing.leaderboard.service;

import com.testing.leaderboard.model.LeaderboardEntity;
import com.testing.leaderboard.repository.LeaderboardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class LeaderboardService {

    @Autowired
    private LeaderboardRepository leaderboardRepository;

    @Transactional
    public LeaderboardEntity updateLeaderboard(Long userId, Long quizId, int score) {
        LeaderboardEntity leaderboardEntity = leaderboardRepository.findByUserIdAndQuizId(userId, quizId);

        if (leaderboardEntity == null) {
            leaderboardEntity = new LeaderboardEntity();
            leaderboardEntity.setUserId(userId);
            leaderboardEntity.setQuizId(quizId);
        }

        leaderboardEntity.setScore(score);
        leaderboardEntity.setRank(calculateRank(quizId, score));
        leaderboardEntity.setUpdatedAt(LocalDateTime.now());

        return leaderboardRepository.save(leaderboardEntity);
    }

    public List<LeaderboardEntity> getLeaderboard(Long quizId) {
        return leaderboardRepository.findByQuizIdOrderByScoreDesc(quizId);
    }

    private int calculateRank(Long quizId, int score) {
        return leaderboardRepository.calculateRank(quizId, score);
    }
}
