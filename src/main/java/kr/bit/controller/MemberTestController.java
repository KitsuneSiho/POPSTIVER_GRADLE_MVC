package kr.bit.controller;

import kr.bit.function.member.dto.CustomOAuth2User;
import kr.bit.function.member.dto.GoogleResponse;
import kr.bit.function.member.dto.KakaoResponse;
import kr.bit.function.member.dto.NaverResponse;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Map;

@Controller
public class MemberTestController {

    // 로그인 후 가입 정보 작성 페이지로 이동
    @RequestMapping("/join_information")
    public String joinInformation(@AuthenticationPrincipal CustomOAuth2User customOAuth2User, Model model) {

        // 사용자 속성(attributes)을 가져옴
        Object attribute = customOAuth2User.getAttributes();
        // 각 제공자별로 적절한 응답 객체를 생성하여 정보 추출
        String provider = customOAuth2User.getProvider();
        switch (provider) {
            case "google":
                GoogleResponse googleResponse = new GoogleResponse((Map<String, Object>) attribute);
                model.addAttribute("name", googleResponse.getName());
                model.addAttribute("email", googleResponse.getEmail());
                break;
            case "kakao":
                KakaoResponse kakaoResponse = new KakaoResponse((Map<String, Object>) attribute);
                model.addAttribute("name", kakaoResponse.getName());
                model.addAttribute("gender", kakaoResponse.getGender());
                model.addAttribute("birthday", kakaoResponse.getBirthday());
                model.addAttribute("birthYear", kakaoResponse.getBirthYear());
                model.addAttribute("email", kakaoResponse.getEmail());
                break;
            case "naver":
                NaverResponse naverResponse = new NaverResponse((Map<String, Object>) attribute);
                model.addAttribute("name", naverResponse.getName());
                model.addAttribute("gender", naverResponse.getGender());
                String firstbirthday = naverResponse.getBirthday();
                String birthday = "";
                if (firstbirthday != null) {
                    birthday = firstbirthday.replace("-", "");
                }
                model.addAttribute("birthday", birthday);
                model.addAttribute("birthYear", naverResponse.getBirthYear());
                model.addAttribute("email", naverResponse.getEmail());
                break;
            default:
                // 예외 처리: 지원하지 않는 제공자인 경우
                return "error"; // 예시로 간단히 에러 페이지 반환
        }

        return "page/join_information";
    }
}
