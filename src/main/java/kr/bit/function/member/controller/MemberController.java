package kr.bit.function.member.controller;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import kr.bit.function.member.dto.CustomOAuth2User;
import kr.bit.function.member.dto.GoogleResponse;
import kr.bit.function.member.dto.KakaoResponse;
import kr.bit.function.member.dto.NaverResponse;
import kr.bit.function.member.entity.MemberEntity;
import kr.bit.function.member.repository.RefreshTokenRepository;
import kr.bit.function.member.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.Map;

@RequestMapping("/member")
@Controller
public class MemberController {

    @Autowired
    private MemberService memberService;

    @Autowired
    private RefreshTokenRepository refreshTokenRepository;


    @PostMapping("/saveUser")
    public String saveUser(@RequestParam("user_email") String userEmail,
                           @RequestParam("user_name") String userName,
                           @RequestParam("user_birth") String userBirth,
                           @RequestParam("user_gender") String userGender,
                           @RequestParam("user_type") int userType,
                           @RequestParam("user_nickName") String userNickname,
                           @AuthenticationPrincipal CustomOAuth2User customOAuth2User) {

        String provider = customOAuth2User.getProvider();
        Object attribute = customOAuth2User.getAttributes();
        String user_id = "";

        switch (provider) {
            case "google":
                GoogleResponse googleResponse = new GoogleResponse((Map<String, Object>) attribute);
                user_id = googleResponse.getProviderId();
                break;
            case "kakao":
                KakaoResponse kakaoResponse = new KakaoResponse((Map<String, Object>) attribute);
                user_id = kakaoResponse.getProviderId();
                break;
            case "naver":
                NaverResponse naverResponse = new NaverResponse((Map<String, Object>) attribute);
                user_id = naverResponse.getProviderId();
                break;
        }
        // 회원 정보 저장
        MemberEntity user = new MemberEntity();
        user.setUser_type(userType); // user_type 필드 추가
        user.setUser_email(userEmail);
        user.setUser_name(userName);
        user.setUser_birth(userBirth);
        user.setUser_gender(userGender);
        user.setUser_id(user_id);
        user.setUser_nickname(userNickname);
        System.out.println(user_id);
        memberService.saveUser(user);

        // 저장 후에 어디로 이동할지 리다이렉트 경로를 반환
        return "redirect:/main";
    }

    // 유저 정보 수정
    @PutMapping("/updateUser")
    @ResponseBody
    public void updateUser(@RequestBody MemberEntity user, @AuthenticationPrincipal CustomOAuth2User customOAuth2User) {

        String provider = customOAuth2User.getProvider();
        Object attribute = customOAuth2User.getAttributes();
        String user_id = "";

        switch (provider) {
            case "google":
                GoogleResponse googleResponse = new GoogleResponse((Map<String, Object>) attribute);
                user_id = googleResponse.getProviderId();
                break;
            case "kakao":
                KakaoResponse kakaoResponse = new KakaoResponse((Map<String, Object>) attribute);
                user_id = kakaoResponse.getProviderId();
                break;
            case "naver":
                NaverResponse naverResponse = new NaverResponse((Map<String, Object>) attribute);
                user_id = naverResponse.getProviderId();
                break;
        }
        System.out.println("수정할 아이디 : " + user_id);
        System.out.println("수정된 이메일 : " + user.getUser_email());
        memberService.updateUserInfo(user_id, user.getUser_email(), user.getUser_nickname());
    }

    // 내 정보 페이지에서 사용자 정보 가져오는 것
    @GetMapping("/getUserInfo")
    @ResponseBody
    public MemberEntity getUserInfo(@AuthenticationPrincipal CustomOAuth2User customOAuth2User) {
        // 세션에서 로그인한 사용자의 정보를 가져옴
        String provider = customOAuth2User.getProvider();
        Object attribute = customOAuth2User.getAttributes();
        String userId = "";
        switch (provider) {
            case "google":
                GoogleResponse googleResponse = new GoogleResponse((Map<String, Object>) attribute);
                userId = googleResponse.getProviderId();
                break;
            case "kakao":
                KakaoResponse kakaoResponse = new KakaoResponse((Map<String, Object>) attribute);
                userId = kakaoResponse.getProviderId();
                break;
            case "naver":
                NaverResponse naverResponse = new NaverResponse((Map<String, Object>) attribute);
                userId = naverResponse.getProviderId();
                break;
        }
        System.out.println("토큰에서 받아온 사용자 아이디: " + userId);
        MemberEntity user = memberService.findById(userId); // 아이디를 기준으로 사용자 정보 조회
        return user;
    }

    // 사용자 삭제
    @DeleteMapping("/deleteUser")
    public ResponseEntity<String> deleteUser(@AuthenticationPrincipal CustomOAuth2User customOAuth2User, HttpSession session, HttpServletRequest request, HttpServletResponse response) {
        if (customOAuth2User != null) {
            String provider = customOAuth2User.getProvider();
            Object attribute = customOAuth2User.getAttributes();
            String userId = "";

            switch (provider) {
                case "google":
                    userId = new GoogleResponse((Map<String, Object>) attribute).getProviderId();
                    break;
                case "kakao":
                    userId = new KakaoResponse((Map<String, Object>) attribute).getProviderId();
                    break;
                case "naver":
                    userId = new NaverResponse((Map<String, Object>) attribute).getProviderId();
                    break;
            }

            if (userId != null) {
                // DB에서 사용자 삭제
                memberService.deleteUserByEmail(userId);

                // Refresh 토큰 삭제
                refreshTokenRepository.deleteRefreshTokensByUsername(userId);

                // 세션 무효화
                session.invalidate();

                // 모든 쿠키 삭제
                clearAllCookies(request, response);

                // 인증 정보 제거
                SecurityContextHolder.clearContext();

                return ResponseEntity.ok("회원 탈퇴가 완료되었습니다.");
            }
        }
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("로그인 정보가 없습니다.");
    }

    private void clearAllCookies(HttpServletRequest request, HttpServletResponse response) {
        Cookie[] cookies = request.getCookies();
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                cookie.setValue(null);
                cookie.setMaxAge(0);
                cookie.setPath("/");
                response.addCookie(cookie);
            }
        }
    }
}