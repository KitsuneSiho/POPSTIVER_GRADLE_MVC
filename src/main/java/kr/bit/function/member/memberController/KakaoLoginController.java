package kr.bit.function.member.memberController;

import com.google.gson.JsonObject;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import kr.bit.function.member.service.KakaoOAuthService;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class KakaoLoginController {

    private final KakaoOAuthService kakaoOAuthService;

    public KakaoLoginController(KakaoOAuthService kakaoOAuthService) {
        this.kakaoOAuthService = kakaoOAuthService;
    }

    // 로그인 창 가는 거 임시로...
    @RequestMapping(value="/login_practice", method= RequestMethod.GET)
    public String login_practice() {
        return "/page/login_practice"; // login.jsp로 이동
    }

    @GetMapping("/login/oauth2/code/kakao")
    public String getLoginInfo(@RequestParam("code") String code, Model model, HttpServletRequest request) {
        System.out.println("Received authorization code: " + code);

        // 액세스 토큰 요청
        String accessToken = kakaoOAuthService.getKakaoAccessToken(code);
        if (accessToken == null) {
            System.out.println("Access Token: 없음");
            return "redirect:/login_practice"; // 로그인 실패 시 리디렉션
        } else {
            System.out.println("Access Token: " + accessToken);
        }

        // 사용자 정보 요청
        JsonObject userInfo = kakaoOAuthService.getKakaoUserInfo(accessToken);
        if (userInfo == null) {
            System.out.println("User Info: 없음");
            return "redirect:/login_practice"; // 사용자 정보 가져오기 실패 시 리디렉션
        } else {
            System.out.println("User Info: " + userInfo);
        }

        // 사용자 정보를 모델에 추가
        String userId = userInfo.get("id").getAsString();
        String userNickname = userInfo.get("nickname").getAsString();
        String userProfileImage = userInfo.get("profileImage").getAsString();
        String userEmail = userInfo.get("email").getAsString();
        String userName = userInfo.get("name").getAsString();
        String userGender = userInfo.get("gender").getAsString();
        String userAgeRange = userInfo.get("ageRange").getAsString();
        String userBirthday = userInfo.get("birthday").getAsString();
        String userBirthyear = userInfo.get("birthyear").getAsString();

        model.addAttribute("userId", userId);
        model.addAttribute("userNickname", userNickname);
        model.addAttribute("userProfileImage", userProfileImage);
        model.addAttribute("userEmail", userEmail);
        model.addAttribute("userName", userName);
        model.addAttribute("userGender", userGender);
        model.addAttribute("userAgeRange", userAgeRange);
        model.addAttribute("userBirthday", userBirthday);
        model.addAttribute("userBirthyear", userBirthyear);

        // 사용자 정보를 세션에 저장
        HttpSession session = request.getSession();
        session.setAttribute("userId", userId);
        session.setAttribute("userNickname", userNickname);
        session.setAttribute("userProfileImage", userProfileImage);
        session.setAttribute("userEmail", userEmail);
        session.setAttribute("userName", userName);
        session.setAttribute("userGender", userGender);
        session.setAttribute("userAgeRange", userAgeRange);
        session.setAttribute("userBirthday", userBirthday);
        session.setAttribute("userBirthyear", userBirthyear);

        // 세션에 액세스 토큰 저장
        session = request.getSession();
        if (session == null) {
            System.out.println("세션 생성 실패");
        } else {
            System.out.println("세션 생성 성공");
            session.setAttribute("access_Token", accessToken);
            System.out.println("세션에 액세스 토큰 저장 성공");
        }

        // 사용자 인증 설정
        UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(userId, null, null);
        SecurityContextHolder.getContext().setAuthentication(authentication);

        // 마이페이지로 리디렉션
        return "redirect:/myPage";

    }

    @PostMapping("/logout/kakao")
    public String logout(HttpServletRequest request, HttpSession session) {
        System.out.println("로그아웃 요청 수신됨");

        // 카카오 로그아웃 처리
        String accessToken = (String) session.getAttribute("access_Token");
        if (accessToken != null) {
            System.out.println("Access Token: " + accessToken);
            kakaoOAuthService.kakaoLogout(accessToken); // 카카오 로그아웃 호출
            session.removeAttribute("access_Token");
        } else {
            System.out.println("Access Token 없음");
        }

        // 로컬 세션 무효화
        session.invalidate();
        SecurityContextHolder.clearContext();

        System.out.println("세션 무효화 완료");

        return "redirect:/login"; // 로그아웃 후 로그인 페이지로 리디렉션
    }


}