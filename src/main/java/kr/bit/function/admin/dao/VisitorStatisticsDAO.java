package kr.bit.function.admin.dao;

import jakarta.servlet.http.HttpSession;
import kr.bit.function.admin.model.VisitorStatistic;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class VisitorStatisticsDAO {
    private Connection connection;

    public VisitorStatisticsDAO(Connection connection) {
        this.connection = connection;
    }

    public List<VisitorStatistic> getVisitorStatistics() {
        List<VisitorStatistic> statistics = new ArrayList<>();
        String sql = "SELECT visit_date, SUM(visit_count) as visit_count FROM visitor_statistics GROUP BY visit_date ORDER BY visit_date";

        try (PreparedStatement statement = connection.prepareStatement(sql);
             ResultSet resultSet = statement.executeQuery()) {

            while (resultSet.next()) {
                VisitorStatistic statistic = new VisitorStatistic();
                statistic.setVisitDate(resultSet.getDate("visit_date"));
                statistic.setVisitCount(resultSet.getInt("visit_count"));
                statistics.add(statistic);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return statistics;
    }

    public void incrementVisitCount(Date visitDate, String ipAddress, HttpSession session) {
        Boolean recentVisit = (Boolean) session.getAttribute("recentVisit");
        Timestamp lastVisit = (Timestamp) session.getAttribute("lastVisit");

        Timestamp currentTime = new Timestamp(System.currentTimeMillis());
        long threshold = 30 * 60 * 1000; // 30 minutes in milliseconds

        if (recentVisit == null || lastVisit == null || (currentTime.getTime() - lastVisit.getTime()) >= threshold) {
            String sql = "INSERT INTO visitor_statistics (visit_date, visit_count) VALUES (?, 1) " +
                    "ON DUPLICATE KEY UPDATE visit_count = visit_count + 1";
            String logSql = "INSERT INTO visitor_logs (ip_address, last_visit) VALUES (?, ?) " +
                    "ON DUPLICATE KEY UPDATE last_visit = ?";

            try (PreparedStatement statement = connection.prepareStatement(sql);
                 PreparedStatement logStatement = connection.prepareStatement(logSql)) {
                statement.setDate(1, visitDate);
                statement.executeUpdate();

                logStatement.setString(1, ipAddress);
                logStatement.setTimestamp(2, currentTime);
                logStatement.setTimestamp(3, currentTime);
                logStatement.executeUpdate();

                session.setAttribute("recentVisit", true);
                session.setAttribute("lastVisit", currentTime);
                session.setMaxInactiveInterval(30 * 60); // 세션 타임아웃을 30분으로 설정
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
