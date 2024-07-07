package scoreservice.service;

import scoreservice.entity.ScoreEntity;
import scoreservice.repository.ScoreRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class ScoreService {
    @Autowired
    private ScoreRepository scoreRepository;

    @Autowired
    private LeaderboardClient leaderboardClient;

    public ScoreEntity saveScore(ScoreEntity newScore) {
        ScoreEntity existingScore = scoreRepository.findByUserIdAndQuizId(newScore.getUserId(), newScore.getQuizId());

        if (existingScore != null) {
            existingScore.setScore(existingScore.getScore() + newScore.getScore());
            newScore = existingScore;
        } else {
            newScore.setScore(calculateScore(newScore));
        }

        ScoreEntity savedScore = scoreRepository.save(newScore);

        leaderboardClient.updateLeaderboard(Map.of(
                "userId", savedScore.getUserId(),
                "quizId", savedScore.getQuizId(),
                "score", savedScore.getScore()
        ));

        return savedScore;
    }


    private int calculateScore(ScoreEntity score) {
        return score.getScore();
    }
}
