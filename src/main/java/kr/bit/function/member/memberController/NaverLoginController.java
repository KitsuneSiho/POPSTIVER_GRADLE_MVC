package kr.bit.function.member.memberController;

import com.google.gson.JsonObject;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import kr.bit.function.member.service.NaverOAuthService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class NaverLoginController {

    private final NaverOAuthService naverOauthService;

    public NaverLoginController(NaverOAuthService naverOauthService) {
        this.naverOauthService = naverOauthService;
    }

    @GetMapping("/auth/naver/callback")
    public String getNaverLoginInfo(@RequestParam("code") String code, @RequestParam("state") String state, Model model, HttpServletRequest request) {
        System.out.println("Received authorization code: " + code);

        // 액세스 토큰 요청
        String accessToken = naverOauthService.getNaverAccessToken(code);
        if (accessToken == null) {
            System.out.println("Access Token: 없음");
            return "redirect:/login_practice"; // 로그인 실패 시 리디렉션
        } else {
            System.out.println("Access Token: " + accessToken);
        }

        // 사용자 정보 요청
        JsonObject userInfo = naverOauthService.getNaverUserInfo(accessToken);
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
        String userBirthday = userInfo.get("birthday").getAsString();
        String userBirthyear = userInfo.get("birthyear").getAsString();
        String userAgeRange = userInfo.get("age").getAsString(); // age는 연령대

        model.addAttribute("userId", userId);
        model.addAttribute("userNickname", userNickname);
        model.addAttribute("userProfileImage", userProfileImage);
        model.addAttribute("userEmail", userEmail);
        model.addAttribute("userName", userName);
        model.addAttribute("userGender", userGender);
        model.addAttribute("userBirthday", userBirthday);
        model.addAttribute("userBirthyear", userBirthyear);
        model.addAttribute("userAgeRange", userAgeRange);

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

    @PostMapping("/logout/naver")
    public ResponseEntity<String> logoutNaver(HttpSession session) {
        System.out.println("로그아웃 요청 수신됨");

        // 구글 로그아웃 처리
        String accessToken = (String) session.getAttribute("access_Token");
        if (accessToken != null) {
            System.out.println("Access Token: " + accessToken);
            naverOauthService.naverLogout(accessToken); // 네이버 로그아웃 호출
            session.removeAttribute("access_Token");
        } else {
            System.out.println("Access Token 없음");
        }
        // 네이버 로그아웃 처리: 세션 무효화
        session.invalidate();

        // 로그아웃 성공 시, HTTP 상태 코드 200을 반환
        return ResponseEntity.ok("네이버 로그아웃 완료");
    }

}
