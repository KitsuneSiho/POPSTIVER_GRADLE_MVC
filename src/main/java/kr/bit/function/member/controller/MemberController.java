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
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
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
                               @RequestParam("user_type") String userType,
                               @RequestParam("user_nickName") String userNickname,
                               @AuthenticationPrincipal CustomOAuth2User customOAuth2User) {

            String provider = customOAuth2User.getProvider();
            Object attribute = customOAuth2User.getAttributes();
            String user_id = "";

            switch (provider) {
                case "google":
                    GoogleResponse googleResponse = new GoogleResponse((Map<String, Object>) attribute);
                    user_id = "google" + googleResponse.getProviderId();
                    break;
                case "kakao":
                    KakaoResponse kakaoResponse = new KakaoResponse((Map<String, Object>) attribute);
                    user_id = "kakao" + kakaoResponse.getProviderId();
                    break;
                case "naver":
                    NaverResponse naverResponse = new NaverResponse((Map<String, Object>) attribute);
                    user_id = "naver" + naverResponse.getProviderId();
                    break;
            }

            MemberEntity user = new MemberEntity();
            user.setUser_type(userType);
            user.setUser_email(userEmail);
            user.setUser_name(userName);
            user.setUser_birth(userBirth);
            user.setUser_gender(userGender);
            user.setUser_id(user_id);
            user.setUser_nickname(userNickname);
            memberService.saveUser(user);

            return "redirect:/main";
        }

        @GetMapping("/myPage")
        public String myPage(@AuthenticationPrincipal CustomOAuth2User customOAuth2User, Model model) {
            String provider = customOAuth2User.getProvider();
            Object attribute = customOAuth2User.getAttributes();
            String user_id = "";

            switch (provider) {
                case "google":
                    GoogleResponse googleResponse = new GoogleResponse((Map<String, Object>) attribute);
                    user_id = "google" + googleResponse.getProviderId();
                    break;
                case "kakao":
                    KakaoResponse kakaoResponse = new KakaoResponse((Map<String, Object>) attribute);
                    user_id = "kakao" + kakaoResponse.getProviderId();
                    break;
                case "naver":
                    NaverResponse naverResponse = new NaverResponse((Map<String, Object>) attribute);
                    user_id = "naver" + naverResponse.getProviderId();
                    break;
            }

            MemberEntity user = memberService.findById(user_id);
            model.addAttribute("user", user);

            return "page/myPage/myPage";
        }

        @PutMapping("/updateUser")
        @ResponseBody
        public ResponseEntity<String> updateUser(@RequestBody MemberEntity updatedUser, @AuthenticationPrincipal CustomOAuth2User customOAuth2User) {
            String provider = customOAuth2User.getProvider();
            Object attribute = customOAuth2User.getAttributes();
            String user_id = "";

            switch (provider) {
                case "google":
                    GoogleResponse googleResponse = new GoogleResponse((Map<String, Object>) attribute);
                    user_id = "google" + googleResponse.getProviderId();
                    break;
                case "kakao":
                    KakaoResponse kakaoResponse = new KakaoResponse((Map<String, Object>) attribute);
                    user_id = "kakao" + kakaoResponse.getProviderId();
                    break;
                case "naver":
                    NaverResponse naverResponse = new NaverResponse((Map<String, Object>) attribute);
                    user_id = "naver" + naverResponse.getProviderId();
                    break;
            }

            MemberEntity existingUser = memberService.findById(user_id);
            if (existingUser != null) {
                existingUser.setUser_type(updatedUser.getUser_type());
                existingUser.setUser_nickname(updatedUser.getUser_nickname());
                memberService.updateUserInfo(existingUser);
                return ResponseEntity.ok("User information updated successfully.");
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not found.");
            }
        }

        // 닉네임 중복 확인 엔드포인트 추가
        @PostMapping("/checkNickname")
        @ResponseBody
        public ResponseEntity<Map<String, Boolean>> checkNickname(@RequestParam("nickname") String nickname) {
            boolean isAvailable = !memberService.existsByNickname(nickname);
            Map<String, Boolean> response = Map.of("available", isAvailable);
            return ResponseEntity.ok(response);
        }

        @GetMapping("/getUserInfo")
        @ResponseBody
        public MemberEntity getUserInfo(@AuthenticationPrincipal CustomOAuth2User customOAuth2User) {
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
            return memberService.findById(userId);
        }

        @DeleteMapping("/deleteUser")
        public ResponseEntity<String> deleteUser(@AuthenticationPrincipal CustomOAuth2User customOAuth2User, HttpSession session, HttpServletRequest request, HttpServletResponse response) {
            if (customOAuth2User != null) {
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

                // 디버깅용 로그 추가
                System.out.println("탈퇴 요청을 받은 사용자 ID: " + userId);

                if (userId != null) {
                    try {
                        memberService.deleteUserByEmail(userId);
                        refreshTokenRepository.deleteRefreshTokensByUsername(userId);
                        session.invalidate();
                        clearAllCookies(request, response);
                        SecurityContextHolder.clearContext();
                        return ResponseEntity.ok("회원 탈퇴가 완료되었습니다.");
                    } catch (Exception e) {
                        // 예외 발생 시 로그 출력
                        e.printStackTrace();
                        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("회원 탈퇴 중 오류가 발생했습니다.");
                    }
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
