package kr.bit.function.member.oauth2;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import kr.bit.function.member.repository.RefreshTokenRepository;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;
import org.springframework.stereotype.Component;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

@Component
public class CustomLogoutSuccessHandler implements LogoutSuccessHandler {

    private static final Logger logger = LoggerFactory.getLogger(CustomLogoutSuccessHandler.class);
    private final RefreshTokenRepository refreshTokenRepository;

    public CustomLogoutSuccessHandler(RefreshTokenRepository refreshTokenRepository) {
        this.refreshTokenRepository = refreshTokenRepository;
    }

    @Override
    public void onLogoutSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException {
        if (authentication != null) {
            String username = authentication.getName();
            logger.info("Logging out user: {}", username);

            // DB에서 사용자의 모든 Refresh 토큰 삭제
            try {
                refreshTokenRepository.deleteRefreshTokensByUsername(username);
                logger.info("Deleted refresh tokens for user: {}", username);
            } catch (Exception e) {
                logger.error("Failed to delete refresh tokens for user: {}", username, e);
            }

            // 쿠키 초기화
            clearCookie(request, response, "refreshToken");

            // 인증 정보 제거
            SecurityContextHolder.clearContext();
        } else {
            logger.warn("No user is currently authenticated");
        }

        // 로그아웃 후 메인 페이지로 리디렉션
        response.sendRedirect("/");
    }

    private void clearCookie(HttpServletRequest request, HttpServletResponse response, String cookieName) {
        Cookie[] cookies = request.getCookies();
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals(cookieName)) {
                    cookie.setValue(null);
                    cookie.setPath("/");
                    cookie.setMaxAge(0);
                    response.addCookie(cookie);
                    logger.info("Cleared cookie: {}", cookieName);
                }
            }
        }
    }
}
