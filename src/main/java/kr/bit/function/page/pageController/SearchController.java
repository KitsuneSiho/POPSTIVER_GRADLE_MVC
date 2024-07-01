package kr.bit.function.page.pageController;


import kr.bit.function.like.LikeService;
import kr.bit.function.member.dto.CustomOAuth2User;
import kr.bit.function.member.dto.GoogleResponse;
import kr.bit.function.member.dto.KakaoResponse;
import kr.bit.function.member.dto.NaverResponse;
import kr.bit.function.page.pageEntity.SearchResult;
import kr.bit.function.page.pageService.SearchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Map;


@Controller
@RequestMapping("/main")
public class SearchController {

    @Autowired
    private SearchService searchService;

    @Autowired
    private LikeService likeService;

    @GetMapping
    public String main_page() {
        return "page/main/main";
    }

    @GetMapping("/search")
    public String search(@RequestParam("keyword") String keyword, Model model, @AuthenticationPrincipal CustomOAuth2User customOAuth2User) {
        List<SearchResult> searchResults = searchService.searchEvents(keyword);

        if (customOAuth2User != null) {
            String userId = getUserId(customOAuth2User);
            for (SearchResult result : searchResults) {
                // isLiked 메소드 대신에 직접 좋아요 여부를 확인하는 로직을 구현합니다.
                result.setIsLiked(checkIsLiked(userId, result.getEvent_no(), result.getEvent_type()));
            }
        }

        model.addAttribute("results", searchResults);
        return "page/searchResult/searchResult";
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

    // 좋아요 여부를 확인하는 메소드를 직접 구현합니다.
    private boolean checkIsLiked(String userId, int eventNo, int eventType) {
        try {
            // LikeService에서 제공하는 다른 메소드를 사용하여 좋아요 여부를 확인합니다.
            // 예를 들어, getLikeCount 메소드를 사용할 수 있습니다.
            int likeCount = likeService.getLikeCount(eventNo, eventType);
            // 좋아요 수가 0보다 크면 좋아요한 것으로 간주합니다.
            return likeCount > 0;
        } catch (Exception e) {
            // 에러 발생 시 기본값으로 false를 반환합니다.
            return false;
        }
    }
}