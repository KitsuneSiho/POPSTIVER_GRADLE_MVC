package kr.bit.function.like;

import kr.bit.function.member.dto.CustomOAuth2User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/like")
public class LikeController {
    @Autowired
    private LikeService likeService;

    @PostMapping("/toggle")
    public ResponseEntity<Map<String, Object>> toggleLike(@RequestBody Map<String, Integer> payload,
                                                          @AuthenticationPrincipal CustomOAuth2User customOAuth2User) {
        String userId = customOAuth2User.getName(); // OAuth2 제공자에 따라 적절한 메서드 사용
        String userName = customOAuth2User.getAttribute("name"); // 사용자 이름 가져오기

        int eventNo = payload.get("event_no");
        int eventType = payload.get("event_type");

        boolean isLiked = likeService.toggleLike(userId, userName, eventNo, eventType);
        int likeCount = likeService.getLikeCount(eventNo, eventType);

        Map<String, Object> response = new HashMap<>();
        response.put("isLiked", isLiked);
        response.put("likeCount", likeCount);
        return ResponseEntity.ok(response);
    }
}
