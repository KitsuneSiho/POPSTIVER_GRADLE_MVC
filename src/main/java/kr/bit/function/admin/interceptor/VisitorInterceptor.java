package kr.bit.function.admin.interceptor;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import kr.bit.function.admin.dao.VisitorStatisticsDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.Date;

@Component
public class VisitorInterceptor implements HandlerInterceptor {

    @Autowired
    private DataSource dataSource;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String ipAddress = request.getRemoteAddr();

        try (Connection connection = dataSource.getConnection()) {
            VisitorStatisticsDAO dao = new VisitorStatisticsDAO(connection);
            dao.incrementVisitCount(new Date(System.currentTimeMillis()), ipAddress);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return true; // 요청을 계속 처리하도록 true 반환
    }
}
