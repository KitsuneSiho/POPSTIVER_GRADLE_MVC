package kr.bit.function.member.oauth2;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class CustomAccessDeniedHandler implements AccessDeniedHandler {

    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response, AccessDeniedException accessDeniedException) throws IOException, ServletException {
        // 경고 메시지 설정
        request.getSession().setAttribute("errorMessage", "You do not have permission to access this page.");

        // 이전 페이지로 리다이렉션하며 경고 메시지를 URL 파라미터로 추가
        String referer = request.getHeader("Referer");
        if (referer != null) {
            response.sendRedirect(referer + "?accessDenied=true");
        } else {
            response.sendRedirect("/?accessDenied=true");
        }
    }
}