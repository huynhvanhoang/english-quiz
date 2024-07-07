package scoreservice.controller;

import scoreservice.entity.ScoreEntity;
import scoreservice.service.ScoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/v1/score")
public class ScoreController {
    @Autowired
    private ScoreService scoreService;

    @PostMapping("/update")
    public ResponseEntity<ScoreEntity> updateScore(@RequestBody ScoreEntity score) {
        ScoreEntity updatedScore = scoreService.saveScore(score);
        return ResponseEntity.ok(updatedScore);
    }
}
