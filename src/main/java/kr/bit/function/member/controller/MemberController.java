package kr.bit.function.member.controller;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import jakarta.servlet.jsp.tagext.Tag;
import kr.bit.function.member.dto.CustomOAuth2User;
import kr.bit.function.member.dto.GoogleResponse;
import kr.bit.function.member.dto.KakaoResponse;
import kr.bit.function.member.dto.NaverResponse;
import kr.bit.function.member.entity.MemberEntity;
import kr.bit.function.member.entity.TagEntity;
import kr.bit.function.member.entity.UserTagEntity;
import kr.bit.function.member.repository.RefreshTokenRepository;
import kr.bit.function.member.repository.TagRepository;
import kr.bit.function.member.repository.UserTagRepository;
import kr.bit.function.member.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RequestMapping("/member")
@Controller
public class MemberController {

    @Autowired
    private MemberService memberService;

    @Autowired
    private RefreshTokenRepository refreshTokenRepository;

    @Autowired
    private  UserTagRepository userTagRepository;

    @Autowired
    private TagRepository tagRepository;

        @PostMapping("/saveUser")
    public String saveUser(@RequestParam("user_email") String userEmail,
                           @RequestParam("user_name") String userName,
                           @RequestParam("user_birth") String userBirth,
                           @RequestParam("user_gender") String userGender,
                           @RequestParam("user_type") String userType,
                           @RequestParam("user_nickName") String userNickname,
                           @RequestParam("tags") String tags,
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
        memberService.saveUser(user, tags);

            // 태그 저장
            String[] tagArray = tags.split(",");
            List<Integer> tagList = Arrays.stream(tagArray).map(Integer::parseInt).collect(Collectors.toList());
            userTagRepository.saveUserTags(user_id, tagList);

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

        // 사용자 정보 모델에 추가
        MemberEntity user = memberService.findById(user_id);
        if (user != null) {
            model.addAttribute("user", user);
        } else {
            model.addAttribute("errorMessage", "사용자 정보를 불러오지 못했습니다.");
        }

        // 태그 목록을 모델에 추가
        List<TagEntity> tags = tagRepository.findAllTags();
        if (tags.isEmpty()) {
            model.addAttribute("errorMessage", "태그를 불러오지 못했습니다. 관리자에게 문의하세요.");
        } else {
            model.addAttribute("tags", tags);
        }

        // 사용자 선택 태그 목록을 모델에 추가
        List<UserTagEntity> userTags = userTagRepository.findByUserId(user_id);
        List<Integer> selectedTagIds = userTags.stream().map(UserTagEntity::getTag_no).collect(Collectors.toList());
        model.addAttribute("selectedTagIds", selectedTagIds);


        return "page/myPage/myPage";
    }

    @PutMapping("/updateUser")
    @ResponseBody
    public ResponseEntity<String> updateUser(@RequestBody Map<String, Object> payload, @AuthenticationPrincipal CustomOAuth2User customOAuth2User) {
        String user_nickName = (String) payload.get("user_nickName");
        String tags = (String) payload.get("tags");
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
            existingUser.setUser_nickname(user_nickName);
            memberService.updateUserInfo(existingUser);

            // 태그 업데이트 로직 추가
            List<Integer> tagList = Arrays.stream(tags.split(","))
                    .map(Integer::parseInt)
                    .collect(Collectors.toList());
            userTagRepository.deleteByUserId(user_id); // 기존 태그 삭제
            userTagRepository.saveUserTags(user_id, tagList); // 새로운 태그 저장

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
    @ResponseBody
    public ResponseEntity<String> deleteUser(@AuthenticationPrincipal CustomOAuth2User customOAuth2User,
                                             HttpSession session,
                                             HttpServletRequest request,
                                             HttpServletResponse response) {
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
            System.out.println("탈퇴 요청을 받은 사용자 ID: " + userId);

            if (userId != null) {
                try {
                    // 사용자와 연관된 모든 데이터 삭제
                    memberService.deleteRelatedData(userId);

                    // 사용자 삭제
                    memberService.deleteUserByEmail(userId);
                    refreshTokenRepository.deleteRefreshTokensByUsername(userId);
                    session.invalidate();
                    clearAllCookies(request, response);
                    SecurityContextHolder.clearContext();
                    return ResponseEntity.ok("회원 탈퇴가 완료되었습니다.");
                } catch (Exception e) {
                    e.printStackTrace();
                    return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("회원 탈퇴 중 오류가 발생했습니다.");
                }
            }
        }
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("로그인 정보가 없습니다.");
    }

    @DeleteMapping("/admin/deleteUser")
    @ResponseBody
    public ResponseEntity<String> adminDeleteUser(@RequestBody Map<String, String> requestBody) {
        String userId = requestBody.get("userId");

        if (userId != null && !userId.isEmpty()) {
            try {
                // 사용자와 연관된 모든 데이터 삭제
                memberService.deleteRelatedData(userId);

                // 사용자 삭제
                memberService.deleteUserByEmail(userId);
                refreshTokenRepository.deleteRefreshTokensByUsername(userId);
                return ResponseEntity.ok("회원 탈퇴가 완료되었습니다.");
            } catch (Exception e) {
                e.printStackTrace();
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("회원 탈퇴 중 오류가 발생했습니다.");
            }
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("유효한 사용자 ID가 제공되지 않았습니다.");
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

//태그

