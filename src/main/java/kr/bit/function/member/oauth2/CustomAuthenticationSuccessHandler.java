package kr.bit.function.member.oauth2;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import kr.bit.function.member.dto.CustomOAuth2User;
import kr.bit.function.member.dto.UserDTO;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import java.io.IOException;

public class CustomAuthenticationSuccessHandler extends SimpleUrlAuthenticationSuccessHandler {

    public CustomAuthenticationSuccessHandler(String defaultTargetUrl) {
        super(defaultTargetUrl);
        setAlwaysUseDefaultTargetUrl(true);
    }

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        CustomOAuth2User oauthUser = (CustomOAuth2User) authentication.getPrincipal();
        UserDTO userDTO = oauthUser.getUserDTO();

        String targetUrl = "/main";

        // userType에 따라 리디렉션 URL 결정
        switch (userDTO.getUserType()) {
            case 1: // 주최자
                targetUrl = "/host"; // 주최자 페이지 (예시)
                break;
            case 2: // 사용자
                targetUrl = "/user"; // 사용자 페이지 (예시)
                break;
            case 3: // 관리자
                targetUrl = "/admin";
                break;
            default:
                targetUrl = "/error";
                break;
        }

        logger.debug("Authentication successful! Redirecting to: " + targetUrl);
        super.onAuthenticationSuccess(request, response, authentication);
    }
}