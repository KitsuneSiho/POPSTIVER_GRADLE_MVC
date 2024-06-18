package kr.bit.controller;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class FlowController {

    @GetMapping("/")//"/" 경로로 들어가면 일단 메인페이지로 리다이렉트 시킨다.
    public String home() {
        return "page/main";
    }

    @GetMapping("/main")//메인페이지
    public String main_page() {
        return "page/main";
    }

    @GetMapping("/login")//로그인페이지
    public String login_page() {
        return "page/login";
    }

    @GetMapping("/contact")//연락처 페이지
    public String contact_page() {
        return "page/contact";
    }

    @GetMapping("/myPage")//마이페이지
    public String my_page(@AuthenticationPrincipal OAuth2User principal, Model model) {
        if (principal != null) {
            // OAuth2User로부터 사용자 정보를 가져와 모델에 추가
            model.addAttribute("userName", principal.getAttribute("name"));
            model.addAttribute("userEmail", principal.getAttribute("email"));
            // 필요한 다른 속성도 추가
        }
        return "page/myPage";
    }

    @GetMapping("/map")//지도페이지
    public String map_page() {
        return "page/map";
    }
}
