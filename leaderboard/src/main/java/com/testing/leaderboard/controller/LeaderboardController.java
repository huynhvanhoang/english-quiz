package com.testing.leaderboard.controller;

import com.testing.leaderboard.model.LeaderboardEntity;
import com.testing.leaderboard.model.UpdateScoreRequest;
import com.testing.leaderboard.service.LeaderboardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/leaderboard")
class LeaderboardController {

    @Autowired
    private LeaderboardService leaderboardService;

    @PostMapping("/update")
    public LeaderboardEntity updateLeaderboard(@RequestBody UpdateScoreRequest request) {
        return leaderboardService.updateLeaderboard(request.getUserId(), request.getQuizId(), request.getScore());
    }

    @GetMapping("/{quizId}")
    public List<LeaderboardEntity> getLeaderboard(@PathVariable Long quizId) {
        List<LeaderboardEntity> leaderboard = leaderboardService.getLeaderboard(quizId);
        updateRanks(leaderboard);
        return leaderboard;
    }

    private void updateRanks(List<LeaderboardEntity> leaderboard) {
        int rank = 1;
        for (LeaderboardEntity entity : leaderboard) {
            entity.setRank(rank++);
        }
    }



}
