package kr.bit.function.like;

import kr.bit.function.member.dto.CustomOAuth2User;
import kr.bit.function.member.dto.GoogleResponse;
import kr.bit.function.member.dto.KakaoResponse;
import kr.bit.function.member.dto.NaverResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/like")
public class LikeController {
    @Autowired
    private LikeService likeService;

    @PostMapping("/toggle")
    public ResponseEntity<Map<String, Object>> toggleLike(@RequestBody Map<String, String> payload,
                                                          @AuthenticationPrincipal CustomOAuth2User customOAuth2User) {
        System.out.println("Received like toggle request");
        System.out.println("Payload: " + payload);

        if (customOAuth2User == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(Map.of("error", "User not authenticated"));
        }

        String userId = payload.get("user_id");
        String userName = payload.get("user_name");

        String eventNoStr = payload.get("event_no");
        String eventTypeStr = payload.get("event_type");

        if (eventNoStr == null || eventTypeStr == null || eventNoStr.isEmpty() || eventTypeStr.isEmpty()) {
            return ResponseEntity.badRequest().body(Map.of("error", "Invalid event_no or event_type"));
        }

        int eventNo;
        int eventType;

        try {
            eventNo = Integer.parseInt(eventNoStr);
            eventType = Integer.parseInt(eventTypeStr);
        } catch (NumberFormatException e) {
            return ResponseEntity.badRequest().body(Map.of("error", "Invalid event_no or event_type format"));
        }

        System.out.println("User ID: " + userId);
        System.out.println("User Name: " + userName);
        System.out.println("Event No: " + eventNo);
        System.out.println("Event Type: " + eventType);

        try {
            boolean isLiked = likeService.toggleLike(userId, userName, eventNo, eventType);
            int likeCount = likeService.getLikeCount(eventNo, eventType);

            System.out.println("Is Liked: " + isLiked);
            System.out.println("Like Count: " + likeCount);

            Map<String, Object> response = new HashMap<>();
            response.put("isLiked", isLiked);
            response.put("likeCount", likeCount);
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(Map.of("error", "Internal Server Error"));
        }
    }

    private String getUserId(CustomOAuth2User customOAuth2User) {
        String provider = customOAuth2User.getProvider();
        Object attribute = customOAuth2User.getAttributes();
        String userId = "";

        switch (provider) {
            case "google":
                GoogleResponse googleResponse = new GoogleResponse((Map<String, Object>) attribute);
                userId = "google" + googleResponse.getProviderId();
                break;
            case "kakao":
                KakaoResponse kakaoResponse = new KakaoResponse((Map<String, Object>) attribute);
                userId = "kakao" + kakaoResponse.getProviderId();
                break;
            case "naver":
                NaverResponse naverResponse = new NaverResponse((Map<String, Object>) attribute);
                userId = "naver" + naverResponse.getProviderId();
                break;
        }
        return userId;
    }

    private String getUserName(CustomOAuth2User customOAuth2User) {
        String provider = customOAuth2User.getProvider();
        Object attribute = customOAuth2User.getAttributes();
        String userName = "";

        switch (provider) {
            case "google":
                GoogleResponse googleResponse = new GoogleResponse((Map<String, Object>) attribute);
                userName = googleResponse.getName();
                break;
            case "kakao":
                KakaoResponse kakaoResponse = new KakaoResponse((Map<String, Object>) attribute);
                userName = kakaoResponse.getName();
                break;
            case "naver":
                NaverResponse naverResponse = new NaverResponse((Map<String, Object>) attribute);
                userName = naverResponse.getName();
                break;
        }
        return userName;
    }


    @GetMapping("/user-likes")
    public ResponseEntity<List<Map<String, Object>>> getUserLikes(@AuthenticationPrincipal CustomOAuth2User customOAuth2User) {
        String userId = getUserId(customOAuth2User);
        List<BookmarkDTO> likes = likeService.getLikedEvents(userId);
        List<Map<String, Object>> simplifiedLikes = likes.stream()
                .map(like -> {
                    Map<String, Object> simplifiedLike = new HashMap<>();
                    simplifiedLike.put("event_no", like.getEvent_no());
                    simplifiedLike.put("event_type", like.getEvent_type());
                    return simplifiedLike;
                })
                .collect(Collectors.toList());
        return ResponseEntity.ok(simplifiedLikes);
    }
}