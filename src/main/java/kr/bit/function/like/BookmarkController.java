package kr.bit.function.like;

import kr.bit.function.member.dto.CustomOAuth2User;
import kr.bit.function.member.dto.GoogleResponse;
import kr.bit.function.member.dto.KakaoResponse;
import kr.bit.function.member.dto.NaverResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/bookmark")
public class BookmarkController {
    @Autowired
    private LikeService likeService;

    @GetMapping
    public String showBookmarks(Model model, @AuthenticationPrincipal CustomOAuth2User customOAuth2User) {
        if (customOAuth2User == null) {
            return "redirect:/login";
        }

        String userId = getUserId(customOAuth2User);

        List<BookmarkDTO> likedEvents = likeService.getLikedEvents(userId);

        LocalDate today = LocalDate.now();

        List<BookmarkDTO> upcomingEvents = new ArrayList<>();
        List<BookmarkDTO> ongoingEvents = new ArrayList<>();
        List<BookmarkDTO> endedEvents = new ArrayList<>();

        for (BookmarkDTO event : likedEvents) {
            try {
                if (event.getStartDate() != null && event.getEndDate() != null) {
                    LocalDate startDate = LocalDate.parse(event.getStartDate());
                    LocalDate endDate = LocalDate.parse(event.getEndDate());

                    if (startDate.isAfter(today)) {
                        upcomingEvents.add(event);
                    } else if (endDate.isBefore(today)) {
                        endedEvents.add(event);
                    } else {
                        ongoingEvents.add(event);
                    }
                } else {
                    // 날짜 정보가 없는 경우 처리
                    ongoingEvents.add(event); // 또는 다른 적절한 처리
                }
            } catch (Exception e) {
                System.out.println("Error parsing date for event: " + event);
                e.printStackTrace();
            }
        }

        model.addAttribute("upcomingEvents", upcomingEvents);
        model.addAttribute("ongoingEvents", ongoingEvents);
        model.addAttribute("endedEvents", endedEvents);

        return "page/myPage/bookmark";
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
}