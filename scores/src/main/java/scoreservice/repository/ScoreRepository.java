package scoreservice.repository;

import scoreservice.entity.ScoreEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ScoreRepository extends JpaRepository<ScoreEntity, Long> {
    ScoreEntity findByUserIdAndQuizId(Long userId, Long quizId);
}
