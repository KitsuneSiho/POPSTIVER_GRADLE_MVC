package kr.bit.function.admin.dao;

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
        String sql = "SELECT visit_date, visit_count FROM visitor_statistics ORDER BY visit_date";

        try (PreparedStatement statement = connection.prepareStatement(sql);
             ResultSet resultSet = statement.executeQuery()) {

            while (resultSet.next()) {
                VisitorStatistic statistic = new VisitorStatistic();
                statistic.setVisitDate(resultSet.getDate("visit_date"));
                statistic.setVisitCount(resultSet.getInt("visit_count"));
                statistics.add(statistic);
                System.out.println("DAO - Date: " + statistic.getVisitDate() + ", Count: " + statistic.getVisitCount());
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        System.out.println("DAO - Total statistics: " + statistics.size());
        return statistics;
    }

    public void incrementVisitCount(Date visitDate, String ipAddress) {
        if (!isRecentVisit(ipAddress)) {
            String sql = "INSERT INTO visitor_statistics (visit_date, visit_count) VALUES (?, 1) " +
                    "ON DUPLICATE KEY UPDATE visit_count = visit_count + 1";
            String logSql = "INSERT INTO visitor_logs (ip_address, last_visit) VALUES (?, NOW()) " +
                    "ON DUPLICATE KEY UPDATE last_visit = NOW()";

            try (PreparedStatement statement = connection.prepareStatement(sql);
                 PreparedStatement logStatement = connection.prepareStatement(logSql)) {
                statement.setDate(1, visitDate);
                statement.executeUpdate();

                logStatement.setString(1, ipAddress);
                logStatement.executeUpdate();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    private boolean isRecentVisit(String ipAddress) {
        String sql = "SELECT last_visit FROM visitor_logs WHERE ip_address = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, ipAddress);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    Timestamp lastVisit = resultSet.getTimestamp("last_visit");
                    Timestamp currentTime = new Timestamp(System.currentTimeMillis());
                    long difference = currentTime.getTime() - lastVisit.getTime();
                    long threshold = 30 * 60 * 1000; // 30 minutes in milliseconds
                    return difference < threshold;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
}
