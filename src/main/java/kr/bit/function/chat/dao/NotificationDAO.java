package kr.bit.function.chat.dao;

import kr.bit.function.chat.model.Notification;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class NotificationDAO {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    private static final String INSERT_NOTIFICATION_SQL = "INSERT INTO notifications (sender, message, timestamp) VALUES (?, ?, ?)";
    private static final String SELECT_ALL_NOTIFICATIONS_SQL = "SELECT * FROM notifications";
    private static final String DELETE_ALL_NOTIFICATIONS_SQL = "DELETE FROM notifications";

    public void addNotification(Notification notification) {
        jdbcTemplate.update(INSERT_NOTIFICATION_SQL, notification.getSender(), notification.getMessage(), notification.getTimestamp());
    }

    public List<Notification> getNotifications() {
        return jdbcTemplate.query(SELECT_ALL_NOTIFICATIONS_SQL, new NotificationRowMapper());
    }

    public void clearNotifications() {
        jdbcTemplate.update(DELETE_ALL_NOTIFICATIONS_SQL);
    }

    private static class NotificationRowMapper implements RowMapper<Notification> {
        @Override
        public Notification mapRow(ResultSet rs, int rowNum) throws SQLException {
            Notification notification = new Notification();
            notification.setId(rs.getInt("id"));
            notification.setSender(rs.getString("sender"));
            notification.setMessage(rs.getString("message"));
            notification.setTimestamp(rs.getTimestamp("timestamp").toLocalDateTime());
            return notification;
        }
    }
}
