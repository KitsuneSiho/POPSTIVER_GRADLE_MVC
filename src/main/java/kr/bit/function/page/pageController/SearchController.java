package kr.bit.function.page.pageController;

import kr.bit.function.like.BookmarkDTO;
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
import java.util.Set;
import java.util.stream.Collectors;

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
            List<BookmarkDTO> likedEvents = likeService.getLikedEvents(userId);

            // 좋아요한 이벤트의 ID와 타입을 Set으로 만듭니다.
            Set<String> likedEventSet = likedEvents.stream()
                    .map(event -> event.getEvent_no() + "-" + event.getEvent_type())
                    .collect(Collectors.toSet());

            for (SearchResult result : searchResults) {
                // 현재 검색 결과가 좋아요한 이벤트 목록에 있는지 확인합니다.
                boolean isLiked = likedEventSet.contains(result.getEvent_no() + "-" + result.getEvent_type());
                result.setIsLiked(isLiked);
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
}