package scoreservice.service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.Map;

@FeignClient(name = "leaderboardservice", url = "${api.internal.leaderboardservice}/v1/leaderboard")
public interface LeaderboardClient {
    @PostMapping("/update")
    void updateLeaderboard(@RequestBody Map<String, Object> updateRequest);
}
