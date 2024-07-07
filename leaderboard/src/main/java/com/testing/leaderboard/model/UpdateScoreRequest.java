package com.testing.leaderboard.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UpdateScoreRequest {
    private Long userId;
    private Long quizId;
    private int score;

}
