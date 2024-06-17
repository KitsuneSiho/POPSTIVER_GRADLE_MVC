package kr.bit.function.member.memberController;

import com.google.gson.JsonObject;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import kr.bit.function.member.service.GoogleOAuthService;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class GoogleLoginController {

    private final GoogleOAuthService googleoauthService;

    public GoogleLoginController(GoogleOAuthService googleoauthService) {
        this.googleoauthService = googleoauthService;
    }

    // 구글 로그인 콜백 처리
    @GetMapping("/auth/google/callback")
    public String handleGoogleCallback(@RequestParam("code") String code, Model model, HttpServletRequest request) {
        // 구글 OAuth 처리
        String accessToken = googleoauthService.getGoogleAccessToken(code);
        JsonObject userInfo = googleoauthService.getGoogleUserInfo(accessToken);
        String userEmail = userInfo.get("email").getAsString();
        String userName = userInfo.get("name").getAsString();
        model.addAttribute("userEmail", userEmail);
        model.addAttribute("userName", userName);
        // 사용자 정보를 세션에 저장
        HttpSession session = request.getSession();
        session.setAttribute("userEmail", userEmail);
        session.setAttribute("userName", userName);

        // 세션에 액세스 토큰 저장
        session = request.getSession();
        if (session == null) {
            System.out.println("세션 생성 실패");
        } else {
            System.out.println("세션 생성 성공");
            session.setAttribute("access_Token", accessToken);
            System.out.println("세션에 액세스 토큰 저장 성공");
        }

        // 마이페이지로 리디렉션
        return "redirect:/myPage";
    }

    @PostMapping("/logout/google")
    public String logoutGoogle(HttpServletRequest request, HttpSession session) {
        System.out.println("로그아웃 요청 수신됨");

        // 구글 로그아웃 처리
        String accessToken = (String) session.getAttribute("access_Token");
        if (accessToken != null) {
            System.out.println("Access Token: " + accessToken);
            googleoauthService.googleLogout(accessToken); // 구글 로그아웃 호출
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
